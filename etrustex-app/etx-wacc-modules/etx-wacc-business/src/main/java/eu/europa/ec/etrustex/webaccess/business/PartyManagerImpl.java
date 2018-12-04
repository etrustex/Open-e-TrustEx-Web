package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.BusinessManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.api.UserManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.*;
import eu.europa.ec.etrustex.webaccess.utils.UserRegistrationWSScenarios;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartyManagerImpl implements PartyManager {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private BusinessUserConfigDAO businessUserConfigDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Autowired
    private BusinessManager businessManager;

    @Autowired
    private UserManager userManager;

    private static final Logger logger = Logger.getLogger(PartyManagerImpl.class);

    /* (non-Javadoc)
      * @see PartyManager#findAvailablePartiesPerBusiness(java.lang.String)
      */
    @Override
    public List<String> findAvailablePartiesPerBusiness(String business) throws RuntimeException {
        return partyDAO.getAvailablePartiesPerBusiness(business);
    }

    /* (non-Javadoc)
      * @see PartyManager#assignUserToParty()
      */
    @Override
    @Transactional(readOnly = false)
    public UserRegistrationWSScenarios assignUserToParty(String userLogin, String userFirstName, String userLastName,
                                                         String partyNodeName, String newPartyDisplayName, String businessName, String eMail) throws RuntimeException {

        logger.info("Assign user [" + userLogin + "] to party [" + partyNodeName + "], business [" + businessName + "]");

        Party requestedParty = partyDAO.getWebManagedPartyByNodeName(partyNodeName);
        if (requestedParty == null) {
            return UserRegistrationWSScenarios.PARTY_UNKNOWN;
        }
        Business business = requestedParty.getBusiness();

        if (partyDAO.isPartyAlreadyAssigned(partyNodeName)) {
            return UserRegistrationWSScenarios.PARTY_UNAVAILABLE;
        }

        if (!business.getName().equals(businessName)) {
            return UserRegistrationWSScenarios.PARTY_UNKNOWN;
        }

        if ((eMail == null || eMail.trim().isEmpty())
                && businessManager.isBusinessEmailEnforceEnabledFor(business)) {
            return UserRegistrationWSScenarios.USER_EMAIL_REQUIRED;
        }

        if (StringUtils.isNotBlank(newPartyDisplayName) && !newPartyDisplayName.equals(requestedParty.getDisplayName())) {
            requestedParty.setDisplayName(newPartyDisplayName);
            partyDAO.update(requestedParty);
        }

        User requestedUser = userDAO.getUser(userLogin);
        if (requestedUser == null) {
            requestedUser = new User();
            requestedUser.setLogin(userLogin);
            requestedUser.setName(userLastName + " " + userFirstName);
            userDAO.save(requestedUser);
        } else {
            requestedUser.setActiveState(true);
            userDAO.update(requestedUser);
        }

        BusinessUserConfig businessUserConfig = businessUserConfigDAO.getBusinessUserConfig(requestedUser, business);
        if (businessUserConfig == null) {
            businessUserConfig = new BusinessUserConfig();
            businessUserConfig.setUser(requestedUser);
            businessUserConfig.setBusiness(business);
            businessUserConfig.setName(userLastName + " " + userFirstName);
            businessUserConfig.setEmail(eMail.trim());
            businessUserConfig.setNotificationsEnabled(true);
            businessUserConfigDAO.save(businessUserConfig);
        }

        List<UserRole> operators = userRoleDAO.getUserRolesWithPrivilege(userLogin, business, Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        if (operators.size() == 0) {
            UserRole userRole = new UserRole();
            userRole.setUser(requestedUser);
            userRole.setParty(requestedParty);
            userRole.setRole(roleDAO.getRole(Role.Type.PARTY_ADMIN));
            userRoleDAO.save(userRole);
            return UserRegistrationWSScenarios.PARTY_MAPPED_SUCCESSFULLY;
        } else {
            return UserRegistrationWSScenarios.USER_ALREADY_ASSIGNED;
        }
    }

    @Override
    public Party getWebManagedPartyByNodeName(String partyNodeName) {
        return partyDAO.getWebManagedPartyByNodeName(partyNodeName);
    }

    @Override
    public Party getRemotePartyByNodeNameBusId(String partyNodeName, Long busId) {
        return partyDAO.getRemotePartyByNodeNameBusId(partyNodeName, busId);
    }

    @Override
    public Party getPartyById(Long partyId) {
        return partyDAO.findById(partyId);
    }

    @Override
    public List<Party> getPartiesOfBusiness(Business business) {
        return partyDAO.getPartiesOfBusiness(business);
    }

    @Override
    public List<Party> getAllParties() {
        return partyDAO.getAllParties();
    }

    @Override
    public List<Party> getRemoteParties(Party party) {
        return partyDAO.getRemoteParties(party);
    }

    @Override
    @Transactional(readOnly = false)
    public Party saveOrUpdate(Party party){
        logger.info("Start saving party [" + party.getNodeName() + "]");
        if (party.getId() != null) {
            partyDAO.update(party);
        } else {
            partyDAO.save(party);
        }
        logger.info("Saving party [" + party.getNodeName() + "] completed");
        return party;
    }

    @Override
    public boolean isNotificationAllowed(Party party, Notification.NotificationType notificationType) {
        switch (notificationType) {
            case MESSAGE_RECEIVED_EMAIL:
            case WARNING_EMAIL:
                return party != null && party.getEmail() != null && party.getNotificationsEnabled();
            case STATUS_RECEIVED_EMAIL:
                return party != null && party.getStatusNotificationEmail() != null && party.getStatusNotificationEnabled();
            default:
                logger.warn("notification type unknown: " + notificationType);
                return false;
        }
    }

}
