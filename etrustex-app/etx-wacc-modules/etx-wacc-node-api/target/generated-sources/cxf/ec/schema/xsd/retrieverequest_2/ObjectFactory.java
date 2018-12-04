
package ec.schema.xsd.retrieverequest_2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.schema.xsd.retrieverequest_2 package. 
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

    private final static QName _RetrieveRequest_QNAME = new QName("ec:schema:xsd:RetrieveRequest-2", "RetrieveRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.schema.xsd.retrieverequest_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveRequestType }
     * 
     */
    public RetrieveRequestType createRetrieveRequestType() {
        return new RetrieveRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:RetrieveRequest-2", name = "RetrieveRequest")
    public JAXBElement<RetrieveRequestType> createRetrieveRequest(RetrieveRequestType value) {
        return new JAXBElement<RetrieveRequestType>(_RetrieveRequest_QNAME, RetrieveRequestType.class, null, value);
    }

}
