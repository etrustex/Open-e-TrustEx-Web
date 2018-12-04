
package ec.services.wsdl.applicationresponse_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.specification.ubl.schema.xsd.applicationresponse_2.ApplicationResponseType;


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
 *         &lt;element name="ApplicationResponse" type="{urn:oasis:names:specification:ubl:schema:xsd:ApplicationResponse-2}ApplicationResponseType"/&gt;
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
    "applicationResponse"
})
@XmlRootElement(name = "SubmitApplicationResponseRequest")
public class SubmitApplicationResponseRequest {

    @XmlElement(name = "ApplicationResponse", required = true)
    protected ApplicationResponseType applicationResponse;

    /**
     * Gets the value of the applicationResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationResponseType }
     *     
     */
    public ApplicationResponseType getApplicationResponse() {
        return applicationResponse;
    }

    /**
     * Sets the value of the applicationResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationResponseType }
     *     
     */
    public void setApplicationResponse(ApplicationResponseType value) {
        this.applicationResponse = value;
    }

}
