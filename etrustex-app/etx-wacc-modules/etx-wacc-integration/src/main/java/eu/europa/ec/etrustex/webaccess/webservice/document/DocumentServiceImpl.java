package eu.europa.ec.etrustex.webaccess.webservice.document;

import ec.schema.xsd.commonaggregatecomponents_2.*;
import ec.schema.xsd.commonbasiccomponents_1.KeyUsageCodeType;
import ec.schema.xsd.documentbundle_1.DocumentBundleType;
import ec.schema.xsd.documentwrapper_1.DocumentWrapperType;
import ec.schema.xsd.documentwrapperrequest_1.DocumentWrapperRequestType;
import ec.services.wsdl.documentbundle_2.DocumentBundlePortType;
import ec.services.wsdl.documentbundle_2.SubmitDocumentBundleRequest;
import ec.services.wsdl.documentwrapper_2.DocumentWrapperPortType;
import ec.services.wsdl.documentwrapper_2.RetrieveDocumentWrapperRequestRequest;
import ec.services.wsdl.documentwrapper_2.RetrieveDocumentWrapperRequestResponse;
import ec.services.wsdl.documentwrapper_2.StoreDocumentWrapperRequest;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.vo.SignatureVO;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.webservice.MessageBundleHandler;
import eu.europa.ec.etrustex.webaccess.webservice.NodeObjectBuilder;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import eu.europa.ec.etrustex.webaccess.webservice.model.NodeAttachment;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.TextType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import un.unece.uncefact.data.specification.unqualifieddatatypesschemamodule._2.BinaryObjectType;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.ws.Holder;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final static String EXPECTED_MIME_CODE = "application/octet-stream";

    private static final Logger logger = Logger.getLogger(DocumentServiceImpl.class);

    @Autowired
    private NodeWebServiceProvider nodeWebServiceProvider;
    @Autowired
    private MessageBundleHandler messageBundleHandler;

    public NodeAttachment downloadFileStream(String nodeUserName, String nodePassword,
                                             String fileReferenceId,
                                             String localPartyNodeName,
                                             String senderPartyName,
                                             String attachmentType) {
        logger.debug("Download file stream: reference [" + fileReferenceId + "], local [" + localPartyNodeName + "], sender[" + senderPartyName + "], type[" + attachmentType + "]");

        IDType idType = new IDType();
        idType.setValue(fileReferenceId);

        DocumentTypeCodeType docTypeCode = new DocumentTypeCodeType();
        docTypeCode.setValue(attachmentType);

        DocumentReferenceRequestType docReference = new DocumentReferenceRequestType();
        docReference.setID(idType);
        docReference.setDocumentTypeCode(docTypeCode);

        DocumentWrapperRequestType docWrapper = new DocumentWrapperRequestType();
        docWrapper.setDocumentReferenceRequest(docReference);
        docWrapper.setSenderParty(NodeObjectBuilder.buildParty(senderPartyName));

        RetrieveDocumentWrapperRequestRequest retrieveDocumentWrapperRequestRequest = new RetrieveDocumentWrapperRequestRequest();
        retrieveDocumentWrapperRequestRequest.setDocumentWrapperRequest(docWrapper);

        Holder<HeaderType> holder = NodeObjectBuilder.buildHeader(localPartyNodeName, null);

        DocumentWrapperPortType port = nodeWebServiceProvider.getDocumentWrapperServicePort();
        nodeWebServiceProvider.setupConnectionCredentials(port, nodeUserName, nodePassword);

        try {
            RetrieveDocumentWrapperRequestResponse response = port.retrieveDocumentWrapperRequest(retrieveDocumentWrapperRequestRequest, holder);

            return getNodeAttachment(response);
        } catch (Exception e) {
            throw new EtxException("There was an error on fetching a valid response from Document Wrapper Service: " + e.getMessage(), e);
        }
    }

    @Override
    public void uploadFileStream(String nodeUserName, String nodePassword,
                                 String localPartyNodeName,
                                 String fileReferenceId,
                                 long fileSize,
                                 AttachmentType attachmentType,
                                 byte[] encryptedKey,
                                 String encryptionCertificateX509SubjectName,
                                 DataSource dataSource) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("Start uploading file [" + fileReferenceId + "], sender [" + localPartyNodeName + "]");

        DocumentWrapperPortType port = nodeWebServiceProvider.getDocumentWrapperServicePort();
        nodeWebServiceProvider.setupConnectionCredentials(port, nodeUserName, nodePassword);

        StoreDocumentWrapperRequest storeDocumentWrapperRequest = new StoreDocumentWrapperRequest();
        DocumentWrapperType type = new DocumentWrapperType();
        Base64BinaryType base64BinaryType = new Base64BinaryType();
        DocumentSizeType documentSizeType = new DocumentSizeType();
        ResourceInformationReferenceType resourceInformationReferenceType = new ResourceInformationReferenceType();
        DocumentTypeCodeType documentTypeCodeType = new DocumentTypeCodeType();
        LargeAttachmentType largeAttachmentType = new LargeAttachmentType();

        if (encryptedKey != null && encryptionCertificateX509SubjectName != null) {
            BinaryObjectType sessionKeyHolder = new BinaryObjectType();
            sessionKeyHolder.setValue(encryptedKey);
            sessionKeyHolder.setMimeCode("application/pgp-encrypted");

            EncryptionInformationType encryptionInformationType = new EncryptionInformationType();
            encryptionInformationType.setSessionKey(sessionKeyHolder);

            CertificateType certificateType = new CertificateType();
            /*TextType certificatesSubjectName = new TextType();
            certificatesSubjectName.setValue(encryptionCertificateX509SubjectName);

            certificateType.setX509SubjectName(certificatesSubjectName);*/
            BinaryObjectType x509Certificate = new BinaryObjectType();
            x509Certificate.setMimeCode("base64");
            certificateType.setX509Certificate(x509Certificate);
            certificateType.setKeyUsageCode(new KeyUsageCodeType());

            encryptionInformationType.setCertificate(certificateType);

            largeAttachmentType.setEncryptionInformation(encryptionInformationType);
        }

        documentTypeCodeType.setValue(attachmentType.name());
        base64BinaryType.setValue(new DataHandler(dataSource));
        base64BinaryType.setMimeCode(EXPECTED_MIME_CODE);
        largeAttachmentType.setStreamBase64Binary(base64BinaryType);

        type.setDocumentTypeCode(documentTypeCodeType);

        resourceInformationReferenceType.setLargeAttachment(largeAttachmentType);

        logger.info("Actual uploading file size [" + fileSize + "]");

        documentSizeType.setValue(new BigDecimal(fileSize));
        resourceInformationReferenceType.setDocumentSize(documentSizeType);

        type.setResourceInformationReference(resourceInformationReferenceType);

        type.setIssueDate(NodeObjectBuilder.buildIssueDate());

        DocumentTypeType docType = new DocumentTypeType();
        docType.setLanguageID("en");
        docType.setValue("English");

        IDType documentId = new IDType();
        documentId.setValue(fileReferenceId);
        type.setID(documentId);

        type.setSenderParty(NodeObjectBuilder.buildParty(localPartyNodeName));
        storeDocumentWrapperRequest.setDocumentWrapper(type);

        Holder<HeaderType> header = NodeObjectBuilder.buildHeader(localPartyNodeName, null);

        try {
            port.storeDocumentWrapper(storeDocumentWrapperRequest, header);
        } catch (Exception e) {
            throw new Exception("The file: " + fileReferenceId + " failed to be uploaded; Cause: " + e.toString(), e);
        }
        logger.info("Upload file completed [" + fileReferenceId + "] lasting: " + (System.currentTimeMillis() - startTime));
    }

    @Override
    public String sendMessageBundle(String nodeUserName, String nodePassword,
                                    String localPartyNodeName,
                                    String receiverParty,
                                    String messageSubject,
                                    String messageText,
                                    List<Attachment> attachments,
                                    SignatureVO signature,
                                    String bundleId) throws Exception {

        DocumentBundleType documentBundleType = messageBundleHandler.buildDocumentBundle(localPartyNodeName, receiverParty,
                messageSubject, messageText, attachments, signature, bundleId);
        logger.info("Start sending message bundle sender [" + localPartyNodeName + "], receiver [" + receiverParty + "], bundleID: [" + bundleId + "]");

        DocumentBundlePortType port = nodeWebServiceProvider.getDocumentBundleServicePort();
        nodeWebServiceProvider.setupConnectionCredentials(port, nodeUserName, nodePassword);

        SubmitDocumentBundleRequest messageRequest = new SubmitDocumentBundleRequest();
        messageRequest.setDocumentBundle(documentBundleType);

        Holder<HeaderType> header = NodeObjectBuilder.buildHeaderWithStatusScope(localPartyNodeName, receiverParty);

        port.submitDocumentBundle(messageRequest, header);

        logger.info("Complete sending of message bundle bundleID: [" + bundleId + "].");

        return bundleId;
    }

    private NodeAttachment getNodeAttachment(RetrieveDocumentWrapperRequestResponse response) throws IOException {
        NodeAttachment nodeAttachment = null;
        if (response != null) {
            DocumentWrapperType documentWrapperType = response.getDocumentWrapper();
            if (documentWrapperType != null &&
                    documentWrapperType.getResourceInformationReference() != null &&
                    documentWrapperType.getResourceInformationReference().getLargeAttachment() != null &&
                    documentWrapperType.getResourceInformationReference().getLargeAttachment().getStreamBase64Binary() != null) {

                final ResourceInformationReferenceType resourceRef = response.getDocumentWrapper().getResourceInformationReference();
                long documentSize = resourceRef.getDocumentSize().getValue() != null ? resourceRef.getDocumentSize().getValue().longValue() : 0L;
                nodeAttachment = new NodeAttachment(resourceRef.getLargeAttachment().getStreamBase64Binary().getValue().getInputStream(), documentSize);

                final EncryptionInformationType encryptionInformation = resourceRef.getLargeAttachment().getEncryptionInformation();
                if (encryptionInformation != null && encryptionInformation.getSessionKey() != null) {
                    nodeAttachment.setSessionKey(encryptionInformation.getSessionKey().getValue());
                }
            }
        }

        return nodeAttachment;
    }
}
