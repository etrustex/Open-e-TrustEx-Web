
package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="ETrustExEdmaMdDocument" type="{urn:eu:europa:ec:comp:etrustex}ETrustExEdmaMdDocument" maxOccurs="unbounded" minOccurs="0"/>
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
    "eTrustExEdmaMdDocument"
})
@XmlRootElement(name = "documents", namespace = "urn:eu:europa:ec:comp:etrustex")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Documents {

    @XmlElement(name = "ETrustExEdmaMdDocument", namespace = "urn:eu:europa:ec:comp:etrustex")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<ETrustExEdmaMdDocument> eTrustExEdmaMdDocument;

    /**
     * Gets the value of the eTrustExEdmaMdDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eTrustExEdmaMdDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getETrustExEdmaMdDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ETrustExEdmaMdDocument }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-27T09:38:28+02:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<ETrustExEdmaMdDocument> getETrustExEdmaMdDocument() {
        if (eTrustExEdmaMdDocument == null) {
            eTrustExEdmaMdDocument = new ArrayList<ETrustExEdmaMdDocument>();
        }
        return this.eTrustExEdmaMdDocument;
    }

}
