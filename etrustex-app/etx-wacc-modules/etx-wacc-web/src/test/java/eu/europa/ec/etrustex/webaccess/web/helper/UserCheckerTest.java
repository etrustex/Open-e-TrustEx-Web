package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.*;

import static eu.europa.ec.etrustex.webaccess.web.ModelHelper.*;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class UserCheckerTest  extends AbstractTest {

    private MockHttpServletRequest request;

    @Mock
    private UserManager userManager;
    @Mock
    private PartyManager partyManager;
    @Mock
    private RoleUtils roleUtils;
    @Mock
    private UserSessionContext userSessionContext;

    @Captor
    private ArgumentCaptor<User> userCaptor;
    @Captor
    private ArgumentCaptor<Collection<Party>> messagePartiesCaptor;
    @Captor
    private  ArgumentCaptor<Map<Long, String>> userNameByPartyCaptor;
    @Captor
    private ArgumentCaptor<List<UserRole>> userRolesCaptor;

    @InjectMocks
    private UserChecker userChecker;

    protected void onSetUp() {
        request = new MockHttpServletRequest();
        request.setRequestURI("test.html");
    }

    @Test
    public void test_preHandle_should_returnSuccess_when_userNotLogged() throws Exception {
        // given
        when(userManager.getUserDetails("LOGIN")).thenReturn(null);

        // when
        boolean result = userChecker.checkUser("LOGIN");

        // then
        assertThat(result, is(false));

        verify(userSessionContext).getUser();
        verify(userManager).getUserDetails("LOGIN");
        verifyNoMoreInteractions(userManager, userSessionContext);
    }

    @Test
    public void test_preHandle_should_returnFailure_when_userHasNoRoles() throws Exception {
        // given
        User user = new User();
        when(userManager.getUserDetails("LOGIN")).thenReturn(user);
        when(userManager.getUserRoles(argThat(sameInstance(user)))).thenReturn(Collections.<UserRole>emptyList());

        // when
        boolean result = userChecker.checkUser("LOGIN");

        // then
        assertThat(result, is(false));

        verify(userSessionContext).setUser(userCaptor.capture());
        assertThat(userCaptor.getValue(), is(sameInstance(user)));

        verify(userSessionContext).getUser();
        verify(userManager).getUserDetails("LOGIN");
        verify(userManager).getUserRoles(argThat(sameInstance(user)));
        verifyNoMoreInteractions(userManager, userSessionContext);
    }

    @Test
    public void test_preHandle_should_returnFailure_when_userNotLoggedPrincipalLoginNotSet() throws Exception {
        // given

        // when
        boolean result = userChecker.checkUser(null);

        // then
        assertThat(result, is(false));

        verify(userSessionContext).getUser();
        verifyNoMoreInteractions(userManager, userSessionContext);
    }

    @Test
    public void test_preHandle_should_returnFailure_when_userNotLoggedPrincipalNotSet() throws Exception {
        // when
        boolean result = userChecker.checkUser(null);

        // then
        assertThat(result, is(false));

        verify(userSessionContext).getUser();
        verifyNoMoreInteractions(userManager, userSessionContext);
    }

    @Test
    public void test_preHandle_should_returnSuccess_when_userAlreadyLogged() throws Exception {
        // given
        User user = new User();
        when(userSessionContext.getUser()).thenReturn(user);

        // when
        boolean result = userChecker.checkUser(null);

        // then
        assertThat(result, is(true));

        verify(userSessionContext).getUser();
        verifyNoMoreInteractions(userManager, userSessionContext);
    }

    @Test
    public void test_setupUserContext_should_setMessageParties_when_loadByParty() throws Exception {
        // given
        final String loginName = "testUser";

        User user = new User();

        Business business = new Business();

        Party party = new Party();
        party.setId(1L);
        party.setDisplayName("party");
        party.setBusiness(business);

        Party party2 = new Party();
        party2.setId(2L);
        party2.setDisplayName("party2");
        party2.setBusiness(business);

        Privilege privilege = buildPrivilege("whatever", Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        Role role = buildRole("Operator", Role.Type.OPERATOR, Collections.singleton(privilege));

        UserRole userRoleOperator1 = buildUserRole(1L, user, role, party, null);
        UserRole userRoleOperator2 = buildUserRole(2L, user, role, party2, null);
        List<UserRole> userRoles = Arrays.asList(userRoleOperator1, userRoleOperator2);
        when(roleUtils.roleHasPrivilege(role, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(true);

        Map<Party, String> partiesWithBusinessUserConfigs = new HashMap<>();
        partiesWithBusinessUserConfigs.put(party, "aaa");
        partiesWithBusinessUserConfigs.put(party2, "bbb");
        when(userManager.getUserDetails(loginName)).thenReturn(user);
        when(userManager.getUserRoles(user)).thenReturn(userRoles);
        when(userManager.loadBusinessUserConfigNameByAccessiblePartyForRoles(userRoles)).thenReturn(partiesWithBusinessUserConfigs);

        // when
        boolean result = userChecker.checkUser(loginName);

        // then
        assertThat(result, is(true));
        verify(userSessionContext).getUser();
        verify(userSessionContext).setUser(userCaptor.capture());
        assertThat(userCaptor.getValue(), is(sameInstance(user)));

        verify(userSessionContext).setUserRoleList(userRolesCaptor.capture());
        assertThat(userRolesCaptor.getValue(), is(sameInstance(userRoles)));

        verify(userSessionContext).setMessageParties(messagePartiesCaptor.capture());
        final Collection<Party> messageParties = messagePartiesCaptor.getValue();
        assertThat(messageParties.size(), is(2));
        assertThat(messageParties, (Matcher) hasItem(sameInstance(party)));
        assertThat(messageParties, (Matcher) hasItem(sameInstance(party2)));

        verify(userSessionContext).setBusinessUserNameByParty(userNameByPartyCaptor.capture());
        final Map<Long, String> userNameByParty = userNameByPartyCaptor.getValue();
        assertThat(userNameByParty.size(), is(2));
        assertThat(userNameByParty, hasEntry(equalTo(party.getId()), equalTo("aaa")));
        assertThat(userNameByParty, hasEntry(equalTo(party2.getId()), equalTo("bbb")));

        verify(userManager).getUserDetails(loginName);
        verify(userManager).getUserRoles(user);
        verify(userManager).loadBusinessUserConfigNameByAccessiblePartyForRoles(userRoles);

        verify(roleUtils, times(2)).roleHasPrivilege(role, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        verify(userManager).loadBusinessUserConfigNameByAccessiblePartyForRoles(userRoles);
        verify(userManager).loadBusinessUserConfigNameByAccessibleBusinessForRoles(Collections.<UserRole>emptyList());

        verifyNoMoreInteractions(roleUtils, userManager, partyManager, userSessionContext);
    }

    @Test
    public void test_setupUserContext_should_setMessageParties_when_loadByPartyAndBusiness() throws Exception {
        // given
        final String loginName = "testUser";

        User user = new User();

        Business business = new Business();
        business.setId(11L);

        Party party = new Party();
        party.setId(1L);
        party.setDisplayName("party");
        party.setBusiness(business);

        business.setParties(Collections.singletonList(party));

        Business business2 = new Business();
        business2.setId(22L);
        Party party2 = new Party();
        party2.setId(2L);
        party2.setDisplayName("party2");
        party2.setBusiness(business2);

        Privilege privilege = buildPrivilege("whatever", Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        Role role = buildRole("Operator", Role.Type.OPERATOR, Collections.singleton(privilege));

        UserRole userRoleOperator1 = buildUserRole(1L, user, role, null, business);
        UserRole userRoleOperator2 = buildUserRole(2L, user, role, party2, null);

        List<UserRole> partyUserRoles = Collections.singletonList(userRoleOperator2);
        List<UserRole> businessUserRoles = Collections.singletonList(userRoleOperator1);

        Map<Party, String> partiesWithBusinessUserConfigs = new HashMap<>();
        partiesWithBusinessUserConfigs.put(party, "aaa");
        when(userManager.loadBusinessUserConfigNameByAccessiblePartyForRoles(partyUserRoles)).thenReturn(partiesWithBusinessUserConfigs);

        Map<Party, String> partiesWithBusinessUserConfigs2 = new HashMap<>();
        partiesWithBusinessUserConfigs.put(party2, "bbb");
        when(userManager.loadBusinessUserConfigNameByAccessibleBusinessForRoles(businessUserRoles)).thenReturn(partiesWithBusinessUserConfigs2);

        Privilege businessRolePriv = buildPrivilege("whatever", Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES);
        Role businessRole = buildRole("business admin", Role.Type.BUSINESS_ADMIN, Collections.singleton(businessRolePriv));
        UserRole businessUserRole = buildUserRole(3L, user, businessRole, party, null);

        List<UserRole> userRoles = Arrays.asList(userRoleOperator1, userRoleOperator2, businessUserRole);

        when(roleUtils.roleHasPrivilege(role, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(true);
        when(userManager.getUserRoles(user)).thenReturn(userRoles);
        when(userManager.getUserDetails(loginName)).thenReturn(user);

        // when
        boolean result = userChecker.checkUser(loginName);

        // then
        assertThat(result, is(true));
        verify(userSessionContext).getUser();
        verify(userSessionContext).setUser(userCaptor.capture());
        assertThat(userCaptor.getValue(), is(sameInstance(user)));

        verify(userSessionContext).setUserRoleList(userRolesCaptor.capture());
        assertThat(userRolesCaptor.getValue(), is(sameInstance(userRoles)));

        verify(userSessionContext).setMessageParties(messagePartiesCaptor.capture());
        final Collection<Party> messageParties = messagePartiesCaptor.getValue();
        assertThat(messageParties.size(), is(2));
        assertThat(messageParties, (Matcher) hasItem(sameInstance(party)));
        assertThat(messageParties, (Matcher) hasItem(sameInstance(party2)));

        verify(userSessionContext).setBusinessUserNameByParty(userNameByPartyCaptor.capture());
        final Map<Long, String> userNameByParty = userNameByPartyCaptor.getValue();
        assertThat(userNameByParty.size(), is(2));
        assertThat(userNameByParty, hasEntry(equalTo(party.getId()), equalTo("aaa")));
        assertThat(userNameByParty, hasEntry(equalTo(party2.getId()), equalTo("bbb")));

        verify(roleUtils, times(2)).roleHasPrivilege(role, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        verify(roleUtils).roleHasPrivilege(businessRole, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);

        verify(userManager).getUserDetails(loginName);
        verify(userManager).getUserRoles(user);
        verify(userManager).loadBusinessUserConfigNameByAccessiblePartyForRoles(partyUserRoles);
        verify(userManager).loadBusinessUserConfigNameByAccessibleBusinessForRoles(businessUserRoles);

        verifyNoMoreInteractions(roleUtils, userManager, partyManager, userSessionContext);
    }

    @Test
    public void test_setupUserContext_should_NotSetMessageParty_when_fullAccess() throws Exception {
        // given
        final String loginName = "testUser";

        User user = new User();
        user.setName("userName");

        Business business = new Business();

        Party party = new Party();
        party.setId(1L);
        party.setBusiness(business);

        Privilege privilege = buildPrivilege("whatever", Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        Role role = buildRole("Operator", Role.Type.OPERATOR, Collections.singleton(privilege));
        UserRole userRoleOperator1 = buildUserRole(1L, user, role, null, null);

        when(roleUtils.roleHasPrivilege(role, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(true);

        List<Party> allParties = Collections.singletonList(party);
        when(partyManager.getAllParties()).thenReturn(allParties);

        final List<UserRole> userRoles = Arrays.asList(userRoleOperator1);
        when(userManager.getUserRoles(user)).thenReturn(userRoles);
        when(userManager.getUserDetails(loginName)).thenReturn(user);

        // when
        boolean result = userChecker.checkUser(loginName);

        // then
        assertThat(result, is(true));
        verify(userSessionContext).getUser();
        verify(userSessionContext).setUser(userCaptor.capture());
        assertThat(userCaptor.getValue(), is(sameInstance(user)));

        verify(userSessionContext).setUserRoleList(userRolesCaptor.capture());
        assertThat(userRolesCaptor.getValue(), is(sameInstance(userRoles)));

        verify(userSessionContext).setMessageParties(messagePartiesCaptor.capture());
        final Collection<Party> messageParties = messagePartiesCaptor.getValue();
        assertThat(messageParties.size(), is(1));
        assertThat(messageParties, (Matcher) hasItem(sameInstance(party)));

        verify(userSessionContext).setBusinessUserNameByParty(userNameByPartyCaptor.capture());
        final Map<Long, String> userNameByParty = userNameByPartyCaptor.getValue();
        assertThat(userNameByParty.size(), is(1));
        assertThat(userNameByParty, hasEntry(equalTo(party.getId()), equalTo("userName")));

        verify(roleUtils).roleHasPrivilege(role, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        verify(partyManager).getAllParties();
        verify(userManager).getUserDetails(loginName);
        verify(userManager).getUserRoles(user);

        verifyNoMoreInteractions(roleUtils, userManager, partyManager, userSessionContext);    }
}