
package ec.services.wsdl.documentbundle_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.documentbundle_1.DocumentBundleType;


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
 *         &lt;element name="DocumentBundle" type="{ec:schema:xsd:DocumentBundle-1}DocumentBundleType"/&gt;
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
    "documentBundle"
})
@XmlRootElement(name = "SubmitDocumentBundleRequest")
public class SubmitDocumentBundleRequest {

    @XmlElement(name = "DocumentBundle", required = true)
    protected DocumentBundleType documentBundle;

    /**
     * Gets the value of the documentBundle property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentBundleType }
     *     
     */
    public DocumentBundleType getDocumentBundle() {
        return documentBundle;
    }

    /**
     * Sets the value of the documentBundle property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentBundleType }
     *     
     */
    public void setDocumentBundle(DocumentBundleType value) {
        this.documentBundle = value;
    }

}
