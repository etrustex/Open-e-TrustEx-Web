package eu.europa.ec.etrustex.webaccess.web.rest;

import eu.europa.ec.etrustex.webaccess.business.util.ExternalToken;
import eu.europa.ec.etrustex.webaccess.rest.TokenRestService;
import eu.europa.ec.etrustex.webaccess.web.helper.ExternalTokenAuthenticationHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

/**
 * REST service implementation to perform the authentication.
 */

@Service("tokenService")
public class TokenRestServiceImpl implements TokenRestService {

    private static final Logger logger = Logger.getLogger(TokenRestServiceImpl.class);

    @Autowired
    private ExternalTokenAuthenticationHelper externalTokenAuthenticationHelper;

    @Override
    public Response validateToken(String token, String userId) {
        ExternalToken externalToken = externalTokenAuthenticationHelper.verifyToken(token);
        Response response;

        if (externalToken != null && externalToken.getUserName().equals(userId)) {
            response = Response.status(Response.Status.OK).build();
        } else {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return response;
    }
}
