package eu.europa.ec.etrustex.webaccess.web.model;

import eu.europa.ec.etrustex.webaccess.model.Message;

public class MessageFormBean {

    private Message message;
    //private String encodedSignature;
    private String xmlSignedBundle; //the complete signed bundle
    private String xmlExtractedSignature; //the signature
    private String xmlDataToSign; //the bundle to sign
    private String encodedIdSelectedPayloadFile;
    private String encodedIcaDetails;
    private String messageMetadata;
    private String icaDetailsVO;
    private String fileIdsListJSON; // replacement for encodedFileListIds, which was tough to replicate in Javasript (used serialized longs gzipped...)
    private boolean isEncryptionRequired;

    public MessageFormBean() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getXmlSignedBundle() {
        return xmlSignedBundle;
    }

    public void setXmlSignedBundle(String xmlSignedBundle) {
        this.xmlSignedBundle = xmlSignedBundle;
    }

    public String getXmlExtractedSignature() {
        return xmlExtractedSignature;
    }

    public void setXmlExtractedSignature(String xmlExtractedSignature) {
        this.xmlExtractedSignature = xmlExtractedSignature;
    }

    public String getXmlDataToSign() {
        return xmlDataToSign;
    }

    public void setXmlDataToSign(String xmlDataToSign) {
        this.xmlDataToSign = xmlDataToSign;
    }

    public String getEncodedIdSelectedPayloadFile() {
        return encodedIdSelectedPayloadFile;
    }

    public void setEncodedIdSelectedPayloadFile(String encodedIdSelectedPayloadFile) {
        this.encodedIdSelectedPayloadFile = encodedIdSelectedPayloadFile;
    }

    public String getEncodedIcaDetails() {
        return encodedIcaDetails;
    }

    public void setEncodedIcaDetails(String encodedIcaDetails) {
        this.encodedIcaDetails = encodedIcaDetails;
    }

    public String getMessageMetadata() {
        return messageMetadata;
    }

    public void setMessageMetadata(String messageMetadata) {
        this.messageMetadata = messageMetadata;
    }

    public String getIcaDetailsVO() {
        return icaDetailsVO;
    }

    public void setIcaDetailsVO(String icaDetailsVO) {
        this.icaDetailsVO = icaDetailsVO;
    }

    public String getFileIdsListJSON() {
        return fileIdsListJSON;
    }

    public void setFileIdsListJSON(String fileIdsListJSON) {
        this.fileIdsListJSON = fileIdsListJSON;
    }

    public boolean getIsEncryptionRequired() {
        return isEncryptionRequired;
    }

    public void setEncryptionRequired(boolean encryptionRequired) {
        isEncryptionRequired = encryptionRequired;
    }


}
