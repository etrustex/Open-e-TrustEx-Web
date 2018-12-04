package eu.europa.ec.etrustex.webaccess.webservice.provider.business;

import eu.europa.ec.etrustex.webaccess.utils.UserRegistrationWSScenarios;

import java.util.List;

/**
 * @author: micleva
 * @date: 1/14/13 3:13 PM
 * @project: ETX
 */
public interface UserRegistrationServiceBO {

    UserRegistrationWSScenarios handleAssignUserParty(String userLogin, String userFirstName, String userLastName, String partyRefId, String newPartyDisplayName, String application, String eMail);

    List<String> findAvailablePartiesPerBusiness(String business);
}
