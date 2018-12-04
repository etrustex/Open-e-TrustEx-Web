/**
 *
 */
package eu.europa.ec.etrustex.webaccess.webservice.provider;

import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.utils.UserRegistrationWSScenarios;
import eu.europa.ec.etrustex.webaccess.webservice.provider.business.UserRegistrationServiceBO;
import eu.europa.ec.etrustex.webaccess.webservice.utils.UserRegistrationConverter;
import eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1.*;
import eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1_0.ECEtxUserManagementServicePortType;
import org.apache.cxf.annotations.SchemaValidation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import java.util.List;


/**
 * @author apladap
 */
@RolesAllowed("USER-REGISTRATION-SERVICE-ROLE")
@Component("userRegistrationServiceBean")
@SchemaValidation(type = SchemaValidation.SchemaValidationType.IN)
@WebService(portName = "ECEtxUserManagementServicePortTypePort", serviceName = "UserRegistrationService-1.0",
        targetNamespace = "http://ec.europa.eu/etrustex/webapp/service/admin/userregistration/v1.0",
        wsdlLocation = "META-INF/wsdl/ETX-UserRegistration-v1.0.wsdl",
        endpointInterface = "eu.europa.ec.etrustex.webapp.service.admin.userregistration.v1_0.ECEtxUserManagementServicePortType")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class UserRegistrationServiceBean extends SpringBeanAutowiringSupport implements ECEtxUserManagementServicePortType {

    private final static Logger logger = Logger.getLogger(UserRegistrationServiceBean.class);

    @Autowired
    private UserRegistrationServiceBO userRegistrationServiceBO;

    @PostConstruct
    private void init() {
        System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "false");
    }

    /* (non-Javadoc)
      * @see eu.europa.ec.digit.etx.user_management.v1.ECEtxUserManagementServicePortType#listAvailableParty(eu.europa.ec.digit.etx.user_management.v1.ListAvailablePartyReq)
      */
    @Override
    public ListAvailablePartiesResp listAvailableParties(ListAvailablePartiesReq parameters) {
        String application = parameters.getApplication();
        ListAvailablePartiesResp response = null;
        try {
            response = new ListAvailablePartiesResp();

            List<String> list = userRegistrationServiceBO.findAvailablePartiesPerBusiness(application);
            for (String party : list) {
                response.getListAvailableParties().add(UserRegistrationConverter.convertPartyToListAvailableParties(party));
            }
        } catch (Exception e) {
            logger.warn("Failed to process listAvailableParties for application:  " + application, e);
            throw new RuntimeException(e);
        }

        return response;
    }

    /* (non-Javadoc)
      * @see eu.europa.ec.digit.etx.user_management.v1.ECEtxUserManagementServicePortType#assignUserParty(eu.europa.ec.digit.etx.user_management.v1.AssignUserPartyReq)
      */
    @Override
    public AssignUserPartyResp assignUserParty(AssignUserPartyReq parameters) {

        AssignUserPartyResp response = null;
        try {
            AssignUserParty assignUserParty = parameters.getAssignUserParty();
            UserRegistrationWSScenarios registrationAttemptResponse = userRegistrationServiceBO.handleAssignUserParty(
                    assignUserParty.getUserLogin(),
                    assignUserParty.getUserFirstName(),
                    assignUserParty.getUserLastName(),
                    assignUserParty.getPartyID(),
                    assignUserParty.getNewPartyDisplayName(),
                    assignUserParty.getApplication(),
                    assignUserParty.getUserEmail());

            response = new AssignUserPartyResp();

            switch (registrationAttemptResponse) {
                case PARTY_MAPPED_SUCCESSFULLY:
                    response.setStatus(AssignUserPartyStatus.SUCCESS);
                    break;
                case PARTY_UNAVAILABLE:
                    response.setStatus(AssignUserPartyStatus.PARTY_NOT_AVAILABLE);
                    break;
                case PARTY_UNKNOWN:
                    response.setStatus(AssignUserPartyStatus.PARTY_DOES_NOT_EXIST);
                    break;
                case USER_ALREADY_ASSIGNED:
                    response.setStatus(AssignUserPartyStatus.USER_ALREADY_ASSIGNED);
                    break;
                case USER_EMAIL_REQUIRED:
                    String errorMsg = "assignUserParty: user email is required for users with party id: " + assignUserParty.getPartyID();
                    logger.warn(errorMsg);
                    throw new EtxException(errorMsg);
                default:
                    logger.warn("Unknown Registration Attempt Response received: " + registrationAttemptResponse + "; Mapping it to PARTY_NOT_AVAILABLE");
                    response.setStatus(AssignUserPartyStatus.PARTY_NOT_AVAILABLE);
                    break;
            }
        } catch (Exception e) {
            logger.warn("Failed to process assignUserParty ", e);
            throw new RuntimeException(e);
        }

        return response;
    }


}
