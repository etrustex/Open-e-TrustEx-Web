package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.*;
import eu.europa.ec.etrustex.webaccess.utils.UserRegistrationWSScenarios;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.Mockito.*;


/**
 * @author apladap
 */
@SuppressWarnings("unchecked")
public class PartyManagerImplTest extends AbstractTest {

    @InjectMocks
    private PartyManagerImpl partyManager = new PartyManagerImpl();

    @Mock
    private PartyDAO partyDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private BusinessUserConfigDAO businessUserConfigDAO;

    @Mock
    private UserRoleDAO userRoleDAO;

    @Mock
    private RoleDAO roleDAO;

    @Test
    public void testFindAvailablePartiesPerApplication_Success() {
        List<String> availableParties = new ArrayList<>();
        availableParties.add("Party1");
        availableParties.add("Party2");

        String application = "Application";

        when(partyDAO.getAvailablePartiesPerBusiness(argThat(is(application)))).thenReturn(availableParties);

        List<String> expectedResultSet = partyManager.findAvailablePartiesPerBusiness(application);

        verify(partyDAO).getAvailablePartiesPerBusiness(argThat(is(application)));
        assertThat(expectedResultSet, notNullValue());
        assertThat(expectedResultSet.size(), is(2));
        assertThat(expectedResultSet, hasItem("Party1"));
        assertThat(expectedResultSet, hasItem("Party2"));
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testFindAvailablePartiesPerApplication_NullList() {
        String application = "Application";

        when(partyDAO.getAvailablePartiesPerBusiness(argThat(is(application)))).thenReturn(null);

        List<String> expectedResultSet = partyManager.findAvailablePartiesPerBusiness(application);

        verify(partyDAO).getAvailablePartiesPerBusiness(argThat(is(application)));
        assertThat(expectedResultSet, nullValue());
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void testFindAvailablePartiesPerApplication_EmptyList() {
        List<String> availableParties = new ArrayList<>();

        String application = "Application";

        when(partyDAO.getAvailablePartiesPerBusiness(argThat(is(application)))).thenReturn(availableParties);

        List<String> expectedResultSet = partyManager.findAvailablePartiesPerBusiness(application);

        verify(partyDAO).getAvailablePartiesPerBusiness(argThat(is(application)));
        assertThat(expectedResultSet, notNullValue());
        assertThat(expectedResultSet.size(), is(0));
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void test_assignUserToParty_should_createRequiredEntities() {
        String userLogin = "apladap";
        String userFirstName = "Apostolis";
        String userLastName = "Apladas";
        String partyRefId = "TEST_REF_ID";
        String newPartyDisplayName = "NEW PARTY NAME";
        String businessName = "EDMA";
        String eMail = "  new_address@test.com  ";

        Party requestedParty = new Party();
        requestedParty.setNodeName(partyRefId);
        requestedParty.setCreatedOn(getCreationDate());
        requestedParty.setDisplayName("OLD PARTY NAME");

        Business requestedBusiness = new Business();
        requestedBusiness.setName(businessName);
        requestedParty.setBusiness(requestedBusiness);

        Role requestedRole = new Role();

        when(partyDAO.getWebManagedPartyByNodeName(partyRefId)).thenReturn(requestedParty);
        when(partyDAO.isPartyAlreadyAssigned(partyRefId)).thenReturn(false);
        when(userDAO.getUser(userLogin)).thenReturn(null);
        when(businessUserConfigDAO.getBusinessUserConfig(null, requestedBusiness)).thenReturn(null);
        when(userRoleDAO.getUserRolesWithPrivilege(userLogin, requestedBusiness, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(new ArrayList<UserRole>());
        when(roleDAO.getRole(Role.Type.PARTY_ADMIN)).thenReturn(requestedRole);

        //ACTUAL CALL
        UserRegistrationWSScenarios result = partyManager.assignUserToParty(userLogin, userFirstName, userLastName, partyRefId, newPartyDisplayName, businessName, eMail);

        assertThat(result, is(UserRegistrationWSScenarios.PARTY_MAPPED_SUCCESSFULLY));

        verify(partyDAO).getWebManagedPartyByNodeName(partyRefId);
        verify(partyDAO).isPartyAlreadyAssigned(partyRefId);

        ArgumentCaptor<Party> partyArgumentCaptor = ArgumentCaptor.forClass(Party.class);
        verify(partyDAO).update(partyArgumentCaptor.capture());
        assertThat(partyArgumentCaptor.getValue().getDisplayName(), is(newPartyDisplayName));
        verifyNoMoreInteractions(partyDAO);

        verify(userDAO).getUser(userLogin);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDAO).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        assertThat(user.getLogin(), is(userLogin));
        assertThat(user.getName(), is(userLastName + " " + userFirstName));
        verifyNoMoreInteractions(userDAO);

        verify(businessUserConfigDAO).getBusinessUserConfig(user, requestedBusiness);
        ArgumentCaptor<BusinessUserConfig> businessUserConfigArgumentCaptor = ArgumentCaptor.forClass(BusinessUserConfig.class);
        verify(businessUserConfigDAO).save(businessUserConfigArgumentCaptor.capture());
        BusinessUserConfig businessUserConfig = businessUserConfigArgumentCaptor.getValue();
        assertThat(businessUserConfig.getName(), is(userLastName + " " + userFirstName));
        assertThat(businessUserConfig.getEmail(), is(eMail.trim()));
        assertThat(businessUserConfig.getUser(), is(sameInstance(user)));
        assertThat(businessUserConfig.getBusiness(), is(sameInstance(requestedBusiness)));
        verifyNoMoreInteractions(businessUserConfigDAO);

        verify(userRoleDAO).getUserRolesWithPrivilege(userLogin, requestedBusiness, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        ArgumentCaptor<UserRole> userRoleArgumentCaptor = ArgumentCaptor.forClass(UserRole.class);
        verify(userRoleDAO).save(userRoleArgumentCaptor.capture());
        UserRole userRole = userRoleArgumentCaptor.getValue();
        assertThat(userRole.getUser(), is(sameInstance(user)));
        assertThat(userRole.getBusiness(), is(nullValue()));
        assertThat(userRole.getParty(), is(sameInstance(requestedParty)));
        assertThat(userRole.getRole(), is(sameInstance(requestedRole)));
        verifyNoMoreInteractions(userRoleDAO);

        verify(roleDAO).getRole(Role.Type.PARTY_ADMIN);
        verifyNoMoreInteractions(roleDAO);
    }


    @Test
    public void test_assignUserToParty_should_returnError_when_unknownParty() {
        String userLogin = "apladap";
        String userFirstName = "Apostolis";
        String userLastName = "Apladas";
        String partyRefId = "TEST_REF_ID";
        String application = "EDMA";
        String eMail = "new_address@test.com";
        String newPartyDisplayName = null;

        when(partyDAO.getWebManagedPartyByNodeName(partyRefId)).thenReturn(null);

        //ACTUAL CALL
        UserRegistrationWSScenarios result = partyManager.assignUserToParty(userLogin, userFirstName, userLastName, partyRefId, newPartyDisplayName, application, eMail);

        assertThat(result, is(UserRegistrationWSScenarios.PARTY_UNKNOWN));

        verify(partyDAO).getWebManagedPartyByNodeName(partyRefId);
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(userDAO);
        verifyNoMoreInteractions(businessUserConfigDAO);
        verifyNoMoreInteractions(userRoleDAO);
        verifyNoMoreInteractions(roleDAO);
    }


    @Test
    public void test_assignUserToParty_should_returnError_when_unavailableParty() {
        String userLogin = "apladap";
        String userFirstName = "Apostolis";
        String userLastName = "Apladas";
        String partyNodeName = "TEST_REF_ID";
        String application = "EDMA";
        String eMail = "new_address@test.com";
        String newPartyDisplayName = null;

        Party requestedParty = new Party();
        requestedParty.setId(1L);
        requestedParty.setNodeName("test_party");

        when(partyDAO.getWebManagedPartyByNodeName(partyNodeName)).thenReturn(requestedParty);
        when(partyDAO.isPartyAlreadyAssigned(partyNodeName)).thenReturn(true);

        //ACTUAL CALL
        UserRegistrationWSScenarios result = partyManager.assignUserToParty(userLogin, userFirstName, userLastName, partyNodeName, newPartyDisplayName, application, eMail);

        assertThat(result, is(UserRegistrationWSScenarios.PARTY_UNAVAILABLE));

        verify(partyDAO).getWebManagedPartyByNodeName(partyNodeName);
        verify(partyDAO).isPartyAlreadyAssigned(partyNodeName);
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(userDAO);
        verifyNoMoreInteractions(businessUserConfigDAO);
        verifyNoMoreInteractions(userRoleDAO);
        verifyNoMoreInteractions(roleDAO);
    }

    @Test
    public void test_assignUserToParty_should_returnError_when_userAlreadyAssigned() {
        String userLogin = "apladap";
        String userFirstName = "Apostolis";
        String userLastName = "Apladas";
        String partyRefId = "TEST_REF_ID";
        String businessName = "EDMA";
        String eMail = "new_address@test.com";
        String newPartyDisplayName = null;

        Party requestedParty = new Party();
        requestedParty.setNodeName(partyRefId);
        requestedParty.setCreatedOn(getCreationDate());

        Business requestedBusiness = new Business();
        requestedBusiness.setName(businessName);
        requestedParty.setBusiness(requestedBusiness);

        Role requestedRole = new Role();
        User requestedUser = new User();

        when(partyDAO.getWebManagedPartyByNodeName(partyRefId)).thenReturn(requestedParty);
        when(partyDAO.isPartyAlreadyAssigned(partyRefId)).thenReturn(false);
        when(userDAO.getUser(userLogin)).thenReturn(requestedUser);
        when(businessUserConfigDAO.getBusinessUserConfig(null, requestedBusiness)).thenReturn(new BusinessUserConfig());
        List<UserRole> operators = Arrays.asList(new UserRole());
        when(userRoleDAO.getUserRolesWithPrivilege(userLogin, requestedBusiness, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(operators);
        when(roleDAO.getRole(Role.Type.OPERATOR)).thenReturn(requestedRole);

        //ACTUAL CALL
        UserRegistrationWSScenarios result = partyManager.assignUserToParty(userLogin, userFirstName, userLastName, partyRefId, newPartyDisplayName, businessName, eMail);

        assertThat(result, is(UserRegistrationWSScenarios.USER_ALREADY_ASSIGNED));

        verify(partyDAO).getWebManagedPartyByNodeName(partyRefId);
        verify(partyDAO).isPartyAlreadyAssigned(partyRefId);
        verify(userDAO).getUser(userLogin);
        verify(userDAO).update(requestedUser);
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(userDAO);
    }


    @Test
    public void test_assignUserToParty_should_activate_existingUser() {
        String userLogin = "apladap";
        String userFirstName = "Apostolis";
        String userLastName = "Apladas";
        String partyRefId = "TEST_REF_ID";
        String businessName = "EDMA";
        String eMail = "new_address@test.com";
        String newPartyDisplayName = null;

        Party requestedParty = new Party();
        requestedParty.setNodeName(partyRefId);
        requestedParty.setCreatedOn(getCreationDate());

        Business requestedBusiness = new Business();
        requestedBusiness.setName(businessName);
        requestedParty.setBusiness(requestedBusiness);

        Role requestedRole = new Role();
        User requestedUser = new User();

        when(partyDAO.getWebManagedPartyByNodeName(partyRefId)).thenReturn(requestedParty);
        when(partyDAO.isPartyAlreadyAssigned(partyRefId)).thenReturn(false);
        when(userDAO.getUser(userLogin)).thenReturn(requestedUser);
        when(businessUserConfigDAO.getBusinessUserConfig(null, requestedBusiness)).thenReturn(new BusinessUserConfig());
        when(userRoleDAO.getUserRolesWithPrivilege(userLogin, requestedBusiness, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(Collections.EMPTY_LIST);
        when(roleDAO.getRole(Role.Type.OPERATOR)).thenReturn(requestedRole);

        //ACTUAL CALL
        UserRegistrationWSScenarios result = partyManager.assignUserToParty(userLogin, userFirstName, userLastName, partyRefId, newPartyDisplayName, businessName, eMail);

        assertThat(result, is(UserRegistrationWSScenarios.PARTY_MAPPED_SUCCESSFULLY));
        assertThat(requestedUser.getActiveState(), is(true));

        verify(partyDAO).getWebManagedPartyByNodeName(partyRefId);
        verify(partyDAO).isPartyAlreadyAssigned(partyRefId);
        verify(userDAO).getUser(userLogin);
        verify(userDAO).update(requestedUser);
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void test_assignUserToParty_should_not_update_party() {
        String userLogin = "apladap";
        String userFirstName = "Apostolis";
        String userLastName = "Apladas";
        String partyRefId = "TEST_REF_ID";
        String businessName = "EDMA";
        String eMail = "new_address@test.com";
        String newPartyDisplayName = new String("My old party name");

        Party requestedParty = new Party();
        requestedParty.setNodeName(partyRefId);
        requestedParty.setCreatedOn(getCreationDate());
        String oldDisplayName = new String("My old party name");
        requestedParty.setDisplayName(oldDisplayName);

        Business requestedBusiness = new Business();
        requestedBusiness.setName(businessName);
        requestedParty.setBusiness(requestedBusiness);

        Role requestedRole = new Role();
        User requestedUser = new User();

        when(partyDAO.getWebManagedPartyByNodeName(partyRefId)).thenReturn(requestedParty);
        when(partyDAO.isPartyAlreadyAssigned(partyRefId)).thenReturn(false);
        when(userDAO.getUser(userLogin)).thenReturn(requestedUser);
        when(businessUserConfigDAO.getBusinessUserConfig(null, requestedBusiness)).thenReturn(new BusinessUserConfig());
        when(userRoleDAO.getUserRolesWithPrivilege(userLogin, requestedBusiness, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(Collections.EMPTY_LIST);
        when(roleDAO.getRole(Role.Type.OPERATOR)).thenReturn(requestedRole);

        //ACTUAL CALL
        UserRegistrationWSScenarios result = partyManager.assignUserToParty(userLogin, userFirstName, userLastName, partyRefId, newPartyDisplayName, businessName, eMail);

        assertThat(result, is(UserRegistrationWSScenarios.PARTY_MAPPED_SUCCESSFULLY));
        assertThat(requestedUser.getActiveState(), is(true));
        assertThat(requestedParty.getDisplayName(), sameInstance(oldDisplayName));
        assertThat(requestedParty.getDisplayName(), not(sameInstance(newPartyDisplayName)));

        verify(partyDAO).getWebManagedPartyByNodeName(partyRefId);
        verify(partyDAO).isPartyAlreadyAssigned(partyRefId);
        verify(userDAO).getUser(userLogin);
        verify(userDAO).update(requestedUser);
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(userDAO);
    }


    private Date getCreationDate() {
        Calendar creationDate = Calendar.getInstance();

        return creationDate.getTime();
    }

    private Date getExpiryDate() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.add(Calendar.DATE, -30);

        return expiryDate.getTime();
    }

    @Test
    public void test_getPartyById_should_callPartyDAO() {
        long partyId = 3L;

        //ACTUAL CALL
        partyManager.getPartyById(partyId);

        verify(partyDAO).findById(partyId);
    }

    @Test
    public void test_getPartiesOfBusiness_should_callPartyDAO() {
        Business business = new Business();

        //ACTUAL CALL
        partyManager.getPartiesOfBusiness(business);

        verify(partyDAO).getPartiesOfBusiness(business);
    }

    @Test
    public void test_getAllParties_should_callPartyDAO() {

        //ACTUAL CALL
        partyManager.getAllParties();

        verify(partyDAO).getAllParties();
    }

    @Test
    public void test_saveOrUpdate_should_saveBusinessUserConfig_when_noIdSet() {
        Party party = new Party();

        //ACTUAL CALL
        partyManager.saveOrUpdate(party);

        verify(partyDAO).save(party);
        verifyNoMoreInteractions(partyDAO);
    }

    @Test
    public void test_saveOrUpdate_should_updateBusinessUserConfig_when_idSet() {
        Party party = new Party();
        party.setId(1L);

        //ACTUAL CALL
        partyManager.saveOrUpdate(party);

        verify(partyDAO).update(party);
        verifyNoMoreInteractions(partyDAO);
    }

    @Test
    public void test_findWebManagedPartyByNodeName_Success() {
        Party party = new Party();
        party.setId(1L);
        party.setNodeName("nodeName");
        party.setWebManaged(true);

        when(partyDAO.getWebManagedPartyByNodeName(party.getNodeName())).thenReturn(party);

        //ACTUAL CALL
        Party expectedResult = partyManager.getWebManagedPartyByNodeName(party.getNodeName());

        verify(partyDAO).getWebManagedPartyByNodeName(party.getNodeName());
        assertThat(expectedResult, notNullValue());
        assertThat(expectedResult.getId(), is(equalTo(party.getId())));
        assertThat(expectedResult.getNodeName(), is(equalTo(party.getNodeName())));
        verifyNoMoreInteractions(partyDAO);
    }

    @Test
    public void test_getRemotePartyByNodeNameBusId_Success() {
        Business business = new Business();
        business.setId(1L);

        Party party = new Party();
        party.setId(1L);
        party.setNodeName("nodeName");
        party.setBusiness(business);

        when(partyDAO.getRemotePartyByNodeNameBusId(party.getNodeName(), business.getId())).thenReturn(party);

        //ACTUAL CALL
        Party expectedResult = partyManager.getRemotePartyByNodeNameBusId(party.getNodeName(), business.getId());

        verify(partyDAO).getRemotePartyByNodeNameBusId(party.getNodeName(), business.getId());
        assertThat(expectedResult, notNullValue());
        assertThat(expectedResult.getId(), is(equalTo(party.getId())));
        assertThat(expectedResult.getNodeName(), is(equalTo(party.getNodeName())));
        verifyNoMoreInteractions(partyDAO);
    }

    @Test
    public void test_getRemoteParties_Success() {
        Party party = new Party();

        List<Party> remoteParties = new ArrayList();
        Party remoteParty1 = new Party();
        remoteParties.add(remoteParty1);
        Party remoteParty2 = new Party();
        remoteParties.add(remoteParty2);

        when(partyDAO.getRemoteParties(party)).thenReturn(remoteParties);

        //ACTUAL CALL
        List<Party> expectedResult = partyManager.getRemoteParties(party);

        verify(partyDAO).getRemoteParties(party);
        assertThat(expectedResult, notNullValue());
        assertThat(expectedResult.size(), is(2));
        assertThat(expectedResult, hasItem(remoteParty1));
        assertThat(expectedResult, hasItem(remoteParty2));
        verifyNoMoreInteractions(partyDAO);
    }
}
