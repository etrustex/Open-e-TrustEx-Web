package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.business.queue.message.RetrieveMessageTriggerServiceImpl;
import eu.europa.ec.etrustex.webaccess.business.util.IcaHelper;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.Message.MessageState;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageReadStatusDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageStatusDAO;
import eu.europa.ec.etrustex.webaccess.persistence.PartyDAO;
import eu.europa.ec.etrustex.webaccess.persistence.jpa.MessageColumn;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.*;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
public class MailboxManagerImplTest extends AbstractTest {

    private Party recipient;

    @Mock
    private MessageDAO messageDAO;

    @Mock
    private PartyDAO partyDAO;

    @Mock
    private MessageReadStatusDAO messageReadStatusDAO;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private MessageStatusDAO messageStatusDAO;

    @Mock
    private RetrieveMessageTriggerServiceImpl nodeMessageTriggerService;

    @Mock
    private IcaManager icaManager;

    @Mock
    private IcaHelper icaHelper;

    @InjectMocks
    private MailboxManagerImpl mailboxManagerImpl = new MailboxManagerImpl();

    @Override
    protected void onSetUp() {
        recipient = new Party();
        recipient.setId(1L);
        recipient.setDisplayName("foo");
        recipient.setNodeName("foo");
    }

    @Test
    public void testGetMessages() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);
        User user = new User();
        user.setId(77L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(6);
        configuration.setRetentionPolicyWarningBeforeDays(3);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        Date createdOn = new Date();
        int retentionPolicyWeeks = configuration.getRetentionPolicyWeeks();
        int retentionPolicyWarningBeforeDays = configuration.getRetentionPolicyWarningBeforeDays();

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(createdOn);
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        Calendar warningLimitDate = Calendar.getInstance();
        warningLimitDate.setTime(expiryDate.getTime());
        warningLimitDate.add(Calendar.DATE, -(retentionPolicyWarningBeforeDays));

        RetentionMetadata retentionMetadata = new RetentionMetadata();
        retentionMetadata.setRetentionExpired(now.compareTo(expiryDate) > 0);
        retentionMetadata.setRetentionWarning(!retentionMetadata.getRetentionExpired() && now.compareTo(warningLimitDate) > 0);
        retentionMetadata.setExpiredOn(expiryDate);

        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("remoteParty1");

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("mySubject");
        message.setMsgState(MessageState.INCOMING);
        message.setRemoteParty(remoteParty1);
        message.setLocalParty(recipient);
        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("Payload");
        attachment.setType(AttachmentType.METADATA);
        attachmentsList.add(attachment);

        message.setAttachments(attachmentsList);
        messages.add(message);

        Party remoteParty2 = new Party();
        remoteParty2.setDisplayName("bla bla");
        remoteParty2.setNodeName("remoteParty2");

        Message secondMessage = new Message();
        secondMessage.setCreatedOn(createdOn);
        secondMessage.setId(35L);
        secondMessage.setContent("myContent");
        secondMessage.setMsgState(MessageState.INCOMING);
        secondMessage.setRemoteParty(remoteParty2);

        secondMessage.setLocalParty(recipient);

        messages.add(secondMessage);

        when(messageDAO.getMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(MessageColumn.RECEIVED)), anyBoolean(), anyInt(), anyInt())).thenReturn(messages);
        when(messageDAO.countMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))))).thenReturn(5L);
        when(messageDAO.getSubMessagesReadByUser(messages, user.getId())).thenReturn(Collections.singletonList(secondMessage.getId()));
        when(messageDAO.getMessageIdsWithBinaryAttachments(messages)).thenReturn(Collections.singletonList(secondMessage.getId()));

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(configurationService.isRetentionPolicyValid(configuration)).thenReturn(true);
        when(configurationService.computeRetentionMetadata(configuration, createdOn)).thenReturn(retentionMetadata);

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(secondMessage)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        ResultDataList<MessageListEntry> result = mailboxManagerImpl.getMessages(queryParams, user, MessageState.INCOMING, secondMessage.getLocalParty());

        assertThat(result.getData().get(0).getSubject(), is("mySubject"));
        assertThat(result.getData().get(0).getMessageId(), is(45L));
        assertThat(result.getData().get(0).getSenderName(), is(nullValue()));
        assertThat(result.getData().get(0).getHasAttachments(), is(false));
        assertThat(result.getData().get(0).getReadByCurrentUser(), is(false));
        assertThat(result.getData().get(0).getRetentionMetadata().getExpiredOn(), is(expiryDate));
        assertThat(result.getData().get(0).isHasIca(), is(true));
        assertThat(messages.get(0).getAttachments().get(0).getType(), is(AttachmentType.BINARY));
        assertThat(messages.get(0).getAttachments().get(1).getType(), is(AttachmentType.METADATA));
        assertThat(result.getData().get(1).getMessageId(), is(35L));
        assertThat(result.getData().get(1).getSenderName(), is("bla bla"));
        assertThat(result.getData().get(1).getHasAttachments(), is(true));
        assertThat(result.getData().get(1).getReadByCurrentUser(), is(true));
        assertThat(result.getData().get(1).getRetentionMetadata().getExpiredOn(), is(expiryDate));
        assertThat(result.getData().get(1).isHasIca(), is(true));
        assertThat(result.getTotalRowCount(), is(5L));

        verify(messageDAO).countMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))));
        verify(messageDAO).getMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt());
        verify(messageDAO).getSubMessagesReadByUser(argThat(sameInstance(messages)), argThat(is(equalTo(user.getId()))));
        verify(messageDAO).getMessageIdsWithBinaryAttachments(argThat(sameInstance(messages)));

        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetMessagesInvalidRetention() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);
        User user = new User();
        user.setId(77L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(1);
        configuration.setRetentionPolicyWarningBeforeDays(10);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        Date createdOn = new Date();

        RetentionMetadata retentionMetadata = new RetentionMetadata();
        retentionMetadata.setRetentionExpired(false);
        retentionMetadata.setRetentionWarning(false);
        retentionMetadata.setExpiredOn(null);

        List<Message> messages = new ArrayList<>();

        Party remoteParty = new Party();
        remoteParty.setDisplayName("bla bla");

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(35L);
        message.setContent("myContent");
        message.setMsgState(MessageState.INCOMING);
        message.setRemoteParty(remoteParty);
        message.setLocalParty(recipient);

        messages.add(message);

        when(messageDAO.countMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))))).thenReturn(5L);
        when(messageDAO.getMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(MessageColumn.RECEIVED)), anyBoolean(), anyInt(), anyInt())).thenReturn(messages);
        when(messageDAO.getSubMessagesReadByUser(messages, user.getId())).thenReturn(Collections.singletonList(message.getId()));
        when(messageDAO.getMessageIdsWithBinaryAttachments(messages)).thenReturn(Collections.singletonList(message.getId()));

        when(configurationService.getConfiguration()).thenReturn(configuration);

        // DO THE ACTUAL CALL
        ResultDataList<MessageListEntry> result = mailboxManagerImpl.getMessages(queryParams, user, MessageState.INCOMING, message.getLocalParty());

        assertThat(configurationService.isRetentionPolicyValid(configuration), is(false));
        assertThat(result.getData().get(0).getMessageId(), is(35L));
        assertThat(result.getData().get(0).getSenderName(), is("bla bla"));
        assertThat(result.getData().get(0).getHasAttachments(), is(true));
        assertThat(result.getData().get(0).getReadByCurrentUser(), is(true));
        assertThat(result.getData().get(0).getRetentionMetadata(), is(RetentionMetadata.INVALID_RETENTION_POLICY_SETTINGS_INSTANCE));
        assertThat(result.getTotalRowCount(), is(5L));

        verify(messageDAO).countMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))));
        verify(messageDAO).getMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt());
        verify(messageDAO).getSubMessagesReadByUser(argThat(sameInstance(messages)), argThat(is(equalTo(user.getId()))));
        verify(messageDAO).getMessageIdsWithBinaryAttachments(argThat(sameInstance(messages)));

        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetMessagesWithSpecificStatus() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);
        queryParams.setMessageStatus(MessageListQueryParams.MessageStatus.READ);
        User user = new User();
        user.setId(77L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(1);
        configuration.setRetentionPolicyWarningBeforeDays(10);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        Date createdOn = new Date();
        Date updatedOn = DateUtils.addDays(createdOn, 2);

        RetentionMetadata retentionMetadata = new RetentionMetadata();
        retentionMetadata.setRetentionExpired(false);
        retentionMetadata.setRetentionWarning(false);
        retentionMetadata.setExpiredOn(null);

        List<Message> messages = new ArrayList<>();

        Party remoteParty = new Party();
        remoteParty.setDisplayName("bla bla");

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setUpdatedOn(updatedOn);
        message.setId(35L);
        message.setContent("myContent");
        message.setMsgState(MessageState.INCOMING);
        message.setRemoteParty(remoteParty);
        message.setLocalParty(recipient);

        messages.add(message);

        when(messageDAO.countMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(MessageStatus.Status.class))), argThat(is(any(Party.class))))).thenReturn(5L);
        when(messageDAO.getMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(MessageStatus.Status.class))), argThat(is(any(Party.class))),
                argThat(is(MessageColumn.RECEIVED)), anyBoolean(), anyInt(), anyInt())).thenReturn(messages);
        when(messageDAO.getSubMessagesReadByUser(messages, user.getId())).thenReturn(Collections.singletonList(message.getId()));
        when(messageDAO.getMessageIdsWithBinaryAttachments(messages)).thenReturn(Collections.singletonList(message.getId()));

        when(configurationService.getConfiguration()).thenReturn(configuration);

        // DO THE ACTUAL CALL
        ResultDataList<MessageListEntry> result = mailboxManagerImpl.getMessages(queryParams, user, MessageState.INCOMING, message.getLocalParty());

        assertThat(configurationService.isRetentionPolicyValid(configuration), is(false));
        assertThat(result.getData().get(0).getCreateDate(), is(createdOn));
        assertThat(result.getData().get(0).getMessageId(), is(35L));
        assertThat(result.getData().get(0).getSenderName(), is("bla bla"));
        assertThat(result.getData().get(0).getHasAttachments(), is(true));
        assertThat(result.getData().get(0).getReadByCurrentUser(), is(true));
        assertThat(result.getData().get(0).getRetentionMetadata(), is(RetentionMetadata.INVALID_RETENTION_POLICY_SETTINGS_INSTANCE));
        assertThat(result.getTotalRowCount(), is(5L));

        verify(messageDAO).countMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(MessageStatus.Status.class))), argThat(is(any(Party.class))));
        verify(messageDAO).getMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(MessageStatus.Status.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt());
        verify(messageDAO).getSubMessagesReadByUser(argThat(sameInstance(messages)), argThat(is(equalTo(user.getId()))));
        verify(messageDAO).getMessageIdsWithBinaryAttachments(argThat(sameInstance(messages)));

        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetDraftMessagesWithLastSavedDate() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);
        queryParams.setMessageStatus(MessageListQueryParams.MessageStatus.READ);
        User user = new User();
        user.setId(77L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(1);
        configuration.setRetentionPolicyWarningBeforeDays(10);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        Date createdOn = new Date();
        Date updatedOn = DateUtils.addDays(createdOn, 2);

        RetentionMetadata retentionMetadata = new RetentionMetadata();
        retentionMetadata.setRetentionExpired(false);
        retentionMetadata.setRetentionWarning(false);
        retentionMetadata.setExpiredOn(null);

        List<Message> messages = new ArrayList<>();

        Party remoteParty = new Party();
        remoteParty.setDisplayName("bla bla");
        remoteParty.setNodeName("remoteParty");

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setUpdatedOn(updatedOn);
        message.setId(35L);
        message.setContent("myContent");
        message.setMsgState(MessageState.DRAFT);
        message.setRemoteParty(remoteParty);
        message.setLocalParty(recipient);

        messages.add(message);

        when(messageDAO.countMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(MessageStatus.Status.class))), argThat(is(any(Party.class))))).thenReturn(5L);
        when(messageDAO.getMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(MessageStatus.Status.class))), argThat(is(any(Party.class))),
                argThat(is(MessageColumn.SAVED)), anyBoolean(), anyInt(), anyInt())).thenReturn(messages);
        when(messageDAO.getSubMessagesReadByUser(messages, user.getId())).thenReturn(Collections.singletonList(message.getId()));
        when(messageDAO.getMessageIdsWithBinaryAttachments(messages)).thenReturn(Collections.singletonList(message.getId()));

        when(configurationService.getConfiguration()).thenReturn(configuration);

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        ResultDataList<MessageListEntry> result = mailboxManagerImpl.getMessages(queryParams, user, MessageState.DRAFT, message.getLocalParty());

        assertThat(configurationService.isRetentionPolicyValid(configuration), is(false));
        assertThat(result.getData().get(0).getCreateDate(), is(updatedOn));
        assertThat(result.getData().get(0).getMessageId(), is(35L));
        assertThat(result.getData().get(0).getSenderName(), is("bla bla"));
        assertThat(result.getData().get(0).getHasAttachments(), is(true));
        assertThat(result.getData().get(0).getReadByCurrentUser(), is(true));
        assertThat(result.getData().get(0).getRetentionMetadata(), is(RetentionMetadata.INVALID_RETENTION_POLICY_SETTINGS_INSTANCE));
        assertThat(result.getData().get(0).isHasIca(), is(true));
        assertThat(result.getTotalRowCount(), is(5L));

        verify(messageDAO).countMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(MessageStatus.Status.class))), argThat(is(any(Party.class))));
        verify(messageDAO).getMessages(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(MessageStatus.Status.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt());
        verify(messageDAO).getSubMessagesReadByUser(argThat(sameInstance(messages)), argThat(is(equalTo(user.getId()))));
        verify(messageDAO).getMessageIdsWithBinaryAttachments(argThat(sameInstance(messages)));

        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetMessagesUnread() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);
        queryParams.setUnreadOnly(true);
        User user = new User();
        user.setId(77L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Party remoteParty = new Party();
        remoteParty.setNodeName("bla bla");

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        Date createdOn = new Date();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("mySubject");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(new Party());
        message.setRemoteParty(remoteParty);

        messages.add(message);

        List<Message> resultDataList = new ArrayList<>(messages);
        when(messageDAO.countUnreadMessages(argThat(is(sameInstance(user))), anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class)))))
                .thenReturn(1L);
        when(messageDAO.getUnreadMessages(argThat(is(sameInstance(user))), anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt())).thenReturn(resultDataList);

        Configuration configuration = new Configuration();
        when(configurationService.getConfiguration()).thenReturn(configuration);

        ResultDataList<MessageListEntry> result = mailboxManagerImpl.getMessages(queryParams, user, MessageState.INCOMING, message.getLocalParty());

        assertThat(result.getData().get(0).getSubject(), is("mySubject"));
        assertThat(result.getData().get(0).getMessageId(), is(45L));
        assertThat(result.getTotalRowCount(), is(1L));
        verify(messageDAO).countUnreadMessages(argThat(is(sameInstance(user))), anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))));
        verify(messageDAO).getUnreadMessages(argThat(is(sameInstance(user))), anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt());

    }

    @Test
    public void testGetMessageDetailsAlreadyRead() throws Exception {
        Message message = new Message();
        long messageId = 33L;
        message.setId(messageId);
        User user = new User();
        user.setId(44L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Party remoteParty = new Party();
        remoteParty.setNodeName("bla bla");

        Message messageData = new Message();
        messageData.setContent("myContent");
        messageData.setId(33L);
        messageData.setMsgState(MessageState.DRAFT);
        messageData.setLocalParty(recipient);
        messageData.setRemoteParty(remoteParty);

        when(messageDAO.findById(33L)).thenReturn(messageData);
        when(messageDAO.getSubMessagesReadByUser(Collections.singletonList(messageData), user.getId())).thenReturn(Collections.singletonList(messageData.getId()));

        Message messageVODetails = mailboxManagerImpl.getMessageDetails(messageId, user);

        assertThat(messageVODetails.getContent(), is("myContent"));
        verify(messageDAO).findById(33L);
        verify(messageDAO).getSubMessagesReadByUser(Collections.singletonList(messageData), user.getId());
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetMessageDetails() throws Exception {
        Message message = new Message();
        long messageId = 33L;
        message.setId(messageId);
        User user = new User();
        user.setId(44L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Party remoteParty = new Party();
        remoteParty.setNodeName("bla bla");

        Message messageData = new Message();
        messageData.setContent("myContent");
        messageData.setId(33L);
        messageData.setMsgState(MessageState.DRAFT);
        messageData.setLocalParty(recipient);
        messageData.setRemoteParty(remoteParty);

        when(messageDAO.findById(33L)).thenReturn(messageData);
        when(messageDAO.getSubMessagesReadByUser(Collections.singletonList(messageData), user.getId())).thenReturn(Collections.<Long>emptyList());

        Message messageVODetails = mailboxManagerImpl.getMessageDetails(messageId, user);

        assertThat(messageVODetails.getContent(), is("myContent"));
        verify(messageReadStatusDAO).markMessageReadByUser(33L, 44L);
        verify(messageDAO).findById(33L);
        verify(messageDAO).getSubMessagesReadByUser(Collections.singletonList(messageData), user.getId());
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetMessagesDraftInboxIcas() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);
        queryParams.setUnreadOnly(true);
        User user = new User();
        user.setId(1L);

        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("Remote party 1");
        remoteParty1.setNodeName("remoteParty1");

        Party remoteParty2 = new Party();
        remoteParty2.setNodeName("Remote party 2");
        remoteParty2.setNodeName("remoteParty2");

        Party remoteParty3 = new Party();
        remoteParty3.setNodeName("Remote party 3");
        remoteParty3.setNodeName("remoteParty3");

        Party remoteParty4 = new Party();
        remoteParty4.setNodeName("Remote party 4");
        remoteParty4.setNodeName("remoteParty4");

        List<Message> messages = new ArrayList<>();
        Date createdOn = new Date();

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(1L);
        message.setSubject("Message 1");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setRemoteParty(remoteParty1);
        messages.add(message);

        message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(2L);
        message.setSubject("Message 2");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setRemoteParty(remoteParty2);
        messages.add(message);

        message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(3L);
        message.setSubject("Message 3");
        message.setMsgState(MessageState.DRAFT);
        message.setLocalParty(recipient);
        message.setRemoteParty(remoteParty3);
        messages.add(message);

        message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(4L);
        message.setSubject("Message 4");
        message.setMsgState(MessageState.DRAFT);
        message.setLocalParty(recipient);
        message.setRemoteParty(remoteParty4);
        messages.add(message);

        List<Message> resultDataList = new ArrayList<>(messages);
        when(messageDAO.countUnreadMessages(argThat(is(sameInstance(user))), anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class)))))
                .thenReturn(4L);
        when(messageDAO.getUnreadMessages(argThat(is(sameInstance(user))), anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt())).thenReturn(resultDataList);

        when(icaHelper.hasIca(resultDataList.get(0))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(resultDataList.get(1))).thenReturn(Boolean.FALSE);
        when(icaHelper.hasIca(resultDataList.get(2))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(resultDataList.get(3))).thenReturn(Boolean.FALSE);

        ResultDataList<MessageListEntry> result = mailboxManagerImpl.getMessages(queryParams, user, MessageState.INCOMING, message.getLocalParty());

        assertThat(result.getTotalRowCount(), is(4L));
        assertThat(result.getData().get(0).isHasIca(), is(true));
        assertThat(result.getData().get(1).isHasIca(), is(false));
        assertThat(result.getData().get(2).isHasIca(), is(true));
        assertThat(result.getData().get(3).isHasIca(), is(false));

        verify(icaHelper).hasIca(resultDataList.get(0));
        verify(icaHelper).hasIca(resultDataList.get(1));
        verify(icaHelper).hasIca(resultDataList.get(2));
        verify(icaHelper).hasIca(resultDataList.get(3));
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void test_SaveOrUpdateUpdate_should_saveAndMarkUserAsRead_when_userIsValid() throws Exception {
        Message message = new Message();
        User user = new User();
        user.setId(4567L);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Message message = (Message) invocationOnMock.getArguments()[0];
                message.setId(100L);
                return null;
            }
        }).when(messageDAO).save(argThat((any(Message.class))));

        Message savedMsg = mailboxManagerImpl.saveOrUpdateMessage(message, user);

        assertThat(savedMsg, notNullValue());
        assertThat(savedMsg.getId(), is(100L));
        assertThat(message.getId(), is(equalTo(100L)));
        verify(messageDAO).save(argThat(any(Message.class)));
        verify(messageReadStatusDAO).markMessageReadByUser(100L, user.getId());

        verifyNoMoreInteractions(messageDAO, messageReadStatusDAO);
    }

    @Test
    public void test_SaveOrUpdateUpdate_should_saveNotMarkUserAsRead_when_userIsNull() throws Exception {
        Message message = new Message();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Message message = (Message) invocationOnMock.getArguments()[0];
                message.setId(100L);
                return null;
            }
        }).when(messageDAO).save(argThat((any(Message.class))));

        Message savedMsg = mailboxManagerImpl.saveOrUpdateMessage(message, null);

        assertThat(savedMsg, notNullValue());
        assertThat(savedMsg.getId(), is(100L));
        assertThat(message.getId(), is(equalTo(100L)));
        verify(messageDAO).save(argThat(any(Message.class)));

        verifyNoMoreInteractions(messageDAO, messageReadStatusDAO);
    }

    @Test
    public void testSaveOrUpdateUpdate() throws Exception {
        Message message = new Message();
        message.setId(100L);

        Message savedMsg = mailboxManagerImpl.saveOrUpdateMessage(message, null);

        assertThat(savedMsg.getId(), is(100L));
        verify(messageDAO).update(argThat(sameInstance(message)));
        verifyNoMoreInteractions(messageDAO, messageReadStatusDAO);
    }

    @Test
    public void testSaveOrUpdateUpdateWeirdState() throws Exception {
        Message message = new Message();

        mailboxManagerImpl.saveOrUpdateMessage(message, null);

        verify(messageDAO).save(argThat(sameInstance(message)));
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetMessagesByDossier() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);
        User user = new User();
        user.setId(77L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("remoteParty1");

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setId(45L);
        message.setSubject("mySubject");
        message.setReferenceId("dos");
        message.setMsgState(MessageState.INCOMING);
        message.setRemoteParty(remoteParty1);
        message.setLocalParty(recipient);

        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("Payload");
        attachment.setType(AttachmentType.METADATA);
        attachmentsList.add(attachment);

        message.setAttachments(attachmentsList);

        messages.add(message);

        Party remoteParty2 = new Party();
        remoteParty2.setDisplayName("ello");
        remoteParty2.setNodeName("remoteParty2");

        message = new Message();
        message.setId(35L);
        message.setContent("myContent");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setReferenceId("dossier1");
        message.setRemoteParty(remoteParty2);

        messages.add(message);

        Message secondMessage = new Message();
        secondMessage.setId(35L);
        secondMessage.setContent("myContent");
        secondMessage.setMsgState(MessageState.INCOMING);
        secondMessage.setLocalParty(recipient);
        secondMessage.setReferenceId("dossier1");
        secondMessage.setRemoteParty(remoteParty2);

        messages.add(secondMessage);


        when(messageDAO.countMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))))).thenReturn(5L);
        when(messageDAO.getMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(MessageColumn.RECEIVED)), anyBoolean(), anyInt(), anyInt())).thenReturn(messages);
        when(messageDAO.getSubMessagesReadByUser(messages, user.getId())).thenReturn(Collections.singletonList(secondMessage.getId()));
        when(messageDAO.getMessageIdsWithBinaryAttachments(messages)).thenReturn(Collections.singletonList(secondMessage.getId()));

        when(icaHelper.hasIca(messages.get(0))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(messages.get(1))).thenReturn(Boolean.TRUE);

        DossierResult result = mailboxManagerImpl.getDossiers(queryParams, user,
                MessageState.INCOMING, secondMessage.getLocalParty());

        assertThat(result.getDossierMap().get("dos").size(), is(1));
        assertThat(result.getDossierMap().get("dossier1").size(), is(2));

        assertThat(result.getDossierMap().get("dos").get(0).getSubject(), is("mySubject"));
        assertThat(result.getDossierMap().get("dos").get(0).getMessageId(), is(45L));
        assertThat(result.getDossierMap().get("dos").get(0).getSenderName(), is(nullValue()));
        assertThat(result.getDossierMap().get("dos").get(0).getHasAttachments(), is(false));
        assertThat(result.getDossierMap().get("dos").get(0).getReadByCurrentUser(), is(false));
        assertThat(result.getDossierMap().get("dos").get(0).isHasIca(), is(true));
        assertThat(messages.get(0).getAttachments().get(0).getType(), is(AttachmentType.BINARY));
        assertThat(messages.get(0).getAttachments().get(1).getType(), is(AttachmentType.METADATA));
        assertThat(result.getDossierMap().get("dossier1").get(0).getMessageId(), is(35L));
        assertThat(result.getDossierMap().get("dossier1").get(0).isHasIca(), is(true));
        assertThat(result.getDossierMap().get("dossier1").get(1).getSenderName(), is("ello"));
        assertThat(result.getDossierMap().get("dossier1").get(1).getHasAttachments(), is(true));
        assertThat(result.getDossierMap().get("dossier1").get(1).getReadByCurrentUser(), is(true));
        assertThat(result.getDossierMap().get("dossier1").get(1).isHasIca(), is(true));

        assertThat(result.getTotalResultSize(), is(5L));
        verify(messageDAO).countMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))));
        verify(messageDAO).getMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt());
        verify(messageDAO).getSubMessagesReadByUser(argThat(sameInstance(messages)), argThat(is(equalTo(user.getId()))));
        verify(messageDAO).getMessageIdsWithBinaryAttachments(argThat(sameInstance(messages)));
        verifyNoMoreInteractions(messageDAO);

    }

    @Test
    public void testGetMessagesOrderedAscByDossier() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.ASC);

        User user = new User();
        user.setId(77L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Party remoteParty = new Party();
        remoteParty.setNodeName("bla bla");
        remoteParty.setNodeName("remoteParty");

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setId(45L);
        message.setSubject("mySubject");
        message.setReferenceId("dos");
        message.setMsgState(MessageState.INCOMING);
        message.setRemoteParty(remoteParty);
        message.setLocalParty(recipient);

        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("Payload");
        attachment.setType(AttachmentType.METADATA);
        attachmentsList.add(attachment);

        message.setAttachments(attachmentsList);

        messages.add(message);

        message = new Message();
        message.setId(35L);
        message.setContent("myContent");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setReferenceId("dossier1");
        message.setRemoteParty(remoteParty);

        messages.add(message);

        message = new Message();
        message.setId(35L);
        message.setContent("myContent");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setReferenceId("dossier1");
        message.setRemoteParty(remoteParty);

        messages.add(message);

        when(messageDAO.countMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))))).thenReturn(5L);

        when(messageDAO.getMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt())).thenReturn(messages);

        when(icaHelper.hasIca(messages.get(0))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(messages.get(1))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(messages.get(2))).thenReturn(Boolean.TRUE);

        DossierResult result = mailboxManagerImpl.getDossiers(queryParams, user,
                MessageState.INCOMING, message.getLocalParty());

        assertThat(result.getDossierMap().get("dos").size(), is(1));
        assertThat(result.getDossierMap().get("dossier1").size(), is(2));

        assertThat(result.getDossierMap().get("dos").get(0).getSubject(), is("mySubject"));
        assertThat(result.getDossierMap().get("dos").get(0).getMessageId(), is(45L));
        assertThat(result.getDossierMap().get("dos").get(0).isHasIca(), is(true));
        assertThat(messages.get(0).getAttachments().get(0).getType(), is(AttachmentType.BINARY));
        assertThat(messages.get(0).getAttachments().get(1).getType(), is(AttachmentType.METADATA));
        assertThat(result.getDossierMap().get("dossier1").get(0).getMessageId(), is(35L));
        assertThat(result.getDossierMap().get("dossier1").get(0).isHasIca(), is(true));

        assertThat(result.getTotalResultSize(), is(5L));

        verify(messageDAO).countMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))));
        verify(messageDAO).getMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt());
        verify(messageDAO).getSubMessagesReadByUser(argThat(sameInstance(messages)), argThat(is(equalTo(user.getId()))));
        verify(messageDAO).getMessageIdsWithBinaryAttachments(argThat(sameInstance(messages)));
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetMessagesOrderedDescByDossier() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);

        User user = new User();
        user.setId(77L);
        User userEntity = new User();
        userEntity.setId(user.getId());

        Party remoteParty = new Party();
        remoteParty.setNodeName("bla bla");
        remoteParty.setNodeName("remoteParty");

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setId(45L);
        message.setSubject("mySubject");
        message.setReferenceId("dos");
        message.setMsgState(MessageState.INCOMING);
        message.setCreatedOn(new Date(27 / 04 / 2012));
        message.setRemoteParty(remoteParty);
        message.setLocalParty(recipient);

        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("Payload");
        attachment.setType(AttachmentType.METADATA);
        attachmentsList.add(attachment);

        message.setAttachments(attachmentsList);

        messages.add(message);

        message = new Message();
        message.setId(35L);
        message.setContent("myContent B");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setReferenceId("dossier1");
        message.setCreatedOn(new Date(24 / 04 / 2012));
        message.setRemoteParty(remoteParty);

        messages.add(message);

        message = new Message();
        message.setId(36L);
        message.setContent("myContent A");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setReferenceId("dossier1");
        message.setCreatedOn(new Date(22 / 04 / 2012));
        message.setRemoteParty(remoteParty);

        messages.add(message);

        when(messageDAO.countMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))))).thenReturn(5L);

        when(messageDAO.getMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt())).thenReturn(messages);

        when(icaHelper.hasIca(messages.get(0))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(messages.get(1))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(messages.get(2))).thenReturn(Boolean.TRUE);

        DossierResult result = mailboxManagerImpl.getDossiers(queryParams, user,
                MessageState.INCOMING, message.getLocalParty());

        assertThat(result.getDossierMap().get("dos").size(), is(1));
        assertThat(result.getDossierMap().get("dossier1").size(), is(2));

        assertThat(result.getDossierMap().get("dos").get(0).getSubject(), is("mySubject"));
        assertThat(result.getDossierMap().get("dos").get(0).getMessageId(), is(45L));
        assertThat(result.getDossierMap().get("dos").get(0).isHasIca(), is(true));
        assertThat(messages.get(0).getAttachments().get(0).getType(), is(AttachmentType.BINARY));
        assertThat(messages.get(0).getAttachments().get(1).getType(), is(AttachmentType.METADATA));
        assertThat(result.getDossierMap().get("dossier1").get(0).getMessageId(), is(35L));
        assertThat(result.getDossierMap().get("dossier1").get(0).isHasIca(), is(true));

        assertThat(result.getTotalResultSize(), is(5L));
        verify(messageDAO).countMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))));
        verify(messageDAO).getMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(any(MessageColumn.class))), anyBoolean(), anyInt(), anyInt());
        verify(messageDAO).getSubMessagesReadByUser(argThat(sameInstance(messages)), argThat(is(equalTo(user.getId()))));
        verify(messageDAO).getMessageIdsWithBinaryAttachments(argThat(sameInstance(messages)));

        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetMessagesByDossierDraftInboxIcas() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setSortDirection(MessageListQueryParams.SortDirection.DESC);
        User user = new User();
        user.setId(1L);

        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("Remote party 1");
        remoteParty1.setNodeName("remoteParty1");

        Party remoteParty2 = new Party();
        remoteParty2.setNodeName("Remote party 2");
        remoteParty2.setNodeName("remoteParty2");

        Party remoteParty3 = new Party();
        remoteParty3.setNodeName("Remote party 3");
        remoteParty3.setNodeName("remoteParty3");

        Party remoteParty4 = new Party();
        remoteParty4.setNodeName("Remote party 4");
        remoteParty4.setNodeName("remoteParty4");

        List<Message> messages = new ArrayList<>();
        Date createdOn = new Date();

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(1L);
        message.setReferenceId("dossier1");
        message.setSubject("Message 1");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setRemoteParty(remoteParty1);
        messages.add(message);

        message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(2L);
        message.setReferenceId("dossier1");
        message.setSubject("Message 2");
        message.setMsgState(MessageState.INCOMING);
        message.setLocalParty(recipient);
        message.setRemoteParty(remoteParty2);
        messages.add(message);

        message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(3L);
        message.setReferenceId("dossier2");
        message.setSubject("Message 3");
        message.setMsgState(MessageState.DRAFT);
        message.setLocalParty(recipient);
        message.setRemoteParty(remoteParty3);
        messages.add(message);

        message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(4L);
        message.setReferenceId("dossier2");
        message.setSubject("Message 4");
        message.setMsgState(MessageState.DRAFT);
        message.setLocalParty(recipient);
        message.setRemoteParty(remoteParty4);
        messages.add(message);

        when(messageDAO.getMessagesByDossier(anyString(), argThat(is(any(MessageState.class))), argThat(is(any(Party.class))),
                argThat(is(MessageColumn.RECEIVED)), anyBoolean(), anyInt(), anyInt())).thenReturn(messages);

        when(icaHelper.hasIca(messages.get(0))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(messages.get(1))).thenReturn(Boolean.FALSE);
        when(icaHelper.hasIca(messages.get(2))).thenReturn(Boolean.TRUE);
        when(icaHelper.hasIca(messages.get(3))).thenReturn(Boolean.FALSE);

        DossierResult result = mailboxManagerImpl.getDossiers(queryParams, user, MessageState.INCOMING, message.getLocalParty());

        assertThat(result.getDossierMap().get("dossier1").size(), is(2));
        assertThat(result.getDossierMap().get("dossier2").size(), is(2));

        assertThat(result.getDossierMap().get("dossier1").get(0).isHasIca(), is(true));
        assertThat(result.getDossierMap().get("dossier1").get(1).isHasIca(), is(false));
        assertThat(result.getDossierMap().get("dossier2").get(0).isHasIca(), is(true));
        assertThat(result.getDossierMap().get("dossier2").get(1).isHasIca(), is(false));

        verify(icaHelper).hasIca(messages.get(0));
        verify(icaHelper).hasIca(messages.get(1));
        verify(icaHelper).hasIca(messages.get(2));
        verify(icaHelper).hasIca(messages.get(3));
        verifyNoMoreInteractions(icaManager);
    }


    @Test
    public void testDisableMessage() {
        Message message = new Message();
        long messageId = 33L;
        message.setId(messageId);
        message.setActiveState(true);

        when(messageDAO.findById(33L)).thenReturn(message);

        // DO THE ACTUAL CALL
        mailboxManagerImpl.disableMessage(message);

        assertThat(message.getActiveState(), is(false));
    }

    @Test
    public void testCreateMessageStatus_should_returnNull_when_noLocalParty() {
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String referenceId = "referenceId1";
        String messageUuid = "uuid1";
        String statusCode = "BDL:7";
        MessageStatus.Status status = MessageStatus.Status.READ;
        String userLogin = "user1";
        String referenceType = "MESSAGE_BUNDLE";

        // DO THE ACTUAL CALL
        MessageStatus messageStatus = mailboxManagerImpl.createMessageStatus(localPartyName, remotePartyName, referenceId, messageUuid, referenceType, status, statusCode, userLogin);

        assertThat(messageStatus, is(nullValue()));
        verify(partyDAO).getWebManagedPartyByNodeName(localPartyName);
        verifyNoMoreInteractions(partyDAO);
        verifyZeroInteractions(messageDAO);
        verifyZeroInteractions(nodeMessageTriggerService);
        verifyZeroInteractions(messageStatusDAO);
    }

    @Test
    public void testCreateMessageStatus_should_returnNull_when_localPartyExists_and_noMessage() {
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String referenceId = "referenceId1";
        String messageUuid = "uuid1";
        String statusCode = "BDL:7";
        MessageStatus.Status status = MessageStatus.Status.READ;
        String userLogin = "user1";
        String referenceType = "MESSAGE_BUNDLE";

        Party localParty = new Party();
        localParty.setId(1L);

        when(partyDAO.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);

        // DO THE ACTUAL CALL
        MessageStatus messageStatus = mailboxManagerImpl.createMessageStatus(localPartyName, remotePartyName, referenceId, messageUuid, referenceType, status, statusCode, userLogin);

        assertThat(messageStatus, is(nullValue()));
        verify(partyDAO).getWebManagedPartyByNodeName(localPartyName);
        verifyNoMoreInteractions(partyDAO);
        verify(messageDAO).findByMessageBundleId(messageUuid, localParty.getId(), remotePartyName);
        verifyNoMoreInteractions(messageDAO);
        verifyZeroInteractions(nodeMessageTriggerService);
        verifyZeroInteractions(messageStatusDAO);
    }

    @Test
    public void testCreateMessageStatus_should_createMessageStatus_when_localPartyAndMessageExists() {
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String referenceId = "referenceId1";
        String messageUuid = "uuid1";
        String statusCode = "BDL:7";
        MessageStatus.Status status = MessageStatus.Status.READ;
        String userLogin = "user1";
        String referenceType = "MESSAGE_BUNDLE";

        Party localParty = new Party();
        localParty.setId(1L);
        localParty.setConsumeNodeMessages(false);

        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);

        Message message = new Message();
        message.setId(100L);
        message.setBundleId(messageUuid);
        message.setLocalParty(localParty);
        message.setRemoteParty(remoteParty);

        when(partyDAO.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);
        when(messageDAO.findByMessageBundleId(messageUuid, localParty.getId(), remotePartyName)).thenReturn(message);
        when(messageStatusDAO.saveOrUpdateMessageStatus(argThat(is(any(MessageStatus.class))))).thenAnswer(new Answer<MessageStatus>() {
            @Override
            public MessageStatus answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (MessageStatus) args[0];
            }
        });

        // DO THE ACTUAL CALL
        MessageStatus messageStatus = mailboxManagerImpl.createMessageStatus(localPartyName, remotePartyName, referenceId, messageUuid, referenceType, status, statusCode, userLogin);

        assertThat(messageStatus, is(notNullValue()));
        verify(partyDAO).getWebManagedPartyByNodeName(localPartyName);
        verify(messageDAO).findByMessageBundleId(messageUuid, localParty.getId(), remotePartyName);
        verify(messageDAO).update(message);
        verify(messageStatusDAO).saveOrUpdateMessageStatus(messageStatus);

        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(messageDAO);
        verifyNoMoreInteractions(messageStatusDAO);
        verifyZeroInteractions(nodeMessageTriggerService);

        assertThat(messageStatus.getMessage(), is(message));
        assertThat(messageStatus.getMstState(), is(MessageStatus.State.INCOMING));
        assertThat(messageStatus.getMstStatus(), is(MessageStatus.Status.READ));
        assertThat(messageStatus.getParent(), is(nullValue()));
    }

    @Test
    public void testCreateMessageStatus_should_triggerRetrieveMessage_when_localPartyConsumesNodeStatuses() {
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String statusUuid = "statusUuid";
        String referenceUuid = "uuid1";
        String statusCode = "BDL:7";
        MessageStatus.Status status = MessageStatus.Status.READ;
        String userLogin = "user1";
        String referenceType = "MESSAGE_BUNDLE";

        Party localParty = new Party();
        localParty.setId(1L);
        localParty.setConsumeNodeMessages(true);

        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);

        Message message = new Message();
        message.setId(100L);
        message.setBundleId(referenceUuid);
        message.setLocalParty(localParty);
        message.setRemoteParty(remoteParty);

        when(partyDAO.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);
        when(messageDAO.findByMessageBundleId(referenceUuid, localParty.getId(), remotePartyName)).thenReturn(message);
        when(messageStatusDAO.saveOrUpdateMessageStatus(argThat(is(any(MessageStatus.class))))).thenAnswer(new Answer<MessageStatus>() {
            @Override
            public MessageStatus answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (MessageStatus) args[0];
            }
        });

        // DO THE ACTUAL CALL
        MessageStatus messageStatus = mailboxManagerImpl.createMessageStatus(localPartyName, remotePartyName, statusUuid, referenceUuid, referenceType, status, statusCode, userLogin);

        assertThat(messageStatus, is(notNullValue()));
        verify(partyDAO).getWebManagedPartyByNodeName(localPartyName);
        verify(messageDAO).findByMessageBundleId(referenceUuid, localParty.getId(), remotePartyName);
        verify(messageDAO).update(message);
        verify(messageStatusDAO).saveOrUpdateMessageStatus(messageStatus);

        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(messageDAO);
        verifyNoMoreInteractions(messageStatusDAO);
        verifyNoMoreInteractions(nodeMessageTriggerService);

        assertThat(messageStatus.getMessage(), is(message));
        assertThat(messageStatus.getMstState(), is(MessageStatus.State.INCOMING));
        assertThat(messageStatus.getMstStatus(), is(MessageStatus.Status.READ));
        assertThat(messageStatus.getParent(), is(nullValue()));
    }

    @Test
    public void testCreateMessageStatus_should_notTriggerRetrieveMessage_when_localPartyConsumesNodeStatusesAndAdapterRequest() {
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String referenceId = "referenceId1";
        String referenceUuid = "uuid1";
        String statusCode = "BDL:7";
        MessageStatus.Status status = MessageStatus.Status.READ;
        String userLogin = "user1";
        String referenceType = "MESSAGE_BUNDLE";

        Party localParty = new Party();
        localParty.setId(1L);
        localParty.setConsumeNodeMessages(true);

        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);

        Message message = new Message();
        message.setId(100L);
        message.setBundleId(referenceUuid);
        message.setLocalParty(localParty);
        message.setRemoteParty(remoteParty);

        when(partyDAO.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);
        when(messageDAO.findByMessageBundleId(referenceUuid, localParty.getId(), remotePartyName)).thenReturn(message);
        when(messageStatusDAO.saveOrUpdateMessageStatus(argThat(is(any(MessageStatus.class))))).thenAnswer(new Answer<MessageStatus>() {
            @Override
            public MessageStatus answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (MessageStatus) args[0];
            }
        });

        // DO THE ACTUAL CALL
        MessageStatus messageStatus = mailboxManagerImpl.createMessageStatus(localPartyName, remotePartyName, referenceId, referenceUuid, referenceType, status, statusCode, userLogin);

        assertThat(messageStatus, is(notNullValue()));
        verify(partyDAO).getWebManagedPartyByNodeName(localPartyName);
        verify(messageDAO).findByMessageBundleId(referenceUuid, localParty.getId(), remotePartyName);
        verify(messageDAO).update(message);
        verify(messageStatusDAO).saveOrUpdateMessageStatus(messageStatus);

        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(messageDAO);
        verifyNoMoreInteractions(messageStatusDAO);
        verifyZeroInteractions(nodeMessageTriggerService);

        assertThat(messageStatus.getMessage(), is(message));
        assertThat(messageStatus.getMstState(), is(MessageStatus.State.INCOMING));
        assertThat(messageStatus.getMstStatus(), is(MessageStatus.Status.READ));
        assertThat(messageStatus.getParent(), is(nullValue()));
    }

    @Test
    public void testCreateMessageStatus_should_createMessageStatus_when_messageStatusType() {
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String referenceId = "referenceId1";
        String messageUuid = "uuid1";
        String statusCode = "BDL:7";
        MessageStatus.Status status = MessageStatus.Status.READ;
        String userLogin = "user1";
        String referenceType = "MESSAGE_STATUS";

        Party localParty = new Party();
        localParty.setId(1L);
        localParty.setConsumeNodeMessages(false);

        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);

        Message message = new Message();
        message.setId(100L);
        message.setBundleId(messageUuid);
        message.setLocalParty(localParty);
        message.setRemoteParty(remoteParty);

        MessageStatus refMessageStatus = new MessageStatus();
        refMessageStatus.setStatusUuid(referenceId);
        refMessageStatus.setMessage(message);

        when(partyDAO.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);
        when(messageStatusDAO.saveOrUpdateMessageStatus(argThat(is(any(MessageStatus.class))))).thenAnswer(new Answer<MessageStatus>() {
            @Override
            public MessageStatus answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (MessageStatus) args[0];
            }
        });
        when(messageStatusDAO.findByStatusUuid(messageUuid)).thenReturn(refMessageStatus);

        // DO THE ACTUAL CALL
        MessageStatus messageStatus = mailboxManagerImpl.createMessageStatus(localPartyName, remotePartyName, referenceId, messageUuid, referenceType, status, statusCode, userLogin);

        assertThat(messageStatus, is(notNullValue()));
        verify(partyDAO).getWebManagedPartyByNodeName(localPartyName);
        verify(messageStatusDAO).findByStatusUuid(messageUuid);
        verify(messageStatusDAO).saveOrUpdateMessageStatus(messageStatus);
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(messageDAO);
        verifyNoMoreInteractions(messageStatusDAO);
        verifyZeroInteractions(nodeMessageTriggerService);

        assertThat(messageStatus.getMessage(), is(nullValue()));
        assertThat(messageStatus.getMstState(), is(MessageStatus.State.INCOMING));
        assertThat(messageStatus.getMstStatus(), is(MessageStatus.Status.READ));
        assertThat(messageStatus.getParent(), is(refMessageStatus));
    }

    @Test
    public void testCreateMessageStatus_should_returnNull_when_messageStatusTypeAndNoReferenceMessageStatus() {
        String localPartyName = "localParty";
        String remotePartyName = "remoteParty";
        String referenceId = "referenceId1";
        String messageUuid = "uuid1";
        String statusCode = "BDL:7";
        MessageStatus.Status status = MessageStatus.Status.READ;
        String userLogin = "user1";
        String referenceType = "MESSAGE_STATUS";

        Party localParty = new Party();
        localParty.setId(1L);

        when(partyDAO.getWebManagedPartyByNodeName(localPartyName)).thenReturn(localParty);
        when(messageStatusDAO.findByStatusUuid(messageUuid)).thenReturn(null);

        // DO THE ACTUAL CALL
        MessageStatus messageStatus = mailboxManagerImpl.createMessageStatus(localPartyName, remotePartyName, referenceId, messageUuid, referenceType, status, statusCode, userLogin);

        assertThat(messageStatus, is(nullValue()));
        verify(partyDAO).getWebManagedPartyByNodeName(localPartyName);
        verify(messageStatusDAO).findByStatusUuid(messageUuid);
        verifyNoMoreInteractions(partyDAO);
        verifyNoMoreInteractions(messageDAO);
        verifyNoMoreInteractions(messageStatusDAO);

        assertThat(messageStatus, is(nullValue()));
    }
}
