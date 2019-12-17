//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for t_event_extension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_event_extension">
 *   &lt;complexContent>
 *     &lt;extension base="{http://publications.europa.eu/resource/core-metadata}t_event_extension_base">
 *       &lt;sequence>
 *         &lt;element name="decision" type="{http://publications.europa.eu/resource/core-metadata/nth}t_decision"/>
 *         &lt;element name="mode_decision" type="{http://publications.europa.eu/resource/core-metadata/nth}t_mode_decision"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://publications.europa.eu/resource/core-metadata}public"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_event_extension", propOrder = {
    "decision",
    "modeDecision"
})
public class TEventExtension
    extends TEventExtensionBase
{

    @XmlElement(required = true)
    protected TDecision decision;
    @XmlElement(name = "mode_decision", required = true)
    protected TModeDecision modeDecision;
    @XmlAttribute(name = "public", namespace = "http://publications.europa.eu/resource/core-metadata")
    protected Boolean _public;

    /**
     * Gets the value of the decision property.
     * 
     * @return
     *     possible object is
     *     {@link TDecision }
     *     
     */
    public TDecision getDecision() {
        return decision;
    }

    /**
     * Sets the value of the decision property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDecision }
     *     
     */
    public void setDecision(TDecision value) {
        this.decision = value;
    }

    /**
     * Gets the value of the modeDecision property.
     * 
     * @return
     *     possible object is
     *     {@link TModeDecision }
     *     
     */
    public TModeDecision getModeDecision() {
        return modeDecision;
    }

    /**
     * Sets the value of the modeDecision property.
     * 
     * @param value
     *     allowed object is
     *     {@link TModeDecision }
     *     
     */
    public void setModeDecision(TModeDecision value) {
        this.modeDecision = value;
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
