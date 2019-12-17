package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.Privilege;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserChecker {
    private static final Logger logger = Logger.getLogger(UserChecker.class);

    @Autowired
    @Qualifier("userSessionContext")
    private UserSessionContext userSessionContext;

    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleUtils roleUtils;

    @Autowired
    private PartyManager partyManager;

    public boolean checkUser(String login) {
        if (userSessionContext.getUser() == null) {
            if (login == null) {
                logger.fatal("The user principal name (request.getUserPrincipal().getName()) is null!");
                logger.fatal("The authentication doesn't work correctly.");
                logger.fatal("--> Please check!");
                return false;
            }
            logger.info("Using ECAS user name: " + login);
            User user = userManager.getUserDetails(login);

            if (user == null) {
                logger.warn("The user " + login + " is not a valid user");
                return false;
            }
            userSessionContext.setUser(user);

            List<UserRole> userRoles = userManager.getUserRoles(user);
            if (userRoles == null || userRoles.isEmpty()) {
                logger.warn("The user " + login + " has no roles defined for this application!");
                return false;
            }
            userSessionContext.setUserRoleList(userRoles);

            setupUserContext(user, userRoles);

            logger.info("User name: " + login + " validation completed.");
        }
        return true;
    }

    private void setupUserContext(User user, List<UserRole> userRoles) {
        boolean hasAccessToAllParties = false;
        List<UserRole> partyLevelRoles = new ArrayList<>();
        List<UserRole> businessLevelRoles = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            if (roleUtils.roleHasPrivilege(userRole.getRole(), Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)) {
                if (userRole.getParty() != null) {
                    partyLevelRoles.add(userRole);
                } else if (userRole.getBusiness() != null) {
                    businessLevelRoles.add(userRole);
                } else {
                    hasAccessToAllParties = true;
                }
            }
        }
        Map<Party, String> businessUserNameByParty = new HashMap<>();
        if (hasAccessToAllParties) {
            List<Party> allParties = partyManager.getAllParties();
            for (Party party : allParties) {
                //for system admin, use the username all the time
                businessUserNameByParty.put(party, user.getName());
            }
        } else {
            businessUserNameByParty.putAll(userManager.loadBusinessUserConfigNameByAccessiblePartyForRoles(partyLevelRoles));
            businessUserNameByParty.putAll(userManager.loadBusinessUserConfigNameByAccessibleBusinessForRoles(businessLevelRoles));
        }
        List<Party> sortedMessageParties = new ArrayList<>(businessUserNameByParty.keySet());
        Collections.sort(sortedMessageParties, new PartyComparator());


        userSessionContext.setMessageParties(sortedMessageParties);

        Map<Long, String> businessUserNameByPartyId = new HashMap<>();
        for (Map.Entry<Party, String> entry : businessUserNameByParty.entrySet()) {
            businessUserNameByPartyId.put(entry.getKey().getId(), entry.getValue());
        }
        userSessionContext.setBusinessUserNameByParty(businessUserNameByPartyId);
    }

    public boolean hasUser() {
        return userSessionContext.getUser() != null;
    }
}
