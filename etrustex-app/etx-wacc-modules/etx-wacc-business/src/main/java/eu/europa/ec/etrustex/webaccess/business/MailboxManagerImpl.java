package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.SendStatusTriggerService;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.business.message.NodeStatusResponseCode;
import eu.europa.ec.etrustex.webaccess.business.queue.message.RetrieveMessageTriggerServiceImpl;
import eu.europa.ec.etrustex.webaccess.business.util.IcaHelper;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.Message.MessageState;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.persistence.*;
import eu.europa.ec.etrustex.webaccess.persistence.jpa.MessageColumn;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.utils.MessageUUIDGenerator;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Transactional
public class MailboxManagerImpl implements MailboxManager {

    private static final String UNREFERENCED = "UNREFERENCED";

    private static final Logger logger = Logger.getLogger(MailboxManagerImpl.class);
    private static final List<String> BUNDLE_TYPES = Collections.unmodifiableList(Arrays.asList("MESSAGE_BUNDLE", MessageType.MESSAGE_BUNDLE.getType()));
    private static final List<String> STATUS_TYPES = Collections.unmodifiableList(Arrays.asList("MESSAGE_STATUS", MessageType.MESSAGE_STATUS.getType()));
    private final ConcurrentMap<String, Boolean> processingMessages = new ConcurrentHashMap<>();
    @Autowired
    protected UserSessionContext userSessionContext;
    @Autowired
    private MessageDAO messageDAO;
    @Autowired
    private AttachmentDAO attachmentDAO;
    @Autowired
    private MessageReadStatusDAO messageReadStatusDAO;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private MessageStatusDAO messageStatusDAO;
    @Autowired
    private PartyDAO partyDAO;
    @Autowired
    private SendStatusTriggerService sendStatusTriggerService;
    @Autowired
    private RetrieveMessageTriggerServiceImpl nodeMessageTriggerService;
    @Autowired
    private MailboxManager mailboxManager;
    @Autowired
    private AttachmentHandler attachmentHandler;
    @Autowired
    private IcaHelper icaHelper;

