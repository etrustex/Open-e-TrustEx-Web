/**
 * 
 */
package eu.europa.ec.etrustex.webaccess.persistence;


import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.Message;

import java.util.List;

/**
 * @author apladap
 *
 */
public interface AttachmentDAO extends AbstractDAO<Attachment, Long> {

	/**
	 *
	 * @param bundleId
	 * @param wrapperId
	 * @return
	 */
	Attachment findNotIncomingByBundleIdAndAndWrapperId(String bundleId, String wrapperId);

	/**
	 * Search for particular attachment
	 * 
	 * @param refId - requested attachment identifier
	 * @param currentMsgID - requested message identifier
	 * @return - requested attachment data
	 */
	Attachment findByReferenceId(String refId, long currentMsgID);

	/**
	 * Finds all the attachments of a given message ID
	 * 
	 * @param msgId - attachment's bind message id
	 */
	List<Attachment> getAttachmentsListByMessageId(long msgId);

    /**
     * Finds all the attachments with a given type linked to a message
     *
     * @param msgId - the message to which the attachments are linked
     * @param attachmentType the attachment type searched
     * @return the list of attachments matching the type and message provided
     */
	List<Attachment> getAttachmentsListByMessageIdAndType(long msgId, AttachmentType attachmentType);

	/**
	 * Counts all the attachments with a given type linked to a message
     */
	Long countAttachmentsByMessageAndType(long msgId, AttachmentType attachmentType);

	/**
	 * Find attachments for given ids
	 * @param attachmentIds attachment ids
	 * @return the list of attachments
	 */
	List<Attachment> getAttachmentsByIds(List<Long> attachmentIds);
}
