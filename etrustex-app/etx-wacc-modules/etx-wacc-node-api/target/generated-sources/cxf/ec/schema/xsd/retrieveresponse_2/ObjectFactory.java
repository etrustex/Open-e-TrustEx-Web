
package ec.schema.xsd.retrieveresponse_2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.schema.xsd.retrieveresponse_2 package. 
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

    private final static QName _RetrieveResponse_QNAME = new QName("ec:schema:xsd:RetrieveResponse-2.1", "RetrieveResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.schema.xsd.retrieveresponse_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveResponseType }
     * 
     */
    public RetrieveResponseType createRetrieveResponseType() {
        return new RetrieveResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:RetrieveResponse-2.1", name = "RetrieveResponse")
    public JAXBElement<RetrieveResponseType> createRetrieveResponse(RetrieveResponseType value) {
        return new JAXBElement<RetrieveResponseType>(_RetrieveResponse_QNAME, RetrieveResponseType.class, null, value);
    }

}
