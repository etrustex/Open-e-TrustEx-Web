//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * Application of the principles of subsidiarity and proportionality.
 * 
 * <p>Java class for SubsidiarityProportionalityApplicationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubsidiarityProportionalityApplicationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="competence_type" type="{urn:eu:ec:cm:common:extensions:v11}ConstantType"/>
 *         &lt;element name="legal_reference" type="{http://publications.europa.eu/resource/core-metadata}t_legal_procedure" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubsidiarityProportionalityApplicationType", namespace = "urn:eu:ec:cm:common:extensions:v11", propOrder = {
    "competenceType",
    "legalReference"
})
public class SubsidiarityProportionalityApplicationType {

    @XmlElement(name = "competence_type", required = true)
    protected String competenceType;
    @XmlElement(name = "legal_reference", required = true)
    protected List<TLegalProcedure> legalReference;

    /**
     * Gets the value of the competenceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompetenceType() {
        return competenceType;
    }

    /**
     * Sets the value of the competenceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompetenceType(String value) {
        this.competenceType = value;
    }

    /**
     * Gets the value of the legalReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the legalReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLegalReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TLegalProcedure }
     * 
     * 
     */
    public List<TLegalProcedure> getLegalReference() {
        if (legalReference == null) {
            legalReference = new ArrayList<>();
        }
        return this.legalReference;
    }

}
