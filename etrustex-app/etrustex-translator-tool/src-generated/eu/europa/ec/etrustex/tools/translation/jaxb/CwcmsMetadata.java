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
 *         &lt;element ref="{}r_object_id"/>
 *         &lt;element ref="{}rendition_url"/>
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
    "rObjectId",
    "renditionUrl"
})
@XmlRootElement(name = "cwcms_metadata")
public class CwcmsMetadata {

    @XmlElement(name = "r_object_id", required = true)
    protected String rObjectId;
    @XmlElement(name = "rendition_url", required = true)
    protected RenditionUrl renditionUrl;

    /**
     * Gets the value of the rObjectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRObjectId() {
        return rObjectId;
    }

    /**
     * Sets the value of the rObjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRObjectId(String value) {
        this.rObjectId = value;
    }

    /**
     * Gets the value of the renditionUrl property.
     * 
     * @return
     *     possible object is
     *     {@link RenditionUrl }
     *     
     */
    public RenditionUrl getRenditionUrl() {
        return renditionUrl;
    }

    /**
     * Sets the value of the renditionUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenditionUrl }
     *     
     */
    public void setRenditionUrl(RenditionUrl value) {
        this.renditionUrl = value;
    }

}
