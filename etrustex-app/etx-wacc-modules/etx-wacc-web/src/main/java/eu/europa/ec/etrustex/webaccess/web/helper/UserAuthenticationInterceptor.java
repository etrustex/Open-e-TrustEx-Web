package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.business.util.ExternalAuthenticationService;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Component("userAuthenticationInterceptor")
public class UserAuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(UserAuthenticationInterceptor.class);

    private static final String ERROR_FORWARD_URL = "/error/unknownLogin.do";

    @Autowired
    private UserChecker userChecker;

    @Autowired
    @Qualifier("userSessionContext")
    private UserSessionContext userSessionContext;

    @Autowired
    private ExternalAuthenticationService externalAuthenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isNewSession = userSessionContext.getUser() == null;

        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            logger.fatal("The user principal (request.getUserPrincipal()) is null!");
            logger.fatal("The authentication doesn't work correctly.");
            logger.fatal("--> Please check if the application enforces the user to log in!");
            return false;
        }

        boolean validUser = userChecker.checkUser(principal.getName());
        if (validUser) {
            if (isNewSession) {
                externalAuthenticationService.registerExternalToken();
            } else {
                externalAuthenticationService.extendExternalTokenValidity();
            }
        } else {
            request.setAttribute("javax.servlet.error.request_uri", request.getRequestURI());
            request.getRequestDispatcher(ERROR_FORWARD_URL).forward(request, response);

            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        return validUser;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("isLogoutLinkEnabled", userChecker.hasUser());
    }

}
