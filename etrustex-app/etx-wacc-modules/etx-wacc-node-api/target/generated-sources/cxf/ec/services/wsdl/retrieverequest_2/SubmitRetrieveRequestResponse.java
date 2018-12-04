
package ec.services.wsdl.retrieverequest_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.retrieveresponse_2.RetrieveResponseType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RetrieveResponse" type="{ec:schema:xsd:RetrieveResponse-2.1}RetrieveResponseType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "retrieveResponse"
})
@XmlRootElement(name = "SubmitRetrieveRequestResponse")
public class SubmitRetrieveRequestResponse {

    @XmlElement(name = "RetrieveResponse", required = true)
    protected RetrieveResponseType retrieveResponse;

    /**
     * Gets the value of the retrieveResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveResponseType }
     *     
     */
    public RetrieveResponseType getRetrieveResponse() {
        return retrieveResponse;
    }

    /**
     * Sets the value of the retrieveResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveResponseType }
     *     
     */
    public void setRetrieveResponse(RetrieveResponseType value) {
        this.retrieveResponse = value;
    }

}
