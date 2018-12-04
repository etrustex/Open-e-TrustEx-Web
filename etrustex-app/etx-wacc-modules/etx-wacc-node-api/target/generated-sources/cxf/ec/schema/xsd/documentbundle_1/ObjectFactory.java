
package ec.schema.xsd.documentbundle_1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.schema.xsd.documentbundle_1 package. 
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

    private final static QName _DocumentBundle_QNAME = new QName("ec:schema:xsd:DocumentBundle-1", "DocumentBundle");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.schema.xsd.documentbundle_1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DocumentBundleType }
     * 
     */
    public DocumentBundleType createDocumentBundleType() {
        return new DocumentBundleType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentBundleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:DocumentBundle-1", name = "DocumentBundle")
    public JAXBElement<DocumentBundleType> createDocumentBundle(DocumentBundleType value) {
        return new JAXBElement<DocumentBundleType>(_DocumentBundle_QNAME, DocumentBundleType.class, null, value);
    }

}
