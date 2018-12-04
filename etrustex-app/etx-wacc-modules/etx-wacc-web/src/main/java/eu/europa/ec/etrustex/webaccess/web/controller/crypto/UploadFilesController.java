package eu.europa.ec.etrustex.webaccess.web.controller.crypto;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.workspace.WorkspaceServiceImpl;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileDataSource;
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
    public String getCertificate(@RequestParam("senderPartyId") Long senderPartyId) {
        return icaManager.getIcasByParty(partyManager.getPartyById(senderPartyId)).get(0).getCertificate();
    }

    @RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
    public Long uploadFile(@RequestParam(value = "localPartyId") long localPartyId,
                           @RequestParam(value = "referenceId") String referenceId,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "path") String path,
                           @RequestParam(value = "size") long size,
                           @RequestParam(value = "mimeType") String mimeType,
                           @RequestParam(value = "attachmentType") String attachmentType,
                           @RequestParam(value = "contentChecksum", required = false) String contentChecksumHex, // should not be required when not using encryption
                           @RequestParam(value = "contentChecksumMethod", required = false) String contentChecksumMethod, // should not be required when not using encryption
                           @RequestParam(value = "transmissionChecksum") String transmissionChecksumHex,
                           @RequestParam(value = "transmissionChecksumMethod") String transmissionChecksumMethod,
                           @RequestParam(value = "encryptedKey", required = false) String encryptedKeyBase64,
                           @RequestParam(value = "encryptionCertificateX509SubjectName", required = false) String encryptionCertificateX509SubjectName,
                           @RequestParam(value = "file") MultipartFile file) throws Exception {
        logger.info("Upload started: fileReferenceId = " + referenceId);

        byte[] encryptedKey = null;
        byte [] contentChecksum = null;

        //final String base64Binary = DatatypeConverter.printBase64Binary(encryptedKey);
        if(encryptedKeyBase64 != null && !"".equals(encryptedKeyBase64)) {
            encryptedKey = DatatypeConverter.parseBase64Binary(encryptedKeyBase64);
            contentChecksum = Hex.decode(contentChecksumHex);
        }

        byte [] transmissionChecksum= Hex.decode(transmissionChecksumHex);


        Path webserviceFolder = workspaceService.getWebserviceFolder();
        Path fileReference = webserviceFolder.resolve(referenceId);
        logger.info("Saving file to workspace location: " + fileReference);

        Long attachmentId;
        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, fileReference);

            inputStream.close();

            attachmentId = attachmentHandler.uploadAttachmentToNode(localPartyId, referenceId, name, path, size, mimeType, attachmentType,
                    contentChecksum, contentChecksumMethod, transmissionChecksum, transmissionChecksumMethod, encryptedKey,
                    encryptionCertificateX509SubjectName, new FileDataSource(fileReference.toFile()));

        } finally {
            Files.deleteIfExists(fileReference);
        }

        logger.info("Completed uploading fileReferenceId = " + referenceId + " with attachmentId: " + attachmentId);

        return attachmentId;
    }
}
