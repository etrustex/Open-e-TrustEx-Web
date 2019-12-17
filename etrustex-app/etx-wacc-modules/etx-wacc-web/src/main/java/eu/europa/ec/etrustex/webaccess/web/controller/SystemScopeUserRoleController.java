package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.Role;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SystemScopeUserRoleController extends AbstractUserRoleController {

    @Autowired
    private UserManager userManager;

    @Autowired
    protected BusinessManager businessManager;

    @RequestMapping(method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = "addSystemScopeUserRole.do"
    )
    protected
    @ResponseBody
    UserRoleFormBean doAddSystemScopeUserRole(@RequestBody UserRoleFormBean formBean) throws Exception {
        if (!isAuthorizedAsSystemUser()) {
            return null;
        }

        User user = createOrUpdateUser(formBean.getLogin(), formBean.getName());

        //create UR if required
        UserRole userRole = createOrRetrieveSystemUR(user, formBean.getRoleType());

        formBean.setUserRoleId(userRole.getId());

        return formBean;
    }

    @RequestMapping(method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        value = "getSystemScopeUserRoles.do"
    )
    protected
    @ResponseBody
    List<UserRoleFormBean> doGetSystemScopeUserRoles(@RequestBody UserRoleFormBean formBean) throws Exception {
        if (!isAuthorizedAsSystemUser() || !isExpectedRoleIn(formBean.getRoleTypes())) {
            return new ArrayList<>();
        }
        return transformToUserFormBeans(userManager.getUserRoles(formBean.getRoleTypes().get(0)));
    }

    protected UserRole createOrRetrieveSystemUR(User user, Role.Type roleType) {
        List<UserRole> userRoles = userManager.getUserRoles(user);
        UserRole userRole = roleUtils.filterUserRolesBySystemRole(userRoles, roleType);
        if (userRole == null) {
            userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(userManager.getRole(roleType));
            userManager.saveOrUpdate(userRole);
        }
        return userRole;
    }

    protected boolean isExpectedRoleIn(List<Role.Type> roleTypes) {
        return !(roleTypes == null || roleTypes.isEmpty() || roleTypes.get(0) != Role.Type.SYSTEM_ADMIN);
    }
}
