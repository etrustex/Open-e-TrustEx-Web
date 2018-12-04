package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.*;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

/**
 * @author: micleva
 * @date: 10/22/13 8:18 AM
 * @project: ETX
 */
public class UserRoleDAOImplTest extends DAOTest {

    private UserRoleDAOImpl userRoleDAO;

    private PartyDAOImpl partyDAO;

    private BusinessDAOImpl businessDAO;

    @Override
    protected void onSetUp(EntityManager entityManager) {
        userRoleDAO = new UserRoleDAOImpl();
        userRoleDAO.entityManager = entityManager;

        partyDAO = new PartyDAOImpl();
        partyDAO.entityManager = entityManager;

        businessDAO = new BusinessDAOImpl();
        businessDAO.entityManager = entityManager;

        insertRoleWithPrivilege(Role.Type.OPERATOR, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
    }

    @Test
    public void testGetUserRoles() throws Exception {
        User user = new User();
        user.setId(101L);
        user.setName("user101_name");
        user.setLogin("user101_login");
        user.setCreatedOn(new Date());
        user.setActiveState(true);

        insertUser(user.getId(), user.getLogin(), user.getName(), user.getCreatedOn(), user.getId(), true);
        insertUser(102L, "user102", "user102", new Date(), user.getId(), true);

        insertRoleWithPrivilege(Role.Type.SYSTEM_ADMIN, Privilege.Type.CAN_MANAGE_PARTIES_FOR_BUSINESS);

        long businessId = 123L;
        long partyId = 33L;
        insertBusiness(businessId, "EDMA", new Date(), user.getId(), true);
        insertParty(partyId, "party@party4.eu", "Party4", "ref4", businessId, new Date(), user.getId(), true, true);

        insertUserRole(1L, user.getId(), Role.Type.OPERATOR, partyId, null, new Date(), user.getId(), true);
        insertUserRole(2L, user.getId(), Role.Type.SYSTEM_ADMIN, partyId, null, new Date(), user.getId(), true);
        insertUserRole(3L, user.getId(), Role.Type.OPERATOR, partyId, null, new Date(), user.getId(), true);
        insertUserRole(4L, user.getId(), Role.Type.OPERATOR, partyId, null, new Date(), user.getId(), false);
        insertUserRole(5L, 102L, Role.Type.OPERATOR, partyId, null, new Date(), 102L, true);

        List<UserRole> userRoles = userRoleDAO.getUserRoles(user);

        assertThat(userRoles, is(notNullValue()));
        assertThat(userRoles.size(), is(3));

        assertThat(userRoles, (Matcher) hasItem(hasProperty("id", is(1L))));
        assertThat(userRoles, (Matcher) hasItem(hasProperty("id", is(2L))));
        assertThat(userRoles, (Matcher) hasItem(hasProperty("id", is(3L))));
    }

    @Test
    public void test_getBusinessUserConfigs_should_returnUsersForParty_when_partyHasUsersOfRoleSpecified() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertUser(2L, "user2", "user2", currentDate, 1L, true);

        insertBusiness(1L, "EDMA", currentDate, 1L, true);

        insertParty(5L, "party@party5.eu", "Party5", "ref5", 1L, currentDate, 1L, true, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);
        insertParty(7L, "party@party7.eu", "Party7", "ref7", 1L, currentDate, 1L, true, true);
        insertParty(8L, "party@party8.eu", "Party8", "ref8", 1L, currentDate, 1L, true, true);
        insertParty(6L, "party@party6.eu", "Party6", "ref6", 1L, currentDate, 1L, true, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);

        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);
        insertUserRole(2L, 2L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        Map<UserRole, BusinessUserConfig> result = userRoleDAO.getUserRoles(party, Role.Type.OPERATOR);

        assertEquals(2, result.size());
        assertThat(result.values(), (Matcher) Matchers.hasItem(hasProperty("name", is("bu1"))));
        assertThat(result.values(), (Matcher) Matchers.hasItem(hasProperty("name", is("bu2"))));
    }

