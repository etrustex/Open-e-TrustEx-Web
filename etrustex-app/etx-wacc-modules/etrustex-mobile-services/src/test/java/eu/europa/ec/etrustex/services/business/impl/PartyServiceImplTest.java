package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.PartyDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.Mockito.*;

public class PartyServiceImplTest extends AbstractTest {

    @InjectMocks
    private PartyServiceImpl partyService = new PartyServiceImpl();

    @Mock
    private UserService userService;

    @Mock
    private PartyDAO partyDAO;

    @Test
    public void test_getAllParties_should_call_PartyDAO() {
        List<Party> parties = new ArrayList<>();

        Party party = new Party();
        party.setId(1L);
        party.setNodeName("NodeName");
        parties.add(party);

        when(partyDAO.getAllParties()).thenReturn(parties);

        //ACTUAL CALL
        List<Party> result = partyService.getAllParties();

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(sameInstance(party)));

        verify(partyDAO).getAllParties();
        verifyNoMoreInteractions(partyDAO);
    }

    @Test
    public void test_getPartiesByUser_userNull_should_return_null() {
        String userId = "userId";

        when(userService.getUserDetails(userId)).thenReturn(null);

        //ACTUAL CALL
        List<Party> result = partyService.getPartiesByUser(userId);

        assertThat(result, nullValue());

        verify(userService).getUserDetails(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_getPartiesByUser_rolesNull_should_return_null() {
        String userId = "userId";
        User user = new User();

        when(userService.getUserDetails(userId)).thenReturn(user);
        when(userService.getUserRoles(user)).thenReturn(null);

        //ACTUAL CALL
        List<Party> result = partyService.getPartiesByUser(userId);

        assertThat(result, nullValue());

        verify(userService).getUserDetails(userId);
        verify(userService).getUserRoles(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_getPartiesByUser_rolesEmpty_should_return_null() {
        String userId = "userId";
        User user = new User();
        List<UserRole> userRoles = new ArrayList<>();

        when(userService.getUserDetails(userId)).thenReturn(user);
        when(userService.getUserRoles(user)).thenReturn(userRoles);

        //ACTUAL CALL
        List<Party> result = partyService.getPartiesByUser(userId);

        assertThat(result, nullValue());

        verify(userService).getUserDetails(userId);
        verify(userService).getUserRoles(user);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_getPartiesByUser_has_access_to_all_parties() {
        String userId = "userId";
        User user = new User();

        List<UserRole> userRoles = new ArrayList<>();

        UserRole userRole1 = new UserRole();
        Role role1 = new Role();
        Set<Privilege> privileges = new HashSet<>();
        Privilege privilege = new Privilege();
        privilege.setType(Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        privileges.add(privilege);
        role1.setPrivileges(privileges);
        userRole1.setRole(role1);
        userRoles.add(userRole1);

        List<Party> parties = new ArrayList<>();

        Party party1 = new Party();
        party1.setId(1L);
        party1.setDisplayName("Party1");
        parties.add(party1);

        Party party2 = new Party();
        party2.setId(2L);
        party2.setDisplayName("Party2");
        parties.add(party2);

        when(userService.getUserDetails(userId)).thenReturn(user);
        when(userService.getUserRoles(user)).thenReturn(userRoles);
        when(partyDAO.getAllParties()).thenReturn(parties);

        //ACTUAL CALL
        List<Party> result = partyService.getPartiesByUser(userId);

        assertThat(result.size(), is(2));
        assertThat(result.get(0), is(party1));
        assertThat(result.get(1), is(party2));

        verify(userService).getUserDetails(userId);
        verify(userService).getUserRoles(user);
        verifyNoMoreInteractions(userService);

        verify(partyDAO).getAllParties();
        verifyNoMoreInteractions(partyDAO);
    }

    @Test
    public void test_getPartiesByUser_has_party_level_roles() {
        String userId = "userId";
        User user = new User();

        List<UserRole> userRoles = new ArrayList<>();

        UserRole userRole1 = new UserRole();
        Role role1 = new Role();
        Set<Privilege> privileges = new HashSet<>();
        Privilege privilege = new Privilege();
        privilege.setType(Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        privileges.add(privilege);
        role1.setPrivileges(privileges);
        userRole1.setRole(role1);
        userRole1.setParty(new Party());
        userRoles.add(userRole1);

        Map<Party, String> businessUserNameByParty = new HashMap<>();
        Party party1 = new Party();
        party1.setId(1L);
        party1.setDisplayName("Party1");
        Party party2 = new Party();
        party2.setId(2L);
        party2.setDisplayName("Party2");
        businessUserNameByParty.put(party1, "1");
        businessUserNameByParty.put(party2, "2");

        when(userService.getUserDetails(userId)).thenReturn(user);
        when(userService.getUserRoles(user)).thenReturn(userRoles);
        when(userService.loadBusinessUserConfigNameByAccessiblePartyForRoles(userRoles)).thenReturn(businessUserNameByParty);

        //ACTUAL CALL
        List<Party> result = partyService.getPartiesByUser(userId);

        assertThat(result.size(), is(2));
        assertThat(result.get(0), is(party1));
        assertThat(result.get(1), is(party2));

        verify(userService).getUserDetails(userId);
        verify(userService).getUserRoles(user);
        verify(userService).loadBusinessUserConfigNameByAccessiblePartyForRoles(userRoles);
        verify(userService).loadBusinessUserConfigNameByAccessibleBusinessForRoles(new ArrayList<UserRole>());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_getPartiesByUser_has_business_level_roles() {
        String userId = "userId";
        User user = new User();

        List<UserRole> userRoles = new ArrayList<>();

        UserRole userRole1 = new UserRole();
        Role role1 = new Role();
        Set<Privilege> privileges = new HashSet<>();
        Privilege privilege = new Privilege();
        privilege.setType(Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        privileges.add(privilege);
        role1.setPrivileges(privileges);
        userRole1.setRole(role1);
        userRole1.setBusiness(new Business());
        userRoles.add(userRole1);

        Map<Party, String> businessUserNameByParty = new HashMap<>();
        Party party1 = new Party();
        party1.setId(1L);
        party1.setDisplayName("Party1");
        Party party2 = new Party();
        party2.setId(2L);
        party2.setDisplayName("Party2");
        businessUserNameByParty.put(party1, "1");
        businessUserNameByParty.put(party2, "2");

        when(userService.getUserDetails(userId)).thenReturn(user);
        when(userService.getUserRoles(user)).thenReturn(userRoles);
        when(userService.loadBusinessUserConfigNameByAccessibleBusinessForRoles(userRoles)).thenReturn(businessUserNameByParty);

        //ACTUAL CALL
        List<Party> result = partyService.getPartiesByUser(userId);

        assertThat(result.size(), is(2));
        assertThat(result.get(0), is(party1));
        assertThat(result.get(1), is(party2));

        verify(userService).getUserDetails(userId);
        verify(userService).getUserRoles(user);
        verify(userService).loadBusinessUserConfigNameByAccessiblePartyForRoles(new ArrayList<UserRole>());
        verify(userService).loadBusinessUserConfigNameByAccessibleBusinessForRoles(userRoles);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void test_getPartyById_should_callPartyDAO() {
        long partyId = 3L;

        //ACTUAL CALL
        partyService.getPartyById(partyId);

        verify(partyDAO).findById(partyId);
        verifyNoMoreInteractions(partyDAO);
    }

}
