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
 * <p>Java class for TransmissionExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransmissionExtensionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://publications.europa.eu/resource/core-metadata-transmission}TransmissionExtensionBaseType">
 *       &lt;sequence>
 *         &lt;element name="sender" type="{urn:eu:ec:cm:common:extensions:v11}ContactType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransmissionExtensionType", namespace = "urn:eu:ec:cm:common:extensions:v11", propOrder = {
    "sender"
})
public class TransmissionExtensionType
    extends TransmissionExtensionBaseType
{

    @XmlElement(required = true)
    protected ContactType sender;

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     */
    public void setSender(ContactType value) {
        this.sender = value;
    }

}