    @Test
    public void test_getBusinessUserConfigs_should_returnNoUsersForParty_when_partyHasNoUsersOfRoleSpecified() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertUser(2L, "user2", "user2", currentDate, 1L, true);

        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(5L, "party@party5.eu", "Party5", "ref5", 1L, currentDate, 1L, true, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);
        insertParty(7L, "party@party7.eu", "Party7", "ref7", 1L, currentDate, 1L, true, true);
        insertParty(8L, "party@party8.eu", "Party8", "ref8", 1L, currentDate, 1L, true, true);
        insertParty(6L, "party@party6.eu", "Party6", "ref6", 1L, currentDate, 1L, true, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);

        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);
        insertUserRole(2L, 2L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, true);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        Map<UserRole, BusinessUserConfig> result = userRoleDAO.getUserRoles(party, Role.Type.SYSTEM_ADMIN);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigs_should_returnNoUsersForParty_when_noUserRoleRelation() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertUser(2L, "user2", "user2", currentDate, 1L, true);

        insertBusiness(1L, "EDMA", currentDate, 1L, true);

        insertParty(5L, "party@party5.eu", "Party5", "ref5", 1L, currentDate, 1L, true, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);
        insertParty(7L, "party@party7.eu", "Party7", "ref7", 1L, currentDate, 1L, true, true);
        insertParty(8L, "party@party8.eu", "Party8", "ref8", 1L, currentDate, 1L, true, true);
        insertParty(6L, "party@party6.eu", "Party6", "ref6", 1L, currentDate, 1L, true, true);

        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertBusinessUserConfig(2L, 2L, 1L, "bu2", "email2", currentDate, 1L, true);


        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        Map<UserRole, BusinessUserConfig> result = userRoleDAO.getUserRoles(party, Role.Type.OPERATOR);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getBusinessUserConfigs_should_returnNoUsersForParty_when_UserRoleIsNotActive() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", 1L, currentDate, 1L, true, true);
        insertBusinessUserConfig(1L, 1L, 1L, "bu1", "email1", currentDate, 1L, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 4L, null, currentDate, 1L, false);

        Party party = partyDAO.findById(4L);

        //ACTUAL CALL
        Map<UserRole, BusinessUserConfig> result = userRoleDAO.getUserRoles(party, Role.Type.OPERATOR);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getUserRolesWithPrivilege_should_returnEmptyList_when_noUserRoleAssignmentsForThisUserOfRoleToBusiness() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertUser(2L, "user2", "user2", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(1L, "party@party5.eu", "Party5", "ref5", 1L, currentDate, 1L, true, true);
        insertUserRole(1L, 2L, Role.Type.OPERATOR, 1L, null, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<UserRole> result = userRoleDAO.getUserRolesWithPrivilege("user1", business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(0, result.size());
    }

    @Test
    public void test_getUserRolesWithPrivilege_should_returnRecord_when_existUserRoleAssignmentForThisUserOfRoleToBusiness() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(1L, "party@party5.eu", "Party5", "ref5", 1L, currentDate, 1L, true, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 1L, null, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<UserRole> result = userRoleDAO.getUserRolesWithPrivilege("user1", business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        assertEquals(1, result.size());
        assertThat(result, (Matcher) Matchers.hasItem(hasProperty("id", is(1L))));
    }

    @Test
    public void test_getUserRolesWithPrivilege_should_returnEmptyList_when_noMatchingPrivilegeFound() {

        Date currentDate = new Date();

        insertUser(1L, "user1", "user1", currentDate, 1L, true);
        insertBusiness(1L, "EDMA", currentDate, 1L, true);
        insertParty(1L, "party@party5.eu", "Party5", "ref5", 1L, currentDate, 1L, true, true);
        insertUserRole(1L, 1L, Role.Type.OPERATOR, 1L, null, currentDate, 1L, true);

        Business business = businessDAO.findById(1L);

        //ACTUAL CALL
        List<UserRole> result = userRoleDAO.getUserRolesWithPrivilege("user1", business, Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES);

        assertEquals(0, result.size());
    }

}
