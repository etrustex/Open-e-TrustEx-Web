package eu.europa.ec.etrustex.webaccess.persistence.support;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.persistence.UserDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author: micleva
 * @date: 10/14/13 9:33 AM
 * @project: ETX
 */
public class UserAccessorTest extends AbstractTest {

    @InjectMocks
    private UserAccessor userAccessor = new UserAccessor();

    @Mock
    private UserSessionContext userSessionContext;

    @Mock
    private UserDAO userDAO;

    @Test
    public void test_init_should_loadSystemUser() {
        //ACTUAL CALL
        userAccessor.init();

        verify(userDAO).getUser(UserAccessor.SYSTEM_USER_LOGIN);
    }

    @Test
    public void test_getLoggedUser_should_getSystemUser_when_UserSessionContextIsEmpty() {

        User systemUser = new User();
        when(userDAO.getUser(UserAccessor.SYSTEM_USER_LOGIN)).thenReturn(systemUser);
        userAccessor.init();

        //ACTUAL CALL
        User result = UserAccessor.getUser();

        assertThat(result, is(sameInstance(systemUser)));
    }

    @Test
    public void test_getLoggedUser_should_getUserFromUserSessionContext_when_UserSessionContextNotEmpty() {

        User contextUser = new User();
        when(userSessionContext.getUser()).thenReturn(contextUser);

        userAccessor.init();

        //ACTUAL CALL
        User result = UserAccessor.getUser();

        assertThat(result, is(sameInstance(contextUser)));
    }
}
