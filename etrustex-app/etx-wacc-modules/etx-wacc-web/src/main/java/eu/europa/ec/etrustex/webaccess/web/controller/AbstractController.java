package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.i18n.DataSourceMessageSource;
import eu.europa.ec.etrustex.webaccess.web.model.ChangeViewFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.MessageFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.MessageFormBeanFactory;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.Configuration;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.ConfigurationProvider;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.WebHandlerHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractController {

    public static final String GENERIC = "generic";
    public static final String CUSTOM = "custom";

    @Autowired
    protected PageState pageState;

    @Autowired
    private WebHandlerHelper webHandlerHelper;

    @Autowired
    protected UserSessionContext userSessionContext;

    @Autowired
    protected MailboxManager mailboxManager;

    @Autowired
    protected ConfigurationProvider configurationProvider;

    @Autowired
    protected SecurityChecker securityChecker;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private DataSourceMessageSource messageSource;

    @Autowired
    protected IcaManager icaManager;

    private static final Logger logger = Logger.getLogger(AbstractController.class);

    protected String resolveView(String initialName, Party party) {
        String resolvedName = null;

        Business business = party.getBusiness();

        Map<BusinessConfigProperty, String> businessConfigs = business.getBusinessConfigValues();
        String customViewName = businessConfigs.get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);

        if (initialName.equals(CUSTOM)
                && configurationProvider.isWebHandlerAvailable(customViewName, getAction())) {

            resolvedName = customViewName;
        }

        if (resolvedName == null
                && configurationProvider.isGenericViewEnabled(customViewName, getAction())) {

            resolvedName = GENERIC;
        }

        if (resolvedName == null) {
            throw new IllegalArgumentException("Unexpected view: " + initialName);
        }
        return resolvedName;
    }

    protected int getAvailableViewsCount(Party party) {
        int count = 0;
        Business representedBusiness = party.getBusiness();

        Map<BusinessConfigProperty, String> businessConfigs = representedBusiness.getBusinessConfigValues();
        String customViewName = businessConfigs.get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);

        if (configurationProvider.isWebHandlerAvailable(customViewName, getAction())) {
            count++;
        }
        if (configurationProvider.isGenericViewEnabled(customViewName, getAction())) {
            count++;
        }
        return count;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView doGet(HttpServletRequest request) throws Exception {
        pageState.populate(request.getParameterMap());

        Party party = getCurrentParty();
        if (party == null) {
            logger.warn("party == null, redirecting to error403.do");
            return new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
        }
        pageState.setPartyId(party.getId());

        String view = resolveView(pageState.getView(), party);
        logger.debug("Getting in " + view + "/" + getAction().getName() + " view");

        //business specific
        Configuration configuration = configurationProvider.getConfiguration(view);
        QueryParams queryParams = buildQueryParams(pageState, party, configuration.getMessageListPageSize());
        WebHandler handler = configuration.getWebHandler(getAction());
        Map<String, Object> businessModel = handler.buildBusinessModel(queryParams);


        //generic
        ModelAndView modelAndView = new ModelAndView(view + "/" + getAction().getName());
        modelAndView.addObject("ps", pageState);
        modelAndView.addObject("action", getAction().getDo());
        int availableViewsCount = getAvailableViewsCount(party);
        modelAndView.addObject("viewCount", availableViewsCount);

        if (availableViewsCount > 1) {
            modelAndView.addObject("customViewLabel", getCustomViewLabel(party));
        }

        modelAndView.addAllObjects(businessModel);

        ChangeViewFormBean changeViewFormBean = new ChangeViewFormBean();
        changeViewFormBean.setView(pageState.getView());
        modelAndView.addObject("changeViewFormBean", changeViewFormBean);

        boolean canSend = securityChecker.canSend(party);
        modelAndView.addObject("sendAbility", canSend ? 1 : 0);

        long unreadCountIncoming = mailboxManager.getUnreadCount(userSessionContext.getUser(), party, Message.MessageState.INCOMING);
        modelAndView.addObject("unreadCountIncoming", unreadCountIncoming);

        modelAndView.addObject("user", userSessionContext.getUser());
        modelAndView.addObject("userSessionContext", userSessionContext);

        modelAndView.addObject("businessUserName", userSessionContext.getBusinessUserNameByParty().get(party.getId()));
        if (canSend) {
            if (modelAndView.getModel().get("hasLinkedIcas") != null && modelAndView.getModel().get("hasLinkedIcas").equals(0)) {
                if (getAction().getName().equals(WebAction.MESSAGE_CREATE_WS.getName())) {
                    logger.warn("No linked party icas available, not authorized to send");
                    return new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
                }
            }

            long unreadCountDraft = mailboxManager.getUnreadCount(userSessionContext.getUser(), party, Message.MessageState.DRAFT);
            modelAndView.addObject("unreadCountDraft", unreadCountDraft);
        }

        modelAndView.addObject("isAdminLinkEnabled", securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES));

        eu.europa.ec.etrustex.webaccess.business.config.Configuration configurations = configurationService.getConfiguration();
        int retentionPolicyWeeks = configurations.getRetentionPolicyWeeks();

        modelAndView.addObject("retentionPolicyWeeks", retentionPolicyWeeks);
        modelAndView.addObject("statuses", getStatusOptions(request));

        modelAndView.addObject("announcementsContent", configurations.getAnnouncementsContent());

        String customViewName = party.getBusiness().getBusinessConfigValues().get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        modelAndView.addObject("businessName", customViewName);

        if (modelAndView.getModel().get("hasMessageRemoteIca") != null && modelAndView.getModel().get("hasMessageRemoteIca").equals(0)) {
            logger.warn("No ICA available for selected Message: " + ((Message) modelAndView.getModel().get(WebHandler.MESSAGE_ATTR)).getId());
            return new ModelAndView(new RedirectViewWithLogging("error/error403.do", request, userSessionContext.getUser().getLogin()));
        }

        if (modelAndView.getModel().get(WebHandler.MESSAGE_ICA) != null) {
            String receiverParty = ((IcaDetailsVO)modelAndView.getModel().get(WebHandler.MESSAGE_ICA)).getReceiverParty();
            ICADetails icaDetails = webHandlerHelper.getIcaDetails(party, receiverParty);
            boolean isEncryptionRequired = icaDetails != null ? icaDetails.getIsEncryptionRequired() : false;
            modelAndView.addObject("isEncryptionRequired", isEncryptionRequired);
        }

        BusinessCustomViewName businessCustomViewName = BusinessCustomViewName.forCustomViewName(customViewName);
        postProcessGet(modelAndView, businessCustomViewName);

        return modelAndView;
    }

    protected Map<String, String> getStatusOptions(HttpServletRequest request) {
        Map<String, String> statuses = new TreeMap<>();
        Locale locale = RequestContextUtils.getLocale(request);
        for (MessageListQueryParams.MessageStatus messageStatus: MessageListQueryParams.MessageStatus.values()) {
            statuses.put(messageStatus.name(), messageSource.getMessage(messageStatus.getMsgCode(), null, locale));
        }
        return statuses;
    }

    @RequestMapping(method = RequestMethod.POST, params = "view")
    protected ModelAndView doChangeView(@ModelAttribute("changeViewFormBean") ChangeViewFormBean formBean,
                                        HttpServletRequest request) throws Exception {
        pageState.populate(request.getParameterMap());
        pageState.setView(formBean.getView());
        ModelAndView modelAndView = new ModelAndView(new RedirectView(getAction().getDo()));
        modelAndView.addAllObjects(pageState.getParams());
        return modelAndView;
    }

    protected String getCustomViewLabel(Party party) {
        String customView = resolveView(CUSTOM, party);
        Configuration customConfiguration = configurationProvider.getConfiguration(customView);
        return customConfiguration.getCustomViewLabel(getAction());
    }

    protected Party getCurrentParty() {
        Long partyId = Long.valueOf(pageState.getPartyId());
        if (partyId == -1) {
            return userSessionContext.getDefaultMessageParty();
        } else {
            return userSessionContext.getMessagePartyById(partyId);
        }
    }

    protected void postProcessGet(ModelAndView modelAndView, BusinessCustomViewName businessName) {
        MessageFormBean formBean = MessageFormBeanFactory.create(businessName, modelAndView.getModelMap());
        modelAndView.addObject("formBean", formBean);
    }

    protected QueryParams buildQueryParams(PageState pageState, Party party, int messageListPageSize) {
        return null;
    }

    protected abstract WebAction getAction();

}
