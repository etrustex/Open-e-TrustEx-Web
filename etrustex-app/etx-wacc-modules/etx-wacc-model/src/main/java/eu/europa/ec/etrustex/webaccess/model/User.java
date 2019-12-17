package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ETX_WEB_USER")
@AttributeOverrides({
        @AttributeOverride(name = "createdOn", column = @Column(name = "USR_CREATED_ON", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "USR_UPDATED_ON")),
        @AttributeOverride(name = "activeState", column = @Column(name = "USR_ACTIVE_STATE", nullable = false))})
@AssociationOverrides({
        @AssociationOverride(name = "createdBy", joinColumns = @JoinColumn(name = "USR_CREATED_BY", nullable = false, updatable = false)),
        @AssociationOverride(name = "updatedBy", joinColumns = @JoinColumn(name = "USR_UPDATED_BY"))})
@NamedQueries({
        @NamedQuery(name = "findUserByLogin", query = "SELECT u FROM User u WHERE u.login = :login",
                hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends StateAndTrackingInformation<Long> {

    private static final long serialVersionUID = 7352585478043988089L;

    @SequenceGenerator(name = "ETX_WEB_USRSEQ", sequenceName = "ETX_WEB_USRSEQ", allocationSize = 1)
    @Id
    @Column(name = "USR_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_USRSEQ")
    private Long id;

    @Column(name = "USR_LOGIN")
    private String login;

    @Column(name = "USR_NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "ETX_WEB_USER_ROLE",
            joinColumns = {@JoinColumn(name = "USR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "URO_ID")})
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<UserRole> userRoles;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
