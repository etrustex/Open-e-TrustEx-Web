//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.05.23 at 11:21:10 AM CEST 
//


package eu.europa.ec.etrustex.tools.translation.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for content_ref_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="content_ref_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ref_date" type="{}date_type"/>
 *         &lt;element name="ref_contact_mail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ref_links">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ref_link_r" type="{}link_r_type"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ref_target_audience" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="ref_organisation" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="ref_geographic_region" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="ref_full_address" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="ref_author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ref_speaker" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "content_ref_type", propOrder = {
    "refDate",
    "refContactMail",
    "refLinks",
    "refTargetAudience",
    "refOrganisation",
    "refGeographicRegion",
    "refFullAddress",
    "refAuthor",
    "refSpeaker"
})
public class ContentRefType {

    @XmlElement(name = "ref_date", required = true)
    protected String refDate;
    @XmlElement(name = "ref_contact_mail", required = true)
    protected String refContactMail;
    @XmlElement(name = "ref_links", required = true)
    protected ContentRefType.RefLinks refLinks;
    @XmlElement(name = "ref_target_audience", required = true)
    protected Object refTargetAudience;
    @XmlElement(name = "ref_organisation")
    protected Object refOrganisation;
    @XmlElement(name = "ref_geographic_region")
    protected Object refGeographicRegion;
    @XmlElement(name = "ref_full_address")
    protected Object refFullAddress;
    @XmlElement(name = "ref_author")
    protected String refAuthor;
    @XmlElement(name = "ref_speaker")
    protected String refSpeaker;

    /**
     * Gets the value of the refDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefDate() {
        return refDate;
    }

    /**
     * Sets the value of the refDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefDate(String value) {
        this.refDate = value;
    }

    /**
     * Gets the value of the refContactMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefContactMail() {
        return refContactMail;
    }

    /**
     * Sets the value of the refContactMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefContactMail(String value) {
        this.refContactMail = value;
    }

    /**
     * Gets the value of the refLinks property.
     * 
     * @return
     *     possible object is
     *     {@link ContentRefType.RefLinks }
     *     
     */
    public ContentRefType.RefLinks getRefLinks() {
        return refLinks;
    }

    /**
     * Sets the value of the refLinks property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentRefType.RefLinks }
     *     
     */
    public void setRefLinks(ContentRefType.RefLinks value) {
        this.refLinks = value;
    }

    /**
     * Gets the value of the refTargetAudience property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRefTargetAudience() {
        return refTargetAudience;
    }

    /**
     * Sets the value of the refTargetAudience property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRefTargetAudience(Object value) {
        this.refTargetAudience = value;
    }

    /**
     * Gets the value of the refOrganisation property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRefOrganisation() {
        return refOrganisation;
    }

    /**
     * Sets the value of the refOrganisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRefOrganisation(Object value) {
        this.refOrganisation = value;
    }

    /**
     * Gets the value of the refGeographicRegion property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRefGeographicRegion() {
        return refGeographicRegion;
    }

    /**
     * Sets the value of the refGeographicRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRefGeographicRegion(Object value) {
        this.refGeographicRegion = value;
    }

    /**
     * Gets the value of the refFullAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRefFullAddress() {
        return refFullAddress;
    }

    /**
     * Sets the value of the refFullAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRefFullAddress(Object value) {
        this.refFullAddress = value;
    }

    /**
     * Gets the value of the refAuthor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefAuthor() {
        return refAuthor;
    }

    /**
     * Sets the value of the refAuthor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefAuthor(String value) {
        this.refAuthor = value;
    }

    /**
     * Gets the value of the refSpeaker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefSpeaker() {
        return refSpeaker;
    }

    /**
     * Sets the value of the refSpeaker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefSpeaker(String value) {
        this.refSpeaker = value;
    }


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
     *         &lt;element name="ref_link_r" type="{}link_r_type"/>
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
        "refLinkR"
    })
    public static class RefLinks {

        @XmlElement(name = "ref_link_r", required = true)
        protected LinkRType refLinkR;

        /**
         * Gets the value of the refLinkR property.
         * 
         * @return
         *     possible object is
         *     {@link LinkRType }
         *     
         */
        public LinkRType getRefLinkR() {
            return refLinkR;
        }

        /**
         * Sets the value of the refLinkR property.
         * 
         * @param value
         *     allowed object is
         *     {@link LinkRType }
         *     
         */
        public void setRefLinkR(LinkRType value) {
            this.refLinkR = value;
        }

    }

}
