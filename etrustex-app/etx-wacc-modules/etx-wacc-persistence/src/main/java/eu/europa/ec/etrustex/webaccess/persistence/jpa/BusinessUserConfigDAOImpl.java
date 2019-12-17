package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.persistence.BusinessUserConfigDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BusinessUserConfigDAOImpl extends AbstractDAOImpl<BusinessUserConfig, Long> implements BusinessUserConfigDAO {

    private final static String QL_FIND_CONFIG_BY_LOGIN_BUSINESS = "SELECT buc FROM BusinessUserConfig buc " +
            "WHERE buc.user = :user AND buc.business = :business AND buc.activeState = :activeState";

    private final static String QL_FIND_CONFIGS_BY_PARTY_AND_PRIVILEGE = "SELECT DISTINCT buc FROM BusinessUserConfig buc " +
        "INNER JOIN buc.user u " +
        "INNER JOIN u.userRoles ur " +
        "INNER JOIN ur.role.privileges p " +
        "WHERE buc.business = ur.party.business " +
        "AND ur.party = :party " +
        "AND p.type = :privilegeType " +
        "AND ur.party.activeState = 1 " +
        "AND ur.activeState = 1 " +
        "AND buc.business.activeState = 1 " +
        "AND u.activeState = 1 " +
        "AND buc.activeState = 1";

    private final static String QL_FIND_CONFIGS_BY_BUSINESS_AND_PRIVILEGE = "SELECT DISTINCT buc FROM BusinessUserConfig buc " +
        "INNER JOIN buc.user u " +
        "INNER JOIN u.userRoles ur " +
        "INNER JOIN ur.role.privileges p " +
        "WHERE buc.business = ur.business " +
        "AND ur.business = :business " +
        "AND p.type = :privilegeType " +
        "AND ur.activeState = 1 " +
        "AND buc.business.activeState = 1 " +
        "AND u.activeState = 1 " +
        "AND buc.activeState = 1";

    private final static String QL_FIND_SYSTEM_CONFIGS_BY_BUSINESS_AND_PRIVILEGE = "SELECT DISTINCT buc FROM BusinessUserConfig buc " +
        "INNER JOIN buc.user u " +
        "INNER JOIN u.userRoles ur " +
        "INNER JOIN ur.role.privileges pr " +
        "WHERE buc.business = :business " +
        "AND pr.type = :privilegeType " +
        "AND ur.party IS NULL " +
        "AND ur.business IS NULL " +
        "AND ur.activeState = 1 " +
        "AND buc.business.activeState = 1 " +
        "AND u.activeState = 1 " +
        "AND buc.activeState = 1";

    @Override
    public BusinessUserConfig getBusinessUserConfig(User user, Business business) {
        TypedQuery<BusinessUserConfig> dataQuery = entityManager.createQuery(QL_FIND_CONFIG_BY_LOGIN_BUSINESS, BusinessUserConfig.class);
        dataQuery.setParameter("user", user);
        dataQuery.setParameter("business", business);
        dataQuery.setParameter("activeState", true);

        try {
            return dataQuery.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<BusinessUserConfig> getBusinessUserConfigs(Party party, Privilege.Type type) {
        TypedQuery<BusinessUserConfig> dataQuery = entityManager.createQuery(QL_FIND_CONFIGS_BY_PARTY_AND_PRIVILEGE, BusinessUserConfig.class);
        dataQuery.setParameter("party", party);
        dataQuery.setParameter("privilegeType", type.name());

        return dataQuery.getResultList();
    }

    @Override
    public List<BusinessUserConfig> getBusinessUserConfigs(Business business, Privilege.Type type) {
        TypedQuery<BusinessUserConfig> dataQuery = entityManager.createQuery(QL_FIND_CONFIGS_BY_BUSINESS_AND_PRIVILEGE, BusinessUserConfig.class);
        dataQuery.setParameter("business", business);
        dataQuery.setParameter("privilegeType", type.name());

        return dataQuery.getResultList();
    }

    @Override
    public List<BusinessUserConfig> getSystemScopeBusinessUserConfigs(Business business, Privilege.Type type) {
        TypedQuery<BusinessUserConfig> dataQuery = entityManager.createQuery(QL_FIND_SYSTEM_CONFIGS_BY_BUSINESS_AND_PRIVILEGE, BusinessUserConfig.class);
        dataQuery.setParameter("business", business);
        dataQuery.setParameter("privilegeType", type.name());

        return dataQuery.getResultList();
    }
}