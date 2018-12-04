
package ec.schema.xsd.commonaggregatecomponents_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.ScopeInstanceIdentifierType;
import ec.schema.xsd.commonbasiccomponents_1.ScopeTypeType;


/**
 * <p>Java class for BusinessScopeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessScopeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}ScopeType"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}ScopeInstanceIdentifier"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessScopeType", propOrder = {
    "scopeType",
    "scopeInstanceIdentifier"
})
public class BusinessScopeType {

    @XmlElement(name = "ScopeType", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected ScopeTypeType scopeType;
    @XmlElement(name = "ScopeInstanceIdentifier", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected ScopeInstanceIdentifierType scopeInstanceIdentifier;

    /**
     * Gets the value of the scopeType property.
     * 
     * @return
     *     possible object is
     *     {@link ScopeTypeType }
     *     
     */
    public ScopeTypeType getScopeType() {
        return scopeType;
    }

    /**
     * Sets the value of the scopeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScopeTypeType }
     *     
     */
    public void setScopeType(ScopeTypeType value) {
        this.scopeType = value;
    }

    /**
     * Gets the value of the scopeInstanceIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ScopeInstanceIdentifierType }
     *     
     */
    public ScopeInstanceIdentifierType getScopeInstanceIdentifier() {
        return scopeInstanceIdentifier;
    }

    /**
     * Sets the value of the scopeInstanceIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScopeInstanceIdentifierType }
     *     
     */
    public void setScopeInstanceIdentifier(ScopeInstanceIdentifierType value) {
        this.scopeInstanceIdentifier = value;
    }

}