    @Override
    public ResultDataList<MessageListEntry> getMessages(MessageListQueryParams queryParams, User user, MessageState messageState, Party userParty) throws EtxException {

        try {

            final MessageColumn sortColumn;
            switch (messageState) {
                case SENT:
                    sortColumn = MessageColumn.SENT;
                    break;
                case DRAFT:
                    sortColumn = MessageColumn.SAVED;
                    break;
                default:
                    sortColumn = MessageColumn.RECEIVED;
            }

            String subjectFilter = queryParams.getFilters().get(MessageListQueryParams.MessageColumn.SUBJECT);
            ResultDataList<Message> searchResult;
            //If the user wants to filter for the unread messages should invoke a new method other than the getMessages
            List<Long> readMessages = new ArrayList<>();
            if (queryParams.isUnreadOnly()) {
                long totalNrOfUnreadMessages = messageDAO.countUnreadMessages(user, subjectFilter, messageState, userParty);
                List<Message> unreadMessages = messageDAO.getUnreadMessages(user, subjectFilter, messageState, userParty, sortColumn,
                        queryParams.getSortDirection().isAscending(), queryParams.getStart(), queryParams.getOffset());

                searchResult = new ResultDataList<>(unreadMessages, totalNrOfUnreadMessages);
            } else {
                long totalNrOfMessages;
                List<Message> messages;
                if (queryParams.getMessageStatus() == MessageListQueryParams.MessageStatus.ALL) {
                    totalNrOfMessages = messageDAO.countMessages(subjectFilter, messageState, userParty);
                    messages = messageDAO.getMessages(subjectFilter, messageState, userParty, sortColumn,
                            queryParams.getSortDirection().isAscending(), queryParams.getStart(), queryParams.getOffset());
                } else {
                    MessageStatus.Status messageStatus = queryParams.getMessageStatus().getMsgStatus();
                    totalNrOfMessages = messageDAO.countMessages(subjectFilter, messageState, messageStatus, userParty);
                    messages = messageDAO.getMessages(subjectFilter, messageState, messageStatus, userParty, sortColumn,
                            queryParams.getSortDirection().isAscending(), queryParams.getStart(), queryParams.getOffset());
                }

                searchResult = new ResultDataList<>(messages, totalNrOfMessages);

                //fetch the read messages only if we need to know this information
                readMessages = messageDAO.getSubMessagesReadByUser(searchResult.getData(), user.getId());
            }

            List<Long> binaryAttachmentMessages = messageDAO.getMessageIdsWithBinaryAttachments(searchResult.getData());

            List<MessageListEntry> results = new ArrayList<>();
            Configuration configuration = configurationService.getConfiguration();
            boolean isRetentionPolicyValid = configurationService.isRetentionPolicyValid(configuration);
            for (Message message : searchResult.getData()) {
                Date createdOn = message.getCreatedOn();
                Date updatedOn = message.getUpdatedOn();

                MessageListEntry messageListEntry = new MessageListEntry();
                messageListEntry.setMessageId(message.getId());
                messageListEntry.setCreateDate((messageState == MessageState.DRAFT && updatedOn != null) ? updatedOn : createdOn);
                messageListEntry.setMessageState(message.getMsgState().name());
                if (message.getMsgState() == MessageState.SENT) {
                    messageListEntry.setSentDate(message.getSentOn());
                }
                messageListEntry.setSubject(message.getSubject());
                messageListEntry.setSenderName(message.getRemoteParty().getDisplayName());

                messageListEntry.setHasAttachments(binaryAttachmentMessages.contains(message.getId()));
                messageListEntry.setReadByCurrentUser(readMessages.contains(message.getId()));

                if (isRetentionPolicyValid) {
                    RetentionMetadata retentionMetadata = configurationService.computeRetentionMetadata(configuration, createdOn);
                    messageListEntry.setRetentionMetadata(retentionMetadata);
                } else {
                    messageListEntry.setRetentionMetadata(RetentionMetadata.INVALID_RETENTION_POLICY_SETTINGS_INSTANCE);
                }

                if (message.getMsgState() == MessageState.SENT && message.getLastStatus() != null) {
                    if(this.isNotDelivered(message) && !userParty.getBusiness().getName().equals("EDMA")) {
                        messageListEntry.setStatusCode(MessageListQueryParams.MessageStatus.UNDELIVERED.getMsgCode());
                    } else {
                        MessageListQueryParams.MessageStatus status = MessageListQueryParams.MessageStatus.parse(message.getLastStatus().getMstStatus());
                        if (status != null) {
                            messageListEntry.setStatusCode(status.getMsgCode());
                        }
                    }
                }

                messageListEntry.setHasIca(icaHelper.hasIca(message));

                results.add(messageListEntry);
            }

            return new ResultDataList<>(results, searchResult.getTotalRowCount());
        } catch (Exception e) {
            String msg = "error while fetching messages for folder "
                    + (messageState != null ? messageState : "null");
            logger.error(msg, e);
            throw new EtxException(msg, e);
        }
    }

    public boolean isNotDelivered(Message message) {
        return messageDAO.countMessagesByBundleId(message.getBundleId()) == 1;
    }

    @Override
    @Transactional(readOnly = false)
    public Message getMessageDetails(Long messageId, User user) throws EtxException {
        Message message = messageDAO.findById(messageId);
        processMessage(messageId, user, message);

        return message;
    }

    @Override
    @Transactional(readOnly = false)
    public Message getMessageDetailsAndFetchSignatureEagerly(Long messageId, User user) throws EtxException {
        Message message = messageDAO.findById(messageId);

        if(!CollectionUtils.isEmpty(message.getSignatures())) {
            Hibernate.initialize(message.getSignatures());
        }

        processMessage(messageId, user, message);

        return message;
    }

    private void processMessage(Long messageId, User user, Message message) {
        logger.info("Get message with id " + messageId + " bundle id " + message.getBundleId());
        List<Long> readMessages = messageDAO.getSubMessagesReadByUser(Collections.singletonList(message), user.getId());
        if (!readMessages.contains(message.getId())) {
            messageReadStatusDAO.markMessageReadByUser(messageId, user.getId());
        }

        String processingKey = processingKey(message);
        if (processingMessages.putIfAbsent(processingKey, true) == null) {
            logger.info("Processing message '" + message.getBundleId() + "'!");
            try {
                if (message.getMsgState().equals(MessageState.INCOMING) && !hasReadMessageStatus(message)) {
                    logger.info("Message bundle id: " + message.getBundleId() + " has no read statuses send.");
                    MessageStatus messageStatus = createReadMessageStatus(message);
                    messageStatus = messageStatusDAO.saveOrUpdateMessageStatus(messageStatus);
                    if (messageStatus.getId() != null) {
                        message.setLastStatus(messageStatus);
                        messageDAO.update(message);
                        logger.info("New message status created with id: " + messageStatus.getId() + " and bundle id: " + message.getBundleId());
                        sendStatusTriggerService.triggerSendStatus(messageStatus.getId());
                        logger.info("Read status notification triggered for message status: " + messageStatus.getId());
                    }
                } else {
                    logger.info("Message bundle id: " + message.getBundleId() + " has read statuses in progress, no message status to send now.");
                }
            } catch (Exception e) {
                logger.error("Unexpected error: " + e.getMessage(), e);
            } finally {
                processingMessages.remove(processingKey);
            }
        } else {
            logger.warn("Message '" + message.getBundleId() + "' is being processed!");
        }
    }

