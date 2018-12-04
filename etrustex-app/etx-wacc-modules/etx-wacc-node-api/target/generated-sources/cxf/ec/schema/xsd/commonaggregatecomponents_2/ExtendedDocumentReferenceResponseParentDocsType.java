
package ec.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtendedDocumentReferenceResponseParentDocsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtendedDocumentReferenceResponseParentDocsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}ExtendedDocumentReferenceResponse"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}ParentDocument" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendedDocumentReferenceResponseParentDocsType", propOrder = {
    "extendedDocumentReferenceResponse",
    "parentDocument"
})
public class ExtendedDocumentReferenceResponseParentDocsType {

    @XmlElement(name = "ExtendedDocumentReferenceResponse", required = true)
    protected ExtendedDocumentReferenceResponseType extendedDocumentReferenceResponse;
    @XmlElement(name = "ParentDocument")
    protected List<DocumentReferenceResponseType> parentDocument;

    /**
     * Gets the value of the extendedDocumentReferenceResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ExtendedDocumentReferenceResponseType }
     *     
     */
    public ExtendedDocumentReferenceResponseType getExtendedDocumentReferenceResponse() {
        return extendedDocumentReferenceResponse;
    }

    /**
     * Sets the value of the extendedDocumentReferenceResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendedDocumentReferenceResponseType }
     *     
     */
    public void setExtendedDocumentReferenceResponse(ExtendedDocumentReferenceResponseType value) {
        this.extendedDocumentReferenceResponse = value;
    }

    /**
     * Gets the value of the parentDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parentDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParentDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentReferenceResponseType }
     * 
     * 
     */
    public List<DocumentReferenceResponseType> getParentDocument() {
        if (parentDocument == null) {
            parentDocument = new ArrayList<DocumentReferenceResponseType>();
        }
        return this.parentDocument;
    }

}
