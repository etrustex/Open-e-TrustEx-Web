
package ec.schema.xsd.commonaggregatecomponents_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.AutomaticIndicatorType;
import ec.schema.xsd.commonbasiccomponents_1.MultipleIndicatorType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DocumentTypeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.VersionIDType;


/**
 * <p>Java class for NextDocumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NextDocumentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}DocumentTypeCode"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}VersionID"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}MultipleIndicator"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}AutomaticIndicator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NextDocumentType", propOrder = {
    "documentTypeCode",
    "versionID",
    "multipleIndicator",
    "automaticIndicator"
})
public class NextDocumentType {

    @XmlElement(name = "DocumentTypeCode", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected DocumentTypeCodeType documentTypeCode;
    @XmlElement(name = "VersionID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected VersionIDType versionID;
    @XmlElement(name = "MultipleIndicator", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected MultipleIndicatorType multipleIndicator;
    @XmlElement(name = "AutomaticIndicator", namespace = "ec:schema:xsd:CommonBasicComponents-1")
    protected AutomaticIndicatorType automaticIndicator;

    /**
     * Gets the value of the documentTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentTypeCodeType }
     *     
     */
    public DocumentTypeCodeType getDocumentTypeCode() {
        return documentTypeCode;
    }

    /**
     * Sets the value of the documentTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentTypeCodeType }
     *     
     */
    public void setDocumentTypeCode(DocumentTypeCodeType value) {
        this.documentTypeCode = value;
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
     * Gets the value of the multipleIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link MultipleIndicatorType }
     *     
     */
    public MultipleIndicatorType getMultipleIndicator() {
        return multipleIndicator;
    }

    /**
     * Sets the value of the multipleIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultipleIndicatorType }
     *     
     */
    public void setMultipleIndicator(MultipleIndicatorType value) {
        this.multipleIndicator = value;
    }

    /**
     * Gets the value of the automaticIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link AutomaticIndicatorType }
     *     
     */
    public AutomaticIndicatorType getAutomaticIndicator() {
        return automaticIndicator;
    }

    /**
     * Sets the value of the automaticIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link AutomaticIndicatorType }
     *     
     */
    public void setAutomaticIndicator(AutomaticIndicatorType value) {
        this.automaticIndicator = value;
    }

}
