package eu.europa.ec.etrustex.webaccess.web.view.business.edma.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
import eu.europa.ec.etrustex.webaccess.model.User;
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


public class MessageViewSentHandlerTest extends AbstractTest {

    @InjectMocks
    private MessageViewSentHandler handler = new MessageViewSentHandler();

    @Mock
    private EDMAUtils edmaUtils;

    @Mock
    protected UserSessionContext userSessionContext;

    @Mock
    protected MailboxManager mailboxManager;

    @Mock
    private AttachmentHandler attachmentHandler;

    @Mock
    private WebHandlerHelper webHandlerHelper;

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel() throws Exception {

        Long messageId = 5L;

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setMessageId(messageId);

        User user = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(user);

        Message message = new Message();
        message.setId(messageId);

        Metadata metadata = new Metadata();
        when(attachmentHandler.getMetadata(messageId)).thenReturn(metadata);

        List<Attachment> binaryAttachments = new ArrayList<>();
        when(mailboxManager.getBinaryAttachments(messageId, user)).thenReturn(binaryAttachments);

        EdmaMessage edmaMessage = new EdmaMessage();
        List<AttachmentMetadata> aml = new ArrayList<>();
        edmaMessage.setAttachmentMetadataList(aml);
        when(edmaUtils.fetchEdmaMessage(message, binaryAttachments, metadata)).thenReturn(edmaMessage);

        MessageViewSentHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        doReturn(new ArrayList<AttachmentMetadata>()).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        assertThat((EdmaMessage) result.get(EDMA_MESSAGE_ATTR), is(sameInstance(edmaMessage)));
        assertThat((List<AttachmentMetadata>) result.get("attachmentMetadataList"), is(sameInstance(aml)));

        verify(mailboxManager).getBinaryAttachments(messageId, user);
        verifyNoMoreInteractions(mailboxManager);

        verify(edmaUtils).fetchEdmaMessage(message, binaryAttachments, metadata);
        verifyNoMoreInteractions(edmaUtils);

        verify(attachmentHandler).getMetadata(messageId);
        verifyNoMoreInteractions(attachmentHandler);
    }

}
