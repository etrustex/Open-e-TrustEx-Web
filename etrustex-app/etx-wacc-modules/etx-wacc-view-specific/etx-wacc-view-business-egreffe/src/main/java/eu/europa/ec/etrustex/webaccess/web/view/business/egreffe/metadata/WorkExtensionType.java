//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Extension for a work.
 * 			
 * 
 * <p>Java class for WorkExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkExtensionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://publications.europa.eu/resource/core-metadata}t_work_extension_base">
 *       &lt;sequence>
 *         &lt;element name="category" type="{urn:eu:ec:cm:common:extensions:v11}ConstantType"/>
 *         &lt;element name="original_language_code" type="{http://publications.europa.eu/resource/languages/}t_language" minOccurs="0"/>
 *         &lt;element name="internal_number" type="{urn:eu:ec:cm:common:extensions:v11}WorkNumberType"/>
 *         &lt;element name="parent_work_reference" type="{http://publications.europa.eu/resource/core-metadata}t_work_ref" minOccurs="0"/>
 *         &lt;element name="sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="security_info" type="{urn:eu:ec:cm:common:extensions:v11}SecurityInfoType"/>
 *         &lt;element name="summary_title" type="{http://publications.europa.eu/resource/core-metadata}t_title"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkExtensionType", namespace = "urn:eu:ec:cm:common:extensions:v11", propOrder = {
    "category",
    "originalLanguageCode",
    "internalNumber",
    "parentWorkReference",
    "sequence",
    "securityInfo",
    "summaryTitle"
})
public class WorkExtensionType
    extends TWorkExtensionBase
{

    @XmlElement(required = true)
    protected String category;
    @XmlElement(name = "original_language_code")
    protected String originalLanguageCode;
    @XmlElement(name = "internal_number", required = true)
    protected WorkNumberType internalNumber;
    @XmlElement(name = "parent_work_reference")
    protected TWorkRef parentWorkReference;
    protected int sequence;
    @XmlElement(name = "security_info", required = true)
    protected SecurityInfoType securityInfo;
    @XmlElement(name = "summary_title", required = true)
    protected TTitle summaryTitle;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the originalLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalLanguageCode() {
        return originalLanguageCode;
    }

    /**
     * Sets the value of the originalLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalLanguageCode(String value) {
        this.originalLanguageCode = value;
    }

    /**
     * Gets the value of the internalNumber property.
     * 
     * @return
     *     possible object is
     *     {@link WorkNumberType }
     *     
     */
    public WorkNumberType getInternalNumber() {
        return internalNumber;
    }

    /**
     * Sets the value of the internalNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkNumberType }
     *     
     */
    public void setInternalNumber(WorkNumberType value) {
        this.internalNumber = value;
    }

    /**
     * Gets the value of the parentWorkReference property.
     * 
     * @return
     *     possible object is
     *     {@link TWorkRef }
     *     
     */
    public TWorkRef getParentWorkReference() {
        return parentWorkReference;
    }

    /**
     * Sets the value of the parentWorkReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link TWorkRef }
     *     
     */
    public void setParentWorkReference(TWorkRef value) {
        this.parentWorkReference = value;
    }

    /**
     * Gets the value of the sequence property.
     * 
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     */
    public void setSequence(int value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the securityInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityInfoType }
     *     
     */
    public SecurityInfoType getSecurityInfo() {
        return securityInfo;
    }

    /**
     * Sets the value of the securityInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityInfoType }
     *     
     */
    public void setSecurityInfo(SecurityInfoType value) {
        this.securityInfo = value;
    }

    /**
     * Gets the value of the summaryTitle property.
     * 
     * @return
     *     possible object is
     *     {@link TTitle }
     *     
     */
    public TTitle getSummaryTitle() {
        return summaryTitle;
    }

    /**
     * Sets the value of the summaryTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTitle }
     *     
     */
    public void setSummaryTitle(TTitle value) {
        this.summaryTitle = value;
    }

}
