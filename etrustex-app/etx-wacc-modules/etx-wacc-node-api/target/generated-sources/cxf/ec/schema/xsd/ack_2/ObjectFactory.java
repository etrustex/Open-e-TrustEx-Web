
package ec.schema.xsd.ack_2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.schema.xsd.ack_2 package. 
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

    private final static QName _Acknowledgment_QNAME = new QName("ec:schema:xsd:Ack-2", "Acknowledgment");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.schema.xsd.ack_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AcknowledgmentType }
     * 
     */
    public AcknowledgmentType createAcknowledgmentType() {
        return new AcknowledgmentType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcknowledgmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:schema:xsd:Ack-2", name = "Acknowledgment")
    public JAXBElement<AcknowledgmentType> createAcknowledgment(AcknowledgmentType value) {
        return new JAXBElement<AcknowledgmentType>(_Acknowledgment_QNAME, AcknowledgmentType.class, null, value);
    }

}
