package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "ETX_WEB_BUSINESS_USER_CONFIG")
@AttributeOverrides({
        @AttributeOverride(name = "createdOn", column = @Column(name = "BUC_CREATED_ON", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "BUC_UPDATED_ON")),
        @AttributeOverride(name = "activeState", column = @Column(name = "BUC_ACTIVE_STATE", nullable = false))})
@AssociationOverrides({
        @AssociationOverride(name = "createdBy", joinColumns = @JoinColumn(name = "BUC_CREATED_BY", nullable = false, updatable = false)),
        @AssociationOverride(name = "updatedBy", joinColumns = @JoinColumn(name = "BUC_UPDATED_BY"))})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BusinessUserConfig extends StateAndTrackingInformation<Long> {

    private static final long serialVersionUID = -5819163348293309729L;

    @SequenceGenerator(name = "ETX_WEB_BUCSEQ", sequenceName = "ETX_WEB_BUCSEQ", allocationSize = 1)
    @Id
    @Column(name = "BUC_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_BUCSEQ")
    private Long id;

    @Column(name = "BUC_NAME")
    private String name;

    @Column(name = "BUC_EMAIL")
    private String email;

    @Column(name = "BUC_NOTIFICATIONS_ENABLED")
    private Boolean notificationsEnabled = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUS_ID")
    private Business business;

    @Column(name = "BUC_STS_NOTIF_ENABLED")
    private Boolean statusNotificationEnabled = Boolean.FALSE;

    @Column(name= "BUC_STS_NOTIF_EMAIL")
    private String statusNotificationEmail;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(Boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
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
}
