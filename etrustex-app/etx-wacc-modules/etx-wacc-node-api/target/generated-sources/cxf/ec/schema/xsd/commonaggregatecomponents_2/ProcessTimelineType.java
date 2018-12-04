
package ec.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ParentDocumentIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ParentDocumentTypeCodeType;


/**
 * <p>Java class for ProcessTimelineType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessTimelineType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ParentDocumentID"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ParentDocumentTypeCode"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}DocumentTimeline" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessTimelineType", propOrder = {
    "parentDocumentID",
    "parentDocumentTypeCode",
    "documentTimeline"
})
public class ProcessTimelineType {

    @XmlElement(name = "ParentDocumentID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected ParentDocumentIDType parentDocumentID;
    @XmlElement(name = "ParentDocumentTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected ParentDocumentTypeCodeType parentDocumentTypeCode;
    @XmlElement(name = "DocumentTimeline", required = true)
    protected List<DocumentTimelineType> documentTimeline;

    /**
     * Gets the value of the parentDocumentID property.
     * 
     * @return
     *     possible object is
     *     {@link ParentDocumentIDType }
     *     
     */
    public ParentDocumentIDType getParentDocumentID() {
        return parentDocumentID;
    }

    /**
     * Sets the value of the parentDocumentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentDocumentIDType }
     *     
     */
    public void setParentDocumentID(ParentDocumentIDType value) {
        this.parentDocumentID = value;
    }

    /**
     * Gets the value of the parentDocumentTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link ParentDocumentTypeCodeType }
     *     
     */
    public ParentDocumentTypeCodeType getParentDocumentTypeCode() {
        return parentDocumentTypeCode;
    }

    /**
     * Sets the value of the parentDocumentTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParentDocumentTypeCodeType }
     *     
     */
    public void setParentDocumentTypeCode(ParentDocumentTypeCodeType value) {
        this.parentDocumentTypeCode = value;
    }

    /**
     * Gets the value of the documentTimeline property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentTimeline property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentTimeline().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentTimelineType }
     * 
     * 
     */
    public List<DocumentTimelineType> getDocumentTimeline() {
        if (documentTimeline == null) {
            documentTimeline = new ArrayList<DocumentTimelineType>();
        }
        return this.documentTimeline;
    }

}
