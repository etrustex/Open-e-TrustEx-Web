package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleDTO;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleFormBean;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.HashMap;
import java.util.List;

import static eu.europa.ec.etrustex.webaccess.web.ModelHelper.buildRole;
import static eu.europa.ec.etrustex.webaccess.web.ModelHelper.buildUserRole;
import static org.mockito.Mockito.*;

public class AbstractUserRoleControllerTest extends AbstractTest {

    @InjectMocks
    private AbstractUserRoleController userRoleController;

    @Mock
    protected PartyManager partyManager;

    @Mock
    protected BusinessManager businessManager;

    @Mock
    protected UserManager userManager;

    @Mock
    protected RoleUtils roleUtils;


    @Test
    public void test_MappingJackson2HttpMessageConverter_should_beAbleToConvertUserFormBean() {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //ACTUAL CALL
        boolean result = converter.canRead(UserRoleFormBean.class, MediaType.APPLICATION_JSON);
        assertThat(result, is(true));
    }

    @Test
    public void test_createOrRetrieveUser_should_returnUnchangedUser_when_suchUserExistsInDB() throws Exception {
        String login = "login";
        String name = "name";
        String name2 = "name2";

        User user = new User();
        user.setLogin(login);
        user.setName(name2);

        when(userManager.getUserDetails(login)).thenReturn(user);

        // DO THE ACTUAL CALL
        User result = userRoleController.createOrRetrieveUser(login, name);

        assertThat(result, is(sameInstance(user)));
        assertThat(result.getName(), is(name2));
    }

    @Test
    public void test_createOrRetrieveUser_should_createUser_when_noSuchUserExistInDB() throws Exception {
        String login = "login";
        String name = "name";

        when(userManager.getUserDetails(login)).thenReturn(null);

        // DO THE ACTUAL CALL
        User result = userRoleController.createOrRetrieveUser(login, name);

        assertThat(result.getLogin(), is(login));
        assertThat(result.getName(), is(name));
    }

    @Test
    public void test_createOrUpdateBUC_should_returnEditedBUC_when_suchRecordExistsInDB() throws Exception {
        String name = "name";
        String email = "email";
        boolean notificationsEnabled = true;

        User user = new User();
        Business business = new Business();

        BusinessUserConfig bucFromDB = new BusinessUserConfig();
        bucFromDB.setName("asdf");
        bucFromDB.setEmail("afaag");
        bucFromDB.setNotificationsEnabled(!notificationsEnabled);

        when(userManager.getBusinessUserConfig(user, business)).thenReturn(bucFromDB);

        // DO THE ACTUAL CALL
        BusinessUserConfig result = userRoleController.createOrUpdateBUC(user, business, new UserRoleDTO(name, email, notificationsEnabled, null, false));

        assertThat(result, is(sameInstance(bucFromDB)));
        assertThat(result.getName(), is(name));
        assertThat(result.getEmail(), is(email));
        assertThat(result.getNotificationsEnabled(), is(notificationsEnabled));

        verify(userManager).saveOrUpdate(result);
    }

    @Test
    public void test_createOrUpdateBUC_should_returnNewBUC_when_noSuchRecordExistInDB() throws Exception {
        String name = "name";
        String email = "email";
        boolean notificationsEnabled = true;

        User user = new User();
        Business business = new Business();

        when(userManager.getBusinessUserConfig(user, business)).thenReturn(null);

        // DO THE ACTUAL CALL
        BusinessUserConfig result = userRoleController.createOrUpdateBUC(user, business, new UserRoleDTO(name, email, notificationsEnabled, null, false));

        assertThat(result.getUser(), is(sameInstance(user)));
        assertThat(result.getBusiness(), is(sameInstance(business)));
        assertThat(result.getName(), is(name));
        assertThat(result.getEmail(), is(email));
        assertThat(result.getNotificationsEnabled(), is(notificationsEnabled));

        verify(userManager).saveOrUpdate(result);
    }

