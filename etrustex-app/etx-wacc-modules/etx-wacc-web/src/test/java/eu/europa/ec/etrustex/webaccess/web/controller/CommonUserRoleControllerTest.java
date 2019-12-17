package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleDTO;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleFormBean;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class CommonUserRoleControllerTest extends AbstractTest {

    @InjectMocks
    private CommonUserRoleController userRoleController;

    @Mock
    protected PartyManager partyManager;

    @Mock
    protected BusinessManager businessManager;

    @Mock
    protected UserManager userManager;

    @Mock
    protected RoleUtils roleUtils;

    @Mock
    protected SecurityChecker securityChecker;

    @Test
    public void test_doDeleteUser_should_returnErr_when_notAuthorized() throws Exception {
        Long userRoleId = 2L;
        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setUserRoleId(userRoleId);
        Party party = new Party();
        UserRole userRole = new UserRole();
        userRole.setParty(party);

        when(userManager.getUserRole(userRoleId)).thenReturn(userRole);

        CommonUserRoleController spy = spy(userRoleController);
        doReturn(false).when(spy).isAuthorized(userRole);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doDeleteUserRole(formBean);

        assertThat(result, is(nullValue()));

        verify(spy).doDeleteUserRole(formBean);
        verify(spy).isAuthorized(userRole);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doDeleteUser_should_callDeleteMethodFromService() throws Exception {
        Long userRoleId = 32L;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setUserRoleId(userRoleId);
        Role role = new Role();
        Party party = new Party();
        UserRole userRole = new UserRole();
        userRole.setParty(party);
        userRole.setRole(role);

        when(userManager.getUserRole(userRoleId)).thenReturn(userRole);
        when(roleUtils.isPartyScopeRole(org.mockito.Matchers.any(Role.Type.class))).thenReturn(false);
        when(securityChecker.allowDeletePartyUserRoleWithBusinessEmailEnforce(userRole)).thenReturn(true);

        CommonUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(userRole);
        doReturn(true).when(spy).allowDeleteUserRoleWithBusinessEmailEnforce(userRole);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doDeleteUserRole(formBean);

        assertThat(result, is(sameInstance(formBean)));

        verify(userManager).delete(userRole);
        verify(spy).doDeleteUserRole(formBean);
        verify(spy).isAuthorized(userRole);
        verify(spy).allowDeleteUserRoleWithBusinessEmailEnforce(userRole);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doDeleteUser_should_notCallDeleteMethodFromService_when_notAllowed() throws Exception {
        Long userRoleId = 32L;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setUserRoleId(userRoleId);
        Role role = new Role();
        Party party = new Party();
        UserRole userRole = new UserRole();
        userRole.setParty(party);
        userRole.setRole(role);

        when(userManager.getUserRole(userRoleId)).thenReturn(userRole);
        when(roleUtils.isPartyScopeRole(org.mockito.Matchers.any(Role.Type.class))).thenReturn(true);
        when(securityChecker.allowDeletePartyUserRoleWithBusinessEmailEnforce(userRole)).thenReturn(false);

        CommonUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(userRole);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doDeleteUserRole(formBean);

        assertThat(result, is(nullValue()));

        verify(userManager, never()).delete(userRole);
        verify(spy).doDeleteUserRole(formBean);
        verify(spy).isAuthorized(userRole);
        verify(spy).allowDeleteUserRoleWithBusinessEmailEnforce(userRole);

        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doDeleteUser_should_returnNull_when_noUserRoleFound() throws Exception {
        Long userRoleId = 32L;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setUserRoleId(userRoleId);

        when(userManager.getUserRole(userRoleId)).thenReturn(null);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = userRoleController.doDeleteUserRole(formBean);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void test_doEditUser_should_returnErr_when_notAuthorized() throws Exception {
        Long userRoleId = 2L;
        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setUserRoleId(userRoleId);
        Party party = new Party();
        UserRole userRole = new UserRole();
        userRole.setParty(party);

        when(userManager.getUserRole(userRoleId)).thenReturn(userRole);

        CommonUserRoleController spy = spy(userRoleController);
        doReturn(false).when(spy).isAuthorized(userRole);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doEditUser(formBean);

        assertThat(result, is(nullValue()));

        verify(spy).doEditUser(formBean);
        verify(spy).isAuthorized(userRole);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doEditUser_should_callCreateOrUpdateBUC() throws Exception {
        Long userRoleId = 32L;
        Long partyId = 2L;
        String email = "email";
        String name = "name";
        String login = "login";
        boolean notificationsEnabled = true;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setPartyId(partyId);
        formBean.setLogin(login);
        formBean.setName(name);
        formBean.setEmail(email);
        formBean.setNotificationsEnabled(notificationsEnabled);
        formBean.setUserRoleId(userRoleId);
        formBean.setRoleType(Role.Type.OPERATOR);

        Business business = new Business();

        Party party = new Party();
        party.setId(partyId);
        party.setBusiness(business);

        User user = new User();
        user.setLogin(login);
        user.setName(name);

        UserRole userRole = new UserRole();
        Role role = mock(Role.class);
        when(role.getType()).thenReturn(Role.Type.OPERATOR);
        userRole.setId(userRoleId);
        userRole.setUser(user);
        userRole.setParty(party);
        userRole.setRole(role);

        when(userManager.getUserRole(userRoleId)).thenReturn(userRole);
        when(roleUtils.extractBusiness(userRole)).thenReturn(business);

        CommonUserRoleController spy = spy(userRoleController);
        doReturn(user).when(spy).createOrUpdateUser(login, name);
        doReturn(null).when(spy).createOrUpdateBUC(user, business, new UserRoleDTO(name, email, notificationsEnabled, null, false));
        doReturn(true).when(spy).isAuthorized(userRole);
        doReturn(true).when(spy).allowEditUserRoleWithBusinessEmailEnforce(formBean, userRole);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doEditUser(formBean);

        assertThat(result, is(sameInstance(formBean)));

        verify(spy).createOrUpdateUser(login, name);
        verify(spy, atMost(1)).createOrUpdateBUC(user, business, new UserRoleDTO(name, email, notificationsEnabled, null, false));
        verify(spy).doEditUser(formBean);
        verify(spy).isAuthorized(userRole);
        verify(spy).allowEditUserRoleWithBusinessEmailEnforce(formBean, userRole);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doEditUser_should_returnNull_when_noUserRoleFound() throws Exception {
        Long userRoleId = 32L;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setUserRoleId(userRoleId);

        when(userManager.getUserRole(userRoleId)).thenReturn(null);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = userRoleController.doEditUser(formBean);

        assertThat(result, is(nullValue()));
    }
}
