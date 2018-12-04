
package ec.services.wsdl.retrieveinterchangeagreementsrequest_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.retrieveinterchangeagreementsrequest_2.RetrieveInterchangeAgreementsRequestType;


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
 *         &lt;element name="RetrieveInterchangeAgreementsRequest" type="{ec:schema:xsd:RetrieveInterchangeAgreementsRequest-2}RetrieveInterchangeAgreementsRequestType"/&gt;
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
    "retrieveInterchangeAgreementsRequest"
})
@XmlRootElement(name = "SubmitRetrieveInterchangeAgreementsRequestRequest")
public class SubmitRetrieveInterchangeAgreementsRequestRequest {

    @XmlElement(name = "RetrieveInterchangeAgreementsRequest", required = true)
    protected RetrieveInterchangeAgreementsRequestType retrieveInterchangeAgreementsRequest;

    /**
     * Gets the value of the retrieveInterchangeAgreementsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveInterchangeAgreementsRequestType }
     *     
     */
    public RetrieveInterchangeAgreementsRequestType getRetrieveInterchangeAgreementsRequest() {
        return retrieveInterchangeAgreementsRequest;
    }

    /**
     * Sets the value of the retrieveInterchangeAgreementsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveInterchangeAgreementsRequestType }
     *     
     */
    public void setRetrieveInterchangeAgreementsRequest(RetrieveInterchangeAgreementsRequestType value) {
        this.retrieveInterchangeAgreementsRequest = value;
    }

}
