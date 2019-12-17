package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ETX_WEB_MESSAGE_STATUS")
@NamedQueries({
        @NamedQuery(name = "findByStatusUuid", query = "SELECT o FROM MessageStatus o WHERE o.statusUuid = :statusUuid")
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MessageStatus extends AbstractEntity<Long> {

    public enum State {
        CREATED, INCOMING, SENT, FAILED
    }

    public enum Status {
        FAILED, AVAILABLE, READ, UNKNOWN
    }

    @SequenceGenerator(name = "ETX_WEB_MSTSEQ", sequenceName = "ETX_WEB_MSTSEQ", allocationSize = 1)
    @Id
    @Column(name = "MST_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_MSTSEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MSG_ID")
    private Message message;

    @Column(name = "MST_UUID")
    private String statusUuid;

    @Column(name = "MST_STATE")
    @Enumerated(EnumType.STRING)
    private State mstState;

    @Column(name = "MST_STATUS")
    @Enumerated(EnumType.STRING)
    private Status mstStatus;

    @Column(name = "MST_STATUS_CODE")
    private String mstStatusCode;

    @Column(name = "MST_LOGIN")
    private String login;

    @Column(name = "MST_CREATED_ON")
    private Date createdOn;

    @Column(name = "MST_UPDATED_ON")
    private Date updatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MST_PARENT_ID", updatable = false)
    private MessageStatus parent;

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

    public String getStatusUuid() {
        return statusUuid;
    }

    public void setStatusUuid(String referenceId) {
        this.statusUuid = referenceId;
    }

    public State getMstState() {
        return mstState;
    }

    public void setMstState(State msState) {
        this.mstState = msState;
    }

    public Status getMstStatus() {
        return mstStatus;
    }

    public void setMstStatus(Status msStatus) {
        this.mstStatus = msStatus;
    }

    public String getMstStatusCode() {
        return mstStatusCode;
    }

    public void setMstStatusCode(String mstStatusCode) {
        this.mstStatusCode = mstStatusCode;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public MessageStatus getParent() {
        return parent;
    }

    public void setParent(MessageStatus parent) {
        this.parent = parent;
    }
}
