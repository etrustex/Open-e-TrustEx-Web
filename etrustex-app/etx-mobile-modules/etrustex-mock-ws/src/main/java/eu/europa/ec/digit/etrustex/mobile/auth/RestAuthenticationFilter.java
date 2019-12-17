package eu.europa.ec.digit.etrustex.mobile.auth;

import eu.cec.digit.ecas.client.jaas.DetailedUser;
import eu.europa.ec.digit.etrustex.mobile.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter responsible for ensuring that access to configured resources is done when user is either ECAS authentication or using a fake
 */
public class RestAuthenticationFilter implements Filter {

    public static final String ALLOW_FAKE_IDENTITY_PARAM = "allowFakeIdentity";

    private static final String AUTH_TYPE = "EtrustExFakeIdentity ";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean allowFakeIdentity;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String param = filterConfig.getInitParameter(ALLOW_FAKE_IDENTITY_PARAM);
        this.allowFakeIdentity = Boolean.valueOf(param);
        logger.info("Init param allowFakeIdentity set to:" + this.allowFakeIdentity);
    }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("RestAuthenticationFilter just supports HTTP requests");
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        doFilterInternal(httpRequest, httpResponse, filterChain);
    }

    @Override
    public void destroy() {
    }

    private void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean userIsAuthenticated = false;
        logger.debug("Enable fake identity " + allowFakeIdentity); //DEBUG
        if (allowFakeIdentity) {
            // Check for a header like
            //      Authorization: EtrustExFakeIdentity ecasId#Full NAME
            String authHeader = request.getHeader("Authorization");
            if (null != authHeader && authHeader.startsWith(AUTH_TYPE)) {
                String fakeIdentity = authHeader.substring(AUTH_TYPE.length());
                if (StringUtils.hasText(fakeIdentity)) {
                    logger.debug("Enable fake identity '{}' for request: {}", fakeIdentity, request.getRequestURI());

                    HttpSession session = request.getSession();
                    if (null != session) {
                        User user = fromFakeIdentity(fakeIdentity);

                        request.setAttribute("user", user);

                        userIsAuthenticated = true;
                    }
                }
            }
        }

        if (!userIsAuthenticated && null != request.getUserPrincipal()) {
            DetailedUser ecasUser = (DetailedUser)request.getUserPrincipal();
            User user = new User();
            user.setFullName(ecasUser.getFirstName() + " " + ecasUser.getLastName());
            user.setUserId(ecasUser.getUid());

            request.setAttribute("user", user);

            userIsAuthenticated = true;
        }

        if (!userIsAuthenticated) {
            logger.warn("User is not authenticated to perform HTTP call {}", getMethodAndRequestURL(request));
            throw new ServletException("User is not authenticated to perform call!");
        }

        filterChain.doFilter(request, response);
    }

    private User fromFakeIdentity(String fakeIdentity) {
        User user = new User();
        String[] items = fakeIdentity.split("#");
        if (items.length > 1) {
            user.setUserId(items[0]);
            user.setFullName(items[1]);
        } else {
            user.setUserId(items[0]);
            user.setFullName(items[0]);
        }
        return user;
    }

    private String getMethodAndRequestURL(ServletRequest request) {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            return httpRequest.getMethod() + "/ " + getRequestURL(httpRequest);
        } catch (Exception e) {
            // Unexpected
            logger.warn("Could not get HTTP method and request URL from request: {}", request, e);
            return "<unknown>";
        }
    }

    private String getRequestURL(HttpServletRequest httpRequest) {
        String requestURL = httpRequest.getRequestURL().toString();
        String queryString = httpRequest.getQueryString();
        if (StringUtils.hasText(queryString)) {
            requestURL = requestURL + "?" + queryString;
        }
        return requestURL;
    }

}
