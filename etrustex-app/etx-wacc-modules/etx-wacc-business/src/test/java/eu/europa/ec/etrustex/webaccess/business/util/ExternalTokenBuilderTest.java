package eu.europa.ec.etrustex.webaccess.business.util;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.mockito.InjectMocks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.mockito.Mockito.*;

public class ExternalTokenBuilderTest extends AbstractTest {

    private HttpServletRequest request;
    private HttpSession session;

    @InjectMocks
    private ExternalTokenBuilder externalTokenBuilder = new ExternalTokenBuilder();

    @Override
    public void setup() {
        EtxSecurityProvider.init();
        request = mock(HttpServletRequest.class);
        externalTokenBuilder.request = request;
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testBuildRequestToken() {
        String userName = "me_user";
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn(userName);
        when(request.getUserPrincipal()).thenReturn(principal);

        String sessionId = "session id";
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getId()).thenReturn(sessionId);
        when(request.getSession()).thenReturn(httpSession);

        //DO THE ACTUAL CALL
        ExternalToken externalToken = externalTokenBuilder.buildRequestToken();

        ExternalToken expectedToken = new ExternalToken(userName, sessionId);
        assertThat(externalToken, is(expectedToken));

        verify(request).getUserPrincipal();
        verify(request).getSession();
        verifyNoMoreInteractions(request);

        verify(httpSession).getId();
        verifyNoMoreInteractions(httpSession);

        verify(principal).getName();
        verifyNoMoreInteractions(principal);
    }

    @Test
    public void testBuildClientToken() throws Exception {
        String sessionId = "a session id";
        when(session.getId()).thenReturn(sessionId);

        String userName = "me_user";
        ExternalToken externalToken = new ExternalToken(userName, sessionId);

        //DO THE ACTUAL CALL
        String result = externalTokenBuilder.buildClientToken(externalToken);

        String expectedValue = Hex.encodeHexString(externalToken.toToken());
        assertThat(result, is(expectedValue));
    }

    @Test
    public void testExtractToken() {

        String token = "6d655f757365723a612073657373696f6e206964";

        //DO THE ACTUAL CALL
        ExternalToken actualToken = externalTokenBuilder.extractToken(token);

        ExternalToken expectedToken = new ExternalToken("me_user", "a session id");

        assertThat(actualToken, is(expectedToken));
    }

    @Test(expected = EtxException.class)
    public void testExtractTokenWhenIncorrectToken() {
        String remoteAddress = "remote addr";
        String remoteHost = "remote host";
        when(request.getRemoteAddr()).thenReturn(remoteAddress);
        when(request.getRemoteHost()).thenReturn(remoteHost);

        String token = "b88c1d9bdf2daee4edf05533832de6927bb05739769c6197237d836619f67972";

        //DO THE ACTUAL CALL
        externalTokenBuilder.extractToken(token);
    }
}