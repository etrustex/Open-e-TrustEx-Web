package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.UserRoleDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRoleDAOImpl extends AbstractDAOImpl<UserRole, Long> implements UserRoleDAO {

    private final static String QL_FIND_USER_ROLES_BY_USER = "SELECT ur FROM UserRole ur " +
            "INNER JOIN FETCH ur.role r " +
            "INNER JOIN FETCH r.privileges " +
        "LEFT JOIN FETCH ur.party " +
        "LEFT JOIN FETCH ur.business " +
            "WHERE ur.user = :user AND ur.activeState = 1";

    private final static String QL_FIND_PARTY_USER_ROLES_BY_LOGIN_BUSINESS_ROLE_AND_PRIVILEGE = "SELECT ur FROM UserRole ur " +
        "INNER JOIN ur.user u " +
        "INNER JOIN ur.role r " +
        "INNER JOIN r.privileges p " +
        "WHERE ur.party.business = :business " +
        "AND u.login = :login " +
        "AND p.type = :privilegeType " +
        "AND ur.activeState = 1 ";

    private final static String QL_FIND_USER_ROLE_AND_CONFIG_BY_PARTY_ROLE = "SELECT ur, buc FROM BusinessUserConfig buc " +
            "INNER JOIN FETCH buc.user u " +
        "INNER JOIN u.userRoles ur " +
            "INNER JOIN FETCH ur.role " +
        "WHERE buc.business = ur.party.business " +
        "AND ur.party = :party " +
        "AND ur.role.type = :roleType " +
        "AND ur.activeState = 1 " +
        "AND buc.activeState = 1";

    private final static String QL_FIND_USER_ROLE_AND_CONFIG_BY_BUSINESS_ROLE = "SELECT ur, buc FROM BusinessUserConfig buc " +
            "INNER JOIN FETCH buc.user u " +
        "INNER JOIN u.userRoles ur " +
            "INNER JOIN FETCH ur.role " +
        "WHERE buc.business = :business " +
        "AND ur.business = :business " +
        "AND ur.role.type = :roleType " +
        "AND ur.activeState = 1 " +
        "AND buc.activeState = 1";

    private final static String QL_FIND_USER_ROLE_AND_CONFIG_BY_SYSTEM_ROLE = "SELECT ur, u FROM User u " +
        "INNER JOIN u.userRoles ur " +
            "INNER JOIN FETCH ur.role " +
        "WHERE ur.role.type = :roleType " +
        "AND ur.activeState = 1 " +
        "AND u.activeState = 1";

    private final static String QL_LOAD_BUSINESS_USER_CONF_BY_ACCESSIBLE_ROLES_WITH_PARTIES = "SELECT p, buc.name FROM UserRole ur " +
            "INNER JOIN ur.party p " +
            "INNER JOIN FETCH p.business b " +
            "INNER JOIN b.businessUserConfigs buc " +
            "WHERE ur in (:userRoles) " +
            "AND ur.user.id = buc.user.id " +
            "AND p.activeState = 1 " +
            "AND buc.activeState = 1";

    private final static String QL_LOAD_BUSINESS_USER_CONF_BY_ACCESSIBLE_ROLES_WITH_BUSINESS = "SELECT p, buc.name FROM UserRole ur " +
            "INNER JOIN ur.business b " +
            "INNER JOIN b.parties p " +
            "INNER JOIN FETCH p.business " +
            "INNER JOIN b.businessUserConfigs buc " +
            "WHERE ur in (:userRoles) " +
            "AND ur.user.id = buc.user.id " +
            "AND p.activeState = 1 " +
            "AND buc.activeState = 1";

    private final static String QL_LOAD_USER_ROLE_BY_ID = "SELECT ur FROM UserRole ur " +
            "INNER JOIN FETCH ur.role r " +
            "INNER JOIN FETCH r.privileges " +
            "LEFT JOIN FETCH ur.party p " +
            "LEFT JOIN FETCH p.business " +
            "LEFT JOIN FETCH ur.business " +
            "WHERE ur.id = :userRoleId " +
            "AND ur.activeState = 1 ";

    public List<UserRole> getUserRoles(User user) {
        TypedQuery<UserRole> dataQuery = entityManager.createQuery(QL_FIND_USER_ROLES_BY_USER, UserRole.class);
        dataQuery.setParameter("user", user);

        return dataQuery.getResultList();
    }

    @Override
    public List<UserRole> getUserRolesWithPrivilege(String login, Business business, Privilege.Type privilege) {
        TypedQuery<UserRole> dataQuery = entityManager.createQuery(QL_FIND_PARTY_USER_ROLES_BY_LOGIN_BUSINESS_ROLE_AND_PRIVILEGE, UserRole.class);
        dataQuery.setParameter("login", login);
        dataQuery.setParameter("business", business);
        dataQuery.setParameter("privilegeType", privilege.name());

        return dataQuery.getResultList();
    }

    @Override
    public Map<UserRole, BusinessUserConfig> getUserRoles(Party party, Role.Type type) {
        TypedQuery<Object[]> dataQuery = entityManager.createQuery(QL_FIND_USER_ROLE_AND_CONFIG_BY_PARTY_ROLE, Object[].class);
        dataQuery.setParameter("party", party);
        dataQuery.setParameter("roleType", type.name());

        Map<UserRole, BusinessUserConfig> result = new HashMap<>();
        for (Object[] record : dataQuery.getResultList()) {
            result.put((UserRole) record[0], (BusinessUserConfig) record[1]);
        }

        return result;
    }

    @Override
    public Map<UserRole, BusinessUserConfig> getUserRoles(Business business, Role.Type type) {
        TypedQuery<Object[]> dataQuery = entityManager.createQuery(QL_FIND_USER_ROLE_AND_CONFIG_BY_BUSINESS_ROLE, Object[].class);
        dataQuery.setParameter("business", business);
        dataQuery.setParameter("roleType", type.name());

        Map<UserRole, BusinessUserConfig> result = new HashMap<>();
        for (Object[] record : dataQuery.getResultList()) {
            result.put((UserRole) record[0], (BusinessUserConfig) record[1]);
        }

        return result;
    }

    @Override
    public Map<UserRole, User> getUserRoles(Role.Type type) {
        TypedQuery<Object[]> dataQuery = entityManager.createQuery(QL_FIND_USER_ROLE_AND_CONFIG_BY_SYSTEM_ROLE, Object[].class);
        dataQuery.setParameter("roleType", type.name());

        Map<UserRole, User> result = new HashMap<>();
        for (Object[] record : dataQuery.getResultList()) {
            result.put((UserRole) record[0], (User) record[1]);
        }

        return result;
    }

    @Override
    public Map<Party, String> loadBusinessUserConfigNameByAccessiblePartyForRoles(List<UserRole> partyLevelRoles) {
        if (partyLevelRoles.isEmpty()) {
            return Collections.emptyMap();
        }
        TypedQuery<Object[]> dataQuery = entityManager.createQuery(QL_LOAD_BUSINESS_USER_CONF_BY_ACCESSIBLE_ROLES_WITH_PARTIES, Object[].class);
        dataQuery.setParameter("userRoles", partyLevelRoles);

        Map<Party, String> result = new HashMap<>();
        for (Object[] record : dataQuery.getResultList()) {
            result.put((Party) record[0], (String) record[1]);
        }

        return result;
    }

    @Override
    public Map<Party, String> loadBusinessUserConfigNameByAccessibleBusinessForRoles(List<UserRole> businessLevelRoles) {
        if (businessLevelRoles.isEmpty()) {
            return Collections.emptyMap();
        }
        TypedQuery<Object[]> dataQuery = entityManager.createQuery(QL_LOAD_BUSINESS_USER_CONF_BY_ACCESSIBLE_ROLES_WITH_BUSINESS, Object[].class);
        dataQuery.setParameter("userRoles", businessLevelRoles);

        Map<Party, String> result = new HashMap<>();
        for (Object[] record : dataQuery.getResultList()) {
            result.put((Party) record[0], (String) record[1]);
        }

        return result;
    }

    @Override
    public UserRole getUserRole(Long userRoleId) {
        TypedQuery<UserRole> dataQuery = entityManager.createQuery(QL_LOAD_USER_ROLE_BY_ID, UserRole.class);
        dataQuery.setParameter("userRoleId", userRoleId);

        return dataQuery.getSingleResult();
    }
}
