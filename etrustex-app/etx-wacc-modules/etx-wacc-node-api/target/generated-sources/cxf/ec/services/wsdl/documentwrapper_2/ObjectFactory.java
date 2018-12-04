
package ec.services.wsdl.documentwrapper_2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import ec.schema.xsd.commonaggregatecomponents_2.HeaderType;
import oasis.names.specification.ubl.schema.xsd.fault_1.FaultType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.services.wsdl.documentwrapper_2 package. 
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

    private final static QName _Header_QNAME = new QName("ec:services:wsdl:DocumentWrapper-2", "Header");
    private final static QName _Fault_QNAME = new QName("ec:services:wsdl:DocumentWrapper-2", "Fault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.services.wsdl.documentwrapper_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StoreDocumentWrapperRequest }
     * 
     */
    public StoreDocumentWrapperRequest createStoreDocumentWrapperRequest() {
        return new StoreDocumentWrapperRequest();
    }

    /**
     * Create an instance of {@link StoreDocumentWrapperResponse }
     * 
     */
    public StoreDocumentWrapperResponse createStoreDocumentWrapperResponse() {
        return new StoreDocumentWrapperResponse();
    }

    /**
     * Create an instance of {@link RetrieveDocumentWrapperRequestRequest }
     * 
     */
    public RetrieveDocumentWrapperRequestRequest createRetrieveDocumentWrapperRequestRequest() {
        return new RetrieveDocumentWrapperRequestRequest();
    }

    /**
     * Create an instance of {@link RetrieveDocumentWrapperRequestResponse }
     * 
     */
    public RetrieveDocumentWrapperRequestResponse createRetrieveDocumentWrapperRequestResponse() {
        return new RetrieveDocumentWrapperRequestResponse();
    }

    /**
     * Create an instance of {@link DeleteDocumentWrapperRequestRequest }
     * 
     */
    public DeleteDocumentWrapperRequestRequest createDeleteDocumentWrapperRequestRequest() {
        return new DeleteDocumentWrapperRequestRequest();
    }

    /**
     * Create an instance of {@link DeleteDocumentWrapperRequestResponse }
     * 
     */
    public DeleteDocumentWrapperRequestResponse createDeleteDocumentWrapperRequestResponse() {
        return new DeleteDocumentWrapperRequestResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:services:wsdl:DocumentWrapper-2", name = "Header")
    public JAXBElement<HeaderType> createHeader(HeaderType value) {
        return new JAXBElement<HeaderType>(_Header_QNAME, HeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "ec:services:wsdl:DocumentWrapper-2", name = "Fault")
    public JAXBElement<FaultType> createFault(FaultType value) {
        return new JAXBElement<FaultType>(_Fault_QNAME, FaultType.class, null, value);
    }

}
