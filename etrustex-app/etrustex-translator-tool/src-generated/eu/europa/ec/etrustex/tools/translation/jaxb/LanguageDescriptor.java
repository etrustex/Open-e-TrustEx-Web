//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.05.23 at 11:21:10 AM CEST 
//


package eu.europa.ec.etrustex.tools.translation.jaxb;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="language_reference" type="{}officialLanguage"/>
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
    "languageReference"
})
@XmlRootElement(name = "language_descriptor")
public class LanguageDescriptor {

    @XmlElement(name = "language_reference", required = true)
    protected String languageReference;

    /**
     * Gets the value of the languageReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageReference() {
        return languageReference;
    }

    /**
     * Sets the value of the languageReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageReference(String value) {
        this.languageReference = value;
    }

}
