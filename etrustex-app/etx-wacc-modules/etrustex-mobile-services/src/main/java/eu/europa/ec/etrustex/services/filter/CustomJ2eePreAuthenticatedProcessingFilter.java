package eu.europa.ec.etrustex.services.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.preauth.j2ee.J2eePreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class CustomJ2eePreAuthenticatedProcessingFilter extends J2eePreAuthenticatedProcessingFilter {
    private static final String AUTH_TYPE = "EtrustExFakeIdentity ";
    private static final String AUTH_HEADER_NAME = "Authorization";

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpRequest) {
        Object principal;
        String authHeader = httpRequest.getHeader(AUTH_HEADER_NAME);

        if(isValid(authHeader)){
            principal = extractUsernameFromHeader(authHeader);
        } else {
            principal = httpRequest.getUserPrincipal() == null ? null : httpRequest.getUserPrincipal().getName();
        }

        this.logger.debug("PreAuthenticated J2EE principal: " + principal);

        return principal;
    }

    private boolean isValid(String authHeader) {
        return StringUtils.isNoneBlank(authHeader) && authHeader.startsWith(AUTH_TYPE);
    }

    /*
     * Header format e.g. Authorization: EtrustExFakeIdentity ecasId#Full NAME
     */
    private String extractUsernameFromHeader(String header) {
        return header.substring(header.indexOf(" ") + 1, header.indexOf("#"));
    }
}
