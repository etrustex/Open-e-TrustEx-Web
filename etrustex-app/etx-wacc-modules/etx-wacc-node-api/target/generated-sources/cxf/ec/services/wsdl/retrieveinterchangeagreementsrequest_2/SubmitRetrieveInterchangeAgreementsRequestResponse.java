
package ec.services.wsdl.retrieveinterchangeagreementsrequest_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.retrieveinterchangeagreementsresponse_2.RetrieveInterchangeAgreementsResponseType;


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
 *         &lt;element name="RetrieveInterchangeAgreementsResponse" type="{ec:schema:xsd:RetrieveInterchangeAgreementsResponse-2}RetrieveInterchangeAgreementsResponseType"/&gt;
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
    "retrieveInterchangeAgreementsResponse"
})
@XmlRootElement(name = "SubmitRetrieveInterchangeAgreementsRequestResponse")
public class SubmitRetrieveInterchangeAgreementsRequestResponse {

    @XmlElement(name = "RetrieveInterchangeAgreementsResponse", required = true)
    protected RetrieveInterchangeAgreementsResponseType retrieveInterchangeAgreementsResponse;

    /**
     * Gets the value of the retrieveInterchangeAgreementsResponse property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveInterchangeAgreementsResponseType }
     *     
     */
    public RetrieveInterchangeAgreementsResponseType getRetrieveInterchangeAgreementsResponse() {
        return retrieveInterchangeAgreementsResponse;
    }

    /**
     * Sets the value of the retrieveInterchangeAgreementsResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveInterchangeAgreementsResponseType }
     *     
     */
    public void setRetrieveInterchangeAgreementsResponse(RetrieveInterchangeAgreementsResponseType value) {
        this.retrieveInterchangeAgreementsResponse = value;
    }

}
