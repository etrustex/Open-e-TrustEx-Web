
package ec.services.wsdl.documentwrapper_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.documentwrapperrequest_1.DocumentWrapperRequestType;


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
 *         &lt;element name="DocumentWrapperRequest" type="{ec:schema:xsd:DocumentWrapperRequest-1}DocumentWrapperRequestType"/&gt;
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
    "documentWrapperRequest"
})
@XmlRootElement(name = "DeleteDocumentWrapperRequestRequest")
public class DeleteDocumentWrapperRequestRequest {

    @XmlElement(name = "DocumentWrapperRequest", required = true)
    protected DocumentWrapperRequestType documentWrapperRequest;

    /**
     * Gets the value of the documentWrapperRequest property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentWrapperRequestType }
     *     
     */
    public DocumentWrapperRequestType getDocumentWrapperRequest() {
        return documentWrapperRequest;
    }

    /**
     * Sets the value of the documentWrapperRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentWrapperRequestType }
     *     
     */
    public void setDocumentWrapperRequest(DocumentWrapperRequestType value) {
        this.documentWrapperRequest = value;
    }

}
