
package oasis.names.specification.ubl.schema.xsd.signatureaggregatecomponents_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.signaturebasiccomponents_2.ReferencedSignatureIDType;
import org.w3._2000._09.xmldsig_.SignatureType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Definition xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:SignatureAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" xmlns:ns1="http://uri.etsi.org/01903/v1.3.2#" xmlns:ns2="http://uri.etsi.org/01903/v1.4.1#" xmlns:sbc="urn:oasis:names:specification:ubl:schema:xsd:SignatureBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;This class captures a single signature and optionally associates to a signature in the document with the corresponding identifier.&lt;/ccts:Definition&gt;
 * </pre>
 * 
 * 
 * <p>Java class for SignatureInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignatureInformationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ID" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:SignatureBasicComponents-2}ReferencedSignatureID" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignatureInformationType", propOrder = {
    "id",
    "referencedSignatureID",
    "signature"
})
public class SignatureInformationType {

    @XmlElement(name = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected IDType id;
    @XmlElement(name = "ReferencedSignatureID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:SignatureBasicComponents-2")
    protected ReferencedSignatureIDType referencedSignatureID;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;

    /**
     * 
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Definition xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:SignatureAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" xmlns:ns1="http://uri.etsi.org/01903/v1.3.2#" xmlns:ns2="http://uri.etsi.org/01903/v1.4.1#" xmlns:sbc="urn:oasis:names:specification:ubl:schema:xsd:SignatureBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;This specifies the identifier of the signature distinguishing it from other signatures.&lt;/ccts:Definition&gt;
     * </pre>
     * 
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
     * 
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Definition xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:SignatureAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" xmlns:ns1="http://uri.etsi.org/01903/v1.3.2#" xmlns:ns2="http://uri.etsi.org/01903/v1.4.1#" xmlns:sbc="urn:oasis:names:specification:ubl:schema:xsd:SignatureBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;This associates this signature with the identifier of a signature business object in the document.&lt;/ccts:Definition&gt;
     * </pre>
     * 
     * 
     * @return
     *     possible object is
     *     {@link ReferencedSignatureIDType }
     *     
     */
    public ReferencedSignatureIDType getReferencedSignatureID() {
        return referencedSignatureID;
    }

    /**
     * Sets the value of the referencedSignatureID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferencedSignatureIDType }
     *     
     */
    public void setReferencedSignatureID(ReferencedSignatureIDType value) {
        this.referencedSignatureID = value;
    }

    /**
     * 
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:Definition xmlns:ccts="urn:un:unece:uncefact:documentation:2" xmlns="urn:oasis:names:specification:ubl:schema:xsd:SignatureAggregateComponents-2" xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" xmlns:ns1="http://uri.etsi.org/01903/v1.3.2#" xmlns:ns2="http://uri.etsi.org/01903/v1.4.1#" xmlns:sbc="urn:oasis:names:specification:ubl:schema:xsd:SignatureBasicComponents-2" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;This is a single digital signature as defined by the W3C specification.&lt;/ccts:Definition&gt;
     * </pre>
     * 
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

}
