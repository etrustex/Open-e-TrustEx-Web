package eu.europa.ec.etrustex.webaccess.business.facade;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.SignatureVO;
import eu.europa.ec.etrustex.webaccess.webservice.document.DocumentService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

public class BusinessFacadeImplTest extends AbstractTest {
    @Mock
    private MailboxManager mailboxManager;
    @Mock
    private AttachmentHandler attachmentHandler;
    @Mock
    private DocumentService documentService;

    @InjectMocks
    private BusinessFacadeImpl businessFacade = new BusinessFacadeImpl();

    @Test
    public void test_update_SaveOrUpdateMessage() throws Exception {
        List<Attachment> attachments = new ArrayList<>();
        Attachment binaryAttachment1 = new Attachment();
        binaryAttachment1.setId(109L);
        binaryAttachment1.setType(AttachmentType.BINARY);
        attachments.add(binaryAttachment1);

        Attachment binaryAttachment2 = new Attachment();
        binaryAttachment2.setId(110L);
        binaryAttachment2.setType(AttachmentType.BINARY);
        attachments.add(binaryAttachment2);

        Attachment payloadAttachment = new Attachment();
        payloadAttachment.setId(111L);
        payloadAttachment.setType(AttachmentType.METADATA);
        attachments.add(payloadAttachment);

        Party party = new Party();
        party.setNodeName("EDMA");

        List<MessageSignature> messageSignatures = new ArrayList<>();
        MessageSignature messageSignature = new MessageSignature();
        messageSignatures.add(messageSignature);

        Party remoteParty = new Party();
        remoteParty.setNodeName("GUI IOCO");

        Message message = new Message();
        message.setAttachments(attachments);
        message.setId(100L);
        message.setMsgState(Message.MessageState.DRAFT);
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setSubject("My test message");
        message.setContent("This is a message content");
        message.setAttachments(attachments);

        Message savedMessage = new Message();
        savedMessage.setId(100L);
        savedMessage.setMsgState(Message.MessageState.DRAFT);
        savedMessage.setAttachments(attachments);
        List<Long> attachmentIds = Arrays.asList(1L);
        SignatureVO signature = new SignatureVO(null, null, null);
        User user = new User();

        when(attachmentHandler.getAttachments(attachmentIds)).thenReturn(attachments);

        Message originalMessage = new Message();
        originalMessage.setId(100L);
        originalMessage.setMsgState(Message.MessageState.DRAFT);
        originalMessage.setSignatures(messageSignatures);
        List<Attachment> attachments_copy = new ArrayList<Attachment>();
        Attachment binaryAttachment1_copy = new Attachment();
        binaryAttachment1_copy.setId(109L);
        binaryAttachment1_copy.setType(AttachmentType.BINARY);
        attachments_copy.add(binaryAttachment1_copy);

        Attachment binaryAttachment2_copy = new Attachment();
        binaryAttachment2_copy.setId(110L);
        binaryAttachment2_copy.setType(AttachmentType.BINARY);
        attachments_copy.add(binaryAttachment2_copy);

        Attachment payloadAttachment_copy = new Attachment();
        payloadAttachment_copy.setId(111L);
        payloadAttachment_copy.setType(AttachmentType.METADATA);
        attachments_copy.add(payloadAttachment_copy);
        originalMessage.setAttachments(attachments_copy);

        when(mailboxManager.getMessageWithSignatureByMessageId(message.getId())).thenReturn(originalMessage);

        doAnswer(new Answer<Message>() {
            public Message answer(InvocationOnMock invocation) throws Throwable {
                Message message = (Message) invocation.getArguments()[0];
                message.setId(100L);
                return message;
            }
        }).when(mailboxManager).saveOrUpdateMessage(argThat(sameInstance(message)), (argThat(sameInstance(attachmentIds))), eq(payloadAttachment.getId()), argThat(sameInstance(user)));


        businessFacade.saveOrUpdateMessage(message, attachmentIds, payloadAttachment.getId(), "PAYLOAD CONTENT", signature, user);

        verify(mailboxManager).getMessageByMessageId(eq(message.getId()));
        verify(mailboxManager).saveOrUpdateMessage(argThat(sameInstance(message)), (argThat(sameInstance(attachmentIds))), eq(payloadAttachment.getId()), argThat(sameInstance(user)));
        verify(attachmentHandler).savePayloadMetadata("PAYLOAD CONTENT", savedMessage.getId(), Metadata.MetadataState.CREATED);
        verifyNoMoreInteractions(mailboxManager, attachmentHandler, documentService);
    }

