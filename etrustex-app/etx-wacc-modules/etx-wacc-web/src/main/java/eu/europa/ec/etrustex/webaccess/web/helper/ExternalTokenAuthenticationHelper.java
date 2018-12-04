package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.business.util.ExternalAuthenticationService;
import eu.europa.ec.etrustex.webaccess.business.util.ExternalToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Helper which allows to validate the external authentication token.
 */

@Component
public class ExternalTokenAuthenticationHelper {

    private final Logger logger = Logger.getLogger(ExternalTokenAuthenticationHelper.class);

    private static final String WWW_AUTHENTICATE_HEADER = "WWW-Authenticate";

    @Autowired
    private ExternalAuthenticationService externalAuthenticationService;

    @Autowired
    private UserChecker userChecker;

    /**
     * Verifies connection token.
     *
     * @param httpResponse    HTTP response.
     * @param connectionToken Connection token.
     * @return True if the token was successfully validated, false otherwise.
     */

    public boolean verifyToken(HttpServletResponse httpResponse, String connectionToken) {
        ExternalToken externalToken = externalAuthenticationService.validateToken(connectionToken);

        if (externalToken == null || !userChecker.checkUser(externalToken.getUserName())) {
            httpResponse.setHeader(WWW_AUTHENTICATE_HEADER, "Basic realm=\"myRealm\"");
            try {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (IOException e) {
                logger.warn("Exception encountered while sending the unauthorized error!", e);
            }
            return false;
        }
        return true;
    }

    /**
     * Verifies connection token.
     *
     * @param connectionToken Connection token.
     * @return External token.
     */

    public ExternalToken verifyToken(String connectionToken) {
        ExternalToken externalToken = externalAuthenticationService.validateToken(connectionToken);

        if (externalToken != null) {
            logger.info("Verified token for user name: " + externalToken.getUserName() + " and browser id: " + externalToken.getBrowserSessionId());
        }

        return externalToken;
    }
}
