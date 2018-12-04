
package ec.schema.xsd.documentwrapperrequest_1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.schema.xsd.documentwrapperrequest_1 package. 
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

    private final static QName _DocumentWrapperRequest_QNAME = new QName("ec:schema:xsd:DocumentWrapperRequest-1", "DocumentWrapperRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.schema.xsd.documentwrapperrequest_1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DocumentWrapperRequestType }
     * 
     */
    public DocumentWrapperRequestType createDocumentWrapperRequestType() {
        return new DocumentWrapperRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentWrapperRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:DocumentWrapperRequest-1", name = "DocumentWrapperRequest")
    public JAXBElement<DocumentWrapperRequestType> createDocumentWrapperRequest(DocumentWrapperRequestType value) {
        return new JAXBElement<DocumentWrapperRequestType>(_DocumentWrapperRequest_QNAME, DocumentWrapperRequestType.class, null, value);
    }

}
