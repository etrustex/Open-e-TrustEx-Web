package eu.europa.ec.etrustex.webaccess.web.rest;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.util.ExternalToken;
import eu.europa.ec.etrustex.webaccess.web.helper.ExternalTokenAuthenticationHelper;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test cases for the implementation of the token rest services.
 */

public class TokenRestServiceImplTest extends AbstractTest {

    @Mock
    private ExternalTokenAuthenticationHelper externalTokenAuthenticationHelper;

    @InjectMocks
    private TokenRestServiceImpl restController;

    private static final String TOKEN = "connection_token";
    private static final String USER_ID = "userId";

    /**
     * Tests validateToken, expecting a successful validation of the token and the user id.
     */

//    @Test
//    public void testValidateToken_UserId_Success() {
//        ExternalToken externalToken = mock(ExternalToken.class);
//        when(externalToken.getUserName()).thenReturn(USER_ID);
//
//        when(externalTokenAuthenticationHelper.verifyToken(TOKEN)).thenReturn(externalToken);
//
//        final Response response = restController.validateToken(TOKEN, USER_ID);
//
//        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
//    }
//
//    /**
//     * Tests validateToken, expecting a successful validation of the token but an unauthorized validation of the user id.
//     */
//
//    @Test
//    public void testValidateToken_Success_UserId_Unauthorized() {
//        ExternalToken externalToken = mock(ExternalToken.class);
//        when(externalToken.getUserName()).thenReturn("otherUser");
//
//        when(externalTokenAuthenticationHelper.verifyToken(TOKEN)).thenReturn(externalToken);
//
//        final Response response = restController.validateToken(TOKEN, USER_ID);
//
//        assertThat(response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
//    }
//
//    /**
//     * Test validateToken, expecting an unauthorized validation of the token.
//     */
//
//    @Test
//    public void testValidateToken_Unauthorized() {
//        when(externalTokenAuthenticationHelper.verifyToken(TOKEN)).thenReturn(null);
//
//        final Response response = restController.validateToken(TOKEN, USER_ID);
//
//        assertThat(response.getStatus(), is(Response.Status.UNAUTHORIZED.getStatusCode()));
//    }

}
