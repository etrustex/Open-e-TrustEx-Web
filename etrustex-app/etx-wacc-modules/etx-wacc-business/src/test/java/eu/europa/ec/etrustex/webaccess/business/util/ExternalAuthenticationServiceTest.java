package eu.europa.ec.etrustex.webaccess.business.util;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class ExternalAuthenticationServiceTest extends AbstractTest {

    @Mock
    private ExternalTokenBuilder externalTokenBuilder;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ExternalAuthenticationService externalAuthenticationService = new ExternalAuthenticationService();

    @Test
    public void testExtendExternalTokenValidity() {
        ExternalToken requestToken = new ExternalToken("userName", "sessionId");
        when(externalTokenBuilder.buildRequestToken()).thenReturn(requestToken);

        when(session.getMaxInactiveInterval()).thenReturn(30);

        externalAuthenticationService.authenticatedTokens.put(requestToken, System.currentTimeMillis());

        //DO THE ACTUAL CALL
        externalAuthenticationService.extendExternalTokenValidity();

        assertThat(new Date(externalAuthenticationService.authenticatedTokens.get(requestToken)),
                is(closeTo(new Date(System.currentTimeMillis() + (30 * 1000)))));
    }

    @Test
    public void testExtendExternalTokenValidity_should_notDoAnything_when_tokenNotFound() {
        ExternalToken requestToken = new ExternalToken("userName", "sessionId");
        when(externalTokenBuilder.buildRequestToken()).thenReturn(requestToken);

        when(session.getMaxInactiveInterval()).thenReturn(30);

        //DO THE ACTUAL CALL
        externalAuthenticationService.extendExternalTokenValidity();

        assertThat(externalAuthenticationService.authenticatedTokens.containsKey(requestToken), is(false));
    }

    @Test
    public void testRegisterExternalToken() {

        ExternalToken requestToken = new ExternalToken("userName", "sessionId");
        when(externalTokenBuilder.buildRequestToken()).thenReturn(requestToken);

        String clientToken = "bla bla bla";
        when(externalTokenBuilder.buildClientToken(requestToken)).thenReturn(clientToken);

        when(session.getMaxInactiveInterval()).thenReturn(30);

        //DO THE ACTUAL CALL
        String actualClientToken = externalAuthenticationService.registerExternalToken();

        assertThat(actualClientToken, is(clientToken));
        assertThat(new Date(externalAuthenticationService.authenticatedTokens.get(requestToken)),
                is(closeTo(new Date(System.currentTimeMillis() + (30 * 1000)))));
    }

    @Test
    public void testRegisterExternalToken_should_notregisterToken_when_exceptionCreatingClientToken() {

        ExternalToken requestToken = new ExternalToken("userName", "sessionId");
        when(externalTokenBuilder.buildRequestToken()).thenReturn(requestToken);

        doThrow(new IllegalStateException()).when(externalTokenBuilder).buildClientToken(requestToken);

        when(session.getMaxInactiveInterval()).thenReturn(30);

        //DO THE ACTUAL CALL
        String actualClientToken = externalAuthenticationService.registerExternalToken();

        assertThat(actualClientToken, is(nullValue()));
        assertThat(externalAuthenticationService.authenticatedTokens.containsKey(requestToken), is(false));
    }

    @Test
    public void testRemoveAuthenticatedSession() {

        ExternalToken requestToken1 = new ExternalToken("userName", "sessionId1");
        ExternalToken requestToken2 = new ExternalToken("userName", "sessionId2");
        ExternalToken requestToken3 = new ExternalToken("userName", "sessionId3");

        externalAuthenticationService.authenticatedTokens.put(requestToken1, System.currentTimeMillis());
        externalAuthenticationService.authenticatedTokens.put(requestToken2, System.currentTimeMillis());
        externalAuthenticationService.authenticatedTokens.put(requestToken3, System.currentTimeMillis());

        //DO THE ACTUAL CALL
        externalAuthenticationService.removeAuthenticatedSession("sessionId2");

        assertThat(externalAuthenticationService.authenticatedTokens.size(), is(2));
        assertThat(externalAuthenticationService.authenticatedTokens.containsKey(requestToken1), is(true));
        assertThat(externalAuthenticationService.authenticatedTokens.containsKey(requestToken2), is(false));
        assertThat(externalAuthenticationService.authenticatedTokens.containsKey(requestToken3), is(true));
    }

    @Test
    public void testRemoveAuthenticatedSession_should_notRemoveAnything_when_noSessionFound() {

        ExternalToken requestToken1 = new ExternalToken("userName", "sessionId1");
        ExternalToken requestToken2 = new ExternalToken("userName", "sessionId2");
        ExternalToken requestToken3 = new ExternalToken("userName", "sessionId3");

        externalAuthenticationService.authenticatedTokens.put(requestToken1, System.currentTimeMillis());
        externalAuthenticationService.authenticatedTokens.put(requestToken2, System.currentTimeMillis());
        externalAuthenticationService.authenticatedTokens.put(requestToken3, System.currentTimeMillis());

        //DO THE ACTUAL CALL
        externalAuthenticationService.removeAuthenticatedSession("sessionId4");

        assertThat(externalAuthenticationService.authenticatedTokens.size(), is(3));
        assertThat(externalAuthenticationService.authenticatedTokens.containsKey(requestToken1), is(true));
        assertThat(externalAuthenticationService.authenticatedTokens.containsKey(requestToken2), is(true));
        assertThat(externalAuthenticationService.authenticatedTokens.containsKey(requestToken3), is(true));
    }

    @Test
    public void testValidateToken() {

        String clientToken = "bla bla bla";

        ExternalToken extractedToken = new ExternalToken("userName", "sessionId");
        when(externalTokenBuilder.extractToken(clientToken)).thenReturn(extractedToken);

        externalAuthenticationService.authenticatedTokens.put(extractedToken, System.currentTimeMillis() + 30000);

        //DO THE ACTUAL CALL
        ExternalToken actualToken = externalAuthenticationService.validateToken(clientToken);

        assertThat(actualToken, is(extractedToken));
    }

    @Test
    public void testValidateToken_should_fail_when_sessionExpired() {

        String clientToken = "bla bla bla";

        ExternalToken extractedToken = new ExternalToken("userName", "sessionId");
        when(externalTokenBuilder.extractToken(clientToken)).thenReturn(extractedToken);

        externalAuthenticationService.authenticatedTokens.put(extractedToken, System.currentTimeMillis() - 1000);

        //DO THE ACTUAL CALL
        ExternalToken actualToken = externalAuthenticationService.validateToken(clientToken);

        assertThat(actualToken, is(nullValue()));
    }

    @Test
    public void testValidateToken_should_fail_when_noSessionMatching() {

        String clientToken = "bla bla bla";

        ExternalToken extractedToken = new ExternalToken("userName", "sessionId");
        when(externalTokenBuilder.extractToken(clientToken)).thenReturn(extractedToken);

        externalAuthenticationService.authenticatedTokens.put(new ExternalToken("userName", "sessionId2"), System.currentTimeMillis() + 1000);

        //DO THE ACTUAL CALL
        ExternalToken actualToken = externalAuthenticationService.validateToken(clientToken);

        assertThat(actualToken, is(nullValue()));
    }

    @Test
    public void testValidateToken_should_fail_when_UnableToExtractToken() {

        String clientToken = "bla bla bla";
        doThrow(new IllegalStateException()).when(externalTokenBuilder).extractToken(clientToken);

        //DO THE ACTUAL CALL
        ExternalToken actualToken = externalAuthenticationService.validateToken(clientToken);

        assertThat(actualToken, is(nullValue()));
    }
}