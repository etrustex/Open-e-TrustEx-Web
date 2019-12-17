package eu.europa.ec.etrustex.webaccess.web.utils;

import eu.europa.ec.etrustex.webaccess.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RoleUtils {
    private static List<Role.Type> partyScopeRoles = Collections.unmodifiableList(Arrays.asList(Role.Type.OPERATOR, Role.Type.PARTY_ADMIN));

    public List<UserRole> filterUserRolesByPrivilege(List<UserRole> initialUserRoles, Privilege.Type privilegeType) {
        List<UserRole> userRoles = new ArrayList<>();
        for (UserRole userRole : initialUserRoles) {
            if (roleHasPrivilege(userRole.getRole(), privilegeType)) {
                userRoles.add(userRole);
            }
        }
        return userRoles;
    }

    public boolean roleHasPrivilege(Role role, Privilege.Type privilegeType) {
        for (Privilege privilege : role.getPrivileges()) {
            if (privilege.getType() == privilegeType) {
                return true;
            }
        }
        return false;
    }

    public boolean isPartyScopeRole(Role.Type roleType) {
        return roleType == Role.Type.OPERATOR || roleType == Role.Type.PARTY_ADMIN;
    }

    public static List<Role.Type> getPartyScopeRoles() {
        return partyScopeRoles;
    }

    public boolean isBusinessScopeRole(Role.Type roleType) {
        return roleType == Role.Type.BUSINESS_ADMIN;
    }

    public boolean isSystemScopeRole(Role.Type roleType) {
        return roleType == Role.Type.SYSTEM_ADMIN;
    }

    public UserRole filterUserRolesByRoleAndParty(List<UserRole> initialUserRoles, Role.Type roleType, Party party) {
        for (UserRole userRole : initialUserRoles) {
            if (userRole.getRole().getType() == roleType) {
                if (isPartyScopeRole(roleType) && userRole.getParty().equals(party)) {
                    return userRole;
                }
                if (isBusinessScopeRole(roleType) && userRole.getBusiness().equals(party.getBusiness())) {
                    return userRole;
                }
                if (isSystemScopeRole(roleType)) {
                    return userRole;
                }
            }
        }
        return null;
    }

    public UserRole filterUserRolesByRoleAndBusiness(List<UserRole> initialUserRoles, Role.Type roleType, Business business) {
        for (UserRole userRole : initialUserRoles) {
            if (userRole.getRole().getType() == roleType) {
                if (isBusinessScopeRole(roleType) && userRole.getBusiness().equals(business)) {
                    return userRole;
                }
                if (isSystemScopeRole(roleType)) {
                    return userRole;
                }
            }
        }
        return null;
    }

    public UserRole filterUserRolesBySystemRole(List<UserRole> initialUserRoles, Role.Type roleType) {
        for (UserRole userRole : initialUserRoles) {
            if (userRole.getRole().getType() == roleType) {
                if (isSystemScopeRole(roleType)) {
                    return userRole;
                }
            }
        }
        return null;
    }

    public Business extractBusiness(UserRole userRole) {
        Role.Type roleType = userRole.getRole().getType();
        if (isPartyScopeRole(roleType)) {
            return userRole.getParty().getBusiness();
        } else if (isBusinessScopeRole(roleType)) {
            return userRole.getBusiness();
        } else {
            return null;
        }
    }

}
