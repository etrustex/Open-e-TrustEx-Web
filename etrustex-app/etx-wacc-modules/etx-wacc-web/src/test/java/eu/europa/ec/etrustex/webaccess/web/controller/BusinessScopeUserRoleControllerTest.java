package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.PartyData;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleFormBean;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static eu.europa.ec.etrustex.webaccess.web.ModelHelper.buildRole;
import static org.mockito.Mockito.*;

public class BusinessScopeUserRoleControllerTest extends AbstractTest {

    @InjectMocks
    private BusinessScopeUserRoleController userRoleController;

    @Mock
    protected BusinessManager businessManager;

    @Mock
    protected UserManager userManager;

    @Mock
    protected RoleUtils roleUtils;

    @Mock
    protected SecurityChecker securityChecker;

    @Mock
    protected PartyManager partyManager;


    @Mock
    protected IcaManager icaManager;

    @Test
    public void test_doAddBusinessScopeUserRole_should_createMissingEntitiesOrUpdateExisting() throws Exception {
        Long userRoleId = 5L;
        Long businessId = 2L;
        String name = "name";
        String login = "login";
        Role.Type roleType = Role.Type.OPERATOR;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setBusinessId(businessId);
        formBean.setName(name);
        formBean.setLogin(login);
        formBean.setRoleType(roleType);

        Business business = new Business();
        business.setId(businessId);

        User user = new User();
        UserRole userRole = new UserRole();
        userRole.setId(userRoleId);

        when(businessManager.getBusinessById(businessId)).thenReturn(business);
        when(securityChecker.hasPrivilegeForBusiness(Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES, business)).thenReturn(true);

        BusinessScopeUserRoleController spy = spy(userRoleController);

        doReturn(true).when(spy).isAuthorized(business);
        doReturn(user).when(spy).createOrRetrieveUser(login, name);
        doReturn(userRole).when(spy).createOrRetrieveBusinessUR(user, business, roleType);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doAddBusinessScopeUserRole(formBean);

        assertThat(result.getUserRoleId(), is(userRoleId));

        verify(spy).doAddBusinessScopeUserRole(formBean);
        verify(spy).isAuthorized(business);
        verify(spy).createOrRetrieveUser(login, name);
        verify(spy).createOrUpdateBUC(user, business, name);
        verify(spy).createOrRetrieveBusinessUR(user, business, roleType);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doAddBusinessScopeUserRole_should_returnErr_when_notAuthorized() throws Exception {
        Long businessId = 2L;
        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setBusinessId(businessId);
        Business business = new Business();

        when(businessManager.getBusinessById(businessId)).thenReturn(business);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(false).when(spy).isAuthorized(business);

        // DO THE ACTUAL CALL
        UserRoleFormBean result = spy.doAddBusinessScopeUserRole(formBean);

        assertThat(result, is(nullValue()));

        verify(spy).doAddBusinessScopeUserRole(formBean);
        verify(spy).isAuthorized(business);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_createOrRetrieveUR_should_returnUR_when_suchRecordExistsInDBAndBusinessScopeRole() throws Exception {

        String login = "login";

        List<UserRole> initialUserRoles = new ArrayList<>();

        Role.Type roleType = Role.Type.BUSINESS_ADMIN;

        User user = new User();
        user.setLogin(login);

        Business business = new Business();

        UserRole filteredUserRole = new UserRole();
        filteredUserRole.setId(3L);

        when(userManager.getUserRoles(user)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByRoleAndBusiness(initialUserRoles, roleType, business)).thenReturn(filteredUserRole);

        // DO THE ACTUAL CALL
        UserRole result = userRoleController.createOrRetrieveBusinessUR(user, business, roleType);

        assertThat(result, is(sameInstance(filteredUserRole)));
    }

    @Test
    public void test_createOrRetrieveUR_should_returnNewUR_when_noSuchRecordExistInDBAndBusinessScopeRole() throws Exception {

        String login = "login";

        List<UserRole> initialUserRoles = new ArrayList<>();


        Role.Type roleType = Role.Type.BUSINESS_ADMIN;
        Role role = buildRole(null, roleType, null);

        User user = new User();
        user.setLogin(login);

        Business business = new Business();

        when(userManager.getUserRoles(user)).thenReturn(initialUserRoles);
        when(roleUtils.filterUserRolesByRoleAndBusiness(initialUserRoles, roleType, business)).thenReturn(null);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(true);
        when(roleUtils.isBusinessScopeRole(roleType)).thenReturn(false);
        when(userManager.getRole(roleType)).thenReturn(role);

        // DO THE ACTUAL CALL
        UserRole result = userRoleController.createOrRetrieveBusinessUR(user, business, roleType);

        assertThat(result.getUser(), is(sameInstance(user)));
        assertThat(result.getBusiness(), is(sameInstance(business)));
        assertThat(result.getParty(), is(nullValue()));
        assertThat(result.getRole().getType(), is(roleType));
    }

    @Test
    public void test_doGetBusinessScopeUserRoles_should_returnUserRoleFormBeans() throws Exception {
        Long businessId = 3L;
        List<Role.Type> roleTypes = Arrays.asList(Role.Type.BUSINESS_ADMIN);
        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setBusinessId(businessId);
        formBean.setRoleTypes(roleTypes);
        Business business = new Business();
        List<UserRoleFormBean> formBeans = new ArrayList<>();

        when(businessManager.getBusinessById(businessId)).thenReturn(business);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(business);
        doReturn(true).when(spy).isValidRole(roleTypes);
        doReturn(formBeans).when(spy).transformToFormBeans(anyMap());

        //ACTUAL CALL
        List<UserRoleFormBean> result = spy.doGetBusinessScopeUserRoles(formBean);

        assertThat(result, is(sameInstance(formBeans)));

        verify(spy).isAuthorized(business);
    }

    @Test
    public void test_doGetBusinessScopeUserRoles_should_returnEmptyList_when_notAuthorized() throws Exception {
        Long businessId = 2L;

        UserRoleFormBean formBean = new UserRoleFormBean();
        formBean.setBusinessId(businessId);
        Business business = new Business();

        when(businessManager.getBusinessById(businessId)).thenReturn(business);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(false).when(spy).isAuthorized(business);

        // DO THE ACTUAL CALL
        List<UserRoleFormBean> result = spy.doGetBusinessScopeUserRoles(formBean);

        assertThat(result.size(), is(0));

        verify(spy).doGetBusinessScopeUserRoles(formBean);
        verify(spy).isAuthorized(business);
        verifyNoMoreInteractions(spy);
    }

    @Test
    public void test_doGetBusinessScopeParties_should_returnEmptyList_when_notAuthorized() throws Exception {
        Long businessId = 2L;

        Business business = new Business();
        business.setId(businessId);

        when(businessManager.getBusinessById(businessId)).thenReturn(business);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(false).when(spy).isAuthorized(business);

        // DO THE ACTUAL CALL
        List<PartyData> result = spy.doGetBusinessScopeParties(businessId);
        assertThat(result, is(notNullValue()));
        assertThat(result, is(empty()));

        verify(spy).doGetBusinessScopeParties(businessId);
        verify(spy).isAuthorized(business);
        verifyNoMoreInteractions(spy);

        verify(businessManager).getBusinessById(businessId);
        verifyZeroInteractions(partyManager);
    }

    @Test
    public void test_doGetBusinessScopeParties_should_returnSortedByPartyDisplaynameList() throws Exception {
        Long businessId = 2L;

        Business business = new Business();
        business.setId(businessId);

        when(businessManager.getBusinessById(businessId)).thenReturn(business);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(business);

        List<Party> parties = new ArrayList<>();
        Party party1 = new Party();
        party1.setDisplayName("party1DisplayName");
        party1.setNodeName("party1NodeName");
        Party party2 = new Party();
        party2.setDisplayName("party2DisplayName");
        party2.setNodeName("party2NodeName");
        List<PartyIca> partyIcas = new ArrayList<>();
        Party remoteParty = new Party();
        PartyIca partyIca = new PartyIca();
        partyIca.setRemoteParty(remoteParty);
        partyIca.setIntegrityCode(1);
        partyIca.setConfidentialityCode(1);
        partyIcas.add(partyIca);
        party1.setPartyICAs(partyIcas);
        // add unsorted by display name parties
        parties.add(party2);
        parties.add(party1);

        when(partyManager.getPartiesOfBusiness(business)).thenReturn(parties);

        when(icaManager.getIcasByParty(party1)).thenReturn(partyIcas);
        // DO THE ACTUAL CALL
        List<PartyData> result = spy.doGetBusinessScopeParties(businessId);
        assertThat(result, is(notNullValue()));

        assertThat(partyIcas, is(notNullValue()));
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getDisplayName(), is("party1DisplayName"));
        assertThat(result.get(0).getPartyIcas().size(), is(1));
        assertThat(result.get(1).getDisplayName(), is("party2DisplayName"));

        verify(spy).doGetBusinessScopeParties(businessId);
        verify(spy).isAuthorized(business);
        verifyNoMoreInteractions(spy);

        verify(partyManager).getPartiesOfBusiness(business);
        verifyNoMoreInteractions(partyManager);

        verify(businessManager).getBusinessById(businessId);
        verifyNoMoreInteractions(businessManager);
    }

    @Test
    public void test_doSavePartyDisplayName_should_returnEmptyList_when_notAuthorized() throws Exception {
        Long businessId = 2L;
        Long partyId = 1L;
        String displayName = "party1";

        Business business = new Business();
        business.setId(businessId);

        Party party = new Party();
        party.setId(partyId);
        party.setBusiness(business);

        when(partyManager.getPartyById(partyId)).thenReturn(party);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(false).when(spy).isAuthorized(business);

        // DO THE ACTUAL CALL
        ResponseEntity result = spy.doSavePartyDisplayName(partyId, displayName);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        verify(spy).doSavePartyDisplayName(partyId, displayName);
        verify(spy).isAuthorized(business);
        verifyNoMoreInteractions(spy);

        verifyZeroInteractions(businessManager);
        verify(partyManager).getPartyById(partyId);
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_doSavePartyDisplayName_should_returnBadRequest_when_emptyPartyDisplayName() throws Exception {
        Long businessId = 2L;
        Long partyId = 1L;
        String partyDisplayName = "";

        Business business = new Business();
        business.setId(businessId);

        Party party = new Party();
        party.setId(partyId);
        party.setBusiness(business);

        when(partyManager.getPartyById(partyId)).thenReturn(party);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(business);

        // DO THE ACTUAL CALL
        ResponseEntity result = spy.doSavePartyDisplayName(partyId, partyDisplayName);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        verify(spy).doSavePartyDisplayName(partyId, partyDisplayName);
        verify(spy).isAuthorized(business);
        verify(spy).isValidPartyDisplayName(partyDisplayName);
        verifyNoMoreInteractions(spy);

        verifyZeroInteractions(businessManager);
        verify(partyManager).getPartyById(partyId);
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_doSavePartyDisplayName_should_updatePartyDisplayName_whenDifferentPartyDisplayName() throws Exception {
        Long businessId = 2L;
        Long partyId = 1L;
        String partyDisplayName = "party1DisplayName";
        String partyOldDisplayName = "party1OldDisplayName";

        Business business = new Business();
        business.setId(businessId);

        Party party = new Party();
        party.setId(partyId);
        party.setBusiness(business);
        party.setDisplayName(partyOldDisplayName);

        when(partyManager.getPartyById(partyId)).thenReturn(party);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(business);

        // DO THE ACTUAL CALL
        ResponseEntity result = spy.doSavePartyDisplayName(partyId, partyDisplayName);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(HttpStatus.OK));

        verify(spy).doSavePartyDisplayName(partyId, partyDisplayName);
        verify(spy).isAuthorized(business);
        verify(spy).isValidPartyDisplayName(partyDisplayName);
        verifyNoMoreInteractions(spy);

        verifyZeroInteractions(businessManager);

        verify(partyManager).getPartyById(partyId);
        verify(partyManager).saveOrUpdate(party);
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_doSavePartyDisplayName_should_notUpdatePartyDisplayName_whenDifferentTheSameDisplayName() throws Exception {
        Long businessId = 2L;
        Long partyId = 1L;
        String partyDisplayName = "party1DisplayName";
        String partyOldDisplayName = "party1DisplayName";

        Business business = new Business();
        business.setId(businessId);

        Party party = new Party();
        party.setId(partyId);
        party.setBusiness(business);
        party.setDisplayName(partyOldDisplayName);

        when(partyManager.getPartyById(partyId)).thenReturn(party);

        BusinessScopeUserRoleController spy = spy(userRoleController);
        doReturn(true).when(spy).isAuthorized(business);

        // DO THE ACTUAL CALL
        ResponseEntity result = spy.doSavePartyDisplayName(partyId, partyDisplayName);
        assertThat(result, is(notNullValue()));
        assertThat(result.getStatusCode(), is(HttpStatus.OK));

        verify(spy).doSavePartyDisplayName(partyId, partyDisplayName);
        verify(spy).isAuthorized(business);
        verify(spy).isValidPartyDisplayName(partyDisplayName);
        verifyNoMoreInteractions(spy);

        verifyZeroInteractions(businessManager);

        verify(partyManager).getPartyById(partyId);
        verify(partyManager, times(0)).saveOrUpdate(party);
        verifyNoMoreInteractions(partyManager);
    }
}
