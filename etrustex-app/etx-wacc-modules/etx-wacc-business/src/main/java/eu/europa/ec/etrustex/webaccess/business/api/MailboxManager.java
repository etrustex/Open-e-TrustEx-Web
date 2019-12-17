package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;

import java.util.List;

/**
 * @author apladap
 */
public interface MailboxManager {

    ResultDataList<MessageListEntry> getMessages(MessageListQueryParams queryParams, User user, Message.MessageState messageState, Party recipient) throws EtxException;

    DossierResult getDossiers(MessageListQueryParams queryParams, User user, Message.MessageState messageState, Party recipient) throws EtxException;

    List<Attachment> getBinaryAttachments(Long messageId, User user) throws EtxException;

    Long countBinaryAttachments(Long messageId);

    List<Attachment> getAttachments(Long messageId, User user) throws EtxException;

    Message getMessageDetails(Long messageId, User user) throws EtxException;

    Message getMessageDetailsAndFetchSignatureEagerly(Long messageId, User user) throws EtxException;

    Message saveOrUpdateMessage(Message message, List<Long> uploadedFiles, Long idSelectedPayloadFile, User user) throws EtxException;

    Message saveOrUpdateMessage(Message message, User user) throws EtxException;

    long getUnreadCount(User user, Party party, Message.MessageState msgState) throws EtxException;

    Message getMessageByMessageId(long messageId) throws EtxException;

    Message getMessageWithSignatureByMessageId(long messageId) throws EtxException;

    void disableMessage(Message message) throws EtxException;

    boolean searchForDuplicateMessageBundle(String bundleId, Long localPartyId, String senderRefId);

    List<Message> getWarningMessages();

    MessageStatus createMessageStatus(String localPartyName, String remotePartyName, String statusUuid, String referenceUuid,
                                             String referenceType, MessageStatus.Status status, String statusCode, String userLogin) throws EtxException;

    void consumeNodeMessage(String localPartyName, String remotePartyName, String messageUuid, MessageType messageType) throws EtxException;
}
