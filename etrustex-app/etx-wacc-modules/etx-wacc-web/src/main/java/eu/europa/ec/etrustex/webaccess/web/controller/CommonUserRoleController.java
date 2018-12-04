package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.Role;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleDTO;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleFormBean;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonUserRoleController extends AbstractUserRoleController {

    @Autowired
    protected PartyManager partyManager;

    @Autowired
    protected RoleUtils roleUtils;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "deleteUserRole.do"
    )
    protected
    @ResponseBody
    UserRoleFormBean doDeleteUserRole(@RequestBody UserRoleFormBean formBean) throws Exception {

        UserRole userRole = userManager.getUserRole(formBean.getUserRoleId());

        if (userRole == null || !isAuthorized(userRole)) {
            return null;
        }

        if (!allowDeleteUserRoleWithBusinessEmailEnforce(userRole)) {
            return null;
        }

        userManager.delete(userRole);
        return formBean;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "editUser.do"
    )
    protected
    @ResponseBody
    UserRoleFormBean doEditUser(@RequestBody UserRoleFormBean formBean) throws Exception {
        UserRole userRole = userManager.getUserRole(formBean.getUserRoleId());
        User user = userManager.getUserDetails(formBean.getLogin());

        if (userRole != null && isAuthorized(userRole) && allowEditUserRoleWithBusinessEmailEnforce(formBean, userRole)) {
            if (user == null || userRole.getRole().getType() == Role.Type.SYSTEM_ADMIN) {
                user = createOrUpdateUser(formBean.getLogin(), formBean.getName());
            } else if (roleUtils.isPartyScopeRole(userRole.getRole().getType())) {
                createOrUpdateBUC(user, roleUtils.extractBusiness(userRole),
                        new UserRoleDTO(formBean.getName(), formBean.getEmail(), formBean.getNotificationsEnabled(),
                                formBean.getStatusNotificationEmail(), formBean.getStatusNotificationEnabled()));
            } else if (roleUtils.isBusinessScopeRole(userRole.getRole().getType())) {
                createOrUpdateBUC(user, roleUtils.extractBusiness(userRole), formBean.getName());
            }

            if (!userRole.getRole().getType().equals(formBean.getRoleType())) {
                Party party = partyManager.getPartyById(formBean.getPartyId());
                createOrRetrievePartyUR(user, party, formBean.getRoleType());
                userManager.delete(userRole);
            }
        } else {
            return null;
        }
        return formBean;
    }

    protected boolean allowEditUserRoleWithBusinessEmailEnforce(UserRoleFormBean formBean, UserRole userRole) {
        return !roleUtils.isPartyScopeRole(userRole.getRole().getType())
                || securityChecker.allowEditPartyUserRoleWithBusinessEmailEnforce(userRole, formBean.getNotificationsEnabled());
    }

    protected boolean allowDeleteUserRoleWithBusinessEmailEnforce(UserRole userRole) {
        return !roleUtils.isPartyScopeRole(userRole.getRole().getType())
                || securityChecker.allowDeletePartyUserRoleWithBusinessEmailEnforce(userRole);
    }
}
