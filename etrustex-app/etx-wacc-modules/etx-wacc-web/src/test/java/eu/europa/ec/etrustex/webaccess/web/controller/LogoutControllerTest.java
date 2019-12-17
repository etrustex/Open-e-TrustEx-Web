package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LogoutControllerTest extends AbstractControllerTest {
    @Mock
    HttpSession httpSession;

    @Mock
    UserSessionContext userSessionContext;

    @InjectMocks
    private LogoutController logoutController = new LogoutController();

    @Test
    public void test_doGet_should_invalidateSessionAndReturnCorrectModelAndView() throws Exception {

        User user = new User();
        user.setLogin("login");

        when(userSessionContext.getUser()).thenReturn(user);

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = logoutController.doGet(httpSession);

        assertThat(modelAndView, is(notNullValue()));
        assertThat(modelAndView.getViewName(), is(equalTo("logout")));

        verify(httpSession).invalidate();
    }
}
