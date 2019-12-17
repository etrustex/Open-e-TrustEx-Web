package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.services.business.MailboxService;
import eu.europa.ec.etrustex.services.model.RestInboxCounters;
import eu.europa.ec.etrustex.services.model.RestSentCounters;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.AttachmentDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageReadStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer implementation for mail box.
 */

@Service
@Transactional
public class MailboxServiceImpl implements MailboxService {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Autowired
    private MessageReadStatusDAO messageReadStatusDAO;

    @Override
    public RestInboxCounters getInboxCountersByParty(Party party, User user) {
        RestInboxCounters inboxCounters = new RestInboxCounters();

        long allMessages = messageDAO.countMessages("", Message.MessageState.INCOMING, party);
        long unreadMessages = messageDAO.countUnreadMessages(user, "", Message.MessageState.INCOMING, party);
        long readMessages = allMessages - unreadMessages;

        inboxCounters.setAll(String.valueOf(allMessages));
        inboxCounters.setRead(String.valueOf(readMessages));

        return inboxCounters;
    }

    @Override
    public RestSentCounters getSentCountersByParty(Party party) {

        RestSentCounters restSentCounters = new RestSentCounters();

        long countDeliveredMessages = 0L;
        long countFailedMessages = 0L;
        long countReadMessages = 0L;
        long countNoneMessages = 0L;

        long countAllMessages = messageDAO.countMessages("", Message.MessageState.SENT, party);
        if (countAllMessages > 0) {
            countDeliveredMessages = messageDAO.countMessages("", Message.MessageState.SENT, MessageStatus.Status.AVAILABLE, party);
            countFailedMessages = messageDAO.countMessages("", Message.MessageState.SENT, MessageStatus.Status.FAILED, party);
            countReadMessages = messageDAO.countMessages("", Message.MessageState.SENT, MessageStatus.Status.READ, party);
            countNoneMessages = messageDAO.countMessages("", Message.MessageState.SENT, MessageStatus.Status.UNKNOWN, party);
        }

        restSentCounters.setAll(String.valueOf(countAllMessages));
        restSentCounters.setDelivered(String.valueOf(countDeliveredMessages));
        restSentCounters.setFailed(String.valueOf(countFailedMessages));
        restSentCounters.setRead(String.valueOf(countReadMessages));
        restSentCounters.setNone(String.valueOf(countNoneMessages));

        return restSentCounters;
    }

    @Override
    public Message getMessageByMessageId(Long messageId) {
        return messageDAO.findById(messageId);
    }

    @Override
    public List<Attachment> getAttachments(Long messageId) {
        return attachmentDAO.getAttachmentsListByMessageId(messageId);
    }

    @Override
    public void markMessageReadByUser(Long messageId, Long userId) {
        messageReadStatusDAO.markMessageReadByUser(messageId, userId);
    }
}
