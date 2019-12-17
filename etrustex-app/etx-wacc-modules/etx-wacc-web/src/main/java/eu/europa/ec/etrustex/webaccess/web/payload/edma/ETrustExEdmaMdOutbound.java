
package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ETrustExEdmaMdOutbound complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ETrustExEdmaMdOutbound">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="registrationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ETrustExEdmaMdOutbound", namespace = "urn:eu:europa:ec:comp:etrustex", propOrder = {
    "to",
    "registrationNumber"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ETrustExEdmaMdOutbound {

    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String to;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String registrationNumber;

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the registrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets the value of the registrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setRegistrationNumber(String value) {
        this.registrationNumber = value;
    }

}
