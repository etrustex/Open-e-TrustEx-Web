package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.util.ExternalAuthenticationService;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.mockito.Mockito.*;

public class UserAuthenticationInterceptorTest extends AbstractTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Mock
    private UserChecker userChecker;

    @Mock
    private UserSessionContext userSessionContext;

    @Mock
    private ExternalAuthenticationService externalAuthenticationService;

    @InjectMocks
    private UserAuthenticationInterceptor userAuthenticationInterceptor;

    protected void onSetUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setRequestURI("test.html");
    }

    @Test
    public void test_preHandle_should_returnTrue_when_userValid() throws Exception {

        String loginName = "login";
        HttpSession httpSession = mock(HttpSession.class);
        request.setSession(httpSession);
        Principal principal = mock(Principal.class);
        request.setUserPrincipal(principal);
        when(principal.getName()).thenReturn(loginName);

        // given
        when(userChecker.checkUser(loginName)).thenReturn(true);

        // when
        boolean result = userAuthenticationInterceptor.preHandle(request, response, new Object());

        // then
        assertThat(result, is(true));
        assertThat(request.getAttribute("javax.servlet.error.request_uri"), nullValue());
        assertThat(response.getForwardedUrl(), is(nullValue()));

        verify(httpSession, times(0)).invalidate();

        verify(externalAuthenticationService).registerExternalToken();
        verifyNoMoreInteractions(externalAuthenticationService);
    }

    @Test
    public void test_preHandle_should_returnTrue_when_userValidAlreadyAuthenticated() throws Exception {

        String loginName = "login";

        HttpSession httpSession = mock(HttpSession.class);
        request.setSession(httpSession);
        Principal principal = mock(Principal.class);
        request.setUserPrincipal(principal);
        when(principal.getName()).thenReturn(loginName);
        // given
        when(userChecker.checkUser(loginName)).thenReturn(true);
        when(userSessionContext.getUser()).thenReturn(new User());

        // when
        boolean result = userAuthenticationInterceptor.preHandle(request, response, new Object());

        // then
        assertThat(result, is(true));
        assertThat(request.getAttribute("javax.servlet.error.request_uri"), nullValue());
        assertThat(response.getForwardedUrl(), is(nullValue()));

        verify(httpSession, times(0)).invalidate();

        verify(externalAuthenticationService).extendExternalTokenValidity();
        verifyNoMoreInteractions(externalAuthenticationService);
    }


    @Test
    public void test_preHandle_should_returnFalse_when_userNotValid() throws Exception {
        // given
        String loginName = "login";
        when(userChecker.checkUser(loginName)).thenReturn(false);

        HttpSession httpSession = mock(HttpSession.class);
        request.setSession(httpSession);
        Principal principal = mock(Principal.class);
        request.setUserPrincipal(principal);
        when(principal.getName()).thenReturn(loginName);

        // when
        boolean result = userAuthenticationInterceptor.preHandle(request, response, new Object());

        // then
        assertThat(result, is(false));
        assertThat((String) request.getAttribute("javax.servlet.error.request_uri"), is("test.html"));
        assertThat(response.getForwardedUrl(), is(equalTo("/error/unknownLogin.do")));
        verify(httpSession).invalidate();

        verifyNoMoreInteractions(externalAuthenticationService);
    }

    @Test
    public void test_postHandle_should_setIsLogoutLinkEnabledToFalse_when_userNotLogged() throws Exception {
        // given
        when(userChecker.hasUser()).thenReturn(false);

        // when
        userAuthenticationInterceptor.postHandle(request, response, new Object(), new ModelAndView());

        // then
        assertThat((Boolean) request.getAttribute("isLogoutLinkEnabled"), is(false));

        verifyNoMoreInteractions(externalAuthenticationService);
    }

    @Test
    public void test_postHandle_should_setIsLogoutLinkEnabledToTrue_when_userNotLogged() throws Exception {
        // given
        when(userChecker.hasUser()).thenReturn(true);

        // when
        userAuthenticationInterceptor.postHandle(request, response, new Object(), new ModelAndView());

        // then
        assertThat((Boolean) request.getAttribute("isLogoutLinkEnabled"), is(true));

        verifyNoMoreInteractions(externalAuthenticationService);
    }
}
