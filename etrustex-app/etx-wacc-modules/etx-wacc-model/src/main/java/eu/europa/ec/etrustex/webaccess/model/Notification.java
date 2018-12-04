package eu.europa.ec.etrustex.webaccess.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ETX_WEB_NOTIFICATION")
public class Notification extends AbstractEntity<Long> {

    public enum NotificationState {
        CREATED, FAILED, SENT
    }

    public enum NotificationType {
        MESSAGE_RECEIVED_EMAIL,
        STATUS_RECEIVED_EMAIL,
        WARNING_EMAIL
    }

    @SequenceGenerator(name = "ETX_WEB_NOTIFICATIONSEQ", sequenceName = "ETX_WEB_NOTIFICATIONSEQ", allocationSize = 1)
    @Id
    @Column(name = "NTF_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_NOTIFICATIONSEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MSG_ID")
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MST_ID", nullable = true)
    private MessageStatus messageStatus;

    @Column(name = "BUC_ID", nullable = true)
    private Long businessUserConfigId;

    @Column(name = "PAR_ID", nullable = true)
    private Long partyId;

    @Column(name = "NTF_EMAIL")
    private String mailAddress;

    @Column(name = "NTF_TYPE")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "NTF_STATE")
    @Enumerated(EnumType.STRING)
    private NotificationState notificationState;

    @Column(name = "NTF_CREATED_ON", nullable = false)
    private Date createdOn;

    @Column(name = "NTF_UPDATED_ON")
    private Date updatedOn;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Long getBusinessUserConfigId() {
        return businessUserConfigId;
    }

    public void setBusinessUserConfigId(Long businessUserConfigId) {
        this.businessUserConfigId = businessUserConfigId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationState getNotificationState() {
        return notificationState;
    }

    public void setNotificationState(NotificationState notificationState) {
        this.notificationState = notificationState;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
