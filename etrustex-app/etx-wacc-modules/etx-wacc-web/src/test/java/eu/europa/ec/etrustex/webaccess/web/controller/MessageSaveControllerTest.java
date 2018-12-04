package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.business.api.IcaDetailsService;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.facade.BusinessFacade;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import eu.europa.ec.etrustex.webaccess.model.vo.IcaDetailsVO;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.model.vo.SignatureVO;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.utils.applet.Encoder;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.MessageFormBean;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.utils.MessageUtils;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.WebHandlerHelper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class MessageSaveControllerTest extends AbstractControllerTest {

    @Mock
    protected BusinessFacade businessFacade;
    @Mock
    protected UserSessionContext userSessionContext;
    @Mock
    protected MessageUtils messageUtils;
    @Mock
    protected SecurityChecker securityChecker;
    @Mock
    protected MailboxManager mailboxManager;
    @Mock
    protected PageState pageState;
    @Mock
    protected PartyManager partyManager;
    private User user = new User();
    @InjectMocks
    private MessageSaveController controller;
    @Mock
    private PartyPropertyEditor partyPropertyEditor;
    @Mock
    private IcaManager icaManager;
    @Mock
    private IcaDetailsService icaDetailsService;
    @Mock
    private WebHandlerHelper webHandlerHelper;

    @Test
    public void test_isAuthorized_should_returnFalse_when_partyNotAccessible() throws Exception {
        Party party = new Party();
        Message message = new Message();
        message.setLocalParty(party);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(false));
    }

    @Test
    public void test_canAccessMessagesOfParty_should_returnFalse_when_partyNotAccessible() throws Exception {
        Party party = new Party();

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = controller.securityChecker.canAccessMessagesOfParty(party);

        assertThat(result, is(false));
    }

    @Test
    public void test_update_isAuthorized_should_returnFalse_when_messageNotFound() throws Exception {
        long messageId = 5L;
        Party party = new Party();
        Message message = new Message();
        message.setId(messageId);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);
        when(mailboxManager.getMessageByMessageId(messageId)).thenReturn(null);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(false));
    }

    @Test
    public void test_update_isAuthorized_should_returnFalse_when_messageIsNotDRAFT() throws Exception {
        long messageId = 5L;
        Party party = new Party();
        Message message = new Message();
        message.setId(messageId);
        message.setLocalParty(party);
        message.setMsgState(Message.MessageState.SENT);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);
        when(mailboxManager.getMessageByMessageId(messageId)).thenReturn(message);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(false));
    }

    @Test
    public void test_update_doSaveSentMessage_should_saveMessage() throws Exception {
        Long messageId = 4L;
        Long partyId = 5L;

        controller.pageState = new PageState();

        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        Business business = new Business();
        business.setId(1L);
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");
        message.setRemoteParty(remoteParty);

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue("generic");

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        party.setId(partyId);
        message.setLocalParty(party);

        List<Long> uploadedFiles = null;

        SignatureVO signature = null;

        MessageFormBean formBean = new MessageFormBean();
        //formBean.setEncodedSignature(null);
        formBean.setMessage(message);
        formBean.setMessageMetadata(null);

        when(userSessionContext.getUser()).thenReturn(user);
        when(businessFacade.saveOrUpdateMessage(argThat(is(sameInstance(message))), argThat(is(uploadedFiles)), (Long) argThat(is(nullValue())), (String) argThat(is(nullValue())), argThat(is(signature)), argThat(sameInstance(user)))).thenReturn(message);
        when(mailboxManager.getMessageByMessageId(formBean.getMessage().getId())).thenReturn(message);

        MessageSaveController spy = spy(controller);
        doReturn(true).when(spy).isAuthorized(message);

        when(partyManager.getRemotePartyByNodeNameBusId(remoteParty.getNodeName(), party.getBusiness().getId())).thenReturn(remoteParty);

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doSaveSentMessage(formBean, request);

        RedirectView redirectView = (RedirectView) modelAndView.getView();
        assertThat(redirectView.getUrl(), is("inbox.do"));
        assertThat(modelAndView.getModelMap().size(), is(equalTo(2)));
        assertThat(modelAndView.getModelMap(), Matchers.<String, Object>hasEntry(equalTo("nso"), Matchers.<Object>equalTo("true")));
        assertThat(modelAndView.getModelMap(), Matchers.<String, Object>hasEntry(equalTo("pid"), Matchers.<Object>equalTo(partyId.toString())));

        verify(messageUtils).limitToMessageMaxSize(message);
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(businessFacade).saveOrUpdateMessage(messageArgumentCaptor.capture(), eq(uploadedFiles), (Long) argThat(is(nullValue())), (String) argThat(is(nullValue())), eq(signature), eq(user));
        Message actualMessage = messageArgumentCaptor.getValue();
        assertThat(actualMessage.getMsgState(), is(equalTo(Message.MessageState.IN_PREPARATION)));
        verifyNoMoreInteractions(businessFacade);

        assertThat(actualMessage.getRemoteParty(), is(equalTo(remoteParty)));

        verify(partyManager).getRemotePartyByNodeNameBusId(remoteParty.getNodeName(), party.getBusiness().getId());
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_update_doSaveDraftMessage_should_saveMessage() throws Exception {
        Long messageId = 3L;
        SignatureVO signature = null;

        controller.pageState = new PageState();

        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        party.setNodeName("localParty");
        Business business = new Business();
        business.setId(1L);
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue("generic");

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");
        message.setRemoteParty(remoteParty);

        List<Long> uploadedFiles = null;
        MessageFormBean formBean = new MessageFormBean();
        formBean.setMessage(message);
        //formBean.setEncodedSignature(Encoder.encode(Collections.singletonList(signature)));


        when(userSessionContext.getUser()).thenReturn(user);
        when(businessFacade.saveOrUpdateMessage(argThat(is(sameInstance(message))), argThat(is(uploadedFiles)), (Long) argThat(is(nullValue())), (String) argThat(is(nullValue())), argThat(is(signature)), argThat(sameInstance(user)))).thenReturn(message);
        when(mailboxManager.getMessageByMessageId(formBean.getMessage().getId())).thenReturn(message);

        MessageSaveController spy = spy(controller);
        doReturn(true).when(spy).isAuthorized(message);

        when(partyManager.getRemotePartyByNodeNameBusId(remoteParty.getNodeName(), party.getBusiness().getId())).thenReturn(remoteParty);

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doSaveDraftMessage(formBean, request);

        RedirectView redirectView = (RedirectView) modelAndView.getView();
        assertThat(redirectView.getUrl(), is("messageEdit.do"));
        assertThat(modelAndView.getModelMap().size(), is(equalTo(3)));
        assertThat(modelAndView.getModelMap(), Matchers.<String, Object>hasEntry(equalTo("nso"), Matchers.<Object>equalTo("true")));
        assertThat(modelAndView.getModelMap(), Matchers.<String, Object>hasEntry(equalTo("mid"), Matchers.<Object>equalTo(messageId.toString())));

        verify(messageUtils).limitToMessageMaxSize(message);
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(businessFacade).saveOrUpdateMessage(messageArgumentCaptor.capture(), eq(uploadedFiles), (Long) argThat(is(nullValue())), (String) isNull(), eq(signature), eq(user));
        Message actualMessage = messageArgumentCaptor.getValue();
        assertThat(actualMessage.getMsgState(), is(equalTo(Message.MessageState.DRAFT)));
        verifyNoMoreInteractions(businessFacade);

        assertThat(actualMessage.getRemoteParty(), is(equalTo(remoteParty)));

        verify(partyManager).getRemotePartyByNodeNameBusId(remoteParty.getNodeName(), party.getBusiness().getId());
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_doSaveDraftMessage_should_saveMessage() throws Exception {
        Long messageId = null;
        SignatureVO signature = null;

        controller.pageState = new PageState();

        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        party.setNodeName("localParty");
        Business business = new Business();
        business.setId(1L);
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue("generic");

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");
        message.setRemoteParty(remoteParty);

        List<Long> uploadedFiles = null;
        MessageFormBean formBean = new MessageFormBean();
        formBean.setMessage(message);
        //formBean.setEncodedSignature(Encoder.encode(Collections.singletonList(signature)));


        when(userSessionContext.getUser()).thenReturn(user);
        Message savedMessage = new Message();
        savedMessage.setId(4L);
        savedMessage.setLocalParty(party);
        when(businessFacade.saveOrUpdateMessage(argThat(is(sameInstance(message))), argThat(is(uploadedFiles)), (Long) argThat(is(nullValue())), (String) argThat(is(nullValue())), argThat(is(signature)), argThat(sameInstance(user)))).thenReturn(savedMessage);

        MessageSaveController spy = spy(controller);
        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        when(partyManager.getRemotePartyByNodeNameBusId(remoteParty.getNodeName(), party.getBusiness().getId())).thenReturn(remoteParty);

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doSaveDraftMessage(formBean, request);

        RedirectView redirectView = (RedirectView) modelAndView.getView();
        assertThat(redirectView.getUrl(), is("messageEdit.do"));
        assertThat(modelAndView.getModelMap().size(), is(equalTo(3)));
        assertThat(modelAndView.getModelMap(), Matchers.<String, Object>hasEntry(equalTo("nso"), Matchers.<Object>equalTo("true")));

        verify(messageUtils).limitToMessageMaxSize(message);
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(businessFacade).saveOrUpdateMessage(messageArgumentCaptor.capture(), eq(uploadedFiles), (Long) argThat(is(nullValue())), (String) isNull(), eq(signature), eq(user));
        Message actualMessage = messageArgumentCaptor.getValue();
        assertThat(actualMessage.getMsgState(), is(equalTo(Message.MessageState.DRAFT)));
        verifyNoMoreInteractions(businessFacade);

        assertThat(actualMessage.getRemoteParty(), is(equalTo(remoteParty)));

        verify(partyManager).getRemotePartyByNodeNameBusId(remoteParty.getNodeName(), party.getBusiness().getId());
        verifyNoMoreInteractions(partyManager);
    }

    @Test
    public void test_doSaveDraftMessage_EDMA_should_saveMessage() throws Exception {
        Long messageId = null;
        SignatureVO signature = null;

        controller.pageState = new PageState();

        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        party.setNodeName("localParty");
        Business business = new Business();
        business.setId(1L);
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");
        message.setRemoteParty(remoteParty);

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue("edma");


        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        List<Long> uploadedFiles = null;
        MessageFormBean formBean = new MessageFormBean();
        formBean.setMessage(message);
        //formBean.setEncodedSignature(Encoder.encode(Collections.singletonList(signature)));

        formBean.setMessageMetadata("{\"subject\":\"Test\"}");

        when(userSessionContext.getUser()).thenReturn(user);
        Message savedMessage = new Message();
        savedMessage.setId(4L);
        savedMessage.setLocalParty(party);
        when(businessFacade.saveOrUpdateMessage(argThat(is(sameInstance(message))), argThat(is(uploadedFiles)), (Long) argThat(is(nullValue())), (String) isNotNull(), argThat(is(signature)), argThat(sameInstance(user)))).thenReturn(savedMessage);

        MessageSaveController spy = spy(controller);
        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        when(partyManager.getRemotePartyByNodeNameBusId(remoteParty.getNodeName(), party.getBusiness().getId())).thenReturn(remoteParty);

        // DO THE ACTUAL CALL
        ModelAndView modelAndView = spy.doSaveDraftMessage(formBean, request);

        RedirectView redirectView = (RedirectView) modelAndView.getView();
        assertThat(redirectView.getUrl(), is("messageEdit.do"));
        assertThat(modelAndView.getModelMap().size(), is(equalTo(3)));
        assertThat(modelAndView.getModelMap(), Matchers.<String, Object>hasEntry(equalTo("nso"), Matchers.<Object>equalTo("true")));

        verify(messageUtils).limitToMessageMaxSize(message);
        ArgumentCaptor<Message> messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        verify(businessFacade).saveOrUpdateMessage(messageArgumentCaptor.capture(), eq(uploadedFiles), (Long) argThat(is(nullValue())), (String) isNotNull(), eq(signature), eq(user));
        Message actualMessage = messageArgumentCaptor.getValue();
        assertThat(actualMessage.getMsgState(), is(equalTo(Message.MessageState.DRAFT)));
        verifyNoMoreInteractions(businessFacade);

        verify(partyManager).getRemotePartyByNodeNameBusId(remoteParty.getNodeName(), party.getBusiness().getId());
        verifyNoMoreInteractions(partyManager);
    }

    @Test(expected = EtxException.class)
    public void test_doSaveDraftMessage_EDMA_wrongMetadataFormat_should_notBeSaved() throws Exception {
        Long messageId = null;
        SignatureVO signature = null;

        controller.pageState = new PageState();

        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue("edma");

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        MessageFormBean formBean = new MessageFormBean();
        formBean.setMessage(message);
        //formBean.setEncodedSignature(Encoder.encode(Collections.singletonList(signature)));

        formBean.setMessageMetadata("");


        when(userSessionContext.getUser()).thenReturn(user);
        Message savedMessage = new Message();
        savedMessage.setId(4L);
        savedMessage.setLocalParty(party);

        MessageSaveController spy = spy(controller);
        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        // DO THE ACTUAL CALL
        spy.doSaveDraftMessage(formBean, request);

    }

    @Test
    public void test_update_doSaveDraftMessage_should_403_when_notAuthorized() throws Exception {
        Long messageId = 3L;

        MessageFormBean formBean = new MessageFormBean();

        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        message.setLocalParty(party);
        formBean.setMessage(message);

        MessageSaveController spy = spy(controller);
        doReturn(false).when(spy).isAuthorized(message);

        User loggedInUser = mock(User.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getScheme()).thenReturn("http");
        when(mockRequest.getServerName()).thenReturn("127.0.0.1");
        when(mockRequest.getServerPort()).thenReturn(7001);
        when(mockRequest.getRequestURI()).thenReturn("/e-trustex/messageEdit.do");
        when(mockRequest.getQueryString()).thenReturn("test=10");
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(loggedInUser.getLogin()).thenReturn("testUsr");

        //ACTUAL CALL
        ModelAndView modelAndView = spy.doSaveDraftMessage(formBean, mockRequest);

        assertThat(((RedirectView) modelAndView.getView()).getUrl(), is(equalTo("error/error403.do")));
    }

    @Test
    public void test_doSaveDraftMessage_should_403_when_notAuthorized() throws Exception {
        Long messageId = null;

        MessageFormBean formBean = new MessageFormBean();

        Message message = new Message();
        message.setId(messageId);
        Party party = null;
        message.setLocalParty(party);
        formBean.setMessage(message);

        MessageSaveController spy = spy(controller);
        when(message.getLocalParty()).thenReturn(party);
        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(false);

        User loggedInUser = mock(User.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getRequestURI()).thenReturn("/e-trustex/messageEdit.do");
        when(mockRequest.getQueryString()).thenReturn("test=10");
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(loggedInUser.getLogin()).thenReturn("testUsr");

        //ACTUAL CALL
        ModelAndView modelAndView = spy.doSaveDraftMessage(formBean, mockRequest);

        assertThat(((RedirectViewWithLogging) modelAndView.getView()).getUrl(), is(equalTo("error/error403.do")));
    }

    @Test
    public void test_update_doSaveSentMessage_should_403_when_notAuthorized() throws Exception {
        Long messageId = 3L;

        MessageFormBean formBean = new MessageFormBean();

        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        message.setLocalParty(party);
        formBean.setMessage(message);

        MessageSaveController spy = spy(controller);
        doReturn(false).when(spy).isAuthorized(message);

        User loggedInUser = mock(User.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getRequestURI()).thenReturn("/e-trustex/messageEdit.do");
        when(mockRequest.getQueryString()).thenReturn("test=10");
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(loggedInUser.getLogin()).thenReturn("testUsr");

        //ACTUAL CALL
        ModelAndView modelAndView = spy.doSaveSentMessage(formBean, mockRequest);

        assertThat(((RedirectViewWithLogging) modelAndView.getView()).getUrl(), is(equalTo("error/error403.do")));

    }

    @Test
    public void test_doSaveSentMessage_should_403_when_notAuthorized() throws Exception {
        Long messageId = 10L;

        MessageFormBean formBean = new MessageFormBean();

        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        message.setLocalParty(party);
        formBean.setMessage(message);

        MessageSaveController spy = spy(controller);
        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(false);

        User loggedInUser = mock(User.class);
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getRequestURI()).thenReturn("/e-trustex/messageEdit.do");
        when(mockRequest.getQueryString()).thenReturn("test=10");
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(loggedInUser.getLogin()).thenReturn("testUsr");

        //ACTUAL CALL
        ModelAndView modelAndView = spy.doSaveSentMessage(formBean, mockRequest);
        assertThat(((RedirectViewWithLogging) modelAndView.getView()).getUrl(), is(equalTo("error/error403.do")));

    }

    @Test
    public void test_doSaveSentMessage_with_messageLength_of_4000() throws Exception {
        Long messageId = 6L;
        String subject = CharBuffer.allocate(4000).toString().replace('\0', ' ');
        Message message = new Message();
        message.setId(messageId);
        message.setSubject(subject);
        message.setContent("");
        MessageUtils messageUtils = new MessageUtils();
        messageUtils.limitToMessageMaxSize(message);
        assertThat(message.getSubject().length(), is(equalTo(4000)));

    }
    @Test
    public void test_initBinderAll_should_registerPartyCustomEditor() {
        WebDataBinder binder = mock(WebDataBinder.class);

        //DO THE ACTUAL CALL
        controller.initBinderAll(binder);

        verify(binder).registerCustomEditor(argThat(is(equalTo(Party.class))), argThat(is(sameInstance(partyPropertyEditor))));
        verifyNoMoreInteractions(binder);
    }

    @Test
    public void test_doReloadMessageIca_no_Certificate() {
        String party1 = "party1";
        String party2 = "party2";
        PartyIca partyIca = new PartyIca();
        Date date = new Date();

        NodeIcaDetails nodeIcaDetails = mock(NodeIcaDetails.class);
        when(nodeIcaDetails.getLocalParty()).thenReturn(party1);
        when(nodeIcaDetails.getRemoteParty()).thenReturn(party2);
        when(nodeIcaDetails.getExtendedCertificateData()).thenReturn(null);
        when(nodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.PUBLIC);
        when(nodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.MODERATE);
        when(nodeIcaDetails.getCreationDate()).thenReturn(date);
        when(nodeIcaDetails.isActiveState()).thenReturn(true);

        when(icaManager.getActiveIcaBySenderReceiver(party1, party2)).thenReturn(partyIca);
        when(icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false)).thenReturn(nodeIcaDetails);

        //Do the actual call
        IcaDetailsVO icaDetailsVO = controller.doReloadMessageIca(party1, party2);

        assertThat(icaDetailsVO.getSenderParty(), is(nodeIcaDetails.getLocalParty()));
        assertThat(icaDetailsVO.getReceiverParty(), is(nodeIcaDetails.getRemoteParty()));
        assertThat(icaDetailsVO.isHasCertificate(), is(false));
        assertThat(icaDetailsVO.getConfidentialityCode(), is(nodeIcaDetails.getConfidentialityCode().toString()));
        assertThat(icaDetailsVO.getIntegrityCode(), is(nodeIcaDetails.getIntegrityCode().toString()));
        assertThat(icaDetailsVO.getIcaStatus(), is(IcaDetailsVO.IcaStatus.OK));
        assertThat(icaDetailsVO.isActiveState(), is(nodeIcaDetails.isActiveState()));
    }

    @Test
    public void test_doReloadMessageIca_Certificate() {
        String party1 = "party1";
        String party2 = "party2";
        PartyIca partyIca = new PartyIca();
        Date date = new Date();
        ExtendedCertificateData extendedCertificateData = new ExtendedCertificateData(null,
                "subject DN",
                "issuer DN",
                date,
                date,
                "serial NR",
                "a sig ALG",
                "a version");

        NodeIcaDetails nodeIcaDetails = mock(NodeIcaDetails.class);
        when(nodeIcaDetails.getLocalParty()).thenReturn(party1);
        when(nodeIcaDetails.getRemoteParty()).thenReturn(party2);
        when(nodeIcaDetails.getExtendedCertificateData()).thenReturn(extendedCertificateData);
        when(nodeIcaDetails.getConfidentialityCode()).thenReturn(ConfidentialityCode.PUBLIC);
        when(nodeIcaDetails.getIntegrityCode()).thenReturn(IntegrityCode.MODERATE);
        when(nodeIcaDetails.getCreationDate()).thenReturn(date);
        when(nodeIcaDetails.isActiveState()).thenReturn(true);

        when(icaManager.getActiveIcaBySenderReceiver(party1, party2)).thenReturn(partyIca);
        when(icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false)).thenReturn(nodeIcaDetails);

        //Do the actual call
        IcaDetailsVO icaDetailsVO = controller.doReloadMessageIca(party1, party2);

        assertThat(icaDetailsVO.getSenderParty(), is(nodeIcaDetails.getLocalParty()));
        assertThat(icaDetailsVO.getReceiverParty(), is(nodeIcaDetails.getRemoteParty()));
        assertThat(icaDetailsVO.isHasCertificate(), is(true));
        assertThat(icaDetailsVO.getVersion(), is(nodeIcaDetails.getExtendedCertificateData().getVersion()));
        assertThat(icaDetailsVO.getSerialNumber(), is(nodeIcaDetails.getExtendedCertificateData().getSerialNumber()));
        assertThat(icaDetailsVO.getIssuerDN(), is(nodeIcaDetails.getExtendedCertificateData().getIssuerDN()));
        assertThat(icaDetailsVO.getSubjectDN(), is(nodeIcaDetails.getExtendedCertificateData().getSubjectDN()));
        assertThat(icaDetailsVO.getSignatureAlgorithm(), is(nodeIcaDetails.getExtendedCertificateData().getSignatureAlgorithm()));
        assertThat(icaDetailsVO.getConfidentialityCode(), is(nodeIcaDetails.getConfidentialityCode().toString()));
        assertThat(icaDetailsVO.getIntegrityCode(), is(nodeIcaDetails.getIntegrityCode().toString()));
        assertThat(icaDetailsVO.getIcaStatus(), is(IcaDetailsVO.IcaStatus.OK));
        assertThat(icaDetailsVO.isActiveState(), is(nodeIcaDetails.isActiveState()));
    }

}
