
package ec.services.wsdl.applicationresponse_2;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.1.10
 * 2018-04-19T14:24:04.011+02:00
 * Generated source version: 3.1.10
 */

@WebFault(name = "Fault", targetNamespace = "ec:services:wsdl:ApplicationResponse-2")
public class FaultResponse extends Exception {
    
    private oasis.names.specification.ubl.schema.xsd.fault_1.FaultType fault;

    public FaultResponse() {
        super();
    }
    
    public FaultResponse(String message) {
        super(message);
    }
    
    public FaultResponse(String message, Throwable cause) {
        super(message, cause);
    }

    public FaultResponse(String message, oasis.names.specification.ubl.schema.xsd.fault_1.FaultType fault) {
        super(message);
        this.fault = fault;
    }

    public FaultResponse(String message, oasis.names.specification.ubl.schema.xsd.fault_1.FaultType fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public oasis.names.specification.ubl.schema.xsd.fault_1.FaultType getFaultInfo() {
        return this.fault;
    }
}
