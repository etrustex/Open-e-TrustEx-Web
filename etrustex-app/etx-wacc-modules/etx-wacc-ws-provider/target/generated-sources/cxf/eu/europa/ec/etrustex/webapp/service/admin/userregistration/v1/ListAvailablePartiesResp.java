
package eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listAvailablePartiesResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listAvailablePartiesResp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="listAvailableParties" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}listAvailableParties" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listAvailablePartiesResp", propOrder = {
    "listAvailableParties"
})
public class ListAvailablePartiesResp {

    protected List<ListAvailableParties> listAvailableParties;

    /**
     * Gets the value of the listAvailableParties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listAvailableParties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListAvailableParties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListAvailableParties }
     * 
     * 
     */
    public List<ListAvailableParties> getListAvailableParties() {
        if (listAvailableParties == null) {
            listAvailableParties = new ArrayList<ListAvailableParties>();
        }
        return this.listAvailableParties;
    }

}
