package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.BusinessUserConfigDAO;
import eu.europa.ec.etrustex.webaccess.persistence.RoleDAO;
import eu.europa.ec.etrustex.webaccess.persistence.UserDAO;
import eu.europa.ec.etrustex.webaccess.persistence.UserRoleDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class UserManagerImplTest extends AbstractTest {
    @InjectMocks
    private UserManagerImpl userManager = new UserManagerImpl();

    @Mock
    private UserDAO userDAO;

    @Mock
    private UserRoleDAO userRoleDAO;

    @Mock
    private RoleDAO roleDAO;

    @Mock
    private BusinessUserConfigDAO businessUserConfigDAO;

    @Test
    public void test_getUserRoles_should_callUserRoleDAO() {
        User user = new User();

        List<UserRole> urs = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setId(12345L);
        urs.add(userRole);
        when(userRoleDAO.getUserRoles(user)).thenReturn(urs);

        //ACTUAL CALL
        List<UserRole> result = userManager.getUserRoles(user);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(sameInstance(userRole)));

        verify(userRoleDAO).getUserRoles(argThat(sameInstance(user)));
    }

    @Test
    public void test_getUserRolesByPartyAndRole_should_callUserRoleDAO() {
        Role.Type type = Role.Type.BUSINESS_ADMIN;
        Party party = new Party();

        Map<UserRole, BusinessUserConfig> bucs = new HashMap<>();
        when(userRoleDAO.getUserRoles(party, type)).thenReturn(bucs);

        //ACTUAL CALL
        Map<UserRole, BusinessUserConfig> result = userManager.getUserRoles(party, type);

        assertThat(result, is(sameInstance(bucs)));

        verify(userRoleDAO).getUserRoles(party, type);
    }

    @Test
    public void test_getUserRolesByBusinessAndRole_should_callUserRoleDAO() {
        Role.Type type = Role.Type.BUSINESS_ADMIN;
        Business business = new Business();

        Map<UserRole, BusinessUserConfig> bucs = new HashMap<>();
        when(userRoleDAO.getUserRoles(business, type)).thenReturn(bucs);

        //ACTUAL CALL
        Map<UserRole, BusinessUserConfig> result = userManager.getUserRoles(business, type);

        assertThat(result, is(sameInstance(bucs)));

        verify(userRoleDAO).getUserRoles(business, type);
    }

    @Test
    public void test_getBusinessUserConfigsByPartyAndPrivilege_should_returnBUCs() {
        Privilege.Type type = Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES;
        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);

        List<BusinessUserConfig> partyBUCs = new ArrayList<>();
        BusinessUserConfig partyBUC = new BusinessUserConfig();
        partyBUC.setId(1L);
        partyBUCs.add(partyBUC);
        List<BusinessUserConfig> businessBUCs = new ArrayList<>();
        BusinessUserConfig businessBUC = new BusinessUserConfig();
        businessBUC.setId(2L);
        businessBUCs.add(businessBUC);
        List<BusinessUserConfig> systemBUCs = new ArrayList<>();
        BusinessUserConfig systemBUC = new BusinessUserConfig();
        systemBUC.setId(3L);
        systemBUCs.add(systemBUC);

        when(businessUserConfigDAO.getBusinessUserConfigs(party, type)).thenReturn(partyBUCs);
        when(businessUserConfigDAO.getBusinessUserConfigs(business, type)).thenReturn(businessBUCs);
        when(businessUserConfigDAO.getSystemScopeBusinessUserConfigs(business, type)).thenReturn(systemBUCs);

        //ACTUAL CALL
        Set<BusinessUserConfig> result = userManager.getBusinessUserConfigs(party, type);

        assertThat(result.size(), is(3));
        assertThat(result.contains(partyBUC), is(true));
        assertThat(result.contains(businessBUC), is(true));
        assertThat(result.contains(systemBUC), is(true));

        verify(businessUserConfigDAO).getBusinessUserConfigs(party, type);
        verify(businessUserConfigDAO).getBusinessUserConfigs(business, type);
        verify(businessUserConfigDAO).getSystemScopeBusinessUserConfigs(business, type);
        verifyNoMoreInteractions(businessUserConfigDAO);
    }

    @Test
    public void test_getBusinessUserConfigsByPartyAndPrivilege_should_notReturnDuplicateBUCs() {
        Privilege.Type type = Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES;
        Business business = new Business();
        Party party = new Party();
        party.setBusiness(business);

        List<BusinessUserConfig> partyBUCs = new ArrayList<>();
        BusinessUserConfig partyBUC = new BusinessUserConfig();
        partyBUC.setId(1L);
        partyBUCs.add(partyBUC);
        List<BusinessUserConfig> businessBUCs = new ArrayList<>();
        BusinessUserConfig businessBUC = new BusinessUserConfig();
        businessBUC.setId(1L);
        businessBUCs.add(businessBUC);

        when(businessUserConfigDAO.getBusinessUserConfigs(party, type)).thenReturn(partyBUCs);
        when(businessUserConfigDAO.getBusinessUserConfigs(business, type)).thenReturn(businessBUCs);

        //ACTUAL CALL
        Set<BusinessUserConfig> result = userManager.getBusinessUserConfigs(party, type);

        assertThat(result.size(), is(1));

        verify(businessUserConfigDAO).getBusinessUserConfigs(party, type);
        verify(businessUserConfigDAO).getBusinessUserConfigs(business, type);
        verify(businessUserConfigDAO).getSystemScopeBusinessUserConfigs(business, type);
        verifyNoMoreInteractions(businessUserConfigDAO);
    }

    @Test
    public void test_getRole_should_callRoleDAO() {
        Role.Type type = Role.Type.BUSINESS_ADMIN;

        Role role = new Role();
        when(roleDAO.getRole(type)).thenReturn(role);

        //ACTUAL CALL
        Role result = userManager.getRole(type);

        assertThat(result, is(sameInstance(role)));

        verify(roleDAO).getRole(type);
        verifyNoMoreInteractions(roleDAO);
    }

    @Test
    public void test_saveOrUpdate_should_saveUser_when_noIdSet() {
        User user = new User();

        //ACTUAL CALL
        userManager.saveOrUpdate(user);

        verify(userDAO).save(user);
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void test_saveOrUpdate_should_updateUser_when_idSet() {
        User user = new User();
        user.setId(1L);

        //ACTUAL CALL
        userManager.saveOrUpdate(user);

        verify(userDAO).update(user);
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void test_saveOrUpdate_should_saveUserRole_when_noIdSet() {
        UserRole userRole = new UserRole();

        //ACTUAL CALL
        userManager.saveOrUpdate(userRole);

        verify(userRoleDAO).save(userRole);
        verifyNoMoreInteractions(userRoleDAO);
    }

    @Test
    public void test_saveOrUpdate_should_updateUserRole_when_idSet() {
        UserRole userRole = new UserRole();
        userRole.setId(1L);

        //ACTUAL CALL
        userManager.saveOrUpdate(userRole);

        verify(userRoleDAO).update(userRole);
        verifyNoMoreInteractions(userRoleDAO);
    }

    @Test
    public void test_saveOrUpdate_should_saveBusinessUserConfig_when_noIdSet() {
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();

        //ACTUAL CALL
        userManager.saveOrUpdate(businessUserConfig);

        verify(businessUserConfigDAO).save(businessUserConfig);
        verifyNoMoreInteractions(businessUserConfigDAO);
    }

    @Test
    public void test_saveOrUpdate_should_updateBusinessUserConfig_when_idSet() {
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setId(1L);

        //ACTUAL CALL
        userManager.saveOrUpdate(businessUserConfig);

        verify(businessUserConfigDAO).update(businessUserConfig);
        verifyNoMoreInteractions(businessUserConfigDAO);
    }

    @Test
    public void test_delete_should_setActiveStateToFalse() {
        UserRole userRole = new UserRole();
        userRole.setActiveState(true);

        UserManager spy = spy(userManager);

        //ACTUAL CALL
        spy.delete(userRole);

        assertThat(userRole.getActiveState(), is(false));

        verify(spy).delete(userRole);
        verify(spy).saveOrUpdate(userRole);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_partyHasRolesWithNotificationsEnabled_should_returnFalse_when_notRoles() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);
        String login = "login";

        UserManager spy = spy(userManager);

        doReturn(Collections.emptyMap()).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        doReturn(Collections.emptyMap()).when(spy).getUserRoles(party, Role.Type.OPERATOR);

        // DO THE ACTUAL CALL
        boolean result = spy.partyHasRolesWithNotificationsEnabled(party, login);

        assertThat(result, is(false));
        verify(spy).partyHasRolesWithNotificationsEnabled(party, login);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verify(spy).getUserRoles(party, Role.Type.OPERATOR);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_partyHasRolesWithNotificationsEnabled_should_returnTrue_when_partyAdminRoleWithDifferentLogin() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);
        String login = "login";

        UserRole userRole = new UserRole();
        String login2 = "login2";
        User user = new User();
        user.setLogin(login2);
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap = new HashMap<>();
        userRoleBusinessUserConfigMap.put(userRole, businessUserConfig);

        UserManager spy = spy(userManager);

        doReturn(userRoleBusinessUserConfigMap).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);

        // DO THE ACTUAL CALL
        boolean result = spy.partyHasRolesWithNotificationsEnabled(party, login);

        assertThat(result, is(true));
        verify(spy).partyHasRolesWithNotificationsEnabled(party, login);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_partyHasRolesWithNotificationsEnabled_should_returnFalse_when_partyAdminRoleWithSameLogin() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);
        String login = "login";

        UserRole userRole = new UserRole();
        User user = new User();
        user.setLogin(login);
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap = new HashMap<>();
        userRoleBusinessUserConfigMap.put(userRole, businessUserConfig);

        UserManager spy = spy(userManager);

        doReturn(userRoleBusinessUserConfigMap).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        doReturn(Collections.emptyMap()).when(spy).getUserRoles(party, Role.Type.OPERATOR);

        // DO THE ACTUAL CALL
        boolean result = spy.partyHasRolesWithNotificationsEnabled(party, login);

        assertThat(result, is(false));
        verify(spy).partyHasRolesWithNotificationsEnabled(party, login);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verify(spy).getUserRoles(party, Role.Type.OPERATOR);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_partyHasRolesWithNotificationsEnabled_should_returnTrue_when_operatorRoleWithDifferentLogin() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);
        String login = "login";

        UserRole userRole = new UserRole();
        String login2 = "login2";
        User user = new User();
        user.setLogin(login2);
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap = new HashMap<>();
        userRoleBusinessUserConfigMap.put(userRole, businessUserConfig);

        UserManager spy = spy(userManager);

        doReturn(Collections.emptyMap()).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        doReturn(userRoleBusinessUserConfigMap).when(spy).getUserRoles(party, Role.Type.OPERATOR);

        // DO THE ACTUAL CALL
        boolean result = spy.partyHasRolesWithNotificationsEnabled(party, login);

        assertThat(result, is(true));
        verify(spy).partyHasRolesWithNotificationsEnabled(party, login);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verify(spy).getUserRoles(party, Role.Type.OPERATOR);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_otherPartyUserRolesWithNotificationsEnabledThan_should_returnFalse_whenNoRoles() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);

        UserRole userRole = new UserRole();
        userRole.setParty(party);

        UserManager spy = spy(userManager);

        doReturn(Collections.emptyMap()).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        doReturn(Collections.emptyMap()).when(spy).getUserRoles(party, Role.Type.OPERATOR);

        // DO THE ACTUAL CALL
        boolean result = spy.otherPartyUserRolesWithNotificationsEnabledThan(userRole);

        assertThat(result, is(false));
        verify(spy).otherPartyUserRolesWithNotificationsEnabledThan(userRole);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verify(spy).getUserRoles(party, Role.Type.OPERATOR);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_otherPartyUserRolesWithNotificationsEnabledThan_should_returnFalse_whenOnlyPartyAdminRole() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap = new HashMap<>();

        Long userRoleId = 1L;
        UserRole userRole1 = new UserRole();
        userRole1.setId(userRoleId);
        userRole1.setParty(party);
        User user = new User();
        String login = "login1";
        user.setLogin(login);
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);
        userRoleBusinessUserConfigMap.put(userRole1, businessUserConfig);

        UserManager spy = spy(userManager);

        doReturn(userRoleBusinessUserConfigMap).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        doReturn(Collections.emptyMap()).when(spy).getUserRoles(party, Role.Type.OPERATOR);

        // DO THE ACTUAL CALL
        boolean result = spy.otherPartyUserRolesWithNotificationsEnabledThan(userRole1);

        assertThat(result, is(false));
        verify(spy).otherPartyUserRolesWithNotificationsEnabledThan(userRole1);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verify(spy).getUserRoles(party, Role.Type.OPERATOR);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_otherPartyUserRolesWithNotificationsEnabledThan_should_returnTrue_whenPartyAdminRoles() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap = new HashMap<>();

        Long userRoleId = 1L;
        UserRole userRole1 = new UserRole();
        userRole1.setId(userRoleId);
        userRole1.setParty(party);
        User user = new User();
        String login = "login1";
        user.setLogin(login);
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);
        userRoleBusinessUserConfigMap.put(userRole1, businessUserConfig);

        userRoleId = 2L;
        UserRole userRole2 = new UserRole();
        userRole2.setId(userRoleId);
        userRole2.setParty(party);
        user = new User();
        login = "login2";
        user.setLogin(login);
        businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);
        userRoleBusinessUserConfigMap.put(userRole2, businessUserConfig);

        UserManager spy = spy(userManager);

        doReturn(userRoleBusinessUserConfigMap).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);

        // DO THE ACTUAL CALL
        boolean result = spy.otherPartyUserRolesWithNotificationsEnabledThan(userRole1);

        assertThat(result, is(true));
        verify(spy).otherPartyUserRolesWithNotificationsEnabledThan(userRole1);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_otherPartyUserRolesWithNotificationsEnabledThan_should_returnFalse_whenSamePartyAdminAndOperatorRole() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap1 = new HashMap<>();

        Long userRoleId = 1L;
        UserRole userRole1 = new UserRole();
        userRole1.setId(userRoleId);
        userRole1.setParty(party);
        User user = new User();
        String login = "login1";
        user.setLogin(login);
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);
        userRoleBusinessUserConfigMap1.put(userRole1, businessUserConfig);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap2 = new HashMap<>();
        userRoleId = 2L;
        UserRole userRole2 = new UserRole();
        userRole2.setId(userRoleId);
        userRole2.setParty(party);
        user.setLogin(login);
        businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);
        userRoleBusinessUserConfigMap2.put(userRole2, businessUserConfig);

        UserManager spy = spy(userManager);

        doReturn(userRoleBusinessUserConfigMap1).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        doReturn(userRoleBusinessUserConfigMap2).when(spy).getUserRoles(party, Role.Type.OPERATOR);

        // DO THE ACTUAL CALL
        boolean result = spy.otherPartyUserRolesWithNotificationsEnabledThan(userRole1);

        assertThat(result, is(false));
        verify(spy).otherPartyUserRolesWithNotificationsEnabledThan(userRole1);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verify(spy).getUserRoles(party, Role.Type.OPERATOR);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_otherPartyUserRolesWithNotificationsEnabledThan_should_returnTrue_whenPartyAdminAndOperatorRole() {
        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap1 = new HashMap<>();

        Long userRoleId = 1L;
        UserRole userRole1 = new UserRole();
        userRole1.setId(userRoleId);
        userRole1.setParty(party);
        User user = new User();
        String login = "login1";
        user.setLogin(login);
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);
        userRoleBusinessUserConfigMap1.put(userRole1, businessUserConfig);

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap2 = new HashMap<>();
        userRoleId = 2L;
        UserRole userRole2 = new UserRole();
        userRole2.setId(userRoleId);
        userRole2.setParty(party);
        user = new User();
        login = "login2";
        user.setLogin(login);
        businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setUser(user);
        userRoleBusinessUserConfigMap2.put(userRole2, businessUserConfig);

        UserManager spy = spy(userManager);

        doReturn(userRoleBusinessUserConfigMap1).when(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        doReturn(userRoleBusinessUserConfigMap2).when(spy).getUserRoles(party, Role.Type.OPERATOR);

        // DO THE ACTUAL CALL
        boolean result = spy.otherPartyUserRolesWithNotificationsEnabledThan(userRole1);

        assertThat(result, is(true));
        verify(spy).otherPartyUserRolesWithNotificationsEnabledThan(userRole1);
        verify(spy).getUserRoles(party, Role.Type.PARTY_ADMIN);
        verify(spy).getUserRoles(party, Role.Type.OPERATOR);
        verifyNoMoreInteractions(spy);
    }
}
