package eu.europa.ec.digit.etrustex.mobile.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import javax.validation.constraints.*;
/**
 * Message
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-04T11:03:17.949+02:00")

public class Message   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("sender")
  private String sender = null;

  @JsonProperty("recipient")
  private String recipient = null;

  @JsonProperty("receiptDate")
  private OffsetDateTime receiptDate = null;

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
  private OffsetDateTime sentDate = null;

  @JsonProperty("statusReceiptDate")
  private OffsetDateTime statusReceiptDate = null;

  public Message id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Message technical ID
   * @return id
  **/
  @ApiModelProperty(value = "Message technical ID")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Message sender(String sender) {
    this.sender = sender;
    return this;
  }

   /**
   * Get sender
   * @return sender
  **/
  @ApiModelProperty(value = "")
  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public Message recipient(String recipient) {
    this.recipient = recipient;
    return this;
  }

   /**
   * Get recipient
   * @return recipient
  **/
  @ApiModelProperty(value = "")
  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public Message receiptDate(OffsetDateTime receiptDate) {
    this.receiptDate = receiptDate;
    return this;
  }

   /**
   * Reception date of an inbox message
   * @return receiptDate
  **/
  @ApiModelProperty(value = "Reception date of an inbox message")
  public OffsetDateTime getReceiptDate() {
    return receiptDate;
  }

  public void setReceiptDate(OffsetDateTime receiptDate) {
    this.receiptDate = receiptDate;
  }

  public Message subject(String subject) {
    this.subject = subject;
    return this;
  }

   /**
   * Get subject
   * @return subject
  **/
  @ApiModelProperty(value = "")
  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Message content(String content) {
    this.content = content;
    return this;
  }

   /**
   * Get content
   * @return content
  **/
  @ApiModelProperty(value = "")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Message attachmentCount(String attachmentCount) {
    this.attachmentCount = attachmentCount;
    return this;
  }

   /**
   * Get attachmentCount
   * @return attachmentCount
  **/
  @ApiModelProperty(value = "")
  public String getAttachmentCount() {
    return attachmentCount;
  }

  public void setAttachmentCount(String attachmentCount) {
    this.attachmentCount = attachmentCount;
  }

  public Message totalAttachmentSize(String totalAttachmentSize) {
    this.totalAttachmentSize = totalAttachmentSize;
    return this;
  }

   /**
   * Get totalAttachmentSize
   * @return totalAttachmentSize
  **/
  @ApiModelProperty(value = "")
  public String getTotalAttachmentSize() {
    return totalAttachmentSize;
  }

  public void setTotalAttachmentSize(String totalAttachmentSize) {
    this.totalAttachmentSize = totalAttachmentSize;
  }

  public Message expirationWarning(String expirationWarning) {
    this.expirationWarning = expirationWarning;
    return this;
  }

   /**
   * Expiration warning message to be displayed for inbox messages
   * @return expirationWarning
  **/
  @ApiModelProperty(value = "Expiration warning message to be displayed for inbox messages")
  public String getExpirationWarning() {
    return expirationWarning;
  }

  public void setExpirationWarning(String expirationWarning) {
    this.expirationWarning = expirationWarning;
  }

  public Message status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Possible values are read/unread for Inbox, read/delivered/failed/none for Sent
   * @return status
  **/
  @ApiModelProperty(value = "Possible values are read/unread for Inbox, read/delivered/failed/none for Sent")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Message expired(String expired) {
    this.expired = expired;
    return this;
  }

   /**
   * Flag that marks an inbox message as expired
   * @return expired
  **/
  @ApiModelProperty(value = "Flag that marks an inbox message as expired")
  public String getExpired() {
    return expired;
  }

  public void setExpired(String expired) {
    this.expired = expired;
  }

  public Message contentEncrypted(String contentEncrypted) {
    this.contentEncrypted = contentEncrypted;
    return this;
  }

   /**
   * Encrypted flag for Inbox messages
   * @return contentEncrypted
  **/
  @ApiModelProperty(value = "Encrypted flag for Inbox messages")
  public String getContentEncrypted() {
    return contentEncrypted;
  }

  public void setContentEncrypted(String contentEncrypted) {
    this.contentEncrypted = contentEncrypted;
  }

  public Message signed(String signed) {
    this.signed = signed;
    return this;
  }

   /**
   * Signed flag for Inbox messages
   * @return signed
  **/
  @ApiModelProperty(value = "Signed flag for Inbox messages")
  public String getSigned() {
    return signed;
  }

  public void setSigned(String signed) {
    this.signed = signed;
  }

  public Message sentDate(OffsetDateTime sentDate) {
    this.sentDate = sentDate;
    return this;
  }

   /**
   * Message sending date for sent messages
   * @return sentDate
  **/
  @ApiModelProperty(value = "Message sending date for sent messages")
  public OffsetDateTime getSentDate() {
    return sentDate;
  }

  public void setSentDate(OffsetDateTime sentDate) {
    this.sentDate = sentDate;
  }

  public Message statusReceiptDate(OffsetDateTime statusReceiptDate) {
    this.statusReceiptDate = statusReceiptDate;
    return this;
  }

   /**
   * Reception date of the status of a sent message (Status received on <statusReceiptDate>)
   * @return statusReceiptDate
  **/
  @ApiModelProperty(value = "Reception date of the status of a sent message (Status received on <statusReceiptDate>)")
  public OffsetDateTime getStatusReceiptDate() {
    return statusReceiptDate;
  }

  public void setStatusReceiptDate(OffsetDateTime statusReceiptDate) {
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
    Message message = (Message) o;
    return Objects.equals(this.id, message.id) &&
        Objects.equals(this.sender, message.sender) &&
        Objects.equals(this.recipient, message.recipient) &&
        Objects.equals(this.receiptDate, message.receiptDate) &&
        Objects.equals(this.subject, message.subject) &&
        Objects.equals(this.content, message.content) &&
        Objects.equals(this.attachmentCount, message.attachmentCount) &&
        Objects.equals(this.totalAttachmentSize, message.totalAttachmentSize) &&
        Objects.equals(this.expirationWarning, message.expirationWarning) &&
        Objects.equals(this.status, message.status) &&
        Objects.equals(this.expired, message.expired) &&
        Objects.equals(this.contentEncrypted, message.contentEncrypted) &&
        Objects.equals(this.signed, message.signed) &&
        Objects.equals(this.sentDate, message.sentDate) &&
        Objects.equals(this.statusReceiptDate, message.statusReceiptDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sender, recipient, receiptDate, subject, content, attachmentCount, totalAttachmentSize, expirationWarning, status, expired, contentEncrypted, signed, sentDate, statusReceiptDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Message {\n");
    
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
    sb.append("    contentEncrypted: ").append(toIndentedString(contentEncrypted)).append("\n");
    sb.append("    signed: ").append(toIndentedString(signed)).append("\n");
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

