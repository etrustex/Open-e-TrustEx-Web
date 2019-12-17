package eu.europa.ec.etrustex.services.rest;

import eu.europa.ec.etrustex.services.business.MailboxService;
import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.services.model.RestAttachment;
import eu.europa.ec.etrustex.services.model.RestAttachments;
import eu.europa.ec.etrustex.services.security.UserContext;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MessageResourceTest extends AbstractTest {

    @InjectMocks
    private MessageResource messageResource;

    @Mock
    private MailboxService mailboxService;

    @Mock
    private UserService userService;

    @Test
    public void test_getAttachmentsByMessageId_invalid_message_should_return_400_code() {
        //ACTUAL CALL
        ResponseEntity<RestAttachments> result = messageResource.getAttachmentsByMessageId("1d");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void test_getAttachmentsByMessageId_non_existing_message_should_return_404_code() {
        Long messageId = 1L;

        when(mailboxService.getMessageByMessageId(messageId)).thenReturn(null);

        //ACTUAL CALL
        ResponseEntity<RestAttachments> result = messageResource.getAttachmentsByMessageId(String.valueOf(messageId));

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

        verify(mailboxService).getMessageByMessageId(messageId);
        verifyNoMoreInteractions(mailboxService);
    }

    @Test
    public void test_getAttachmentsByMessageId_should_return_200_code() {
        Message message = new Message();
        message.setId(1L);

        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment1 = new Attachment();
        attachment1.setId(1L);
        attachments.add(attachment1);
        Attachment attachment2 = new Attachment();
        attachment2.setId(2L);
        attachments.add(attachment2);

        RestAttachments restAttachments = new RestAttachments();
        RestAttachment restAttachment1 = new RestAttachment();
        restAttachment1.setId(String.valueOf(attachment1.getId()));
        restAttachments.addAttachmentListItem(restAttachment1);
        RestAttachment restAttachment2 = new RestAttachment();
        restAttachment2.setId(String.valueOf((attachment2.getId())));
        restAttachments.addAttachmentListItem(restAttachment2);

        when(mailboxService.getMessageByMessageId(message.getId())).thenReturn(message);
        when(mailboxService.getAttachments(message.getId())).thenReturn(attachments);

        //ACTUAL CALL
        ResponseEntity<RestAttachments> result = messageResource.getAttachmentsByMessageId(String.valueOf(message.getId()));

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.OK));
        assertThat(result.getBody(), is(restAttachments));

        verify(mailboxService).getMessageByMessageId(message.getId());
        verify(mailboxService).getAttachments(message.getId());
        verifyNoMoreInteractions(mailboxService);
    }

    @Test
    public void test_reportMessageRead_invalid_message_should_return_400_code() {
        UserDetails currentUser = new UserContext("userId");

        //ACTUAL CALL
        ResponseEntity result = messageResource.reportMessageRead(currentUser, "1d");

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void test_reportMessageRead_non_existing_message_should_return_404_code() {
        UserDetails currentUser = new UserContext("userId");
        Long messageId = 1L;

        when(mailboxService.getMessageByMessageId(messageId)).thenReturn(null);

        //ACTUAL CALL
        ResponseEntity result = messageResource.reportMessageRead(currentUser, String.valueOf(messageId));

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.NOT_FOUND));

        verify(mailboxService).getMessageByMessageId(messageId);
        verifyNoMoreInteractions(mailboxService);
    }

    @Test
    public void test_reportMessageRead_should_return_200_code() {
        String userLogin = "userLogin";
        UserDetails currentUser = new UserContext(userLogin);

        Long messageId = 1L;
        Message message = new Message();
        message.setId(messageId);

        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(mailboxService.getMessageByMessageId(messageId)).thenReturn(message);
        when(userService.getUserDetails(userLogin)).thenReturn(user);

        //ACTUAL CALL
        ResponseEntity result = messageResource.reportMessageRead(currentUser, String.valueOf(messageId));

        assertThat(result, notNullValue());
        assertThat(result.getStatusCode(), is(HttpStatus.OK));

        verify(mailboxService).getMessageByMessageId(messageId);

        verify(userService).getUserDetails(userLogin);
        verifyNoMoreInteractions(userService);

        verify(mailboxService).markMessageReadByUser(messageId, userId);
        verifyNoMoreInteractions(mailboxService);
    }

}
