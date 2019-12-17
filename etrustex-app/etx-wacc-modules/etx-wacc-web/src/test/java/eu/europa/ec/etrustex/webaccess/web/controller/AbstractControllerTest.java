package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.CompatibleBrowserService;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.i18n.DataSourceMessageSource;
import eu.europa.ec.etrustex.webaccess.web.model.ChangeViewFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.Configuration;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.ConfigurationProvider;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataSourceMessageSource.class})
public class AbstractControllerTest extends eu.europa.ec.etrustex.test.support.AbstractControllerTest {

    private static final WebAction ACTION = WebAction.MESSAGE_VIEW_RECEIVED;
    private static final int MANDATORY_ATTR_CNT = 19;
    private QueryParams queryParams = new MessageListQueryParams();

    protected static Set<String> statuses;

    @InjectMocks
    private AbstractController controller = new AbstractController() {
        @Override
        protected WebAction getAction() {
            return ACTION;
        }

        @Override
        protected QueryParams buildQueryParams(PageState pageState, Party party, int messageListPageSize) {
            return queryParams;
        }
    };

    @BeforeClass
    public static void oneTimeSetUp() {
        statuses = new HashSet<>();
        for (MessageListQueryParams.MessageStatus messageStatus : MessageListQueryParams.MessageStatus.values()) {
            statuses.add(messageStatus.name());
        }
    }

    @Mock
    private ConfigurationProvider configurationProvider;

    @Mock
    private UserSessionContext userSessionContext;

    @Mock
    private MailboxManager mailboxManager;

    @Mock
    private SecurityChecker securityChecker;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private AbstractMessageSource messageSource = PowerMockito.mock(DataSourceMessageSource.class);

    @Mock
    protected IcaManager icaManager;

    @Mock
    private CompatibleBrowserService compatibleBrowserService;

    @Test(expected = IllegalArgumentException.class)
    public void test_resolveView_should_throwIllegalArgumentException_when_businessSpecificWebHandlerIsNAAndStandardDisabled() throws Exception {
        String viewName = "business1";

        Party mockParty = mock(Party.class);
        Business business = new Business();
        setCustomViewName(business, viewName);
        when(mockParty.getBusiness()).thenReturn(business);

        when(configurationProvider.isWebHandlerAvailable(viewName, ACTION)).thenReturn(false);
        when(configurationProvider.isGenericViewEnabled(viewName, ACTION)).thenReturn(false);

        // DO THE ACTUAL CALL
        controller.resolveView(AbstractController.CUSTOM, mockParty);
    }

    @Test
    public void test_resolveView_should_resolveCustomToBusinessName_when_businessSpecificWebHandlerIsAvailable() throws Exception {
        String viewName = "businessName2";

        Party mockParty = mock(Party.class);
        Business business = new Business();
        setCustomViewName(business, viewName);
        when(mockParty.getBusiness()).thenReturn(business);

        when(configurationProvider.isWebHandlerAvailable(viewName, ACTION)).thenReturn(true);
        when(configurationProvider.isGenericViewEnabled(viewName, ACTION)).thenReturn(false);

        // DO THE ACTUAL CALL
        String resolvedBusinessName = controller.resolveView(AbstractController.CUSTOM, mockParty);

        assertThat(resolvedBusinessName, is(equalTo(viewName)));
    }

    @Test
    public void test_resolveView_should_resolveToGeneric_when_businessSpecificWebHandlerNotAvailableButGenericEnabled() throws Exception {
        String viewName = "businessName1";

        Party mockParty = mock(Party.class);
        Business business = new Business();
        setCustomViewName(business, viewName);
        when(mockParty.getBusiness()).thenReturn(business);

        when(configurationProvider.isWebHandlerAvailable(viewName, ACTION)).thenReturn(false);
        when(configurationProvider.isGenericViewEnabled(viewName, ACTION)).thenReturn(true);

        // DO THE ACTUAL CALL
        String resolvedBusinessName = controller.resolveView("someStrangeView", mockParty);

        assertThat(resolvedBusinessName, is(equalTo("generic")));
    }


