package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.services.business.UserService;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.UserRole;
import eu.europa.ec.etrustex.webaccess.persistence.UserDAO;
import eu.europa.ec.etrustex.webaccess.persistence.UserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Service layer implementation for users.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public User getUserDetails(String login) {
        return userDAO.getUser(login);
    }

    @Override
    public List<UserRole> getUserRoles(User user) {
        return new ArrayList<>(new HashSet<>(userRoleDAO.getUserRoles(user)));
    }

    @Override
    public Map<Party, String> loadBusinessUserConfigNameByAccessiblePartyForRoles(List<UserRole> partyLevelRoles) {
        return userRoleDAO.loadBusinessUserConfigNameByAccessiblePartyForRoles(partyLevelRoles);
    }

    @Override
    public Map<Party, String> loadBusinessUserConfigNameByAccessibleBusinessForRoles(List<UserRole> businessLevelRoles) {
        return userRoleDAO.loadBusinessUserConfigNameByAccessibleBusinessForRoles(businessLevelRoles);
    }

}
