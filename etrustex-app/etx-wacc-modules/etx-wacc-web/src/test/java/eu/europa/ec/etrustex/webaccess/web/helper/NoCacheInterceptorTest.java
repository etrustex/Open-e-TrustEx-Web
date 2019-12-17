package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import org.junit.Test;
import org.mockito.InjectMocks;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NoCacheInterceptorTest extends AbstractTest{

    @InjectMocks
    private NoCacheInterceptor interceptor;

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel() throws Exception {
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);

        // DO THE ACTUAL CALL
        interceptor.preHandle(null, mockResponse, null);

        verify(mockResponse).setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        verify(mockResponse).setHeader("Pragma", "no-cache");
        verify(mockResponse).setDateHeader("Expires", 0);
    }
}
