package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.model.Privilege;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WelcomeControllerTest extends AbstractTest {
    @InjectMocks
    private WelcomeController controller;

    @Mock
    private SecurityChecker securityChecker;

    @Mock
    private UserSessionContext userSessionContext;

    @Mock
    private HttpServletRequest mockRequest;

    @Test
    public void test_doGet_should_403_when_noPrivileges() throws Exception {
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(false);
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(false);

        User loggedInUser = mock(User.class);

        when(mockRequest.getRequestURI()).thenReturn("/e-trustex/welcome.do");
        when(mockRequest.getQueryString()).thenReturn("test=10");
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(loggedInUser.getLogin()).thenReturn("testUsr");

        // DO THE ACTUAL CALL
        ModelAndView result = controller.doGet(mockRequest);

        assertThat(((RedirectViewWithLogging) result.getView()).getUrl(), is(equalTo("error/error403.do")));
    }

    @Test
    public void test_doGet_should_goInbox_when_CAN_ACCESS_OWN_PARTY_MESSAGES() throws Exception {
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(true);
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(true);

        // DO THE ACTUAL CALL
        ModelAndView result = controller.doGet(mockRequest);

        assertThat(((RedirectView) result.getView()).getUrl(), is(equalTo("inbox.do")));
    }

    @Test
    public void test_doGet_should_goAdmin_when_CAN_ASSIGN_PARTY_SCOPE_ROLES() throws Exception {
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(false);
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(true);

        // DO THE ACTUAL CALL
        ModelAndView result = controller.doGet(mockRequest);

        assertThat(((RedirectView) result.getView()).getUrl(), is(equalTo("admin.do")));
    }

}