    @Test
    public void test_getAvailableViewsCount_should_return0_when_noBusinessSpecificViewAvailableAndStandardDisabled() throws Exception {
        String viewName = "businessName";

        Party mockParty = mock(Party.class);
        Business business = new Business();
        setCustomViewName(business, viewName);
        when(mockParty.getBusiness()).thenReturn(business);

        when(configurationProvider.isWebHandlerAvailable(viewName, ACTION)).thenReturn(false);
        when(configurationProvider.isGenericViewEnabled(viewName, ACTION)).thenReturn(false);

        // DO THE ACTUAL CALL
        int availableViewsCount = controller.getAvailableViewsCount(mockParty);

        assertThat(availableViewsCount, is(equalTo(0)));
    }

    @Test
    public void test_getAvailableViewsCount_should_return1_when_businessSpecificViewAvailableAndStandardDisabled() throws Exception {
        String viewName = "businessName";

        Party mockParty = mock(Party.class);
        Business business = new Business();
        setCustomViewName(business, viewName);
        when(mockParty.getBusiness()).thenReturn(business);

        when(configurationProvider.isWebHandlerAvailable(viewName, ACTION)).thenReturn(true);
        when(configurationProvider.isGenericViewEnabled(viewName, ACTION)).thenReturn(false);

        // DO THE ACTUAL CALL
        int availableViewsCount = controller.getAvailableViewsCount(mockParty);

        assertThat(availableViewsCount, is(equalTo(1)));
    }

    @Test
    public void test_getAvailableViewsCount_should_return1_when_noBusinessSpecificViewAvailableAndStandardEnabled() throws Exception {
        String viewName = "businessName";

        Party mockParty = mock(Party.class);
        Business business = new Business();
        setCustomViewName(business, viewName);
        when(mockParty.getBusiness()).thenReturn(business);

        when(configurationProvider.isWebHandlerAvailable(viewName, ACTION)).thenReturn(false);
        when(configurationProvider.isGenericViewEnabled(viewName, ACTION)).thenReturn(true);

        // DO THE ACTUAL CALL
        int availableViewsCount = controller.getAvailableViewsCount(mockParty);

        assertThat(availableViewsCount, is(equalTo(1)));
    }

    @Test
    public void test_getAvailableViewsCount_should_return2_when_businessSpecificViewAvailableAndStandardEnabled() throws Exception {
        String viewName = "viewName";

        Party mockParty = mock(Party.class);
        Business business = new Business();
        setCustomViewName(business, viewName);
        when(mockParty.getBusiness()).thenReturn(business);

        when(configurationProvider.isWebHandlerAvailable(viewName, ACTION)).thenReturn(true);
        when(configurationProvider.isGenericViewEnabled(viewName, ACTION)).thenReturn(true);

        // DO THE ACTUAL CALL
        int availableViewsCount = controller.getAvailableViewsCount(mockParty);

        assertThat(availableViewsCount, is(equalTo(2)));
    }

