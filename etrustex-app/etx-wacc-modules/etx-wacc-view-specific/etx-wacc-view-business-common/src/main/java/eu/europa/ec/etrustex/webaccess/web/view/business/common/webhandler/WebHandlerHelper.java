package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import com.google.gson.JsonArray;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.IcaDetailsService;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.converters.CertificateDataConverter;
import eu.europa.ec.etrustex.webaccess.model.converters.FileElementVOJsonWrapper;
import eu.europa.ec.etrustex.webaccess.model.converters.ICADetailsJsonWrapper;
import eu.europa.ec.etrustex.webaccess.model.converters.util.JsonUtil;
import eu.europa.ec.etrustex.webaccess.model.vo.*;
import eu.europa.ec.etrustex.webaccess.utils.applet.Encoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WebHandlerHelper {

    private final Logger logger = Logger.getLogger(AbstractMessageHandler.class);

    @Autowired
    protected AttachmentHandler attachmentHandler;

    @Autowired
    private IcaDetailsService icaDetailsService;

    @Autowired
    private IcaManager icaManager;

    public void handlePageNavigation(Map<String, Object> model, int page, long resultCount, int pageSize) {

        long pageCount = (resultCount / pageSize + (resultCount % pageSize != 0 ? 1 : 0));
        if (page > pageCount) {
            page = 1;
        }
        // add values needed for paging to the model
        model.put("firstPage", 1);
        model.put("prevPage", page - 1);
        model.put("lastPage", (int) pageCount);
        model.put("nextPage", page + 1);
        model.put("currentPage", page);
    }

    public MessageSignatureData getMessageSignatureData(List<MessageSignature> signatures) {
        MessageSignatureData messageSignatureData = null;
        if (CollectionUtils.isNotEmpty(signatures)) {
            MessageSignature messageSignature = signatures.get(0);

            CertificateData certificateData = CertificateDataConverter.getInstance().convertToEntityAttribute(messageSignature.getCertificate());
            messageSignatureData = new MessageSignatureData(certificateData, messageSignature.isSignatureValid());
        }

        return messageSignatureData;
    }

    public ICADetails getIcaDetails(Party currentParty, String icaNodeParty) {
        ICADetails icaDetails = null;
        NodeIcaDetails nodeIcaDetails = icaDetailsService.getIcaDetails(currentParty, icaNodeParty);

        if (nodeIcaDetails != null) {
            ExtendedCertificateData ecd = nodeIcaDetails.getExtendedCertificateData();
            icaDetails = new ICADetails(nodeIcaDetails.getIntegrityCode().getIsSignatureRequired(),
                    nodeIcaDetails.getConfidentialityCode().isEncryptionRequired(),
                    ecd != null ? ecd.getSubjectDN() : null,
                    ecd != null ? ecd.getPublicKey() : null);

            if (nodeIcaDetails.getConfidentialityCode().isEncryptionRequired()) {

                icaDetails.setCertificateValidity(toCertificateValidity(nodeIcaDetails.getExtendedCertificateData()));
            }
        }

        return icaDetails;
    }

    private CertificateValidity toCertificateValidity(ExtendedCertificateData extendedCertificateData) {
        CertificateValidity validity = new CertificateValidity();
        Date currentDate = new Date();
        if (extendedCertificateData != null) {
            validity.setExpired(extendedCertificateData.getEndDate().before(currentDate));
            validity.setEndDate(extendedCertificateData.getEndDate());
            validity.setNotYetValid(extendedCertificateData.getStartDate().after(currentDate));
            validity.setStartDate(extendedCertificateData.getStartDate());
        }

        return validity;
    }

    public void addJsonParameters(Map<String, Object> model, String icaNodePartyName,
                                  List<Attachment> attachments, Party currentParty, List<AttachmentMetadata> aml, List<MessageSignature> signatures) {
        try {
            NodeIcaDetails nodeIcaDetails = icaDetailsService.getIcaDetails(currentParty, icaNodePartyName);

            ICADetails icaDetails = null;
            if (nodeIcaDetails != null) {
                ExtendedCertificateData ecd = nodeIcaDetails.getExtendedCertificateData();
                icaDetails = new ICADetails(nodeIcaDetails.getIntegrityCode().getIsSignatureRequired(),
                        nodeIcaDetails.getConfidentialityCode().isEncryptionRequired(),
                        ecd != null ? ecd.getSubjectDN() : null,
                        ecd != null ? ecd.getPublicKey() : null);

                model.put("icaDetails", Base64.encodeBase64String(JsonUtil.objectToJson(new ICADetailsJsonWrapper(icaDetails)).getBytes()));
            }

            //create a copy of the attachment object in order to avoid serialization of the message attribute of the attachment
            List<FileElementVO> fileElementsToToEncode = getFileElementsToEncode(attachments, aml);
            List<FileElementVOJsonWrapper> serializableFileElements = new ArrayList<>(fileElementsToToEncode.size());
            Set<Long>  fileElementsIds=new HashSet<>();
            Long selectedPayloadId =null;
            for(FileElementVO fileElement: fileElementsToToEncode) {
                serializableFileElements.add(new FileElementVOJsonWrapper(fileElement));
                fileElementsIds.add(fileElement.getId());
                if(fileElement.getType()==AttachmentType.METADATA){
                    selectedPayloadId=fileElement.getId();
                }
            }
            final String fileElementsAsJson = JsonUtil.arrayToJson(serializableFileElements);
            if(selectedPayloadId!=null) {
                model.put("encodedIdSelectedPayloadFile", Encoder.encode(selectedPayloadId));
            }
            else{
                model.put("encodedIdSelectedPayloadFile", "");
            }
            //model.put("encodedFileListIds", Encoder.encode(fileElementsIds));
            JsonArray jsonFileIdsArray = new JsonArray();
            for(Long id :fileElementsIds ) {
                jsonFileIdsArray.add(id);
            }
            model.put("fileIdsListJSON", jsonFileIdsArray.toString());

            model.put("encodedFileElements", Base64.encodeBase64String(fileElementsAsJson.getBytes("UTF-8")));


            if (CollectionUtils.isNotEmpty(signatures)) {
                MessageSignature messageSignature = signatures.get(0);

                SignatureVO signatureVO = new SignatureVO(messageSignature.getSignature(), messageSignature.getSignedData(), messageSignature.getId());
                //model.put("encodedSignature", Encoder.encode(signatureVO));
                String xmlExtractedSignature = signatureVO.getSignature().replace("\n", "").replace("\r", "" );
                model.put("xmlExtractedSignature", xmlExtractedSignature);
                String xmlDataToSign = signatureVO.getSignedData().replace("\n", "").replace("\r", "" );
                model.put("xmlDataToSign", xmlDataToSign);
                model.put("xmlSignedBundle", "TODO!");

                model.put("isSignatureValid", messageSignature.isSignatureValid());
                model.put("certificate", messageSignature.getCertificate());
                model.put("isSignatureProvided", true);
            } else {
                model.put("isSignatureProvided", false);
            }

            model.put("isHTMLViewSupported", true);
        } catch (Exception e) {
            logger.warn("Couldn't load system configuration!", e);
            model.put("nodeConfiguration", null);
        }
    }

    public List<FileElementVO> getFileElementsToEncode(List<Attachment> attachments, List<AttachmentMetadata> aml) {
        List<FileElementVO> fileElementsToToEncode = new ArrayList<>();
        if (attachments != null) {
            for (Attachment attachment : attachments) {

                // TODO
                // For backward compatibility the filename is used to get the meta data, but for the new payloads the fileReferenceId (wrapperId in attachment) will be available
                // the wrapperId will be used instead of the filename to avoid the problems if there are identical files with a path and without
                AttachmentMetadata attachmentMetadata = attachment.getWrapperId() != null ? findAttachmentMetadata(aml, attachment.getWrapperId(), false) : findAttachmentMetadata(aml, attachment.getFileName(), true);

                String comment = null;
                String path = attachment.getFilePath();
                Boolean isConfidential = null;
                if (attachmentMetadata != null) {
                    comment = attachmentMetadata.getComment();
                    isConfidential = attachmentMetadata.getConfidential();
                    path = StringUtils.isNotEmpty(attachmentMetadata.getPath()) ? attachmentMetadata.getPath() : path; //for compatibility with WebStart
                }

                FileElementVO attToEncode = new FileElementVO();
                attToEncode.setId(attachment.getId());
                attToEncode.setFileSize(attachment.getFileSize());
                attToEncode.setFileName(attachment.getFileName());
                attToEncode.setTransmissionChecksum(attachment.getTransmissionChecksum());
                attToEncode.setTransmissionChecksumMethod(attachment.getTransmissionChecksumMethod());
                attToEncode.setContentChecksum(attachment.getContentChecksum());
                attToEncode.setContentChecksumMethod(attachment.getContentChecksumMethod());
                attToEncode.setMimeType(attachment.getMimeType());
                attToEncode.setFileReferenceId(attachment.getWrapperId());
                attToEncode.setType(attachment.getType());
                attToEncode.setComment(comment);
                attToEncode.setConfidential(isConfidential);
                attToEncode.setPath(path);

                fileElementsToToEncode.add(attToEncode);
            }
        }
        return fileElementsToToEncode;
    }


    public boolean isLightweightClient(ICADetails nodeIcaDetails) {
        return nodeIcaDetails == null || !nodeIcaDetails.getIsEncryptionRequired();
    }

    public String fetchPayloadIfReady(Map<String, Object> model, Long messageId) {

        String payload = null;
        Metadata meta = attachmentHandler.getMetadata(messageId);

        if (meta != null) {
            if (meta.getMetadataState() == Metadata.MetadataState.FAILED) {
                logger.debug("Fetching of payload failed for message: " + messageId);
                if (model != null) {
                    model.put("errorOccurred", "message.metadataFailure");
                }
            } else {
                payload = meta.getContent();
            }
        } else {
            logger.debug("Unable to find the content of payload for message " + messageId);
            if (model != null) {
                model.put("notReady", "message.metadataNotReady");
            }
        }
        return payload;
    }

    /**
     * @param aml        the list of attachment metadata
     * @param identifier contain the value to compare to
     * @param isFileName when true fileName is used when false the fileReferenceId is used to retrieve the metadata
     * @return the metadata for the specific attachment
     */
    private AttachmentMetadata findAttachmentMetadata(List<AttachmentMetadata> aml, String identifier, boolean isFileName) {
        if (identifier != null && aml != null) {
            for (AttachmentMetadata attachmentMetadata : aml) {
                if (isFileName && identifier.equals(attachmentMetadata.getFilename())) {
                    return attachmentMetadata;
                }

                if (!isFileName && identifier.equals(attachmentMetadata.getFileReferenceId())) {
                    return attachmentMetadata;
                }
            }
        }
        return null;
    }

    protected boolean hasLinkedIcas(Party party) {
        List<PartyIca> partyIcas = icaManager.getIcasByParty(party);
        return partyIcas.size() > 0 ? true : false;
    }
}
