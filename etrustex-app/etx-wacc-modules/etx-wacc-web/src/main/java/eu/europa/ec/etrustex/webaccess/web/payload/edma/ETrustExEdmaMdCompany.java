
package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ETrustExEdmaMdCompany complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ETrustExEdmaMdCompany">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eMail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nationalRegNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactPerson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ETrustExEdmaMdCompany", namespace = "urn:eu:europa:ec:comp:etrustex", propOrder = {
    "company",
    "street",
    "city",
    "phone",
    "eMail",
    "nationalRegNumber",
    "country",
    "position",
    "zip",
    "contactPerson"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ETrustExEdmaMdCompany {

    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String company;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String street;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String city;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String phone;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String eMail;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String nationalRegNumber;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String country;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String position;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String zip;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String contactPerson;

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPhone(String value) {
        this.phone = value;
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
     * Gets the value of the nationalRegNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getNationalRegNumber() {
        return nationalRegNumber;
    }

    /**
     * Sets the value of the nationalRegNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNationalRegNumber(String value) {
        this.nationalRegNumber = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * Gets the value of the contactPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * Sets the value of the contactPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setContactPerson(String value) {
        this.contactPerson = value;
    }

}
