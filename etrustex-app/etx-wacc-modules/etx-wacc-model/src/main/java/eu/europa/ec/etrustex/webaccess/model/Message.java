package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This entity is a special case were cache strategy NONSTRICT_READ_WRITE were choosen instead of READ_WRITE as it is on
 * the rest of entities. We need the values of fields createdOn/createdBy to be cached also on updates, which doesn't
 * happen for READ_WRITE strategy because of updatable false on these columns. The NONSTRICT_READ_WRITE strategy will
 * clean the entity from the second level cache at every update, forcing the retrieve of the entity from DB.
 */
@Entity
@Table(name = "ETX_WEB_MESSAGE")
@AttributeOverrides({
        @AttributeOverride(name = "createdOn", column = @Column(name = "MSG_CREATED_ON", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "MSG_UPDATED_ON")),
        @AttributeOverride(name = "activeState", column = @Column(name = "MSG_ACTIVE_STATE", nullable = false))})
@AssociationOverrides({
        @AssociationOverride(name = "createdBy", joinColumns = @JoinColumn(name = "MSG_CREATED_BY", updatable = false)),
        @AssociationOverride(name = "updatedBy", joinColumns = @JoinColumn(name = "MSG_UPDATED_BY"))})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Message extends StateAndTrackingInformation<Long> {

    private static final long serialVersionUID = -5819163348293309729L;

    @SequenceGenerator(name = "ETX_WEB_MSGSEQ", sequenceName = "ETX_WEB_MSGSEQ", allocationSize = 1)
    @Id
    @Column(name = "MSG_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_MSGSEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MSG_LOCAL_PARTY_ID", updatable = false)
    private Party localParty;

    @ManyToOne
    @JoinColumn(name = "MSG_REMOTE_PARTY_ID")
    private Party remoteParty;

    @Column(name = "MSG_ISSUE_DATE")
    private Date issueDate;

    @Column(name = "MSG_SUBJECT")
    private String subject;

    @Column(name = "MSG_CONTENT", length = 4000)
    private String content;

    @Column(name = "MSG_STATE")
    @Enumerated(EnumType.STRING)
    private MessageState msgState;

    @Column(name = "MSG_REF_ID", updatable = false)
    private String referenceId;

    @Column(name = "MSG_BUNDLE_ID")
    private String bundleId;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Attachment> attachments;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "MSG_ID", referencedColumnName = "MSG_ID", nullable = false)
    private List<MessageSignature> signatures;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="LAST_MST_ID")
    private MessageStatus lastStatus;

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MessageStatus> statuses;

    @Formula("case when MSG_UPDATED_ON is not null then MSG_UPDATED_ON else MSG_CREATED_ON end")
    private Date savedOn;

    @Column(name = "MSG_SEARCHABLE_CONTENT")
    private String searchableContent;
    @Column(name = "MSG_SENT_ON")
    private Date sentOn;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Party getLocalParty() {
        return localParty;
    }

    public void setLocalParty(Party localParty) {
        this.localParty = localParty;
    }

    public Party getRemoteParty() {
        return remoteParty;
    }

    public void setRemoteParty(Party remoteParty) {
        this.remoteParty = remoteParty;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageState getMsgState() {
        return msgState;
    }

    public void setMsgState(MessageState msgState) {
        this.msgState = msgState;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        for (Attachment attachment : attachments) {
            attachment.setMessage(this);
        }
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public MessageStatus getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(MessageStatus status) {
        this.lastStatus = status;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<MessageStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<MessageStatus> statuses) {
        this.statuses = statuses;
    }

    public Date getSavedOn() {
        return savedOn;
    }

    public String getSearchableContent() {
        return searchableContent;
    }

    public void setSearchableContent(String searchableContent) {
        this.searchableContent = searchableContent;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }
    public List<MessageSignature> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<MessageSignature> signatures) {
        if (this.signatures != null) {
            this.signatures.clear();
            if (signatures != null) {
                this.signatures.addAll(signatures);
            }
        } else {
            this.signatures = signatures;
        }
    }

    public enum MessageState {
        INCOMING, DRAFT, IN_PREPARATION, SENT
    }
}