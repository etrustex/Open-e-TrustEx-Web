
package ec.schema.xsd.commonaggregatecomponents_2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.InternalIDType;
import ec.schema.xsd.commonbasiccomponents_1.MimeCodeType;
import ec.schema.xsd.commonbasiccomponents_1.RegistrationDateType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ReceivedDateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.VersionIDType;


/**
 * <p>Java class for ECDocumentReceivedDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECDocumentReceivedDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ID" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}InternalID" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}VersionID" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}AuthorisationHeader"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ReceivedDate"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}RegistrationDate" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}IssuerParty" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}RecipientParty" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}AdditionalDocumentReference" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}ProcessingWarning" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonAggregateComponents-2}AdditionaHeaderlProperty" minOccurs="0"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}MimeCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECDocumentReceivedDataType", propOrder = {
    "id",
    "internalID",
    "versionID",
    "authorisationHeader",
    "receivedDate",
    "registrationDate",
    "issuerParty",
    "recipientParty",
    "additionalDocumentReference",
    "processingWarning",
    "additionaHeaderlProperty",
    "mimeCode"
})
public class ECDocumentReceivedDataType {

    @XmlElement(name = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected IDType id;
    @XmlElement(name = "InternalID", namespace = "ec:schema:xsd:CommonBasicComponents-1")
    protected InternalIDType internalID;
    @XmlElement(name = "VersionID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected VersionIDType versionID;
    @XmlElement(name = "AuthorisationHeader", required = true)
    protected AuthorisationHeaderType authorisationHeader;
    @XmlElement(name = "ReceivedDate", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected ReceivedDateType receivedDate;
    @XmlElement(name = "RegistrationDate", namespace = "ec:schema:xsd:CommonBasicComponents-1")
    protected RegistrationDateType registrationDate;
    @XmlElement(name = "IssuerParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected PartyType issuerParty;
    @XmlElement(name = "RecipientParty", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected PartyType recipientParty;
    @XmlElement(name = "AdditionalDocumentReference", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2")
    protected List<DocumentReferenceType> additionalDocumentReference;
    @XmlElement(name = "ProcessingWarning")
    protected ProcessingWarningType processingWarning;
    @XmlElement(name = "AdditionaHeaderlProperty")
    protected AdditionalProperty additionaHeaderlProperty;
    @XmlElement(name = "MimeCode", namespace = "ec:schema:xsd:CommonBasicComponents-1")
    protected MimeCodeType mimeCode;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link IDType }
     *     
     */
    public IDType getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDType }
     *     
     */
    public void setID(IDType value) {
        this.id = value;
    }

    /**
     * Gets the value of the internalID property.
     * 
     * @return
     *     possible object is
     *     {@link InternalIDType }
     *     
     */
    public InternalIDType getInternalID() {
        return internalID;
    }

    /**
     * Sets the value of the internalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternalIDType }
     *     
     */
    public void setInternalID(InternalIDType value) {
        this.internalID = value;
    }

    /**
     * Gets the value of the versionID property.
     * 
     * @return
     *     possible object is
     *     {@link VersionIDType }
     *     
     */
    public VersionIDType getVersionID() {
        return versionID;
    }

    /**
     * Sets the value of the versionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionIDType }
     *     
     */
    public void setVersionID(VersionIDType value) {
        this.versionID = value;
    }

    /**
     * Gets the value of the authorisationHeader property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorisationHeaderType }
     *     
     */
    public AuthorisationHeaderType getAuthorisationHeader() {
        return authorisationHeader;
    }

    /**
     * Sets the value of the authorisationHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorisationHeaderType }
     *     
     */
    public void setAuthorisationHeader(AuthorisationHeaderType value) {
        this.authorisationHeader = value;
    }

    /**
     * Gets the value of the receivedDate property.
     * 
     * @return
     *     possible object is
     *     {@link ReceivedDateType }
     *     
     */
    public ReceivedDateType getReceivedDate() {
        return receivedDate;
    }

    /**
     * Sets the value of the receivedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceivedDateType }
     *     
     */
    public void setReceivedDate(ReceivedDateType value) {
        this.receivedDate = value;
    }

    /**
     * Gets the value of the registrationDate property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationDateType }
     *     
     */
    public RegistrationDateType getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the value of the registrationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationDateType }
     *     
     */
    public void setRegistrationDate(RegistrationDateType value) {
        this.registrationDate = value;
    }

    /**
     * Gets the value of the issuerParty property.
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getIssuerParty() {
        return issuerParty;
    }

    /**
     * Sets the value of the issuerParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setIssuerParty(PartyType value) {
        this.issuerParty = value;
    }

    /**
     * Gets the value of the recipientParty property.
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getRecipientParty() {
        return recipientParty;
    }

    /**
     * Sets the value of the recipientParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setRecipientParty(PartyType value) {
        this.recipientParty = value;
    }

    /**
     * Gets the value of the additionalDocumentReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalDocumentReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalDocumentReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentReferenceType }
     * 
     * 
     */
    public List<DocumentReferenceType> getAdditionalDocumentReference() {
        if (additionalDocumentReference == null) {
            additionalDocumentReference = new ArrayList<DocumentReferenceType>();
        }
        return this.additionalDocumentReference;
    }

    /**
     * Gets the value of the processingWarning property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessingWarningType }
     *     
     */
    public ProcessingWarningType getProcessingWarning() {
        return processingWarning;
    }

    /**
     * Sets the value of the processingWarning property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessingWarningType }
     *     
     */
    public void setProcessingWarning(ProcessingWarningType value) {
        this.processingWarning = value;
    }

    /**
     * Gets the value of the additionaHeaderlProperty property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalProperty }
     *     
     */
    public AdditionalProperty getAdditionaHeaderlProperty() {
        return additionaHeaderlProperty;
    }

    /**
     * Sets the value of the additionaHeaderlProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalProperty }
     *     
     */
    public void setAdditionaHeaderlProperty(AdditionalProperty value) {
        this.additionaHeaderlProperty = value;
    }

    /**
     * Gets the value of the mimeCode property.
     * 
     * @return
     *     possible object is
     *     {@link MimeCodeType }
     *     
     */
    public MimeCodeType getMimeCode() {
        return mimeCode;
    }

    /**
     * Sets the value of the mimeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link MimeCodeType }
     *     
     */
    public void setMimeCode(MimeCodeType value) {
        this.mimeCode = value;
    }

}
