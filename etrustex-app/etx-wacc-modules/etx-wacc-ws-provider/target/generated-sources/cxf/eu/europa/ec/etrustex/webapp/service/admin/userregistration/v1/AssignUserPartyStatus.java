
package eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssignUserPartyStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AssignUserPartyStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SUCCESS"/&gt;
 *     &lt;enumeration value="PARTY_NOT_AVAILABLE"/&gt;
 *     &lt;enumeration value="PARTY_DOES_NOT_EXIST"/&gt;
 *     &lt;enumeration value="USER_ALREADY_ASSIGNED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AssignUserPartyStatus")
@XmlEnum
public enum AssignUserPartyStatus {

    SUCCESS,
    PARTY_NOT_AVAILABLE,
    PARTY_DOES_NOT_EXIST,
    USER_ALREADY_ASSIGNED;

    public String value() {
        return name();
    }

    public static AssignUserPartyStatus fromValue(String v) {
        return valueOf(v);
    }

}
