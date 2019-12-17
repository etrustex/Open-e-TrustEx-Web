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
    NodeAttachment retrieveNodeAttachment(Attachment attachment, boolean downloadAsSender);

    Attachment getAttachment(Long attachmentId);

    List<Attachment> getAttachments(List<Long> attachmentIds);

    void uploadAttachmentToNode(long localPartyId, String referenceId, long size, String attachmentType,
                                byte[] encryptedKey, String encryptionCertificateX509SubjectName,
                                DataSource dataSource) throws Exception;
    Attachment saveAttachmentInfo(String referenceId, String name, String path, String mimeType, long size,
                                  String attachmentType, byte[] transmissionChecksum, String transmissionChecksumMethod);
}
