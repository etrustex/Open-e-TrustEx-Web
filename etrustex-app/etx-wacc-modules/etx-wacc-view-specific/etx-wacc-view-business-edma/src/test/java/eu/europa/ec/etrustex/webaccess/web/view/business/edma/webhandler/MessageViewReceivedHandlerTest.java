package eu.europa.ec.etrustex.webaccess.web.view.business.edma.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.util.IcaHelper;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.AttachmentMetadata;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.WebHandlerHelper;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EDMAUtils;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.EDMA_MESSAGE_ATTR;
import static org.mockito.Mockito.*;

public class MessageViewReceivedHandlerTest extends AbstractTest {

    @InjectMocks
    private MessageViewReceivedHandler handler = new MessageViewReceivedHandler();

    @Mock
    private EDMAUtils edmaUtils;

    @Mock
    private WebHandlerHelper webHandlerHelper;

    @Mock
    protected UserSessionContext userSessionContext;

    @Mock
    protected MailboxManager mailboxManager;

    @Mock
    protected IcaManager icaManager;

    @Mock
    private IcaHelper icaHelper;

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel() throws Exception {
        Long messageId = 5L;
        String payloadXml = "payload";
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        EdmaMessage edmaMessage = new EdmaMessage();

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);
        queryParams.setParty(party);

        User user = mock(User.class);

        when(userSessionContext.getUser()).thenReturn(user);
        when(edmaUtils.convertXMLToEdma(payloadXml)).thenReturn(edmaMessage);
        when(webHandlerHelper.fetchPayloadIfReady(argThat(any(Map.class)), argThat(equalTo(messageId)))).thenReturn(payloadXml);

        ArrayList<AttachmentMetadata> aml = new ArrayList<>();
        edmaMessage.setAttachmentMetadataList(aml);

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.INCOMING);

        MessageViewReceivedHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        ArrayList<Attachment> attachments = new ArrayList<>();
        doReturn(attachments).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        assertThat((EdmaMessage) result.get(EDMA_MESSAGE_ATTR), is(sameInstance(edmaMessage)));
        verify(edmaUtils).convertXMLToEdma(payloadXml);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(webHandlerHelper).addJsonParameters(argThat(any(Map.class)), anyString(),
                argThat(is(attachments)), argThat(is(party)), argThat(is(aml)), (List<MessageSignature>)argThat(is(nullValue())));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void test_buildBusinessModel_should_addEmptyEdmaMessage_when_payloadIsNull() throws Exception {
        Long messageId = 5L;
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);
        queryParams.setParty(party);

        User user = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(user);
        when(webHandlerHelper.fetchPayloadIfReady(argThat(any(Map.class)), argThat(equalTo(messageId)))).thenReturn(null);

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.INCOMING);

        MessageViewReceivedHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        ArrayList<Attachment> attachments = new ArrayList<>();
        doReturn(attachments).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        assertThat(result.get(EDMA_MESSAGE_ATTR), is(notNullValue()));
        verifyZeroInteractions(edmaUtils);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(webHandlerHelper).addJsonParameters(argThat(any(Map.class)), anyString(),
                argThat(is(attachments)), argThat(is(party)), (List<AttachmentMetadata>)argThat(is(nullValue())), (List<MessageSignature>)argThat(is(nullValue())));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel_sentMessage() throws Exception {
        Long messageId = 5L;
        String payloadXml = "payload";
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        EdmaMessage edmaMessage = new EdmaMessage();

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);
        queryParams.setParty(party);

        User user = mock(User.class);

        when(userSessionContext.getUser()).thenReturn(user);
        when(edmaUtils.convertXMLToEdma(payloadXml)).thenReturn(edmaMessage);
        when(webHandlerHelper.fetchPayloadIfReady(argThat(any(Map.class)), argThat(equalTo(messageId)))).thenReturn(payloadXml);

        ArrayList<AttachmentMetadata> aml = new ArrayList<>();
        edmaMessage.setAttachmentMetadataList(aml);

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.SENT);

        MessageViewReceivedHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        ArrayList<Attachment> attachments = new ArrayList<>();
        doReturn(attachments).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        assertThat((EdmaMessage) result.get(EDMA_MESSAGE_ATTR), is(sameInstance(edmaMessage)));
        verify(edmaUtils).convertXMLToEdma(payloadXml);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(webHandlerHelper).addJsonParameters(argThat(any(Map.class)), anyString(),
                argThat(is(attachments)), argThat(is(party)), argThat(is(aml)), (List<MessageSignature>) argThat(is(nullValue())));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void test_buildBusinessModel_should_addEmptyEdmaMessage_and_add_error_message_when_exceptionThrownDuringParsingPayload() throws Exception {
        String payloadXml = "payload";
        Long messageId = 5L;
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);
        queryParams.setParty(party);

        User user = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(user);

        when(webHandlerHelper.fetchPayloadIfReady(argThat(any(Map.class)), argThat(equalTo(messageId)))).thenReturn(payloadXml);
        when(edmaUtils.convertXMLToEdma(payloadXml)).thenThrow(new RuntimeException());

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.INCOMING);

        MessageViewReceivedHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        ArrayList<Attachment> attachments = new ArrayList<>();
        doReturn(attachments).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        assertThat(result.get(EDMA_MESSAGE_ATTR), is(notNullValue()));
        verify(edmaUtils).convertXMLToEdma(payloadXml);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        assertThat((String) result.get("errorOccurred"), is(equalTo("message.metadataFailure")));
        verify(webHandlerHelper).addJsonParameters(argThat(any(Map.class)), anyString(),
                argThat(is(attachments)), argThat(is(party)), (List<AttachmentMetadata>)argThat(is(nullValue())), (List<MessageSignature>)argThat(is(nullValue())));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }
}
