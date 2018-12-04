package eu.europa.ec.etrustex.webaccess.business.notification;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailHandlerService;
import eu.europa.ec.etrustex.webaccess.business.api.MailTriggerService;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.persistence.NotificationDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.jms.JMSException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.verifyZeroInteractions;

@RunWith(PowerMockRunner.class)
@PrepareForTest(NotifyServiceImpl.class)
public class NotifyServiceImplTest extends AbstractTest {

    @InjectMocks
    private NotifyServiceImpl notifyService = new NotifyServiceImpl();

    @Mock
    private UserManager userManager;

    @Mock
    private MailTriggerService mailTriggerService;

    @Mock
    private MailHandlerService messageReceivedMail;

    @Mock
    private MailHandlerService messageWarningHandler;

    @Mock
    private MailHandlerService statusReceivedMailHandler;

    @Mock
    private NotificationDAO notificationDAO;

    @Mock
    private PartyManager partyManager;

    @Test
    public void test_notifyUsers_should_triggerEmailSending_when_notificationsEnabledAndEmailPresent() throws JMSException {
        String email1 = "email";
        String email2 = "other@email";

        Party party = new Party();
        Mail mail1 = new Mail();
        Mail mail2 = new Mail();
        List<Mail> mails = Arrays.asList(mail2);

        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        Message message = new Message();
        message.setId(1L);
        message.setLocalParty(party);

        BusinessUserConfig businessUserConfig1 = new BusinessUserConfig();
        businessUserConfig1.setNotificationsEnabled(true);
        businessUserConfig1.setEmail(email1);
        businessUserConfig1.setUser(user1);

        BusinessUserConfig businessUserConfig2 = new BusinessUserConfig();
        businessUserConfig2.setNotificationsEnabled(true);
        businessUserConfig2.setEmail(email2);
        businessUserConfig2.setUser(user2);

        Set<BusinessUserConfig> businessUserConfigs = new HashSet<>();
        businessUserConfigs.add(businessUserConfig1);
        businessUserConfigs.add(businessUserConfig2);

        when(userManager.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(businessUserConfigs);
        when(messageReceivedMail.prepareMessageMailForUser(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mail1);
        when(messageReceivedMail.prepareMessageMailForUser(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mail2);
        when(messageReceivedMail.prepareMessageMailForParty(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mails);
        when(partyManager.isNotificationAllowed(party, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL)).thenReturn(true);

        //ACTUAL CALL
        notifyService.notify(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verify(messageReceivedMail).prepareMessageMailForParty(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))));
        verify(mailTriggerService).triggerEmailSending(argThat(is(any(Mail.class))));

        verifyNoMoreInteractions(mailTriggerService);
        verifyZeroInteractions(statusReceivedMailHandler);
    }

    @Test
    public void test_notifyUsers_should_triggerEmailSending_when_onlyNotificationsStatusEnabledAndEmailPresent() throws JMSException {
        String email1 = "email";
        String email2 = "other@email";

        Party party = new Party();
        Mail mail1 = new Mail();
        Mail mail2 = new Mail();

        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        Message message = new Message();
        message.setId(1L);
        message.setLocalParty(party);
        message.setActiveState(true);

        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setId(1L);
        messageStatus.setMessage(message);

        BusinessUserConfig businessUserConfig1 = new BusinessUserConfig();
        businessUserConfig1.setId(1L);
        businessUserConfig1.setStatusNotificationEnabled(true);
        businessUserConfig1.setStatusNotificationEmail(email1);
        businessUserConfig1.setUser(user1);
        businessUserConfig1.setName("buc1");

        BusinessUserConfig businessUserConfig2 = new BusinessUserConfig();
        businessUserConfig2.setId(2L);
        businessUserConfig2.setStatusNotificationEnabled(true);
        businessUserConfig2.setStatusNotificationEmail(email2);
        businessUserConfig2.setUser(user2);
        businessUserConfig2.setName("buc2");

        Set<BusinessUserConfig> businessUserConfigs = new HashSet<>();
        businessUserConfigs.add(businessUserConfig1);
        businessUserConfigs.add(businessUserConfig2);

        when(userManager.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(businessUserConfigs);
        when(statusReceivedMailHandler.prepareStatusMailForUser(messageStatus.getId(), businessUserConfig1.getId())).thenReturn(mail1);
        when(statusReceivedMailHandler.prepareStatusMailForUser(messageStatus.getId(), businessUserConfig2.getId())).thenReturn(mail2);
        when(partyManager.isNotificationAllowed(party, Notification.NotificationType.STATUS_RECEIVED_EMAIL)).thenReturn(false);
        when(userManager.isNotificationAllowed(businessUserConfig1, Notification.NotificationType.STATUS_RECEIVED_EMAIL)).thenReturn(true);
        when(userManager.isNotificationAllowed(businessUserConfig2, Notification.NotificationType.STATUS_RECEIVED_EMAIL)).thenReturn(true);

        //ACTUAL CALL
        notifyService.notify(messageStatus);

        verify(statusReceivedMailHandler).prepareStatusMailForUser(messageStatus.getId(), businessUserConfig1.getId());
        verify(statusReceivedMailHandler).prepareStatusMailForUser(messageStatus.getId(), businessUserConfig2.getId());
        verifyZeroInteractions(messageReceivedMail);

        verify(mailTriggerService).triggerEmailSending(mail1);
        verify(mailTriggerService).triggerEmailSending(mail2);

        verifyNoMoreInteractions(mailTriggerService);
    }

    @Test
    public void test_notifyUsers_should_notTriggerEmailSending_when_notificationsDisabled() throws JMSException {
        String email = "email";

        Party party = new Party();
        Mail mail = new Mail();

        Message message = new Message();
        message.setLocalParty(party);

        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(false);
        businessUserConfig.setEmail(email);

        Set<BusinessUserConfig> businessUserConfigs = new HashSet<>();
        businessUserConfigs.add(businessUserConfig);

        when(userManager.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(businessUserConfigs);
        when(messageReceivedMail.prepareMessageMailForUser(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mail);

        //ACTUAL CALL
        notifyService.notifyUsers(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verifyZeroInteractions(mailTriggerService);
        verifyZeroInteractions(statusReceivedMailHandler);
    }

    @Test
    public void test_notifyUsers_should_notTriggerEmailSending_when_emailIsNotPresent() throws JMSException {
        String email = "";

        Party party = new Party();
        Mail mail = new Mail();

        Message message = new Message();
        message.setLocalParty(party);

        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setNotificationsEnabled(true);
        businessUserConfig.setEmail(email);

        Set<BusinessUserConfig> businessUserConfigs = new HashSet<>();
        businessUserConfigs.add(businessUserConfig);

        when(userManager.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(businessUserConfigs);
        when(messageReceivedMail.prepareMessageMailForUser(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mail);

        //ACTUAL CALL
        notifyService.notifyUsers(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verifyZeroInteractions(mailTriggerService);
        verifyZeroInteractions(statusReceivedMailHandler);
    }

    @Test
    public void test_notifyParty_should_triggerEmailSending_when_notificationsEnabledAndEmailPresent() throws Exception {
        String email = "email";

        Party party = new Party();
        party.setEmail(email);
        party.setNotificationsEnabled(true);

        Mail mail = new Mail();
        List<Mail> mails = Arrays.asList(mail);

        Message message = new Message();
        message.setLocalParty(party);

        NotifyServiceImpl spy = PowerMockito.spy(notifyService);

        when(messageReceivedMail.prepareMessageMailForParty(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mails);
        when(partyManager.isNotificationAllowed(party, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL)).thenReturn(true);

        //ACTUAL CALL
        spy.notifyParty(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verify(messageReceivedMail).prepareMessageMailForParty(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))));
        verify(mailTriggerService).triggerEmailSending(mail);
    }

    @Test
    public void test_notifyParty_should_notTriggerEmailSending_when_notificationsNotEnabled() throws JMSException {
        String email = "email";

        Party party = new Party();
        party.setEmail(email);
        party.setNotificationsEnabled(false);

        Message message = new Message();
        message.setLocalParty(party);

        //ACTUAL CALL
        notifyService.notifyParty(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verifyZeroInteractions(mailTriggerService);
    }

    @Test
    public void test_notifyParty_should_notTriggerEmailSending_when_emailNotPresent() throws JMSException {

        Party party = new Party();
        party.setEmail(null);
        party.setNotificationsEnabled(true);

        Message message = new Message();
        message.setLocalParty(party);

        //ACTUAL CALL
        notifyService.notifyParty(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verifyZeroInteractions(mailTriggerService);
    }

    @Test
    public void test_notifyUsersAndParty_should_triggerEmailSending_when_notificationsEnabledAndEmailPresent() throws Exception {
        NotifyServiceImpl notifyServiceSpy = PowerMockito.spy(notifyService);

        String email1 = "email";
        String email2 = "other@email";
        String email3 = "another@email";

        Party party = new Party();
        party.setId(1L);
        Mail mail1 = new Mail();
        Mail mail2 = new Mail();
        Mail mail3 = new Mail();
        List<Mail> mails = Arrays.asList(mail3);

        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        Message message = new Message();
        message.setLocalParty(party);

        BusinessUserConfig businessUserConfig1 = new BusinessUserConfig();
        businessUserConfig1.setNotificationsEnabled(true);
        businessUserConfig1.setEmail(email1);
        businessUserConfig1.setUser(user1);

        BusinessUserConfig businessUserConfig2 = new BusinessUserConfig();
        businessUserConfig2.setNotificationsEnabled(true);
        businessUserConfig2.setEmail(email2);
        businessUserConfig2.setUser(user2);

        Set<BusinessUserConfig> businessUserConfigs = new HashSet<>();
        businessUserConfigs.add(businessUserConfig1);
        businessUserConfigs.add(businessUserConfig2);

        party.setEmail(email3);
        party.setNotificationsEnabled(true);

        when(userManager.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(businessUserConfigs);
        when(messageWarningHandler.prepareMessageMailForUser(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mail1);
        when(messageWarningHandler.prepareMessageMailForUser(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mail2);
        when(messageWarningHandler.prepareMessageMailForParty(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))))).thenReturn(mails);
        when(partyManager.isNotificationAllowed(party, Notification.NotificationType.WARNING_EMAIL)).thenReturn(true);
        when(userManager.isNotificationAllowed(businessUserConfig1, Notification.NotificationType.WARNING_EMAIL)).thenReturn(true);
        when(userManager.isNotificationAllowed(businessUserConfig2, Notification.NotificationType.WARNING_EMAIL)).thenReturn(true);

        //ACTUAL CALL
        notifyServiceSpy.notify(message, Notification.NotificationType.WARNING_EMAIL);

        verify(messageWarningHandler, times(2)).prepareMessageMailForUser(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))));
        verify(messageWarningHandler).prepareMessageMailForParty(anyLong(), anyLong(), argThat(is(any(Notification.NotificationType.class))));
        verify(mailTriggerService, times(3)).triggerEmailSending(argThat(is(any(Mail.class))));

        verifyNoMoreInteractions(mailTriggerService);

        verifyZeroInteractions(statusReceivedMailHandler);
    }

    @Test
    public void test_notifyUsersAndParty_should_triggerStatusEmailSending_when_notificationsEnabledAndEmailPresent() throws Exception {
        NotifyServiceImpl notifyServiceSpy = PowerMockito.spy(notifyService);

        String email1 = "email";
        String email2 = "other@email";
        String email3 = "another@email";

        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);
        Mail mail1 = new Mail();
        Mail mail2 = new Mail();
        Mail mail3 = new Mail();

        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        Message message = new Message();
        message.setId(1L);
        message.setLocalParty(party);
        message.setActiveState(true);

        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setId(1L);
        messageStatus.setMessage(message);

        BusinessUserConfig businessUserConfig1 = new BusinessUserConfig();
        businessUserConfig1.setId(1L);
        businessUserConfig1.setNotificationsEnabled(true);
        businessUserConfig1.setEmail(email1);
        businessUserConfig1.setUser(user1);
        businessUserConfig1.setStatusNotificationEnabled(true);
        businessUserConfig1.setStatusNotificationEmail(email1);
        businessUserConfig1.setName("buc1");

        BusinessUserConfig businessUserConfig2 = new BusinessUserConfig();
        businessUserConfig2.setId(2L);
        businessUserConfig2.setNotificationsEnabled(true);
        businessUserConfig2.setEmail(email2);
        businessUserConfig2.setUser(user2);
        businessUserConfig2.setStatusNotificationEnabled(true);
        businessUserConfig2.setStatusNotificationEmail(email2);
        businessUserConfig2.setName("buc2");

        Set<BusinessUserConfig> businessUserConfigs = new HashSet<>();
        businessUserConfigs.add(businessUserConfig1);
        businessUserConfigs.add(businessUserConfig2);

        party.setEmail(email3);
        party.setNotificationsEnabled(true);
        party.setStatusNotificationEmail(email3);
        party.setStatusNotificationEnabled(true);
        party.setDisplayName("party1");

        when(userManager.getBusinessUserConfigs(party, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES)).thenReturn(businessUserConfigs);
        when(statusReceivedMailHandler.prepareStatusMailForUser(messageStatus.getId(), businessUserConfig1.getId())).thenReturn(mail1);
        when(statusReceivedMailHandler.prepareStatusMailForUser(messageStatus.getId(), businessUserConfig2.getId())).thenReturn(mail2);
        when(statusReceivedMailHandler.prepareStatusMailForParty(messageStatus.getId(), party.getId())).thenReturn(mail3);
        when(partyManager.isNotificationAllowed(party, Notification.NotificationType.STATUS_RECEIVED_EMAIL)).thenReturn(true);
        when(userManager.isNotificationAllowed(businessUserConfig1, Notification.NotificationType.STATUS_RECEIVED_EMAIL)).thenReturn(true);
        when(userManager.isNotificationAllowed(businessUserConfig2, Notification.NotificationType.STATUS_RECEIVED_EMAIL)).thenReturn(true);

        //ACTUAL CALL
        notifyServiceSpy.notify(messageStatus);

        verify(mailTriggerService).triggerEmailSending(mail1);
        verify(mailTriggerService).triggerEmailSending(mail2);
        verify(mailTriggerService).triggerEmailSending(mail3);
        verify(statusReceivedMailHandler).prepareStatusMailForUser(messageStatus.getId(), businessUserConfig1.getId());
        verify(statusReceivedMailHandler).prepareStatusMailForUser(messageStatus.getId(), businessUserConfig2.getId());
        verify(statusReceivedMailHandler).prepareStatusMailForParty(messageStatus.getId(), partyId);

        verifyNoMoreInteractions(mailTriggerService);

        verifyZeroInteractions(statusReceivedMailHandler);
    }

    @Test
    public void test_notifyUsersAndParty_should_notTriggerStatusEmailSending_when_notificationsEnabledAndMessageDisabled() throws Exception {
        NotifyServiceImpl notifyServiceSpy = PowerMockito.spy(notifyService);

        String email1 = "email";
        String email2 = "other@email";
        String email3 = "another@email";

        Long partyId = 1L;
        Party party = new Party();
        party.setId(partyId);

        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        Message message = new Message();
        message.setId(1L);
        message.setLocalParty(party);
        message.setActiveState(false);

        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setId(1L);
        messageStatus.setMessage(message);

        BusinessUserConfig businessUserConfig1 = new BusinessUserConfig();
        businessUserConfig1.setId(1L);
        businessUserConfig1.setNotificationsEnabled(true);
        businessUserConfig1.setEmail(email1);
        businessUserConfig1.setUser(user1);
        businessUserConfig1.setStatusNotificationEnabled(true);
        businessUserConfig1.setStatusNotificationEmail(email1);
        businessUserConfig1.setName("buc1");

        BusinessUserConfig businessUserConfig2 = new BusinessUserConfig();
        businessUserConfig2.setId(2L);
        businessUserConfig2.setNotificationsEnabled(true);
        businessUserConfig2.setEmail(email2);
        businessUserConfig2.setUser(user2);
        businessUserConfig2.setStatusNotificationEnabled(true);
        businessUserConfig2.setStatusNotificationEmail(email2);
        businessUserConfig2.setName("buc2");

        party.setEmail(email3);
        party.setNotificationsEnabled(true);
        party.setStatusNotificationEmail(email3);
        party.setStatusNotificationEnabled(true);
        party.setDisplayName("party1");

        //ACTUAL CALL
        notifyServiceSpy.notify(messageStatus);

        Mockito.verifyZeroInteractions(mailTriggerService);
        Mockito.verifyZeroInteractions(statusReceivedMailHandler);
        Mockito.verifyZeroInteractions(mailTriggerService);
    }
}