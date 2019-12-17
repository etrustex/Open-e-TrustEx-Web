package eu.europa.ec.etrustex.webaccess.webservice.provider.business;

import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.utils.UserRegistrationWSScenarios;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: micleva
 * @date: 1/14/13 3:13 PM
 * @project: ETX
 */
@Service
@Transactional
public class UserRegistrationServiceBOImpl implements UserRegistrationServiceBO {

    Logger logger = Logger.getLogger(UserRegistrationServiceBOImpl.class);

    @Autowired
    private PartyManager partyManager;

    @Override
    public UserRegistrationWSScenarios handleAssignUserParty(String userLogin, String userFirstName, String userLastName, String partyRefId, String newPartyDisplayName, String application, String eMail) {
        UserRegistrationWSScenarios registrationAttemptResponse = partyManager.assignUserToParty(userLogin,
                userFirstName,
                userLastName,
                partyRefId,
                newPartyDisplayName,
                application,
                eMail);

        return registrationAttemptResponse;
    }

    @Override
    public List<String> findAvailablePartiesPerBusiness(String business) {
        return partyManager.findAvailablePartiesPerBusiness(business);
    }
}
