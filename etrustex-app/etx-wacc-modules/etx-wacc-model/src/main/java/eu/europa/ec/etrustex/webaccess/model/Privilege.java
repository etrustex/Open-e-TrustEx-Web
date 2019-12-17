package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "ETX_WEB_PRIVILEGE")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Privilege extends AbstractEntity<Privilege.Type> {

    public enum Type {
        CAN_ACCESS_OWN_PARTY_MESSAGES,
        CAN_ASSIGN_PARTY_SCOPE_ROLES,
        CAN_ASSIGN_BUSINESS_SCOPE_ROLES,
        CAN_DEFINE_BUSINESS,
        CAN_ASSIGN_SYSTEM_SCOPE_ROLES,
        CAN_MANAGE_PARTIES_FOR_BUSINESS,
        CAN_MANAGE_BUSINESS_CONFIGURATIONS,
        CAN_MANAGE_APP_CONFIGURATIONS
    }

    private static final long serialVersionUID = -5819163348293309729L;

    @Id
    @Column(name = "PRV_TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "PRV_NAME")
    private String name;

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

    public void setType(Type type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }
}