    @Test
    public void test_transformToFormBeans_should_createCompleteFormBeans() throws Exception {
        HashMap<UserRole, BusinessUserConfig> map = new HashMap<>();

        String login = "lajksdf";
        User user = new User();
        user.setLogin(login);

        Role role = buildRole(null, Role.Type.OPERATOR, null);

        long userRoleId = 5L;
        UserRole userRole = buildUserRole(userRoleId, user, role, null, null);

        String name = "name";
        String email = "aname";
        boolean notificationsEnabled = false;
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setUser(user);
        businessUserConfig.setName(name);
        businessUserConfig.setEmail(email);
        businessUserConfig.setNotificationsEnabled(notificationsEnabled);

        map.put(userRole, businessUserConfig);

        //ACTUAL CALL
        List<UserRoleFormBean> result = userRoleController.transformToFormBeans(map);

        UserRoleFormBean userRoleFormBean = result.get(0);

        assertThat(userRoleFormBean.getUserRoleId(), is(userRoleId));
        assertThat(userRoleFormBean.getLogin(), is(login));
        assertThat(userRoleFormBean.getName(), is(name));
        assertThat(userRoleFormBean.getEmail(), is(email));
        assertThat(userRoleFormBean.getNotificationsEnabled(), is(notificationsEnabled));
        assertThat(userRoleFormBean.getRoleType(), is(role.getType()));
    }

    @Test
    public void test_isAuthorized_should_callPartyAuthorization_when_isPartyScopeRole() throws Exception {
        UserRole userRole = new UserRole();
        Party party = new Party();
        userRole.setParty(party);
        Role.Type roleType = Role.Type.PARTY_ADMIN;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(true);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(false);

        AbstractUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(party);

        // DO THE ACTUAL CALL
        boolean result = spy.isAuthorized(userRole);

        assertThat(result, is(true));

        verify(spy).isAuthorized(userRole);
        verify(spy).isAuthorized(party);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_isAuthorized_should_callBusinessAuthorization_when_isBusinessScopeRole() throws Exception {
        UserRole userRole = new UserRole();
        Business business = new Business();
        userRole.setBusiness(business);
        Role.Type roleType = Role.Type.BUSINESS_ADMIN;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(true);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(false);

        AbstractUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(business);

        // DO THE ACTUAL CALL
        boolean result = spy.isAuthorized(userRole);

        assertThat(result, is(true));

        verify(spy).isAuthorized(userRole);
        verify(spy).isAuthorized(business);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_isAuthorized_should_returnTrue_when_isSystemScopeRole() throws Exception {
        UserRole userRole = new UserRole();
        Role.Type roleType = Role.Type.SYSTEM_ADMIN;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(true);

        AbstractUserRoleController spy = spy(userRoleController);

        // DO THE ACTUAL CALL
        boolean result = spy.isAuthorized(userRole);

        assertThat(result, is(true));

        verify(spy).isAuthorized(userRole);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_isAuthorized_should_returnFalse_when_undetectedRoleScope() throws Exception {
        UserRole userRole = new UserRole();
        Role.Type roleType = Role.Type.OPERATOR;
        Role role = buildRole(null, roleType, null);
        userRole.setRole(role);

        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(false);
        when(roleUtils.isSystemScopeRole(roleType)).thenReturn(false);

        AbstractUserRoleController spy = spy(userRoleController);

        // DO THE ACTUAL CALL
        boolean result = spy.isAuthorized(userRole);

        assertThat(result, is(false));

        verify(spy).isAuthorized(userRole);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_createOrUpdateUser_should_returnEditedUser_when_suchRecordExistsInDB() throws Exception {
        String login = "login";
        String name = "name";

        User user = new User();
        user.setId(1L);
        user.setLogin(login);
        user.setName(name);

        when(userManager.getUserDetails(login)).thenReturn(user);

        UserRole userRole = new UserRole();
        Role role = mock(Role.class);
        when(role.getType()).thenReturn(Role.Type.SYSTEM_ADMIN);
        userRole.setRole(role);

        // DO THE ACTUAL CALL
        User result = userRoleController.createOrUpdateUser(login, name);

        assertThat(result, is(sameInstance(user)));
        assertThat(result.getName(), is(name));
        assertThat(result.getLogin(), is(login));

        verify(userManager).saveOrUpdate(result);
    }

    @Test
    public void test_createOrUpdateUser_should_returnNewUser_when_noSuchRecordExistInDB() throws Exception {
        String login = "login";
        String name = "name";

        User user = new User();
        user.setLogin(login);
        user.setName(name);

        UserRole userRole = new UserRole();
        Role role = mock(Role.class);
        when(role.getType()).thenReturn(Role.Type.SYSTEM_ADMIN);
        userRole.setRole(role);

        when(userManager.getUserDetails(login)).thenReturn(null);

        // DO THE ACTUAL CALL
        User result = userRoleController.createOrUpdateUser(login, name);

        assertThat(result.getName(), is(name));
        assertThat(result.getLogin(), is(login));

        verify(userManager).saveOrUpdate(result);
    }
}
