package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.Business;
import eu.europa.ec.etrustex.webaccess.model.Notification;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.utils.UserRegistrationWSScenarios;

import java.util.List;

public interface PartyManager {

    List<String> findAvailablePartiesPerBusiness(String business) throws RuntimeException;

    UserRegistrationWSScenarios assignUserToParty(String userLogin, String userFirstName, String userLastName, String partyRefId, String newPartyDisplayName, String application, String eMail) throws RuntimeException;

    Party getWebManagedPartyByNodeName(String partyNodeName);

    Party getRemotePartyByNodeNameBusId(String partyNodeName, Long busId);

    List<Party> getPartiesOfBusiness(Business business);

    Party getPartyById(Long partyId);

    List<Party> getAllParties();

    List<Party> getRemoteParties(Party party);

    Party saveOrUpdate(Party party);

    boolean isNotificationAllowed(Party party, Notification.NotificationType notificationType);

}
