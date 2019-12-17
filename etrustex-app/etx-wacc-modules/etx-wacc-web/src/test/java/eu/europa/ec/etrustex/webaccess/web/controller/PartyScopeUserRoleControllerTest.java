package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.web.ModelHelper;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleDTO;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleFormBean;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class PartyScopeUserRoleControllerTest extends AbstractTest {

    @InjectMocks
    private PartyScopeUserRoleController userRoleController;

    @Mock
    protected PartyManager partyManager;

    @Mock
    protected UserManager userManager;

    @Mock
    protected RoleUtils roleUtils;

    @Mock
    protected BusinessManager businessManager;

    @Test
    public void test_doAddPartyScopeUserRole_should_createMissingEntitiesOrUpdateExisting() throws Exception {
        Long userRoleId = 5L;
        Long partyId = 2L;
        String email = "email";
        String name = "name";
        String login = "login";
        boolean statusNotificationEnabled = false;
        String statusNotificationEmail = "statusemail";
        Role.Type roleType = Role.Type.OPERATOR;
        boolean notificationsEnabled = true;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setPartyId(partyId);
        formBean.setName(name);
        formBean.setLogin(login);
        formBean.setEmail(email);
        formBean.setRoleType(roleType);
        formBean.setNotificationsEnabled(notificationsEnabled);
        formBean.setStatusNotificationEnabled(statusNotificationEnabled);
        formBean.setStatusNotificationEmail(statusNotificationEmail);

        Business business = new Business();

        Party party = new Party();
        party.setId(partyId);
        party.setBusiness(business);

        User user = new User();
        UserRole userRole = new UserRole();
        userRole.setId(userRoleId);

        PartyScopeUserRoleController spy = spy(userRoleController);

        doReturn(true).when(spy).isAuthorized(party);
        doReturn(user).when(spy).createOrRetrieveUser(login, name);
        doReturn(userRole).when(spy).createOrRetrievePartyUR(user, party, roleType);

        when(userManager.getUserDetails(login.trim())).thenReturn(user);
        when(partyManager.getPartyById(partyId)).thenReturn(party);

        ArgumentCaptor<UserRoleDTO> userRoleDTOCaptor = ArgumentCaptor.forClass(UserRoleDTO.class);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doAddPartyScopeUserRole(formBean);

        assertThat(result.getUserRoleId(), is(userRoleId));
        assertThat(result.getEmail(), is("email"));

        verify(spy).doAddPartyScopeUserRole(formBean);
        verify(spy).isAuthorized(party);
        verify(spy).createOrRetrieveUser(login.trim(), name.trim());
        verify(spy).createOrUpdateBUC(argThat(is(user)), argThat(is(business)), userRoleDTOCaptor.capture());
        UserRoleDTO dto = userRoleDTOCaptor.getValue();
        assertThat(dto, notNullValue());
        assertThat(dto.getName(), is(name));
        assertThat(dto.getEmail(), is(email));
        assertThat(dto.getNotificationsEnabled(), is(notificationsEnabled));
        assertThat(dto.getStatusNotificationEnabled(), is(statusNotificationEnabled));
        assertThat(dto.getStatusNotificationEmail(), is(statusNotificationEmail));
        verify(spy).createOrRetrievePartyUR(user, party, roleType);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doAddPartyScopeUserRole_should_returnErr_when_notAuthorized() throws Exception {
        Long partyId = 2L;
        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setPartyId(partyId);
        Party party = new Party();

        when(partyManager.getPartyById(partyId)).thenReturn(party);

        PartyScopeUserRoleController spy = spy(userRoleController);
        doReturn(false).when(spy).isAuthorized(party);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doAddPartyScopeUserRole(formBean);

        assertThat(result, is(nullValue()));

        verify(spy).doAddPartyScopeUserRole(formBean);
        verify(spy).isAuthorized(party);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_createOrRetrieveUR_should_returnUR_when_suchRecordExistsInDBAndPartyScopeRole() throws Exception {

        String login = "login";

        List<UserRole> initialUserRoles = new ArrayList<>();

        Role.Type roleType = Role.Type.OPERATOR;

        User user = new User();
        user.setLogin(login);

        Party party = new Party();

        UserRole filteredUserRole = new UserRole();
        filteredUserRole.setId(3L);

        when(userManager.getUserRoles(user)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByRoleAndParty(initialUserRoles, roleType, party)).thenReturn(filteredUserRole);

        // DO THE ACTUAL CALL
        UserRole result = userRoleController.createOrRetrievePartyUR(user, party, roleType);

        assertThat(result, is(sameInstance(filteredUserRole)));
    }

    @Test
    public void test_createOrRetrieveUR_should_returnNewUR_when_noSuchRecordExistInDBAndPartyScopeRole() throws Exception {

        String login = "login";

        List<UserRole> initialUserRoles = new ArrayList<>();


        Role.Type roleType = Role.Type.OPERATOR;
        Role role = ModelHelper.buildRole(null, roleType, null);

        User user = new User();
        user.setLogin(login);

        Party party = new Party();

        when(userManager.getUserRoles(user)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByRoleAndParty(initialUserRoles, roleType, party)).thenReturn(null);
        when(roleUtils.isPartyScopeRole(roleType)).thenReturn(true);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(false);
        when(userManager.getRole(roleType)).thenReturn(role);

        // DO THE ACTUAL CALL
        UserRole result = userRoleController.createOrRetrievePartyUR(user, party, roleType);

        assertThat(result.getUser(), is(sameInstance(user)));
        assertThat(result.getParty(), is(sameInstance(party)));
        assertThat(result.getBusiness(), is(nullValue()));
        assertThat(result.getRole().getType(), is(roleType));
    }

    @Test
    public void test_doGetPartyScopeUserRoles_should_returnUserRoleFormBeans() throws Exception {
        Long partyId = 3L;
        List<Role.Type> roleTypes = Arrays.asList(Role.Type.SYSTEM_ADMIN, Role.Type.OPERATOR);

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setPartyId(partyId);
        formBean.setRoleTypes(roleTypes);
        Party party = new Party();
        Business business = new Business();
        party.setBusiness(business);
        List<UserRoleFormBean> formBeans = new ArrayList<>();

        when(partyManager.getPartyById(partyId)).thenReturn(party);
        when(businessManager.isBusinessEmailEnforceEnabledFor(party.getBusiness())).thenReturn(false);

        PartyScopeUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(party);
        doReturn(formBeans).when(spy).transformToFormBeans(anyMap());

        //ACTUAL CALL
        List<UserRoleFormBean> result = spy.doGetPartyScopeUserRoles(formBean);

        assertThat(result, is(sameInstance(formBeans)));

        verify(spy).isAuthorized(party);
    }

    @Test
    public void test_doGetPartyScopeUserRoles_should_returnEmptyList_when_notAuthorized() throws Exception {
        Long partyId = 2L;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setPartyId(partyId);
        Party party = new Party();

        when(partyManager.getPartyById(partyId)).thenReturn(party);

        PartyScopeUserRoleController spy = spy(userRoleController);
        doReturn(false).when(spy).isAuthorized(party);

        // DO THE ACTUAL CALL
        List<UserRoleFormBean> result = spy.doGetPartyScopeUserRoles(formBean);

        assertThat(result.size(), is(0));

        verify(spy).doGetPartyScopeUserRoles(formBean);
        verify(spy).isAuthorized(party);
        verifyNoMoreInteractions(spy);
    }

}
