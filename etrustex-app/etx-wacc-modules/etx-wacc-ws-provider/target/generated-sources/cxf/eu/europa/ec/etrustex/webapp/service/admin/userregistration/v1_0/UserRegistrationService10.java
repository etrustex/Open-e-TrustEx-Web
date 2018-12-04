package eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1_0;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2018-04-19T14:24:54.209+02:00
 * Generated source version: 3.1.10
 * 
 */
@WebServiceClient(name = "UserRegistrationService-1.0", 
                  wsdlLocation = "classpath:META-INF/wsdl/ETX-UserRegistration-v1.0.wsdl",
                  targetNamespace = "http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0") 
public class UserRegistrationService10 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", "UserRegistrationService-1.0");
    public final static QName ECEtxUserManagementServicePortTypePort = new QName("http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0", "ECEtxUserManagementServicePortTypePort");
    static {
        URL url = UserRegistrationService10.class.getClassLoader().getResource("META-INF/wsdl/ETX-UserRegistration-v1.0.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(UserRegistrationService10.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:META-INF/wsdl/ETX-UserRegistration-v1.0.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public UserRegistrationService10(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserRegistrationService10(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserRegistrationService10() {
        super(WSDL_LOCATION, SERVICE);
    }
    




    /**
     *
     * @return
     *     returns ECEtxUserManagementServicePortType
     */
    @WebEndpoint(name = "ECEtxUserManagementServicePortTypePort")
    public ECEtxUserManagementServicePortType getECEtxUserManagementServicePortTypePort() {
        return super.getPort(ECEtxUserManagementServicePortTypePort, ECEtxUserManagementServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ECEtxUserManagementServicePortType
     */
    @WebEndpoint(name = "ECEtxUserManagementServicePortTypePort")
    public ECEtxUserManagementServicePortType getECEtxUserManagementServicePortTypePort(WebServiceFeature... features) {
        return super.getPort(ECEtxUserManagementServicePortTypePort, ECEtxUserManagementServicePortType.class, features);
    }

}
