package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.services.business.PartyService;
import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.services.helper.PartyComparator;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.Privilege;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import eu.europa.ec.etrustex.webaccess.persistence.PartyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service layer implementation for parties.
 */

@Service
@Transactional
public class PartyServiceImpl implements PartyService {

    @Autowired
    private UserService userService;

    @Autowired
    private PartyDAO partyDAO;

    private static final Logger logger = LoggerFactory.getLogger(PartyServiceImpl.class);

    @Override
    public List<Party> getPartiesByUser(String userName) {
        List<Party> userParties = new ArrayList<>();
        User user = userService.getUserDetails(userName);

        if (user == null) {
            logger.warn("The user " + userName + " is not a valid user");
            return null;
        }

        List<UserRole> userRoles = userService.getUserRoles(user);

        if (userRoles == null || userRoles.isEmpty()) {
            logger.warn("The user " + userName + " has no roles defined for this application!");
            return null;
        }

        boolean hasAccessToAllParties = false;
        List<UserRole> partyLevelRoles = new ArrayList<>();
        List<UserRole> businessLevelRoles = new ArrayList<>();

        for (UserRole userRole : userRoles) {
            boolean roleHasPrivilege = false;

            for (Privilege privilege : userRole.getRole().getPrivileges()) {
                if (privilege.getType() == Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES) {
                    roleHasPrivilege = true;
                    break;
                }
            }

            if (roleHasPrivilege) {
                if (userRole.getParty() != null) {
                    partyLevelRoles.add(userRole);
                } else if (userRole.getBusiness() != null) {
                    businessLevelRoles.add(userRole);
                } else {
                    hasAccessToAllParties = true;
                }
            }
        }

        Map<Party, String> businessUserNameByParty = new HashMap<>();

        if (hasAccessToAllParties) {
            List<Party> allParties = getAllParties();

            for (Party party : allParties) {
                businessUserNameByParty.put(party, user.getName());
            }
        } else {
            businessUserNameByParty.putAll(userService.loadBusinessUserConfigNameByAccessiblePartyForRoles(partyLevelRoles));
            businessUserNameByParty.putAll(userService.loadBusinessUserConfigNameByAccessibleBusinessForRoles(businessLevelRoles));
        }

        userParties.addAll(businessUserNameByParty.keySet());
        Collections.sort(userParties, new PartyComparator());

        return userParties;
    }

    @Override
    public List<Party> getAllParties() {
        return partyDAO.getAllParties();
    }

    @Override
    public Party getPartyById(Long partyId) {
        return partyDAO.findById(partyId);
    }

}
