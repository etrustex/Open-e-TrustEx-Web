package eu.europa.ec.etrustex.webaccess.web.controller.crypto;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.workspace.WorkspaceServiceImpl;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.utils.HashingInputStream;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileDataSource;
import javax.json.Json;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class UploadFilesController {
    private static final Logger logger = Logger.getLogger(UploadFilesController.class);

    private final SecurityChecker securityChecker;
    private final WorkspaceServiceImpl workspaceService;
    private final AttachmentHandler attachmentHandler;
    private final UserSessionContext userSessionContext;
    private final IcaManager icaManager;
    private final PartyManager partyManager;


    public UploadFilesController(SecurityChecker securityChecker, WorkspaceServiceImpl workspaceService, AttachmentHandler attachmentHandler, UserSessionContext userSessionContext, IcaManager icaManager, PartyManager partyManager) {
        this.securityChecker = securityChecker;
        this.workspaceService = workspaceService;
        this.attachmentHandler = attachmentHandler;
        this.userSessionContext = userSessionContext;
        this.icaManager = icaManager;
        this.partyManager = partyManager;
    }

    @RequestMapping(value = "/getCertificate", method = RequestMethod.GET)
    public String getCertificate(@RequestParam("senderParty") String senderParty, @RequestParam ("receiverParty") String receiverParty) {
        //return icaManager.getIcasByParty(partyManager.getPartyById(senderPartyId)).get(0).getCertificate();
        return icaManager.getActiveIcaBySenderReceiver(senderParty, receiverParty).getCertificate();
    }

    @RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String uploadFile(@RequestParam(value = "localPartyId") long localPartyId,
                                     @RequestParam(value = "referenceId") String referenceId,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "path") String path,
                                     @RequestParam(value = "size") long size,
                                     @RequestParam(value = "mimeType") String mimeType,
                                     @RequestParam(value = "attachmentType") String attachmentType,
//                                     @RequestParam(value = "contentChecksum", required = false) String contentChecksumHex, // should not be required when not using encryption
//                                     @RequestParam(value = "contentChecksumMethod", required = false) String contentChecksumMethod, // should not be required when not using encryption
//                                     @RequestParam(value = "transmissionChecksum") String transmissionChecksumHex,
                                     @RequestParam(value = "transmissionChecksumMethod") String transmissionChecksumMethod,
                                     @RequestParam(value = "encryptedKey", required = false) String encryptedKeyBase64,
                                     @RequestParam(value = "encryptionCertificateX509SubjectName", required = false) String encryptionCertificateX509SubjectName,
                                     @RequestParam(value = "file") MultipartFile file, HttpServletResponse response) throws Exception {
        logger.info("Upload started: fileReferenceId = " + referenceId);

        byte[] encryptedKey = null;
        if(encryptedKeyBase64 != null && !"".equals(encryptedKeyBase64)) {
            encryptedKey = DatatypeConverter.parseBase64Binary(encryptedKeyBase64);
        }

        byte [] transmissionChecksum = null; //= Hex.decode(transmissionChecksumHex);

        Path webserviceFolder = workspaceService.getWebserviceFolder();
        Path fileReference = webserviceFolder.resolve(referenceId);
        logger.info("Saving file to workspace location: " + fileReference);

        Long attachmentId = null;
        String res = null;

        try {
            InputStream inputStream = file.getInputStream();
            HashingInputStream his = new HashingInputStream(inputStream, transmissionChecksumMethod);
            Files.copy(his, fileReference);

            inputStream.close();

            transmissionChecksum = his.getDigest();

            attachmentHandler.uploadAttachmentToNode(localPartyId, referenceId, size, attachmentType, encryptedKey,
                    encryptionCertificateX509SubjectName, new FileDataSource(fileReference.toFile()));

            Attachment attachment = attachmentHandler.saveAttachmentInfo(referenceId, name, path, mimeType, size, attachmentType, transmissionChecksum, transmissionChecksumMethod);

            res = Json.createObjectBuilder()
                    .add("attachmentId", attachment.getId())
                    .add("transmissionChecksum", DatatypeConverter.printHexBinary(transmissionChecksum).toUpperCase())
                    .build().toString();

        } catch (Exception ex) {
            response.setHeader("ErrorMsg", "Error sending attachment to eTrustEx Node" + ex.getMessage() != null ? ": " + ex.getMessage() : "");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.error(ex.getMessage(), ex);
        } finally {
            Files.deleteIfExists(fileReference);
        }

        logger.info("Completed uploading fileReferenceId = " + referenceId + " with attachmentId: " + attachmentId);

        return res;
    }
}
