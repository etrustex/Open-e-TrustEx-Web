package eu.europa.ec.etrustex.services.security;

import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.services.exception.RestAuthenticationException;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.LinkedList;
import java.util.Set;


@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceCustom.class);

    @Autowired
    private UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        try {

            User user = userService.getUserDetails(username);

            if (user == null) {
                throw new UsernameNotFoundException("You are not a registered user. Please contact the support team at DIGIT-CIPA-SUPPORT@ec.europa.eu in order to request access rights.");
            }

            UserContext userContext = new UserContext(username);

            if (existsClass("weblogic.security.Security")) {
                Subject subject = (Subject) Class.forName("weblogic.security.Security").getMethod("getCurrentSubject", null).invoke(null, null);
                Set<Principal> principals = subject.getPrincipals();
                for (Principal principal : principals) {
//                    if (Class.forName("weblogic.security.spi.WLSGroup").isInstance(principal)) {
                        userContext.setRoleList(new LinkedList<UserRole>());
                        for (UserRole userRole : user.getUserRoles()) {
                            Hibernate.initialize(userRole.getRole());
                            userContext.getRoleList().add(userRole);
                        }

                        logger.info("---------------------------------------REGISTERED ROLE : " + principal.getName());
//                    }
                }
            }

            return userContext;

        } catch (Exception ex) {
            if (ex instanceof UsernameNotFoundException) {
                throw (UsernameNotFoundException) ex;
            } else {
                throw new RestAuthenticationException("Cannot retrieve the userDetails. An error occurred. Please retry later or contact the support team at DIGIT-CIPA-SUPPORT@ec.europa.eu providing all relevant details", ex);
            }
        }
    }

    public boolean existsClass(String claszzName) {
        boolean exists = true;
        try {
            Class.forName(claszzName, false, getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            exists = false;
        }
        return exists;
    }
}
