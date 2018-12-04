package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.BusinessUserConfigDAO;
import eu.europa.ec.etrustex.webaccess.persistence.RoleDAO;
import eu.europa.ec.etrustex.webaccess.persistence.UserDAO;
import eu.europa.ec.etrustex.webaccess.persistence.UserRoleDAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserManagerImpl implements UserManager {

    private static final Logger logger = Logger.getLogger(UserManagerImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BusinessUserConfigDAO businessUserConfigDAO;

    @Override
    public User getUserById(Long id) {
        return userDAO.findById(id);
    }

    public User getUserDetails(String login) {
        return userDAO.getUser(login);
    }

    public List<UserRole> getUserRoles(User user) {
        return new ArrayList<>(new HashSet<>(userRoleDAO.getUserRoles(user)));
    }

    @Override
    public BusinessUserConfig getBusinessUserConfig(User user, Business business) {
        return businessUserConfigDAO.getBusinessUserConfig(user, business);
    }

    @Override
    public Set<BusinessUserConfig> getBusinessUserConfigs(Party party, Privilege.Type privilegeType) {
        logger.debug("Start getting business user configurations for party [" + party.getId() + "] of type [" + privilegeType.toString() + "]");
        Set<BusinessUserConfig> businessUserConfigs = new HashSet<>();
        businessUserConfigs.addAll(businessUserConfigDAO.getBusinessUserConfigs(party, privilegeType));
        businessUserConfigs.addAll(businessUserConfigDAO.getBusinessUserConfigs(party.getBusiness(), privilegeType));
        businessUserConfigs.addAll(businessUserConfigDAO.getSystemScopeBusinessUserConfigs(party.getBusiness(), privilegeType));
        logger.debug("Got " + businessUserConfigs.size() + " business user configurations for party [" + party.getId() + "] of type [" + privilegeType.toString() + "]");
        return businessUserConfigs;
    }

    @Override
    public Map<UserRole, BusinessUserConfig> getUserRoles(Party party, Role.Type roleType) {

        logger.info("Start getting user roles for party [" + party.getId() + "] of type [" + roleType.toString() + "]");
        Map<UserRole, BusinessUserConfig> userRoles = userRoleDAO.getUserRoles(party, roleType);
        logger.info("Got " + userRoles.size() + " user roles for party [" + party.getId() + "] of type [" + roleType.toString() + "]");
        return userRoles;
    }

    @Override
    public Map<UserRole, BusinessUserConfig> getUserRoles(Business business, Role.Type roleType) {

        logger.info("Start getting user roles for business [" + business.getName() + "] of type [" + roleType.toString() + "]");
        Map<UserRole, BusinessUserConfig> userRoles = userRoleDAO.getUserRoles(business, roleType);
        logger.info("Got " + userRoles.size() + " user roles for business [" + business.getName() + "] of type [" + roleType.toString() + "]");
        return userRoles;
    }

    @Override
    public Map<UserRole, User> getUserRoles(Role.Type roleType) {
        logger.info("Start getting user roles of type [" + roleType.toString() + "]");
        Map<UserRole, User> userRoles = userRoleDAO.getUserRoles(roleType);
        logger.info("Got " + userRoles.size() + " user roles of type [" + roleType.toString() + "]");
        return userRoles;
    }

    @Override
    public Role getRole(Role.Type roleType) {
        return roleDAO.getRole(roleType);
    }

    @Override
    @Transactional(readOnly = false)
    public User saveOrUpdate(User user) {
        logger.info("Start saving user [" + user.toString() + "]");
        if (user.getId() != null) {
            userDAO.update(user);
        } else {
            userDAO.save(user);
        }
        logger.info("Save user [" + user.toString() + "] completed");
        return user;
    }

    @Override
    @Transactional(readOnly = false)
    public BusinessUserConfig saveOrUpdate(BusinessUserConfig businessUserConfig) {
        logger.info("Start saving business user config [" + businessUserConfig.toString() + "]");
        if (businessUserConfig.getId() != null) {
            businessUserConfigDAO.update(businessUserConfig);
        } else {
            businessUserConfigDAO.save(businessUserConfig);
        }
        logger.info("Save business user config [" + businessUserConfig.toString() + "] completed");
        return businessUserConfig;
    }

    @Override
    @Transactional(readOnly = false)
    public UserRole saveOrUpdate(UserRole userRole) {
        logger.info("Start saving user role [" + userRole.toString() + "]");
        if (userRole.getId() != null) {
            userRoleDAO.update(userRole);
        } else {
            userRoleDAO.save(userRole);
        }
        logger.info("Save user role [" + userRole.toString() + "] completed");
        return userRole;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(UserRole userRole) {
        logger.info("Start deleting user role [" + userRole.toString() + "]");
        userRole.setActiveState(false);
        saveOrUpdate(userRole);
        logger.info("Delete user role [" + userRole.toString() + "] completed");
    }

    @Override
    public UserRole getUserRole(Long userRoleId) {
        return userRoleDAO.getUserRole(userRoleId);
    }

    @Override
    public Map<Party, String> loadBusinessUserConfigNameByAccessiblePartyForRoles(List<UserRole> partyLevelRoles) {
        return userRoleDAO.loadBusinessUserConfigNameByAccessiblePartyForRoles(partyLevelRoles);
    }

    @Override
    public Map<Party, String> loadBusinessUserConfigNameByAccessibleBusinessForRoles(List<UserRole> businessLevelRoles) {
        return userRoleDAO.loadBusinessUserConfigNameByAccessibleBusinessForRoles(businessLevelRoles);
    }

    @Override
    public boolean partyHasRolesWithNotificationsEnabled(Party party, String login) {
        List<BusinessUserConfig> businessUserConfigSet = new ArrayList<>();
        businessUserConfigSet.addAll(getUserRoles(party, Role.Type.PARTY_ADMIN).values());
        for (BusinessUserConfig businessUserConfig : businessUserConfigSet) {
            if (businessUserConfig.getNotificationsEnabled() && !businessUserConfig.getUser().getLogin().equals(login)) {
                logger.info("partyHasRolesWithNotificationsEnabled: true, party id: " + party.getId());
                return true;
            }
        }
        businessUserConfigSet.addAll(getUserRoles(party, Role.Type.OPERATOR).values());
        for (BusinessUserConfig businessUserConfig : businessUserConfigSet) {
            if (businessUserConfig.getNotificationsEnabled() && !businessUserConfig.getUser().getLogin().equals(login)) {
                logger.info("partyHasRolesWithNotificationsEnabled: true, party id: " + party.getId());
                return true;
            }
        }
        logger.warn("partyHasRolesWithNotificationsEnabled: false, party id: " + party.getId());
        return false;
    }

    @Override
    public boolean otherPartyUserRolesWithNotificationsEnabledThan(UserRole userRole) {
        String login = null;
        Set<String> logins = new HashSet<>();
        Party party = userRole.getParty();

        Map<UserRole, BusinessUserConfig> userRoleBusinessUserConfigMap = getUserRoles(party, Role.Type.PARTY_ADMIN);
        for (Map.Entry<UserRole, BusinessUserConfig> entry : userRoleBusinessUserConfigMap.entrySet()) {
            UserRole ur = entry.getKey();
            BusinessUserConfig buc = entry.getValue();
            String tmpLogin = buc.getUser().getLogin();
            if (ur.getId().equals(userRole.getId())) {
                login = tmpLogin;
            }
            if (buc.getNotificationsEnabled()) {
                if (logins.add(tmpLogin) && logins.size() > 1) {
                    logger.info("otherPartyUserRolesWithNotificationsEnabledThan: true, user role id: " + userRole.getId());
                    return true;
                }
            }
        }

        userRoleBusinessUserConfigMap = getUserRoles(party, Role.Type.OPERATOR);
        for (Map.Entry<UserRole, BusinessUserConfig> entry : userRoleBusinessUserConfigMap.entrySet()) {
            UserRole ur = entry.getKey();
            BusinessUserConfig buc = entry.getValue();
            String tmpLogin = buc.getUser().getLogin();
            if (ur.getId().equals(userRole.getId())) {
                login = tmpLogin;
            }
            if (buc.getNotificationsEnabled()) {
                if (logins.add(tmpLogin) && logins.size() > 1) {
                    logger.info("otherPartyUserRolesWithNotificationsEnabledThan: true, user role id: " + userRole.getId());
                    return true;
                }
            }
        }

        if (logins.isEmpty() || (logins.size() == 1 && logins.contains(login))) {
            logger.warn("otherPartyUserRolesWithNotificationsEnabledThan: false, user role id: " + userRole.getId());
            return false;
        }

        logger.info("otherPartyUserRolesWithNotificationsEnabledThan: true, user role id: " + userRole.getId());
        return true;
    }

    @Override
    public boolean isNotificationAllowed(BusinessUserConfig businessUserConfig, Notification.NotificationType notificationType) {
        switch (notificationType) {
            case MESSAGE_RECEIVED_EMAIL:
            case WARNING_EMAIL:
                return businessUserConfig.getNotificationsEnabled() && StringUtils.isNotBlank(businessUserConfig.getEmail());
            case STATUS_RECEIVED_EMAIL:
                return businessUserConfig.getStatusNotificationEnabled() && StringUtils.isNotBlank(businessUserConfig.getStatusNotificationEmail());
            default:
                logger.warn("notification type unknown: " + notificationType);
                return false;
        }
    }
}
