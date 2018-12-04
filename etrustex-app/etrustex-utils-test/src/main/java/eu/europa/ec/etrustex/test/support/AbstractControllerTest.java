/**
 *
 */
package eu.europa.ec.etrustex.test.support;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

/**
 * @author apladap
 */
public class AbstractControllerTest extends AbstractTest {

    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected MockHttpSession session;

    @Override
    protected void onSetUp() {
        super.onSetUp();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
    }

}
