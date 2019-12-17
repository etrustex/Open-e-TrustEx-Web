package eu.europa.ec.etrustex.webaccess.model.vo;

import java.io.Serializable;

public class AttachmentMetadata implements Serializable{

    private static final long serialVersionUID = -4822368730222120473L;
    private String comment;
    private Boolean isConfidential;
    private String filename;
    private String path;
    private String fileReferenceId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getConfidential() {
        return isConfidential;
    }

    public void setConfidential(Boolean isConfidential) {
        this.isConfidential = isConfidential;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileReferenceId() {
        return fileReferenceId;
    }

    public void setFileReferenceId(String fileReferenceId) {
        this.fileReferenceId = fileReferenceId;
    }
}