    private boolean hasReadMessageStatus(Message message) {
        List<MessageStatus> messageStatuses = message.getStatuses();
        if (messageStatuses != null && !messageStatuses.isEmpty()) {
            for (MessageStatus messageStatus : messageStatuses) {
                if (messageStatus.getMstStatus() == MessageStatus.Status.READ &&
                        (messageStatus.getMstState() == MessageStatus.State.CREATED || messageStatus.getMstState() == MessageStatus.State.SENT))
                    return true;
            }
        }
        return false;
    }

    /**
     * Compose the message key based on bundle id, local and remote party.
     */
    private String processingKey(Message message) {
        return message.getBundleId() + message.getLocalParty().getId() + message.getRemoteParty().getNodeName();
    }

    private MessageStatus createReadMessageStatus(Message message) {
        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setMessage(message);
        messageStatus.setStatusUuid(MessageUUIDGenerator.randomMessageStatusUUID(message.getLocalParty().getNodeName()));
        messageStatus.setMstState(MessageStatus.State.CREATED);
        messageStatus.setMstStatus(MessageStatus.Status.READ);
        messageStatus.setMstStatusCode(NodeStatusResponseCode.BDL7.getCode());
        messageStatus.setLogin(userSessionContext.getUser().getLogin());
        messageStatus.setCreatedOn(new Date());
        return messageStatus;
    }

    @Override
    public List<Attachment> getBinaryAttachments(Long messageId, User user) throws EtxException {
        return attachmentDAO.getAttachmentsListByMessageIdAndType(messageId, AttachmentType.BINARY);
    }

    @Override
    public Long countBinaryAttachments(Long messageId) {
        return attachmentDAO.countAttachmentsByMessageAndType(messageId, AttachmentType.BINARY);
    }

    @Override
    public List<Attachment> getAttachments(Long messageId, User user) throws EtxException {
        return attachmentDAO.getAttachmentsListByMessageId(messageId);
    }


    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdateMessage(Message message, List<Long> uploadedFiles, Long idSelectedPayloadFile, User user) {

        if (uploadedFiles != null) {
            List<Long> attachmentIds = new ArrayList<>(uploadedFiles.size());
            attachmentIds.addAll(uploadedFiles);
            final List<Attachment> attachments = attachmentHandler.getAttachments(attachmentIds);
            for (Attachment attachment : attachments) {
                if(attachment.getType() == null) {
                    if (attachment.getId().equals(idSelectedPayloadFile)) {
                        attachment.setType(AttachmentType.METADATA);
                    } else {
                        attachment.setType(AttachmentType.BINARY);
                    }
                }
            }
            message.setAttachments(attachments);
        }

        Message savedMessage;

        if (message.getId() != null) {
            Message origMessage = mailboxManager.getMessageWithSignatureByMessageId(message.getId());
            if (origMessage == null) {
                throw new IllegalStateException("Couldn't find a message in the DB for the given id: " + message.getId());
            }
            final List<Attachment> origAttachments = origMessage.getAttachments();
            origAttachments.clear();
            if (message.getAttachments() != null) {
                origAttachments.addAll(message.getAttachments());
            }
            origMessage.setContent(message.getContent());
            origMessage.setSubject(message.getSubject());
            origMessage.setIssueDate(message.getIssueDate());
            origMessage.setMsgState(message.getMsgState());
            origMessage.setSignatures(message.getSignatures());
            savedMessage = origMessage;
        } else {
            savedMessage = message;
            try {
                final String bundleId = MessageUUIDGenerator.randomMessageBundleUUID(message.getLocalParty().getNodeName());
                savedMessage.setBundleId(bundleId);
            } catch (Exception e) {
                throw new EtxException("message bundle id generation failed", e);
            }
        }

        if (savedMessage.getMsgState() == MessageState.SENT) {
            savedMessage.setSentOn(new Date());
        }

        saveOrUpdateMessage(savedMessage, user);
        logger.info("Message saved with bundle id " + savedMessage.getBundleId());
        return savedMessage;
    }

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdateMessage(Message message, User user) {
        logger.info("Save message with bundle id " + message.getBundleId());

        // message already exists
        if (message.getId() != null) {
            messageDAO.update(message);
        } else {
            messageDAO.save(message);
            if (user != null) {
                messageReadStatusDAO.markMessageReadByUser(message.getId(), user.getId());
            }
        }

        return message;
    }


