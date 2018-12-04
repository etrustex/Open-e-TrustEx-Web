package eu.europa.ec.etrustex.webaccess.webservice.model;

import java.io.InputStream;

public class NodeAttachment {

    private InputStream inputStream;
    private long documentSize;
    private byte[] sessionKey;

    public NodeAttachment(InputStream inputStream, long documentSize) {
        this.inputStream = inputStream;
        this.documentSize = documentSize;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public long getDocumentSize() {
        return documentSize;
    }

    public byte[] getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(byte[] sessionKey) {
        this.sessionKey = sessionKey;
    }
}