    @Test
    public void test_doGet_should_setAllCommonAttributes_when_canSend() throws Exception {
        Map<String, String[]> requestMap = new HashMap<>();
        String view = "custom";
        int availableViewsCnt = 1;
        long incomingCnt = 3;
        long draftCnt = 13;
        requestMap.put("v", new String[]{view});

        eu.europa.ec.etrustex.webaccess.business.config.Configuration configuration = new eu.europa.ec.etrustex.webaccess.business.config.Configuration();
        when(configurationService.getConfiguration()).thenReturn(configuration);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        WebHandler mockWebHandler = mock(WebHandler.class);
        Configuration mockConfiguration = mock(Configuration.class);
        when(mockConfiguration.getWebHandler(ACTION)).thenReturn(mockWebHandler);
        when(mockRequest.getParameterMap()).thenReturn(requestMap);
        when(mockRequest.getRequestURL()).thenReturn(new StringBuffer("fakeUrl"));

        User mockUser = mock(User.class);
        Party party = new Party();
        Business business = new Business();
        business.setId(11L);
        BusinessConfig bc = new BusinessConfig();
        bc.setBusinessId(11L);
        bc.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        bc.setPropertyValue("edma");
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        business.getBusinessConfigs().add(bc);
        party.setBusiness(business);

        controller.pageState = new PageState();
        when(configurationProvider.getConfiguration(view)).thenReturn(mockConfiguration);
        when(userSessionContext.getUser()).thenReturn(mockUser);
        when(mailboxManager.getUnreadCount(mockUser, party, Message.MessageState.INCOMING)).thenReturn(incomingCnt);
        when(mailboxManager.getUnreadCount(mockUser, party, Message.MessageState.DRAFT)).thenReturn(draftCnt);
        when(securityChecker.hasPrivilege(Privilege.Type.CAN_ASSIGN_PARTY_SCOPE_ROLES)).thenReturn(true);
        when(securityChecker.canSend(party)).thenReturn(true);

        AbstractController spy = spy(controller);
        doReturn(view).when(spy).resolveView(view, party);
        doReturn(availableViewsCnt).when(spy).getAvailableViewsCount(party);
        doReturn(party).when(spy).getCurrentParty();
        String userName = "bucname";
        when(userSessionContext.getBusinessUserNameByParty()).thenReturn(Collections.singletonMap(party.getId(), userName));

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doGet(mockRequest);

        assertThat(modelAndView, is(notNullValue()));
        assertThat(modelAndView.getViewName(), is(equalTo(view + "/" + ACTION.getName())));

        Map<String, Object> model = modelAndView.getModel();
        assertThat((PageState) model.get("ps"), is(sameInstance(controller.pageState)));
        assertThat((String) model.get("action"), is(equalTo(ACTION.getDo())));
        assertThat((Integer) model.get("viewCount"), is(equalTo(availableViewsCnt)));
        assertThat((Integer) model.get("sendAbility"), is(equalTo(1)));
        assertThat((Long) model.get("unreadCountIncoming"), is(equalTo(incomingCnt)));
        assertThat((Long) model.get("unreadCountDraft"), is(equalTo(draftCnt)));
        ChangeViewFormBean changeViewFormBean = (ChangeViewFormBean) model.get("changeViewFormBean");
        assertThat((Boolean) modelAndView.getModel().get("isAdminLinkEnabled"), is(true));
        assertThat(changeViewFormBean, is(notNullValue(ChangeViewFormBean.class)));
        assertThat(changeViewFormBean.getView(), is(equalTo(view)));
        assertThat((String) model.get("businessUserName"), is(equalTo(userName)));
        assertThat(model.get("statuses"), is(notNullValue()));
        assertThat(((Map<String, String>) model.get("statuses")).keySet(), containsInAnyOrder(statuses.toArray()));

        assertThat((User) model.get("user"), is(sameInstance(mockUser)));
        assertThat((UserSessionContext) model.get("userSessionContext"), is(sameInstance(userSessionContext)));

        assertThat(model.size(), is(MANDATORY_ATTR_CNT+2));
        assertThat(party.getBusiness(), is(business));
        assertThat(party.getBusiness().getBusinessConfigs().size(), is(1));
    }


