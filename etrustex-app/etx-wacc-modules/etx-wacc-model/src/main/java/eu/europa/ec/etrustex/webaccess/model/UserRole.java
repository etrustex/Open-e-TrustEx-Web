package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "ETX_WEB_USER_ROLE")
@AttributeOverrides({
        @AttributeOverride(name = "createdOn", column = @Column(name = "URO_CREATED_ON", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "URO_UPDATED_ON")),
        @AttributeOverride(name = "activeState", column = @Column(name = "URO_ACTIVE_STATE", nullable = false))})
@AssociationOverrides({
        @AssociationOverride(name = "createdBy", joinColumns = @JoinColumn(name = "URO_CREATED_BY", nullable = false, updatable = false)),
        @AssociationOverride(name = "updatedBy", joinColumns = @JoinColumn(name = "URO_UPDATED_BY"))})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserRole extends StateAndTrackingInformation<Long> {

    private static final long serialVersionUID = -5819163348293309729L;

    @SequenceGenerator(name = "ETX_WEB_UROSEQ", sequenceName = "ETX_WEB_UROSEQ", allocationSize = 1)
    @Id
    @Column(name = "URO_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_UROSEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROL_TYPE")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAR_ID")
    private Party party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BUS_ID")
    private Business business;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
