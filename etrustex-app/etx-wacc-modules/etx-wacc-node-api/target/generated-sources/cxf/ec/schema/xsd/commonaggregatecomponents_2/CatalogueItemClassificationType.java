
package ec.schema.xsd.commonaggregatecomponents_2;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CatalogueItemClassificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogueItemClassificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}ItemClassification"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}NumberOfItems"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogueItemClassificationType", propOrder = {
    "itemClassification",
    "numberOfItems"
})
public class CatalogueItemClassificationType {

    @XmlElement(name = "ItemClassification", required = true)
    protected ItemClassificationType itemClassification;
    @XmlElement(name = "NumberOfItems", required = true)
    protected BigDecimal numberOfItems;

    /**
     * Gets the value of the itemClassification property.
     * 
     * @return
     *     possible object is
     *     {@link ItemClassificationType }
     *     
     */
    public ItemClassificationType getItemClassification() {
        return itemClassification;
    }

    /**
     * Sets the value of the itemClassification property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemClassificationType }
     *     
     */
    public void setItemClassification(ItemClassificationType value) {
        this.itemClassification = value;
    }

    /**
     * Gets the value of the numberOfItems property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * Sets the value of the numberOfItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberOfItems(BigDecimal value) {
        this.numberOfItems = value;
    }

}
