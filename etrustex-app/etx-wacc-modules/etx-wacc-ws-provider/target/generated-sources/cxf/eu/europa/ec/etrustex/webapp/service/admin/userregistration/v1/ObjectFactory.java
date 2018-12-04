
package eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListAvailablePartiesReq_QNAME = new QName("http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", "listAvailablePartiesReq");
    private final static QName _ListAvailablePartiesResp_QNAME = new QName("http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", "listAvailablePartiesResp");
    private final static QName _AssignUserPartyReq_QNAME = new QName("http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", "assignUserPartyReq");
    private final static QName _AssignUserPartyResp_QNAME = new QName("http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", "assignUserPartyResp");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListAvailablePartiesReq }
     * 
     */
    public ListAvailablePartiesReq createListAvailablePartiesReq() {
        return new ListAvailablePartiesReq();
    }

    /**
     * Create an instance of {@link ListAvailablePartiesResp }
     * 
     */
    public ListAvailablePartiesResp createListAvailablePartiesResp() {
        return new ListAvailablePartiesResp();
    }

    /**
     * Create an instance of {@link AssignUserPartyReq }
     * 
     */
    public AssignUserPartyReq createAssignUserPartyReq() {
        return new AssignUserPartyReq();
    }

    /**
     * Create an instance of {@link AssignUserPartyResp }
     * 
     */
    public AssignUserPartyResp createAssignUserPartyResp() {
        return new AssignUserPartyResp();
    }

    /**
     * Create an instance of {@link ListAvailableParties }
     * 
     */
    public ListAvailableParties createListAvailableParties() {
        return new ListAvailableParties();
    }

    /**
     * Create an instance of {@link AssignUserParty }
     * 
     */
    public AssignUserParty createAssignUserParty() {
        return new AssignUserParty();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAvailablePartiesReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", name = "listAvailablePartiesReq")
    public JAXBElement<ListAvailablePartiesReq> createListAvailablePartiesReq(ListAvailablePartiesReq value) {
        return new JAXBElement<ListAvailablePartiesReq>(_ListAvailablePartiesReq_QNAME, ListAvailablePartiesReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAvailablePartiesResp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", name = "listAvailablePartiesResp")
    public JAXBElement<ListAvailablePartiesResp> createListAvailablePartiesResp(ListAvailablePartiesResp value) {
        return new JAXBElement<ListAvailablePartiesResp>(_ListAvailablePartiesResp_QNAME, ListAvailablePartiesResp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignUserPartyReq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", name = "assignUserPartyReq")
    public JAXBElement<AssignUserPartyReq> createAssignUserPartyReq(AssignUserPartyReq value) {
        return new JAXBElement<AssignUserPartyReq>(_AssignUserPartyReq_QNAME, AssignUserPartyReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignUserPartyResp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", name = "assignUserPartyResp")
    public JAXBElement<AssignUserPartyResp> createAssignUserPartyResp(AssignUserPartyResp value) {
        return new JAXBElement<AssignUserPartyResp>(_AssignUserPartyResp_QNAME, AssignUserPartyResp.class, null, value);
    }

}
