
package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ETrustExEdmaMdInbound complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ETrustExEdmaMdInbound">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transferStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{urn:eu:europa:ec:comp:etrustex}documents"/>
 *         &lt;element name="onBehalfOf" type="{urn:eu:europa:ec:comp:etrustex}ETrustExEdmaMdCompany"/>
 *         &lt;element name="sender" type="{urn:eu:europa:ec:comp:etrustex}ETrustExEdmaMdCompany"/>
 *         &lt;element name="eMail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="yourReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instrument" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="distributionList" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ETrustExEdmaMdInbound", namespace = "urn:eu:europa:ec:comp:etrustex", propOrder = {
    "transferStatus",
    "documents",
    "onBehalfOf",
    "sender",
    "eMail",
    "from",
    "yourReference",
    "instrument",
    "distributionList"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ETrustExEdmaMdInbound {

    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String transferStatus;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Documents documents;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ETrustExEdmaMdCompany onBehalfOf;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ETrustExEdmaMdCompany sender;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String eMail;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String from;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String yourReference;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String instrument;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String distributionList;

    /**
     * Gets the value of the transferStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getTransferStatus() {
        return transferStatus;
    }

    /**
     * Sets the value of the transferStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTransferStatus(String value) {
        this.transferStatus = value;
    }

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link Documents }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Documents getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link Documents }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDocuments(Documents value) {
        this.documents = value;
    }

    /**
     * Gets the value of the onBehalfOf property.
     * 
     * @return
     *     possible object is
     *     {@link ETrustExEdmaMdCompany }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ETrustExEdmaMdCompany getOnBehalfOf() {
        return onBehalfOf;
    }

    /**
     * Sets the value of the onBehalfOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETrustExEdmaMdCompany }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOnBehalfOf(ETrustExEdmaMdCompany value) {
        this.onBehalfOf = value;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link ETrustExEdmaMdCompany }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ETrustExEdmaMdCompany getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETrustExEdmaMdCompany }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSender(ETrustExEdmaMdCompany value) {
        this.sender = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setEMail(String value) {
        this.eMail = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFrom(String value) {
        this.from = value;
    }

    /**
     * Gets the value of the yourReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getYourReference() {
        return yourReference;
    }

    /**
     * Sets the value of the yourReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setYourReference(String value) {
        this.yourReference = value;
    }

    /**
     * Gets the value of the instrument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getInstrument() {
        return instrument;
    }

    /**
     * Sets the value of the instrument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setInstrument(String value) {
        this.instrument = value;
    }

    /**
     * Gets the value of the distributionList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getDistributionList() {
        return distributionList;
    }

    /**
     * Sets the value of the distributionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDistributionList(String value) {
        this.distributionList = value;
    }

}
