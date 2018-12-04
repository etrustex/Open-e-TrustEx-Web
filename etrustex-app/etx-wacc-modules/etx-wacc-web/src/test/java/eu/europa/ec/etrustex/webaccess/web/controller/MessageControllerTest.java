package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Unit tests for Message Controller.
 */

public class MessageControllerTest extends AbstractControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MailboxManager mailboxManager;

    @Mock
    private AttachmentHandler attachmentHandler;

    @Mock
    protected UserSessionContext userSessionContext;

    private User user = new User();

    @Test
    public void test_exportMessage_invalid_message_id_should_redirect_to_403() {
        when(userSessionContext.getUser()).thenReturn(user);

        //ACTUAL CALL
        ModelAndView modelAndView = messageController.exportMessage(request, response, "1a");

        RedirectView redirectView = (RedirectView) modelAndView.getView();
        assertThat(redirectView.getUrl(), is("error/error403.do"));

        verifyNoMoreInteractions(mailboxManager);
        verifyNoMoreInteractions(attachmentHandler);
    }

    @Test
    public void test_exportMessage_null_message_should_redirect_to_404() {
        when(userSessionContext.getUser()).thenReturn(user);
        when(mailboxManager.getMessageByMessageId(1L)).thenReturn(null);

        //ACTUAL CALL
        ModelAndView modelAndView = messageController.exportMessage(request, response, "1");

        RedirectView redirectView = (RedirectView) modelAndView.getView();
        assertThat(redirectView.getUrl(), is("error/error404.do"));

        verify(mailboxManager).getMessageByMessageId(1L);
        verifyNoMoreInteractions(mailboxManager);
        verifyNoMoreInteractions(attachmentHandler);
    }

    @Test
    public void test_exportMessage_success() {
        Party sender = new Party();
        sender.setNodeName("party1");
        Party recipient = new Party();
        recipient.setNodeName("party2");

        Message message = new Message();
        message.setId(1L);
        message.setBundleId("bundleId");
        message.setSentOn(new Date());
        message.setLocalParty(sender);
        message.setRemoteParty(recipient);
        message.setContent("comments");

        Metadata metadata = new Metadata();
        metadata.setContent("metadata");

        List<Attachment> attachmentList = new ArrayList();
        Attachment attachment1 = new Attachment();
        attachment1.setFileName("file1");
        attachment1.setFileSize(100L);
        attachmentList.add(attachment1);
        Attachment attachment2 = new Attachment();
        attachment2.setFileName("file2");
        attachment2.setFileSize(100L);
        attachmentList.add(attachment2);

        when(userSessionContext.getUser()).thenReturn(user);
        when(mailboxManager.getMessageByMessageId(1L)).thenReturn(message);
        when(attachmentHandler.getMetadata(1L)).thenReturn(metadata);
        when(mailboxManager.getAttachments(1L, null)).thenReturn(attachmentList);

        //ACTUAL CALL
        ModelAndView modelAndView = messageController.exportMessage(request, response, "1");

        Map<String, Object> model = modelAndView.getModel();
        assertThat((Message) model.get("message"), equalTo(message));
        assertThat((Metadata) model.get("metadata"), equalTo(metadata));
        assertThat((List<Attachment>) model.get("attachments"), equalTo(attachmentList));

        assertThat(modelAndView.getViewName(), is("MessagePdfView"));

        verify(mailboxManager).getMessageByMessageId(1L);
        verify(mailboxManager).getAttachments(1L, null);
        verifyNoMoreInteractions(mailboxManager);

        verify(attachmentHandler).getMetadata(1L);
        verifyNoMoreInteractions(attachmentHandler);
    }

}
