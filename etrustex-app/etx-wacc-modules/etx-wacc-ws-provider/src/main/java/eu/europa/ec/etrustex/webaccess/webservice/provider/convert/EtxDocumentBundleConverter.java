package eu.europa.ec.etrustex.webaccess.webservice.provider.convert;

import ec.schema.xsd.commonaggregatecomponents_2.DocumentWrapperReferenceType;
import ec.schema.xsd.documentbundle_1.DocumentBundleType;
import eu.europa.ec.etrustex.signature.v1.DocumentType;
import eu.europa.ec.etrustex.signature.v1.SignedBundle;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.persistence.AttachmentDAO;
import eu.europa.ec.etrustex.webaccess.security.MessageSignatureUtil;
import eu.europa.ec.etrustex.webaccess.utils.XMLHelper;
import oasis.names.specification.ubl.schema.xsd.commonextensioncomponents_2.UBLExtensionType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.persistence.NoResultException;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

@Component
public class EtxDocumentBundleConverter {
    private static final Logger logger = Logger.getLogger(EtxDocumentBundleConverter.class);

    private final static String SIGNATURE_EXTENSION_ID = "signature";
    private final static String SIGNED_DATE_EXTENSION_ID = "signedData";
    private final static String EXTENSION_CONTENT_VALUE_ELEMENT = "valueElement";

    private AttachmentDAO attachmentDAO;

    public EtxDocumentBundleConverter(AttachmentDAO attachmentDAO) {
        this.attachmentDAO = attachmentDAO;
    }

    public Message convertMessage(DocumentBundleType documentBundle) throws DatatypeConfigurationException {
        Message message = new Message();

        if (documentBundle.getID() != null) {
            message.setBundleId(documentBundle.getID().getValue());
        }

        if (documentBundle.getUUID() != null) {
            message.setReferenceId(documentBundle.getUUID().getValue());
        }
        if (documentBundle.getIssueDate() != null) {
            Calendar issueDateTime;
            if (documentBundle.getIssueTime() != null) {
                issueDateTime = getGregorianCalendarDateTime(documentBundle.getIssueDate().getValue(),
                    documentBundle.getIssueTime().getValue());
            } else {
                issueDateTime = getGregorianCalendarDateTime(documentBundle.getIssueDate().getValue(), null);
            }

            message.setIssueDate(issueDateTime.getTime());
        }

        if (documentBundle.getProfileID() != null) {
            message.setSubject(documentBundle.getProfileID().getValue());
        }

        if (documentBundle.getNote() != null && !documentBundle.getNote().isEmpty()) {
            message.setContent(documentBundle.getNote().get(0).getValue());
        }

        String signature = null;
        Element signatureValueElement = getExtensionContentById(documentBundle, SIGNATURE_EXTENSION_ID);
        if (signatureValueElement != null && signatureValueElement.getTagName().equals(EXTENSION_CONTENT_VALUE_ELEMENT)) {
            NodeList signatureNodeList = signatureValueElement.getChildNodes();
            if (signatureNodeList != null && signatureNodeList.getLength() == 1) {
                signature = signatureNodeList.item(0).getTextContent();
            }
        }

        String signedData = null;
        Element signedDataValueElement = getExtensionContentById(documentBundle, SIGNED_DATE_EXTENSION_ID);
        if (signedDataValueElement != null && signedDataValueElement.getTagName().equals(EXTENSION_CONTENT_VALUE_ELEMENT)) {
            NodeList signatureDataNodeList = signedDataValueElement.getChildNodes();
            if (signatureDataNodeList != null) {
                signedData = signatureDataNodeList.item(0).getTextContent();
            }
        }

        MessageSignatureUtil.populateMessageSignature(message, signature, signedData, null);
        Map<String, DocumentType> contentChecksumMap = getContentChecksumMap(signedData);

        List<Attachment> attachments = new ArrayList<>();
        message.setAttachments(attachments);
        for (DocumentWrapperReferenceType documentWrapperReferenceType : documentBundle.getDocumentWrapperReference()) {
            String wrapperId= documentWrapperReferenceType.getID().getValue();
            Attachment etxAttachment= EtxDocumentWrapperReferenceConverter.toAttachment(documentWrapperReferenceType, contentChecksumMap.get(documentWrapperReferenceType.getID().getValue()));
            etxAttachment.setMessage(message);

            try {
                Attachment originalAttachment = attachmentDAO.findNotIncomingByBundleIdAndAndWrapperId(message.getBundleId(), wrapperId);
                etxAttachment.setFilePath(originalAttachment.getFilePath());
                logger.debug("Adding path from SENT attachment: " + originalAttachment.getFilePath());
            } catch (NoResultException e) {
                etxAttachment.setFilePath("");
                logger.debug(String.format("Attachment for path recovery not found! BundleId: %s, messageState: %s, wrapperId: %s", message.getBundleId(), Message.MessageState.SENT, wrapperId));
            }
            attachments.add(etxAttachment);
        }

        return message;
    }

    private static Map<String, DocumentType> getContentChecksumMap(String signedData) {
        Map<String, DocumentType> result = new HashMap<>();
        if (signedData != null) {
            try {
                SignedBundle signedBundle = (SignedBundle) XMLHelper.xmlToJaxbObject(signedData, SignedBundle.class);
                for(DocumentType documentType : signedBundle.getDocument()) {
                    result.put(documentType.getId(), documentType);
                }
            } catch (JAXBException e) {
                logger.warn("Invalid signed data", e);
            }
        }

        return result;
    }

    private static Element getExtensionContentById(DocumentBundleType documentBundle, String extensionId) {
        Element result = null;

        if (documentBundle.getUBLExtensions() != null) {
            for (UBLExtensionType ublExtensionType : documentBundle.getUBLExtensions().getUBLExtension()) {
                if (ublExtensionType.getID().getValue().equals(extensionId)) {
                    result = ublExtensionType.getExtensionContent().getAny();
                }
            }
        }
        return result;
    }

    public static Calendar getGregorianCalendarDateTime(XMLGregorianCalendar xgcDateTime) throws DatatypeConfigurationException {
        return xgcDateTime.toGregorianCalendar();
    }

    public static Calendar getGregorianCalendarDateTime(XMLGregorianCalendar xgcDate, XMLGregorianCalendar xgcTime) throws DatatypeConfigurationException {
        if (xgcTime == null) {
            return getGregorianCalendarDateTime(xgcDate);
        } else {
            return new GregorianCalendar(xgcDate.getYear(), xgcDate.getMonth() - 1, xgcDate.getDay(), xgcTime.getHour(), xgcTime.getMinute(), xgcTime.getSecond());
        }
    }
}
