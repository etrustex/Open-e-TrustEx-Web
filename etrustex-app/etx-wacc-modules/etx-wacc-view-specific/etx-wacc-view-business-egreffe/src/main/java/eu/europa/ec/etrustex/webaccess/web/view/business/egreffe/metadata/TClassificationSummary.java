//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for t_classification_summary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_classification_summary">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="master" use="required" type="{http://publications.europa.eu/resource/core-metadata/nth}t_yesno_cnt" />
 *       &lt;attribute name="anchor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute ref="{http://publications.europa.eu/resource/core-metadata}public"/>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_classification_summary", propOrder = {
    "value"
})
public class TClassificationSummary {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "master", required = true)
    protected TYesnoCnt master;
    @XmlAttribute(name = "anchor", required = true)
    protected String anchor;
    @XmlAttribute(name = "public", namespace = "http://publications.europa.eu/resource/core-metadata")
    protected Boolean _public;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the master property.
     * 
     * @return
     *     possible object is
     *     {@link TYesnoCnt }
     *     
     */
    public TYesnoCnt getMaster() {
        return master;
    }

    /**
     * Sets the value of the master property.
     * 
     * @param value
     *     allowed object is
     *     {@link TYesnoCnt }
     *     
     */
    public void setMaster(TYesnoCnt value) {
        this.master = value;
    }

    /**
     * Gets the value of the anchor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchor() {
        return anchor;
    }

    /**
     * Sets the value of the anchor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchor(String value) {
        this.anchor = value;
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
