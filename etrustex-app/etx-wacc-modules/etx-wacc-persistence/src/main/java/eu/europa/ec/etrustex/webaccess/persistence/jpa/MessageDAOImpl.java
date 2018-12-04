package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.Message.MessageState;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.util.CollectionUtil;
import eu.europa.ec.etrustex.webaccess.persistence.util.MessageUtil;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class MessageDAOImpl extends AbstractDAOImpl<Message, Long> implements MessageDAO {

    private final static String QL_FETCH_READ_MESSAGES_FOR_USERID = "SELECT msgr.messageId FROM MessageRead msgr " +
            "WHERE msgr.messageId in (:messages) and msgr.userId = :userId";

    private final static String QL_FETCH_MESSAGE_IDS_BY_ATTACHMENT_TYPE2 = "SELECT DISTINCT att.message.id FROM Attachment att " +
            "WHERE att.message in (:messages) and att.type = :attachmentType";

    private final static String QUERY_MESSAGE_SELECT_M = "SELECT m ";
    private final static String QUERY_MESSAGE_SELECT_COUNT = "SELECT COUNT(m.id) ";
    private final static String QUERY_MESSAGE_LOCAL_PARTY = "FROM Message m "
            + "WHERE m.localParty = :localParty "
            + "AND  m.msgState = :msgState "
            + "AND  m.activeState = 1 ";

    private final static String QUERY_MESSAGE_LOCAL_PARTY_WITH_STATUS = "FROM Message m "
            + "JOIN m.lastStatus ls "
            + "WHERE m.localParty = :localParty "
            + "AND  m.msgState = :msgState "
            + "AND  m.activeState = 1 "
            + "AND  ls.mstStatus = :mstStatus ";

    private final static String QUERY_MESSAGE_LOCAL_PARTY_WITHOUT_STATUS = "FROM Message m "
            + "WHERE m.localParty = :localParty "
            + "AND  m.msgState = :msgState "
            + "AND  m.activeState = 1 "
            + "AND  m.lastStatus is null ";


    private final static String QUERY_DOSSIER_REF =
            "SELECT m.referenceId "
                    + "FROM Message m "
                    + "WHERE m.localParty = :localParty "
                    + "AND  m.msgState = :msgState "
                    + "AND  m.activeState = 1 ";

    private final static String QUERY_DOSSIER_REF_END =
            " GROUP BY m.referenceId ";

    private final static String QL_FILTER_SUBJECT = " and m.searchableContent like :subject";

    private final static String QL_FILTER_DOSSIERS = " and m.referenceId IN (:referenceIds)";

    private final static String QL_FILTER_DOSSIERS_WITH_NULLS = " and ( m.referenceId IN (:referenceIds) or m.referenceId is null)";

    private final static String QL_FILTER_DOSSIERS_WITH_NULLS_ONLY = " and m.referenceId is null";

    private final static String QL_COUNT_UNREAD_MESSAGES = "SELECT COUNT(m.id) " +
            "FROM Message m " +
            "WHERE  m.localParty.id = :localPartyId " +
            "AND m.msgState = :msgState " +
            "AND m.activeState = 1 " +
            "AND NOT EXISTS (" +
            "   SELECT mr.id " +
            "   FROM MessageRead mr " +
            "   where mr.messageId = m.id AND mr.userId = :userId " +
            " ) ";

    private final static String QL_FETCH_UNREAD_MESSAGES = "SELECT m " +
            "FROM Message m " +
            "WHERE NOT EXISTS " +
            "(SELECT mrs.id FROM MessageRead mrs WHERE mrs.messageId = m.id AND mrs.userId = :userId) " +
            "AND m.localParty=:localParty " +
            "AND m.msgState=:msgState " +
            "AND m.activeState = 1 ";


    private final static String QUERY_DOSSIER = "SELECT m.referenceId " +
            "FROM Message m " +
            "WHERE " +
            "m.localParty = :localParty  " +
            "AND m.msgState = :msgState  " +
            "AND m.activeState = 1 " +
            "AND m.searchableContent like :subject " +
            "GROUP BY m.referenceId" ;


    private final static String QUERY_DOSSIER_UNREAD = "SELECT m.referenceId " +
            "FROM Message m " +
            "WHERE " +
            "m.localParty = :localParty  " +
            "AND m.msgState = :msgState  " +
            "AND m.activeState = 1 " +
            "AND m.searchableContent like :subject " +
            "AND NOT EXISTS (" +
            "   SELECT mr.id " +
            "   FROM MessageRead mr " +
            "   where mr.messageId = m.id AND mr.userId = :userId " +
            " ) " +
            "GROUP BY m.referenceId" ;

    private final static String QUERY_DOSSIER_UNREAD_SELECT_REF = "SELECT m.referenceId " +
            "FROM Message m " +
            "WHERE NOT EXISTS " +
            "(SELECT mrs.id FROM MessageRead mrs WHERE mrs.messageId = m.id AND mrs.userId = :userId) " +
            "AND m.localParty = :localParty " +
            "AND m.msgState = :msgState " +
            "AND m.activeState = 1 ";

    private final static String QL_FETCH_MESSAGE_BY_BUNDLE_ID_RECIPIENT_REF_SENDER_REF = "SELECT m " +
            "FROM Message m " +
            "WHERE m.bundleId = :bundleId " +
            "AND m.localParty.id = :localPartyId " +
            "AND m.remoteParty.nodeName = :remotePartyName";

    private final static String QL_FETCH_WARNING_MESSAGES = "SELECT m FROM Message m " +
            "INNER JOIN m.localParty.business b " +
            "INNER JOIN FETCH b.businessConfigs bc " +
            "WHERE m.msgState = 'INCOMING' " +
            "AND bc.propertyName = :mailNotifEnabledCode AND bc.propertyValue = :mailNotifEnabledValue " +
            "AND m.createdOn between :startDate AND :endDate " +
            "AND m.activeState = 1 " +
            "AND (SELECT COUNT(nt) FROM Notification nt WHERE nt.notificationType = 'WARNING_EMAIL' AND nt.message = m) = 0";

    private static final String QL_FETCH_MESSAGE_AND_SIGNATURE = "SELECT m FROM Message m LEFT OUTER JOIN FETCH m.signatures s WHERE m.id = :messageId";

    @Override
    public long countMessages(String subjectFilter, Message.MessageState messageState, Party localParty) {

        String query = buildMessageQuery(QUERY_MESSAGE_LOCAL_PARTY, subjectFilter);

        Query countQuery = entityManager.createQuery(QUERY_MESSAGE_SELECT_COUNT + query);
        countQuery.setParameter("localParty", localParty);
        countQuery.setParameter("msgState", messageState.name());
        populateMessageQueryParameters(countQuery, subjectFilter);

        return (Long) countQuery.getSingleResult();
    }

    @Override
    public long countMessages(String subjectFilter, Message.MessageState messageState, MessageStatus.Status messageStatus, Party localParty) {
        String query = messageStatus == null ? buildMessageQuery(QUERY_MESSAGE_LOCAL_PARTY_WITHOUT_STATUS, subjectFilter)
                : buildMessageQuery(QUERY_MESSAGE_LOCAL_PARTY_WITH_STATUS, subjectFilter);

        Query countQuery = entityManager.createQuery(QUERY_MESSAGE_SELECT_COUNT + query);
        countQuery.setParameter("localParty", localParty);
        countQuery.setParameter("msgState", messageState.name());
        if (messageStatus != null) {
            countQuery.setParameter("mstStatus", messageStatus.name());
        }
        populateMessageQueryParameters(countQuery, subjectFilter);

        return (Long) countQuery.getSingleResult();
    }

    @Override
    public List<Message> getMessages(String subjectFilter, Message.MessageState messageState, Party localParty,
                                     MessageColumn sort, boolean isAscending, int startIndex, int maxResults) {

        String query = buildMessageQuery(QUERY_MESSAGE_LOCAL_PARTY, subjectFilter);
        query = buildMessageSorters(query, sort, isAscending);

        TypedQuery<Message> dataQuery = entityManager.createQuery(QUERY_MESSAGE_SELECT_M + query, Message.class);

        dataQuery.setFirstResult(startIndex - 1); // (>min &&
        // <=max)
        dataQuery.setMaxResults(maxResults);

        dataQuery.setParameter("msgState", messageState.name());
        dataQuery.setParameter("localParty", localParty);

        populateMessageQueryParameters(dataQuery, subjectFilter);

        return dataQuery.getResultList();
    }

    @Override
    public List<Message> getMessages(String subjectFilter, MessageState messageState, MessageStatus.Status messageStatus,
                                     Party localParty, MessageColumn sort, boolean isAscending, int startIndex, int maxResults) {

        String query = messageStatus == null ? buildMessageQuery(QUERY_MESSAGE_LOCAL_PARTY_WITHOUT_STATUS, subjectFilter) :
                buildMessageQuery(QUERY_MESSAGE_LOCAL_PARTY_WITH_STATUS, subjectFilter);

        query = buildMessageSorters(query, sort, isAscending);

        TypedQuery<Message> dataQuery = entityManager.createQuery(QUERY_MESSAGE_SELECT_M + query, Message.class);

        dataQuery.setFirstResult(startIndex - 1); // (>min &&
        // <=max)
        dataQuery.setMaxResults(maxResults);

        dataQuery.setParameter("msgState", messageState.name());
        dataQuery.setParameter("localParty", localParty);
        if (messageStatus != null) {
            dataQuery.setParameter("mstStatus", messageStatus.name());
        }

        populateMessageQueryParameters(dataQuery, subjectFilter);

        return dataQuery.getResultList();
    }

    @Override
    public long countMessagesByDossier(String subjectFilter, Message.MessageState messageState, Party localParty) {

        Query countQuery = entityManager.createQuery(QUERY_DOSSIER);
        if (StringUtils.isEmpty(subjectFilter)) {
            subjectFilter="";
        }
        countQuery.setParameter("localParty", localParty);
        countQuery.setParameter("msgState", messageState.name());
        countQuery.setParameter("subject", '%' + MessageUtil.normalizeString(subjectFilter) + '%');

        return countQuery.getResultList().size();
    }

    @Override
    public List<Message> getMessagesByDossier(String subjectFilter, Message.MessageState messageState, Party localParty,
                                              MessageColumn sort, boolean isAscending, int startIndex, int maxResults) {

        String selectWithFilters = buildMessageQuery(QUERY_DOSSIER_REF, subjectFilter);
        String groupedByDossier = selectWithFilters + QUERY_DOSSIER_REF_END;
        groupedByDossier = buildMessageDossierSorters(groupedByDossier, sort, isAscending);

        TypedQuery<String> dossierReferenceIdsQuery = entityManager.createQuery(groupedByDossier, String.class);

        dossierReferenceIdsQuery.setFirstResult(startIndex - 1);
        dossierReferenceIdsQuery.setMaxResults(maxResults);

        dossierReferenceIdsQuery.setParameter("msgState", messageState.name());
        dossierReferenceIdsQuery.setParameter("localParty", localParty);
        populateMessageQueryParameters(dossierReferenceIdsQuery, subjectFilter);

        List<String> dossierReferenceIdList = dossierReferenceIdsQuery.getResultList();

        List<Message> messageList = new ArrayList<>();
        if (!dossierReferenceIdList.isEmpty()) {
            String query = buildMessageQuery(QUERY_MESSAGE_SELECT_M + QUERY_MESSAGE_LOCAL_PARTY, subjectFilter);
            if (dossierReferenceIdList.contains(null)) {
                dossierReferenceIdList.remove(null);
                if (!dossierReferenceIdList.isEmpty()) {
                    query += QL_FILTER_DOSSIERS_WITH_NULLS;
                } else {
                    query += QL_FILTER_DOSSIERS_WITH_NULLS_ONLY;
                }
            } else {
                query += QL_FILTER_DOSSIERS;
            }
            query = buildMessageSorters(query, sort, isAscending);
            TypedQuery<Message> dataQuery = entityManager.createQuery(query, Message.class);
            dataQuery.setParameter("msgState", messageState.name());
            dataQuery.setParameter("localParty", localParty);
            if (!dossierReferenceIdList.isEmpty()) {
                dataQuery.setParameter("referenceIds", dossierReferenceIdList);
            }
            populateMessageQueryParameters(dataQuery, subjectFilter);

            messageList.addAll(dataQuery.getResultList());
        }

        return messageList;
    }

    private String buildMessageQuery(String query, String subjectFilter) {

        if (!StringUtils.isEmpty(subjectFilter)) {
            query = query + QL_FILTER_SUBJECT;
        }

        return query;
    }

    private void populateMessageQueryParameters(Query typedQuery, String subjectFilter) {
        if (!StringUtils.isEmpty(subjectFilter)) {
            typedQuery.setParameter("subject", '%' + MessageUtil.normalizeString(subjectFilter) + '%');
        }
    }

    private String buildMessageSorters(String query, MessageColumn sortColumn, boolean isAscending) {

        if (sortColumn != null) {
            query = query + " ORDER BY m." + sortColumn.getColumn();
            query = query + (isAscending ? " ASC" : " DESC");
        }
        return query;
    }


    private String buildMessageDossierSorters(String query, MessageColumn sort, boolean isAscending) {
        if (sort != null) {
            String minMax = isAscending ? "min" : "max";
            query = query + " ORDER BY " + minMax + "(m." + sort.getColumn() + ")";
            query = query + (isAscending ? " ASC" : " DESC");
        }
        return query;
    }

    @Override
    public Message findByReferenceId(String refId) {

        if (refId.contains(".")) {
            refId = refId.substring(0, refId.indexOf("."));

        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query = cb.createQuery(Message.class);
        Root<Message> attRoot = query.from(Message.class);
        query.where(cb.equal(attRoot.get("referenceId"), refId));
        Message md;
        try {
            md = entityManager.createQuery(query).getSingleResult();
            return md;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Message findByMessageBundleId(String bundleId, Long localPartyId, String remotePartyName) {

        Message md;
        try {
            TypedQuery<Message> readMessagesQuery = entityManager.createQuery(QL_FETCH_MESSAGE_BY_BUNDLE_ID_RECIPIENT_REF_SENDER_REF, Message.class);
            readMessagesQuery.setParameter("bundleId", bundleId);
            readMessagesQuery.setParameter("localPartyId", localPartyId);
            readMessagesQuery.setParameter("remotePartyName", remotePartyName);

            md = readMessagesQuery.getSingleResult();
            return md;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public long getUnreadCount(User user, Party party, MessageState msgState) {
        Query totalMessagesCountQuery = entityManager.createQuery(QL_COUNT_UNREAD_MESSAGES);
        totalMessagesCountQuery.setParameter("localPartyId", party.getId());
        totalMessagesCountQuery.setParameter("msgState", msgState.name());
        totalMessagesCountQuery.setParameter("userId", user.getId());
        return (Long) totalMessagesCountQuery.getSingleResult();
    }

    @Override
    public long countUnreadMessages(User user, String subjectFilter, MessageState messageState, Party localParty) {

        String countQueryString = QL_COUNT_UNREAD_MESSAGES;

        // query total row count
        if (!StringUtils.isEmpty(subjectFilter)) {
            countQueryString += QL_FILTER_SUBJECT;
        }
        Query countQuery = entityManager.createQuery(countQueryString);

        countQuery.setParameter("localPartyId", localParty.getId());
        countQuery.setParameter("msgState", messageState.name());
        countQuery.setParameter("userId", user.getId());
        populateMessageQueryParameters(countQuery, subjectFilter);

        return (Long) countQuery.getSingleResult();
    }

    @Override
    public List<Message> getUnreadMessages(User user, String subjectFilter, MessageState messageState, Party localParty,
                                           MessageColumn sort, boolean isAscending, int startIndex, int maxResults) {

        // here it starts the second part of the method, loading the messages
        String dataQueryString;
        if (!StringUtils.isEmpty(subjectFilter)) {
            dataQueryString = buildMessageSorters(QL_FETCH_UNREAD_MESSAGES + QL_FILTER_SUBJECT, sort, isAscending);
        } else {
            dataQueryString = buildMessageSorters(QL_FETCH_UNREAD_MESSAGES, sort, isAscending);
        }

        TypedQuery<Message> dataQuery = entityManager.createQuery(dataQueryString, Message.class);
        dataQuery.setFirstResult(startIndex - 1); // (>min &&
        dataQuery.setMaxResults(maxResults);

        dataQuery.setParameter("localParty", localParty);
        dataQuery.setParameter("msgState", messageState.name());
        dataQuery.setParameter("userId", user.getId());

        populateMessageQueryParameters(dataQuery, subjectFilter);

        return dataQuery.getResultList();
    }

    @Override
    public long countUnreadMessagesByDossier(User user, String subjectFilter, MessageState messageState, Party localParty) {

        Query countQuery = entityManager.createQuery(QUERY_DOSSIER_UNREAD);
        if (StringUtils.isEmpty(subjectFilter)) {
            subjectFilter="";
        }
        countQuery.setParameter("localParty", localParty);
        countQuery.setParameter("msgState", messageState.name());
        countQuery.setParameter("userId", user.getId());
        countQuery.setParameter("subject", '%' + MessageUtil.normalizeString(subjectFilter) + '%');

        return countQuery.getResultList().size();
    }

    @Override
    public List<Message> getUnreadMessagesByDossier(User user, String subjectFilter, MessageState messageState, Party localParty,
                                                    MessageColumn sort, boolean isAscending, int startIndex, int maxResults) {

        //here it starts the second part of the method, loading of the messages
        String selectWithFilters = buildMessageQuery(QUERY_DOSSIER_UNREAD_SELECT_REF, subjectFilter);
        String groupedByDossier = selectWithFilters + QUERY_DOSSIER_REF_END;
        groupedByDossier = buildMessageDossierSorters(groupedByDossier, sort, isAscending);

        TypedQuery<String> dossierQuery = entityManager.createQuery(groupedByDossier, String.class);

        dossierQuery.setFirstResult(startIndex - 1); // (>min &&
        dossierQuery.setMaxResults(maxResults);

        dossierQuery.setParameter("msgState", messageState.name());
        dossierQuery.setParameter("localParty", localParty);
        dossierQuery.setParameter("userId", user.getId());
        populateMessageQueryParameters(dossierQuery, subjectFilter);

        List<String> dossierList = dossierQuery.getResultList();

        List<Message> messageList = new ArrayList<>();
        if (!dossierList.isEmpty()) {
            String query = buildMessageQuery(QL_FETCH_UNREAD_MESSAGES, subjectFilter);
            if (dossierList.contains(null)) {
                dossierList.remove(null);
                if (!dossierList.isEmpty()) {
                    query += QL_FILTER_DOSSIERS_WITH_NULLS;
                } else {
                    query += QL_FILTER_DOSSIERS_WITH_NULLS_ONLY;
                }
            } else {
                query += QL_FILTER_DOSSIERS;
            }
            query = buildMessageSorters(query, sort, isAscending);
            TypedQuery<Message> dataQuery = entityManager.createQuery(query, Message.class);
            dataQuery.setParameter("msgState", messageState.name());
            dataQuery.setParameter("localParty", localParty);
            if (!dossierList.isEmpty()) {
                dataQuery.setParameter("referenceIds", dossierList);
            }
            dataQuery.setParameter("userId", user.getId());
            populateMessageQueryParameters(dataQuery, subjectFilter);

            messageList.addAll(dataQuery.getResultList());
        }

        return messageList;
    }

    @Override
    public List<Long> getSubMessagesReadByUser(List<Message> messages, Long userId) {
        if (messages.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> resultList = new ArrayList<>();
        TypedQuery<Long> readMessagesForUserQuery = entityManager.createQuery(QL_FETCH_READ_MESSAGES_FOR_USERID, Long.class);
        readMessagesForUserQuery.setParameter("userId", userId);

        for (List<Message> chunk : CollectionUtil.breakCollectionInSubLists(messages, ORACLE_MAX_IN_STATEMENT_VALUES)) {
            readMessagesForUserQuery.setParameter("messages", chunk);
            resultList.addAll(readMessagesForUserQuery.getResultList());
        }

        return resultList;
    }

    @Override
    public List<Long> getMessageIdsWithBinaryAttachments(List<Message> messages) {
        if (messages.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> resultList = new ArrayList<>();
        TypedQuery<Long> messagesWithBinaryAttachmentsQuery = entityManager.createQuery(QL_FETCH_MESSAGE_IDS_BY_ATTACHMENT_TYPE2, Long.class);
        messagesWithBinaryAttachmentsQuery.setParameter("attachmentType", AttachmentType.BINARY.name());

        for (List<Message> chunk : CollectionUtil.breakCollectionInSubLists(messages, ORACLE_MAX_IN_STATEMENT_VALUES)) {
            messagesWithBinaryAttachmentsQuery.setParameter("messages", chunk);
            resultList.addAll(messagesWithBinaryAttachmentsQuery.getResultList());
        }

        return resultList;
    }

    @Override
    public List<Message> getWarningMessages(Date startDate, Date endDate) {
        TypedQuery<Message> warningMessagesQuery = entityManager.createQuery(QL_FETCH_WARNING_MESSAGES, Message.class);
        warningMessagesQuery.setParameter("startDate", startDate);
        warningMessagesQuery.setParameter("endDate", endDate);
        warningMessagesQuery.setParameter("mailNotifEnabledCode", BusinessConfigProperty.BUS_WARN_EMAIL_NOTIF_ENABLED.getCode());
        warningMessagesQuery.setParameter("mailNotifEnabledValue", "true");
        return warningMessagesQuery.getResultList();
    }

    @Override
    public Message findByIdAndFetchSignature(Long messageId) {
        TypedQuery<Message> msgSignatureQuery = entityManager.createQuery(QL_FETCH_MESSAGE_AND_SIGNATURE, Message.class);
        msgSignatureQuery.setParameter("messageId", messageId);
        try {
            return msgSignatureQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Message instance) {
        setSearchableMessageContent(instance);
        super.save(instance);
    }

    @Override
    public Message update(Message entity) {
        setSearchableMessageContent(entity);
        return super.update(entity);
    }

    private void setSearchableMessageContent(Message entity) {
        entity.setSearchableContent(MessageUtil.normalizeString(entity.getSubject()));
    }
}
