package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.services.business.IcaService;
import eu.europa.ec.etrustex.services.business.config.Configuration;
import eu.europa.ec.etrustex.services.business.config.ConfigurationService;
import eu.europa.ec.etrustex.services.model.RestMessage;
import eu.europa.ec.etrustex.services.model.RestMessages;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.jpa.MessageColumn;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class MessageServiceImplTest extends AbstractTest {

    private static final SimpleDateFormat simpleDateFormatterJs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final static SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");


    @InjectMocks
    private MessageServiceImpl messageService = new MessageServiceImpl();

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private IcaService icaService;

    @Mock
    private MessageDAO messageDAO;

    @Test
    public void test_getMessages_should_callMessageDAO() {
        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(2);

        Party party = new Party();
        party.setId(1L);
        party.setNodeName("p1nodeName");

        Party party2 = new Party();
        party2.setId(2L);
        party2.setNodeName("p2nodeName");

        PartyIca partyIca = new PartyIca();
        partyIca.setId(1L);
        partyIca.setConfidentialityCode(3);
        partyIca.setIntegrityCode(0);
        partyIca.setParty(party);
        partyIca.setRemoteParty(party2);
        List<PartyIca> partyIcas = new ArrayList<PartyIca>();
        partyIcas.add(partyIca);
        party.setPartyICAs(partyIcas);

        Date createdOn = new Date();

        List<MessageSignature> messageSignatures = new ArrayList<>();
        MessageSignature messageSignature = new MessageSignature();
        messageSignatures.add(messageSignature);

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("message1Sub");
        message.setContent("myContent");
        message.setLocalParty(party);
        message.setRemoteParty(party2);
        message.setMsgState(Message.MessageState.INCOMING);
        MessageStatus status1 = new MessageStatus();
        status1.setMstStatus(MessageStatus.Status.AVAILABLE);
        status1.setId(1L);
        message.setLastStatus(status1);
        message.setSignatures(messageSignatures);
        List<Attachment> attachmentsList = new ArrayList<>();

        message.setAttachments(attachmentsList);
        messages.add(message);

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(message.getCreatedOn());
        int retentionPolicyWeeks = 2;
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        RestMessages restMessages = new RestMessages();

        RestMessage restMessage1 = new RestMessage();
        restMessage1.setId("45");
        restMessage1.setSender("p1nodeName");
        restMessage1.setRecipient("p2nodeName");
        restMessage1.setContent("myContent");
        restMessage1.setSubject("message1Sub");
        restMessage1.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage1.setAttachmentCount("2");
        restMessage1.setTotalAttachmentSize("25");
        restMessage1.setStatus("AVAILABLE");
        restMessage1.setContentEncrypted("true");
        restMessage1.setExpired("false");
        restMessage1.setSigned("false");
        restMessage1.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));
        restMessages.addMessageListItem(restMessage1);

        String subject = null;
        String startfrom = "1";
        String resultsize = "10";
        boolean isAscending = true;

        String sortby = "asc";

        MessageColumn sortColumn = MessageColumn.RECEIVED;

        MessageStatus.Status messageStatus = MessageStatus.Status.AVAILABLE;

        String status = "AVAILABLE";

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(icaService.getIcasByParty(argThat(is(party)))).thenReturn(partyIcas);
        when(messageDAO.getMessages(subject, Message.MessageState.INCOMING, messageStatus, party, sortColumn, isAscending, Integer.valueOf(startfrom).intValue(), Integer.valueOf(resultsize).intValue())).thenReturn(messages);

        //ACTUAL CALL
        RestMessages result = messageService.getMessages(Message.MessageState.INCOMING, party, startfrom, resultsize, status, subject, sortby);

        verify(messageDAO).getMessages(subject, Message.MessageState.INCOMING, messageStatus, party, sortColumn, isAscending, Integer.valueOf(startfrom).intValue(), Integer.valueOf(resultsize).intValue());
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_getMessages_INCOMING_status_null() {
        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(2);

        Party party = new Party();
        party.setId(1L);
        party.setNodeName("p1nodeName");

        Party party2 = new Party();
        party2.setId(2L);
        party2.setNodeName("p2nodeName");

        PartyIca partyIca = new PartyIca();
        partyIca.setId(1L);
        partyIca.setConfidentialityCode(3);
        partyIca.setIntegrityCode(0);
        partyIca.setParty(party);
        partyIca.setRemoteParty(party2);
        List<PartyIca> partyIcas = new ArrayList<PartyIca>();
        partyIcas.add(partyIca);
        party.setPartyICAs(partyIcas);

        Date createdOn = new Date();

        List<MessageSignature> messageSignatures = new ArrayList<>();
        MessageSignature messageSignature = new MessageSignature();
        messageSignatures.add(messageSignature);

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("message1Sub");
        message.setContent("myContent");
        message.setLocalParty(party);
        message.setRemoteParty(party2);
        message.setMsgState(Message.MessageState.INCOMING);
        MessageStatus status1 = new MessageStatus();
        status1.setMstStatus(MessageStatus.Status.AVAILABLE);
        status1.setId(1L);
        message.setLastStatus(status1);
        message.getLastStatus().setCreatedOn(new Date());
        message.setSentOn(new Date());
        message.setSignatures(messageSignatures);
        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachment.setFileSize(10L);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(23L);
        attachment.setFileName("Payload");
        attachment.setType(AttachmentType.METADATA);
        attachment.setFileSize(15L);
        attachmentsList.add(attachment);

        message.setAttachments(attachmentsList);
        messages.add(message);

        Message secondMessage = new Message();
        secondMessage.setCreatedOn(createdOn);
        secondMessage.setId(35L);
        secondMessage.setContent("myContent2");
        secondMessage.setSubject("message2Sub");
        secondMessage.setLocalParty(party);
        secondMessage.setRemoteParty(party2);
        secondMessage.setAttachments(new ArrayList<Attachment>());
        secondMessage.setMsgState(Message.MessageState.INCOMING);
        MessageStatus status2 = new MessageStatus();
        status2.setMstStatus(MessageStatus.Status.AVAILABLE);
        status2.setId(2L);
        secondMessage.setLastStatus(status2);
        secondMessage.getLastStatus().setCreatedOn(new Date());
        secondMessage.setSentOn(new Date());
        secondMessage.setSignatures(messageSignatures);
        messages.add(secondMessage);

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(message.getCreatedOn());
        int retentionPolicyWeeks = 2;
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        RestMessages restMessages = new RestMessages();

        RestMessage restMessage1 = new RestMessage();
        restMessage1.setId("45");
        restMessage1.setSender("p2nodeName");
        restMessage1.setRecipient("p1nodeName");
        restMessage1.setContent("myContent");
        restMessage1.setSubject("message1Sub");
        restMessage1.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage1.setAttachmentCount("2");
        restMessage1.setTotalAttachmentSize("25");
        restMessage1.setStatus("AVAILABLE");
        restMessage1.setContentEncrypted("true");
        restMessage1.setExpired("false");
        restMessage1.setSigned("true");
        restMessage1.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));
        restMessages.addMessageListItem(restMessage1);

        RestMessage restMessage2 = new RestMessage();
        restMessage2.setId("35");
        restMessage2.setSender("p2nodeName");
        restMessage2.setRecipient("p1nodeName");
        restMessage2.setContent("myContent2");
        restMessage2.setSubject("message2Sub");
        restMessage2.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setAttachmentCount("0");
        restMessage2.setTotalAttachmentSize("0");
        restMessage2.setStatus("AVAILABLE");
        restMessage2.setContentEncrypted("true");
        restMessage2.setExpired("false");
        restMessage2.setSigned("true");
        restMessage2.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage2.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage2.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));
        restMessages.addMessageListItem(restMessage2);


        String subject = null;
        String startfrom = "1";
        String resultsize = "10";

        boolean isAscending = true;

        String sortby = "asc";

        MessageColumn sortColumn = MessageColumn.RECEIVED;

        String status = null;

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(icaService.getIcasByParty(argThat(is(party)))).thenReturn(partyIcas);
        when(messageDAO.getMessages(subject, Message.MessageState.INCOMING, party, sortColumn, isAscending, Integer.valueOf(startfrom).intValue(), Integer.valueOf(resultsize).intValue())).thenReturn(messages);

        //ACTUAL CALL
        RestMessages result = messageService.getMessages(Message.MessageState.INCOMING, party, startfrom, resultsize, status, subject, sortby);

        assertThat(result.getMessageCount(), is("2"));
        assertThat(result.getMessageList().get(0), is(restMessage1));
        assertThat(result.getMessageList().get(1), is(restMessage2));

        verify(messageDAO).getMessages(subject, Message.MessageState.INCOMING, party, sortColumn, isAscending, Integer.valueOf(startfrom).intValue(), Integer.valueOf(resultsize).intValue());
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_getMessages_SENT() {
        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(2);

        Party party = new Party();
        party.setId(1L);
        party.setNodeName("p1nodeName");

        Party party2 = new Party();
        party2.setId(2L);
        party2.setNodeName("p2nodeName");

        PartyIca partyIca = new PartyIca();
        partyIca.setId(1L);
        partyIca.setConfidentialityCode(3);
        partyIca.setIntegrityCode(0);
        partyIca.setParty(party);
        partyIca.setRemoteParty(party2);
        List<PartyIca> partyIcas = new ArrayList<PartyIca>();
        partyIcas.add(partyIca);
        party.setPartyICAs(partyIcas);

        Date createdOn = new Date();

        List<MessageSignature> messageSignatures = new ArrayList<>();

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("message1Sub");
        message.setContent("myContent");
        message.setLocalParty(party);
        message.setRemoteParty(party2);
        message.setMsgState(Message.MessageState.SENT);
        MessageStatus status1 = new MessageStatus();
        status1.setMstStatus(MessageStatus.Status.AVAILABLE);
        status1.setId(1L);
        message.setLastStatus(status1);
        message.getLastStatus().setCreatedOn(new Date());
        message.setSentOn(new Date());
        message.setSignatures(messageSignatures);
        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachment.setFileSize(10L);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(23L);
        attachment.setFileName("Payload");
        attachment.setType(AttachmentType.METADATA);
        attachment.setFileSize(15L);
        attachmentsList.add(attachment);

        message.setAttachments(attachmentsList);
        messages.add(message);

        Message secondMessage = new Message();
        secondMessage.setCreatedOn(createdOn);
        secondMessage.setId(35L);
        secondMessage.setContent("myContent2");
        secondMessage.setSubject("message2Sub");
        secondMessage.setLocalParty(party);
        secondMessage.setRemoteParty(party2);
        secondMessage.setAttachments(new ArrayList<Attachment>());
        secondMessage.setMsgState(Message.MessageState.SENT);
        MessageStatus status2 = new MessageStatus();
        status2.setMstStatus(MessageStatus.Status.AVAILABLE);
        status2.setId(2L);
        secondMessage.setLastStatus(status2);
        secondMessage.getLastStatus().setCreatedOn(new Date());
        secondMessage.setSentOn(new Date());
        secondMessage.setSignatures(messageSignatures);
        messages.add(secondMessage);

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(message.getCreatedOn());
        int retentionPolicyWeeks = 2;
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        RestMessages restMessages = new RestMessages();

        RestMessage restMessage1 = new RestMessage();
        restMessage1.setId("45");
        restMessage1.setSender("p1nodeName");
        restMessage1.setRecipient("p2nodeName");
        restMessage1.setContent("myContent");
        restMessage1.setSubject("message1Sub");
        restMessage1.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage1.setAttachmentCount("2");
        restMessage1.setTotalAttachmentSize("25");
        restMessage1.setStatus("AVAILABLE");
        restMessage1.setContentEncrypted("true");
        restMessage1.setExpired("false");
        restMessage1.setSigned("false");
        restMessage1.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));
        restMessages.addMessageListItem(restMessage1);

        RestMessage restMessage2 = new RestMessage();
        restMessage2.setId("35");
        restMessage2.setSender("p1nodeName");
        restMessage2.setRecipient("p2nodeName");
        restMessage2.setContent("myContent2");
        restMessage2.setSubject("message2Sub");
        restMessage2.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setAttachmentCount("0");
        restMessage2.setTotalAttachmentSize("0");
        restMessage2.setStatus("AVAILABLE");
        restMessage2.setContentEncrypted("true");
        restMessage2.setExpired("false");
        restMessage2.setSigned("false");
        restMessage2.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage2.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage2.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));
        restMessages.addMessageListItem(restMessage2);


        String subject = null;
        String startfrom = "1";
        String resultsize = "10";

        boolean isAscending = true;

        String sortby = "asc";

        MessageColumn sortColumn = MessageColumn.SENT;

        MessageStatus.Status messageStatus = MessageStatus.Status.AVAILABLE;

        String status_ = "AVAILABLE";

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(icaService.getIcasByParty(argThat(is(party)))).thenReturn(partyIcas);
        when(messageDAO.getMessages(subject, Message.MessageState.SENT, messageStatus, party, sortColumn, isAscending, Integer.valueOf(startfrom).intValue(), Integer.valueOf(resultsize).intValue())).thenReturn(messages);

        //ACTUAL CALL
        RestMessages result = messageService.getMessages(Message.MessageState.SENT, party, startfrom, resultsize, status_, subject, sortby);

        assertThat(result.getMessageCount(), is("2"));
        assertThat(result.getMessageList().get(0), is(restMessage1));
        assertThat(result.getMessageList().get(1), is(restMessage2));

        verify(messageDAO).getMessages(subject, Message.MessageState.SENT, messageStatus, party, sortColumn, isAscending, Integer.valueOf(startfrom).intValue(), Integer.valueOf(resultsize).intValue());
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_getMessages_SENT_status_null() {
        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(2);

        Party party = new Party();
        party.setId(1L);
        party.setNodeName("p1nodeName");

        Party party2 = new Party();
        party2.setId(2L);
        party2.setNodeName("p2nodeName");

        PartyIca partyIca = new PartyIca();
        partyIca.setId(1L);
        partyIca.setConfidentialityCode(3);
        partyIca.setIntegrityCode(0);
        partyIca.setParty(party);
        partyIca.setRemoteParty(party2);
        List<PartyIca> partyIcas = new ArrayList<PartyIca>();
        partyIcas.add(partyIca);
        party.setPartyICAs(partyIcas);

        Date createdOn = new Date();

        List<MessageSignature> messageSignatures = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("message1Sub");
        message.setContent("myContent");
        message.setLocalParty(party);
        message.setRemoteParty(party2);
        message.setMsgState(Message.MessageState.SENT);
        MessageStatus status1 = new MessageStatus();
        status1.setMstStatus(MessageStatus.Status.AVAILABLE);
        status1.setId(1L);
        message.setLastStatus(status1);
        message.getLastStatus().setCreatedOn(new Date());
        message.setSentOn(new Date());
        message.setSignatures(messageSignatures);
        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachment.setFileSize(10L);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(23L);
        attachment.setFileName("Payload");
        attachment.setType(AttachmentType.METADATA);
        attachment.setFileSize(15L);
        attachmentsList.add(attachment);

        message.setAttachments(attachmentsList);
        messages.add(message);

        Message secondMessage = new Message();
        secondMessage.setCreatedOn(createdOn);
        secondMessage.setId(35L);
        secondMessage.setContent("myContent2");
        secondMessage.setSubject("message2Sub");
        secondMessage.setLocalParty(party);
        secondMessage.setRemoteParty(party2);
        secondMessage.setAttachments(new ArrayList<Attachment>());
        secondMessage.setMsgState(Message.MessageState.SENT);
        MessageStatus status2 = new MessageStatus();
        status2.setMstStatus(MessageStatus.Status.AVAILABLE);
        status2.setId(2L);
        secondMessage.setLastStatus(status2);
        secondMessage.getLastStatus().setCreatedOn(new Date());
        secondMessage.setSentOn(new Date());
        secondMessage.setSignatures(messageSignatures);
        messages.add(secondMessage);

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(message.getCreatedOn());
        int retentionPolicyWeeks = 2;
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        RestMessages restMessages = new RestMessages();

        RestMessage restMessage1 = new RestMessage();
        restMessage1.setId("45");
        restMessage1.setSender("p1nodeName");
        restMessage1.setRecipient("p2nodeName");
        restMessage1.setContent("myContent");
        restMessage1.setSubject("message1Sub");
        restMessage1.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage1.setAttachmentCount("2");
        restMessage1.setTotalAttachmentSize("25");
        restMessage1.setStatus("AVAILABLE");
        restMessage1.setContentEncrypted("true");
        restMessage1.setExpired("false");
        restMessage1.setSigned("false");
        restMessage1.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));
        restMessages.addMessageListItem(restMessage1);

        RestMessage restMessage2 = new RestMessage();
        restMessage2.setId("35");
        restMessage2.setSender("p1nodeName");
        restMessage2.setRecipient("p2nodeName");
        restMessage2.setContent("myContent2");
        restMessage2.setSubject("message2Sub");
        restMessage2.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setAttachmentCount("0");
        restMessage2.setTotalAttachmentSize("0");
        restMessage2.setStatus("AVAILABLE");
        restMessage2.setContentEncrypted("true");
        restMessage2.setExpired("false");
        restMessage2.setSigned("false");
        restMessage2.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage2.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage2.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));
        restMessages.addMessageListItem(restMessage2);


        String subject = null;
        String startfrom = "1";
        String resultsize = "10";

        boolean isAscending = true;

        String sortby = "asc";

        MessageColumn sortColumn = MessageColumn.SENT;

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(icaService.getIcasByParty(argThat(is(party)))).thenReturn(partyIcas);
        when(messageDAO.getMessages(subject, Message.MessageState.SENT, party, sortColumn, isAscending, Integer.valueOf(startfrom).intValue(), Integer.valueOf(resultsize).intValue())).thenReturn(messages);

        //ACTUAL CALL
        RestMessages result = messageService.getMessages(Message.MessageState.SENT, party, startfrom, resultsize, null, subject, sortby);

        assertThat(result.getMessageCount(), is("2"));
        assertThat(result.getMessageList().get(0), is(restMessage1));
        assertThat(result.getMessageList().get(1), is(restMessage2));

        verify(messageDAO).getMessages(subject, Message.MessageState.SENT, party, sortColumn, isAscending, Integer.valueOf(startfrom).intValue(), Integer.valueOf(resultsize).intValue());
        verifyNoMoreInteractions(messageDAO);
    }
}
