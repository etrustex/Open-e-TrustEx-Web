
package ec.services.wsdl.documentwrapper_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.documentwrapper_1.DocumentWrapperType;


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
 *         &lt;element name="DocumentWrapper" type="{ec:schema:xsd:DocumentWrapper-1}DocumentWrapperType" minOccurs="0"/&gt;
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
    "documentWrapper"
})
@XmlRootElement(name = "RetrieveDocumentWrapperRequestResponse")
public class RetrieveDocumentWrapperRequestResponse {

    @XmlElement(name = "DocumentWrapper")
    protected DocumentWrapperType documentWrapper;

    /**
     * Gets the value of the documentWrapper property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentWrapperType }
     *     
     */
    public DocumentWrapperType getDocumentWrapper() {
        return documentWrapper;
    }

    /**
     * Sets the value of the documentWrapper property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentWrapperType }
     *     
     */
    public void setDocumentWrapper(DocumentWrapperType value) {
        this.documentWrapper = value;
    }

}
