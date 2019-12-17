package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SecurityChecker {

    @Autowired @Qualifier("userSessionContext")
    protected UserSessionContext userSessionContext;

    @Autowired
    protected RoleUtils roleUtils;

    @Autowired
    private BusinessManager businessManager;

    @Autowired
    private UserManager userManager;

    public boolean canSend(Party party) {
        Map<BusinessConfigProperty, String> businessConfigs = party.getBusiness().getBusinessConfigValues();
        String businessCanSend = businessConfigs.get(BusinessConfigProperty.BUS_SENDING_ENABLED);
        return Boolean.valueOf(businessCanSend);
    }

    public boolean canAccessMessagesOfParty(Party party) {
        return userSessionContext.getMessageParties().contains(party);
    }

    public boolean hasPrivilege(Privilege.Type privilegeType) {
        List<UserRole> userRoles = userSessionContext.getUserRoleList();
        return roleUtils.filterUserRolesByPrivilege(userRoles, privilegeType).size() > 0;
    }

    public boolean hasPrivilegeForParty(Privilege.Type privilegeType, Party party) {
        List<UserRole> userRoles = roleUtils.filterUserRolesByPrivilege(userSessionContext.getUserRoleList(), privilegeType);
        for (UserRole userRole : userRoles) {
            if (roleUtils.isPartyScopeRole(userRole.getRole().getType())
                    && party.equals(userRole.getParty())) {
                return true;
            }
            if (roleUtils.isBusinessScopeRole(userRole.getRole().getType())
                    && party.getBusiness().equals(userRole.getBusiness())) {
                return true;
            }
            if (roleUtils.isSystemScopeRole(userRole.getRole().getType())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPrivilegeForBusiness(Privilege.Type privilegeType, Business business) {
        List<UserRole> userRoles = roleUtils.filterUserRolesByPrivilege(userSessionContext.getUserRoleList(), privilegeType);
        for (UserRole userRole : userRoles) {
            if (roleUtils.isBusinessScopeRole(userRole.getRole().getType())
                    && business.equals(userRole.getBusiness())) {
                return true;
            }
            if (roleUtils.isSystemScopeRole(userRole.getRole().getType())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPrivilegeForSystemUser(Privilege.Type privilegeType) {
        List<UserRole> userRoles = roleUtils.filterUserRolesByPrivilege(userSessionContext.getUserRoleList(), privilegeType);
        for (UserRole userRole : userRoles) {
            if (roleUtils.isSystemScopeRole(userRole.getRole().getType())) {
                return true;
            }
        }
        return false;
    }

    public boolean allowSavePartyDetailsWithBusinessEmailEnforce(Party party, boolean notificationsEnabled) {
        if (notificationsEnabled
                || !businessManager.isBusinessEmailEnforceEnabledFor(party.getBusiness())) {
            return true;
        }

        return userManager.partyHasRolesWithNotificationsEnabled(party, null);
    }

    public boolean allowAddPartyScopeUserRoleWithBusinessEmailEnforce(Party party, boolean notificationsEnabled, String login) {
        if (notificationsEnabled
                || party.getNotificationsEnabled()
                || !businessManager.isBusinessEmailEnforceEnabledFor(party.getBusiness())) {
            return true;
        }

        return userManager.partyHasRolesWithNotificationsEnabled(party, login);
    }

    public boolean allowDeletePartyUserRoleWithBusinessEmailEnforce(UserRole userRole) {
        Party party = userRole.getParty();

        if (party.getNotificationsEnabled()
                || !businessManager.isBusinessEmailEnforceEnabledFor(party.getBusiness())) {
            return true;
        }

        return userManager.otherPartyUserRolesWithNotificationsEnabledThan(userRole);
    }

    public boolean allowEditPartyUserRoleWithBusinessEmailEnforce(UserRole userRole, boolean notificationsEnabled) {
        Party party = userRole.getParty();

        if (notificationsEnabled
                || party.getNotificationsEnabled()
                || !businessManager.isBusinessEmailEnforceEnabledFor(party.getBusiness())) {
            return true;
        }

        return userManager.otherPartyUserRolesWithNotificationsEnabledThan(userRole);
    }
}
