
package eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssignUserParty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignUserParty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="userLogin" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}NonEmptyString"/&gt;
 *         &lt;element name="partyID" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}NonEmptyString"/&gt;
 *         &lt;element name="newPartyDisplayName" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}NonEmptyString" minOccurs="0"/&gt;
 *         &lt;element name="application" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}NonEmptyString"/&gt;
 *         &lt;element name="userFirstName" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}NonEmptyString"/&gt;
 *         &lt;element name="userLastName" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}NonEmptyString"/&gt;
 *         &lt;element name="userEmail" type="{http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0}EmailAddress"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignUserParty", propOrder = {
    "userLogin",
    "partyID",
    "newPartyDisplayName",
    "application",
    "userFirstName",
    "userLastName",
    "userEmail"
})
public class AssignUserParty {

    @XmlElement(required = true)
    protected String userLogin;
    @XmlElement(required = true)
    protected String partyID;
    protected String newPartyDisplayName;
    @XmlElement(required = true)
    protected String application;
    @XmlElement(required = true)
    protected String userFirstName;
    @XmlElement(required = true)
    protected String userLastName;
    @XmlElement(required = true)
    protected String userEmail;

    /**
     * Gets the value of the userLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Sets the value of the userLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserLogin(String value) {
        this.userLogin = value;
    }

    /**
     * Gets the value of the partyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartyID() {
        return partyID;
    }

    /**
     * Sets the value of the partyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyID(String value) {
        this.partyID = value;
    }

    /**
     * Gets the value of the newPartyDisplayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPartyDisplayName() {
        return newPartyDisplayName;
    }

    /**
     * Sets the value of the newPartyDisplayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPartyDisplayName(String value) {
        this.newPartyDisplayName = value;
    }

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplication(String value) {
        this.application = value;
    }

    /**
     * Gets the value of the userFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Sets the value of the userFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserFirstName(String value) {
        this.userFirstName = value;
    }

    /**
     * Gets the value of the userLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Sets the value of the userLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserLastName(String value) {
        this.userLastName = value;
    }

    /**
     * Gets the value of the userEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the value of the userEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserEmail(String value) {
        this.userEmail = value;
    }

}
