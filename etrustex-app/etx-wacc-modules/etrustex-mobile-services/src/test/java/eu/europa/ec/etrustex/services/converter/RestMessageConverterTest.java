package eu.europa.ec.etrustex.services.converter;

import eu.europa.ec.etrustex.services.business.config.Configuration;
import eu.europa.ec.etrustex.services.model.RestMessage;
import eu.europa.ec.etrustex.services.model.RestMessages;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RestMessageConverterTest extends AbstractTest {

    private static final SimpleDateFormat simpleDateFormatterJs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final static SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Test
    public void test_convertToRestMessages_nullMessages_should_return_empty_list() {
        Party party = new Party();
        party.setId(1L);
        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(2);

        //ACTUAL CALL
        RestMessages result = RestMessageConverter.getInstance().convertToRestMessages(null, party, configuration, true);

        assertThat(result.getMessageList().size(), is(0));
    }

    @Test
    public void test_convertToRestParties_emptyParties_should_return_empty_list() {
        Party party = new Party();
        party.setId(1L);
        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(2);

        List<Message> messages = new ArrayList<>();

        //ACTUAL CALL
        RestMessages result = RestMessageConverter.getInstance().convertToRestMessages(messages, party, configuration, true);

        assertThat(result.getMessageList().size(), is(0));
    }

    @Test
    public void test_convertToRestMessages_should_return_incoming_restMessages() {
        Party party = new Party();
        party.setId(1L);
        party.setNodeName("p1nodeName");


        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(2);

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

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("message1Sub");
        message.setContent("myContent");
        message.setLocalParty(party);
        message.setRemoteParty(party2);
        MessageStatus status = new MessageStatus();
        status.setId(1L);
        status.setMstStatus(MessageStatus.Status.READ);
        message.setLastStatus(status);
        message.getLastStatus().setCreatedOn(new Date());
        message.setSentOn(new Date());
        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachment.setFileSize(10L);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(22L);
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
        secondMessage.setLastStatus(status);
        secondMessage.getLastStatus().setCreatedOn(new Date());
        secondMessage.setAttachments(new ArrayList<Attachment>());
        secondMessage.setSentOn(new Date());
        messages.add(secondMessage);

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(message.getCreatedOn());
        int retentionPolicyWeeks = 2;
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        RestMessage restMessage1 = new RestMessage();
        restMessage1.setId("45");
        restMessage1.setSender("p2nodeName");
        restMessage1.setRecipient("p1nodeName");
        restMessage1.setContent("myContent");
        restMessage1.setSubject("message1Sub");
        restMessage1.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage1.setAttachmentCount("2");
        restMessage1.setTotalAttachmentSize("25");
        restMessage1.setStatus("READ");
        restMessage1.setContentEncrypted("true");
        restMessage1.setExpired("false");
        restMessage1.setSigned("false");
        restMessage1.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));


        RestMessage restMessage2 = new RestMessage();
        restMessage2.setId("35");
        restMessage2.setSender("p2nodeName");
        restMessage2.setRecipient("p1nodeName");
        restMessage2.setContent("myContent2");
        restMessage2.setSubject("message2Sub");
        restMessage2.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setAttachmentCount("0");
        restMessage2.setTotalAttachmentSize("0");
        restMessage2.setStatus("READ");
        restMessage2.setContentEncrypted("true");
        restMessage2.setExpired("false");
        restMessage2.setSigned("false");
        restMessage2.setSentDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setStatusReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));

        //ACTUAL CALL
        RestMessages result = RestMessageConverter.getInstance().convertToRestMessages(messages, party, configuration, true);

        assertThat(result.getMessageList().size(), is(2));
        assertThat(result.getMessageList().get(0), is(restMessage1));
        assertThat(result.getMessageList().get(1), is(restMessage2));
    }

    @Test
    public void test_convertToRestMessages_should_return_sent_restMessages() {
        Party party = new Party();
        party.setId(1L);
        party.setNodeName("p1nodeName");


        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(2);

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

        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("message1Sub");
        message.setContent("myContent");
        message.setLocalParty(party);
        message.setRemoteParty(party2);
        MessageStatus status = new MessageStatus();
        status.setId(1L);
        status.setMstStatus(MessageStatus.Status.READ);
        message.setLastStatus(status);
        message.getLastStatus().setCreatedOn(new Date());
        message.setSentOn(new Date());
        List<Attachment> attachmentsList = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.setId(22L);
        attachment.setFileName("TestAttach");
        attachment.setType(AttachmentType.BINARY);
        attachment.setFileSize(10L);
        attachmentsList.add(attachment);

        attachment = new Attachment();
        attachment.setId(22L);
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
        secondMessage.setLastStatus(status);
        secondMessage.getLastStatus().setCreatedOn(new Date());
        secondMessage.setAttachments(new ArrayList<Attachment>());
        secondMessage.setSentOn(new Date());
        messages.add(secondMessage);

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(message.getCreatedOn());
        int retentionPolicyWeeks = 2;
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        RestMessage restMessage1 = new RestMessage();
        restMessage1.setId("45");
        restMessage1.setSender("p1nodeName");
        restMessage1.setRecipient("p2nodeName");
        restMessage1.setContent("myContent");
        restMessage1.setSubject("message1Sub");
        restMessage1.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage1.setAttachmentCount("2");
        restMessage1.setTotalAttachmentSize("25");
        restMessage1.setStatus("READ");
        restMessage1.setContentEncrypted("true");
        restMessage1.setExpired("false");
        restMessage1.setSigned("false");
        restMessage1.setSentDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setStatusReceiptDate(simpleDateFormatterJs.format(new Date()));
        restMessage1.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));


        RestMessage restMessage2 = new RestMessage();
        restMessage2.setId("35");
        restMessage2.setSender("p1nodeName");
        restMessage2.setRecipient("p2nodeName");
        restMessage2.setContent("myContent2");
        restMessage2.setSubject("message2Sub");
        restMessage2.setReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setAttachmentCount("0");
        restMessage2.setTotalAttachmentSize("0");
        restMessage2.setStatus("READ");
        restMessage2.setContentEncrypted("true");
        restMessage2.setExpired("false");
        restMessage2.setSigned("false");
        restMessage2.setSentDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setStatusReceiptDate(simpleDateFormatterJs.format(createdOn));
        restMessage2.setExpirationWarning("ATTACHMENTS WILL EXPIRE ON " + simpleDateFormatter.format(expiryDate.getTime()));

        //ACTUAL CALL
        RestMessages result = RestMessageConverter.getInstance().convertToRestMessages(messages, party, configuration, false);

        assertThat(result.getMessageList().size(), is(2));
        assertThat(result.getMessageList().get(0), is(restMessage1));
        assertThat(result.getMessageList().get(1), is(restMessage2));
    }
}
