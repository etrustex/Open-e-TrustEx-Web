package eu.europa.ec.etrustex.webaccess.model.vo;

import eu.europa.ec.etrustex.webaccess.model.AttachmentType;

import java.io.Serializable;

public class FileElementVO implements Serializable {

    private Long id;
    private Long fileSize;
    private String fileName;
    private String path;
    private byte[] transmissionChecksum;
    private String transmissionChecksumMethod;
    private byte[] contentChecksum;
    private String contentChecksumMethod;
    private String mimeType;
    private String fileReferenceId;
    private AttachmentType type;
    private String comment;
    private Boolean confidential;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getTransmissionChecksum() {
        return transmissionChecksum;
    }

    public void setTransmissionChecksum(byte[] transmissionChecksum) {
        this.transmissionChecksum = transmissionChecksum;
    }

    public String getTransmissionChecksumMethod() {
        return transmissionChecksumMethod;
    }

    public void setTransmissionChecksumMethod(String transmissionChecksumMethod) {
        this.transmissionChecksumMethod = transmissionChecksumMethod;
    }

    public byte[] getContentChecksum() {
        return contentChecksum;
    }

    public void setContentChecksum(byte[] contentChecksum) {
        this.contentChecksum = contentChecksum;
    }

    public String getContentChecksumMethod() {
        return contentChecksumMethod;
    }

    public void setContentChecksumMethod(String contentChecksumMethod) {
        this.contentChecksumMethod = contentChecksumMethod;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileReferenceId() {
        return fileReferenceId;
    }

    public void setFileReferenceId(String fileReferenceId) {
        this.fileReferenceId = fileReferenceId;
    }

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getConfidential() {
        return confidential;
    }

    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

}
