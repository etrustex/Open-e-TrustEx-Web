package eu.europa.ec.etrustex.webaccess.business.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExternalAuthenticationService {

    private static final Logger logger = Logger.getLogger(ExternalAuthenticationService.class);

    protected final Map<ExternalToken, Long> authenticatedTokens = new ConcurrentHashMap<>();

    @Autowired
    private ExternalTokenBuilder externalTokenBuilder;

    @Autowired
    private HttpSession session;

    public void extendExternalTokenValidity() {
        ExternalToken requestToken = externalTokenBuilder.buildRequestToken();
        if (authenticatedTokens.containsKey(requestToken)) {
            authenticatedTokens.put(requestToken, System.currentTimeMillis() + (session.getMaxInactiveInterval() * 1000));
        }
    }

    public String registerExternalToken() {
        ExternalToken requestToken = externalTokenBuilder.buildRequestToken();

        String clientToken = null;

        try {
            clientToken = externalTokenBuilder.buildClientToken(requestToken);

            authenticatedTokens.put(requestToken, System.currentTimeMillis() + (session.getMaxInactiveInterval() * 1000));
        } catch (Exception e) {
            logger.error("Unable to register external token!", e);
        }
        return clientToken;
    }

    public void removeAuthenticatedSession(String sessionId) {
        for (Iterator<ExternalToken> iterator = authenticatedTokens.keySet().iterator(); iterator.hasNext(); ) {
            ExternalToken externalToken = iterator.next();
            if (externalToken.getBrowserSessionId().equals(sessionId)) {
                iterator.remove();
            }
        }
    }

    public ExternalToken validateToken(String connectionToken) {
        ExternalToken extractedToken = null;
        try {
            extractedToken = externalTokenBuilder.extractToken(connectionToken);
        } catch (Exception e) {
            logger.error("Unable to extract the token from connectionToken: " + connectionToken, e);
        }
        ExternalToken validatedToken = null;
        if (extractedToken != null) {
            if (authenticatedTokens.containsKey(extractedToken)) {
                if (authenticatedTokens.get(extractedToken) > System.currentTimeMillis()) {
                    validatedToken = extractedToken;
                }
            }
        }
        if (validatedToken == null) {
            logger.warn("Unable to validate the token: " + extractedToken);
        }
        return validatedToken;
    }
}
