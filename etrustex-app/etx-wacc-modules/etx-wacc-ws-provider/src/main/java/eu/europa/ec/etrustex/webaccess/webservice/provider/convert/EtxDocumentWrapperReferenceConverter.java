package eu.europa.ec.etrustex.webaccess.webservice.provider.convert;

import ec.schema.xsd.commonaggregatecomponents_2.DocumentWrapperReferenceType;
import ec.schema.xsd.commonaggregatecomponents_2.ResourceInformationReferenceType;
import eu.europa.ec.etrustex.signature.v1.DocumentType;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;

import javax.xml.bind.DatatypeConverter;

public class EtxDocumentWrapperReferenceConverter {

    public static Attachment toAttachment(DocumentWrapperReferenceType wrapperReferenceType, DocumentType contentChecksum) {
        ResourceInformationReferenceType reference = wrapperReferenceType.getResourceInformationReference();

        Attachment attachment = new Attachment();

        IDType id = wrapperReferenceType.getID();
        attachment.setWrapperId(id.getValue());

        DocumentTypeCodeType documentTypeCodeType = wrapperReferenceType.getDocumentTypeCode();
        AttachmentType attachmentType = AttachmentType.valueOf(documentTypeCodeType.getValue());
        attachment.setType(attachmentType);

        attachment.setTransmissionChecksum(DatatypeConverter.parseHexBinary(reference.getDocumentHash().getValue()));
        attachment.setTransmissionChecksumMethod(reference.getDocumentHashMethod().getValue());

        if (contentChecksum != null) {
            attachment.setContentChecksum(DatatypeConverter.parseHexBinary(contentChecksum.getDigestValue()));
            attachment.setContentChecksumMethod(contentChecksum.getDigestMethod());
        }

        attachment.setFileName(reference.getName().getValue());
        attachment.setFileSize(reference.getDocumentSize().getValue().longValue());

        if (reference.getLargeAttachment() != null && reference.getLargeAttachment().getStreamBase64Binary() != null
            && reference.getLargeAttachment().getStreamBase64Binary().getMimeCode() != null) {
            attachment.setMimeType(reference.getLargeAttachment().getStreamBase64Binary().getMimeCode());
        }
        return attachment;
    }
}
