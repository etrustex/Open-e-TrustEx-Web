package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.*;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class BusinessUserConfigDAOTest extends DAOTest {

    private BusinessUserConfigDAOImpl businessUserConfigDAO;

    private PartyDAOImpl partyDAO;

    private BusinessDAOImpl businessDAO;

    @Override
    protected void onSetUp(EntityManager entityManager) {
        businessUserConfigDAO = new BusinessUserConfigDAOImpl();
        businessUserConfigDAO.entityManager = entityManager;

        partyDAO = new PartyDAOImpl();
        partyDAO.entityManager = entityManager;

        businessDAO = new BusinessDAOImpl();
        businessDAO.entityManager = entityManager;

        insertRoleWithPrivilege(Role.Type.OPERATOR, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        insertRoleWithPrivilege(Role.Type.BUSINESS_ADMIN, Privilege.Type.CAN_DEFINE_BUSINESS);
        insertRole(Role.Type.SYSTEM_ADMIN);
        assignPrivilegeToRole(Role.Type.SYSTEM_ADMIN, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

    }

    @Test
    public void test_getBusinessUserConfigsByParty_should_returnNoUsersForParty_when_userIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, false);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByParty_should_returnNoUsersForParty_when_businessIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, false);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByParty_should_returnNoUsersForParty_when_partyIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, false, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByParty_should_returnNoUsersForParty_when_businessUserConfigIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, false);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByParty_should_returnNoUsersForParty_when_userRoleIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, false);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByParty_should_returnBUCs_when_privilegesAreGranted() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        insertUser(2L, "user2", "user2", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);
        insertUserRole(2L, 2L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        MatcherAssert.assertThat(result, (Matcher) hasItem(hasProperty("id", is(1L))));
        MatcherAssert.assertThat(result, (Matcher) hasItem(hasProperty("id", is(2L))));

        assertEquals(2, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByParty_should_returnNoBUCs_when_privilegesAreNotGranted() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        insertUser(2L, "user2", "user2", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);
        insertUserRole(2L, 2L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(party, Privilege.Type.CAN_DEFINE_BUSINESS);

        assertEquals(0, result.size());
    }


    @Test
    public void test_getBusinessUserConfigsByBusiness_should_returnNoUsersForParty_when_userIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, false);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, null, 1L, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByBusiness_should_returnNoUsersForParty_when_businessIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, false);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, null, 1L, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByBusiness_should_returnNoUsersForParty_when_businessUserConfigIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, false);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, null, 1L, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByBusiness_should_returnNoUsersForParty_when_userRoleIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, null, 1L, currentDate, 1L, false);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }


    @Test
    public void test_getBusinessUserConfigsByBusiness_should_returnBUCs_when_privilegesAreGranted() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, null, 1L, currentDate, 1L, true);

        insertUser(2L, "user2", "user2", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);
        insertUserRole(2L, 2L, Role.Type.OPERATOR, null, 1L, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        MatcherAssert.assertThat(result, (Matcher) hasItem(hasProperty("id", is(1L))));
        MatcherAssert.assertThat(result, (Matcher) hasItem(hasProperty("id", is(2L))));

        assertEquals(2, result.size());
    }

    @Test
    public void test_getBusinessUserConfigsByBusiness_should_returnNoBUCs_when_privilegesAreNotGranted() {
        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, null, 1L, currentDate, 1L, true);

        insertUser(2L, "user2", "user2", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);
        insertUserRole(2L, 2L, Role.Type.OPERATOR, null, 1L, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getBusinessUserConfigs(business, Privilege.Type.CAN_ASSIGN_SYSTEM_SCOPE_ROLES);

        assertEquals(0, result.size());
    }


    @Test
    public void test_getSystemScopeBusinessUserConfigs_should_returnBUCs_when_privilegesAreNotGranted() {
        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.SYSTEM_ADMIN, null, null, currentDate, 1L, true);

        insertUser(2L, "user2", "user2", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);
        insertUserRole(2L, 2L, Role.Type.SYSTEM_ADMIN, null, null, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getSystemScopeBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        assertEquals(2, result.size());

        MatcherAssert.assertThat(result, (Matcher) hasItem(hasProperty("id", is(1L))));
        MatcherAssert.assertThat(result, (Matcher) hasItem(hasProperty("id", is(2L))));
    }

    @Test
    public void test_getSystemScopeBusinessUserConfigs_should_returnNoBUCs_when_privilegesAreNotGranted() {
        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.SYSTEM_ADMIN, null, null, currentDate, 1L, true);

        insertUser(2L, "user2", "user2", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);
        insertUserRole(2L, 2L, Role.Type.SYSTEM_ADMIN, null, null, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getSystemScopeBusinessUserConfigs(business, Privilege.Type.CAN_MANAGE_BUSINESS_CONFIGURATIONS);
        assertEquals(0, result.size());
    }

    @Test
    public void test_getSystemScopeBusinessUserConfigs_should_returnNoUsersForParty_when_userIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, false);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.SYSTEM_ADMIN, null, null, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getSystemScopeBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getSystemScopeBusinessUserConfigs_should_returnNoUsersForParty_when_businessIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, false);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.SYSTEM_ADMIN, null, null, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getSystemScopeBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getSystemScopeBusinessUserConfigs_should_returnNoUsersForParty_when_businessUserConfigIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, false);
        insertUserRole(1L, 1L, Role.Type.SYSTEM_ADMIN, null, null, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getSystemScopeBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getSystemScopeBusinessUserConfigs_should_returnNoUsersForParty_when_userRoleIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.SYSTEM_ADMIN, null, null, currentDate, 1L, false);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<BusinessUserConfig> result = businessUserConfigDAO.getSystemScopeBusinessUserConfigs(business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

}
