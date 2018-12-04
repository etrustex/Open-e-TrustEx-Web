package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.PayloadMailContentExtractor;
import eu.europa.ec.etrustex.webaccess.business.api.VelocityService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.persistence.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.*;

import static org.mockito.Mockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(AbstractMailHandlerService.class)
public class AbstractMailHandlerServiceTest extends AbstractTest {

    @Mock
    private MessageDAO messageDAO;

    @Mock
    private PartyDAO partyDAO;

    @Mock
    private MessageStatusDAO messageStatusDAO;

    @Mock
    private VelocityService velocityService;

    @Mock
    private NotificationDAO notificationDAO;

    @Mock
    private BusinessUserConfigDAO businessUserConfigDAO;

    @Mock
    protected MailboxManager mailboxManager;

    @Mock
    protected AttachmentHandler attachmentHandler;

    @Mock
    protected PayloadMailContentExtractor payloadMailContentExtractor;

    private String templateFile;
    private Long messageId;
    private String receiverName;
    private String userName;

    private final String EXPECTED_CONTENT = "CONTENT";

    private final String PAYLOAD_FOLDER = "payload";

    @InjectMocks
    private AbstractMailHandlerService abstractMailHandlerService = new MailHandlerServiceAdapter() {
        @Override
        protected String buildContent(MailContentData mailContentData) {
            if (templateFile.equals(mailContentData.getTemplateFile()) && mailContentData.getMessageId().equals(messageId) &&
                    ((mailContentData.getReceiverName() != null && mailContentData.getReceiverName().equals(receiverName)) ||
                            (mailContentData.getUserName() != null && mailContentData.getUserName().equals(userName)))) {
                return EXPECTED_CONTENT;
            } else {
                return "incorrect";
            }
        }

        @Override
        protected String subjectTemplateName() {
            return "subject";
        }

        @Override
        protected String contentUserTemplateName() {
            return "contentUser";
        }

        @Override
        protected String contentPartyTemplateName() {
            return "contentParty";
        }

    };

    @Test
    public void test_prepareMessageMailForParty_should_returnPartyMail_fromTemplate() throws Exception {
        String subjectText = "My subject";
        String recipientEmailAddress = "recipientEmailAddress";
        String receiverName = "receiverName";
        Long messageId = 2L;
        Long partyId = 1L;

        String referenceId = "myref";
        String customViewName = "business-name";

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);

        Party party = new Party();
        party.setId(partyId);
        party.setDisplayName(receiverName);
        party.setEmail(recipientEmailAddress);
        when(partyDAO.findById(partyId)).thenReturn(party);

        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        when(messageDAO.findById(messageId)).thenReturn(message);

        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", null);
        when(velocityService.applyTemplate("templates/subject", model)).thenReturn(subjectText);

        templateFile = "templates/contentParty";
        this.messageId = messageId;
        this.receiverName = receiverName;

        PowerMockito.spy(AbstractMailHandlerService.class);


        AbstractMailHandlerService spy = PowerMockito.spy(abstractMailHandlerService);

        final Long notifId = 1L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(notifId);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        ArgumentCaptor<MailContentData> argumentCaptor = ArgumentCaptor.forClass(MailContentData.class);

        // DO THE ACTUAL CALL
        List<Mail> mails = spy.prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        assertThat(mails, notNullValue());
        assertThat(mails.size(), is(1));

        assertThat(mails.get(0).getMailAddress(), is(recipientEmailAddress));
        assertThat(mails.get(0).getNotificationId(), is(notifId));

        assertThat(mails.get(0).getSubject(), is(subjectText));
        assertThat(mails.get(0).getContent(), is(EXPECTED_CONTENT));
        assertThat(mails.get(0).getSentDate(), notNullValue());

