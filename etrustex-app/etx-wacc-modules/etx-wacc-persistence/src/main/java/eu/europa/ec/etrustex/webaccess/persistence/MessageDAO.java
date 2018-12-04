package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaKey;
import eu.europa.ec.etrustex.webaccess.persistence.jpa.MessageColumn;

import java.util.Date;
import java.util.List;

/**
 * Data access object for message data
 *
 * @author dzierma
 */
public interface MessageDAO extends AbstractDAO<Message, Long> {

    /**
     * Counts the messages based on the given filter
     *
     * @return
     */
    public long countMessages(String subjectFilter, Message.MessageState messageState, Party localParty);

    /**
     * Counts the messages based on the given filter and message status.
     */
    long countMessages(String subjectFilter, Message.MessageState messageState, MessageStatus.Status messageStatus, Party localParty);

    /**
     * Queries storage for message list. A query might be narrowed by filters and page size.
     * The result is ordered by the sorters
     */
    public List<Message> getMessages(String subjectFilter, Message.MessageState messageState, Party localParty,
                                               MessageColumn sort, boolean isAscending, int startIndex, int maxResults);

    /**
     * Queries storage for message list. A query might be narrowed by filters, message status and page size.
     * The result is ordered by the sorters
     */
    List<Message> getMessages(String subjectFilter, Message.MessageState messageState, MessageStatus.Status messageStatus,
                Party localParty, MessageColumn sort, boolean isAscending, int startIndex, int maxResults);

    /**
     * Counts the total number of messages per dossier
     *
     * @return
     */
    public long countMessagesByDossier(String subjectFilter, Message.MessageState messageState, Party localParty);

    /**

    /**
     * Queries storage for message list. A query might be narrowed by filters and page size.
     * The result is grouped by referenceId and ordered by the sorters
     */
    public List<Message> getMessagesByDossier(String subjectFilter, Message.MessageState messageState, Party localParty,
                                                        MessageColumn sort, boolean isAscending, int startIndex, int maxResults);

    /**
     * Search for particular message
     *
     * @param refId - requested message identifier
     * @return - requested message data
     */
    public Message findByReferenceId(String refId);

    /**
     * Search for duplicate message
     *
     * @param bundleId     - requested message bundleId
     * @param localPartyId - requested message localPartyId
     * @param senderRefId  - requested message senderRefId
     * @return - requested message data
     */
    public Message findByMessageBundleId(String bundleId, Long localPartyId, String senderRefId);

    /**
     * Returns the number of messages in the given state unread by the given user for the given party
     *
     * @return
     */
    long getUnreadCount(User user, Party party, Message.MessageState msgState);

    /**
     * Counts the unread messages based on the given filter
     *
     * @return
     */
    public long countUnreadMessages(User user, String subjectFilter, Message.MessageState messageState, Party localParty);

    /**
     * Returns the unread messages
     */
    public List<Message> getUnreadMessages(User user, String subjectFilter, Message.MessageState messageState, Party localParty,
                                                     MessageColumn sort, boolean isAscending, int startIndex, int maxResults);

    /**
     * Counts the unread messages by dossier
     *
     * @return
     */
    public long countUnreadMessagesByDossier(User user, String subjectFilter, Message.MessageState messageState, Party localParty);

    /**
     * Returns the unread messages group by Dossier
     */
    public List<Message> getUnreadMessagesByDossier(User user, String subjectFilter, Message.MessageState messageState, Party localParty,
                                                              MessageColumn sort, boolean isAscending, int startIndex, int maxResults);

    /**
     * Searches and returns the set of messages that are read by the given user
     */
    List<Long> getSubMessagesReadByUser(List<Message> messages, Long userId);

    /**
     * Search for warning messages which aren't notified.
     *
     * @param startDate Start data limit for message creation date.
     * @param endDate   End date for message creation.
     * @return List with warning messages.
     */
    List<Message> getWarningMessages(Date startDate, Date endDate);
    
    List<Long> getMessageIdsWithBinaryAttachments(List<Message> messages);

    /**
     * Get message and fetch signature eagerly
     * @param messageId message id
     * @return
     */
    Message findByIdAndFetchSignature(Long messageId);
}
