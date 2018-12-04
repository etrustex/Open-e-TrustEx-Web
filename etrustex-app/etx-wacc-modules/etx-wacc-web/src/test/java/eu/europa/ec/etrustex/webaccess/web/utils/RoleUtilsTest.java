package eu.europa.ec.etrustex.webaccess.web.utils;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.*;

import static eu.europa.ec.etrustex.webaccess.web.ModelHelper.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleUtilsTest extends AbstractTest {

    @InjectMocks
    private RoleUtils roleUtils;

    @Test
    public void test_filterUserRolesByPrivilege_should_filterOnlyRolesWithPrivilege() throws Exception {
        List<UserRole> userRoles = new ArrayList<>();

        Privilege p1 = buildPrivilege("whatever", Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES);
        Role r1 = buildRole(null, Role.Type.OPERATOR, Collections.singleton(p1));
        UserRole ur1 = buildUserRole(1L, null, r1, null, null);
        userRoles.add(ur1);

        Role r2 = buildRole(null, Role.Type.BUSINESS_ADMIN, Collections.singleton(p1));
        UserRole ur2 = buildUserRole(1L, null, r2, null, null);
        userRoles.add(ur2);

        Privilege p3 = buildPrivilege("whatever", Privilege.Type.CAN_MANAGE_APP_CONFIGURATIONS);
        Role r3 = buildRole(null, Role.Type.SYSTEM_ADMIN, Collections.singleton(p3));
        UserRole ur3 = buildUserRole(1L, null, r3, null, null);
        userRoles.add(ur3);

        // DO THE ACTUAL CALL
        List<UserRole> result = roleUtils.filterUserRolesByPrivilege(userRoles, Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES);

        assertThat(result.size(), is(2));
        assertThat(result.contains(ur1), is(true));
        assertThat(result.contains(ur2), is(true));
    }

    @Test
    public void test_roleHasPrivilege_should_returnTrue_when_roleHasSuchPrivilege() throws Exception {
        Role roleMock = mock(Role.class);
        Set<Privilege> privileges = new HashSet<>();

        Privilege p1 = buildPrivilege("whatever", Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES);
        privileges.add(p1);

        Privilege p2 = buildPrivilege("whatever", Privilege.Type.CAN_ASSIGN_SYSTEM_SCOPE_ROLES);
        privileges.add(p2);

        when(roleMock.getPrivileges()).thenReturn(privileges);

        // DO THE ACTUAL CALL
        boolean result = roleUtils.roleHasPrivilege(roleMock, Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES);

        assertThat(result, is(true));
    }

    @Test
    public void test_roleHasPrivilege_should_returnFalse_when_roleHasNoSuchPrivilege() throws Exception {
        Role roleMock = mock(Role.class);
        Set<Privilege> privileges = new HashSet<>();

        Privilege p1 = buildPrivilege("whatever", Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES);
        privileges.add(p1);

        Privilege p2 = buildPrivilege("whatever", Privilege.Type.CAN_ASSIGN_SYSTEM_SCOPE_ROLES);
        privileges.add(p2);

        when(roleMock.getPrivileges()).thenReturn(privileges);

        // DO THE ACTUAL CALL
        boolean result = roleUtils.roleHasPrivilege(roleMock, Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES);

        assertThat(result, is(false));
    }

    @Test
    public void test_isPartyScopeRole_should_returnTrue_when_roleOPERATORorPARTY_ADMIN() throws Exception {
        // ACTUAL CALL
        assertThat(roleUtils.isPartyScopeRole(Role.Type.OPERATOR), is(true));
        assertThat(roleUtils.isPartyScopeRole(Role.Type.PARTY_ADMIN), is(true));
        assertThat(roleUtils.isPartyScopeRole(Role.Type.BUSINESS_ADMIN), is(false));
        assertThat(roleUtils.isPartyScopeRole(Role.Type.SYSTEM_ADMIN), is(false));
    }

    @Test
    public void test_isBusinessScopeRole_should_returnTrue_when_roleBUSINESS_ADMIN() throws Exception {
        // ACTUAL CALL
        assertThat(roleUtils.isBusinessScopeRole(Role.Type.OPERATOR), is(false));
        assertThat(roleUtils.isBusinessScopeRole(Role.Type.PARTY_ADMIN), is(false));
        assertThat(roleUtils.isBusinessScopeRole(Role.Type.BUSINESS_ADMIN), is(true));
        assertThat(roleUtils.isBusinessScopeRole(Role.Type.SYSTEM_ADMIN), is(false));
    }

    @Test
    public void test_isSystemScopeRole_should_returnTrue_when_roleSYSTEM_ADMIN() throws Exception {
        // ACTUAL CALL
        assertThat(roleUtils.isSystemScopeRole(Role.Type.OPERATOR), is(false));
        assertThat(roleUtils.isSystemScopeRole(Role.Type.PARTY_ADMIN), is(false));
        assertThat(roleUtils.isSystemScopeRole(Role.Type.BUSINESS_ADMIN), is(false));
        assertThat(roleUtils.isSystemScopeRole(Role.Type.SYSTEM_ADMIN), is(true));
    }

    @Test
    public void test_filterUserRolesByRoleAndParty_should_filterByRole_when_roleIsPartyScope() throws Exception {
        List<UserRole> userRoles = new ArrayList<>();

        Party party = new Party();
        Party party2 = new Party();

        Role r1 = buildRole(null, Role.Type.OPERATOR, null);
        UserRole ur1 = buildUserRole(1L, null, r1, party, null);
        userRoles.add(ur1);

        Role r2 = buildRole(null, Role.Type.PARTY_ADMIN, null);
        UserRole ur2 = buildUserRole(2L, null, r2, party, null);
        userRoles.add(ur2);

        Role r3 = buildRole(null, Role.Type.SYSTEM_ADMIN, null);
        UserRole ur3 = buildUserRole(3L, null, r3, null, null);
        userRoles.add(ur3);

        Role r4 = buildRole(null, Role.Type.PARTY_ADMIN, null);
        UserRole ur4 = buildUserRole(4L, null, r4, party2, null);
        userRoles.add(ur4);

        //ACTUAL CALL
        UserRole userRole = roleUtils.filterUserRolesByRoleAndParty(userRoles, Role.Type.PARTY_ADMIN, party);

        assertThat(userRole, is(sameInstance(ur2)));
    }

    @Test
    public void test_filterUserRolesByRoleAndParty_should_filterByRole_when_roleIsBusinessScope() throws Exception {
        List<UserRole> userRoles = new ArrayList<>();

        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);

        Business business2 = new Business();
        Party party2 = new Party();
        party2.setBusiness(business2);


        Role r1 = buildRole(null, Role.Type.OPERATOR, null);
        UserRole ur1 = buildUserRole(1L, null, r1, party, null);
        userRoles.add(ur1);

        Role r2 = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole ur2 = buildUserRole(2L, null, r2, null, business2);
        userRoles.add(ur2);

        Role r3 = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole ur3 = buildUserRole(3L, null, r3, null, business);
        userRoles.add(ur3);

        //ACTUAL CALL
        UserRole userRole = roleUtils.filterUserRolesByRoleAndParty(userRoles, Role.Type.BUSINESS_ADMIN, party);

        assertThat(userRole, is(sameInstance(ur3)));
    }

    @Test
    public void test_filterUserRolesByRoleAndParty_should_filterByRole_when_roleIsSystemScope() throws Exception {
        List<UserRole> userRoles = new ArrayList<>();

        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);

        Business business2 = new Business();
        Party party2 = new Party();
        party2.setBusiness(business2);


        Role r1 = buildRole(null, Role.Type.OPERATOR, null);
        UserRole ur1 = buildUserRole(1L, null, r1, party, null);
        userRoles.add(ur1);

        Role r2 = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole ur2 = buildUserRole(2L, null, r2, null, business2);
        userRoles.add(ur2);

        Role r3 = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole ur3 = buildUserRole(3L, null, r3, null, business);
        userRoles.add(ur3);

        Role r4 = buildRole(null, Role.Type.SYSTEM_ADMIN, null);
        UserRole ur4 = buildUserRole(4L, null, r4, null, null);
        userRoles.add(ur4);

        //ACTUAL CALL
        UserRole userRole = roleUtils.filterUserRolesByRoleAndParty(userRoles, Role.Type.SYSTEM_ADMIN, party);

        assertThat(userRole, is(sameInstance(ur4)));
    }

    @Test
    public void test_filterUserRolesByRoleAndBusiness_should_filterByRole_when_roleIsBusinessScope() throws Exception {
        List<UserRole> userRoles = new ArrayList<>();

        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);

        Business business2 = new Business();
        Party party2 = new Party();
        party2.setBusiness(business2);


        Role r1 = buildRole(null, Role.Type.OPERATOR, null);
        UserRole ur1 = buildUserRole(1L, null, r1, party, null);
        userRoles.add(ur1);

        Role r2 = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole ur2 = buildUserRole(2L, null, r2, null, business2);
        userRoles.add(ur2);

        Role r3 = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole ur3 = buildUserRole(3L, null, r3, null, business);
        userRoles.add(ur3);

        //ACTUAL CALL
        UserRole userRole = roleUtils.filterUserRolesByRoleAndBusiness(userRoles, Role.Type.BUSINESS_ADMIN, business);

        assertThat(userRole, is(sameInstance(ur3)));
    }

    @Test
    public void test_filterUserRolesByRoleAndBusiness_should_filterByRole_when_roleIsSystemScope() throws Exception {
        List<UserRole> userRoles = new ArrayList<>();

        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);

        Business business2 = new Business();
        Party party2 = new Party();
        party2.setBusiness(business2);


        Role r1 = buildRole(null, Role.Type.OPERATOR, null);
        UserRole ur1 = buildUserRole(1L, null, r1, party, null);
        userRoles.add(ur1);

        Role r2 = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole ur2 = buildUserRole(2L, null, r2, null, business2);
        userRoles.add(ur2);

        Role r3 = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole ur3 = buildUserRole(3L, null, r3, null, business);
        userRoles.add(ur3);

        Role r4 = buildRole(null, Role.Type.SYSTEM_ADMIN, null);
        UserRole ur4 = buildUserRole(4L, null, r4, null, null);
        userRoles.add(ur4);

        //ACTUAL CALL
        UserRole userRole = roleUtils.filterUserRolesByRoleAndBusiness(userRoles, Role.Type.SYSTEM_ADMIN, business);

        assertThat(userRole, is(sameInstance(ur4)));
    }

    /*
    public Business extractBusiness(UserRole userRole) {
        Role.Type roleType = userRole.getRole().getType();
        if (isPartyScopeRole(roleType)) {
            return userRole.getParty().getBusiness();
        } else if (isBusinessScopeRole(roleType)) {
            return userRole.getBusiness();
        } else {
            return null;
        }
    }
     */

    @Test
    public void test_extractBusiness_should_returnPartyBusiness_when_roleIsPartyScope() throws Exception {
        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);
        Business business2 = new Business();

        Role role = buildRole(null, Role.Type.OPERATOR, null);
        UserRole userRole = buildUserRole(1L, null, role, party, business2);

        //ACTUAL CALL
        Business result = roleUtils.extractBusiness(userRole);

        assertThat(result, is(sameInstance(business)));
    }

    @Test
    public void test_extractBusiness_should_returnBusiness_when_roleIsBusinessScope() throws Exception {
        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);
        Business business2 = new Business();

        Role role = buildRole(null, Role.Type.BUSINESS_ADMIN, null);
        UserRole userRole = buildUserRole(1L, null, role, party, business2);

        //ACTUAL CALL
        Business result = roleUtils.extractBusiness(userRole);

        assertThat(result, is(sameInstance(business2)));
    }

    @Test
    public void test_extractBusiness_should_returnNull_when_roleIsNotPartyOrBusinessScope() throws Exception {
        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);
        Business business2 = new Business();

        Role role = buildRole(null, Role.Type.SYSTEM_ADMIN, null);
        UserRole userRole = buildUserRole(1L, null, role, party, business2);

        //ACTUAL CALL
        Business result = roleUtils.extractBusiness(userRole);

        assertThat(result, is(nullValue()));
    }
}
