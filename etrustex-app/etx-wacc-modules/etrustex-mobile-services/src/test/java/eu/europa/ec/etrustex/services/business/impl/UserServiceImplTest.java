package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import eu.europa.ec.etrustex.webaccess.persistence.UserDAO;
import eu.europa.ec.etrustex.webaccess.persistence.UserRoleDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class UserServiceImplTest extends AbstractTest {

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    @Mock
    private UserRoleDAO userRoleDAO;

    @Mock
    UserDAO userDAO;

    @Test
    public void test_getUserRoles_should_call_userRoleDAO() {
        User user = new User();

        List<UserRole> urs = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setId(12345L);
        urs.add(userRole);
        when(userRoleDAO.getUserRoles(user)).thenReturn(urs);

        //ACTUAL CALL
        List<UserRole> result = userService.getUserRoles(user);

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(sameInstance(userRole)));

        verify(userRoleDAO).getUserRoles(argThat(sameInstance(user)));
        verifyNoMoreInteractions(userRoleDAO);
    }

    @Test
    public void test_loadBusinessUserConfigNameByAccessiblePartyForRoles_should_call_userRoleDAO() {
        List<UserRole> userRoles = new ArrayList<>();

        Map<Party, String> parties = new HashMap<>();
        Party party = new Party();
        party.setId(1L);
        party.setNodeName("NodeName");
        parties.put(party, "1");

        when(userRoleDAO.loadBusinessUserConfigNameByAccessiblePartyForRoles(userRoles)).thenReturn(parties);

        //ACTUAL CALL
        Map<Party, String> result = userService.loadBusinessUserConfigNameByAccessiblePartyForRoles(userRoles);

        assertThat(result.size(), is(1));
        assertThat(result.get(party), is(sameInstance("1")));

        verify(userRoleDAO).loadBusinessUserConfigNameByAccessiblePartyForRoles(userRoles);
        verifyNoMoreInteractions(userRoleDAO);
    }

    @Test
    public void test_loadBusinessUserConfigNameByAccessibleBusinessForRoles_should_call_userRoleDAO() {
        List<UserRole> userRoles = new ArrayList<>();

        Map<Party, String> parties = new HashMap<>();
        Party party = new Party();
        party.setId(1L);
        party.setNodeName("NodeName");
        parties.put(party, "1");

        when(userRoleDAO.loadBusinessUserConfigNameByAccessibleBusinessForRoles(userRoles)).thenReturn(parties);

        //ACTUAL CALL
        Map<Party, String> result = userService.loadBusinessUserConfigNameByAccessibleBusinessForRoles(userRoles);

        assertThat(result.size(), is(1));
        assertThat(result.get(party), is(sameInstance("1")));

        verify(userRoleDAO).loadBusinessUserConfigNameByAccessibleBusinessForRoles(userRoles);
        verifyNoMoreInteractions(userRoleDAO);
    }

    @Test
    public void test_getUserDetailsByLogin_should_call_UserDAO() {
        String userLogin = "userId";
        User user = new User();
        user.setId(1L);
        user.setName("John Doo");

        when(userDAO.getUser(userLogin)).thenReturn(user);

        //ACTUAL CALL
        User user2 = userService.getUserDetails(userLogin);

        assertThat(user2.getId(), is(1L));
        assertThat(user2.getName(), is("John Doo"));

        verify(userDAO).getUser(userLogin);
        verifyNoMoreInteractions(userDAO);

    }

    @Test
    public void test_getUserDetailsByLogin_userNull_should_return_null() {
        String userLogin = "userId";

        when(userDAO.getUser(userLogin)).thenReturn(null);

        //ACTUAL CALL
        User user = userService.getUserDetails(userLogin);

        assertThat(user, nullValue());

        verify(userDAO).getUser(userLogin);
        verifyNoMoreInteractions(userDAO);

    }

}
