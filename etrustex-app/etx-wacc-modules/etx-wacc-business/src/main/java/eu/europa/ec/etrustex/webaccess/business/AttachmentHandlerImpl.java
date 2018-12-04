/**
 *
 */
package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.persistence.AttachmentDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MetadataDAO;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.webservice.document.DocumentService;
import eu.europa.ec.etrustex.webaccess.webservice.model.NodeAttachment;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author apladap
 */
@Service
public class AttachmentHandlerImpl implements AttachmentHandler {

    private Logger logger = Logger.getLogger(AttachmentHandlerImpl.class);

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private MetadataDAO metadataDAO;

    @Autowired
    protected UserSessionContext userSessionContext;

    @Autowired
    private DocumentService documentService;

    private InputStream downloadFileStream(String fileRefId, Message message, Party localParty) {

        Attachment attachment = attachmentDAO.findByReferenceId(fileRefId, message.getId());
        AttachmentType attachment_type = attachment.getType();

        String senderPartyName = message.getMsgState().equals(Message.MessageState.INCOMING) ?
                message.getRemoteParty().getNodeName() : localParty.getNodeName();
        final NodeAttachment nodeAttachment = documentService.downloadFileStream(localParty.getNodeUserName(), localParty.getNodeUserPass(),
                fileRefId, localParty.getNodeName(),
                senderPartyName, attachment_type.name());
        return nodeAttachment != null ? nodeAttachment.getInputStream() : null;
    }

    @Override
    @Transactional(readOnly = false)
    public void savePayloadMetadata(String xmlResult, long msgID, Metadata.MetadataState state) {

        Metadata metadata = new Metadata();
        metadata.setContent(xmlResult);
        metadata.setMessageId(msgID);
        metadata.setMetadataState(state);

        Metadata previousRecord = metadataDAO.retrievePayload(msgID);

        if (previousRecord == null) {
            metadataDAO.save(metadata);
        } else {
            //For proper metadata update we have to set the id to the new record
            metadata.setId(previousRecord.getId());
            metadataDAO.update(metadata);
        }
    }

    @Override
    public Metadata getMetadata(long messageID) {
        return metadataDAO.retrievePayload(messageID);
    }

    @Transactional(readOnly = true, timeout = 150)
    public String retrievePayloadContent(Long messageId) {
        logger.info("Start retrieving payload: message id [" + messageId + "]");

            String payloadFileRefId = null;
            String fileContent = null;
            try {

                Metadata metadata = metadataDAO.retrievePayload(messageId);

                if (metadata == null) {
                    Message message = messageDAO.findById(messageId);
                    Party localParty = message.getLocalParty();
                    for (Attachment attachment : message.getAttachments()) {
                        if (attachment.getType().equals(AttachmentType.METADATA)) {
                            payloadFileRefId = attachment.getWrapperId();
                            break;
                        }
                    }

                    if (payloadFileRefId != null) {
                        //download the Payload file
                        StringBuilder stringBuilder = new StringBuilder();

                        InputStream inputStream = downloadFileStream(payloadFileRefId, message, localParty);

                        if (inputStream != null) {
                            try (InputStreamReader inReader = new InputStreamReader(inputStream, "UTF-8")) {
                                char[] buffer = new char[8 * 1024];
                                int read = 0;
                                while ((read = inReader.read(buffer)) > -1) {
                                    stringBuilder.append(Arrays.copyOfRange(buffer, 0, read));
                                }
                            }

                            fileContent = stringBuilder.toString();


                        } else {
                            logger.warn("Cannot download payload for message with id : " + messageId + " Bundle id: " + message.getBundleId());
                        }
                    } else {
                        logger.info("cannot find payload in the attachment of this message : " + messageId + " Bundle id: " + message.getBundleId());
                    }
                } else {
                    fileContent = metadata.getContent();
                }
            } catch (UnsupportedEncodingException exc) {
                logger.error("UTF-8 encoding is not supported" + exc);
                throw new EtxException("UTF-8 encoding is not supported", exc);
            } catch (IOException e) {
                logger.error("Couldn't open an input stream to the archive. The archive doesn't exist: " + e);
                throw new EtxException("Couldn't open an input stream to the archive. The archive doesn't exist", e);
            }
        return fileContent;
    }