    @Test
    public void test_SaveOrUpdateMessage() throws Exception {
        List<Attachment> attachments = new ArrayList<>();
        Attachment binaryAttachment1 = new Attachment();
        binaryAttachment1.setId(109L);
        binaryAttachment1.setType(AttachmentType.BINARY);
        attachments.add(binaryAttachment1);

        Attachment binaryAttachment2 = new Attachment();
        binaryAttachment2.setId(110L);
        binaryAttachment2.setType(AttachmentType.BINARY);
        attachments.add(binaryAttachment2);

        Attachment payloadAttachment = new Attachment();
        payloadAttachment.setId(111L);
        payloadAttachment.setType(AttachmentType.METADATA);
        attachments.add(payloadAttachment);

        Party party = new Party();
        party.setNodeName("EDMA");

        Party remoteParty = new Party();
        remoteParty.setNodeName("GUI IOCO");

        Message message = new Message();
        message.setId(null);
        message.setMsgState(Message.MessageState.DRAFT);
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setSubject("My test message");
        message.setContent("This is a message content");
        message.setAttachments(attachments);

        Message savedMessage = new Message();
        savedMessage.setId(123L);
        savedMessage.setMsgState(Message.MessageState.DRAFT);
        savedMessage.setAttachments(attachments);

        List<Long> attachmentIds = Arrays.asList(1L);
        SignatureVO signature = new SignatureVO(null, null, null);
        User user = new User();

        when(attachmentHandler.getAttachments(attachmentIds)).thenReturn(attachments);


        doAnswer(new Answer<Message>() {
            public Message answer(InvocationOnMock invocation) throws Throwable {
                Message message = (Message) invocation.getArguments()[0];
                message.setId(123L);
                return message;
            }
        }).when(mailboxManager).saveOrUpdateMessage(argThat(sameInstance(message)), (argThat(sameInstance(attachmentIds))), eq(payloadAttachment.getId()), argThat(sameInstance(user)));


        Message persistedMessage = businessFacade.saveOrUpdateMessage(message, attachmentIds, payloadAttachment.getId(), "PAYLOAD CONTENT", signature, user);

        assertThat(persistedMessage, sameInstance(message));
        verify(mailboxManager).saveOrUpdateMessage(argThat(sameInstance(message)), (argThat(sameInstance(attachmentIds))), eq(payloadAttachment.getId()), argThat(sameInstance(user)));
        verify(attachmentHandler).savePayloadMetadata("PAYLOAD CONTENT", savedMessage.getId(), Metadata.MetadataState.CREATED);
        verifyNoMoreInteractions(mailboxManager, attachmentHandler, documentService);
    }

