package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static eu.europa.ec.etrustex.webaccess.web.ModelHelper.buildRole;
import static org.mockito.Mockito.when;

public class SecurityCheckerTest extends AbstractTest {

    @InjectMocks
    private SecurityChecker securityChecker = new SecurityChecker();

    @Mock
    private UserSessionContext userSessionContext;

    @Mock
    private MailboxManager mailboxManager;

    @Mock
    private RoleUtils roleUtils;

    @Test
    public void test_canAccessMessagesOfParty_should_returnTrue_when_partyIsNotAccessible() throws Exception {
        Party party = new Party();
        ArrayList<Party> parties = new ArrayList<>();
        when(userSessionContext.getMessageParties()).thenReturn(parties);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.canAccessMessagesOfParty(party);

        assertThat(result, is(false));
    }

    @Test
    public void test_canAccessMessagesOfParty_should_returnTrue_when_partyIsAccessible() throws Exception {
        Party party = new Party();
        party.setId(12345L);
        ArrayList<Party> parties = new ArrayList<>();
        parties.add(party);
        when(userSessionContext.getMessageParties()).thenReturn(parties);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.canAccessMessagesOfParty(party);

        assertThat(result, is(true));
    }


    @Test
    public void test_canSend_should_returnTrue_when_userCanSend() throws Exception {

        Party party = new Party();
        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_SENDING_ENABLED);
        businessConfig.setPropertyValue("true");

        business.getBusinessConfigs().add(businessConfig);
        party.setBusiness(business);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.canSend(party);

        assertThat(result, is(true));
    }

    @Test
    public void test_canSend_should_returnFalse_when_userCantSend() throws Exception {

        Party party = new Party();
        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        party.setBusiness(business);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.canSend(party);

        assertThat(result, is(false));
    }

    @Test
    public void test_hasPrivilege_should_returnTrue_when_userHasAtLeastOneRoleWithThatPrivilegeAssigned() throws Exception {

        List<UserRole> userRoles = Arrays.asList(new UserRole(), new UserRole());
        List<UserRole> filteredUserRoles = Arrays.asList(new UserRole());
        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(filteredUserRoles);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilege(privilegeType);

        assertThat(result, is(true));
    }

    @Test
    public void test_hasPrivilege_should_returnFalse_when_userHasNoRoleWithThatPrivilegeAssigned() throws Exception {

        List<UserRole> userRoles = Arrays.asList(new UserRole(), new UserRole());
        List<UserRole> filteredUserRoles = new ArrayList<>();
        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(filteredUserRoles);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilege(privilegeType);

        assertThat(result, is(false));
    }

    @Test
    public void test_hasPrivilegeForParty_should_returnTrue_when_userHasRoleForPartyWithThatPrivilegeAssigned() throws Exception {

        Party party = new Party();
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setParty(party);

        Role.Type roleType = Role.Type.OPERATOR;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        List<UserRole> filteredUserRoles = Arrays.asList(userRole);
        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(filteredUserRoles);
        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(true);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilegeForParty(privilegeType, party);

        assertThat(result, is(true));
    }

    @Test
    public void test_hasPrivilegeForParty_should_returnTrue_when_userHasRoleForBusinessWithThatPrivilegeAssigned() throws Exception {

        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setBusiness(business);

        Role.Type roleType = Role.Type.BUSINESS_ADMIN;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        List<UserRole> filteredUserRoles = Arrays.asList(userRole);
        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(filteredUserRoles);
        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(true);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilegeForParty(privilegeType, party);

        assertThat(result, is(true));
    }

    @Test
    public void test_hasPrivilegeForParty_should_returnTrue_when_userHasRoleForSystemWithThatPrivilegeAssigned() throws Exception {

        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setBusiness(business);

        Role.Type roleType = Role.Type.SYSTEM_ADMIN;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        List<UserRole> filteredUserRoles = Arrays.asList(userRole);
        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(filteredUserRoles);
        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilegeForParty(privilegeType, party);

        assertThat(result, is(true));
    }

    @Test
    public void test_hasPrivilegeForParty_should_returnFalse_when_userHasNoRoleWithThatPrivilegeAssigned() throws Exception {

        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);
        List<UserRole> userRoles = new ArrayList<>();

        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(new ArrayList<UserRole>());

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilegeForParty(privilegeType, party);

        assertThat(result, is(false));
    }

    @Test
    public void test_hasPrivilegeForBusiness_should_returnTrue_when_userHasRoleForBusinessWithThatPrivilegeAssigned() throws Exception {

        Business business = new Business();
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setBusiness(business);

        Role.Type roleType = Role.Type.BUSINESS_ADMIN;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        List<UserRole> filteredUserRoles = Arrays.asList(userRole);
        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(filteredUserRoles);
        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(true);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilegeForBusiness(privilegeType, business);

        assertThat(result, is(true));
    }

    @Test
    public void test_hasPrivilegeForBusiness_should_returnTrue_when_userHasRoleForSystemWithThatPrivilegeAssigned() throws Exception {

        Business business = new Business();
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setBusiness(business);

        Role.Type roleType = Role.Type.SYSTEM_ADMIN;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        List<UserRole> filteredUserRoles = Arrays.asList(userRole);
        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(filteredUserRoles);
        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilegeForBusiness(privilegeType, business);

        assertThat(result, is(true));
    }

    @Test
    public void test_hasPrivilegeForBusiness_should_returnFalse_when_userHasNoRoleWithThatPrivilegeAssigned() throws Exception {

        Business business = new Business();
        List<UserRole> userRoles = new ArrayList<>();

        Privilege.Type privilegeType = Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES;

        when(userSessionContext.getUserRoleList()).thenReturn(userRoles);
        when(roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType)).thenReturn(new ArrayList<UserRole>());

        // DO THE ACTUAL CALL
        boolean result = securityChecker.hasPrivilegeForBusiness(privilegeType, business);

        assertThat(result, is(false));
    }
}
