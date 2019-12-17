package eu.europa.ec.etrustex.webaccess.model.vo;

import java.util.Date;

/**
 * @author: micleva
 * @date: 9/26/13 9:14 AM
 * @project: ETX
 */
public class MessageListEntry {
    // this group of data are information directly linked to a message
    private Long messageId;
    private Date createDate;
    private Date sentDate;
    private String subject;
    private String senderName;
    private String referenceId;
    private RetentionMetadata retentionMetadata;
    private String statusCode;
    private String messageState;

    // here are additional information about the message
    private boolean readByCurrentUser;
    private boolean hasAttachments;
    private boolean hasIca;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public boolean getReadByCurrentUser() {
        return readByCurrentUser;
    }

    public void setReadByCurrentUser(boolean readByCurrentUser) {
        this.readByCurrentUser = readByCurrentUser;
    }

    public boolean getHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public RetentionMetadata getRetentionMetadata() {
        return retentionMetadata;
    }

    public void setRetentionMetadata(RetentionMetadata retentionMetadata) {
        this.retentionMetadata = retentionMetadata;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessageState() {
        return messageState;
    }

    public void setMessageState(String messageState) {
        this.messageState = messageState;
    }

    public boolean isHasIca() {
        return hasIca;
    }

    public void setHasIca(boolean hasIca) {
        this.hasIca = hasIca;
    }
}
