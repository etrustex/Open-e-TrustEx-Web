
package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ETrustExEdmaMdDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ETrustExEdmaMdDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confidential" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentComment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="originalFilename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="file" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileReferenceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ETrustExEdmaMdDocument", namespace = "urn:eu:europa:ec:comp:etrustex", propOrder = {
    "confidential",
    "documentComment",
    "originalFilename",
    "file",
    "fileReferenceId",
    "path"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class ETrustExEdmaMdDocument {

    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String confidential;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String documentComment;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String originalFilename;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String file;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String fileReferenceId;
    @XmlElement(namespace = "urn:eu:europa:ec:comp:etrustex", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String path;

    /**
     * Gets the value of the confidential property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getConfidential() {
        return confidential;
    }

    /**
     * Sets the value of the confidential property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setConfidential(String value) {
        this.confidential = value;
    }

    /**
     * Gets the value of the documentComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getDocumentComment() {
        return documentComment;
    }

    /**
     * Sets the value of the documentComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDocumentComment(String value) {
        this.documentComment = value;
    }

    /**
     * Gets the value of the originalFilename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getOriginalFilename() {
        return originalFilename;
    }

    /**
     * Sets the value of the originalFilename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOriginalFilename(String value) {
        this.originalFilename = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFile(String value) {
        this.file = value;
    }

    /**
     * Gets the value of the fileReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getFileReferenceId() {
        return fileReferenceId;
    }

    /**
     * Sets the value of the fileReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFileReferenceId(String value) {
        this.fileReferenceId = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPath(String value) {
        this.path = value;
    }

}
