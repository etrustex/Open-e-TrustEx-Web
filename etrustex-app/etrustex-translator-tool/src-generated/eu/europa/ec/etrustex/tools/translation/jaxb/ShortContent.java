//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.05.23 at 11:21:10 AM CEST 
//


package eu.europa.ec.etrustex.tools.translation.jaxb;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}cwcms_metadata" minOccurs="0"/>
 *         &lt;element ref="{}language_descriptor"/>
 *         &lt;element name="heading" type="{}hd_heading_type"/>
 *         &lt;element ref="{}content_qual"/>
 *         &lt;element name="content_ref_short_content" type="{}content_ref_type"/>
 *         &lt;element ref="{}text"/>
 *         &lt;element ref="{}medias"/>
 *         &lt;element ref="{}links"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cwcmsMetadata",
    "languageDescriptor",
    "heading",
    "contentQual",
    "contentRefShortContent",
    "text",
    "medias",
    "links"
})
@XmlRootElement(name = "short_content")
public class ShortContent {

    @XmlElement(name = "cwcms_metadata")
    protected CwcmsMetadata cwcmsMetadata;
    @XmlElement(name = "language_descriptor", required = true)
    protected LanguageDescriptor languageDescriptor;
    @XmlElement(required = true)
    protected HdHeadingType heading;
    @XmlElement(name = "content_qual", required = true)
    protected ContentQual contentQual;
    @XmlElement(name = "content_ref_short_content", required = true)
    protected ContentRefType contentRefShortContent;
    @XmlElement(required = true)
    protected TextType text;
    @XmlElement(required = true)
    protected MediaRListType medias;
    @XmlElement(required = true)
    protected LinkRListType links;

    /**
     * Gets the value of the cwcmsMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link CwcmsMetadata }
     *     
     */
    public CwcmsMetadata getCwcmsMetadata() {
        return cwcmsMetadata;
    }

    /**
     * Sets the value of the cwcmsMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link CwcmsMetadata }
     *     
     */
    public void setCwcmsMetadata(CwcmsMetadata value) {
        this.cwcmsMetadata = value;
    }

    /**
     * Gets the value of the languageDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link LanguageDescriptor }
     *     
     */
    public LanguageDescriptor getLanguageDescriptor() {
        return languageDescriptor;
    }

    /**
     * Sets the value of the languageDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link LanguageDescriptor }
     *     
     */
    public void setLanguageDescriptor(LanguageDescriptor value) {
        this.languageDescriptor = value;
    }

    /**
     * Gets the value of the heading property.
     * 
     * @return
     *     possible object is
     *     {@link HdHeadingType }
     *     
     */
    public HdHeadingType getHeading() {
        return heading;
    }

    /**
     * Sets the value of the heading property.
     * 
     * @param value
     *     allowed object is
     *     {@link HdHeadingType }
     *     
     */
    public void setHeading(HdHeadingType value) {
        this.heading = value;
    }

    /**
     * Gets the value of the contentQual property.
     * 
     * @return
     *     possible object is
     *     {@link ContentQual }
     *     
     */
    public ContentQual getContentQual() {
        return contentQual;
    }

    /**
     * Sets the value of the contentQual property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentQual }
     *     
     */
    public void setContentQual(ContentQual value) {
        this.contentQual = value;
    }

    /**
     * Gets the value of the contentRefShortContent property.
     * 
     * @return
     *     possible object is
     *     {@link ContentRefType }
     *     
     */
    public ContentRefType getContentRefShortContent() {
        return contentRefShortContent;
    }

    /**
     * Sets the value of the contentRefShortContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentRefType }
     *     
     */
    public void setContentRefShortContent(ContentRefType value) {
        this.contentRefShortContent = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setText(TextType value) {
        this.text = value;
    }

    /**
     * Gets the value of the medias property.
     * 
     * @return
     *     possible object is
     *     {@link MediaRListType }
     *     
     */
    public MediaRListType getMedias() {
        return medias;
    }

    /**
     * Sets the value of the medias property.
     * 
     * @param value
     *     allowed object is
     *     {@link MediaRListType }
     *     
     */
    public void setMedias(MediaRListType value) {
        this.medias = value;
    }

    /**
     * Gets the value of the links property.
     * 
     * @return
     *     possible object is
     *     {@link LinkRListType }
     *     
     */
    public LinkRListType getLinks() {
        return links;
    }

    /**
     * Sets the value of the links property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkRListType }
     *     
     */
    public void setLinks(LinkRListType value) {
        this.links = value;
    }

}
