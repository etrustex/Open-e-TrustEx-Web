package eu.europa.ec.etrustex.webaccess.web.rest;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.workspace.WorkspaceServiceImpl;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.rest.AttachmentRestService;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.webservice.model.NodeAttachment;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.FileDataSource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service("attachmentService")
public class AttachmentRestServiceImpl implements AttachmentRestService {
    private static final Logger logger = Logger.getLogger(AttachmentRestServiceImpl.class);

    @Autowired
    private AttachmentHandler attachmentHandler;

    @Autowired
    protected SecurityChecker securityChecker;

    @Autowired
    private WorkspaceServiceImpl workspaceService;

//    @Override
//    @Transactional
//    public Response read(Long id) throws IOException {
//        Response.Status responseStatus = Response.Status.BAD_REQUEST;
//        Response response = null;
//
//        final Attachment attachment = attachmentHandler.getAttachment(id);
//        if (attachment != null) {
//            Message message = attachment.getMessage();
//
//            if (isAuthorized(message)) {
//
//                final NodeAttachment nodeAttachment = attachmentHandler.retrieveNodeAttachment(attachment);
//                if (nodeAttachment != null) {
//
//                    final Response.ResponseBuilder responseBuilder = Response.ok(nodeAttachment.getInputStream(), MediaType.APPLICATION_OCTET_STREAM_TYPE)
//                            .header(AttachmentRestService.CONTENT_DISPOSITION_HEADER, "attachment; filename=" + attachment.getFileName())
//                            .header(AttachmentRestService.CONTENT_LENGTH_HEADER, Long.toString(nodeAttachment.getDocumentSize()))
//                            .header(AttachmentRestService.TRANSMISSION_CHECKSUM_HEADER, DatatypeConverter.printBase64Binary(attachment.getTransmissionChecksum()))
//                            .header(AttachmentRestService.TRANSMISSION_CHECKSUM_METHOD_HEADER, attachment.getTransmissionChecksumMethod())
//                            .header(AttachmentRestService.CONTENT_CHECKSUM_HEADER, attachment.getContentChecksum() != null ? DatatypeConverter.printBase64Binary(attachment.getContentChecksum()) : null)
//                            .header(AttachmentRestService.CONTENT_CHECKSUM_METHOD_HEADER, attachment.getContentChecksumMethod())
//                            .header(AttachmentRestService.IS_SIGNED, CollectionUtils.isNotEmpty(message.getSignatures()));
//
//                    String base64SessionKey = nodeAttachment.getSessionKey() != null ? DatatypeConverter.printBase64Binary(nodeAttachment.getSessionKey()) : null;
//                    if (base64SessionKey != null) {
//                        responseBuilder.header(AttachmentRestService.SESSION_KEY_HEADER, base64SessionKey);
//                    }
//
//                    response = responseBuilder.build();
//
//                } else {
//                    logger.warn("Response is null for document wrapper ID [" + attachment.getWrapperId() + "]");
//                    responseStatus = Response.Status.BAD_REQUEST;
//                }
//            } else {
//                logger.warn("User not authorized to access message data");
//                responseStatus = Response.Status.UNAUTHORIZED;
//            }
//        } else {
//            logger.warn("Attachment not found for document wrapper id: " + id);
//            responseStatus = Response.Status.BAD_REQUEST;
//        }
//
//        return response != null ? response : Response.status(responseStatus).build();
//    }
//
//    @Override
//    public Long upload(long localPartyId,
//                       String referenceId,
//                       String name,
//                       long size,
//                       String mimeType,
//                       String attachmentType,
//                       byte[] contentChecksum,
//                       String contentChecksumMethod,
//                       byte[] transmissionChecksum,
//                       String transmissionChecksumMethod,
//                       byte[] encryptedKey,
//                       String encryptionCertificateX509SubjectName,
//                       InputStream inputStream) throws Exception {
//
//        logger.info("Upload started: fileReferenceId = " + referenceId);
//
//        Path webserviceFolder = workspaceService.getWebserviceFolder();
//        Path fileReference = webserviceFolder.resolve(referenceId);
//        logger.info("Saving file to workspace location: " + fileReference);
//
//        Attachment attachment;
//        try {
//            Files.copy(inputStream, fileReference);
//
//            inputStream.close();
//
//            attachmentHandler.uploadAttachmentToNode(localPartyId, referenceId, size, attachmentType,
//                    encryptedKey, encryptionCertificateX509SubjectName, new FileDataSource(fileReference.toFile()));
//
//            attachment = attachmentHandler.saveAttachmentInfo(referenceId, name, "", mimeType, size, attachmentType, transmissionChecksum, transmissionChecksumMethod);
//
//        } finally {
//            Files.deleteIfExists(fileReference);
//        }
//
//        logger.info("Completed uploading fileReferenceId = " + referenceId + " with attachmentId: " + attachment.getId());
//
//        return attachment.getId();
//    }

    private boolean isAuthorized(Message message) {
        return message != null
                && message.getActiveState()
                && securityChecker.canAccessMessagesOfParty(message.getLocalParty());
    }
}
