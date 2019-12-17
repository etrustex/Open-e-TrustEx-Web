package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.services.model.RestInboxCounters;
import eu.europa.ec.etrustex.services.model.RestSentCounters;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.AttachmentDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageReadStatusDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MailboxServiceImplTest extends AbstractTest {

    @InjectMocks
    private MailboxServiceImpl mailboxService = new MailboxServiceImpl();

    @Mock
    private MessageDAO messageDAO;

    @Mock
    private AttachmentDAO attachmentDAO;

    @Mock
    private MessageReadStatusDAO messageReadStatusDAO;

    @Test
    public void test_getInboxCountersByParty() {
        User user = new User();
        Party party = new Party();
        RestInboxCounters restInboxCounters = new RestInboxCounters();
        restInboxCounters.setAll("20");
        restInboxCounters.setRead("15");

        when(messageDAO.countMessages("", Message.MessageState.INCOMING, party)).thenReturn(20L);
        when(messageDAO.countUnreadMessages(user, "", Message.MessageState.INCOMING, party)).thenReturn(5L);

        //ACTUAL CALL
        RestInboxCounters result = mailboxService.getInboxCountersByParty(party, user);

        assertThat(result, notNullValue());
        assertThat(result, is(restInboxCounters));

        verify(messageDAO).countMessages("", Message.MessageState.INCOMING, party);
        verify(messageDAO).countUnreadMessages(user, "", Message.MessageState.INCOMING, party);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_getSentCountersByParty() {
        Party party = new Party();
        party.setId(1L);

        long countAll = 20L;
        long countDeliveredMessages = 5L;
        long countFailedMessages = 2L;
        long countReadMessages = 10L;
        long countNoneMessages = 1L;

        when(messageDAO.countMessages("", Message.MessageState.SENT, party)).thenReturn(countAll);
        when(messageDAO.countMessages("", Message.MessageState.SENT, MessageStatus.Status.AVAILABLE, party)).thenReturn(countDeliveredMessages);
        when(messageDAO.countMessages("", Message.MessageState.SENT, MessageStatus.Status.FAILED, party)).thenReturn(countFailedMessages);
        when(messageDAO.countMessages("", Message.MessageState.SENT, MessageStatus.Status.READ, party)).thenReturn(countReadMessages);
        when(messageDAO.countMessages("", Message.MessageState.SENT, MessageStatus.Status.UNKNOWN, party)).thenReturn(countNoneMessages);

        //ACTUAL CALL
        RestSentCounters restSentCounters = mailboxService.getSentCountersByParty(party);

        assertThat(restSentCounters.getAll(), is(String.valueOf(countAll)));
        assertThat(restSentCounters.getDelivered(), is(String.valueOf(countDeliveredMessages)));
        assertThat(restSentCounters.getFailed(), is(String.valueOf(countFailedMessages)));
        assertThat(restSentCounters.getRead(), is(String.valueOf(countReadMessages)));
        assertThat(restSentCounters.getNone(), is(String.valueOf(countNoneMessages)));


        verify(messageDAO).countMessages("", Message.MessageState.SENT, party);
        verify(messageDAO).countMessages("", Message.MessageState.SENT, MessageStatus.Status.AVAILABLE, party);
        verify(messageDAO).countMessages("", Message.MessageState.SENT, MessageStatus.Status.FAILED, party);
        verify(messageDAO).countMessages("", Message.MessageState.SENT, MessageStatus.Status.READ, party);
        verify(messageDAO).countMessages("", Message.MessageState.SENT, MessageStatus.Status.UNKNOWN, party);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_getSentCountersByUserParty_should_return_empty_restSentCounters_when_no_messages() {
        Party party = new Party();
        party.setId(1L);

        when(messageDAO.countMessages("", Message.MessageState.SENT, party)).thenReturn(0L);

        //ACTUAL CALL
        RestSentCounters restSentCounters = mailboxService.getSentCountersByParty(party);

        assertThat(restSentCounters.getAll(), is("0"));
        assertThat(restSentCounters.getDelivered(), is("0"));
        assertThat(restSentCounters.getFailed(), is("0"));
        assertThat(restSentCounters.getNone(), is("0"));
        assertThat(restSentCounters.getRead(), is("0"));

        verify(messageDAO).countMessages("", Message.MessageState.SENT, party);
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_getMessageByMessageId_should_call_messageDAO() {
        Message message = new Message();
        message.setId(1L);

        when(messageDAO.findById(message.getId())).thenReturn(message);

        //ACTUAL CALL
        Message result = mailboxService.getMessageByMessageId(message.getId());

        assertThat(result, notNullValue());
        assertThat(result, is(message));

        verify(messageDAO).findById(message.getId());
        verifyNoMoreInteractions(messageDAO);
    }

    @Test
    public void test_getAttachments_should_call_attachmentDAO() {
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment = new Attachment();
        attachment.setId(1L);
        attachments.add(attachment);

        when(attachmentDAO.getAttachmentsListByMessageId(1L)).thenReturn(attachments);

        //ACTUAL CALL
        List<Attachment> result = mailboxService.getAttachments(1L);

        assertThat(result, notNullValue());
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(attachment));

        verify(attachmentDAO).getAttachmentsListByMessageId(1L);
        verifyNoMoreInteractions(attachmentDAO);
    }

    @Test
    public void test_markMessageReadByUser_should_call_messageReadStatusDAO() {
        //ACTUAL CALL
        mailboxService.markMessageReadByUser(1L, 1L);

        verify(messageReadStatusDAO).markMessageReadByUser(1L, 1L);
        verifyNoMoreInteractions(messageReadStatusDAO);
    }

}
