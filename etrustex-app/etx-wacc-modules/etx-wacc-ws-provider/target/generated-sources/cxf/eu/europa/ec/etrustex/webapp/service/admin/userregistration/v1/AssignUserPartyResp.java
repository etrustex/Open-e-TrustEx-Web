
package eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assignUserPartyResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assignUserPartyResp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="status" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}AssignUserPartyStatus"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assignUserPartyResp", propOrder = {
    "status"
})
public class AssignUserPartyResp {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected AssignUserPartyStatus status;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link AssignUserPartyStatus }
     *     
     */
    public AssignUserPartyStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssignUserPartyStatus }
     *     
     */
    public void setStatus(AssignUserPartyStatus value) {
        this.status = value;
    }

}
