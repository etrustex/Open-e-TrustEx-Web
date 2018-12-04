
package ec.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DocumentReferenceResponseRelatedDocsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocumentReferenceResponseRelatedDocsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}DocumentReferenceResponse"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}RelatedDocument" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentReferenceResponseRelatedDocsType", propOrder = {
    "documentReferenceResponse",
    "relatedDocument"
})
public class DocumentReferenceResponseRelatedDocsType {

    @XmlElement(name = "DocumentReferenceResponse", required = true)
    protected DocumentReferenceResponseType documentReferenceResponse;
    @XmlElement(name = "RelatedDocument")
    protected List<DocumentReferenceResponseType> relatedDocument;

    /**
     * Gets the value of the documentReferenceResponse property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentReferenceResponseType }
     *     
     */
    public DocumentReferenceResponseType getDocumentReferenceResponse() {
        return documentReferenceResponse;
    }

    /**
     * Sets the value of the documentReferenceResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentReferenceResponseType }
     *     
     */
    public void setDocumentReferenceResponse(DocumentReferenceResponseType value) {
        this.documentReferenceResponse = value;
    }

    /**
     * Gets the value of the relatedDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentReferenceResponseType }
     * 
     * 
     */
    public List<DocumentReferenceResponseType> getRelatedDocument() {
        if (relatedDocument == null) {
            relatedDocument = new ArrayList<DocumentReferenceResponseType>();
        }
        return this.relatedDocument;
    }

}
