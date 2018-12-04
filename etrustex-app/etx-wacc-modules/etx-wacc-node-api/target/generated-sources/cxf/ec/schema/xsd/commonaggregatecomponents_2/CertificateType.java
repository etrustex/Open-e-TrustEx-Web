
package ec.schema.xsd.commonaggregatecomponents_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.KeyUsageCodeType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PeriodType;
import un.unece.uncefact.data.specification.unqualifieddatatypesschemamodule._2.BinaryObjectType;
import un.unece.uncefact.data.specification.unqualifieddatatypesschemamodule._2.TextType;


/**
 * <p>Java class for CertificateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificateType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}KeyUsageCode"/&gt;
 *         &lt;element name="X509Certificate" type="{urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2}BinaryObjectType"/&gt;
 *         &lt;element name="X509SubjectName" type="{urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2}TextType" minOccurs="0"/&gt;
 *         &lt;element name="X509CRL" type="{urn:un:unece:uncefact:data:specification:UnqualifiedDataTypesSchemaModule:2}BinaryObjectType" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}ValidityPeriod" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateType", propOrder = {
    "keyUsageCode",
    "x509Certificate",
    "x509SubjectName",
    "x509CRL",
    "validityPeriod"
})
public class CertificateType {

    @XmlElement(name = "KeyUsageCode", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected KeyUsageCodeType keyUsageCode;
    @XmlElement(name = "X509Certificate", required = true)
    protected BinaryObjectType x509Certificate;
    @XmlElement(name = "X509SubjectName")
    protected TextType x509SubjectName;
    @XmlElement(name = "X509CRL")
    protected BinaryObjectType x509CRL;
    @XmlElement(name = "ValidityPeriod", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected PeriodType validityPeriod;

    /**
     * Gets the value of the keyUsageCode property.
     * 
     * @return
     *     possible object is
     *     {@link KeyUsageCodeType }
     *     
     */
    public KeyUsageCodeType getKeyUsageCode() {
        return keyUsageCode;
    }

    /**
     * Sets the value of the keyUsageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyUsageCodeType }
     *     
     */
    public void setKeyUsageCode(KeyUsageCodeType value) {
        this.keyUsageCode = value;
    }

    /**
     * Gets the value of the x509Certificate property.
     * 
     * @return
     *     possible object is
     *     {@link BinaryObjectType }
     *     
     */
    public BinaryObjectType getX509Certificate() {
        return x509Certificate;
    }

    /**
     * Sets the value of the x509Certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryObjectType }
     *     
     */
    public void setX509Certificate(BinaryObjectType value) {
        this.x509Certificate = value;
    }

    /**
     * Gets the value of the x509SubjectName property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getX509SubjectName() {
        return x509SubjectName;
    }

    /**
     * Sets the value of the x509SubjectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setX509SubjectName(TextType value) {
        this.x509SubjectName = value;
    }

    /**
     * Gets the value of the x509CRL property.
     * 
     * @return
     *     possible object is
     *     {@link BinaryObjectType }
     *     
     */
    public BinaryObjectType getX509CRL() {
        return x509CRL;
    }

    /**
     * Sets the value of the x509CRL property.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryObjectType }
     *     
     */
    public void setX509CRL(BinaryObjectType value) {
        this.x509CRL = value;
    }

    /**
     * Gets the value of the validityPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodType }
     *     
     */
    public PeriodType getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * Sets the value of the validityPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodType }
     *     
     */
    public void setValidityPeriod(PeriodType value) {
        this.validityPeriod = value;
    }

}
