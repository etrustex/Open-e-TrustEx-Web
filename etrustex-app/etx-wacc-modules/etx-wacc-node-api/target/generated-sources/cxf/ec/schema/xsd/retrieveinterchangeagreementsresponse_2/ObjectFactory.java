
package ec.schema.xsd.retrieveinterchangeagreementsresponse_2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.schema.xsd.retrieveinterchangeagreementsresponse_2 package. 
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

    private final static QName _RetrieveInterchangeAgreementsResponse_QNAME = new QName("ec:schema:xsd:RetrieveInterchangeAgreementsResponse-2", "RetrieveInterchangeAgreementsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.schema.xsd.retrieveinterchangeagreementsresponse_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveInterchangeAgreementsResponseType }
     * 
     */
    public RetrieveInterchangeAgreementsResponseType createRetrieveInterchangeAgreementsResponseType() {
        return new RetrieveInterchangeAgreementsResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveInterchangeAgreementsResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:RetrieveInterchangeAgreementsResponse-2", name = "RetrieveInterchangeAgreementsResponse")
    public JAXBElement<RetrieveInterchangeAgreementsResponseType> createRetrieveInterchangeAgreementsResponse(RetrieveInterchangeAgreementsResponseType value) {
        return new JAXBElement<RetrieveInterchangeAgreementsResponseType>(_RetrieveInterchangeAgreementsResponse_QNAME, RetrieveInterchangeAgreementsResponseType.class, null, value);
    }

}
