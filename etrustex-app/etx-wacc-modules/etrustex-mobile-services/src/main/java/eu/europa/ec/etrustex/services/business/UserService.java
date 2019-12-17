package eu.europa.ec.etrustex.services.business;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Service layer for users.
 */

public interface UserService {

    User getUserDetails(String login);

    List<UserRole> getUserRoles(User user);

    Map<Party, String> loadBusinessUserConfigNameByAccessiblePartyForRoles(List<UserRole> partyLevelRoles);

    Map<Party, String> loadBusinessUserConfigNameByAccessibleBusinessForRoles(List<UserRole> businessLevelRoles);

}
