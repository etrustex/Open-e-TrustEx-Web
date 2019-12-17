package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.administration.CacheService;
import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.IcaDetailsService;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import eu.europa.ec.etrustex.webaccess.model.vo.IcaDetailsVO;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaKey;
import eu.europa.ec.etrustex.webaccess.web.helper.BusinessComparator;
import eu.europa.ec.etrustex.webaccess.web.helper.IcaDetailsComparator;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.AdminFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.PartyData;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.utils.RoleUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {
    private static final Logger logger = Logger.getLogger(AdminController.class);

    public static final String ADMIN_TILE = "admin";

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Autowired
    protected RoleUtils roleUtils;

    @Autowired
    protected PartyManager partyManager;

    @Autowired
    protected BusinessManager businessManager;

    @Autowired
    protected UserManager userManager;

    @Autowired
    protected SecurityChecker securityChecker;

    @Autowired
    protected UserSessionContext userSessionContext;

    @Autowired
    protected IcaDetailsService icaDetailsService;

    @Autowired
    protected CacheService cacheService;

    @Autowired
    private ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.GET, value = "admin.do")
    protected ModelAndView doGet(HttpServletRequest request) throws Exception {

        if (!isAuthorized()) {
            return new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
        }

        List<UserRole> userRoles = userSessionContext.getUserRoleList();
        ModelAndView modelAndView = new ModelAndView(ADMIN_TILE);

        modelAndView.addObject("isPartyAdmin", adminWithParties(userRoles));

        modelAndView.addObject("hideUserDetails", true);

        List<Business> businesses = retrieveBusinessesToAdmin(userRoles);
        modelAndView.addObject("businesses", businesses);

        modelAndView.addObject("isSystemAdmin", isSystemAdmin());

        modelAndView.addObject("user", userSessionContext.getUser());

        Configuration configurations = configurationService.getConfiguration();
        modelAndView.addObject("announcementsContent", configurations.getAnnouncementsContent());

        modelAndView.addObject("supportEmail", configurations.getSupportEmail());
        modelAndView.addObject("userGuideUrl", configurations.getUserGuideUrl());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "getPartiesToAdmin.do")
    protected
    @ResponseBody
    Collection<PartyData> doGetPartiesToAdmin() {
        if (!isAuthorized()) {
            return Collections.emptySet();
        }
        List<UserRole> userRoles = userSessionContext.getUserRoleList();
        return retrievePartiesToAdmin(userRoles);
    }

    protected Collection<PartyData> retrievePartiesToAdmin(List<UserRole> accessibleUserRoles) {
        Set<PartyData> parties = new TreeSet<>();
        List<UserRole> userRoles = roleUtils.filterUserRolesByPrivilege(accessibleUserRoles, Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES);
        for (UserRole userRole : userRoles) {
            if (userRole.getParty() != null) {
                parties.add(partyData(userRole.getParty()));
            }
            if (userRole.getBusiness() != null && userRole.getParty() == null) {
                List<Party> partiesOfBusiness = partyManager.getPartiesOfBusiness(userRole.getBusiness());
                for (Party partyOfBusiness : partiesOfBusiness) {
                    parties.add(partyData(partyOfBusiness));
                }
            }
            if (userRole.getParty() == null && userRole.getBusiness() == null) {
                List<Party> partiesOfSystem = partyManager.getAllParties();
                for (Party partyOfSystem : partiesOfSystem) {
                    parties.add(partyData(partyOfSystem));
                }
            }
        }
        return parties;
    }

    protected boolean adminWithParties(List<UserRole> accessibleUserRoles) {
        return !roleUtils.filterUserRolesByPrivilege(accessibleUserRoles, Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES).isEmpty();
    }

    private PartyData partyData(Party party) {
        return new PartyData(party.getId(), party.getDisplayName(), party.getNodeName(), securityChecker.canSend(party));
    }

    protected List<Business> retrieveBusinessesToAdmin(List<UserRole> accessibleUserRoles) {
        Set<Business> businesses = new HashSet<>();

        List<UserRole> userRoles = roleUtils.filterUserRolesByPrivilege(accessibleUserRoles, Privilege.Type.CAN_ASSIGN_BUSINESS_SCOPE_ROLES);

        for (UserRole userRole : userRoles) {
            if (userRole.getBusiness() != null && userRole.getParty() == null) {
                businesses.add(userRole.getBusiness());
            }
            if (userRole.getParty() == null && userRole.getBusiness() == null) {
                List<Business> businessesOfSystem = businessManager.getAllBusinesses();
                for (Business partyOfSystem : businessesOfSystem) {
                    businesses.add(partyOfSystem);
                }
            }
        }

        List<Business> sortedBusinesses = new ArrayList<>(businesses);
        Collections.sort(sortedBusinesses, new BusinessComparator());

        return sortedBusinesses;
    }

    protected boolean isSystemAdmin() {
        return securityChecker.hasPrivilegeForSystemUser(Privilege.Type.CAN_ASSIGN_SYSTEM_SCOPE_ROLES);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "loadPartyDetails.do")
    protected
    @ResponseBody
    AdminFormBean doLoadPartyDetails(@RequestBody AdminFormBean formBean) {
        Party party = partyManager.getPartyById(formBean.getPartyId());

        if (party == null || !isAuthorized(party)) {
            return null;
        }

        Business business = businessManager.getBusinessById(party.getBusiness().getId());
        formBean.setEmail(party.getEmail());
        formBean.setNotificationsEnabled(party.getNotificationsEnabled());
        formBean.setStatusNotificationEmail(party.getStatusNotificationEmail());
        formBean.setStatusNotificationEnabled(party.getStatusNotificationEnabled());
        formBean.setBusinessLabel(business.getName());
        fillEmailEnforceInfo(formBean, party);

        return formBean;
    }

    private void fillEmailEnforceInfo(AdminFormBean formBean, Party party) {
        formBean.setEnforceNotifications(false);
        formBean.setCheckDisabled(false);
        if (businessManager.isBusinessEmailEnforceEnabledFor(party.getBusiness())) {
            formBean.setEnforceNotifications(!party.getNotificationsEnabled());
            formBean.setCheckDisabled(party.getNotificationsEnabled() && party.getEmail() != null && party.getEmail().trim().length() > 0);
            Collection<BusinessUserConfig> businessUserConfigs = new HashSet<>();
            businessUserConfigs.addAll(userManager.getUserRoles(party, Role.Type.OPERATOR).values());
            businessUserConfigs.addAll(userManager.getUserRoles(party, Role.Type.PARTY_ADMIN).values());
            for (BusinessUserConfig businessUserConfig : businessUserConfigs) {
                if (businessUserConfig.getNotificationsEnabled()) {
                    formBean.setEnforceNotifications(false);
                    formBean.setCheckDisabled(false);
                    break;
                }
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "savePartyDetails.do")
    protected
    @ResponseBody
    AdminFormBean doSavePartyDetails(@RequestBody AdminFormBean formBean) {
        Party party = partyManager.getPartyById(formBean.getPartyId());

        if (party == null || !isAuthorized(party)) {
            return null;
        }

        if (!securityChecker.allowSavePartyDetailsWithBusinessEmailEnforce(party, formBean.getNotificationsEnabled())) {
            return null;
        }

        party.setEmail(formBean.getEmail());
        party.setNotificationsEnabled(formBean.getNotificationsEnabled());
        party.setStatusNotificationEmail(formBean.getStatusNotificationEmail());
        party.setStatusNotificationEnabled(formBean.getStatusNotificationEnabled());
        partyManager.saveOrUpdate(party);

        fillEmailEnforceInfo(formBean, party);

        return formBean;
    }

    protected boolean isAuthorized() {
        return securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES);
    }

    protected boolean isAuthorized(Party party) {
        return securityChecker.hasPrivilegeForParty(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES, party);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "showIcaDetails.do")
    protected
    @ResponseBody
    List<IcaDetailsVO> doShowIcaDetails() {
        List<IcaDetailsVO> icaDetailsVOs = Collections.emptyList();
        if (isSystemAdmin()) {
            Map<NodeIcaKey, NodeIcaDetails> nodeIcaDetailsMap = icaDetailsService.getIcasByActivePartiesDetails();
            icaDetailsVOs = adaptNodeIcaDetails(nodeIcaDetailsMap);
        }
        return icaDetailsVOs;
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "reloadIcaDetails.do")
    protected
    @ResponseBody
    List<IcaDetailsVO> doReloadIcaDetails() {
        List<IcaDetailsVO> icaDetailsVOs = Collections.emptyList();
        if (isSystemAdmin()) {
            Map<NodeIcaKey, NodeIcaDetails> nodeIcaDetailsMap = icaDetailsService.loadAllIcaDetails();
            icaDetailsVOs = adaptNodeIcaDetails(nodeIcaDetailsMap);
        }
        return icaDetailsVOs;
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "reloadIcaDetailsSenderReceiver.do")
    protected
    @ResponseBody
    IcaDetailsVO doReloadIcaDetailsSenderReceiver(@RequestParam(value = "webParty", required = true) String webParty,
                                                  @RequestParam(value = "icaNodeParty", required = true) String icaNodeParty) {
        IcaDetailsVO icaDetailsVO;

        if (isSystemAdmin()) {
            NodeIcaDetails nodeIcaDetails = icaDetailsService.loadIcaDetails(webParty, icaNodeParty);
            if (nodeIcaDetails == null) {
                nodeIcaDetails = icaDetailsService.getIcaDetails(partyManager.getWebManagedPartyByNodeName(webParty), icaNodeParty);
                icaDetailsVO = adaptNodeIcaDetails(nodeIcaDetails, true);
            } else {
                icaDetailsVO = adaptNodeIcaDetails(nodeIcaDetails, false);
            }
        } else {
            throw new UnsupportedOperationException("This action is restricted to System Administrator ");
        }

        return icaDetailsVO;
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "resetCache.do")
    protected
    @ResponseBody
    Boolean doResetCache() {
        if (isSystemAdmin()) {
            cacheService.resetCache();
            return true;
        }
        return false;
    }

    private List<IcaDetailsVO> adaptNodeIcaDetails(Map<NodeIcaKey, NodeIcaDetails> nodeIcaDetailsMap) {
        List<IcaDetailsVO> icaDetailsVOs = new ArrayList<>();

        for (NodeIcaDetails nodeIcaDetails : nodeIcaDetailsMap.values()) {
            IcaDetailsVO icaDetailsVO = adaptNodeIcaDetails(nodeIcaDetails, false);
            icaDetailsVOs.add(icaDetailsVO);
        }

        Collections.sort(icaDetailsVOs, new IcaDetailsComparator());
        return icaDetailsVOs;
    }

    private IcaDetailsVO adaptNodeIcaDetails(NodeIcaDetails nodeIcaDetails, boolean updateFailed) {
        IcaDetailsVO icaDetailsVO = new IcaDetailsVO();

        if (nodeIcaDetails != null) {
            icaDetailsVO.setSenderParty(nodeIcaDetails.getLocalParty());
            icaDetailsVO.setReceiverParty(nodeIcaDetails.getRemoteParty());
            if (nodeIcaDetails.getExtendedCertificateData() != null) {
                try {
                    ExtendedCertificateData extendedCertificateData = nodeIcaDetails.getExtendedCertificateData();
                    icaDetailsVO.setVersion(extendedCertificateData.getVersion());
                    icaDetailsVO.setSerialNumber(extendedCertificateData.getSerialNumber());
                    icaDetailsVO.setIssuerDN(extendedCertificateData.getIssuerDN());
                    icaDetailsVO.setStartDate(extendedCertificateData.getStartDate().toString());
                    icaDetailsVO.setFinalDate(extendedCertificateData.getEndDate().toString());
                    icaDetailsVO.setSubjectDN(extendedCertificateData.getSubjectDN());
                    icaDetailsVO.setSignatureAlgorithm(extendedCertificateData.getSignatureAlgorithm());
                    icaDetailsVO.setHasCertificate(true);
                } catch (Exception e) {
                    logger.warn("missing or incorrect ICA certificate", e.getCause());
                }
            }
            icaDetailsVO.setConfidentialityCode(nodeIcaDetails.getConfidentialityCode() != null ? nodeIcaDetails.getConfidentialityCode().name() : "");
            icaDetailsVO.setIntegrityCode(nodeIcaDetails.getIntegrityCode() != null ? nodeIcaDetails.getIntegrityCode().name() : "");
            icaDetailsVO.setLastDateReloaded(formatter.format(nodeIcaDetails.getCreationDate()));
            icaDetailsVO.setIcaStatus(updateFailed ? IcaDetailsVO.IcaStatus.UPDATE_FAILED : IcaDetailsVO.IcaStatus.OK);
            icaDetailsVO.setActiveState(nodeIcaDetails.isActiveState());
        } else {
            icaDetailsVO.setIcaStatus(IcaDetailsVO.IcaStatus.UPDATE_FAILED);
        }

        return icaDetailsVO;
    }

}