    @Test
    public void test_Signature_onDraft_SaveOrUpdateMessage() throws Exception {
        final List<Attachment> attachments = new ArrayList<>();
        Attachment binaryAttachment1 = new Attachment();
        binaryAttachment1.setId(109L);
        binaryAttachment1.setType(AttachmentType.BINARY);
        attachments.add(binaryAttachment1);

        Attachment binaryAttachment2 = new Attachment();
        binaryAttachment2.setId(110L);
        binaryAttachment2.setType(AttachmentType.BINARY);
        attachments.add(binaryAttachment2);

        final Attachment payloadAttachment = new Attachment();
        payloadAttachment.setId(111L);
        payloadAttachment.setType(AttachmentType.METADATA);
        attachments.add(payloadAttachment);

        Party party = new Party();

        Party remoteParty = new Party();

        final Message message = new Message();
        message.setId(null);
        message.setMsgState(Message.MessageState.DRAFT);
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setSubject("My test message");
        message.setContent("This is a message content");
        message.setAttachments(attachments);

        Message savedMessage = new Message();
        savedMessage.setId(123L);
        savedMessage.setMsgState(Message.MessageState.DRAFT);
        savedMessage.setAttachments(attachments);

        final List<Long> attachmentIds = Arrays.asList(1L);
        String signature="<?xml version=\"1.0\" encoding=\"UTF-8\"?><ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"xmldsig-3d408ac8-9c42-4c28-9c2c-205e9d64ee68\">\n" +
                "<ds:SignedInfo>\n" +
                "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
                "<ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\n" +
                "<ds:Reference Id=\"xmldsig-3d408ac8-9c42-4c28-9c2c-205e9d64ee68-ref0\">\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
                "<ds:DigestValue>BOVkeTBgMfPcopL6K40+eC7y3qwJf4XCRG8KeieboJI=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "<ds:Reference Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#xmldsig-3d408ac8-9c42-4c28-9c2c-205e9d64ee68-signedprops\">\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" +
                "<ds:DigestValue>znfJgkciu2LrNTLESLtbHXyohxdDjSiGMYLTuRoXCHU=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "</ds:SignedInfo>\n" +
                "<ds:SignatureValue Id=\"xmldsig-3d408ac8-9c42-4c28-9c2c-205e9d64ee68-sigvalue\">\n" +
                "n/d9Jx2LpDmv09FAsvOJqaLpmkXSFRI+tIuDGgxkfJ/GLyD4tPgIHozOKcdjr3EJJXoXPsiJQGMl&#13;\n" +
                "pWdtiJsdi3WNgTkhIpsuJr6ebdaQAREAdNKHvmv2EgICM5ikEg0Xypa0r6ngeNGOc8wtf2TwOQ1r&#13;\n" +
                "JihUNkBZ/12gDLtR64A=\n" +
                "</ds:SignatureValue>\n" +
                "<ds:KeyInfo>\n" +
                "<ds:X509Data>\n" +
                "<ds:X509Certificate>\n" +
                "MIICgzCCAeygAwIBAgIEU1oU2zANBgkqhkiG9w0BAQUFADCBhTEfMB0GCSqGSIb3DQEJARYQQVJN&#13;\n" +
                "RU5AQ0hPTEFLLkNPTTELMAkGA1UEBhMCQkUxETAPBgNVBAgMCEJSVVNTRUxTMRIwEAYDVQQHDAlF&#13;\n" +
                "VFRFUkJFRUsxCzAJBgNVBAoMAkVDMQ4wDAYDVQQLDAVESUdJVDERMA8GA1UEAwwIQVJNRU5LRVkw&#13;\n" +
                "HhcNMTQwNDI1MDc1NjE1WhcNMTkwNDI1MDc1NjE1WjCBhTEfMB0GCSqGSIb3DQEJARYQQVJNRU5A&#13;\n" +
                "Q0hPTEFLLkNPTTELMAkGA1UEBhMCQkUxETAPBgNVBAgMCEJSVVNTRUxTMRIwEAYDVQQHDAlFVFRF&#13;\n" +
                "UkJFRUsxCzAJBgNVBAoMAkVDMQ4wDAYDVQQLDAVESUdJVDERMA8GA1UEAwwIQVJNRU5LRVkwgZ8w&#13;\n" +
                "DQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBALFKjrH7+QEOn1TGEpONGK6bjOb3YtOYMt4pofvflgUE&#13;\n" +
                "dcZ0QMmDqRdfMFyUFVqtaMXsFP/iRfec9fMGCKWiK/OH2a8mtlxNZKpiyVkMua2BEySAu8L3ABKV&#13;\n" +
                "WUyARkwkG9UH1gs3qtBYGRvqkUo/DfhvOtzJmixMrIe++PtPoSOJAgMBAAEwDQYJKoZIhvcNAQEF&#13;\n" +
                "BQADgYEAWeYtMk6tLf3PAYShlT9G6NIGOkt4J3pWWVJZ+d/Nuqyv20yS4Oxq/8Dij3dHNIov9vGp&#13;\n" +
                "+dqHi881FXEsd7onYQNcEjXT2brm8z+EUFlJYZZSX0RhzVGG14uTXMIm5LcFb85RG6mMHGV//jeO&#13;\n" +
                "F3/KptfmZSwUNFbLrKFeeSDOmco=\n" +
                "</ds:X509Certificate>\n" +
                "</ds:X509Data>\n" +
                "</ds:KeyInfo>\n" +
                "<ds:Object><xades:QualifyingProperties xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xades141=\"http://uri.etsi.org/01903/v1.4.1#\" Target=\"#xmldsig-3d408ac8-9c42-4c28-9c2c-205e9d64ee68\"><xades:SignedProperties Id=\"xmldsig-3d408ac8-9c42-4c28-9c2c-205e9d64ee68-signedprops\"><xades:SignedSignatureProperties><xades:SigningTime>2017-11-15T17:51:28.345+01:00</xades:SigningTime><xades:SigningCertificate><xades:Cert><xades:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/><ds:DigestValue>Mi34/VcktsUWMJ0RhABDxHFInA+jIvTyIeUPhwk6890=</ds:DigestValue></xades:CertDigest><xades:IssuerSerial><ds:X509IssuerName>CN=ARMENKEY,OU=DIGIT,O=EC,L=ETTERBEEK,ST=BRUSSELS,C=BE,1.2.840.113549.1.9.1=#161041524d454e4043484f4c414b2e434f4d</ds:X509IssuerName><ds:X509SerialNumber>1398412507</ds:X509SerialNumber></xades:IssuerSerial></xades:Cert></xades:SigningCertificate></xades:SignedSignatureProperties></xades:SignedProperties></xades:QualifyingProperties></ds:Object>\n" +
                "</ds:Signature>";
        String signedData="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><signedBundle xmlns=\"urn:eu:europa:ec:etrustex:signature:v1.0\"><document><id>20171115175125282-6043301137207433091</id><digestMethod>SHA-512</digestMethod><digestValue>A7D3006285FC1499D9B778E60E3FDF60AB63D0E9B6CC0ABB026B5DB7223A7DCE222C9753199B0ED16C87F19326A230335011598675772EE57CC18DA8AF2D1645</digestValue></document><document><id>20171115175125282-6174484050123819179</id><digestMethod>SHA-512</digestMethod><digestValue>39AB7993BD704761EE4F38A98DBEE7C66071874A1F18E2A6BFEE6BA6165BDCA8B043E7CEA7BA8507DED7435DFF8D667037D094869A4402FCF2F6C9847090844A</digestValue></document></signedBundle>";
        SignatureVO signatureVO = new SignatureVO(signature, signedData, null);
        User user = new User();

        doAnswer(new Answer<Message>() {
            public Message answer(InvocationOnMock invocation) throws Throwable {
                Message message = (Message) invocation.getArguments()[0];
                message.setId(100L);
                return message;
            }
        }).when(mailboxManager).saveOrUpdateMessage(argThat(sameInstance(message)), (argThat(sameInstance(attachmentIds))), eq(payloadAttachment.getId()), argThat(sameInstance(user)));


        when(attachmentHandler.getAttachments(attachmentIds)).thenReturn(attachments);

        Message persistedMessage = businessFacade.saveOrUpdateMessage(message, attachmentIds, payloadAttachment.getId(), "PAYLOAD CONTENT", signatureVO, user);

        assertNotEquals(persistedMessage.getSignatures(), null);
        assertEquals(signatureVO.getSignature(), persistedMessage.getSignatures().get(0).getSignature());
        assertEquals(signatureVO.getSignedData(), persistedMessage.getSignatures().get(0).getSignedData());

    }

    @Test(expected = RuntimeException.class)
    public void test_saveOrUpdateMessage_should_propagateExceptions() throws Exception {
        Message message = new Message();
        User user = new User();
        List<Long> attachmentIds = Arrays.asList(2L);

        doThrow(RuntimeException.class).when(mailboxManager).saveOrUpdateMessage(message, user);

        businessFacade.saveOrUpdateMessage(message, attachmentIds, 1L, null, null, user);
    }

    @Test
    public void testDisableMessage() {
        Message message = new Message();
        long messageId = 33L;
        message.setId(messageId);
        message.setActiveState(true);

        // DO THE ACTUAL CALL
        businessFacade.disableMessage(message);

        verify(mailboxManager).disableMessage(message);
    }
}
