package eu.europa.ec.etrustex.webaccess.persistence.support;

import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.persistence.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class UserAccessor {

    private static final Logger logger = Logger.getLogger(UserAccessor.class.getName());

    public static final String SYSTEM_USER_LOGIN = "system";

    @Autowired
    private UserSessionContext userSessionContext;

    @Autowired
    private UserDAO userDAO;

    private static UserAccessor userAccessor;
    private static User systemUser;

    @PostConstruct
    protected void init() {
        userAccessor = this;
        systemUser = userDAO.getUser(SYSTEM_USER_LOGIN);
    }

    public static User getUser() {
        User user = null;
        try {
            if (userAccessor.userSessionContext.getUser() != null) {
                user = userAccessor.userSessionContext.getUser();
            }
        } catch (Exception e) {
            logger.warn("Unable to fetch the current logged in user: " + e);
        }
        if (user == null) {
            user = systemUser;
        }
        return user;
    }
}
