package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ETX_WEB_ROLE")
@NamedQueries({
        @NamedQuery(name = "findRoleByType", query = "SELECT r FROM Role r WHERE r.type = :roleType",
                hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Role extends AbstractEntity<Role.Type> {

    public enum Type {
        OPERATOR,
        PARTY_ADMIN,
        BUSINESS_ADMIN,
        SYSTEM_ADMIN
    }

    private static final long serialVersionUID = -5819163348293309729L;

    @Id
    @Column(name = "ROL_TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "ROL_NAME")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ETX_WEB_ROLE_PRIVILEGE",
            joinColumns = {@JoinColumn(name = "ROL_TYPE")},
            inverseJoinColumns = {@JoinColumn(name = "PRV_TYPE")})
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private Set<Privilege> privileges = new HashSet<>();

    @Override
    public Type getId() {
        return type;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
