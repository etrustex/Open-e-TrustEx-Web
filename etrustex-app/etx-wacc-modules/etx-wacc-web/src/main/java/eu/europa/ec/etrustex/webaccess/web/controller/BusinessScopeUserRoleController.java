package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.web.model.DisplayPartyIca;
import eu.europa.ec.etrustex.webaccess.web.model.PartyData;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.UserRoleXLS;
import eu.europa.ec.etrustex.webaccess.web.utils.XlsExportService;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class BusinessScopeUserRoleController extends AbstractUserRoleController {

    @Autowired
    protected BusinessManager businessManager;

    @Autowired
    protected PartyManager partyManager;

    @Autowired
    protected IcaManager icaManager;

    @Autowired
    protected XlsExportService xlsExportService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "addBusinessScopeUserRole.do"
    )
    protected
    @ResponseBody
    UserRoleFormBean doAddBusinessScopeUserRole(@RequestBody UserRoleFormBean formBean) throws Exception {

        Business business = businessManager.getBusinessById(formBean.getBusinessId());
        if (!isAuthorized(business)) {
            return null;
        }

        User user = createOrRetrieveUser(formBean.getLogin(), formBean.getName());

        //create BUC if required
        createOrUpdateBUC(user, business, formBean.getName());

        //create UR if required
        UserRole userRole = createOrRetrieveBusinessUR(user, business, formBean.getRoleType());

        formBean.setUserRoleId(userRole.getId());

        return formBean;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "getBusinessScopeUserRoles.do"
    )
    protected
    @ResponseBody
    List<UserRoleFormBean> doGetBusinessScopeUserRoles(@RequestBody UserRoleFormBean formBean) throws Exception {
        Business business = businessManager.getBusinessById(formBean.getBusinessId());
        if (!isAuthorized(business) || !isValidRole(formBean.getRoleTypes())) {
            return Collections.emptyList();
        }
        Role.Type roleType = formBean.getRoleTypes().get(0);
        Map<UserRole, BusinessUserConfig> userRoles = userManager.getUserRoles(business, roleType);
        return transformToFormBeans(userRoles);
    }

    protected UserRole createOrRetrieveBusinessUR(User user, Business business, Role.Type roleType) {
        List<UserRole> userRoles = userManager.getUserRoles(user);
        UserRole userRole = roleUtils.filterUserRolesByRoleAndBusiness(userRoles, roleType, business);
        if (userRole == null) {
            userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(userManager.getRole(roleType));
            userRole.setBusiness(business);
            userManager.saveOrUpdate(userRole);
        }
        return userRole;
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "getBusinessScopeParties.do"
    )
    protected
    @ResponseBody
    List<PartyData> doGetBusinessScopeParties(@RequestParam(value = "businessId", required = true) Long businessId) throws Exception {
        List<PartyData> partyDatas = new ArrayList<>();
        Business business = businessManager.getBusinessById(businessId);
        if (isAuthorized(business)) {
            List<Party> parties = partyManager.getPartiesOfBusiness(business);
            for (Party party : parties) {
                List<PartyIca> partyIcas = icaManager.getIcasByParty(party);
                List<DisplayPartyIca> partyIcasModels = new ArrayList<>();
                for (PartyIca partyIca : partyIcas) {
                    DisplayPartyIca partyIcaModel = new DisplayPartyIca();
                    Party remoteParty = partyIca.getRemoteParty();

                    partyIcaModel.setRemotePartyId(remoteParty.getId());
                    partyIcaModel.setRemotePartyDisplayName(remoteParty.getDisplayName());
                    partyIcaModel.setRemotePartyNodeName(remoteParty.getNodeName());
                    partyIcaModel.setSignatureMandatory(IntegrityCode.forCode("" + partyIca.getIntegrityCode()).getIsSignatureRequired());
                    partyIcaModel.setEncryptionRequired(ConfidentialityCode.forCode(partyIca.getConfidentialityCode()).isEncryptionRequired());
                    partyIcasModels.add(partyIcaModel);
                }
                partyDatas.add(new PartyData(party.getId(), party.getDisplayName(), party.getNodeName(), partyIcasModels));
            }
            Collections.sort(partyDatas);
        }
        return partyDatas;
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "savePartyDisplayName.do"
    )
    protected
    @ResponseBody
    ResponseEntity doSavePartyDisplayName(@RequestParam(value = "partyId", required = true) Long partyId,
                                          @RequestParam(value = "displayName", required = true) String displayName) throws Exception {
        Party party = partyManager.getPartyById(partyId);
        if (party != null) {
            Business business = party.getBusiness();
            if (isAuthorized(business) && isValidPartyDisplayName(displayName)) {
                if (!party.getDisplayName().equals(displayName)) {
                    party.setDisplayName(displayName);
                    partyManager.saveOrUpdate(party);
                }
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    protected boolean isValidPartyDisplayName(String displayName) {
        return displayName != null && !displayName.trim().isEmpty();
    }

    protected boolean isValidRole(List<Role.Type> roleTypes) {
        return roleTypes != null && !roleTypes.isEmpty() && roleTypes.get(0) == Role.Type.BUSINESS_ADMIN;
    }

    @RequestMapping(value = "exportPartyUsers.do", method = RequestMethod.GET)
    public void exportPartyUsers(@RequestParam(value = "businessId", required = true) String id, HttpServletResponse response) {
        Long businessId = Long.parseLong(id);
        Set<Role.Type> reqRoleTypes = new HashSet<>();
        reqRoleTypes.add(Role.Type.PARTY_ADMIN);
        reqRoleTypes.add(Role.Type.OPERATOR);
        Map<UserRole, BusinessUserConfig> userRoles;
        Business business = businessManager.getBusinessById(businessId);
        List<UserRoleXLS> userRoleXLSs = new ArrayList<>();
        if (isAuthorized(business)) {
            List<Party> parties = partyManager.getPartiesOfBusiness(business);
            for (Party party : parties) {
                userRoles = new HashMap<>();
                if (party.getEmail() != null) {
                    UserRoleXLS user = new UserRoleXLS();
                    user.setPartyName(party.getDisplayName());
                    user.setUserName("Functional mailbox");
                    user.setEmail(party.getEmail());
                    user.setNotificationsEnabled(party.getNotificationsEnabled() ? "Yes" : "No");
                    userRoleXLSs.add(user);
                }
                for (Role.Type roleType : reqRoleTypes) {
                    userRoles.putAll(userManager.getUserRoles(party, roleType));
                }
                userRoleXLSs.addAll(transformToUserRoleXLS(userRoles,party));
            }
        }

        try {
            Sheet sheet = xlsExportService.buildExcelDocument(userRoleXLSs);
            String fileName = business.getName()+".XLSX";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream outputStream = response.getOutputStream();
            sheet.getWorkbook().write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
