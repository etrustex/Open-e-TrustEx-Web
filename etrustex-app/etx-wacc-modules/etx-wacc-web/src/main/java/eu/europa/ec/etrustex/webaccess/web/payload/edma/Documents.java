//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.07.26 at 05:11:11 PM CEST 
//


package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
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
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "eTrustExEdmaMdDocument"
})
@XmlRootElement(name = "documents")
public class Documents {

    @XmlElement(name = "ETrustExEdmaMdDocument")
    protected List<ETrustExEdmaMdDocument> eTrustExEdmaMdDocument;

    /**
     * Gets the value of the eTrustExEdmaMdDocument property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eTrustExEdmaMdDocument property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getETrustExEdmaMdDocument().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link ETrustExEdmaMdDocument }
     */
    public List<ETrustExEdmaMdDocument> getETrustExEdmaMdDocument() {
        if (eTrustExEdmaMdDocument == null) {
            eTrustExEdmaMdDocument = new ArrayList<>();
        }
        return this.eTrustExEdmaMdDocument;
    }

}
