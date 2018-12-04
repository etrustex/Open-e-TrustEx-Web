package eu.europa.ec.etrustex.webaccess.model;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "ETX_WEB_BUSINESS")
@AttributeOverrides({
        @AttributeOverride(name = "createdOn", column = @Column(name = "BUS_CREATED_ON", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedOn", column = @Column(name = "BUS_UPDATED_ON")),
        @AttributeOverride(name = "activeState", column = @Column(name = "BUS_ACTIVE_STATE", nullable = false))})
@AssociationOverrides({
        @AssociationOverride(name = "createdBy", joinColumns = @JoinColumn(name = "BUS_CREATED_BY", nullable = false, updatable = false)),
        @AssociationOverride(name = "updatedBy", joinColumns = @JoinColumn(name = "BUS_UPDATED_BY"))})
@NamedQueries({
        @NamedQuery(name = "findAllBusinesses", query = "SELECT o FROM Business o WHERE o.activeState = 1",
                hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
})
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Business extends StateAndTrackingInformation<Long> {

    @SequenceGenerator(name = "ETX_WEB_BUSSEQ", sequenceName = "ETX_WEB_BUSSEQ", allocationSize = 1)
    @Id
    @Column(name = "BUS_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_BUSSEQ")
    private Long id;

    @Column(name = "BUS_NAME")
    private String name;

    @Column(name = "BUS_DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    private List<Party> parties;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<BusinessUserConfig> businessUserConfigs;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "BUS_ID")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private List<BusinessConfig> businessConfigs;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BusinessConfig> getBusinessConfigs() {
        return businessConfigs;
    }

    public Map<BusinessConfigProperty, String> getBusinessConfigValues() {
        Map<BusinessConfigProperty, String> values = new HashMap<>();
        for (BusinessConfig businessConfig : businessConfigs) {
            values.put(businessConfig.getBusinessConfigProperty(), businessConfig.getPropertyValue());
        }
        return values;
    }

    public void setBusinessConfigs(List<BusinessConfig> businessConfigs) {
        this.businessConfigs = businessConfigs;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

    public List<BusinessUserConfig> getBusinessUserConfigs() {
        return businessUserConfigs;
    }

    public void setBusinessUserConfigs(List<BusinessUserConfig> businessUserConfigs) {
        this.businessUserConfigs = businessUserConfigs;
    }
}