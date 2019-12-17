
package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reqForInfoNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inAttentionOf" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inboundDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outboundMetaData" type="{urn:eu:europa:ec:comp:etrustex}ETrustExEdmaMdOutbound"/>
 *         &lt;element name="inboundMetaData" type="{urn:eu:europa:ec:comp:etrustex}ETrustExEdmaMdInbound"/>
 *         &lt;element name="outboundDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "subject",
    "reqForInfoNumber",
    "fileNumber",
    "inAttentionOf",
    "inboundDate",
    "language",
    "outboundMetaData",
    "inboundMetaData",
    "outboundDate",
    "comment"
})
@XmlRootElement(name = "ETrustExEdmaMd", namespace = "urn:eu:europa:ec:comp:etrustex")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ETrustExEdmaMd {

    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String subject;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String reqForInfoNumber;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String fileNumber;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String inAttentionOf;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar inboundDate;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String language;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ETrustExEdmaMdOutbound outboundMetaData;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected ETrustExEdmaMdInbound inboundMetaData;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar outboundDate;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String comment;

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the reqForInfoNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getReqForInfoNumber() {
        return reqForInfoNumber;
    }

    /**
     * Sets the value of the reqForInfoNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setReqForInfoNumber(String value) {
        this.reqForInfoNumber = value;
    }

    /**
     * Gets the value of the fileNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getFileNumber() {
        return fileNumber;
    }

    /**
     * Sets the value of the fileNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFileNumber(String value) {
        this.fileNumber = value;
    }

    /**
     * Gets the value of the inAttentionOf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getInAttentionOf() {
        return inAttentionOf;
    }

    /**
     * Sets the value of the inAttentionOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setInAttentionOf(String value) {
        this.inAttentionOf = value;
    }

    /**
     * Gets the value of the inboundDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getInboundDate() {
        return inboundDate;
    }

    /**
     * Sets the value of the inboundDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setInboundDate(XMLGregorianCalendar value) {
        this.inboundDate = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the outboundMetaData property.
     * 
     * @return
     *     possible object is
     *     {@link ETrustExEdmaMdOutbound }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ETrustExEdmaMdOutbound getOutboundMetaData() {
        return outboundMetaData;
    }

    /**
     * Sets the value of the outboundMetaData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETrustExEdmaMdOutbound }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOutboundMetaData(ETrustExEdmaMdOutbound value) {
        this.outboundMetaData = value;
    }

    /**
     * Gets the value of the inboundMetaData property.
     * 
     * @return
     *     possible object is
     *     {@link ETrustExEdmaMdInbound }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public ETrustExEdmaMdInbound getInboundMetaData() {
        return inboundMetaData;
    }

    /**
     * Sets the value of the inboundMetaData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ETrustExEdmaMdInbound }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setInboundMetaData(ETrustExEdmaMdInbound value) {
        this.inboundMetaData = value;
    }

    /**
     * Gets the value of the outboundDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getOutboundDate() {
        return outboundDate;
    }

    /**
     * Sets the value of the outboundDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOutboundDate(XMLGregorianCalendar value) {
        this.outboundDate = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setComment(String value) {
        this.comment = value;
    }

}
