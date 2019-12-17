package eu.europa.ec.etrustex.webaccess.web.utils;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RedirectViewWithLoggingTest extends AbstractTest {

    @Mock
    private UserSessionContext userSessionContext;

    @Mock
    HttpServletRequest request;

    private User loggedInUser;

    protected void onSetUp() {
        loggedInUser = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
    }

    @Test
    public void test_redirectToError403() throws Exception {

        User loggedInUser = mock(User.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getRequestURI()).thenReturn("welcome.do");
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(loggedInUser.getLogin()).thenReturn("testUsr");
        String error403url = "error/error403.do";
        RedirectViewWithLogging redirectViewWithLogging = new RedirectViewWithLogging(error403url, request, loggedInUser.getLogin());

        assertThat(redirectViewWithLogging.getUrl(), is("error/error403.do"));
    }


}
