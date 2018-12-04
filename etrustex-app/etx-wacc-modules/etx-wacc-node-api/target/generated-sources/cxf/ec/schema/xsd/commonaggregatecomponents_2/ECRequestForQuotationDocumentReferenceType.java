
package ec.schema.xsd.commonaggregatecomponents_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.RequestForQuotationPropertyCodeType;
import ec.schema.xsd.commonbasiccomponents_1.RequestForQuotationTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;


/**
 * <p>Java class for ECRequestForQuotationDocumentReferenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECRequestForQuotationDocumentReferenceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}RequestForQuotationDocumentReference"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}RequestForQuotationTypeCode"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}RequestForQuotationPropertyCode"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}Contract"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECRequestForQuotationDocumentReferenceType", propOrder = {
    "requestForQuotationDocumentReference",
    "requestForQuotationTypeCode",
    "requestForQuotationPropertyCode",
    "contract"
})
public class ECRequestForQuotationDocumentReferenceType {

    @XmlElement(name = "RequestForQuotationDocumentReference", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2", required = true)
    protected DocumentReferenceType requestForQuotationDocumentReference;
    @XmlElement(name = "RequestForQuotationTypeCode", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected RequestForQuotationTypeCodeType requestForQuotationTypeCode;
    @XmlElement(name = "RequestForQuotationPropertyCode", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected RequestForQuotationPropertyCodeType requestForQuotationPropertyCode;
    @XmlElement(name = "Contract", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2", required = true)
    protected ContractType contract;

    /**
     * Gets the value of the requestForQuotationDocumentReference property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentReferenceType }
     *     
     */
    public DocumentReferenceType getRequestForQuotationDocumentReference() {
        return requestForQuotationDocumentReference;
    }

    /**
     * Sets the value of the requestForQuotationDocumentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentReferenceType }
     *     
     */
    public void setRequestForQuotationDocumentReference(DocumentReferenceType value) {
        this.requestForQuotationDocumentReference = value;
    }

    /**
     * Gets the value of the requestForQuotationTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link RequestForQuotationTypeCodeType }
     *     
     */
    public RequestForQuotationTypeCodeType getRequestForQuotationTypeCode() {
        return requestForQuotationTypeCode;
    }

    /**
     * Sets the value of the requestForQuotationTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestForQuotationTypeCodeType }
     *     
     */
    public void setRequestForQuotationTypeCode(RequestForQuotationTypeCodeType value) {
        this.requestForQuotationTypeCode = value;
    }

    /**
     * Gets the value of the requestForQuotationPropertyCode property.
     * 
     * @return
     *     possible object is
     *     {@link RequestForQuotationPropertyCodeType }
     *     
     */
    public RequestForQuotationPropertyCodeType getRequestForQuotationPropertyCode() {
        return requestForQuotationPropertyCode;
    }

    /**
     * Sets the value of the requestForQuotationPropertyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestForQuotationPropertyCodeType }
     *     
     */
    public void setRequestForQuotationPropertyCode(RequestForQuotationPropertyCodeType value) {
        this.requestForQuotationPropertyCode = value;
    }

    /**
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link ContractType }
     *     
     */
    public ContractType getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractType }
     *     
     */
    public void setContract(ContractType value) {
        this.contract = value;
    }

}
