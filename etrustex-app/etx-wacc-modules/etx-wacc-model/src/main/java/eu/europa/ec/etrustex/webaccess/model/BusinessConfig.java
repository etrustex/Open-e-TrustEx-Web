package eu.europa.ec.etrustex.webaccess.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name="ETX_WEB_BUSINESS_CONFIG")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class BusinessConfig extends AbstractEntity<Long>  {

    @SequenceGenerator(name = "ETX_WEB_BCGSEQ", sequenceName = "ETX_WEB_BCGSEQ", allocationSize = 1)
    @Id
    @Column(name="BCG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ETX_WEB_BCGSEQ")
    private Long id;

    @Column(name = "BCG_PROPERTY_NAME", length = 50, nullable = false, unique = false)
    private String propertyName;

    @Column(name = "BCG_PROPERTY_VALUE", length = 250, nullable = false, unique = false)
    private String propertyValue;

    @Column(name = "BUS_ID", nullable = false, unique = false)
    private Long businessId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public BusinessConfigProperty getBusinessConfigProperty() {
        return BusinessConfigProperty.fromCode(propertyName);
    }

    public void setBusinessConfigProperty(BusinessConfigProperty businessConfigProperty) {
        this.propertyName = businessConfigProperty.getCode();
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessConfig{");
        sb.append("id=").append(id);
        sb.append(", propertyName='").append(propertyName).append('\'');
        sb.append(", propertyValue='").append(propertyValue).append('\'');
        sb.append(", businessId=").append(businessId);
        sb.append('}');
        return sb.toString();
    }
}