    @Transactional(readOnly = false, timeout = 150)
    public void retrievePayloadForMessage(String loggedInUser, Long messageId) {
        logger.info("Start retrieving payload: message id [" + messageId + "]");

        //Here we have to find the payload.xml and persist it separately
        Metadata meta = getMetadata(messageId);

        if (meta == null) {

            String payloadFileRefId = null;
            String fileContent = null;

            try {
                // save the payload content to the DB
                fileContent = retrievePayloadContent(messageId);

                if (fileContent != null) {
                    savePayloadMetadata(fileContent, messageId, Metadata.MetadataState.DOWNLOADED);
                    logger.info("Complete retrieving payload: message id [" + messageId + "]");
                }
            } catch (EtxException exc) {
                logger.error("EtxException thrown: " + exc);
                savePayloadMetadata(fileContent, messageId, Metadata.MetadataState.FAILED);
            }
        } else {
            logger.error("Payload is already downloaded for message(messageId: " + messageId + ") content to the DB");
        }
    }


    @Override
    public HashMap<String, String> getMapOfAttachmentFilenamesPerReferenceIds(long msgId) {
        try {
            List<Attachment> resultList = attachmentDAO.getAttachmentsListByMessageId(msgId);

            HashMap<String, String> mapOfReferenceIdsPerFilename = new HashMap<>();
            for (Attachment tempElement : resultList) {
                mapOfReferenceIdsPerFilename.put(tempElement.getWrapperId(), tempElement.getFileName());
            }
            return mapOfReferenceIdsPerFilename;
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    @Override
    public Attachment getAttachment(Long attachmentId) {
        return attachmentDAO.findById(attachmentId);
    }

    @Override
    @Transactional(readOnly = false)
    public Long uploadAttachmentToNode(long localPartyId, String referenceId, String name, String path, long size, String mimeType,
                                       String attachmentType, byte[] contentChecksum, String contentChecksumMethod,
                                       byte[] transmissionChecksum, String transmissionChecksumMethod, byte[] encryptedKey,
                                       String encryptionCertificateX509SubjectName, DataSource dataSource) throws Exception {

        final Party localParty = userSessionContext.getMessagePartyById(localPartyId);

        documentService.uploadFileStream(localParty.getNodeUserName(), localParty.getNodeUserPass(), localParty.getNodeName(),
                referenceId, size, AttachmentType.valueOf(attachmentType),
                encryptedKey, encryptionCertificateX509SubjectName, dataSource);

        Attachment attachment = new Attachment();
        attachment.setWrapperId(referenceId);
        attachment.setFileName(name);
        attachment.setFilePath(path);
        attachment.setMimeType(mimeType);
        attachment.setFileSize(size);
        attachment.setType(AttachmentType.valueOf(attachmentType));
        attachment.setTransmissionChecksum(transmissionChecksum);
        attachment.setTransmissionChecksumMethod(transmissionChecksumMethod);
        attachment.setContentChecksum(contentChecksum);
        attachment.setContentChecksumMethod(contentChecksumMethod);

        attachmentDAO.save(attachment);

        logger.info("Persisted attachment [id:" + attachment.getId() + ", wrapperId: " + attachment.getWrapperId() + "] for party: " + localParty.getNodeName());

        return attachment.getId();
    }

    @Override
    public List<Attachment> getAttachments(List<Long> attachmentIds) {
        return CollectionUtils.isNotEmpty(attachmentIds) ? attachmentDAO.getAttachmentsByIds(attachmentIds) : new ArrayList<Attachment>();
    }


    @Override
    public NodeAttachment retrieveNodeAttachment(Attachment attachment) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        logger.info("Start downloading attachment from Node [" + attachment.getWrapperId() +"]");

        final Message message = attachment.getMessage();
        final Party localParty = message.getLocalParty();
        final String remotePartyName = message.getRemoteParty().getNodeName();
        final AttachmentType attachmentType = attachment.getType();

        final NodeAttachment nodeAttachment = documentService.downloadFileStream(localParty.getNodeUserName(), localParty.getNodeUserPass(), attachment.getWrapperId(),
                localParty.getNodeName(), remotePartyName, attachmentType.name());

        stopWatch.stop();
        logger.info("Finished downloading attachment from Node [" + attachment.getWrapperId() + "]. Total time: " + stopWatch.toString());

        return nodeAttachment;
    }
}