        verify(spy).prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);
        verify(spy).buildContent(argumentCaptor.capture());
        MailContentData messageMailContentData = argumentCaptor.getValue();
        assertThat(messageMailContentData.getTemplateFile(), is(templateFile));
        assertThat(messageMailContentData.getMessageId(), is(messageId));
        assertThat(messageMailContentData.getReceiverName(), is(receiverName));
        PowerMockito.verifyStatic(times(1));
        AbstractMailHandlerService.buildMail(anyString(), anyString(), anyString(), anyLong());

        verify(velocityService).applyTemplate("templates/subject", model);
        verifyNoMoreInteractions(velocityService);

        verify(messageDAO).findById(messageId);
        verifyNoMoreInteractions(messageDAO);
    }


    @Test
    public void test_prepareMessageMailForParty_should_returnPartyMail_fromTemplate_retrieve_payload_unsuccesfull() throws Exception {
        String subjectText = "My subject";
        String recipientEmailAddress = "recipientEmailAddress";
        String receiverName = "receiverName";
        Long messageId = 2L;
        Long partyId = 1L;

        String referenceId = "myref";
        String customViewName = "business-name";

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);

        Party party = new Party();
        party.setId(partyId);
        party.setDisplayName(receiverName);
        party.setEmail(recipientEmailAddress);
        when(partyDAO.findById(partyId)).thenReturn(party);

        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        when(messageDAO.findById(messageId)).thenReturn(message);

        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", null);

        Set<String> resourcesLocations = new HashSet<>();
        resourcesLocations.add("templates/subject");
        resourcesLocations.add("templates/contentParty");
        resourcesLocations.add("templates/" + customViewName + "/subject");
        resourcesLocations.add("templates/" + customViewName + "/contentParty");

        abstractMailHandlerService.resources.addAll(resourcesLocations);

        resourcesLocations.add("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/subject");
        resourcesLocations.add("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/contentParty");

        abstractMailHandlerService.payloadResources.addAll(resourcesLocations);

        when(attachmentHandler.retrievePayloadContent(messageId)).thenReturn(null);

        when(velocityService.applyTemplate("templates/" + customViewName + "/subject", model)).thenReturn(subjectText);
        when(velocityService.applyTemplate("templates/subject", model)).thenReturn(subjectText);

        templateFile = "templates/" + customViewName + "/contentParty";
        this.messageId = messageId;
        this.receiverName = receiverName;

        PowerMockito.spy(AbstractMailHandlerService.class);

        AbstractMailHandlerService spy = PowerMockito.spy(abstractMailHandlerService);

        Long notifId = 1L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(1L);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        ArgumentCaptor<MailContentData> argumentCaptor = ArgumentCaptor.forClass(MailContentData.class);

        // DO THE ACTUAL CALL
        List<Mail> mails = spy.prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        assertThat(mails, notNullValue());
        assertThat(mails.size(), is(1));

        assertThat(mails.get(0).getMailAddress(), is(recipientEmailAddress));
        assertThat(mails.get(0).getNotificationId(), is(notifId));

        assertThat(mails.get(0).getSubject(), is(subjectText));
        assertThat(mails.get(0).getContent(), is(EXPECTED_CONTENT));
        assertThat(mails.get(0).getSentDate(), notNullValue());

        verify(spy).prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);
        verify(spy).buildContent(argumentCaptor.capture());
        MailContentData messageMailContentData = argumentCaptor.getValue();
        assertThat(messageMailContentData.getTemplateFile(), is(templateFile));
        assertThat(messageMailContentData.getMessageId(), is(messageId));
        assertThat(messageMailContentData.getReceiverName(), is(receiverName));
        PowerMockito.verifyStatic(times(1));
        AbstractMailHandlerService.buildMail(anyString(), anyString(), anyString(), anyLong());

        verify(velocityService).applyTemplate("templates/" + customViewName + "/subject", model);
        verifyNoMoreInteractions(velocityService);

        verify(messageDAO).findById(messageId);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_prepareMessageMailForParty_should_returnPartyMail_fromTemplate_retrieve_extract_payload_succesfull() throws Exception {
        String subjectText = "My subject";
        String recipientEmailAddress = "recipientEmailAddress";
        String receiverName = "receiverName";
        Long messageId = 2L;
        Long partyId = 1L;

        String referenceId = "myref";
        String customViewName = "business-name";

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);
        message.setSubject("Test subject");

        Party party = new Party();
        party.setId(partyId);
        party.setDisplayName(receiverName);
        party.setEmail(recipientEmailAddress);
        when(partyDAO.findById(partyId)).thenReturn(party);

        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        when(messageDAO.findById(messageId)).thenReturn(message);

        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", "TitleTest");

        Set<String> resourcesLocations = new HashSet<>();
        resourcesLocations.add("templates/subject");
        resourcesLocations.add("templates/contentParty");
        resourcesLocations.add("templates/" + customViewName + "/subject");
        resourcesLocations.add("templates/" + customViewName + "/contentParty");

        abstractMailHandlerService.resources.addAll(resourcesLocations);

        resourcesLocations.add("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/subject");
        resourcesLocations.add("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/contentParty");

        abstractMailHandlerService.payloadResources.addAll(resourcesLocations);

        String payload = "payloadContent";
        when(attachmentHandler.retrievePayloadContent(messageId)).thenReturn(payload);

        templateFile = "templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/contentParty";
        this.messageId = messageId;
        this.receiverName = receiverName;

        String title = "TitleTest";
        String linguisticVersions = "EN/FR/NL";
        boolean subsidiarityCheck = true;


        PayloadMailContentData payloadMailContentData = new PayloadMailContentData();
        payloadMailContentData.setTitle(title);
        payloadMailContentData.setLinguisticVersions(linguisticVersions);
        payloadMailContentData.setSubsidiarityCheck(subsidiarityCheck);
        payloadMailContentData.setValid(true);

        when(payloadMailContentExtractor.extractPayloadMailContentDataFromPayload(payload)).thenReturn(payloadMailContentData);

        MailContentData mailContentData = payloadMailContentData;

        when(velocityService.applyTemplate("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/subject", model)).thenReturn(subjectText);
        when(velocityService.applyTemplate("templates/" + customViewName + "/subject", model)).thenReturn(subjectText);

        PowerMockito.spy(AbstractMailHandlerService.class);

        AbstractMailHandlerService spy = PowerMockito.spy(abstractMailHandlerService);

        Long notifId = 1L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(1L);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        ArgumentCaptor<MailContentData> argumentCaptor = ArgumentCaptor.forClass(MailContentData.class);

        // DO THE ACTUAL CALL
        List<Mail> mails = spy.prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        assertThat(mails, notNullValue());
        assertThat(mails.size(), is(1));

        assertThat(mails.get(0).getMailAddress(), is(recipientEmailAddress));
        assertThat(mails.get(0).getNotificationId(), is(notifId));

        assertThat(mails.get(0).getSubject(), is(subjectText));
        assertThat(mails.get(0).getContent(), is(EXPECTED_CONTENT));
        assertThat(mails.get(0).getSentDate(), notNullValue());

        verify(spy).prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);
        verify(spy).buildContent(argumentCaptor.capture());
        MailContentData messageMailContentData = argumentCaptor.getValue();
        assertThat(messageMailContentData.getTemplateFile(), is(templateFile));
        assertThat(messageMailContentData.getMessageId(), is(messageId));
        assertThat(messageMailContentData.getReceiverName(), is(receiverName));
        PowerMockito.verifyStatic(times(1));
        AbstractMailHandlerService.buildMail(anyString(), anyString(), anyString(), anyLong());

        verify(velocityService).applyTemplate("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/subject", model);
        verifyNoMoreInteractions(velocityService);

        verify(messageDAO).findById(messageId);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_prepareMessageMailForParty_should_returnPartyMail_fromTemplate_retrieve_extract_payload_succesfull_mailContentData_invalid() throws Exception {
        String subjectText = "My subject";
        String recipientEmailAddress = "recipientEmailAddress";
        String receiverName = "receiverName";
        Long messageId = 2L;
        Long partyId = 1L;

        String referenceId = "myref";
        String customViewName = "business-name";

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);
        message.setSubject("Test subject");

        Party party = new Party();
        party.setId(partyId);
        party.setDisplayName(receiverName);
        party.setEmail(recipientEmailAddress);
        when(partyDAO.findById(partyId)).thenReturn(party);

        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        when(messageDAO.findById(messageId)).thenReturn(message);

        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", null);

        Set<String> resourcesLocations = new HashSet<>();
        resourcesLocations.add("templates/subject");
        resourcesLocations.add("templates/contentParty");
        resourcesLocations.add("templates/" + customViewName + "/subject");
        resourcesLocations.add("templates/" + customViewName + "/contentParty");

        abstractMailHandlerService.resources.addAll(resourcesLocations);

        resourcesLocations.add("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/subject");
        resourcesLocations.add("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/contentParty");

        abstractMailHandlerService.payloadResources.addAll(resourcesLocations);

        String payload = "payloadContent";
        when(attachmentHandler.retrievePayloadContent(messageId)).thenReturn(payload);

        templateFile = "templates/" + customViewName + "/contentParty";
        this.messageId = messageId;
        this.receiverName = receiverName;

        String title = "TitleTest";
        String linguisticVersions = "EN/FR/NL";
        boolean subsidiarityCheck = true;


        PayloadMailContentData payloadMailContentData = new PayloadMailContentData();
        payloadMailContentData.setTitle(null);
        payloadMailContentData.setLinguisticVersions(linguisticVersions);
        payloadMailContentData.setSubsidiarityCheck(subsidiarityCheck);
        payloadMailContentData.setValid(false);

        when(payloadMailContentExtractor.extractPayloadMailContentDataFromPayload(payload)).thenReturn(payloadMailContentData);
        when(velocityService.applyTemplate("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/subject", model)).thenReturn(subjectText);
        when(velocityService.applyTemplate("templates/" + customViewName + "/subject", model)).thenReturn(subjectText);

        PowerMockito.spy(AbstractMailHandlerService.class);

        AbstractMailHandlerService spy = PowerMockito.spy(abstractMailHandlerService);

        Long notifId = 1L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(1L);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        ArgumentCaptor<MailContentData> argumentCaptor = ArgumentCaptor.forClass(MailContentData.class);

        // DO THE ACTUAL CALL
        List<Mail> mails = spy.prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        assertThat(mails, notNullValue());
        assertThat(mails.size(), is(1));

        assertThat(mails.get(0).getMailAddress(), is(recipientEmailAddress));
        assertThat(mails.get(0).getNotificationId(), is(notifId));

        assertThat(mails.get(0).getSubject(), is(subjectText));
        assertThat(mails.get(0).getContent(), is(EXPECTED_CONTENT));
        assertThat(mails.get(0).getSentDate(), notNullValue());

        verify(spy).prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);
        verify(spy).buildContent(argumentCaptor.capture());
        MailContentData messageMailContentData = argumentCaptor.getValue();
        assertThat(messageMailContentData.getTemplateFile(), is(templateFile));
        assertThat(messageMailContentData.getMessageId(), is(messageId));
        assertThat(messageMailContentData.getReceiverName(), is(receiverName));
        PowerMockito.verifyStatic(times(1));
        AbstractMailHandlerService.buildMail(anyString(), anyString(), anyString(), anyLong());

        verify(velocityService).applyTemplate("templates/" + customViewName + "/subject", model);
        verifyNoMoreInteractions(velocityService);

        verify(messageDAO).findById(messageId);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_prepareMessageMailForParty_should_returnPartyMail_fromTemplate_extract_payload_unsuccesfull() throws Exception {
        String subjectText = "My subject";
        String recipientEmailAddress = "recipientEmailAddress";
        String receiverName = "receiverName";
        Long messageId = 2L;
        Long partyId = 1L;

        String referenceId = "myref";
        String customViewName = "business-name";

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);
        message.setSubject("Test subject");

        Party party = new Party();
        party.setId(partyId);
        party.setDisplayName(receiverName);
        party.setEmail(recipientEmailAddress);
        when(partyDAO.findById(partyId)).thenReturn(party);

        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        when(messageDAO.findById(messageId)).thenReturn(message);

        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", null);

        Set<String> resourcesLocations = new HashSet<>();
        resourcesLocations.add("templates/subject");
        resourcesLocations.add("templates/contentParty");
        resourcesLocations.add("templates/" + customViewName + "/subject");
        resourcesLocations.add("templates/" + customViewName + "/contentParty");

        abstractMailHandlerService.resources.addAll(resourcesLocations);

        resourcesLocations.add("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/subject");
        resourcesLocations.add("templates/" + customViewName + "/" + PAYLOAD_FOLDER + "/contentParty");

        abstractMailHandlerService.payloadResources.addAll(resourcesLocations);

        String payload = "<ns1:procedure_interinstitutional><ns1:event_legal><ns1:extension xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns2:EventExtensionType\">";
        when(attachmentHandler.retrievePayloadContent(messageId)).thenReturn(payload);

        templateFile = "templates/" + customViewName + "/contentParty";
        this.messageId = messageId;
        this.receiverName = receiverName;

        PayloadMailContentData payloadMailContentData = new PayloadMailContentData();

        when(payloadMailContentExtractor.extractPayloadMailContentDataFromPayload(payload)).thenReturn(payloadMailContentData);

        when(velocityService.applyTemplate("templates/" + customViewName + "/subject", model)).thenReturn(subjectText);

        PowerMockito.spy(AbstractMailHandlerService.class);

        AbstractMailHandlerService spy = PowerMockito.spy(abstractMailHandlerService);

        Long notifId = 1L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(1L);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        ArgumentCaptor<MailContentData> argumentCaptor = ArgumentCaptor.forClass(MailContentData.class);

        // DO THE ACTUAL CALL
        List<Mail> mails = spy.prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        assertThat(mails, notNullValue());
        assertThat(mails.size(), is(1));

        assertThat(mails.get(0).getMailAddress(), is(recipientEmailAddress));
        assertThat(mails.get(0).getNotificationId(), is(notifId));

        assertThat(mails.get(0).getSubject(), is(subjectText));
        assertThat(mails.get(0).getContent(), is(EXPECTED_CONTENT));
        assertThat(mails.get(0).getSentDate(), notNullValue());

        verify(spy).prepareMessageMailForParty(messageId, partyId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);
        verify(spy).buildContent(argumentCaptor.capture());
        MailContentData messageMailContentData = argumentCaptor.getValue();
        assertThat(messageMailContentData.getTemplateFile(), is(templateFile));
        assertThat(messageMailContentData.getMessageId(), is(messageId));
        assertThat(messageMailContentData.getReceiverName(), is(receiverName));
        PowerMockito.verifyStatic(times(1));
        AbstractMailHandlerService.buildMail(anyString(), anyString(), anyString(), anyLong());

        verify(velocityService).applyTemplate("templates/" + customViewName + "/subject", model);
        verifyNoMoreInteractions(velocityService);

        verify(messageDAO).findById(messageId);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_prepareMessageMailForUser_should_returnUseryMail_fromTemplate() throws Exception {
        String subjectText = "My subject";
        String recipientEmailAddress = "recipientEmailAddress";
        String userName = "userName";
        Long messageId = 2L;

        String referenceId = "myref";
        String customViewName = "business-name";

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);

        Party party = new Party();
        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        when(messageDAO.findById(messageId)).thenReturn(message);

        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", null);
        when(velocityService.applyTemplate("templates/subject", model)).thenReturn(subjectText);

        templateFile = "templates/contentUser";
        this.messageId = messageId;
        this.userName = userName;

        Long bucId = 1L;
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setId(bucId);
        businessUserConfig.setEmail(recipientEmailAddress);
        businessUserConfig.setName(userName);
        businessUserConfig.setBusiness(business);

        when(businessUserConfigDAO.findById(bucId)).thenReturn(businessUserConfig);

        PowerMockito.spy(AbstractMailHandlerService.class);

        AbstractMailHandlerService spy = PowerMockito.spy(abstractMailHandlerService);

        Long notifId = 1L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(1L);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        ArgumentCaptor<MailContentData> argumentCaptor = ArgumentCaptor.forClass(MailContentData.class);

        // DO THE ACTUAL CALL
        Mail mail = spy.prepareMessageMailForUser(messageId, bucId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        assertThat(mail, notNullValue());

        assertThat(mail.getMailAddress(), is(recipientEmailAddress));
        assertThat(mail.getNotificationId(), is(notifId));

        assertThat(mail.getSubject(), is(subjectText));
        assertThat(mail.getContent(), is(EXPECTED_CONTENT));
        assertThat(mail.getSentDate(), notNullValue());

        verify(spy).prepareMessageMailForUser(messageId, bucId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);
        verify(spy).buildContent(argumentCaptor.capture());
        MailContentData messageMailContentData = argumentCaptor.getValue();
        assertThat(messageMailContentData.getTemplateFile(), is(templateFile));
        assertThat(messageMailContentData.getMessageId(), is(messageId));
        assertThat(messageMailContentData.getReceiverName(), is(nullValue()));
        assertThat(messageMailContentData.getUserName(), is(userName));
        PowerMockito.verifyStatic(times(1));
        AbstractMailHandlerService.buildMail(anyString(), anyString(), anyString(), anyLong());

        verify(velocityService).applyTemplate("templates/subject", model);
        verifyNoMoreInteractions(velocityService);

        verify(messageDAO).findById(messageId);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_prepareStatusMailForParty_should_returnPartyMail_fromTemplate() throws Exception {
        String subjectText = "My subject";
        String recipientEmailAddress = "recipientEmailAddress";
        String receiverName = "receiverName";
        String senderName = "senderName";
        Long messageId = 2L;
        Long userId = 1L;
        String userName = "user1";
        long numFiles = 2;

        String referenceId = "myref";
        String customViewName = "business-name";

        User user = new User();
        user.setId(userId);
        user.setName(userName);

        Party remoteParty = new Party();
        remoteParty.setDisplayName(receiverName);

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);
        message.setRemoteParty(remoteParty);
        message.setCreatedBy(user);

        Long messageStatusId = 3L;
        Date now = new Date();
        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setId(messageStatusId);
        messageStatus.setMessage(message);
        messageStatus.setMstStatus(MessageStatus.Status.AVAILABLE);
        messageStatus.setCreatedOn(now);

        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);
        party.setStatusNotificationEmail(recipientEmailAddress);
        party.setDisplayName(senderName);

        when(partyDAO.findById(partyId)).thenReturn(party);

        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        when(messageStatusDAO.findById(messageStatusId)).thenReturn(messageStatus);

        Map<String, Object> model = new HashMap<>();
        model.put("messageDossierId", referenceId);
        model.put("messageMstStatus", messageStatus.getMstStatus().name());
        model.put("messageTitle", messageStatus.getMessage().getSubject());
        model.put("payloadDossierTitle", null);
        when(velocityService.applyTemplate("templates/subject", model)).thenReturn(subjectText);

        templateFile = "templates/contentParty";
        this.messageId = messageId;
        this.receiverName = receiverName;

        PowerMockito.spy(AbstractMailHandlerService.class);

        AbstractMailHandlerService spy = PowerMockito.spy(abstractMailHandlerService);

        Long notifId = 1L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(1L);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        when(mailboxManager.countBinaryAttachments(messageId)).thenReturn(numFiles);

        ArgumentCaptor<MailContentData> argumentCaptor = ArgumentCaptor.forClass(MailContentData.class);

        // DO THE ACTUAL CALL
        Mail mail = spy.prepareStatusMailForParty(messageStatusId, partyId);

        assertThat(mail, notNullValue());

        assertThat(mail.getMailAddress(), is(recipientEmailAddress));
        assertThat(mail.getNotificationId(), is(notifId));

        assertThat(mail.getSubject(), is(subjectText));
        assertThat(mail.getContent(), is(EXPECTED_CONTENT));
        assertThat(mail.getSentDate(), notNullValue());

        verify(spy).prepareStatusMailForParty(messageStatusId, partyId);
        verify(spy).buildContent(argumentCaptor.capture());
        MailContentData statusMailContentData = argumentCaptor.getValue();
        assertThat(statusMailContentData.getTemplateFile(), is(templateFile));
        assertThat(statusMailContentData.getStatus(), is(messageStatus.getMstStatus().name()));
        assertThat(statusMailContentData.getReceiverName(), is(receiverName));
        assertThat(statusMailContentData.getSenderName(), is(senderName));
        assertThat(statusMailContentData.getStatusDate(), is(AbstractMailHandlerService.dateFormat.format(messageStatus.getCreatedOn())));
        assertThat(statusMailContentData.getStatusTime(), is(AbstractMailHandlerService.timeFormat.format(messageStatus.getCreatedOn())));
        assertThat(statusMailContentData.getNumFiles(), is(numFiles));
        PowerMockito.verifyStatic(times(1));
        AbstractMailHandlerService.buildMail(anyString(), anyString(), anyString(), anyLong());

        verify(velocityService).applyTemplate("templates/subject", model);
        verifyNoMoreInteractions(velocityService);

        verify(messageStatusDAO).findById(messageStatusId);
        verifyNoMoreInteractions(messageDAO);

        verify(mailboxManager).countBinaryAttachments(messageId);
        verifyNoMoreInteractions(mailboxManager);
    }

    @Test
    public void test_prepareStatusMailForUser_should_returnUserMail_fromTemplate() throws Exception {
        String subjectText = "My subject";
        String recipientEmailAddress = "recipientEmailAddress";
        String receiverName = "receiverName";
        String senderName = "senderName";
        Long messageId = 2L;
        Long userId = 1L;
        String userName = "user1";
        long numFiles = 2;

        String referenceId = "myref";
        String customViewName = "business-name";

        User user = new User();
        user.setId(userId);
        user.setName(userName);

        Party remoteParty = new Party();
        remoteParty.setDisplayName(receiverName);

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);
        message.setCreatedBy(user);
        message.setRemoteParty(remoteParty);

        Long messageStatusId = 3L;
        Date now = new Date();
        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setId(messageStatusId);
        messageStatus.setMessage(message);
        messageStatus.setMstStatus(MessageStatus.Status.AVAILABLE);
        messageStatus.setCreatedOn(now);

        Party party = new Party();
        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        party.setDisplayName(senderName);
        message.setLocalParty(party);

        when(messageStatusDAO.findById(messageStatusId)).thenReturn(messageStatus);

        Map<String, Object> model = new HashMap<>();
        model.put("messageDossierId", referenceId);
        model.put("messageMstStatus", messageStatus.getMstStatus().name());
        model.put("messageTitle", messageStatus.getMessage().getSubject());
        model.put("payloadDossierTitle", null);
        when(velocityService.applyTemplate("templates/subject", model)).thenReturn(subjectText);

        templateFile = "templates/contentUser";
        this.messageId = messageId;
        this.receiverName = receiverName;

        Long bucId = 1L;
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setId(bucId);
        businessUserConfig.setStatusNotificationEmail(recipientEmailAddress);
        businessUserConfig.setName(receiverName);
        businessUserConfig.setBusiness(business);

        when(businessUserConfigDAO.findById(bucId)).thenReturn(businessUserConfig);

        PowerMockito.spy(AbstractMailHandlerService.class);

        AbstractMailHandlerService spy = PowerMockito.spy(abstractMailHandlerService);

        Long notifId = 1L;
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(1L);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        when(mailboxManager.countBinaryAttachments(messageId)).thenReturn(numFiles);

        ArgumentCaptor<MailContentData> argumentCaptor = ArgumentCaptor.forClass(MailContentData.class);

        // DO THE ACTUAL CALL
        Mail mail = spy.prepareStatusMailForUser(messageStatusId, bucId);

        assertThat(mail, notNullValue());

        assertThat(mail.getMailAddress(), is(recipientEmailAddress));
        assertThat(mail.getNotificationId(), is(notNullValue()));

        assertThat(mail.getSubject(), is(subjectText));
        assertThat(mail.getContent(), is(EXPECTED_CONTENT));
        assertThat(mail.getSentDate(), notNullValue());

        verify(spy).prepareStatusMailForUser(messageStatusId, bucId);
        verify(spy).buildContent(argumentCaptor.capture());
        MailContentData statusMailContentData = argumentCaptor.getValue();
        assertThat(statusMailContentData.getTemplateFile(), is(templateFile));
        assertThat(statusMailContentData.getStatus(), is(messageStatus.getMstStatus().name()));
        assertThat(statusMailContentData.getReceiverName(), is(receiverName));
        assertThat(statusMailContentData.getSenderName(), is(senderName));
        assertThat(statusMailContentData.getStatusDate(), is(AbstractMailHandlerService.dateFormat.format(messageStatus.getCreatedOn())));
        assertThat(statusMailContentData.getStatusTime(), is(AbstractMailHandlerService.timeFormat.format(messageStatus.getCreatedOn())));
        assertThat(statusMailContentData.getNumFiles(), is(numFiles));
        PowerMockito.verifyStatic(times(1));
        AbstractMailHandlerService.buildMail(anyString(), anyString(), anyString(), anyLong());

        verify(velocityService).applyTemplate("templates/subject", model);
        verifyNoMoreInteractions(velocityService);

        verify(messageStatusDAO).findById(messageStatusId);
        verifyNoMoreInteractions(messageDAO);

        verify(mailboxManager).countBinaryAttachments(messageId);
        verifyNoMoreInteractions(mailboxManager);
    }
}