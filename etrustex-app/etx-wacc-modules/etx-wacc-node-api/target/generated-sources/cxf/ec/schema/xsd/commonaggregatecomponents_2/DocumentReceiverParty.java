
package ec.schema.xsd.commonaggregatecomponents_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.EndpointIDType;


/**
 * <p>Java class for DocumentReceiverParty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentReceiverParty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}EndpointID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentReceiverParty", propOrder = {
    "endpointID"
})
public class DocumentReceiverParty {

    @XmlElement(name = "EndpointID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected EndpointIDType endpointID;

    /**
     * Gets the value of the endpointID property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointIDType }
     *     
     */
    public EndpointIDType getEndpointID() {
        return endpointID;
    }

    /**
     * Sets the value of the endpointID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointIDType }
     *     
     */
    public void setEndpointID(EndpointIDType value) {
        this.endpointID = value;
    }

}
