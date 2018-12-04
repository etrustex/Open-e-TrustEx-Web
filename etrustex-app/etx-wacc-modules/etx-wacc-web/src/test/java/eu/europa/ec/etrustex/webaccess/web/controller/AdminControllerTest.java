package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.IcaDetailsService;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.AdminFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.PartyData;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;

public class AdminControllerTest extends AbstractTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private SecurityChecker securityChecker;

    @Mock
    private PartyManager partyManager;

    @Mock
    private BusinessManager businessManager;

    @Mock
    private UserManager userManager;

    @Mock
    private RoleUtils roleUtils;

    @Mock
    private UserSessionContext userSessionContext;

    @Mock
    private IcaDetailsService icaDetailsService;

    @Mock
    private ConfigurationService configurationService;

    private User loggedInUser;

    protected void onSetUp() {
        loggedInUser = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
    }

    @Test
    public void test_doGet_should_addMandatoryObjectsToModel() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        Long partyId = 2L;

        AdminFormBean formBean = new AdminFormBean();
        formBean.setPartyId(partyId);

        when(configurationService.getConfiguration()).thenReturn(new Configuration());

        AdminController spy = spy(adminController);
        doReturn(true).when(spy).isAuthorized();

        // DO THE ACTUAL CALL
        ModelAndView result = spy.doGet(mockRequest);

        assertThat(result.getModel().size(), is(6));
        assertThat((Boolean) result.getModel().get("hideUserDetails"), is(Boolean.TRUE));
        assertThat(result.getModel().containsKey("isPartyAdmin"), is(true));
        assertThat(result.getModel().containsKey("businesses"), is(true));
        assertThat(result.getModel().containsKey("user"), is(true));
        assertThat(result.getModel().containsKey("announcementsContent"), is(true));
    }

    @Test
    public void test_retrievePartiesToAdmin_should_retrieveAllPartiesForPartyLevelRole() throws Exception {

        ArrayList<UserRole> initialUserRoles = new ArrayList<>();
        ArrayList<UserRole> filteredUserRoles = new ArrayList<>();

        Role r1 = new Role();
        UserRole ur1 = new UserRole();
        ur1.setRole(r1);
        Party party1 = new Party();
        party1.setId(1L);
        party1.setDisplayName("party1");
        ur1.setParty(party1);
        filteredUserRoles.add(ur1);

        Role r2 = new Role();
        UserRole ur2 = new UserRole();
        ur2.setRole(r2);
        Party party2 = new Party();
        party2.setId(2L);
        party2.setDisplayName("party2");
        ur2.setParty(party2);
        filteredUserRoles.add(ur2);

        Role r3 = new Role();
        UserRole ur3 = new UserRole();
        ur3.setRole(r3);
        filteredUserRoles.add(ur3);

        when(userManager.getUserRoles(loggedInUser)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByPrivilege(initialUserRoles, Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(filteredUserRoles);


        // DO THE ACTUAL CALL
        Collection<PartyData> result = adminController.retrievePartiesToAdmin(initialUserRoles);

        PartyData[] partyDatas = Arrays.copyOf(result.toArray(), result.toArray().length, PartyData[].class);
        assertThat(partyDatas.length, is(2));
        assertThat(partyDatas[0].getPartyId(), is(party1.getId()));
        assertThat(partyDatas[1].getPartyId(), is(party2.getId()));
    }

    @Test
    public void test_retrievePartiesToAdmin_should_retrieveAllPartiesForBusinessLevelRole() throws Exception {

        ArrayList<UserRole> initialUserRoles = new ArrayList<>();
        ArrayList<UserRole> filteredUserRoles = new ArrayList<>();

        Role r3 = new Role();
        Business b3 = new Business();
        UserRole ur3 = new UserRole();
        ur3.setRole(r3);
        ur3.setBusiness(b3);
        filteredUserRoles.add(ur3);

        ArrayList<Party> parties = new ArrayList<>();
        Party p1 = new Party();
        p1.setId(1L);
        p1.setDisplayName("party1");
        parties.add(p1);
        Party p2 = new Party();
        p2.setId(2L);
        p2.setDisplayName("party2");
        parties.add(p2);

        when(userManager.getUserRoles(loggedInUser)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByPrivilege(initialUserRoles, Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(filteredUserRoles);
        when(partyManager.getPartiesOfBusiness(b3)).thenReturn(parties);

        // DO THE ACTUAL CALL
        Collection<PartyData> result = adminController.retrievePartiesToAdmin(initialUserRoles);

        PartyData[] partyDatas = Arrays.copyOf(result.toArray(), result.toArray().length, PartyData[].class);
        assertThat(partyDatas.length, is(2));
        assertThat(partyDatas[0].getPartyId(), is(p1.getId()));
        assertThat(partyDatas[1].getPartyId(), is(p2.getId()));
    }

    @Test
    public void test_retrievePartiesToAdmin_should_retrieveAllPartiesForSystemLevelRole() throws Exception {

        ArrayList<UserRole> initialUserRoles = new ArrayList<>();
        ArrayList<UserRole> filteredUserRoles = new ArrayList<>();

        Role r3 = new Role();
        UserRole ur3 = new UserRole();
        ur3.setRole(r3);
        filteredUserRoles.add(ur3);

        ArrayList<Party> parties = new ArrayList<>();
        Party p1 = new Party();
        p1.setId(1L);
        p1.setDisplayName("party1");
        parties.add(p1);

        Party p2 = new Party();
        p2.setId(2L);
        p2.setDisplayName("party2");
        parties.add(p2);

        when(userManager.getUserRoles(loggedInUser)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByPrivilege(initialUserRoles, Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(filteredUserRoles);
        when(partyManager.getAllParties()).thenReturn(parties);

        // DO THE ACTUAL CALL
        Collection<PartyData> result = adminController.retrievePartiesToAdmin(initialUserRoles);

        PartyData[] partyDatas = Arrays.copyOf(result.toArray(), result.toArray().length, PartyData[].class);
        assertThat(partyDatas.length, is(2));
        assertThat(partyDatas[0].getPartyId(), is(p1.getId()));
        assertThat(partyDatas[1].getPartyId(), is(p2.getId()));
    }

    @Test
    public void test_adminWithParties_should_haveParties_when_roleWithParty() throws Exception {

        ArrayList<UserRole> initialUserRoles = new ArrayList<>();
        ArrayList<UserRole> filteredUserRoles = new ArrayList<>();

        ArrayList<Party> parties = new ArrayList<>();
        Party p1 = new Party();
        p1.setId(1L);
        p1.setDisplayName("party1");
        parties.add(p1);

        Role r3 = new Role();
        Business b3 = new Business();
        UserRole ur3 = new UserRole();
        ur3.setRole(r3);
        ur3.setBusiness(b3);
        ur3.setParty(p1);
        filteredUserRoles.add(ur3);

        when(userManager.getUserRoles(loggedInUser)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByPrivilege(initialUserRoles, Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(filteredUserRoles);

        // DO THE ACTUAL CALL
        boolean result = adminController.adminWithParties(initialUserRoles);

        assertThat(result, is(true));
        verifyZeroInteractions(partyManager);
    }

    @Test
    public void test_retrieveBusinessesToAdmin_should_retrieveAllBusinessesForBusinessLevelRole() throws Exception {

        ArrayList<UserRole> initialUserRoles = new ArrayList<>();
        ArrayList<UserRole> filteredUserRoles = new ArrayList<>();

        Role r1 = new Role();
        UserRole ur1 = new UserRole();
        ur1.setRole(r1);
        Business b1 = new Business();
        b1.setId(1L);
        ur1.setBusiness(b1);
        filteredUserRoles.add(ur1);

        Role r2 = new Role();
        UserRole ur2 = new UserRole();
        ur2.setRole(r2);
        Business b2 = new Business();
        b2.setId(2L);
        ur2.setBusiness(b2);
        filteredUserRoles.add(ur2);

        when(userManager.getUserRoles(loggedInUser)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByPrivilege(initialUserRoles, Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES)).thenReturn(filteredUserRoles);


        // DO THE ACTUAL CALL
        List<Business> result = adminController.retrieveBusinessesToAdmin(initialUserRoles);

        assertThat(result.size(), is(2));
        assertThat(result.contains(b1), is(true));
        assertThat(result.contains(b2), is(true));
    }

    @Test
    public void test_retrieveBusinessesToAdmin_should_retrieveAllBusinessesForSystemLevelRole() throws Exception {

        ArrayList<UserRole> initialUserRoles = new ArrayList<>();
        ArrayList<UserRole> filteredUserRoles = new ArrayList<>();

        Role r3 = new Role();
        UserRole ur3 = new UserRole();
        ur3.setRole(r3);
        filteredUserRoles.add(ur3);

        ArrayList<Business> businesses = new ArrayList<>();
        Business b1 = new Business();
        b1.setId(1L);
        businesses.add(b1);

        Business b2 = new Business();
        b2.setId(2L);
        businesses.add(b2);

        when(userManager.getUserRoles(loggedInUser)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByPrivilege(initialUserRoles, Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES)).thenReturn(filteredUserRoles);
        when(businessManager.getAllBusinesses()).thenReturn(businesses);

        // DO THE ACTUAL CALL
        List<Business> result = adminController.retrieveBusinessesToAdmin(initialUserRoles);

        assertThat(result.size(), is(2));
        assertThat(result.contains(b1), is(true));
        assertThat(result.contains(b2), is(true));
    }

    @Test
    public void test_doGet_should_403_when_notAuthorized() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        AdminController spy = spy(adminController);
        doReturn(false).when(spy).isAuthorized();

        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(mockRequest.getRequestURI()).thenReturn("/e-trustex/admin.do");
        when(mockRequest.getQueryString()).thenReturn("test=10");
        when(loggedInUser.getLogin()).thenReturn("testUsr");

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doGet(mockRequest);

        assertThat(((RedirectViewWithLogging) modelAndView.getView()).getUrl(), is(equalTo("error/error403.do")));
    }

    @Test
    public void test_isAuthorized_should_returnTrue_when_hasCAN_ASSIGN_PARTY_SCOPE_ROLES() throws Exception {
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean result = adminController.isAuthorized();

        assertThat(result, is(true));

        verify(securityChecker).hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES);
        verifyNoMoreInteractions(securityChecker);
    }

    @Test
    public void test_isAuthorized_should_returnFalse_when_hasNotCAN_ASSIGN_PARTY_SCOPE_ROLES() throws Exception {
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = adminController.isAuthorized();

        assertThat(result, is(false));

        verify(securityChecker).hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES);
        verifyNoMoreInteractions(securityChecker);
    }

    @Test
    public void test_isAuthorizedForParty_should_returnTrue_when_hasCAN_ASSIGN_PARTY_SCOPE_ROLES() throws Exception {

        Party party = new Party();

        when(securityChecker.hasPrivilegeForParty(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES, party)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean result = adminController.isAuthorized(party);

        assertThat(result, is(true));

        verify(securityChecker).hasPrivilegeForParty(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES, party);
        verifyNoMoreInteractions(securityChecker);
    }

    @Test
    public void test_isAuthorizedForParty_should_returnFalse_when_hasNotCAN_ASSIGN_PARTY_SCOPE_ROLES() throws Exception {

        Party party = new Party();

        when(securityChecker.hasPrivilegeForParty(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES, party)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = adminController.isAuthorized(party);

        assertThat(result, is(false));

        verify(securityChecker).hasPrivilegeForParty(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES, party);
        verifyNoMoreInteractions(securityChecker);
    }

    @Test
    public void test_doSavePartyDetails_should_updateParty() throws Exception {
        Long partyId = 2L;
        String email = "email";
        boolean notificationsEnabled = true;

        AdminFormBean formBean = new AdminFormBean();
        formBean.setPartyId(partyId);
        formBean.setEmail(email);
        formBean.setNotificationsEnabled(notificationsEnabled);

        Party party = new Party();
        party.setId(partyId);
        when(partyManager.getPartyById(partyId)).thenReturn(party);
        when(securityChecker.allowSavePartyDetailsWithBusinessEmailEnforce(party, notificationsEnabled)).thenReturn(true);

        AdminController spy = spy(adminController);

        doReturn(true).when(spy).isAuthorized(party);

        // DO THE ACTUAL CALL
        AdminFormBean result = spy.doSavePartyDetails(formBean);

        assertThat(result, is(sameInstance(formBean)));
        assertThat(party.getId(), is(partyId));
        assertThat(party.getEmail(), is(email));
        assertThat(party.getNotificationsEnabled(), is(notificationsEnabled));

        verify(partyManager).saveOrUpdate(party);
    }

    @Test
    public void test_doSavePartyDetails_should_returnNull_when_emailEnforceServiceDisallow() throws Exception {
        Long partyId = 2L;
        String email = "email";
        boolean notificationsEnabled = true;

        AdminFormBean formBean = new AdminFormBean();
        formBean.setPartyId(partyId);
        formBean.setEmail(email);
        formBean.setNotificationsEnabled(notificationsEnabled);

        Party party = new Party();
        party.setId(partyId);
        when(partyManager.getPartyById(partyId)).thenReturn(party);
        when(securityChecker.allowSavePartyDetailsWithBusinessEmailEnforce(party, notificationsEnabled)).thenReturn(false);

        AdminController spy = spy(adminController);

        doReturn(true).when(spy).isAuthorized(party);

        // DO THE ACTUAL CALL
        AdminFormBean result = spy.doSavePartyDetails(formBean);

        assertThat(result, is(nullValue()));

        verify(partyManager, never()).saveOrUpdate(party);
    }

    @Test
    public void test_doSavePartyDetails_should_returnNull_when_notAuthorized() throws Exception {
        Long partyId = 2L;

        AdminFormBean formBean = new AdminFormBean();
        formBean.setPartyId(partyId);

        Party party = new Party();
        when(partyManager.getPartyById(partyId)).thenReturn(party);

        AdminController spy = spy(adminController);

        doReturn(false).when(spy).isAuthorized(party);

        // DO THE ACTUAL CALL
        AdminFormBean result = spy.doSavePartyDetails(formBean);

        assertThat(result, is(nullValue()));

        verify(partyManager).getPartyById(partyId);
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_doSavePartyDetails_should_returnNull_when_partyNotFound() throws Exception {
        Long partyId = 2L;

        AdminFormBean formBean = new AdminFormBean();
        formBean.setPartyId(partyId);

        when(partyManager.getPartyById(partyId)).thenReturn(null);

        // DO THE ACTUAL CALL
        AdminFormBean result = adminController.doSavePartyDetails(formBean);

        assertThat(result, is(nullValue()));

        verify(partyManager).getPartyById(partyId);
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_doLoadPartyDetails_should_populateFormBean() throws Exception {
        Long partyId = 2L;
        String email = "email";
        boolean notificationsEnabled = true;
        String businessName = "businessName";
        boolean statusNotificationEnabled = true;
        String statusNotificationEmail = "statusemail";

        AdminFormBean formBean = new AdminFormBean();
        formBean.setPartyId(partyId);

        Business business = new Business();
        business.setId(1L);
        business.setName(businessName);
        Party party = new Party();
        party.setId(partyId);
        party.setEmail(email);
        party.setNotificationsEnabled(notificationsEnabled);
        party.setBusiness(business);
        party.setStatusNotificationEnabled(statusNotificationEnabled);
        party.setStatusNotificationEmail(statusNotificationEmail);
        when(partyManager.getPartyById(partyId)).thenReturn(party);
        when(businessManager.getBusinessById(business.getId())).thenReturn(business);

        AdminController spy = spy(adminController);

        doReturn(true).when(spy).isAuthorized(party);

        // DO THE ACTUAL CALL
        AdminFormBean result = spy.doLoadPartyDetails(formBean);

        assertThat(result, is(sameInstance(formBean)));
        assertThat(result.getPartyId(), is(partyId));
        assertThat(result.getEmail(), is(email));
        assertThat(result.getNotificationsEnabled(), is(notificationsEnabled));
        assertThat(result.getBusinessLabel(), is(businessName));
        assertThat(result.getStatusNotificationEnabled(), is(statusNotificationEnabled));
        assertThat(result.getStatusNotificationEmail(), is(statusNotificationEmail));
    }

    @Test
    public void test_doLoadPartyDetails_should_returnNull_when_notAuthorized() throws Exception {
        Long partyId = 2L;

        AdminFormBean formBean = new AdminFormBean();
        formBean.setPartyId(partyId);

        Party party = new Party();
        when(partyManager.getPartyById(partyId)).thenReturn(party);

        AdminController spy = spy(adminController);

        doReturn(false).when(spy).isAuthorized(party);

        // DO THE ACTUAL CALL
        AdminFormBean result = spy.doLoadPartyDetails(formBean);

        assertThat(result, is(nullValue()));

        verify(partyManager).getPartyById(partyId);
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_doLoadPartyDetails_should_returnNull_when_partyNotFound() throws Exception {
        Long partyId = 2L;

        AdminFormBean formBean = new AdminFormBean();
        formBean.setPartyId(partyId);

        when(partyManager.getPartyById(partyId)).thenReturn(null);

        // DO THE ACTUAL CALL
        AdminFormBean result = adminController.doLoadPartyDetails(formBean);

        assertThat(result, is(nullValue()));

        verify(partyManager).getPartyById(partyId);
        verifyNoMoreInteractions(partyManager);
    }
}