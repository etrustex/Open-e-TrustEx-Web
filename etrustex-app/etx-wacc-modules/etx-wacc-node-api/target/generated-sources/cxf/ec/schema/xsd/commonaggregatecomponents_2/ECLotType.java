
package ec.schema.xsd.commonaggregatecomponents_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ec.schema.xsd.commonbasiccomponents_1.MarketProcedureNameType;
import ec.schema.xsd.commonbasiccomponents_1.ModeCodeType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;


/**
 * <p>Java class for ECLotType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECLotType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}ID"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}MarketProcedureName"/&gt;
 *         &lt;element ref="{ec:schema:xsd:CommonBasicComponents-1}ModeCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECLotType", propOrder = {
    "id",
    "marketProcedureName",
    "modeCode"
})
public class ECLotType {

    @XmlElement(name = "ID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected IDType id;
    @XmlElement(name = "MarketProcedureName", namespace = "ec:schema:xsd:CommonBasicComponents-1", required = true)
    protected MarketProcedureNameType marketProcedureName;
    @XmlElement(name = "ModeCode", namespace = "ec:schema:xsd:CommonBasicComponents-1")
    protected ModeCodeType modeCode;

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
     * Gets the value of the marketProcedureName property.
     * 
     * @return
     *     possible object is
     *     {@link MarketProcedureNameType }
     *     
     */
    public MarketProcedureNameType getMarketProcedureName() {
        return marketProcedureName;
    }

    /**
     * Sets the value of the marketProcedureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarketProcedureNameType }
     *     
     */
    public void setMarketProcedureName(MarketProcedureNameType value) {
        this.marketProcedureName = value;
    }

    /**
     * Gets the value of the modeCode property.
     * 
     * @return
     *     possible object is
     *     {@link ModeCodeType }
     *     
     */
    public ModeCodeType getModeCode() {
        return modeCode;
    }

    /**
     * Sets the value of the modeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModeCodeType }
     *     
     */
    public void setModeCode(ModeCodeType value) {
        this.modeCode = value;
    }

}
