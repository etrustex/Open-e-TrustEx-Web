package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.util.IcaHelper;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.AttachmentMetadata;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.WebHandlerHelper;
import eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.EGREFFEUtils;
import eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.EgreffeTransmissionVO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class MessageViewHandlerTest extends AbstractTest {

    @InjectMocks
    private MessageViewHandler handler = new MessageViewHandler();

    @Mock
    private EGREFFEUtils eGreffeUtils;

    @Mock
    private WebHandlerHelper webHandlerHelper;

    @Mock
    protected UserSessionContext userSessionContext;

    @Mock
    protected MailboxManager mailboxManager;

    @Mock
    protected IcaManager icaManager;

    @InjectMocks
    WebHandlerHelper helper;

    @Mock
    private AttachmentHandler attachmentHandler;

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

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);
        queryParams.setParty(party);

        User user = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(user);

        EgreffeTransmissionVO egreffeTransmissionVO = new EgreffeTransmissionVO();
        when(eGreffeUtils.getListOfWorks(payloadXml, messageId)).thenReturn(egreffeTransmissionVO);
        when(webHandlerHelper.fetchPayloadIfReady(argThat(any(Map.class)), argThat(equalTo(messageId)))).thenReturn(payloadXml);

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.DRAFT);

        MessageViewHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        doReturn(new ArrayList<AttachmentMetadata>()).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(webHandlerHelper).addJsonParameters(argThat(any(Map.class)), anyString(),
                argThat(any(List.class)), argThat(is(party)), (List<AttachmentMetadata>)argThat(is(nullValue())), (List<MessageSignature>)argThat(is(nullValue())));

        assertThat((EgreffeTransmissionVO) result.get("eGreffeMetadata"), is(sameInstance(egreffeTransmissionVO)));

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

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);
        queryParams.setParty(party);

        User user = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(user);

        EgreffeTransmissionVO egreffeTransmissionVO = new EgreffeTransmissionVO();
        when(eGreffeUtils.getListOfWorks(payloadXml, messageId)).thenReturn(egreffeTransmissionVO);
        when(webHandlerHelper.fetchPayloadIfReady(argThat(any(Map.class)), argThat(equalTo(messageId)))).thenReturn(payloadXml);

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.SENT);

        MessageViewHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        doReturn(new ArrayList<AttachmentMetadata>()).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(webHandlerHelper).addJsonParameters(argThat(any(Map.class)), anyString(),
                argThat(any(List.class)), argThat(is(party)), (List<AttachmentMetadata>) argThat(is(nullValue())), (List<MessageSignature>) argThat(is(nullValue())));

        assertThat((EgreffeTransmissionVO) result.get("eGreffeMetadata"), is(sameInstance(egreffeTransmissionVO)));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void test_buildBusinessModel_should_notAddEgreffeMetadata_when_payloadIsNull() throws Exception {
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
        message.setMsgState(Message.MessageState.DRAFT);

        MessageViewHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        doReturn(new ArrayList<AttachmentMetadata>()).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(webHandlerHelper).addJsonParameters(argThat(any(Map.class)), anyString(),
                argThat(any(List.class)), argThat(is(party)), (List<AttachmentMetadata>) argThat(is(nullValue())), (List<MessageSignature>)argThat(is(nullValue())));

        assertThat(result.get("eGreffeMetadata"), is(nullValue()));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void test_buildBusinessModel_should_addErrorMessage_when_exceptionThrownDuringParsingPayload() throws Exception {
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

        HashMap<String, Object> model = new HashMap<>();
        Metadata metadata = new Metadata();
        metadata.setMetadataState(Metadata.MetadataState.DOWNLOADED);
        String content = "content";
        metadata.setContent(content);

        when(attachmentHandler.getMetadata(messageId)).thenReturn(metadata);

        when(webHandlerHelper.fetchPayloadIfReady(argThat(any(Map.class)), argThat(equalTo(messageId)))).thenReturn(payloadXml);

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.DRAFT);

        MessageViewHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        doReturn(new ArrayList<AttachmentMetadata>()).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(eGreffeUtils.getListOfWorks(payloadXml, messageId)).thenThrow(new RuntimeException());

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        assertThat((String) result.get("errorOccurred"), is(equalTo("message.metadataFailure")));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }
}
