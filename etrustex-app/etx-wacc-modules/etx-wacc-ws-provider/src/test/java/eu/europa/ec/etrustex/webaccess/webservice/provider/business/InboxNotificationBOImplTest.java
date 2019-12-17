package eu.europa.ec.etrustex.webaccess.webservice.provider.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.*;
import eu.europa.ec.etrustex.webaccess.business.queue.message.RetrieveMessageTriggerServiceImpl;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.BusinessCustomViewName;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

public class InboxNotificationBOImplTest extends AbstractTest {

    @Mock
    private MailboxManager mailboxManager;

    @Mock
    private PartyManager partyManager;

    @Mock
    private IcaManager icaManager;

    @Mock
    private RetrievePayloadTriggerService retrievePayloadTriggerService;

    @Mock
    private NotifyService notifyService;

    @Mock
    private RetrieveMessageTriggerServiceImpl nodeMessageTriggerService;

    @InjectMocks
    private InboxNotificationBOImpl inboxNotificationBO = new InboxNotificationBOImpl();

    @Test
    public void test_handleMessage_shouldConsumeNodeMessage_when_partyConsumesNodeMessages() throws Exception {
        long localPartyId = 1L;
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String messageUuid = "uuid1";
        long messageId = 100L;
        String loggedInUser = "user1";

        Business business = new Business();
        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(BusinessCustomViewName.EDMA.name());
        LinkedList<BusinessConfig> businessConfigs = new LinkedList<BusinessConfig>();
        businessConfigs.add(businessConfig);
        business.setBusinessConfigs(businessConfigs);

        Party localParty = new Party();
        localParty.setId(localPartyId);
        localParty.setNodeName(localPartyName);
        localParty.setConsumeNodeMessages(true);
        localParty.setBusiness(business);

        PartyIca partyIca = new PartyIca();
        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);
        partyIca.setRemoteParty(remoteParty);

        Message message = new Message();
        message.setId(messageId);
        message.setBundleId(messageUuid);
        message.setRemoteParty(remoteParty);

        when(partyManager.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);
        when(partyManager.getWebManagedPartyByNodeName(remotePartyName)).thenReturn(remoteParty);
        when(mailboxManager.searchForDuplicateMessageBundle(messageUuid, localPartyId, remotePartyName)).thenReturn(false);
        when(mailboxManager.saveOrUpdateMessage(message, null)).thenReturn(message);
        when(icaManager.getIcaBySenderReceiver(localPartyName, remotePartyName)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        inboxNotificationBO.handleMessage(loggedInUser, message, localPartyName, remotePartyName);

        verify(mailboxManager).searchForDuplicateMessageBundle(messageUuid, localPartyId, remotePartyName);
        verify(mailboxManager).saveOrUpdateMessage(message, null);
        verify(retrievePayloadTriggerService).triggerRetrievePayload(loggedInUser, messageId);
        verify(notifyService).notify(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verifyNoMoreInteractions(mailboxManager);
        verifyNoMoreInteractions(retrievePayloadTriggerService);
        verifyNoMoreInteractions(notifyService);
    }

    @Test
    public void test_handleMessage_shouldNotConsumeNodeMessage_when_partyNotConsumesNodeMessages() throws Exception {
        long localPartyId = 1L;
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String messageUuid = "uuid1";
        long messageId = 100L;
        String loggedInUser = "user1";

        Business business = new Business();
        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(BusinessCustomViewName.EDMA.name());
        LinkedList<BusinessConfig> businessConfigs = new LinkedList();
        businessConfigs.add(businessConfig);
        business.setBusinessConfigs(businessConfigs);

        Party localParty = new Party();
        localParty.setId(localPartyId);
        localParty.setNodeName(localPartyName);
        localParty.setConsumeNodeMessages(false);
        localParty.setBusiness(business);

        PartyIca partyIca = new PartyIca();
        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);
        partyIca.setRemoteParty(remoteParty);

        Message message = new Message();
        message.setId(messageId);
        message.setBundleId(messageUuid);
        message.setRemoteParty(remoteParty);

        when(partyManager.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);
        when(partyManager.getWebManagedPartyByNodeName(remotePartyName)).thenReturn(remoteParty);
        when(mailboxManager.searchForDuplicateMessageBundle(messageUuid, localPartyId, remotePartyName)).thenReturn(false);
        when(mailboxManager.saveOrUpdateMessage(message, null)).thenReturn(message);
        when(icaManager.getIcaBySenderReceiver(localPartyName, remotePartyName)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        inboxNotificationBO.handleMessage(loggedInUser, message, localPartyName, remotePartyName);

        verify(mailboxManager).searchForDuplicateMessageBundle(messageUuid, localPartyId, remotePartyName);
        verify(mailboxManager).saveOrUpdateMessage(message, null);
        verify(retrievePayloadTriggerService).triggerRetrievePayload(loggedInUser, messageId);
        verify(notifyService).notify(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verifyNoMoreInteractions(mailboxManager);
        verifyNoMoreInteractions(retrievePayloadTriggerService);
        verifyNoMoreInteractions(notifyService);
    }

    @Test
    public void test_handleMessage_shouldConsumeNodeMessage_when_partyConsumesNodeMessages_should_not_retrieve_payload_when_generic() throws Exception {
        long localPartyId = 1L;
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String messageUuid = "uuid1";
        long messageId = 100L;
        String loggedInUser = "user1";

        Business business = new Business();
        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(BusinessCustomViewName.GENERIC.name());
        LinkedList<BusinessConfig> businessConfigs = new LinkedList();
        businessConfigs.add(businessConfig);
        business.setBusinessConfigs(businessConfigs);

        Party localParty = new Party();
        localParty.setId(localPartyId);
        localParty.setNodeName(localPartyName);
        localParty.setConsumeNodeMessages(true);
        localParty.setBusiness(business);

        PartyIca partyIca = new PartyIca();
        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);
        partyIca.setRemoteParty(remoteParty);

        Message message = new Message();
        message.setId(messageId);
        message.setBundleId(messageUuid);
        message.setRemoteParty(remoteParty);

        when(partyManager.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);
        when(partyManager.getWebManagedPartyByNodeName(remotePartyName)).thenReturn(remoteParty);
        when(mailboxManager.searchForDuplicateMessageBundle(messageUuid, localPartyId, remotePartyName)).thenReturn(false);
        when(mailboxManager.saveOrUpdateMessage(message, null)).thenReturn(message);
        when(icaManager.getIcaBySenderReceiver(localPartyName, remotePartyName)).thenReturn(partyIca);

        // DO THE ACTUAL CALL
        message = inboxNotificationBO.handleMessage(loggedInUser, message, localPartyName, remotePartyName);

        verify(mailboxManager).searchForDuplicateMessageBundle(messageUuid, localPartyId, remotePartyName);
        verify(mailboxManager).saveOrUpdateMessage(message, null);
        verify(notifyService).notify(message, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        verifyNoMoreInteractions(mailboxManager);
        verifyZeroInteractions(retrievePayloadTriggerService);
        verifyNoMoreInteractions(notifyService);
    }
}