package eu.europa.ec.etrustex.webaccess.webservice.document;

import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.vo.SignatureVO;
import eu.europa.ec.etrustex.webaccess.webservice.model.NodeAttachment;

import javax.activation.DataSource;
import java.util.List;

public interface DocumentService {

    NodeAttachment downloadFileStream(String nodeUserName, String nodePassword,
                                      String fileReferenceId,
                                      String localPartyNodeName,
                                      String sender,
                                      String attachmentType);

    void uploadFileStream(String nodeUserName, String nodePassword,
                          String localPartyNodeName,
                          String fileReferenceId,
                          long fileSize,
                          AttachmentType attachmentType,
                          byte[] encryptedKey,
                          String encryptionCertificateX509SubjectName,
                          DataSource dataSource) throws Exception;

    String sendMessageBundle(String nodeUserName, String nodePassword,
                             String localPartyNodeName,
                             String receiverParty,
                             String messageSubject,
                             String messageText,
                             List<Attachment> attachments,
                             SignatureVO signature,
                             String bundleId) throws Exception;

}
