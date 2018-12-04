
package ec.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.VersionIDType;
import oasis.names.specification.ubl.schema.xsd.signatureaggregatecomponents_2.SignatureInformationType;


/**
 * <p>Java class for TechnicalHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TechnicalHeaderType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}VersionID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}PayloadDigestValue" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}PayloadDigestMethod" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}PayloadCanonicalizationMethod" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:SignatureAggregateComponents-2}SignatureInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TechnicalHeaderType", propOrder = {
    "versionID",
    "payloadDigestValue",
    "payloadDigestMethod",
    "payloadCanonicalizationMethod",
    "signatureInformation"
})
public class TechnicalHeaderType {

    @XmlElement(name = "VersionID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<VersionIDType> versionID;
    @XmlElement(name = "PayloadDigestValue")
    protected DigestValueType payloadDigestValue;
    @XmlElement(name = "PayloadDigestMethod")
    protected DigestMethodType payloadDigestMethod;
    @XmlElement(name = "PayloadCanonicalizationMethod")
    protected CanonicalizationMethodType payloadCanonicalizationMethod;
    @XmlElement(name = "SignatureInformation", namespace = "urn:oasis:names:specification:ubl:schema:xsd:SignatureAggregateComponents-2")
    protected List<SignatureInformationType> signatureInformation;

    /**
     * Gets the value of the versionID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the versionID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVersionID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VersionIDType }
     * 
     * 
     */
    public List<VersionIDType> getVersionID() {
        if (versionID == null) {
            versionID = new ArrayList<VersionIDType>();
        }
        return this.versionID;
    }

    /**
     * Gets the value of the payloadDigestValue property.
     * 
     * @return
     *     possible object is
     *     {@link DigestValueType }
     *     
     */
    public DigestValueType getPayloadDigestValue() {
        return payloadDigestValue;
    }

    /**
     * Sets the value of the payloadDigestValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link DigestValueType }
     *     
     */
    public void setPayloadDigestValue(DigestValueType value) {
        this.payloadDigestValue = value;
    }

    /**
     * Gets the value of the payloadDigestMethod property.
     * 
     * @return
     *     possible object is
     *     {@link DigestMethodType }
     *     
     */
    public DigestMethodType getPayloadDigestMethod() {
        return payloadDigestMethod;
    }

    /**
     * Sets the value of the payloadDigestMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link DigestMethodType }
     *     
     */
    public void setPayloadDigestMethod(DigestMethodType value) {
        this.payloadDigestMethod = value;
    }

    /**
     * Gets the value of the payloadCanonicalizationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link CanonicalizationMethodType }
     *     
     */
    public CanonicalizationMethodType getPayloadCanonicalizationMethod() {
        return payloadCanonicalizationMethod;
    }

    /**
     * Sets the value of the payloadCanonicalizationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CanonicalizationMethodType }
     *     
     */
    public void setPayloadCanonicalizationMethod(CanonicalizationMethodType value) {
        this.payloadCanonicalizationMethod = value;
    }

    /**
     * Gets the value of the signatureInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signatureInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignatureInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignatureInformationType }
     * 
     * 
     */
    public List<SignatureInformationType> getSignatureInformation() {
        if (signatureInformation == null) {
            signatureInformation = new ArrayList<SignatureInformationType>();
        }
        return this.signatureInformation;
    }

}
