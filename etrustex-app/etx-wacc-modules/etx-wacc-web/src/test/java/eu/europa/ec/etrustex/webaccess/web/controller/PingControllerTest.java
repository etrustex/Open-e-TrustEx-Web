/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.business.util.ExternalAuthenticationService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author apladap
 *
 */
public class PingControllerTest extends AbstractControllerTest{

	@Mock
	private ExternalAuthenticationService externalAuthenticationService;

	@InjectMocks
	private PingController pingController = new PingController();

	@Test	
	public void testPing() throws IOException{
		String result = pingController.ping(request, response);
		assertThat(result, is("isAlive"));

		verify(externalAuthenticationService).extendExternalTokenValidity();
		verifyNoMoreInteractions(externalAuthenticationService);
	}

}
