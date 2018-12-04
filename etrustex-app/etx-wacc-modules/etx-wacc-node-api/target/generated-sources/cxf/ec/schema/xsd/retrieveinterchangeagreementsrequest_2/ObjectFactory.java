
package ec.schema.xsd.retrieveinterchangeagreementsrequest_2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.schema.xsd.retrieveinterchangeagreementsrequest_2 package. 
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

    private final static QName _RetrieveInterchangeAgreementsRequest_QNAME = new QName("ec:schema:xsd:RetrieveInterchangeAgreementsRequest-2", "RetrieveInterchangeAgreementsRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.schema.xsd.retrieveinterchangeagreementsrequest_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveInterchangeAgreementsRequestType }
     * 
     */
    public RetrieveInterchangeAgreementsRequestType createRetrieveInterchangeAgreementsRequestType() {
        return new RetrieveInterchangeAgreementsRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveInterchangeAgreementsRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:RetrieveInterchangeAgreementsRequest-2", name = "RetrieveInterchangeAgreementsRequest")
    public JAXBElement<RetrieveInterchangeAgreementsRequestType> createRetrieveInterchangeAgreementsRequest(RetrieveInterchangeAgreementsRequestType value) {
        return new JAXBElement<RetrieveInterchangeAgreementsRequestType>(_RetrieveInterchangeAgreementsRequest_QNAME, RetrieveInterchangeAgreementsRequestType.class, null, value);
    }

}