    @Override
    @Transactional(readOnly = false)
    public void disableMessage(Message message) throws EtxException {
        logger.info("Set message active state to FALSE with bundle id " + message.getBundleId());

        if (message.getActiveState()) {
            //set the new status on this message and update it
            message.setActiveState(false);
            messageDAO.update(message);
        } else {
            logger.warn("Message active state is already FALSE with bundle id " + message.getBundleId());
        }
    }

    @Override
    public DossierResult getDossiers(MessageListQueryParams queryParams, User user, MessageState messageState, Party recipient) throws EtxException {
        try {

            String subjectFilter = queryParams.getFilters().get(MessageListQueryParams.MessageColumn.SUBJECT);

            //If the user wants to filter for the unread messages should invoke a new method other than the getMessages
            ResultDataList<Message> searchResult;
            List<Long> readMessages = new ArrayList<>();
            if (queryParams.isUnreadOnly()) {
                long unreadMessagesByDossier = messageDAO.countUnreadMessagesByDossier(user, subjectFilter, messageState, recipient);
                List<Message> unreadMessages = messageDAO.getUnreadMessagesByDossier(user, subjectFilter, messageState, recipient, MessageColumn.RECEIVED,
                        queryParams.getSortDirection().isAscending(), queryParams.getStart(), queryParams.getOffset());

                searchResult = new ResultDataList<>(unreadMessages, unreadMessagesByDossier);
            } else {
                long messagesByDossier = messageDAO.countMessagesByDossier(subjectFilter, messageState, recipient);
                List<Message> messages = messageDAO.getMessagesByDossier(subjectFilter, messageState, recipient, MessageColumn.RECEIVED,
                        queryParams.getSortDirection().isAscending(), queryParams.getStart(), queryParams.getOffset());

                searchResult = new ResultDataList<>(messages, messagesByDossier);

                //fetch the read messages only if we need to know this information
                readMessages = messageDAO.getSubMessagesReadByUser(messages, user.getId());
            }

            List<Long> binaryAttachmentMessages = messageDAO.getMessageIdsWithBinaryAttachments(searchResult.getData());

            List<MessageListEntry> results = new ArrayList<>();
            Configuration configuration = configurationService.getConfiguration();
            boolean isRetentionPolicyValid = configurationService.isRetentionPolicyValid(configuration);
            for (Message message : searchResult.getData()) {
                Date createdOn = message.getCreatedOn();

                MessageListEntry messageListEntry = new MessageListEntry();
                messageListEntry.setMessageId(message.getId());
                messageListEntry.setCreateDate(createdOn);
                messageListEntry.setSubject(message.getSubject());
                messageListEntry.setSenderName(message.getRemoteParty().getDisplayName());
                messageListEntry.setReferenceId(message.getReferenceId());

                messageListEntry.setHasAttachments(binaryAttachmentMessages.contains(message.getId()));
                messageListEntry.setReadByCurrentUser(readMessages.contains(message.getId()));

                if (isRetentionPolicyValid) {
                    RetentionMetadata retentionMetadata = configurationService.computeRetentionMetadata(configuration, createdOn);
                    messageListEntry.setRetentionMetadata(retentionMetadata);
                } else {
                    messageListEntry.setRetentionMetadata(RetentionMetadata.INVALID_RETENTION_POLICY_SETTINGS_INSTANCE);
                }

                messageListEntry.setHasIca(icaHelper.hasIca(message));

                results.add(messageListEntry);
            }
            LinkedHashMap<String, List<MessageListEntry>> sortedDossierMap = new LinkedHashMap<>();

            for (MessageListEntry messageListEntry : results) {
                if (messageListEntry.getReferenceId() == null) {
                    if (!sortedDossierMap.containsKey(UNREFERENCED)) {
                        sortedDossierMap.put(UNREFERENCED, new ArrayList<MessageListEntry>());
                    }
                    sortedDossierMap.get(UNREFERENCED).add(messageListEntry);
                } else {
                    if (!sortedDossierMap.containsKey(messageListEntry.getReferenceId())) {
                        sortedDossierMap.put(messageListEntry.getReferenceId(), new ArrayList<MessageListEntry>());
                    }
                    sortedDossierMap.get(messageListEntry.getReferenceId()).add(messageListEntry);
                }
            }

            DossierResult dossierResult = new DossierResult();
            dossierResult.setTotalResultSize(searchResult.getTotalRowCount());
            dossierResult.setDossierMap(sortedDossierMap);

            return dossierResult;
        } catch (Exception e) {
            String msg = "error while fetching messages for message State: "
                    + (messageState != null ? messageState : "null");
            logger.error(msg, e);
            throw new EtxException(msg, e);
        }
    }

