package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.Role;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleFormBean;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SystemScopeUserRoleControllerTest extends AbstractTest {

    @InjectMocks
    private SystemScopeUserRoleController userRoleController;

    @Mock
    protected UserManager userManager;

    @Mock
    protected RoleUtils roleUtils;

    @Mock
    protected SecurityChecker securityChecker;

    @Test
    public void test_doAddSystemScopeUserRole_should_createMissingEntitiesOrUpdateExisting() throws Exception {
        Long userRoleId = 5L;
        String name = "name";
        String login = "login";
        Role.Type roleType = Role.Type.SYSTEM_ADMIN;
        String email = "email";
        boolean notificationsEnabled = true;

        Long systemUserId = 1L;
        User systemUser = new User();
        systemUser.setId(systemUserId);

        User user = new User();
        user.setLogin(login);
        user.setName(name);

        UserRole userRole = new UserRole();
        userRole.setId(userRoleId);

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setName(name);
        formBean.setLogin(login);
        formBean.setEmail(email);
        formBean.setRoleType(roleType);
        formBean.setNotificationsEnabled(notificationsEnabled);

        SystemScopeUserRoleController spy = spy(userRoleController);

        doReturn(systemUser).when(userManager).getUserById(systemUserId);
        doReturn(true).when(spy).isAuthorizedAsSystemUser();
        doReturn(user).when(spy).createOrUpdateUser(login, name);
        doReturn(userRole).when(spy).createOrRetrieveSystemUR(user, roleType);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doAddSystemScopeUserRole(formBean);

        assertThat(result, is(formBean));

        verify(spy).doAddSystemScopeUserRole(formBean);
        verify(spy).isAuthorizedAsSystemUser();
        verify(spy).createOrUpdateUser(login, name);
        verify(spy).createOrRetrieveSystemUR(user, roleType);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doAddSystemScopeUserRole_returnError_when_notAuthorized() throws Exception {
        String name = "name";
        String login = "login";
        Role.Type roleType = Role.Type.SYSTEM_ADMIN;
        String email = "email";
        boolean notificationsEnabled = true;

        Long systemUserId = 1L;
        User systemUser = new User();
        systemUser.setId(systemUserId);

        User user = new User();
        user.setLogin(login);
        user.setName(name);

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setName(name);
        formBean.setLogin(login);
        formBean.setEmail(email);
        formBean.setRoleType(roleType);
        formBean.setNotificationsEnabled(notificationsEnabled);

        SystemScopeUserRoleController spy = spy(userRoleController);

        doReturn(systemUser).when(userManager).getUserById(systemUserId);
        doReturn(false).when(spy).isAuthorizedAsSystemUser();

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doAddSystemScopeUserRole(formBean);

        assertThat(result, is(nullValue()));

        verify(spy).doAddSystemScopeUserRole(formBean);
        verify(spy).isAuthorizedAsSystemUser();
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doGetSystemScopeUserRoles_should_returnUserRoleFormBeans() throws Exception {

        Long systemUserId = 1L;
        User systemUser = new User();
        systemUser.setId(systemUserId);

        UserRoleFormBean formBean = new UserRoleFormBean();

        List<UserRoleFormBean> formBeans = new ArrayList<>();

        SystemScopeUserRoleController spy = spy(userRoleController);

        doReturn(systemUser).when(userManager).getUserById(systemUserId);
        doReturn(true).when(spy).isAuthorizedAsSystemUser();
        doReturn(formBeans).when(spy).transformToFormBeans(anyMap());

        // DO THE ACTUAL CALL
        List<UserRoleFormBean> result = spy.doGetSystemScopeUserRoles(formBean);

        assertThat(result, is(formBeans));

        verify(spy).doGetSystemScopeUserRoles(formBean);
        verify(spy).isAuthorizedAsSystemUser();
    }

    @Test
    public void test_createOrRetrieveSystemUR_should_returnUserRole() throws Exception {
        Long userRoleId = 5L;
        String name = "name";
        String login = "login";
        Role.Type roleType = Role.Type.SYSTEM_ADMIN;
        String email = "email";
        boolean notificationsEnabled = true;

        Long systemUserId = 1L;
        User systemUser = new User();
        systemUser.setId(systemUserId);

        User user = new User();
        user.setLogin(login);
        user.setName(name);

        UserRole userRole = new UserRole();
        userRole.setId(userRoleId);

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setName(name);
        formBean.setLogin(login);
        formBean.setEmail(email);
        formBean.setRoleType(roleType);
        formBean.setNotificationsEnabled(notificationsEnabled);

        SystemScopeUserRoleController spy = spy(userRoleController);

        List<UserRole> userRoles = new ArrayList<>();

        doReturn(userRoles).when(userManager).getUserRoles(user);
        doReturn(userRole).when(roleUtils).filterUserRolesBySystemRole(userRoles, roleType);

        // DO THE ACTUAL CALL
        UserRole result = spy.createOrRetrieveSystemUR(user, roleType);

        assertThat(result, is(userRole));
        verify(userManager).getUserRoles(user);
        verify(roleUtils).filterUserRolesBySystemRole(userRoles, roleType);
    }
}