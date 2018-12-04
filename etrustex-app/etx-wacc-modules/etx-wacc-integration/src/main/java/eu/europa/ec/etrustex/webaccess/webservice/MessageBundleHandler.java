/**
 *
 */
package eu.europa.ec.etrustex.webaccess.webservice;

import ec.schema.xsd.commonaggregatecomponents_2.DigestMethodType;
import ec.schema.xsd.commonaggregatecomponents_2.DocumentSizeType;
import ec.schema.xsd.commonaggregatecomponents_2.DocumentWrapperReferenceType;
import ec.schema.xsd.commonaggregatecomponents_2.ResourceInformationReferenceType;
import ec.schema.xsd.documentbundle_1.DocumentBundleType;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.vo.SignatureVO;
import eu.europa.ec.etrustex.webaccess.utils.XMLHelper;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.ExtensionContentType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionType;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionsType;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class MessageBundleHandler {
    public final static String SIGNATURE_EXTENSION_ID = "signature";
    public final static String SIGNED_DATE_EXTENSION_ID = "signedData";
    public final static String EXTENSION_CONTENT_VALUE_ELEMENT = "valueElement";

    public DocumentBundleType buildDocumentBundle(String senderParty, String receiverParty, String subject, String content,
                                                  List<Attachment> attachments, SignatureVO signature, String bundleId) throws Exception {
        DocumentBundleType documentBundleType = new DocumentBundleType();

        IDType id = new IDType();
        id.setValue(bundleId);
        documentBundleType.setID(id);

        GregorianCalendar dateTime = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

        IssueDateType issueDate = new IssueDateType();
        XMLGregorianCalendar xgcDate = datatypeFactory.newXMLGregorianCalendarDate(dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH) + 1, dateTime.get(Calendar.DAY_OF_MONTH),
                DatatypeConstants.FIELD_UNDEFINED);
        issueDate.setValue(xgcDate);
        documentBundleType.setIssueDate(issueDate);

        IssueTimeType issueTime = new IssueTimeType();
        XMLGregorianCalendar xgcTime = datatypeFactory.newXMLGregorianCalendarTime(dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), dateTime.get(Calendar.SECOND), dateTime.get(Calendar.MILLISECOND),
                DatatypeConstants.FIELD_UNDEFINED);
        issueTime.setValue(xgcTime);
        documentBundleType.setIssueTime(issueTime);

        //Set subject
        ProfileIDType profileId = new ProfileIDType();
        profileId.setValue(subject);
        documentBundleType.setProfileID(profileId);

        //Set content
        NoteType note = new NoteType();
        note.setValue(content);
        documentBundleType.getNote().add(note);

        documentBundleType.setSenderParty(getPartyTypeFromName(senderParty));
        documentBundleType.getReceiverParty().add(getPartyTypeFromName(receiverParty));

        List<DocumentWrapperReferenceType> docReferenceList = documentBundleType.getDocumentWrapperReference();

        for (Attachment attachment : attachments) {
            DocumentWrapperReferenceType docReference = getNodeFormatAttachment(attachment);
            docReferenceList.add(docReference);
        }

        if (signature != null) {
            UBLExtensionsType ublExtensionsType = new UBLExtensionsType();

            //this is the data being signed
            ublExtensionsType.getUBLExtension().add(buildExtension(SIGNED_DATE_EXTENSION_ID, signature.getSignedData()));

            ublExtensionsType.getUBLExtension().add(buildExtension(SIGNATURE_EXTENSION_ID, signature.getSignature()));

            documentBundleType.setUBLExtensions(ublExtensionsType);
        }

        return documentBundleType;
    }


    private UBLExtensionType buildExtension(String id, String contentValue) throws ParserConfigurationException {

        UBLExtensionType extensionType = new UBLExtensionType();
        IDType idType = new IDType();
        idType.setValue(id);
        extensionType.setID(idType);

        Document document = XMLHelper.getEmptyDocument();
        Element rootElement = document.createElement(EXTENSION_CONTENT_VALUE_ELEMENT);
        rootElement.appendChild(document.createTextNode(contentValue));

        ExtensionContentType extensionContentType = new ExtensionContentType();
        extensionContentType.setAny(rootElement);
        extensionType.setExtensionContent(extensionContentType);

        return extensionType;
    }

    private DocumentWrapperReferenceType getNodeFormatAttachment(Attachment attachment) {
        DocumentWrapperReferenceType nodeAttachment = new DocumentWrapperReferenceType();

        IDType id = new IDType();
        id.setValue(attachment.getWrapperId());
        nodeAttachment.setID(id);

        DocumentTypeCodeType documentTypeCode = new DocumentTypeCodeType();
        documentTypeCode.setValue(attachment.getType().name());
        nodeAttachment.setDocumentTypeCode(documentTypeCode);

        // Node's wsdl v2.0 change - We have to set the hash method and hash
        // algorithm in the document bundle
        ResourceInformationReferenceType resourceInformationReferenceType = new ResourceInformationReferenceType();

        DocumentHashType documentHashType = new DocumentHashType();
        documentHashType.setValue(DatatypeConverter.printHexBinary(attachment.getTransmissionChecksum() != null ? attachment.getTransmissionChecksum() : new byte[]{}));
        resourceInformationReferenceType.setDocumentHash(documentHashType);

        DocumentSizeType documentSizeType = new DocumentSizeType();
        documentSizeType.setValue(new BigDecimal(attachment.getFileSize()));
        resourceInformationReferenceType.setDocumentSize(documentSizeType);

        DigestMethodType digestMethodType = new DigestMethodType();
        digestMethodType.setValue(attachment.getTransmissionChecksumMethod());
        resourceInformationReferenceType.setDocumentHashMethod(digestMethodType);

        NameType nameType = new NameType();
        nameType.setValue(attachment.getFileName());
        resourceInformationReferenceType.setName(nameType);

        nodeAttachment.setResourceInformationReference(resourceInformationReferenceType);

        return nodeAttachment;
    }

    private PartyType getPartyTypeFromName(String sender) {
        PartyType party = new PartyType();

        party.setEndpointID(new EndpointIDType());
        party.getEndpointID().setValue(sender);

        return party;
    }
}
