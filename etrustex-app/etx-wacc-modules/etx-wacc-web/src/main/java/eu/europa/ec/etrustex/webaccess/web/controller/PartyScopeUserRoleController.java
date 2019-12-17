package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.*;
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

import java.util.*;

@Controller
public class PartyScopeUserRoleController extends AbstractUserRoleController {

    @Autowired
    protected PartyManager partyManager;

    @Autowired
    protected RoleUtils roleUtils;

    @Autowired
    protected BusinessManager businessManager;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "addPartyScopeUserRole.do"
    )
    protected
    @ResponseBody
    UserRoleFormBean doAddPartyScopeUserRole(@RequestBody UserRoleFormBean formBean) throws Exception {

        Party party = partyManager.getPartyById(formBean.getPartyId());
        if (party == null || !isAuthorized(party)) {
            return null;
        }

        if (roleUtils.isPartyScopeRole(formBean.getRoleType())
                && !securityChecker.allowAddPartyScopeUserRoleWithBusinessEmailEnforce(party, formBean.getNotificationsEnabled(), formBean.getLogin())) {
            return null;
        }

        User user = createOrRetrieveUser(formBean.getLogin(), formBean.getName());

        //create BUC if required
        createOrUpdateBUC(user, party.getBusiness(),
                new UserRoleDTO(formBean.getName(), formBean.getEmail(), formBean.getNotificationsEnabled(),
                        formBean.getStatusNotificationEmail(), formBean.getStatusNotificationEnabled()));

        //create UR if required
        UserRole userRole = createOrRetrievePartyUR(user, party, formBean.getRoleType());

        formBean.setUserRoleId(userRole.getId());

        return formBean;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "getPartyScopeUserRoles.do"
    )
    protected
    @ResponseBody
    List<UserRoleFormBean> doGetPartyScopeUserRoles(@RequestBody UserRoleFormBean formBean) throws Exception {
        Party party = partyManager.getPartyById(formBean.getPartyId());
        if (!isAuthorized(party)) {
            return new ArrayList<>();
        }

        Map<UserRole, BusinessUserConfig> userRoles = new HashMap<>();
        Set<Role.Type> reqRoleTypes = new HashSet<>(formBean.getRoleTypes());
        reqRoleTypes.retainAll(RoleUtils.getPartyScopeRoles());
        for (Role.Type roleType : reqRoleTypes) {
            userRoles.putAll(userManager.getUserRoles(party, roleType));
        }

        List<UserRoleFormBean> response = transformToFormBeans(userRoles);
        if (!businessManager.isBusinessEmailEnforceEnabledFor(party.getBusiness()) || party.getNotificationsEnabled()) {
            return response;
        }
        Collection<BusinessUserConfig> businessUserConfigs = new HashSet<>();
        businessUserConfigs.addAll(userRoles.values());

        Set<Role.Type> partyRoleTypes = new HashSet<>(RoleUtils.getPartyScopeRoles());
        partyRoleTypes.removeAll(reqRoleTypes);
        if (!partyRoleTypes.isEmpty()) {
            for (Role.Type roleType : partyRoleTypes) {
                businessUserConfigs.addAll(userManager.getUserRoles(party, roleType).values());
            }
        }

        markNotDeletable(response, businessUserConfigs);
        return response;
    }
}