    @Test
    public void test_doGet_should_setAllButDraftsCountCommonAttributes_when_cantSend() throws Exception {
        Map<String, String[]> requestMap = new HashMap<>();
        String view = "custom";
        int availableViewsCnt = 1;
        int canSend = 0;
        long incomingCnt = 3;
        long draftCnt = 13;
        requestMap.put("v", new String[]{view});

        eu.europa.ec.etrustex.webaccess.business.config.Configuration configuration = new eu.europa.ec.etrustex.webaccess.business.config.Configuration();
        when(configurationService.getConfiguration()).thenReturn(configuration);

        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        WebHandler mockWebHandler = mock(WebHandler.class);
        Configuration mockBusinesView = mock(Configuration.class);
        when(mockBusinesView.getWebHandler(ACTION)).thenReturn(mockWebHandler);
        when(requestMock.getParameterMap()).thenReturn(requestMap);
        when(requestMock.getRequestURL()).thenReturn(new StringBuffer("fakeUrl"));


        User mockUser = mock(User.class);
        Party party = new Party();
        Business business = new Business();
        business.setId(11L);
        BusinessConfig bc = new BusinessConfig();
        bc.setBusinessId(11L);
        bc.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        bc.setPropertyValue("edma");
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        business.getBusinessConfigs().add(bc);
        party.setBusiness(business);

        controller.pageState = new PageState();
        when(configurationProvider.getConfiguration(view)).thenReturn(mockBusinesView);
        when(userSessionContext.getUser()).thenReturn(mockUser);
        when(mailboxManager.getUnreadCount(mockUser, party, Message.MessageState.INCOMING)).thenReturn(incomingCnt);
        when(mailboxManager.getUnreadCount(mockUser, party, Message.MessageState.DRAFT)).thenReturn(draftCnt);

        AbstractController spy = spy(controller);
        doReturn(view).when(spy).resolveView(view, party);
        doReturn(availableViewsCnt).when(spy).getAvailableViewsCount(party);
        doReturn(party).when(spy).getCurrentParty();

        String userName = "bucname";
        when(userSessionContext.getBusinessUserNameByParty()).thenReturn(Collections.singletonMap(party.getId(), userName));

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doGet(requestMock);

        assertThat(modelAndView, is(notNullValue()));
        assertThat(modelAndView.getViewName(), is(equalTo(view + "/" + ACTION.getName())));

        Map<String, Object> model = modelAndView.getModel();
        assertThat((PageState) model.get("ps"), is(sameInstance(controller.pageState)));
        assertThat((String) model.get("action"), is(equalTo(ACTION.getDo())));
        assertThat((Integer) model.get("viewCount"), is(equalTo(availableViewsCnt)));
        assertThat((Integer) model.get("sendAbility"), is(equalTo(canSend)));
        assertThat((Long) model.get("unreadCountIncoming"), is(equalTo(incomingCnt)));
        assertThat(model.get("unreadCountDraft"), is(nullValue()));
        ChangeViewFormBean changeViewFormBean = (ChangeViewFormBean) model.get("changeViewFormBean");
        assertThat(changeViewFormBean, is(notNullValue(ChangeViewFormBean.class)));
        assertThat(changeViewFormBean.getView(), is(equalTo(view)));
        assertThat((User) model.get("user"), is(sameInstance(mockUser)));
        assertThat((UserSessionContext) model.get("userSessionContext"), is(sameInstance(userSessionContext)));
        assertThat((String) model.get("businessUserName"), is(equalTo(userName)));
        assertThat(model.get("statuses"), is(notNullValue()));
        assertThat(((Map<String, String>) model.get("statuses")).keySet(), containsInAnyOrder(statuses.toArray()));
        assertThat(model.size(), is(MANDATORY_ATTR_CNT+1));
    }

    @Test
    public void test_doGet_should_setBusinessAttributes() throws Exception {
        Map<String, String[]> requestMap = new HashMap<>();
        String view = "custom";
        int availableViewsCnt = 1;
        requestMap.put("v", new String[]{view});

        Map<String, Object> businessMap = new HashMap<>();
        businessMap.put("b1", "b11");
        businessMap.put("b3", 44L);

        eu.europa.ec.etrustex.webaccess.business.config.Configuration configuration = new eu.europa.ec.etrustex.webaccess.business.config.Configuration();
        configuration.setRetentionPolicyWeeks(6);
        when(configurationService.getConfiguration()).thenReturn(configuration);

        WebHandler mockWebHandler = mock(WebHandler.class);
        when(mockWebHandler.buildBusinessModel(queryParams)).thenReturn(businessMap);
        Configuration mockBusinesView = mock(Configuration.class);
        when(mockBusinesView.getWebHandler(ACTION)).thenReturn(mockWebHandler);
        when(configurationProvider.getConfiguration(view)).thenReturn(mockBusinesView);

        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameterMap()).thenReturn(requestMap);
        when(requestMock.getRequestURL()).thenReturn(new StringBuffer("fakeUrl"));