    @Override
    public long getUnreadCount(User user, Party party, Message.MessageState msgState) {
        // Fetch the total number of messages without any filters set
        return messageDAO.getUnreadCount(user, party, msgState);
    }

    @Override
    public Message getMessageByMessageId(long messageId) throws EtxException {
        return messageDAO.findById(messageId);
    }

    @Override
    public Message getMessageWithSignatureByMessageId(long messageId) throws EtxException {
        return messageDAO.findByIdAndFetchSignature(messageId);
    }

    @Override
    public boolean searchForDuplicateMessageBundle(String bundleId, Long localPartyId, String senderRefId) {
        Message messageData = messageDAO.findByMessageBundleId(bundleId,
                localPartyId,
                senderRefId);

        return messageData != null;
    }

    @Override
    public List<Message> getWarningMessages() {
        Configuration configuration = configurationService.getConfiguration();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7 * configuration.getRetentionPolicyWeeks());
        Date startDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, configuration.getRetentionPolicyWarningBeforeDays());
        Date endDate = calendar.getTime();

        return messageDAO.getWarningMessages(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = false)
    public MessageStatus createMessageStatus(String localPartyName, String remotePartyName, String statusUuid, String referenceUuid,
                                             String referenceType, MessageStatus.Status status, String statusCode, String userLogin) throws EtxException {
        MessageStatus messageStatus = null;
        if (status == MessageStatus.Status.UNKNOWN) {
            logger.warn("createMessageStatus: unknown status code: " + status + " for referenceUuid: " + referenceUuid);
        }
        Party localParty = partyDAO.getWebManagedPartyByNodeName(localPartyName);
        if (localParty == null) {
            logger.warn("createMessageStatus: unknown local party with name: " + localPartyName);
        } else {
            MessageStatus refMessageStatus = null;
            Message message = null;
            if (BUNDLE_TYPES.contains(referenceType)) {
                message = messageDAO.findByMessageBundleId(referenceUuid, localParty.getId(), remotePartyName);
                if (message == null) {
                    logger.warn("createMessageStatus: no message found for referenceUuid: " + referenceUuid +
                            ", localPartyName: " + localPartyName + ", remotePartyName: " + remotePartyName);
                }
            } else if (STATUS_TYPES.contains(referenceType)) {
                refMessageStatus = messageStatusDAO.findByStatusUuid(referenceUuid);
                if (refMessageStatus == null) {
                    logger.warn("createMessageStatus: no reference message status found for referenceUuid: " + referenceUuid);
                }
            }
            if (message != null || refMessageStatus != null) {
                messageStatus = new MessageStatus();
                messageStatus.setMessage(message);
                messageStatus.setStatusUuid(statusUuid);
                messageStatus.setMstState(MessageStatus.State.INCOMING);
                messageStatus.setMstStatus(status);
                messageStatus.setMstStatusCode(statusCode);
                messageStatus.setLogin(userLogin);
                messageStatus.setCreatedOn(new Date());
                messageStatus.setParent(refMessageStatus);
                messageStatus = messageStatusDAO.saveOrUpdateMessageStatus(messageStatus);
                if (message != null) {
                    message.setLastStatus(messageStatus);
                    messageDAO.update(message);
                }
            }
        }
        return messageStatus;
    }

    @Override
    public void consumeNodeMessage(String localPartyName, String remotePartyName, String messageUuid, MessageType messageType) throws EtxException {
        Party localParty = partyDAO.getWebManagedPartyByNodeName(localPartyName);
        if (localParty.getConsumeNodeMessages() == Boolean.TRUE) {
            nodeMessageTriggerService.triggerRetrieveMessage(localPartyName, remotePartyName, messageUuid, messageType);
        }
    }
}
