package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO;
import org.hamcrest.Matcher;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class PartyIcaDAOTest extends DAOTest {

    private PartyIcaDAOImpl partyIcaDAO;

    private PartyDAOImpl partyDAO;

    private Calendar currentDate = Calendar.getInstance();

    @Override
    protected void onSetUp(EntityManager entityManager) {
        partyIcaDAO = new PartyIcaDAOImpl();
        partyIcaDAO.entityManager = entityManager;

        partyDAO = new PartyDAOImpl();
        partyDAO.entityManager = entityManager;

        currentDate.set(2017, 02, 07); //07-FEB-2017

        insertUser(1L, "user1", "user1", currentDate.getTime(), 1L, true);

        insertBusiness(1L, "EDMA", currentDate.getTime(), 1L, true);
    }

    @Test
    public void testFindPartyIcaById() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        //ACTUAL CALL
        PartyIca partyIca = partyIcaDAO.findById(1L);
        assertThat("Party ICA not found", partyIca, notNullValue());
    }

    @Test
    public void testFindPartyIcaById_Missing() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        //ACTUAL CALL
        PartyIca partyIca = partyIcaDAO.findById(10L);
        assertThat("Party ICA should be null", partyIca, nullValue());
    }

    @Test
    public void testUpdate_updateSuccess() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        PartyIca testPartyIca = partyIcaDAO.findById(1L);
        Party remoteParty = testPartyIca.getRemoteParty();
        remoteParty.setDisplayName("Test display name");
        testPartyIca.setRemoteParty(remoteParty);

        //ACTUAL CALL
        testPartyIca = partyIcaDAO.update(testPartyIca);

        assertThat(testPartyIca, notNullValue());
        assertThat(testPartyIca.getId(), is(1L));
        assertThat(testPartyIca.getRemoteParty().getDisplayName(), is("Test display name"));
        assertThat(testPartyIca.getRemoteParty().getNodeName(), is("rem"));
    }

    @Test
    public void test_getIcasByParty_should_returnServeralIcas() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);
        insertPartyIca(2L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        Party party = partyDAO.findById(1L);

        //ACTUAL CALL
        List<PartyIca> receiverParties = partyIcaDAO.getIcasByParty(party);

        assertThat(receiverParties, hasSize(2));
        assertThat(receiverParties, (Matcher) hasItem(hasProperty("id", is(1L))));
        assertThat(receiverParties, (Matcher) hasItem(hasProperty("id", is(2L))));
    }

    @Test
    public void test_getIcasByParty_should_returnOneIca() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        Party party = partyDAO.findById(1L);

        //ACTUAL CALL
        List<PartyIca> receiverParties = partyIcaDAO.getIcasByParty(party);

        assertThat(receiverParties, hasSize(1));
        assertThat(receiverParties, (Matcher) hasItem(hasProperty("id", is(1L))));
    }

    @Test
    public void test_getIcasByParty_should_returnZeroIca() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);

        Party party = partyDAO.findById(1L);

        //ACTUAL CALL
        List<PartyIca> receiverParties = partyIcaDAO.getIcasByParty(party);

        assertThat(receiverParties, hasSize(0));
    }

    @Test
    public void test_getIcasByParty_should_returnOnlyActiveIcas() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, false);
        insertPartyIca(2L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        Party party = partyDAO.findById(1L);

        //ACTUAL CALL
        List<PartyIca> receiverParties = partyIcaDAO.getIcasByParty(party);

        assertThat(receiverParties, hasSize(1));
        assertThat(receiverParties, (Matcher) hasItem(hasProperty("id", is(2L))));
    }

    @Test
    public void test_getIcasByActiveParties_should_returnOnlyActiveIcas() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);
        insertPartyIca(2L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);
        insertPartyIca(3L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, false);
        insertPartyIca(4L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, false);

        //ACTUAL CALL
        List<PartyIca> icas = partyIcaDAO.getIcasByActiveParties();

        assertEquals(4, icas.size());
        assertThat(icas, (Matcher) hasItem(hasProperty("id", is(1L))));
        assertThat(icas, (Matcher) hasItem(hasProperty("id", is(2L))));
        assertThat(icas, (Matcher) hasItem(hasProperty("id", is(3L))));
        assertThat(icas, (Matcher) hasItem(hasProperty("id", is(4L))));
    }

    @Test
    public void test_getIcaBySenderReceiver_should_returnIca() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        Party party = partyDAO.findById(1L);
        String receiver = "rem";

        //ACTUAL CALL
        PartyIca partyIca = partyIcaDAO.getIcaBySenderReceiver(party.getNodeName(), receiver);

        assertThat("Party ICA not found", partyIca, notNullValue());
        assertThat(partyIca, hasProperty("id", is(1L)));
        assertThat(partyIca, hasProperty("party", is(party)));
    }

    @Test
    public void test_getIcaBySenderReceiver_should_returnNull() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        Party party = partyDAO.findById(1L);
        String receiver = "nodeName";

        //ACTUAL CALL
        PartyIca partyIca = partyIcaDAO.getIcaBySenderReceiver(party.getNodeName(), receiver);

        assertThat("Party ICA shoud be null", partyIca, nullValue());
    }

    @Test
    public void test_getActiveIcaBySenderReceiver_should_returnIca() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);
        insertPartyIca(2L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, false);

        Party party = partyDAO.findById(1L);
        String receiver = "rem";

        //ACTUAL CALL
        PartyIca partyIca = partyIcaDAO.getActiveIcaBySenderReceiver(party.getNodeName(), receiver);

        assertThat("Party ICA not found", partyIca, notNullValue());
        assertThat(partyIca, hasProperty("id", is(1L)));
        assertThat(partyIca, hasProperty("party", is(party)));
    }

    @Test
    public void test_getActiveIcaBySenderReceiver_should_returnNull() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        Party party = partyDAO.findById(1L);
        String receiver = "nodeName";

        //ACTUAL CALL
        PartyIca partyIca = partyIcaDAO.getActiveIcaBySenderReceiver(party.getNodeName(), receiver);

        assertThat("Party ICA shoud be null", partyIca, nullValue());
    }

    @Test
    public void test_getAllIcasSenderReceiver_should_returnOnlyActiveIcas() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);
        insertPartyIca(2L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);
        insertPartyIca(3L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, false);
        insertPartyIca(4L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, false);

        //ACTUAL CALL
        List<PartyIcaVO> icas = partyIcaDAO.getAllIcasSenderReceiver();

        assertEquals(2, icas.size());
        assertThat(icas, (Matcher) hasItem(hasProperty("id", is(1L))));
        assertThat(icas, (Matcher) hasItem(hasProperty("id", is(2L))));
    }

    @Test
    public void test_icaExists_should_returnTrue() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);
        insertPartyIca(2L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, false);

        Party party = partyDAO.findById(1L);
        String receiver = "rem";

        //ACTUAL CALL
        boolean result = partyIcaDAO.icaExists(party.getNodeName(), receiver);

        assertThat(result, is(true));
    }

    @Test
    public void test_icaExists_should_returnFalse() {
        insertParty(1L, "party@party.eu", "Party", "ref", 1L, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@remoteParty.eu", "RemoteParty", "rem", 1L, currentDate.getTime(), 1L, true, false);
        insertPartyIca(1L, 1L, 2L, 1L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1L, true);

        Party party = partyDAO.findById(1L);
        String receiver = "nodeName";

        //ACTUAL CALL
        boolean result = partyIcaDAO.icaExists(party.getNodeName(), receiver);

        assertThat(result, is(false));
    }

}
