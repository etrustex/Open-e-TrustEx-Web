package eu.europa.ec.etrustex.webaccess.model.websocket;

/**
 * WebSocket uploading files data structures.
 * This class represents the information sent from the WebStart to the WebPage after the attachments have been uploaded to the node
 */
public class UploadBundle {


    private String fileList;  //uploaded files metadata  in json string format
    private Long idSelectedPayloadFile; // id of selected uploaded file as payload (generic case)
    private String encodedIdSelectedPayloadFile; // encoded id of selected uploaded file as payload (generic case)
    private String xmlSignedBundle;
    private String xmlDataToSign;
    private String xmlExtractedSignature;
    private String fileIdsListJSON; // substitute for the encodedfilelistids...

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }

    public Long getIdSelectedPayloadFile() {
        return idSelectedPayloadFile;
    }

    public void setIdSelectedPayloadFile(Long idSelectedPayloadFile) {
        this.idSelectedPayloadFile = idSelectedPayloadFile;
    }

    public String getEncodedIdSelectedPayloadFile() {
        return encodedIdSelectedPayloadFile;
    }

    public void setEncodedIdSelectedPayloadFile(String encodedIdSelectedPayloadFile) {
        this.encodedIdSelectedPayloadFile = encodedIdSelectedPayloadFile;
    }

    public String getXmlSignedBundle() {
        return xmlSignedBundle;
    }

    public void setXmlSignedBundle(String xmlSignedBundle) {
        this.xmlSignedBundle = xmlSignedBundle;
    }

    public String getXmlDataToSign() {
        return xmlDataToSign;
    }

    public void setXmlDataToSign(String xmlDataToSign) {
        this.xmlDataToSign = xmlDataToSign;
    }

    public String getXmlExtractedSignature() {
        return xmlExtractedSignature;
    }

    public void setXmlExtractedSignature(String xmlExtractedSignature) {
        this.xmlExtractedSignature = xmlExtractedSignature;
    }

    public String getFileIdsListJSON() {
        return fileIdsListJSON;
    }

    public void setFileIdsListJSON(String fileIdsListJSON) {
        this.fileIdsListJSON = fileIdsListJSON;
    }
}
