package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.business.util.ExternalAuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicReference;

@WebListener("Session listener for the application")
public class TraceSessionListener implements HttpSessionListener {

    private static final Logger logger = Logger.getLogger(TraceSessionListener.class);

    protected final AtomicReference<ExternalAuthenticationService> authenticationServiceDelegate = new AtomicReference<>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("Session created - " + httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("Session destroyed - " + httpSessionEvent.getSession().getId());
        getAuthenticationService(httpSessionEvent.getSession()).removeAuthenticatedSession(httpSessionEvent.getSession().getId());
    }

    protected ExternalAuthenticationService getAuthenticationService(HttpSession httpSession) {
        ExternalAuthenticationService existingValue = authenticationServiceDelegate.get();
        if (existingValue == null) {
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(httpSession.getServletContext());
            ExternalAuthenticationService externalAuthenticationService = webApplicationContext.getBean(ExternalAuthenticationService.class);

            authenticationServiceDelegate.compareAndSet(null, externalAuthenticationService);
        }

        return authenticationServiceDelegate.get();
    }
}
