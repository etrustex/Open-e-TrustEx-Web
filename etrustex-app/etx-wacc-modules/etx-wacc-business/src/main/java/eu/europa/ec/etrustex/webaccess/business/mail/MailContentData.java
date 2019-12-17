package eu.europa.ec.etrustex.webaccess.business.mail;

import java.util.Date;

public class MailContentData {
    private String templateFile;
    private String status;
    private String statusCode;
    private String userName;
    private String receiverName;
    private String senderName;
    private Long messageId;
    private String messageReferenceId;
    private String messageTitle;
    private Date messageCreatedOn;
    private String createdByUser;
    private String statusDate;
    private String statusTime;
    private long numFiles;
    private boolean isValid;
    private String messageSentOnDate;
    private String messageSentOnTime;

    public MailContentData() {
    }

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageReferenceId() {
        return messageReferenceId;
    }

    public void setMessageReferenceId(String messageReferenceId) {
        this.messageReferenceId = messageReferenceId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public Date getMessageCreatedOn() {
        return messageCreatedOn;
    }

    public void setMessageCreatedOn(Date messageCreatedOn) {
        this.messageCreatedOn = messageCreatedOn;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    public long getNumFiles() {
        return numFiles;
    }

    public void setNumFiles(long numFiles) {
        this.numFiles = numFiles;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessageSentOnDate() {
        return messageSentOnDate;
    }

    public void setMessageSentOnDate(String messageSentOnDate) {
        this.messageSentOnDate = messageSentOnDate;
    }

    public String getMessageSentOnTime() {
        return messageSentOnTime;
    }

    public void setMessageSentOnTime(String messageSentOnTime) {
        this.messageSentOnTime = messageSentOnTime;
    }
}
