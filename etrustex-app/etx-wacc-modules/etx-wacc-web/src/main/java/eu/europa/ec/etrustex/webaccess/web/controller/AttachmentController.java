package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.webservice.model.NodeAttachment;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Controller related to the attachment's logic.
 */

@RestController
public class AttachmentController {

    private static final Logger logger = Logger.getLogger(AttachmentController.class);

    private final AttachmentHandler attachmentHandler;
    protected final SecurityChecker securityChecker;

    public AttachmentController(AttachmentHandler attachmentHandler, SecurityChecker securityChecker) {
        this.attachmentHandler = attachmentHandler;
        this.securityChecker = securityChecker;
    }

    /**
     * Download attachment.
     *
     * @param response     HttpServletResponse.
     * @param attachmentId Attachment id.
     * @throws IOException
     */
    @CrossOrigin(allowCredentials="true", methods={RequestMethod.GET})
    @RequestMapping(method = RequestMethod.GET, value = "downloadAttachment.do" /*, produces = "application/octet-stream"*/)
    public void downloadAttachment(
            HttpServletResponse response,
            @RequestParam(value = "attachmentId", required = true) String attachmentId,
            @RequestParam(value = "downloadAsSender", required =false, defaultValue = "false") boolean downloadAsSender
            ) throws IOException {
        Attachment attachment = null;

        try {
            attachment = attachmentHandler.getAttachment(Long.parseLong(attachmentId));
        } catch (NumberFormatException numberFormatException) {
            logger.warn(attachmentId + " is not a valid attachment id");
        }

        if (attachment != null) {
            Message message = attachment.getMessage();

            if (isAuthorized(message)) {
                NodeAttachment nodeAttachment = attachmentHandler.retrieveNodeAttachment(attachment, downloadAsSender);

                if (nodeAttachment != null) {
                    String fileSize = Long.toString(nodeAttachment.getDocumentSize());
                    response.setContentType(attachment.getMimeType());
                    //response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getFileName());
                    response.setHeader("filename", DatatypeConverter.printHexBinary(attachment.getFileName().getBytes("UTF-8")));

                    String filePath = attachment.getFilePath();
                    if(filePath!=null) {
                        filePath = DatatypeConverter.printHexBinary(filePath.getBytes("UTF-8"));
                    } else {
                        filePath = "";
                    }

                    response.setHeader("filePath", filePath);
                    response.setHeader("Content-Length", fileSize);
                    response.setHeader("Message-reference-id", "TEST_MSG_REF_ID");

                    // attachment metadata and cryptographic info
                    byte[] contentChecksum = attachment.getContentChecksum();
                    if(contentChecksum != null) {
                        response.setHeader("contentChecksumHex", DatatypeConverter.printHexBinary(contentChecksum));
                        response.setHeader("contentChecksumMethod", attachment.getContentChecksumMethod());
                    }

                    byte[] transmissionChecksum = attachment.getTransmissionChecksum();
                    if(transmissionChecksum != null) {
                        response.setHeader("transmissionChecksumHex", DatatypeConverter.printHexBinary(transmissionChecksum));
                        response.setHeader("transmissionChecksumMethod", attachment.getTransmissionChecksumMethod());
                    }

                    byte[] sessionKey = nodeAttachment.getSessionKey();
                    if(sessionKey != null) {
                        String sessionKeyBase64 = DatatypeConverter.printBase64Binary(sessionKey);
                        response.setHeader("encryptedRandomBitsBase64", sessionKeyBase64);
                    }

                    response.setHeader("fileSize", fileSize);

                    // the id as created at the time of the upload, needed to retrieve the content checksum from the signature
                    response.setHeader("referenceId", attachment.getWrapperId());

                    InputStream is = nodeAttachment.getInputStream();
                    IOUtils.copy(is, response.getOutputStream());


//                    File file = new File("C:/Pgm/workspaces.zip");
//                    FileInputStream fis = null;
//
//                    try {
//                        fis = new FileInputStream(file);
//
//                        System.out.println("Total file size (in bytes) : " + file.length());
//                        IOUtils.copy(fis, response.getOutputStream());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            if (fis != null)
//                                fis.close();
//
//                            response.flushBuffer();
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        }
//                    }

                } else {
                    logger.warn("Response is null for document wrapper ID [" + attachment.getWrapperId() + "]");
                }
            } else {
                logger.warn("User not authorized to access message data");
            }
        } else {
            logger.warn("Attachment not found for document wrapper id: " + attachmentId);
        }
    }

    private boolean isAuthorized(Message message) {
        return message != null
                && message.getActiveState()
                && securityChecker.canAccessMessagesOfParty(message.getLocalParty());
    }

}
