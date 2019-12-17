package eu.europa.ec.etrustex.services.business;

import eu.europa.ec.etrustex.services.model.RestInboxCounters;
import eu.europa.ec.etrustex.services.model.RestSentCounters;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;

import java.util.List;

/**
 * Service layer for mail box.
 */

public interface MailboxService {

    RestInboxCounters getInboxCountersByParty(Party party, User user);

    RestSentCounters getSentCountersByParty(Party party);

    Message getMessageByMessageId(Long messageId);

    List<Attachment> getAttachments(Long messageId);

    void markMessageReadByUser(Long messageId, Long userId);

}
