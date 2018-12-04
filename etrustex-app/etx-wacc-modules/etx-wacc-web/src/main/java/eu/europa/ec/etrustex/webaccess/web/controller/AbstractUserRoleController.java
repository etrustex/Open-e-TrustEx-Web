package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class AbstractUserRoleController {

    @Autowired
    protected UserManager userManager;

    @Autowired
    protected RoleUtils roleUtils;

    @Autowired
    protected SecurityChecker securityChecker;

    @Autowired
    protected UserSessionContext userSessionContext;

    private static final Logger logger = Logger.getLogger(AbstractUserRoleController.class);

    protected User createOrRetrieveUser(String login, String name) {
        User user = userManager.getUserDetails(login);
        if (user == null) {
            user = new User();
            user.setLogin(login);
            user.setName(name);
            userManager.saveOrUpdate(user);
        }
        return user;
    }

    protected User createOrUpdateUser(String login, String name) {
        User user = userManager.getUserDetails(login);
        if (user == null) {
            user = new User();
            user.setLogin(login);
            user.setName(name);
        } else {
            user.setName(name);
        }
        userManager.saveOrUpdate(user);
        return user;
    }

    protected BusinessUserConfig createOrUpdateBUC(User user, Business business, UserRoleDTO userRoleDTO) {
        BusinessUserConfig businessUserConfig = null;
        if (business != null) {
            businessUserConfig = userManager.getBusinessUserConfig(user, business);

            if (businessUserConfig == null) {
                businessUserConfig = new BusinessUserConfig();
                businessUserConfig.setUser(user);
                businessUserConfig.setBusiness(business);
            }
            businessUserConfig.setName(userRoleDTO.getName());
            businessUserConfig.setEmail(userRoleDTO.getEmail());
            businessUserConfig.setNotificationsEnabled(userRoleDTO.getNotificationsEnabled());
            businessUserConfig.setStatusNotificationEmail(userRoleDTO.getStatusNotificationEmail());
            businessUserConfig.setStatusNotificationEnabled(userRoleDTO.getStatusNotificationEnabled());
            userManager.saveOrUpdate(businessUserConfig);
        }
        return businessUserConfig;
    }

    protected BusinessUserConfig createOrUpdateBUC(User user, Business business, String name) {
        BusinessUserConfig businessUserConfig = null;
        if (business != null) {
            businessUserConfig = userManager.getBusinessUserConfig(user, business);

            if (businessUserConfig == null) {
                businessUserConfig = new BusinessUserConfig();
                businessUserConfig.setUser(user);
                businessUserConfig.setBusiness(business);
            }
            businessUserConfig.setName(name);
            userManager.saveOrUpdate(businessUserConfig);
        }
        return businessUserConfig;
    }

    protected boolean isAuthorized(Party party) {
        boolean hasPrivilege = securityChecker.hasPrivilegeForParty(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES, party);
        if (!hasPrivilege) {
            logger.warn("Is not authorized for party " + party.getId());
        }
        return hasPrivilege;
    }

    protected boolean isAuthorized(Business business) {
        boolean hasPrivilege = securityChecker.hasPrivilegeForBusiness(Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES, business);
        if (!hasPrivilege) {
            logger.warn("Is not authorized for business " + business.getId());
        }
        return hasPrivilege;
    }

    /**
     * Check that current user has system role privilege.
     *
     * @return <code>true<code/> if it has system role, <code>false<code/> otherwise.
     */
    protected boolean isAuthorizedAsSystemUser() {
        boolean hasPrivilege = securityChecker.hasPrivilegeForSystemUser(Privilege.Type.CAN_ASSIGN_SYSTEM_SCOPE_ROLES);
        User user = userSessionContext.getUser();
        if (!hasPrivilege) {
            logger.warn("[" + user.getLogin() + "] is not authorized as system user");
        }
        return hasPrivilege;
    }

    protected boolean isAuthorized(UserRole userRole) {
        Role.Type roleType = userRole.getRole().getType();
        if (roleUtils.isPartyScopeRole(roleType)) {
            return isAuthorized(userRole.getParty());
        } else if (roleUtils.isBusinessScopeRole(roleType)) {
            return isAuthorized(userRole.getBusiness());
        } else if (roleUtils.isSystemScopeRole(roleType)) {
            return true;
        }
        return false;//unexpected role type
    }

    protected List<UserRoleFormBean> transformToFormBeans(Map<UserRole, BusinessUserConfig> map) {
        List<UserRoleFormBean> userRoleFormBeans = new ArrayList<>();
        for (Map.Entry<UserRole, BusinessUserConfig> entry : map.entrySet()) {
            UserRole userRole = entry.getKey();
            BusinessUserConfig businessUserConfig = entry.getValue();

            UserRoleFormBean formBean = new UserRoleFormBean();
            formBean.setUserRoleId(userRole.getId());
            formBean.setLogin(businessUserConfig.getUser().getLogin());
            formBean.setName(businessUserConfig.getName());
            formBean.setEmail(businessUserConfig.getEmail());
            formBean.setNotificationsEnabled(businessUserConfig.getNotificationsEnabled());
            formBean.setStatusNotificationEnabled(businessUserConfig.getStatusNotificationEnabled());
            formBean.setStatusNotificationEmail(businessUserConfig.getStatusNotificationEmail());
            formBean.setRoleType(userRole.getRole().getType());
            formBean.setDeletable(true);

            userRoleFormBeans.add(formBean);
        }
        return userRoleFormBeans;
    }

    protected List<UserRoleFormBean> transformToUserFormBeans(Map<UserRole, User> map) {
        List<UserRoleFormBean> userRoleFormBeans = new ArrayList<>();
        for (Map.Entry<UserRole, User> entry : map.entrySet()) {
            UserRole userRole = entry.getKey();
            User user = entry.getValue();

            UserRoleFormBean formBean = new UserRoleFormBean();
            formBean.setUserRoleId(userRole.getId());
            formBean.setLogin(user.getLogin());
            formBean.setName(user.getName());
            // system admin doesn't have e-mail nor notification
            formBean.setEmail("");
            formBean.setNotificationsEnabled(false);
            formBean.setRoleType(userRole.getRole().getType());

            userRoleFormBeans.add(formBean);
        }
        return userRoleFormBeans;
    }

    protected UserRole createOrRetrievePartyUR(User user, Party party, Role.Type roleType) {
        List<UserRole> userRoles = userManager.getUserRoles(user);
        UserRole userRole = roleUtils.filterUserRolesByRoleAndParty(userRoles, roleType, party);
        if (userRole == null) {
            userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(userManager.getRole(roleType));
            userRole.setParty(party);
            userManager.saveOrUpdate(userRole);
        }
        return userRole;
    }

    protected void markNotDeletable(List<UserRoleFormBean> response, Collection<BusinessUserConfig> businessUserConfigs) {
        Iterator<BusinessUserConfig> businessUserConfigIterator = businessUserConfigs.iterator();
        while (businessUserConfigIterator.hasNext()) {
            BusinessUserConfig businessUserConfig = businessUserConfigIterator.next();
            if (!businessUserConfig.getNotificationsEnabled()) {
                businessUserConfigIterator.remove();
            }
        }
        if (businessUserConfigs.size() == 1) {
            BusinessUserConfig businessUserConfig = businessUserConfigs.iterator().next();
            for (UserRoleFormBean userRoleFormBean : response) {
                if (userRoleFormBean.getLogin().equals(businessUserConfig.getUser().getLogin())) {
                    userRoleFormBean.setDeletable(false);
                }
            }
        }
    }
}