        User mockUser = mock(User.class);
        Party party = new Party();
        Business business = new Business();
        business.setId(11L);
        BusinessConfig bc = new BusinessConfig();
        bc.setBusinessId(11L);
        bc.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        bc.setPropertyValue("edma");
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        business.getBusinessConfigs().add(bc);
        party.setBusiness(business);

        controller.pageState = new PageState();
        when(userSessionContext.getUser()).thenReturn(mockUser);
        when(securityChecker.canSend(party)).thenReturn(true);

        AbstractController spy = spy(controller);
        doReturn(view).when(spy).resolveView(view, party);
        doReturn(availableViewsCnt).when(spy).getAvailableViewsCount(party);
        doReturn(party).when(spy).getCurrentParty();

        when(userSessionContext.getBusinessUserNameByParty()).thenReturn(Collections.singletonMap(party.getId(), ""));

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doGet(requestMock);

        assertThat((String) modelAndView.getModel().get("b1"), is(equalTo("b11")));
        assertThat((Long) modelAndView.getModel().get("b3"), is(equalTo(44L)));
        assertThat((Integer) modelAndView.getModel().get("retentionPolicyWeeks"), is(equalTo(6)));
        assertThat(modelAndView.getModel().get("statuses"), is(notNullValue()));

        assertThat(modelAndView.getModel().size(), is(MANDATORY_ATTR_CNT + 4));
        assertThat(party.getBusiness(), is(business));
        assertThat(party.getBusiness().getBusinessConfigs().size(), is(1));
    }

    private void setCustomViewName(Business business, String viewName) {
        if (business.getBusinessConfigs() == null) {
            business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        }
        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(viewName);

        business.getBusinessConfigs().add(businessConfig);
    }

    @Test
    public void test_doGet_should_setCustomViewLable_when_bothViewsAvailable() throws Exception {
        Map<String, String[]> requestMap = new HashMap<>();
        String view = "custom";
        String label = "b11";
        int availableViewsCnt = 2;
        requestMap.put("v", new String[]{view});

        eu.europa.ec.etrustex.webaccess.business.config.Configuration configuration = new eu.europa.ec.etrustex.webaccess.business.config.Configuration();
        configuration.setRetentionPolicyWeeks(6);
        when(configurationService.getConfiguration()).thenReturn(configuration);

        WebHandler mockWebHandler = mock(WebHandler.class);
        Configuration mockBusinesView = mock(Configuration.class);
        when(mockBusinesView.getWebHandler(ACTION)).thenReturn(mockWebHandler);
        when(configurationProvider.getConfiguration(view)).thenReturn(mockBusinesView);

        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameterMap()).thenReturn(requestMap);
        when(requestMock.getRequestURL()).thenReturn(new StringBuffer("fakeUrl"));


        User mockUser = mock(User.class);
        Party party = new Party();
        Business business = new Business();
        business.setId(11L);
        BusinessConfig bc = new BusinessConfig();
        bc.setBusinessId(11L);
        bc.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        bc.setPropertyValue("edma");
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        business.getBusinessConfigs().add(bc);
        party.setBusiness(business);

        controller.pageState = new PageState();
        when(userSessionContext.getUser()).thenReturn(mockUser);
        when(securityChecker.canSend(party)).thenReturn(true);

        AbstractController spy = spy(controller);
        doReturn(view).when(spy).resolveView(view, party);
        doReturn(availableViewsCnt).when(spy).getAvailableViewsCount(party);
        doReturn(label).when(spy).getCustomViewLabel(party);

        when(userSessionContext.getBusinessUserNameByParty()).thenReturn(Collections.singletonMap(party.getId(), ""));
        doReturn(party).when(spy).getCurrentParty();

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doGet(requestMock);

        assertThat((String) modelAndView.getModel().get("customViewLabel"), is(equalTo(label)));
        assertThat((Integer) modelAndView.getModel().get("retentionPolicyWeeks"), is(equalTo(6)));

        assertThat(modelAndView.getModel().size(), is(MANDATORY_ATTR_CNT + 3));
        assertThat(party.getBusiness(), is(business));
        assertThat(party.getBusiness().getBusinessConfigs().size(), is(1));
    }

    @Test
    public void test_doGet_should_403_when_noCurrentParty() throws Exception {
        Map<String, String[]> requestMap = new HashMap<>();
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getParameterMap()).thenReturn(requestMap);
        controller.pageState = new PageState();

        AbstractController spy = spy(controller);
        doReturn(null).when(spy).getCurrentParty();

        User loggedInUser = mock(User.class);

        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(loggedInUser.getLogin()).thenReturn("testUsr");
        when(requestMock.getRequestURI()).thenReturn("/e-trustex/welcome.do");
        when(requestMock.getQueryString()).thenReturn("test=10");
        when(requestMock.getRequestURL()).thenReturn(new StringBuffer("fakeUrl"));


        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doGet(requestMock);

        assertThat(((RedirectViewWithLogging) modelAndView.getView()).getUrl(), is(equalTo("error/error403.do")));
    }

    @Test
    public void test_doGet_should_setNewParty_when_getCurrentPartyReturnsParty() throws Exception {
        Map<String, String[]> requestMap = new HashMap<>();
        String view = "custom";
        int availableViewsCnt = 1;
        long incomingCnt = 3;
        long draftCnt = 13;
        long partyId = 4L;
        requestMap.put("v", new String[]{view});

        eu.europa.ec.etrustex.webaccess.business.config.Configuration configuration = new eu.europa.ec.etrustex.webaccess.business.config.Configuration();
        configuration.setRetentionPolicyWeeks(6);
        when(configurationService.getConfiguration()).thenReturn(configuration);

        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        WebHandler mockWebHandler = mock(WebHandler.class);
        Configuration mockBusinesView = mock(Configuration.class);
        when(mockBusinesView.getWebHandler(ACTION)).thenReturn(mockWebHandler);
        when(requestMock.getParameterMap()).thenReturn(requestMap);
        when(requestMock.getRequestURL()).thenReturn(new StringBuffer("fakeUrl"));


        User mockUser = mock(User.class);
        Party party = new Party();
        party.setId(partyId);
        Business business = new Business();
        party.setBusiness(business);
        business.setId(11L);
        BusinessConfig bc = new BusinessConfig();
        bc.setBusinessId(11L);
        bc.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        bc.setPropertyValue("edma");
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        business.getBusinessConfigs().add(bc);

        controller.pageState = new PageState();
        when(configurationProvider.getConfiguration(view)).thenReturn(mockBusinesView);
        when(userSessionContext.getUser()).thenReturn(mockUser);
        when(mailboxManager.getUnreadCount(mockUser, party, Message.MessageState.INCOMING)).thenReturn(incomingCnt);
        when(mailboxManager.getUnreadCount(mockUser, party, Message.MessageState.DRAFT)).thenReturn(draftCnt);

        AbstractController spy = spy(controller);
        doReturn(view).when(spy).resolveView(view, party);
        doReturn(availableViewsCnt).when(spy).getAvailableViewsCount(party);
        doReturn(party).when(spy).getCurrentParty();
        when(userSessionContext.getBusinessUserNameByParty()).thenReturn(Collections.singletonMap(party.getId(), ""));

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doGet(requestMock);

        assertThat(controller.pageState.getPartyId(), is(equalTo(party.getId().toString())));
        assertThat((Integer) modelAndView.getModel().get("retentionPolicyWeeks"), is(equalTo(6)));
    }

    @Test
    public void test_doChangeView_should_addViewToModelAndView() throws Exception {
        String view = "v13";
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        ChangeViewFormBean formBean = new ChangeViewFormBean();
        formBean.setView(view);

        controller.pageState = new PageState();

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = controller.doChangeView(formBean, mockRequest);

        assertThat((String) modelAndView.getModel().get(PageState.Param.VIEW.getName()), is(equalTo(view)));
    }

    @Test
    public void test_doChangeView_should_propagateRequestAttributes() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        ChangeViewFormBean formBean = new ChangeViewFormBean();

        controller.pageState = new PageState();

        String subject = "subjectFromPS";
        String backAction = "someActionFromPS";
        String backView = "backView";
        String sortDirection = "ASC";
        Map<String, String[]> requestMap = new HashMap<>();
        requestMap.put(PageState.Param.SUBJECT.getName(), new String[]{subject});
        requestMap.put(PageState.Param.BACK_ACTION_DO.getName(), new String[]{backAction});
        requestMap.put(PageState.Param.SORT_DIRECTION.getName(), new String[]{sortDirection});
        requestMap.put(PageState.Param.BACK_VIEW.getName(), new String[]{backView});
        when(mockRequest.getParameterMap()).thenReturn(requestMap);

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = controller.doChangeView(formBean, mockRequest);

        assertThat((String) modelAndView.getModel().get(PageState.Param.SUBJECT.getName()), is(equalTo(subject)));
        assertThat((String) modelAndView.getModel().get(PageState.Param.SORT_DIRECTION.getName()), is(equalTo(sortDirection)));
        assertThat((String) modelAndView.getModel().get(PageState.Param.BACK_ACTION_DO.getName()), is(equalTo(backAction)));
        assertThat((String) modelAndView.getModel().get(PageState.Param.BACK_VIEW.getName()), is(equalTo(backView)));
        assertThat(modelAndView.getModel().size(), is(4 + 1));
    }

    @Test
    public void test_getCustomViewLabel_should_lookForLabelInCustomConfiguration() throws Exception {
        String view = "edma";
        String label = "b11";
        Party party = new Party();

        Configuration mockConfiguration = mock(Configuration.class);
        when(configurationProvider.getConfiguration(view)).thenReturn(mockConfiguration);
        when(mockConfiguration.getCustomViewLabel(ACTION)).thenReturn(label);

        AbstractController spy = spy(controller);
        doReturn(view).when(spy).resolveView(AbstractController.CUSTOM, party);

        // DO THE ACTUAL CALL
        String result = spy.getCustomViewLabel(party);

        assertThat(result, is(equalTo(label)));

        verify(spy).resolveView(AbstractController.CUSTOM, party);
        verify(spy).getCustomViewLabel(party);
        verify(spy).getAction();
        verifyNoMoreInteractions(spy);

        verify(configurationProvider).getConfiguration(view);
        verifyNoMoreInteractions(configurationProvider);
    }

    @Test
    public void test_getCurrentParty_should_returnDefaultParty_when_noMessageIdSupplied() throws Exception {
        Party party = new Party();

        controller.pageState = new PageState();

        when(userSessionContext.getDefaultMessageParty()).thenReturn(party);

        //ACTUAL CALL
        Party result = controller.getCurrentParty();

        assertThat(result, is(sameInstance(party)));
    }

    @Test
    public void test_getCurrentParty_should_returnParty_when_messageIdSupplied() throws Exception {
        long partyId = 54L;
        Party party = new Party();

        controller.pageState = new PageState();
        controller.pageState.setPartyId(partyId);

        when(userSessionContext.getMessagePartyById(partyId)).thenReturn(party);

        //ACTUAL CALL
        Party result = controller.getCurrentParty();

        assertThat(result, is(sameInstance(party)));
    }
}
