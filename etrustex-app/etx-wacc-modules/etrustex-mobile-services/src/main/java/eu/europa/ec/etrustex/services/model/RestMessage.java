package eu.europa.ec.etrustex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * RestMessage
 */


public class RestMessage {
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("sender")
    private String sender = null;

    @JsonProperty("recipient")
    private String recipient = null;

    @JsonProperty("receiptDate")
    private String receiptDate = null;

    @JsonProperty("subject")
    private String subject = null;

    @JsonProperty("content")
    private String content = null;

    @JsonProperty("attachmentCount")
    private String attachmentCount = null;

    @JsonProperty("totalAttachmentSize")
    private String totalAttachmentSize = null;

    @JsonProperty("expirationWarning")
    private String expirationWarning = null;

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("expired")
    private String expired = null;

    @JsonProperty("contentEncrypted")
    private String contentEncrypted = null;

    @JsonProperty("signed")
    private String signed = null;

    @JsonProperty("sentDate")
    private String sentDate = null;

    @JsonProperty("statusReceiptDate")
    private String statusReceiptDate = null;


    public RestMessage id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RestMessage sender(String sender) {
        this.sender = sender;
        return this;
    }

    /**
     * Get sender
     *
     * @return sender
     **/
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public RestMessage recipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    /**
     * Get recipient
     *
     * @return recipient
     **/
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public RestMessage receiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    /**
     * Get receiptDate
     *
     * @return receiptDate
     **/
    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public RestMessage subject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Get subject
     *
     * @return subject
     **/
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public RestMessage content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Get content
     *
     * @return content
     **/
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RestMessage attachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
        return this;
    }

    /**
     * Get attachmentCount
     *
     * @return attachmentCount
     **/
    public String getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(String attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public RestMessage totalAttachmentSize(String totalAttachmentSize) {
        this.totalAttachmentSize = totalAttachmentSize;
        return this;
    }

    /**
     * Get totalAttachmentSize
     *
     * @return totalAttachmentSize
     **/
    public String getTotalAttachmentSize() {
        return totalAttachmentSize;
    }

    public void setTotalAttachmentSize(String totalAttachmentSize) {
        this.totalAttachmentSize = totalAttachmentSize;
    }

    public RestMessage expirationWarning(String expirationWarning) {
        this.expirationWarning = expirationWarning;
        return this;
    }

    /**
     * Expiration warning message to be displayed for inbox messages
     * @return expirationWarning
     **/
    public String getExpirationWarning() {
        return expirationWarning;
    }

    public void setExpirationWarning(String expirationWarning) {
        this.expirationWarning = expirationWarning;
    }

    /**
     * Get status
     *
     * @return status
     **/
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RestMessage expired(String expired) {
        this.expired = expired;
        return this;
    }

    /**
     * Get expired
     *
     * @return expired
     **/
    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public RestMessage contentEncrypted(String contentEncrypted) {
        this.contentEncrypted = contentEncrypted;
        return this;
    }

    /**
     * Get contentEncrypted
     *
     * @return contentEncrypted
     **/
    public String getContentEncrypted() {
        return contentEncrypted;
    }

    public void setContentEncrypted(String contentEncrypted) {
        this.contentEncrypted = contentEncrypted;
    }

    public RestMessage sentDate(String sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    public RestMessage signed(String signed) {
        this.signed = signed;
        return this;
    }

    /**
     * Signed flag for Inbox messages
     *
     * @return signed
     **/
    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    /**
     * Message sending date for sent messages
     *
     * @return sentDate
     **/
    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public RestMessage statusReceiptDate(String statusReceiptDate) {
        this.statusReceiptDate = statusReceiptDate;
        return this;
    }

    /**
     * Reception date of the status of a sent message (Status received on <statusReceiptDate>)
     *
     * @return statusReceiptDate
     **/
    public String getStatusReceiptDate() {
        return statusReceiptDate;
    }

    public void setStatusReceiptDate(String statusReceiptDate) {
        this.statusReceiptDate = statusReceiptDate;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestMessage restMessage = (RestMessage) o;
        return Objects.equals(this.id, restMessage.id) &&
                Objects.equals(this.sender, restMessage.sender) &&
                Objects.equals(this.recipient, restMessage.recipient) &&
                Objects.equals(this.receiptDate, restMessage.receiptDate) &&
                Objects.equals(this.subject, restMessage.subject) &&
                Objects.equals(this.content, restMessage.content) &&
                Objects.equals(this.attachmentCount, restMessage.attachmentCount) &&
                Objects.equals(this.totalAttachmentSize, restMessage.totalAttachmentSize) &&
                Objects.equals(this.expirationWarning, restMessage.expirationWarning) &&
                Objects.equals(this.status, restMessage.status) &&
                Objects.equals(this.expired, restMessage.expired) &&
                Objects.equals(this.signed, restMessage.signed) &&
                Objects.equals(this.contentEncrypted, restMessage.contentEncrypted) &&
                Objects.equals(this.sentDate, restMessage.sentDate) &&
                Objects.equals(this.statusReceiptDate, restMessage.statusReceiptDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, recipient, receiptDate, subject, content, attachmentCount, totalAttachmentSize, expirationWarning, status, expired, contentEncrypted, signed, sentDate, statusReceiptDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestMessage {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
        sb.append("    recipient: ").append(toIndentedString(recipient)).append("\n");
        sb.append("    receiptDate: ").append(toIndentedString(receiptDate)).append("\n");
        sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("    attachmentCount: ").append(toIndentedString(attachmentCount)).append("\n");
        sb.append("    totalAttachmentSize: ").append(toIndentedString(totalAttachmentSize)).append("\n");
        sb.append("    expirationWarning: ").append(toIndentedString(expirationWarning)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    expired: ").append(toIndentedString(expired)).append("\n");
        sb.append("    signed: ").append(toIndentedString(signed)).append("\n");
        sb.append("    contentEncrypted: ").append(toIndentedString(contentEncrypted)).append("\n");
        sb.append("    sentDate: ").append(toIndentedString(sentDate)).append("\n");
        sb.append("    statusReceiptDate: ").append(toIndentedString(statusReceiptDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

