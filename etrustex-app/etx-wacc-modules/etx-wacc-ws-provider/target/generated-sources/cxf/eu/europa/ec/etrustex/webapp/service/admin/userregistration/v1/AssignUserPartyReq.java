
package eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assignUserPartyReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assignUserPartyReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="assignUserParty" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}AssignUserParty"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assignUserPartyReq", propOrder = {
    "assignUserParty"
})
public class AssignUserPartyReq {

    @XmlElement(required = true)
    protected AssignUserParty assignUserParty;

    /**
     * Gets the value of the assignUserParty property.
     * 
     * @return
     *     possible object is
     *     {@link AssignUserParty }
     *     
     */
    public AssignUserParty getAssignUserParty() {
        return assignUserParty;
    }

    /**
     * Sets the value of the assignUserParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssignUserParty }
     *     
     */
    public void setAssignUserParty(AssignUserParty value) {
        this.assignUserParty = value;
    }

}
