package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;


public class HttpErrorControllerTest extends AbstractControllerTest {
    @InjectMocks
    private HttpErrorController httpErrorController = new HttpErrorController();

    @Test
    public void test_doError_should_addMandatoryObjectsToModel() throws Exception {
        URL wrongUrl = new URL("http://etrustex:80");
        request.setSession(session);
        request.setAttribute("javax.servlet.error.request_uri", wrongUrl);
        String exception = "EXCEPTION";
        request.setAttribute("javax.servlet.error.exception", exception);

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = httpErrorController.doError("errorTemplate", request);

        assertThat(modelAndView, is(notNullValue()));
        assertThat((URL) modelAndView.getModel().get("badUrl"), is(sameInstance(wrongUrl)));
        assertThat((String) modelAndView.getModel().get("exception"), is(exception));
    }
}
