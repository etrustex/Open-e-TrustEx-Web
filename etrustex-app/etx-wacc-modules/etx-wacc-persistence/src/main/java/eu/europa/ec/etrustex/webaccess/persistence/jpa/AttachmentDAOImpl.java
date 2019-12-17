/**
 *
 */
package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.persistence.AttachmentDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author apladap
 */
@Repository
public class AttachmentDAOImpl extends AbstractDAOImpl<Attachment, Long> implements AttachmentDAO {

    private final static String QUERY_ATTACHMENT_BY_MESSAGEID = "SELECT a FROM Attachment a WHERE a.message.id = :messageId ORDER BY a.fileName";

    private final static String QUERY_ATTACHMENT_BY_MESSAGEID_AND_TYPE = "SELECT a FROM Attachment a WHERE a.message.id = :messageId AND a.type = :attachmentType ORDER BY a.fileName";

    private final static String QUERY_COUNT_ATTACHMENT_BY_MESSAGEID_AND_TYPE = "SELECT COUNT(att) FROM Attachment att WHERE att.message.id = :messageId AND att.type = :attachmentType";

    private final static String QUERY_ATTACHMENT_BY_MESSAGEID_AND_REFERENCEID = "SELECT a FROM Attachment a WHERE a.message.id = :messageId AND a.wrapperId = :referenceId";

    private final static String QUERY_ATTACHMENTS_BY_ATTACHMENT_IDS = "SELECT a FROM Attachment a WHERE a.id IN (:attachmentIds)";

    private final static String QUERY_ATTACHMENT_NOT_INCOMING_BY_BUNDLE_ID_AND_WRAPPER_ID =
            "SELECT a FROM Attachment a WHERE a.message.bundleId = :bundleId AND a.message.msgState != :messageState AND a.wrapperId = :wrapperId";

    @Override
    public Attachment findNotIncomingByBundleIdAndAndWrapperId(String bundleId, String wrapperId) {
        TypedQuery<Attachment> attachmentQuery = entityManager.createQuery(QUERY_ATTACHMENT_NOT_INCOMING_BY_BUNDLE_ID_AND_WRAPPER_ID, Attachment.class);
        attachmentQuery.setParameter("bundleId", bundleId);
        attachmentQuery.setParameter("messageState", Message.MessageState.INCOMING);
        attachmentQuery.setParameter("wrapperId", wrapperId);
        return attachmentQuery.getSingleResult();
    }

    @Override
    public Attachment findByReferenceId(String refId, long currentMsgId) {

        TypedQuery<Attachment> attachmentQuery = entityManager.createQuery(QUERY_ATTACHMENT_BY_MESSAGEID_AND_REFERENCEID, Attachment.class);
        attachmentQuery.setParameter("messageId", currentMsgId);
        attachmentQuery.setParameter("referenceId", refId);
        return attachmentQuery.getSingleResult();
    }

    @Override
    public List<Attachment> getAttachmentsListByMessageId(long msgId) {
        TypedQuery<Attachment> dataQuery = entityManager.createQuery(QUERY_ATTACHMENT_BY_MESSAGEID, Attachment.class);
        dataQuery.setParameter("messageId", msgId);

        return dataQuery.getResultList();
    }

    @Override
    public List<Attachment> getAttachmentsListByMessageIdAndType(long msgId, AttachmentType attachmentType) {
        TypedQuery<Attachment> dataQuery = entityManager.createQuery(QUERY_ATTACHMENT_BY_MESSAGEID_AND_TYPE, Attachment.class);
        dataQuery.setParameter("messageId", msgId);
        dataQuery.setParameter("attachmentType", attachmentType.name());

        return dataQuery.getResultList();
    }

    @Override
    public Long countAttachmentsByMessageAndType(long msgId, AttachmentType attachmentType) {
        TypedQuery<Long> query = entityManager.createQuery(QUERY_COUNT_ATTACHMENT_BY_MESSAGEID_AND_TYPE, Long.class);
        query.setParameter("messageId", msgId);
        query.setParameter("attachmentType", attachmentType.name());
        return query.getSingleResult();
    }

    @Override
    public List<Attachment> getAttachmentsByIds(List<Long> attachmentIds) {
        TypedQuery<Attachment> dataQuery = entityManager.createQuery(QUERY_ATTACHMENTS_BY_ATTACHMENT_IDS, Attachment.class);
        dataQuery.setParameter("attachmentIds", attachmentIds);

        return dataQuery.getResultList();
    }
}
