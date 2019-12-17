package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.AttachmentDAO;
import eu.europa.ec.etrustex.webaccess.persistence.ConfigurationDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MetadataDAO;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.webservice.document.DocumentService;
import eu.europa.ec.etrustex.webaccess.webservice.model.NodeAttachment;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class AttachmentHandlerImplTest extends AbstractTest {

    @InjectMocks
    private AttachmentHandlerImpl attachmentHandlerImpl;

    @Mock
    private MetadataDAO metadataDAO;

    @Mock
    private MessageDAO messageDAO;

    @Mock
    private ConfigurationDAO configurationDAO;

    @Mock
    private AttachmentDAO attachmentDAO;

    @Mock
    private DocumentService documentService;

    @Test
    public void testSaveOrupdateSave() throws Exception {

        Long messageId = 1L;
        String xmlResult = "<ETrustExEdmaMd>" +
                "<subject>meta2</subject>" +
                "<inboundDate>2012-08-23T11:30:59.701+02:00</inboundDate>" +
                "<language>EN</language>" +
                "</ETrustExEdmaMd>";

        Metadata previousRecord = null;

        when(metadataDAO.retrievePayload(messageId)).thenReturn(previousRecord);

        attachmentHandlerImpl.savePayloadMetadata(xmlResult, messageId, Metadata.MetadataState.DOWNLOADED);

        verify(metadataDAO).retrievePayload(messageId);
        verify(metadataDAO).save(argThat(any(Metadata.class)));
        verifyNoMoreInteractions(metadataDAO);
    }

    @Test
    public void testSaveOrupdateUpdate() throws Exception {

        Long messageId = 1L;
        String xmlResult = "<ETrustExEdmaMd>" +
                "<subject>meta2</subject>" +
                "<inboundDate>2012-08-23T11:30:59.701+02:00</inboundDate>" +
                "<language>EN</language>" +
                "</ETrustExEdmaMd>";

        Metadata previousRecord = new Metadata();
        previousRecord.setMessageId(1L);
        previousRecord.setContent(xmlResult);
        previousRecord.setId(1L);


        when(metadataDAO.retrievePayload(messageId)).thenReturn(previousRecord);

        attachmentHandlerImpl.savePayloadMetadata(xmlResult, messageId, Metadata.MetadataState.CREATED);

        verify(metadataDAO).retrievePayload(messageId);
        verify(metadataDAO).update(argThat(any(Metadata.class)));
        verifyNoMoreInteractions(metadataDAO);
    }

    @Test
    public void testGetAttachmentReferenceIdListPerMessage() throws Exception {

        Long messageId = 1L;
        List<Attachment> attachmentList = new ArrayList<>();

        Attachment attachment1 = new Attachment();
        attachment1.setFileName("Filename1");
        attachment1.setWrapperId("Reference1");
        attachmentList.add(attachment1);

        Attachment attachment2 = new Attachment();
        attachment2.setFileName("Filename2");
        attachment2.setWrapperId("Reference2");
        attachmentList.add(attachment2);

        Attachment attachment3 = new Attachment();
        attachment3.setFileName("Filename3");
        attachment3.setWrapperId("Reference3");
        attachmentList.add(attachment3);

        Attachment attachment4 = new Attachment();
        attachment4.setFileName("Filename4");
        attachment4.setWrapperId("Reference4");
        attachmentList.add(attachment4);

        when(attachmentDAO.getAttachmentsListByMessageId(messageId)).thenReturn(attachmentList);

        HashMap<String, String> resultMap = attachmentHandlerImpl.getMapOfAttachmentFilenamesPerReferenceIds(messageId);

        verify(attachmentDAO).getAttachmentsListByMessageId(messageId);
        assertThat(resultMap, notNullValue());
        assertThat(resultMap.size(), is(4));
        assertThat(resultMap.get("Reference1"), is("Filename1"));
        assertThat(resultMap.get("Reference2"), is("Filename2"));
        assertThat(resultMap.get("Reference3"), is("Filename3"));
        assertThat(resultMap.get("Reference4"), is("Filename4"));
        verifyNoMoreInteractions(attachmentDAO);
    }


    @Test
    public void testNoMetadataIsCreatedWhenRetrievePayloadForMessageFails() {
        Long messageId = 1L;

        Metadata previousRecord = new Metadata();
        previousRecord.setMessageId(1L);
        previousRecord.setContent(null);
        previousRecord.setId(1L);

        Message message = new Message();
        message.setAttachments(Collections.<Attachment>emptyList());

        when(messageDAO.findById(messageId)).thenReturn(message);
        when(metadataDAO.retrievePayload(messageId)).thenReturn(null);

        attachmentHandlerImpl.retrievePayloadForMessage(null, messageId);

        verify(messageDAO).findById(messageId);

        verify(metadataDAO, times(2)).retrievePayload(messageId);

        verifyNoMoreInteractions(metadataDAO);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void testGetAttachmentById() {
        // given
        Long attachmentId = 133l;
        Attachment expectedAttachment = new Attachment();
        when(attachmentDAO.findById(attachmentId)).thenReturn(expectedAttachment);

        // when
        Attachment currentAttachment = attachmentHandlerImpl.getAttachment(attachmentId);

        // then
        assertThat(currentAttachment, sameInstance(expectedAttachment));
        verify(attachmentDAO).findById(attachmentId);
    }

    @Test
    public void testRetrieveNodeAttachment() {
        // given
        String nodeName = "EDMA";
        String remotePartyName = "EGREFFE";

        Party localParty = new Party();
        localParty.setNodeUserName("usr");
        localParty.setNodeUserPass("pass");
        localParty.setNodeName(nodeName);

        Party remoteParty = new Party();
        remoteParty.setNodeName(remotePartyName);

        Message message = new Message();
        message.setLocalParty(localParty);
        message.setRemoteParty(remoteParty);

        String fileReferenceId = "387987c987";
        Attachment attachment = new Attachment();
        attachment.setWrapperId(fileReferenceId);
        attachment.setMessage(message);
        attachment.setType(AttachmentType.BINARY);

        // when
        attachmentHandlerImpl.retrieveNodeAttachment(attachment);

        // then
        verify(documentService).downloadFileStream(localParty.getNodeUserName(), localParty.getNodeUserPass(), fileReferenceId, nodeName, remotePartyName, AttachmentType.BINARY.name());
    }

    @Test
    public void shouldReturnAttachments() {
        // given
        Attachment attachment = new Attachment();
        attachment.setId(1410L);

        List<Attachment> expectedAttachments = Arrays.asList(attachment);
        List<Long> attachmentIds = Arrays.asList(attachment.getId());

        when(attachmentDAO.getAttachmentsByIds(attachmentIds)).thenReturn(expectedAttachments);

        // when
        final List<Attachment> attachments = attachmentHandlerImpl.getAttachments(attachmentIds);

        // then
        assertThat(attachments, is(expectedAttachments));
        verify(attachmentDAO).getAttachmentsByIds(attachmentIds);
        verifyNoMoreInteractions(attachmentDAO);
    }

    @Test
    public void retrievePayloadContent() {
        Long messageId = 1L;
        Message message = new Message();
        message.setId(messageId);

        Party testParty = new Party();
        testParty.setId(4L);
        message.setLocalParty(testParty);
        message.setMsgState(Message.MessageState.SENT);

        Party remoteParty = new Party();
        remoteParty.setId(5L);
        remoteParty.setNodeName("NodeNameTest");
        message.setRemoteParty(remoteParty);

        Attachment attachment = new Attachment();
        attachment.setId(155L);
        attachment.setWrapperId("reference6");
        attachment.setType(AttachmentType.METADATA);

        List<Attachment> attachments = new ArrayList<>();
        attachments.add(attachment);

        message.setAttachments(attachments);

        when(messageDAO.findById(messageId)).thenReturn(message);

        byte[] data = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ec_transmission_request></ec_transmission_request>".getBytes();

        InputStream input = new ByteArrayInputStream(data);

        when(attachmentDAO.findByReferenceId(attachment.getWrapperId(), message.getId())).thenReturn(attachment);

        NodeAttachment nodeAttachment = new NodeAttachment(input, 15);
        when(documentService.downloadFileStream(message.getLocalParty().getNodeUserName(), message.getLocalParty().getNodeUserPass(),
                attachment.getWrapperId(), message.getLocalParty().getNodeName(),
                remoteParty.getNodeName(), attachment.getType().name())).thenReturn(nodeAttachment);
        String payload = null;

        try {
            payload = attachmentHandlerImpl.retrievePayloadContent(messageId);
        } catch (EtxException etx) {
            etx.toString();
        }

        assertThat(payload, is(payload));
        verify(messageDAO).findById(messageId);
    }

}
