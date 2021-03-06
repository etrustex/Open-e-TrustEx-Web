//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.26 at 09:07:40 AM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.metadata;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for t_work_legis_sum complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_work_legis_sum">
 *   &lt;complexContent>
 *     &lt;extension base="{http://publications.europa.eu/resource/core-metadata}t_work_extension_base">
 *       &lt;sequence>
 *         &lt;element name="identifier_work_alternative" type="{http://publications.europa.eu/resource/core-metadata/nth}t_identifier_work_alternative" minOccurs="0"/>
 *         &lt;element name="id_act_summarized" type="{http://publications.europa.eu/resource/core-metadata/nth}t_id_act_basic_summarized" maxOccurs="unbounded"/>
 *         &lt;element name="id_act_summarized_celex" type="{http://publications.europa.eu/resource/core-metadata/nth}t_id_act_basic_summarized_celex" maxOccurs="unbounded"/>
 *         &lt;element name="id_act_consolidated_summarized_celex" type="{http://publications.europa.eu/resource/core-metadata/nth}t_id_act_consolidated_summarized_celex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="date_initial" type="{http://publications.europa.eu/resource/core-metadata/nth}t_date_initial"/>
 *         &lt;element name="language_draft" type="{http://publications.europa.eu/resource/core-metadata/nth}t_language_draft" minOccurs="0"/>
 *         &lt;element name="obsolete" type="{http://publications.europa.eu/resource/core-metadata/nth}t_obsolete"/>
 *         &lt;element name="id_doc_related" type="{http://publications.europa.eu/resource/core-metadata/nth}t_id_doc_related" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="classifications_summary" type="{http://publications.europa.eu/resource/core-metadata/nth}t_classifications_summary" minOccurs="0"/>
 *         &lt;element name="descriptors_ev" type="{http://publications.europa.eu/resource/core-metadata/nth}t_descriptors_ev" minOccurs="0"/>
 *         &lt;element name="classifications_directory" type="{http://publications.europa.eu/resource/core-metadata/nth}t_classifications_directory" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://publications.europa.eu/resource/core-metadata}public"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_work_legis_sum", propOrder = {
    "identifierWorkAlternative",
    "idActSummarized",
    "idActSummarizedCelex",
    "idActConsolidatedSummarizedCelex",
    "dateInitial",
    "languageDraft",
    "obsolete",
    "idDocRelated",
    "classificationsSummary",
    "descriptorsEv",
    "classificationsDirectory"
})
public class TWorkLegisSum
    extends TWorkExtensionBase
{

    @XmlElement(name = "identifier_work_alternative")
    protected TIdentifierWorkAlternative identifierWorkAlternative;
    @XmlElement(name = "id_act_summarized", required = true)
    protected List<TIdActBasicSummarized> idActSummarized;
    @XmlElement(name = "id_act_summarized_celex", required = true)
    protected List<TIdActBasicSummarizedCelex> idActSummarizedCelex;
    @XmlElement(name = "id_act_consolidated_summarized_celex")
    protected List<TIdActConsolidatedSummarizedCelex> idActConsolidatedSummarizedCelex;
    @XmlElement(name = "date_initial", required = true)
    protected TDateInitial dateInitial;
    @XmlElement(name = "language_draft")
    protected TLanguageDraft languageDraft;
    @XmlElement(required = true)
    protected TObsolete obsolete;
    @XmlElement(name = "id_doc_related")
    protected List<TIdDocRelated> idDocRelated;
    @XmlElement(name = "classifications_summary")
    protected TClassificationsSummary classificationsSummary;
    @XmlElement(name = "descriptors_ev")
    protected TDescriptorsEv descriptorsEv;
    @XmlElement(name = "classifications_directory")
    protected TClassificationsDirectory classificationsDirectory;
    @XmlAttribute(name = "public", namespace = "http://publications.europa.eu/resource/core-metadata")
    protected Boolean _public;

    /**
     * Gets the value of the identifierWorkAlternative property.
     * 
     * @return
     *     possible object is
     *     {@link TIdentifierWorkAlternative }
     *     
     */
    public TIdentifierWorkAlternative getIdentifierWorkAlternative() {
        return identifierWorkAlternative;
    }

    /**
     * Sets the value of the identifierWorkAlternative property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIdentifierWorkAlternative }
     *     
     */
    public void setIdentifierWorkAlternative(TIdentifierWorkAlternative value) {
        this.identifierWorkAlternative = value;
    }

    /**
     * Gets the value of the idActSummarized property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idActSummarized property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdActSummarized().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TIdActBasicSummarized }
     * 
     * 
     */
    public List<TIdActBasicSummarized> getIdActSummarized() {
        if (idActSummarized == null) {
            idActSummarized = new ArrayList<>();
        }
        return this.idActSummarized;
    }

    /**
     * Gets the value of the idActSummarizedCelex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idActSummarizedCelex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdActSummarizedCelex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TIdActBasicSummarizedCelex }
     * 
     * 
     */
    public List<TIdActBasicSummarizedCelex> getIdActSummarizedCelex() {
        if (idActSummarizedCelex == null) {
            idActSummarizedCelex = new ArrayList<>();
        }
        return this.idActSummarizedCelex;
    }

    /**
     * Gets the value of the idActConsolidatedSummarizedCelex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idActConsolidatedSummarizedCelex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdActConsolidatedSummarizedCelex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TIdActConsolidatedSummarizedCelex }
     * 
     * 
     */
    public List<TIdActConsolidatedSummarizedCelex> getIdActConsolidatedSummarizedCelex() {
        if (idActConsolidatedSummarizedCelex == null) {
            idActConsolidatedSummarizedCelex = new ArrayList<>();
        }
        return this.idActConsolidatedSummarizedCelex;
    }

    /**
     * Gets the value of the dateInitial property.
     * 
     * @return
     *     possible object is
     *     {@link TDateInitial }
     *     
     */
    public TDateInitial getDateInitial() {
        return dateInitial;
    }

    /**
     * Sets the value of the dateInitial property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDateInitial }
     *     
     */
    public void setDateInitial(TDateInitial value) {
        this.dateInitial = value;
    }

    /**
     * Gets the value of the languageDraft property.
     * 
     * @return
     *     possible object is
     *     {@link TLanguageDraft }
     *     
     */
    public TLanguageDraft getLanguageDraft() {
        return languageDraft;
    }

    /**
     * Sets the value of the languageDraft property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLanguageDraft }
     *     
     */
    public void setLanguageDraft(TLanguageDraft value) {
        this.languageDraft = value;
    }

    /**
     * Gets the value of the obsolete property.
     * 
     * @return
     *     possible object is
     *     {@link TObsolete }
     *     
     */
    public TObsolete getObsolete() {
        return obsolete;
    }

    /**
     * Sets the value of the obsolete property.
     * 
     * @param value
     *     allowed object is
     *     {@link TObsolete }
     *     
     */
    public void setObsolete(TObsolete value) {
        this.obsolete = value;
    }

    /**
     * Gets the value of the idDocRelated property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idDocRelated property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdDocRelated().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TIdDocRelated }
     * 
     * 
     */
    public List<TIdDocRelated> getIdDocRelated() {
        if (idDocRelated == null) {
            idDocRelated = new ArrayList<>();
        }
        return this.idDocRelated;
    }

    /**
     * Gets the value of the classificationsSummary property.
     * 
     * @return
     *     possible object is
     *     {@link TClassificationsSummary }
     *     
     */
    public TClassificationsSummary getClassificationsSummary() {
        return classificationsSummary;
    }

    /**
     * Sets the value of the classificationsSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link TClassificationsSummary }
     *     
     */
    public void setClassificationsSummary(TClassificationsSummary value) {
        this.classificationsSummary = value;
    }

    /**
     * Gets the value of the descriptorsEv property.
     * 
     * @return
     *     possible object is
     *     {@link TDescriptorsEv }
     *     
     */
    public TDescriptorsEv getDescriptorsEv() {
        return descriptorsEv;
    }

    /**
     * Sets the value of the descriptorsEv property.
     * 
     * @param value
     *     allowed object is
     *     {@link TDescriptorsEv }
     *     
     */
    public void setDescriptorsEv(TDescriptorsEv value) {
        this.descriptorsEv = value;
    }

    /**
     * Gets the value of the classificationsDirectory property.
     * 
     * @return
     *     possible object is
     *     {@link TClassificationsDirectory }
     *     
     */
    public TClassificationsDirectory getClassificationsDirectory() {
        return classificationsDirectory;
    }

    /**
     * Sets the value of the classificationsDirectory property.
     * 
     * @param value
     *     allowed object is
     *     {@link TClassificationsDirectory }
     *     
     */
    public void setClassificationsDirectory(TClassificationsDirectory value) {
        this.classificationsDirectory = value;
    }

    /**
     * Gets the value of the public property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPublic() {
        if (_public == null) {
            return true;
        } else {
            return _public;
        }
    }

    /**
     * Sets the value of the public property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPublic(Boolean value) {
        this._public = value;
    }

}
