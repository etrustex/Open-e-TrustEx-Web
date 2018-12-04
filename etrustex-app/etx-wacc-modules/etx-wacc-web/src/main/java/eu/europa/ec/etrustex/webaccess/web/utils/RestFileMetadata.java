package eu.europa.ec.etrustex.webaccess.web.utils;

public class RestFileMetadata {

    private String filename;
    private byte[] aes256EncryptedKey;
    private long documentSize;
    private String transmissionChecksumHex;
    private String transmissionChecksumMethod;
    private String contentChecksumHex;
    private String contentChecksumMethod;
    private boolean isSigned;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getAes256EncryptedKey() {
        return aes256EncryptedKey;
    }

    public void setAes256EncryptedKey(byte[] aes256EncryptedKey) {
        this.aes256EncryptedKey = aes256EncryptedKey;
    }

    public long getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSize(long documentSize) {
        this.documentSize = documentSize;
    }

    public String getTransmissionChecksumHex() {
        return transmissionChecksumHex;
    }

    public void setTransmissionChecksumHex(String transmissionChecksumHex) {
        this.transmissionChecksumHex = transmissionChecksumHex;
    }

    public String getTransmissionChecksumMethod() {
        return transmissionChecksumMethod;
    }

    public void setTransmissionChecksumMethod(String transmissionChecksumMethod) {
        this.transmissionChecksumMethod = transmissionChecksumMethod;
    }

    public String getContentChecksumHex() {
        return contentChecksumHex;
    }

    public void setContentChecksumHex(String contentChecksumHex) {
        this.contentChecksumHex = contentChecksumHex;
    }

    public String getContentChecksumMethod() {
        return contentChecksumMethod;
    }

    public void setContentChecksumMethod(String contentChecksumMethod) {
        this.contentChecksumMethod = contentChecksumMethod;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }
}
