//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for GenericIdentificationNumberType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericIdentificationNumberType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{urn:eu:ec:cm:common:extensions:v11}SecurableConstantType"/>
 *         &lt;element name="year" type="{urn:eu:ec:cm:common:extensions:v11}YearType"/>
 *         &lt;element name="number" type="{urn:eu:ec:cm:common:extensions:v11}NumberType"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://publications.europa.eu/resource/core-metadata}public"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericIdentificationNumberType", namespace = "urn:eu:ec:cm:common:extensions:v11", propOrder = {
    "code",
    "year",
    "number"
})
@XmlSeeAlso({
    WorkNumberType.class,
    WorkflowNumberType.class
})
public class GenericIdentificationNumberType {

    @XmlElement(required = true)
    protected SecurableConstantType code;
    @XmlElement(required = true)
    protected YearType year;
    @XmlElement(required = true)
    protected NumberType number;
    @XmlAttribute(name = "public", namespace = "http://publications.europa.eu/resource/core-metadata")
    protected Boolean _public;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link SecurableConstantType }
     *     
     */
    public SecurableConstantType getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurableConstantType }
     *     
     */
    public void setCode(SecurableConstantType value) {
        this.code = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link YearType }
     *     
     */
    public YearType getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link YearType }
     *     
     */
    public void setYear(YearType value) {
        this.year = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link NumberType }
     *     
     */
    public NumberType getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumberType }
     *     
     */
    public void setNumber(NumberType value) {
        this.number = value;
    }

    /**
     * Gets the value of the public property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPublic() {
        if (_public == null) {
            return true;
        } else {
            return _public;
        }
    }

    /**
     * Sets the value of the public property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPublic(Boolean value) {
        this._public = value;
    }

}
