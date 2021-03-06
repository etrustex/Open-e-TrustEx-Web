//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				Extension for an expression.
 * 			
 * 
 * <p>Java class for ExpressionExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpressionExtensionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://publications.europa.eu/resource/core-metadata}t_expression_extension_base">
 *       &lt;sequence>
 *         &lt;element name="language_authentic" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="estimated_transmission_date_time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpressionExtensionType", namespace = "urn:eu:ec:cm:common:extensions:v11", propOrder = {
    "languageAuthentic",
    "estimatedTransmissionDateTime"
})
public class ExpressionExtensionType
    extends TExpressionExtensionBase
{

    @XmlElement(name = "language_authentic")
    protected Boolean languageAuthentic;
    @XmlElement(name = "estimated_transmission_date_time")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar estimatedTransmissionDateTime;

    /**
     * Gets the value of the languageAuthentic property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLanguageAuthentic() {
        return languageAuthentic;
    }

    /**
     * Sets the value of the languageAuthentic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLanguageAuthentic(Boolean value) {
        this.languageAuthentic = value;
    }

    /**
     * Gets the value of the estimatedTransmissionDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEstimatedTransmissionDateTime() {
        return estimatedTransmissionDateTime;
    }

    /**
     * Sets the value of the estimatedTransmissionDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEstimatedTransmissionDateTime(XMLGregorianCalendar value) {
        this.estimatedTransmissionDateTime = value;
    }

}
