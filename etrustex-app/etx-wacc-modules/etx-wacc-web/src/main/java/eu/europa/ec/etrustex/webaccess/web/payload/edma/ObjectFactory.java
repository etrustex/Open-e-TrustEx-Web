
package eu.europa.ec.etrustex.webaccess.web.payload.edma;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.europa.ec.etrustex.webaccess.web.payload.edma package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.europa.ec.etrustex.webaccess.web.payload.edma
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Documents }
     * 
     */
    public Documents createDocuments() {
        return new Documents();
    }

    /**
     * Create an instance of {@link ETrustExEdmaMdDocument }
     * 
     */
    public ETrustExEdmaMdDocument createETrustExEdmaMdDocument() {
        return new ETrustExEdmaMdDocument();
    }

    /**
     * Create an instance of {@link ETrustExEdmaMd }
     * 
     */
    public ETrustExEdmaMd createETrustExEdmaMd() {
        return new ETrustExEdmaMd();
    }

    /**
     * Create an instance of {@link ETrustExEdmaMdOutbound }
     * 
     */
    public ETrustExEdmaMdOutbound createETrustExEdmaMdOutbound() {
        return new ETrustExEdmaMdOutbound();
    }

    /**
     * Create an instance of {@link ETrustExEdmaMdInbound }
     * 
     */
    public ETrustExEdmaMdInbound createETrustExEdmaMdInbound() {
        return new ETrustExEdmaMdInbound();
    }

    /**
     * Create an instance of {@link ETrustExEdmaMdCompany }
     * 
     */
    public ETrustExEdmaMdCompany createETrustExEdmaMdCompany() {
        return new ETrustExEdmaMdCompany();
    }

}
