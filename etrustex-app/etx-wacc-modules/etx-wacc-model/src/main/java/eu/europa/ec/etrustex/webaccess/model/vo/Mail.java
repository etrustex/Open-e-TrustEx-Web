package eu.europa.ec.etrustex.webaccess.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author apladap
 */
public class Mail implements Serializable {

    private static final long serialVersionUID = -861943757725438367L;

    private String content;
    private String subject;
    private Date sentDate;
    private String mailAddress;
    private Long notificationId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }
}
