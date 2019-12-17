package eu.europa.ec.etrustex.services.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private final static Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        logger.debug("==== Session is created ====");
        event.getSession().setMaxInactiveInterval(60*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        logger.debug("==== Session is destroyed ====");
    }
}
