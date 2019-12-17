package eu.europa.ec.etrustex.webaccess.business.api;


import eu.europa.ec.etrustex.webaccess.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author apladap
 */
public interface UserManager {
    /**
     * Returns the user with the given login name
     *
     * @param id the id by which the user is searched
     * @return the user with the given id or null if this does not exist
     */
    User getUserById(Long id);

    /**
     * Returns the user with the given login name
     *
     * @param login the login by which the user is searched
     * @return the user with the given login or null if this does not exist
     */
    User getUserDetails(String login);

    /**
     * Searches the user roles associated with the give user
     *
     * @param user a valid user
     * @return the list of user roles associated with this user or an empty list if none is found
     */
    List<UserRole> getUserRoles(User user);

    /**
     * Searches a business user config directly mapped to the input user and business
     *
     * @param user     the user for which a user config is loaded
     * @param business the business for which the user config is loaded
     * @return the business user config directly linked to the input user and business or null if none is found
     */
    BusinessUserConfig getBusinessUserConfig(User user, Business business);

    /**
     * Searches for business user configs of users linked to the input party and with granted privilege
     *
     * @param party     the user for which a user config is loaded
     * @param privilegeType the business for which the user config is loaded
     * @return the business user configs linked to the input party and with granted privilege
     * or empty set if none is found
     */
    Set<BusinessUserConfig> getBusinessUserConfigs(Party party, Privilege.Type privilegeType);

    /**
     * Searches and returns all business user configs that exist for a party in the scope of a business and multiple users
     *
     * @param party    the party for which business user configs are searched
     * @param roleType the role types for which business user configs are searched
     * @return all accessible user roles with business user configs or an empty list if none are found
     */
    Map<UserRole, BusinessUserConfig> getUserRoles(Party party, Role.Type roleType);

    /**
     * Searches and returns all business user configs that exist for a a business and multiple users
     *
     * @param business the business for which business user configs are searched
     * @param roleType the role types for which business user configs are searched
     * @return all accessible user roles with business user configs or an empty list if none are found
     */
    Map<UserRole, BusinessUserConfig> getUserRoles(Business business, Role.Type roleType);

    /**
     * Searches and returns all users that exist for a role type
     *
     * @param roleType the role types for which users are searched
     * @return all accessible user roles with users or an empty list if none are found
     */
    Map<UserRole, User> getUserRoles(Role.Type roleType);

    Role getRole(Role.Type roleType);

    User saveOrUpdate(User user);

    BusinessUserConfig saveOrUpdate(BusinessUserConfig businessUserConfig);

    UserRole saveOrUpdate(UserRole userRole);

    void delete(UserRole userRole);

    UserRole getUserRole(Long userRoleId);

    Map<Party, String> loadBusinessUserConfigNameByAccessiblePartyForRoles(List<UserRole> partyLevelRoles);

    Map<Party, String> loadBusinessUserConfigNameByAccessibleBusinessForRoles(List<UserRole> businessLevelRoles);

    /**
     * Finds if this party has roles for which the notifications are enabled
     *
     * @param party The party
     * @param login Login name
     * @return <code>true</code> if there are, <code>false</code> otherwise
     */
    boolean partyHasRolesWithNotificationsEnabled(Party party, String login);

    /**
     * Finds if there are other roles in the party of this role with notifications enabled.
     *
     * @param userRole The role
     * @return <code>true</code> if there are, <code>false</code> otherwise
     */
    boolean otherPartyUserRolesWithNotificationsEnabledThan(UserRole userRole);

    boolean isNotificationAllowed(BusinessUserConfig businessUserConfig, Notification.NotificationType notificationType);
}
