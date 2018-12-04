package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Business;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.Role;
import org.hamcrest.Matcher;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class PartyDAOTest extends DAOTest {

    private PartyDAOImpl partyDAO;
    private BusinessDAOImpl businessDAO;

    private Calendar currentDate = Calendar.getInstance();
    private Calendar expiredDate = Calendar.getInstance();

    @Override
    protected void onSetUp(EntityManager entityManager) {

        partyDAO = new PartyDAOImpl();
        partyDAO.entityManager = entityManager;

        businessDAO = new BusinessDAOImpl();
        businessDAO.entityManager = entityManager;

        currentDate.set(2012, 9, 10); //10-SEP-2012
        expiredDate.set(2012, 7, 10); //10-JUL-2012

        insertUser(1L, "user1", "user1", currentDate.getTime(), 1L, true);

        insertBusiness(1L, "EDMA", currentDate.getTime(), 1L, true);

        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(5L, "party@party5.eu", "Party5", "ref5", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(7L, "party@party7.eu", "Party7", "ref7", 1L, expiredDate.getTime(), 1L, true, true);
        insertParty(8L, "party@party8.eu", "Party8", "ref8", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(6L, "party@party6.eu", "Party6", "ref6", 1L, currentDate.getTime(), 1L, true, true);

        insertUser(101L, "user101", "user101", currentDate.getTime(), 1L, true);
        insertUser(102L, "user102", "user102", currentDate.getTime(), 1L, true);

        insertRole(Role.Type.OPERATOR);

        insertUserRole(1L, 101L, Role.Type.OPERATOR, 4L, null, currentDate.getTime(), 1L, true);
        insertUserRole(2L, 101L, Role.Type.OPERATOR, 8L, null, currentDate.getTime(), 1L, true);
    }

    @Test
    public void testFindPartyById() {
        Party pd = partyDAO.findById(4L);
        assertThat("Party not found", pd, notNullValue());
    }

    @Test
    public void testFindPartyByIdMissing() {
        Party pd = partyDAO.findById(2000L);
        assertThat("Party should be null", pd, nullValue());
    }


    @Test
    public void testGetListOfAvailablePartiesPerApplication() {
        List<String> pdList = partyDAO.getAvailablePartiesPerBusiness("EDMA");
        assertThat("Party not found", pdList, notNullValue());

        //Should have 3 unassigned and EDMA specific parties
        assertThat(pdList, hasSize(3));
    }


    @Test
    public void testIsPartyAssignedFalse() {
        boolean result = partyDAO.isPartyAlreadyAssigned("ref5");
        assertThat(result, is(Boolean.FALSE));
    }


    @Test
    public void testIsPartyAssignedTrue() {
        boolean result = partyDAO.isPartyAlreadyAssigned("ref4");
        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void testUpdate_updateSuccess() {
        Party testParty = partyDAO.findById(4L);
        testParty.setEmail("updated.address@test.com");

        testParty = partyDAO.update(testParty);

        assertThat(testParty, notNullValue());
        assertThat(testParty.getId(), is(4L));
        assertThat(testParty.getEmail(), is("updated.address@test.com"));
        assertThat(testParty.getDisplayName(), is("Party4"));
        assertThat(testParty.getNodeName(), is("ref4"));
    }

    @Test
    public void test_getPartiesOfBusiness_should_returnPartiesOfBusiness() {

        insertBusiness(2L, "EDMA2", currentDate.getTime(), 1L, true);

        insertParty(12L, "party@party4.eu", "Party4", "ref12", 2L, currentDate.getTime(), 1L, true, true);
        insertParty(13L, "party@party4.eu", "Party45", "ref13", 2L, currentDate.getTime(), 1L, true, true);

        //ACTUAL CALL
        Business business = businessDAO.findById(2L);

        List<Party> partiesOfBusiness = partyDAO.getPartiesOfBusiness(business);

        assertThat(partiesOfBusiness, hasSize(2));
        assertThat(partiesOfBusiness, (Matcher) hasItem(hasProperty("nodeName", is("ref12"))));
        assertThat(partiesOfBusiness, (Matcher) hasItem(hasProperty("nodeName", is("ref13"))));
    }

    @Test
    public void test_getPartiesOfBusiness_should_returnNoParties_when_businessIsNotActive() {

        insertBusiness(2L, "EDMA2", currentDate.getTime(), 1L, false);

        insertParty(12L, "party@party4.eu", "Party4", "ref4", 2L, currentDate.getTime(), 1L, true, true);

        //ACTUAL CALL
        Business business = businessDAO.findById(2L);

        List<Party> partiesOfBusiness = partyDAO.getPartiesOfBusiness(business);

        assertThat(partiesOfBusiness, hasSize(0));
    }

    @Test
    public void test_getPartiesOfBusiness_should_returnNoParties_when_partiesAreNotActive() {

        insertBusiness(2L, "EDMA2", currentDate.getTime(), 1L, true);

        insertParty(12L, "party@party4.eu", "Party4", "ref4", 2L, currentDate.getTime(), 1L, false, true);
        insertParty(11L, "party@party4.eu", "Party4", "ref4", 2L, currentDate.getTime(), 1L, false, true);

        //ACTUAL CALL
        Business business = businessDAO.findById(2L);

        List<Party> partiesOfBusiness = partyDAO.getPartiesOfBusiness(business);

        assertThat(partiesOfBusiness, hasSize(0));
    }

    @Test
    public void test_getAllParties_should_returnOnlyActiveParties() {

        insertParty(15L, "party@party4.eu", "Party", "ref15", 1L, currentDate.getTime(), 1L, false, true);
        insertParty(16L, "party@party4.eu", "Party", "ref16", 1L, currentDate.getTime(), 1L, false, true);

        //ACTUAL CALL
        List<Party> allParties = partyDAO.getAllParties();

        assertEquals(5, allParties.size());
        assertThat(allParties, (Matcher) hasItem(hasProperty("nodeName", is("ref4"))));
        assertThat(allParties, (Matcher) hasItem(hasProperty("nodeName", is("ref5"))));
        assertThat(allParties, (Matcher) hasItem(hasProperty("nodeName", is("ref6"))));
        assertThat(allParties, (Matcher) hasItem(hasProperty("nodeName", is("ref7"))));
        assertThat(allParties, (Matcher) hasItem(hasProperty("nodeName", is("ref8"))));
    }

    @Test
    public void test_findWebManagedPartyByNodeName_should_return_party() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);

        //ACTUAL CALL
        Party party = partyDAO.getWebManagedPartyByNodeName("ref");

        assertThat(party, notNullValue());
        assertThat(party.getId(), is(equalTo(1L)));
        assertThat(party.getNodeName(), is(equalTo("ref")));
    }

    @Test
    public void test_findWebManagedPartyByNodeName_should_return_null() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);

        //ACTUAL CALL
        Party party = partyDAO.getWebManagedPartyByNodeName("nodeName");

        assertThat(party, nullValue());
    }

    @Test
    public void test_getRemotePartyByNodeNameBusId_should_return_party() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);

        //ACTUAL CALL
        Party party = partyDAO.getRemotePartyByNodeNameBusId("ref", 1L);

        assertThat(party, notNullValue());
        assertThat(party.getId(), is(equalTo(1L)));
        assertThat(party.getNodeName(), is(equalTo("ref")));
    }

    @Test
    public void test_getRemotePartyByNodeNameBusId_should_return_null() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);

        //ACTUAL CALL
        Party party = partyDAO.getRemotePartyByNodeNameBusId("nodeName", 1L);

        assertThat(party, nullValue());
    }

    @Test
    public void test_getRemoteParties_should_return_active_parties() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty1.eu", "RemoteParty1", "rem1", 1L, currentDate.getTime(), 1L, true, false);
        insertParty(3L, "party@remoteParty2.eu", "RemoteParty2", "rem2", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);
        insertPartyIca(2L, 1L, 3L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        Party party = partyDAO.findById(1L);

        //ACTUAL CALL
        List<Party> remoteParties = partyDAO.getRemoteParties(party);

        assertEquals(2, remoteParties.size());
        assertThat(remoteParties, (Matcher) hasItem(hasProperty("id", is(2L))));
        assertThat(remoteParties, (Matcher) hasItem(hasProperty("id", is(3L))));
    }

    @Test
    public void test_getRemoteParties_should_return_empty() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);

        Party party = partyDAO.findById(1L);

        //ACTUAL CALL
        List<Party> remoteParties = partyDAO.getRemoteParties(party);

        assertEquals(0, remoteParties.size());
    }

}
