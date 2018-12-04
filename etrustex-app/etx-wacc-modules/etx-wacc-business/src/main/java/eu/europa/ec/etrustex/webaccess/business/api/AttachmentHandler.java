/**
 *
 */
package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
import eu.europa.ec.etrustex.webaccess.webservice.model.NodeAttachment;

import javax.activation.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

/**
 * @author apladap
 */
public interface AttachmentHandler {

    void savePayloadMetadata(String xmlResult, long msgId, Metadata.MetadataState state);

    Metadata getMetadata(long messageID);

    String retrievePayloadContent(Long messageId);

    void retrievePayloadForMessage(String loggedInUser, Long messageId);

    HashMap<String, String> getMapOfAttachmentFilenamesPerReferenceIds(long msgId);

    NodeAttachment retrieveNodeAttachment(Attachment attachment);

    Attachment getAttachment(Long attachmentId);

    List<Attachment> getAttachments(List<Long> attachmentIds);

    Long uploadAttachmentToNode(long localPartyId, String referenceId, String name, String path, long size, String mimeType,
                                String attachmentType, byte[] contentChecksum, String contentChecksumMethod, byte[] transmissionChecksum,
                                String transmissionChecksumMethod, byte[] encryptedKey, String encryptionCertificateX509SubjectName,
                                DataSource dataSource) throws Exception;
}
