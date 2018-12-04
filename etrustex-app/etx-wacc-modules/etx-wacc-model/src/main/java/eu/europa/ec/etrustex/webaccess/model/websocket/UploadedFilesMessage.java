package eu.europa.ec.etrustex.webaccess.model.websocket;


/**
 * WebSocket message for uploading files.
 * This class represents the message sent from the WebStart to the WebPage after the attachments have been uploaded to the node
 */
public class UploadedFilesMessage extends Message {

    private UploadBundle uploadBundle;

    public UploadBundle getUploadBundle() {
        return uploadBundle;
    }

    public void setUploadBundle(UploadBundle uploadBundle) {
        this.uploadBundle = uploadBundle;
    }
}
