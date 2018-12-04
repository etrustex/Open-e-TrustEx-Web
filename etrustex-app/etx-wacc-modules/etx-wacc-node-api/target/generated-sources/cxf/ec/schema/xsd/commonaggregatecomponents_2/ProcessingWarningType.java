
package ec.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.FailedAssertType;


/**
 * <p>Java class for ProcessingWarningType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessingWarningType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}FailedAssert" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessingWarningType", propOrder = {
    "failedAssert"
})
public class ProcessingWarningType {

    @XmlElement(name = "FailedAssert", namespace = "ec:schema:xsd:CommonBasicComponents-1")
    protected List<FailedAssertType> failedAssert;

    /**
     * Gets the value of the failedAssert property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the failedAssert property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFailedAssert().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FailedAssertType }
     * 
     * 
     */
    public List<FailedAssertType> getFailedAssert() {
        if (failedAssert == null) {
            failedAssert = new ArrayList<FailedAssertType>();
        }
        return this.failedAssert;
    }

}
