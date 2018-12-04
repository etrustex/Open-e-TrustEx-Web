
package ec.schema.xsd.commonaggregatecomponents_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HeaderType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}BusinessHeader"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}TechnicalHeader" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderType", propOrder = {
    "businessHeader",
    "technicalHeader"
})
public class HeaderType {

    @XmlElement(name = "BusinessHeader", required = true)
    protected BusinessHeaderType businessHeader;
    @XmlElement(name = "TechnicalHeader")
    protected TechnicalHeaderType technicalHeader;

    /**
     * Gets the value of the businessHeader property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessHeaderType }
     *     
     */
    public BusinessHeaderType getBusinessHeader() {
        return businessHeader;
    }

    /**
     * Sets the value of the businessHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessHeaderType }
     *     
     */
    public void setBusinessHeader(BusinessHeaderType value) {
        this.businessHeader = value;
    }

    /**
     * Gets the value of the technicalHeader property.
     * 
     * @return
     *     possible object is
     *     {@link TechnicalHeaderType }
     *     
     */
    public TechnicalHeaderType getTechnicalHeader() {
        return technicalHeader;
    }

    /**
     * Sets the value of the technicalHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link TechnicalHeaderType }
     *     
     */
    public void setTechnicalHeader(TechnicalHeaderType value) {
        this.technicalHeader = value;
    }

}
