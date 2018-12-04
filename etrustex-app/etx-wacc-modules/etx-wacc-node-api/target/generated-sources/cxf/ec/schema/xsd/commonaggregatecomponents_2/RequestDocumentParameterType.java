
package ec.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.RetrieveExtensionsIndicatorType;
import ec.schema.xsd.commonbasiccomponents_1.RetrievedDocumentsIndicatorType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PeriodType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeCodeType;


/**
 * <p>Java class for RequestDocumentParameterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestDocumentParameterType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}Period" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}DocumentTypeCode" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}OriginatorDocumentReference" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}RetrievedDocumentsIndicator" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}RetrieveExtensionsIndicator" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}SenderParty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}ReceiverParty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestDocumentParameterType", propOrder = {
    "period",
    "documentTypeCode",
    "originatorDocumentReference",
    "retrievedDocumentsIndicator",
    "retrieveExtensionsIndicator",
    "senderParty",
    "receiverParty"
})
public class RequestDocumentParameterType {

    @XmlElement(name = "Period", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected PeriodType period;
    @XmlElement(name = "DocumentTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<DocumentTypeCodeType> documentTypeCode;
    @XmlElement(name = "OriginatorDocumentReference", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected DocumentReferenceType originatorDocumentReference;
    @XmlElement(name = "RetrievedDocumentsIndicator", namespace = "ec:schema:xsd:CommonBasicComponents-1")
    protected List<RetrievedDocumentsIndicatorType> retrievedDocumentsIndicator;
    @XmlElement(name = "RetrieveExtensionsIndicator", namespace = "ec:schema:xsd:CommonBasicComponents-1")
    protected RetrieveExtensionsIndicatorType retrieveExtensionsIndicator;
    @XmlElement(name = "SenderParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected List<PartyType> senderParty;
    @XmlElement(name = "ReceiverParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected List<PartyType> receiverParty;

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodType }
     *     
     */
    public PeriodType getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodType }
     *     
     */
    public void setPeriod(PeriodType value) {
        this.period = value;
    }

    /**
     * Gets the value of the documentTypeCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentTypeCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentTypeCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentTypeCodeType }
     * 
     * 
     */
    public List<DocumentTypeCodeType> getDocumentTypeCode() {
        if (documentTypeCode == null) {
            documentTypeCode = new ArrayList<DocumentTypeCodeType>();
        }
        return this.documentTypeCode;
    }

    /**
     * Gets the value of the originatorDocumentReference property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentReferenceType }
     *     
     */
    public DocumentReferenceType getOriginatorDocumentReference() {
        return originatorDocumentReference;
    }

    /**
     * Sets the value of the originatorDocumentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentReferenceType }
     *     
     */
    public void setOriginatorDocumentReference(DocumentReferenceType value) {
        this.originatorDocumentReference = value;
    }

    /**
     * Gets the value of the retrievedDocumentsIndicator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the retrievedDocumentsIndicator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRetrievedDocumentsIndicator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RetrievedDocumentsIndicatorType }
     * 
     * 
     */
    public List<RetrievedDocumentsIndicatorType> getRetrievedDocumentsIndicator() {
        if (retrievedDocumentsIndicator == null) {
            retrievedDocumentsIndicator = new ArrayList<RetrievedDocumentsIndicatorType>();
        }
        return this.retrievedDocumentsIndicator;
    }

    /**
     * Gets the value of the retrieveExtensionsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveExtensionsIndicatorType }
     *     
     */
    public RetrieveExtensionsIndicatorType getRetrieveExtensionsIndicator() {
        return retrieveExtensionsIndicator;
    }

    /**
     * Sets the value of the retrieveExtensionsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveExtensionsIndicatorType }
     *     
     */
    public void setRetrieveExtensionsIndicator(RetrieveExtensionsIndicatorType value) {
        this.retrieveExtensionsIndicator = value;
    }

    /**
     * Gets the value of the senderParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the senderParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSenderParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyType }
     * 
     * 
     */
    public List<PartyType> getSenderParty() {
        if (senderParty == null) {
            senderParty = new ArrayList<PartyType>();
        }
        return this.senderParty;
    }

    /**
     * Gets the value of the receiverParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the receiverParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReceiverParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyType }
     * 
     * 
     */
    public List<PartyType> getReceiverParty() {
        if (receiverParty == null) {
            receiverParty = new ArrayList<PartyType>();
        }
        return this.receiverParty;
    }

}
