package eu.europa.ec.etrustex.functionaltest;

import ec.schema.xsd.ack_2.AcknowledgmentType;
import ec.schema.xsd.commonaggregatecomponents_2.*;
import ec.schema.xsd.documentbundle_1.DocumentBundleType;
import ec.schema.xsd.documentwrapper_1.DocumentWrapperType;
import ec.schema.xsd.documentwrapperrequest_1.DocumentWrapperRequestType;
import ec.schema.xsd.retrieveinterchangeagreementsrequest_2.RetrieveInterchangeAgreementsRequestType;
import ec.services.wsdl.documentbundle_2.DocumentBundlePortType;
import ec.services.wsdl.documentbundle_2.SubmitDocumentBundleRequest;
import ec.services.wsdl.documentbundle_2.SubmitDocumentBundleResponse;
import ec.services.wsdl.documentwrapper_2.DocumentWrapperPortType;
import ec.services.wsdl.documentwrapper_2.RetrieveDocumentWrapperRequestRequest;
import ec.services.wsdl.documentwrapper_2.RetrieveDocumentWrapperRequestResponse;
import ec.services.wsdl.documentwrapper_2.StoreDocumentWrapperRequest;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.RetrieveInterchangeAgreementsRequestPortType;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.SubmitRetrieveInterchangeAgreementsRequestRequest;
import ec.services.wsdl.retrieveinterchangeagreementsrequest_2.SubmitRetrieveInterchangeAgreementsRequestResponse;
import eu.europa.ec.etrustex.webaccess.model.ConfidentialityCode;
import eu.europa.ec.etrustex.webaccess.model.IntegrityCode;
import eu.europa.ec.etrustex.webaccess.model.vo.ExtendedCertificateData;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.security.EtxSecurityProvider;
import eu.europa.ec.etrustex.webaccess.security.KeyStoreEntry;
import eu.europa.ec.etrustex.webaccess.security.SignatureService;
import eu.europa.ec.etrustex.webaccess.utils.XMLHelper;
import eu.europa.ec.etrustex.webaccess.webservice.NodeObjectBuilder;
import eu.europa.ec.etrustex.webaccess.webservice.NodeWebServiceProvider;
import eu.europa.ec.etrustex.webaccess.webservice.converter.NodeExtendedCertificateDataConverter;
import eu.europa.ec.etrustex.webaccess.webservice.model.CertificateCode;
import eu.europa.ec.etrustex.webaccess.webservice.model.ConnectionConfiguration;
import eu.europa.ec.etrustex.webaccess.webservice.model.RawNodeIcaDetails;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.ExtensionContentType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionsType;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.bouncycastle.operator.OperatorCreationException;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.Partner;
import org.unece.cefact.namespaces.standardbusinessdocumentheader.PartnerIdentification;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class NodeFunctionalTest {

    public static final String SERVER_URL = "https://webgate.acceptance.ec.europa.eu";
    //    public static final String SERVER_URL = "https://webgate.test.ec.europa.eu";
    //        public static final String SERVER_URL = "nothing";
    public static final String DOCUMENT_WRAPPER_SERVICE_URL = SERVER_URL + "/e-trustexnode/DocumentWrapper-2.0?wsdl";
    public static final String DOCUMENT_BUNDLE_SERVICE_URL = SERVER_URL + "/e-trustexnode/DocumentBundle-2.0?wsdl";
    public static final String INTERCHANGE_AGREEMENT_URL = SERVER_URL + "/e-trustexnode/RetrieveInterchangeAgreementsRequest-2.0?wsdl";

    private static NodeWebServiceProvider nodeWebServiceProvider;

    static {
        EtxSecurityProvider.init();
    }

    public static void main(String[] args) throws Exception {

        nodeWebServiceProvider = new NodeWebServiceProvider();
        nodeWebServiceProvider.setConnectionConfiguration(buildTransmissionConfiguration());

        testIntegrity();
        //        validateSignature();

        /*buildTransmissionConfiguration("e-TrustExWeb", "e-TrustExWeb");
        IcaDetailsVO icaDetailsVO = retrieveInterchangeAgreements("DEV-ETX-TOLIS-PARTY", "DEV-ETX-EDMA-APP-PARTY");
        NodeWebServiceProvider.setIcaDetailsVO(icaDetailsVO);

        //send a file to ARRANDA-PARTY as EDMA
        buildTransmissionConfiguration("EDMA", "EDMA");
        String fileLocation = NodeFunctionalTest.class.getClass().getResource("/log4j.properties").getFile();
        File file = new File(fileLocation);
        String fileReferenceId = uploadFile("EDMA-APP-PARTY", "EDMA-APP-PARTY", fileLocation);

        //submit the document bundle as EDMA
        submitDocumentBundle("EDMA-APP-PARTY", "EDMA-APP-PARTY", "ARRANDA-PARTY", fileReferenceId);

        //try to receive it as ARRANDA-PARTY
        String path2Save = System.getProperty("java.io.tmpdir");
        buildTransmissionConfiguration("e-TrustExWeb", "e-TrustExWeb");
        downloadFile("ARRANDA-PARTY", "EDMA-APP-PARTY", fileReferenceId, path2Save + FileSystems.getDefault().getSeparator() + file.getName());*/
    }

    public static void testIntegrity() throws Exception {
        //        buildTransmissionConfiguration("LOCAL-MICLEVA-ENCR", "micleva");
        //        IcaDetailsVO icaDetailsVO = retrieveInterchangeAgreements("LOCAL-MICLEVA-ENCR-PARTY", "LOCAL-MICLEVA-EDMA-APP-PARTY");


        DocumentBundlePortType documentBundlePortType = nodeWebServiceProvider.getDocumentBundleServicePort();
        nodeWebServiceProvider.setupConnectionCredentials(documentBundlePortType, "e-TrustExWeb", "e-TrustExWeb");

        enableLogging((BindingProvider) documentBundlePortType);

        DocumentWrapperPortType documentWrapperPortType = nodeWebServiceProvider.getDocumentWrapperServicePort();
        nodeWebServiceProvider.setupConnectionCredentials(documentWrapperPortType, "e-TrustExWeb", "e-TrustExWeb");
        enableLogging((BindingProvider) documentWrapperPortType);

        RetrieveInterchangeAgreementsRequestPortType interchangeAgreementPort = nodeWebServiceProvider.getIcaPortFactory().create();
        nodeWebServiceProvider.setupConnectionCredentials(interchangeAgreementPort, "e-TrustExWeb", "e-TrustExWeb");
        enableLogging((BindingProvider) interchangeAgreementPort);
        NodeIcaDetails nodeIcaDetails = retrieveInterchangeAgreements(interchangeAgreementPort, "TEST1-EGREFFE-WEB-PARTY", "EGREFFE-APP-PARTY");

        System.out.println("icaDetailsVO = " + nodeIcaDetails);

        //        NodeWebServiceProvider.setIcaDetailsVO(icaDetailsVO);

        //send a file to ARRANDA-PARTY as EDMA
        //        String fileLocation = NodeFunctionalTest.class.getClass().getResource("/log4j.properties").getFile();
        //        String fileReferenceId = uploadFile("LOCAL-MICLEVA-ENCR-PARTY", "LOCAL-MICLEVA-EDMA-APP-PARTY", fileLocation);

        //submit the document bundle as EDMA
        //        submitDocumentBundle("LOCAL-MICLEVA-ENCR-PARTY", "LOCAL-MICLEVA-ENCR-PARTY", "LOCAL-MICLEVA-EDMA-APP-PARTY", fileReferenceId);
    }

    private static NodeIcaDetails retrieveInterchangeAgreements(RetrieveInterchangeAgreementsRequestPortType port, String sender, String receiver) throws Exception {

        Holder<HeaderType> holder = NodeObjectBuilder.buildHeader(sender, null);

        SubmitRetrieveInterchangeAgreementsRequestRequest request = new SubmitRetrieveInterchangeAgreementsRequestRequest();
        RetrieveInterchangeAgreementsRequestType requestType = new RetrieveInterchangeAgreementsRequestType();


        requestType.setReceiverParty(getPartyType(receiver));
        requestType.setSenderParty(getPartyType(sender));

        request.setRetrieveInterchangeAgreementsRequest(requestType);

        SubmitRetrieveInterchangeAgreementsRequestResponse response = port.submitRetrieveInterchangeAgreementsRequest(request, holder);

        List<InterchangeAgreementType> interchangeAgreements = response.getRetrieveInterchangeAgreementsResponse().getInterchangeAgreement();

        InterchangeAgreementType matchingInterchangeAgreement = null;
        if (interchangeAgreements != null) {
            for (InterchangeAgreementType agreement : interchangeAgreements) {
                if (agreement.getReceiverParty().getEndpointID().getValue().equalsIgnoreCase(receiver)) {
                    if (agreement.getSenderParty().getEndpointID().getValue().equalsIgnoreCase(sender)) {
                        matchingInterchangeAgreement = agreement;
                    }
                }
            }
        }
        CertificateType recEncipheringCertificate = null;
        if (matchingInterchangeAgreement != null && matchingInterchangeAgreement.getSecurityInformation() != null) {
            List<CertificateType> listOfCertificateTypes = matchingInterchangeAgreement.getSecurityInformation().getReceiverPartyCertificate();
            if (listOfCertificateTypes != null && !listOfCertificateTypes.isEmpty()) {
                for (CertificateType certificate : listOfCertificateTypes) {
                    if (certificate.getKeyUsageCode().getValue().equalsIgnoreCase(CertificateCode.KEY_ENCIPHERMENT.name())) {
                        recEncipheringCertificate = certificate;
                    }
                }
            }
        }
        ConfidentialityCode confidentialityCode = null;
        IntegrityCode integrityCode = null;
        if (matchingInterchangeAgreement != null && matchingInterchangeAgreement.getSecurityInformation() != null) {
            confidentialityCode = ConfidentialityCode.forCode(matchingInterchangeAgreement.getSecurityInformation().getConfidentialityLevelCode().getValue());
            integrityCode = IntegrityCode.forCode(matchingInterchangeAgreement.getSecurityInformation().getIntegrityLevelCode().getValue());
        }

        System.out.println("recEncipheringCertificate = " + recEncipheringCertificate);
        System.out.println("confidentialityCode = " + confidentialityCode);
        NodeIcaDetails nodeIcaDetails = null;

        if (confidentialityCode != null && confidentialityCode.isEncryptionRequired()) {
            byte[] x509certificate = recEncipheringCertificate.getX509Certificate().getValue();
            String x509SubjectName = recEncipheringCertificate.getX509SubjectName().getValue();
            RawNodeIcaDetails rawNodeIcaDetails = new RawNodeIcaDetails(x509certificate, x509SubjectName, confidentialityCode, integrityCode);
            ExtendedCertificateData ecd = NodeExtendedCertificateDataConverter.toExtendedCertificateData(x509certificate, x509SubjectName);
            nodeIcaDetails = new NodeIcaDetails(new Date(), sender, receiver, rawNodeIcaDetails.getConfidentialityCode(),
                    rawNodeIcaDetails.getIntegrityCode(), ecd, true);
        }

        return nodeIcaDetails;
    }

    private static void submitDocumentBundle(DocumentBundlePortType port, String wsCustomerSender, String documentSender, String receiver, String fileRefId) throws Exception {
        DocumentBundleType documentBundleType = new DocumentBundleType();

        IDType id = new IDType();
        id.setValue("ETX-MSG-BUNDLE-" + System.currentTimeMillis() + "_" + System.nanoTime());
        documentBundleType.setID(id);

        GregorianCalendar gc = new GregorianCalendar();
        DatatypeFactory dtf = null;
        dtf = DatatypeFactory.newInstance();

        IssueDateType issueDate = new IssueDateType();
        issueDate.setValue(dtf.newXMLGregorianCalendar(gc));
        documentBundleType.setIssueDate(issueDate);

        IssueTimeType issueTime = new IssueTimeType();
        issueTime.setValue(dtf.newXMLGregorianCalendar(gc));
        documentBundleType.setIssueTime(issueTime);

        //Set subject
        ProfileIDType profileId = new ProfileIDType();
        profileId.setValue("SUBJECT_HC");
        documentBundleType.setProfileID(profileId);

        //Set content
        NoteType note = new NoteType();
        note.setValue("CONTENT_HC");
        documentBundleType.getNote().add(note);

        documentBundleType.setSenderParty(getPartyType(documentSender));
        documentBundleType.getReceiverParty().add(getPartyType(receiver));

        DocumentWrapperReferenceType nodeAttachment = new DocumentWrapperReferenceType();

        IDType idType = new IDType();
        idType.setValue(fileRefId);
        nodeAttachment.setID(idType);

        DocumentTypeCodeType documentTypeCode = new DocumentTypeCodeType();
        documentTypeCode.setValue("BINARY");
        nodeAttachment.setDocumentTypeCode(documentTypeCode);

        List<DocumentWrapperReferenceType> docReferenceList = documentBundleType.getDocumentWrapperReference();
        docReferenceList.add(nodeAttachment);

        // Node's wsdl v2.0 change - We have to set the hash method and hash
        // algorithm in the document bundle
        ResourceInformationReferenceType resourceInformationReferenceType = new ResourceInformationReferenceType();

        DocumentHashType documentHashType = new DocumentHashType();
        documentHashType.setValue(DatatypeConverter.printHexBinary(new byte[]{123}));
        resourceInformationReferenceType.setDocumentHash(documentHashType);

        DocumentSizeType documentSizeType = new DocumentSizeType();
        documentSizeType.setValue(new BigDecimal("1805"));
        resourceInformationReferenceType.setDocumentSize(documentSizeType);

        DigestMethodType digestMethodType = new DigestMethodType();
        digestMethodType.setValue("MD5");
        resourceInformationReferenceType.setDocumentHashMethod(digestMethodType);

        NameType nameType = new NameType();
        nameType.setValue("radios.m3u");
        resourceInformationReferenceType.setName(nameType);

        nodeAttachment.setResourceInformationReference(resourceInformationReferenceType);

        Holder<HeaderType> holder = NodeObjectBuilder.buildHeaderWithStatusScope(wsCustomerSender, receiver);

        SubmitDocumentBundleRequest messageRequest = new SubmitDocumentBundleRequest();
        messageRequest.setDocumentBundle(documentBundleType);

        String signedData = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><signedBundle xmlns=\"urn:eu:europa:ec:etrustex:signature:v1.0\"><document><id>23450</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456780</digestValue></document><document><id>23451</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456781</digestValue></document><document><id>23452</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456782</digestValue></document><document><id>23453</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456783</digestValue></document><document><id>23454</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456784</digestValue></document><document><id>23455</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456785</digestValue></document><document><id>23456</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456786</digestValue></document><document><id>23457</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456787</digestValue></document><document><id>23458</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456788</digestValue></document><document><id>23459</id><digestMethod>SHA-512</digestMethod><digestValue>34567897643456789</digestValue></document></signedBundle>";

        UBLExtensionsType ublExtensionsType = new UBLExtensionsType();
        ublExtensionsType.getUBLExtension().add(buildExtension("signedData", signedData));

        documentBundleType.setUBLExtensions(ublExtensionsType);

        ublExtensionsType = new UBLExtensionsType();
        String signature = buildSignature(signedData);
        ublExtensionsType.getUBLExtension().add(buildExtension("signature", signature));
        documentBundleType.setUBLExtensions(ublExtensionsType);

        SubmitDocumentBundleResponse response = port.submitDocumentBundle(messageRequest, holder);
        AcknowledgmentType ack = response.getAck();

        System.out.println("ack.getAckIndicator().isValue() = " + ack.getAckIndicator().isValue());
    }

    private static String buildSignature(String dataToSign) throws Exception {

        CertificateHelperTest certificateHelperTest = new CertificateHelperTest("etx_web_test.jks", "etx_web", "etxweb", "etx_web_key");

        KeyStoreEntry<PrivateKey> signatureKey = new KeyStoreEntry<>();
        signatureKey.setKeyEntry(certificateHelperTest.getPrivateKey());
        X509Certificate cert = certificateHelperTest.getX509Certificate();
        signatureKey.setX509Certificate(cert);
        signatureKey.setAlias(certificateHelperTest.getX509Certificate().getIssuerX500Principal().getName());

        SignatureService signatureService = EtxSecurityProvider.getInstance().getSignatureService();
        Element signature = signatureService.signData(dataToSign, signatureKey.getKeyEntry(), signatureKey.getX509Certificate());

        return XMLHelper.nodeElementToXmlString(signature);
    }

    private static ConnectionConfiguration buildTransmissionConfiguration() throws IOException, NoSuchProviderException, NoSuchAlgorithmException, OperatorCreationException, CertificateException {
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration();
        connectionConfiguration.setDocumentWrapperServiceUrl(DOCUMENT_WRAPPER_SERVICE_URL);
        connectionConfiguration.setDocumentBundleServiceUrl(DOCUMENT_BUNDLE_SERVICE_URL);
        connectionConfiguration.setInterchangeAgreementUrl(INTERCHANGE_AGREEMENT_URL);

        return connectionConfiguration;
    }

    private static void enableLogging(BindingProvider bindingProvider) {
        org.apache.cxf.endpoint.Client interchangeAgreementClient = ClientProxy.getClient(bindingProvider);
        interchangeAgreementClient.getInInterceptors().add(new LoggingInInterceptor());
        interchangeAgreementClient.getOutInterceptors().add(new LoggingOutInterceptor());
    }

    public static String uploadFile(DocumentWrapperPortType port, String wsCustomerSender, String documentSender, String filePath) throws Exception {

        Holder<HeaderType> holder = NodeObjectBuilder.buildHeader(wsCustomerSender, null);

        StoreDocumentWrapperRequest storeDocumentWrapperRequest = new StoreDocumentWrapperRequest();
        DocumentWrapperType type = new DocumentWrapperType();
        Base64BinaryType base64BinaryType = new Base64BinaryType();
        ResourceInformationReferenceType resourceInformationReferenceType = new ResourceInformationReferenceType();
        DocumentTypeCodeType documentTypeCodeType = new DocumentTypeCodeType();

        DataHandler fileDataHandler = new DataHandler(new FileDataSource(filePath));

        documentTypeCodeType.setValue("BINARY");
        type.setDocumentTypeCode(documentTypeCodeType);

        base64BinaryType.setValue(fileDataHandler);
        base64BinaryType.setMimeCode(getMimeType(fileDataHandler));

        //        DocumentSizeType documentSizeType = new DocumentSizeType();
        //        documentSizeType.setValue(new BigDecimal(file.length()));
        //        resourceInformationReferenceType.setDocumentSize(documentSizeType);


        LargeAttachmentType largeAttachmentType = new LargeAttachmentType();
        largeAttachmentType.setStreamBase64Binary(base64BinaryType);
        resourceInformationReferenceType.setLargeAttachment(largeAttachmentType);

        IssueDateType issueDateType = new IssueDateType();
        issueDateType.setValue(getIssueDate());
        type.setIssueDate(issueDateType);

        //        IssueTimeType issueTimeType = new IssueTimeType();
        //        issueTimeType.setValue(getIssueTime());
        //        resourceInformationReferenceType.setIssueTime(issueTimeType);

        type.setResourceInformationReference(resourceInformationReferenceType);

        DocumentTypeType docType = new DocumentTypeType();
        docType.setLanguageID("en");
        docType.setValue("English");

        IDType documentid = new IDType();
        String finalReferenceID = "_micleva_" + System.currentTimeMillis() + "_" + System.nanoTime();
        documentid.setValue(finalReferenceID);
        type.setID(documentid);

        type.setSenderParty(getPartyType(documentSender));
        storeDocumentWrapperRequest.setDocumentWrapper(type);

        try {
            port.storeDocumentWrapper(storeDocumentWrapperRequest, holder);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getCause());
            System.err.println(e.getMessage());
            throw new Exception("The file: " + filePath + " failed to be uploaded", e);
        }

        return finalReferenceID;
    }

    private static void downloadFile(DocumentWrapperPortType port, String wsCustomerSender, String documentSender, String fileRefId, String fileToSavePath) throws Exception {
        Holder<HeaderType> holder = new Holder<>();
        holder.value = new HeaderType();
        BusinessHeaderType businessHeaderType = new BusinessHeaderType();
        //The sender here is actually the party that makes the request to the DocumentWrapperService
        businessHeaderType.getSender().add(buildPartnerType(wsCustomerSender));
        holder.value.setBusinessHeader(businessHeaderType);

        RetrieveDocumentWrapperRequestRequest retrieveDocumentWrapperRequestRequest = new RetrieveDocumentWrapperRequestRequest();
        DocumentWrapperRequestType docWrapper = new DocumentWrapperRequestType();

        DocumentReferenceRequestType docReference = new DocumentReferenceRequestType();

        IDType idType = new IDType();
        DocumentTypeCodeType docTypeCode = new DocumentTypeCodeType();

        idType.setValue(fileRefId);
        docTypeCode.setValue("BINARY");

        docReference.setID(idType);
        docReference.setDocumentTypeCode(docTypeCode);

        docWrapper.setDocumentReferenceRequest(docReference);

        docWrapper.setSenderParty(getPartyType(documentSender));
        retrieveDocumentWrapperRequestRequest.setDocumentWrapperRequest(docWrapper);

        RetrieveDocumentWrapperRequestResponse response = null;
        try {
            response = port.retrieveDocumentWrapperRequest(retrieveDocumentWrapperRequestRequest, holder);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getCause());
            System.err.println(e.getMessage());

        }
        if (response != null) {
            DocumentWrapperType documentWrapperType = response.getDocumentWrapper();
            if (documentWrapperType != null &&
                    documentWrapperType.getResourceInformationReference() != null &&
                    documentWrapperType.getResourceInformationReference().getLargeAttachment() != null) {
                LargeAttachmentType largeAttachmentType = documentWrapperType.getResourceInformationReference().getLargeAttachment();
                Base64BinaryType base64BinaryType = largeAttachmentType.getStreamBase64Binary();

                Path path = Paths.get(fileToSavePath);
                Files.deleteIfExists(path);
                Files.createFile(path);

                try (OutputStream fos = Files.newOutputStream(path)){
                    DataHandler progressDataHandler = new DataHandler(base64BinaryType.getValue().getDataSource());
                    progressDataHandler.writeTo(fos);
                }
            }
        }

        if (Files.notExists(Paths.get(fileToSavePath))) {
            throw new IllegalStateException("The file was not downloaded successfully");
        }
    }

    private static Partner buildPartnerType(String partnerName) {
        PartnerIdentification partnerIdentification = new PartnerIdentification();
        partnerIdentification.setValue(partnerName);

        Partner partner = new Partner();
        partner.setIdentifier(partnerIdentification);
        return partner;
    }

    private static PartyType getPartyType(String partyName) {
        PartyType party = new PartyType();
        party.setEndpointID(new EndpointIDType());
        party.getEndpointID().setValue(partyName);
        return party;
    }

    private static String getMimeType(DataHandler dataHandler) {
        String result = null;
        if (dataHandler != null) {
            String contentType = dataHandler.getContentType();
            try {
                MimeType mimeType = new MimeType(contentType);
                result = mimeType.getBaseType();
            } catch (MimeTypeParseException e) {
                result = contentType;
            }
        }
        return result;
    }

    private static XMLGregorianCalendar getIssueDate() {
        XMLGregorianCalendar xgcDate = null;

        try {
            Date date = new Date();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(date.getTime());
            xgcDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            xgcDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            xgcDate.setTime(DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
        } catch (Exception e) {
            return null;
        }

        return xgcDate;
    }

    private static UBLExtensionType buildExtension(String id, String contentValue) throws ParserConfigurationException {

        UBLExtensionType extensionType = new UBLExtensionType();
        IDType idType = new IDType();
        idType.setValue(id);
        extensionType.setID(idType);

        Document document = XMLHelper.getEmptyDocument();
        Element rootElement = document.createElement("valueElement");
        rootElement.appendChild(document.createTextNode(contentValue));

        ExtensionContentType extensionContentType = new ExtensionContentType();
        extensionContentType.setAny(rootElement);
        extensionType.setExtensionContent(extensionContentType);

        return extensionType;
    }
}
