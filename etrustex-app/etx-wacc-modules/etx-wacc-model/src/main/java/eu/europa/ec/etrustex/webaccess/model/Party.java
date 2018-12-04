package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ETX_WEB_PARTY")
@AttributeOverrides({
        @AttributeOverride(name = "createdOn", column = @Column(name = "PAR_CREATED_ON", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "PAR_UPDATED_ON")),
        @AttributeOverride(name = "activeState", column = @Column(name = "PAR_ACTIVE_STATE", nullable = false))})
@AssociationOverrides({
        @AssociationOverride(name = "createdBy", joinColumns = @JoinColumn(name = "PAR_CREATED_BY", nullable = false, updatable = false)),
        @AssociationOverride(name = "updatedBy", joinColumns = @JoinColumn(name = "PAR_UPDATED_BY"))})
@NamedQueries({
        @NamedQuery(name = "findAllParties", query = "SELECT p FROM Party p WHERE p.activeState = 1 AND p.webManaged = 1",
                hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true") }),
        @NamedQuery(name = "findAvailablePartiesForBusiness", query = "SELECT p FROM Party p WHERE p.business = :business AND p.activeState = 1 AND p.webManaged = 1 AND p.business.activeState = 1",
                hints = {@QueryHint(name = "org.hibernate.cacheable", value = "true")}),
        @NamedQuery(name = "findRemoteParties", query = "SELECT ica.remoteParty FROM PartyIca ica WHERE ica.party = :party AND ica.activeState = 1"),
        @NamedQuery(name = "findWebManagedPartyByNodeName", query = "SELECT p FROM Party p WHERE p.nodeName = :nodeName AND p.webManaged = 1 AND p.activeState = 1"),
        @NamedQuery(name = "findRemotePartyByNodeNameBusId", query = "SELECT p FROM Party p WHERE p.nodeName = :nodeName AND p.business.id = :busId AND p.activeState = 1")
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Party extends StateAndTrackingInformation<Long> {

    private static final long serialVersionUID = -5819163348293309729L;

    @SequenceGenerator(name = "ETX_WEB_PRTSEQ", sequenceName = "ETX_WEB_PRTSEQ", allocationSize = 1)
    @Id
    @Column(name = "PAR_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_PRTSEQ")
    private Long id;

    @Column(name = "PAR_NODE_NAME")
    private String nodeName;

    @Column(name = "PAR_DISPLAY_NAME", nullable = false)
    private String displayName;

    @Column(name = "PAR_EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "BUS_ID")
    private Business business;

    @Column(name = "PAR_NODE_USR")
    private String nodeUserName;

    @Column(name = "PAR_NODE_PASS")
    private String nodeUserPass;

    @Column(name = "PAR_NOTIFICATIONS_ENABLED")
    private Boolean notificationsEnabled;

    @Column(name = "PAR_CONSUME_NODE_MSG")
    private Boolean consumeNodeMessages;

    @Column(name = "PAR_STS_NOTIF_ENABLED")
    private Boolean statusNotificationEnabled;

    @Column(name= "PAR_STS_NOTIF_EMAIL")
    private String statusNotificationEmail;

    @Column(name = "PAR_WEB_MANAGED", nullable = false)
    private boolean webManaged;

    @OneToMany(mappedBy = "party", fetch = FetchType.LAZY)
    private List<PartyIca> partyICAs;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getNodeUserName() {
        return nodeUserName;
    }

    public void setNodeUserName(String nodeUserName) {
        this.nodeUserName = nodeUserName;
    }

    public String getNodeUserPass() {
        return nodeUserPass;
    }

    public void setNodeUserPass(String nodeUserPass) {
        this.nodeUserPass = nodeUserPass;
    }

    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public Boolean getConsumeNodeMessages() {
        return consumeNodeMessages;
    }

    public void setConsumeNodeMessages(Boolean consumeNodeBundles) {
        this.consumeNodeMessages = consumeNodeBundles;
    }

    public Boolean getStatusNotificationEnabled() {
        return statusNotificationEnabled;
    }

    public void setStatusNotificationEnabled(Boolean statusNotificationEnabled) {
        this.statusNotificationEnabled = statusNotificationEnabled;
    }

    public String getStatusNotificationEmail() {
        return statusNotificationEmail;
    }

    public void setStatusNotificationEmail(String statusNotificationEmail) {
        this.statusNotificationEmail = statusNotificationEmail;
    }

    public boolean getWebManaged() {
        return webManaged;
    }

    public void setWebManaged(boolean webManaged) {
        this.webManaged = webManaged;
    }

    public List<PartyIca> getPartyICAs() {
        return partyICAs;
    }

    public void setPartyICAs(List<PartyIca> partyICAs) {
        this.partyICAs = partyICAs;
    }

}