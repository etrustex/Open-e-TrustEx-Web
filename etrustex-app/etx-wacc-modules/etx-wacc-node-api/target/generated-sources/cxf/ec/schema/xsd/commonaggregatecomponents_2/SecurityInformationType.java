
package ec.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.AvailabilityLevelCodeType;
import ec.schema.xsd.commonbasiccomponents_1.ConfidentialityLevelCodeType;
import ec.schema.xsd.commonbasiccomponents_1.IntegrityLevelCodeType;


/**
 * <p>Java class for SecurityInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecurityInformationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}ConfidentialityLevelCode"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}IntegrityLevelCode"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}AvailabilityLevelCode"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}SenderPartyCertificate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}ReceiverPartyCertificate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityInformationType", propOrder = {
    "confidentialityLevelCode",
    "integrityLevelCode",
    "availabilityLevelCode",
    "senderPartyCertificate",
    "receiverPartyCertificate"
})
public class SecurityInformationType {

    @XmlElement(name = "ConfidentialityLevelCode", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected ConfidentialityLevelCodeType confidentialityLevelCode;
    @XmlElement(name = "IntegrityLevelCode", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected IntegrityLevelCodeType integrityLevelCode;
    @XmlElement(name = "AvailabilityLevelCode", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected AvailabilityLevelCodeType availabilityLevelCode;
    @XmlElement(name = "SenderPartyCertificate")
    protected List<CertificateType> senderPartyCertificate;
    @XmlElement(name = "ReceiverPartyCertificate")
    protected List<CertificateType> receiverPartyCertificate;

    /**
     * Gets the value of the confidentialityLevelCode property.
     * 
     * @return
     *     possible object is
     *     {@link ConfidentialityLevelCodeType }
     *     
     */
    public ConfidentialityLevelCodeType getConfidentialityLevelCode() {
        return confidentialityLevelCode;
    }

    /**
     * Sets the value of the confidentialityLevelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfidentialityLevelCodeType }
     *     
     */
    public void setConfidentialityLevelCode(ConfidentialityLevelCodeType value) {
        this.confidentialityLevelCode = value;
    }

    /**
     * Gets the value of the integrityLevelCode property.
     * 
     * @return
     *     possible object is
     *     {@link IntegrityLevelCodeType }
     *     
     */
    public IntegrityLevelCodeType getIntegrityLevelCode() {
        return integrityLevelCode;
    }

    /**
     * Sets the value of the integrityLevelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegrityLevelCodeType }
     *     
     */
    public void setIntegrityLevelCode(IntegrityLevelCodeType value) {
        this.integrityLevelCode = value;
    }

    /**
     * Gets the value of the availabilityLevelCode property.
     * 
     * @return
     *     possible object is
     *     {@link AvailabilityLevelCodeType }
     *     
     */
    public AvailabilityLevelCodeType getAvailabilityLevelCode() {
        return availabilityLevelCode;
    }

    /**
     * Sets the value of the availabilityLevelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailabilityLevelCodeType }
     *     
     */
    public void setAvailabilityLevelCode(AvailabilityLevelCodeType value) {
        this.availabilityLevelCode = value;
    }

    /**
     * Gets the value of the senderPartyCertificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the senderPartyCertificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSenderPartyCertificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertificateType }
     * 
     * 
     */
    public List<CertificateType> getSenderPartyCertificate() {
        if (senderPartyCertificate == null) {
            senderPartyCertificate = new ArrayList<CertificateType>();
        }
        return this.senderPartyCertificate;
    }

    /**
     * Gets the value of the receiverPartyCertificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the receiverPartyCertificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReceiverPartyCertificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertificateType }
     * 
     * 
     */
    public List<CertificateType> getReceiverPartyCertificate() {
        if (receiverPartyCertificate == null) {
            receiverPartyCertificate = new ArrayList<CertificateType>();
        }
        return this.receiverPartyCertificate;
    }

}
