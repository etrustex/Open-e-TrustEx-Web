package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Business;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.persistence.PartyDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author apladap
 */
@Repository
public class PartyDAOImpl extends AbstractDAOImpl<Party, Long> implements PartyDAO {

    private final static String QL_FIND_AVAILABLE_PARTIES_FOR_APPLICATION = "SELECT p.nodeName FROM Party p " +
            "WHERE p.business.name = :businessName " +
            "AND p.activeState = 1 " +
            "AND p.webManaged = 1 " +
            "AND p.id NOT IN " +
            "(SELECT ur.party.id FROM UserRole ur WHERE ur.party.id IS NOT NULL AND ur.activeState = 1) " +
            "ORDER BY p.createdOn ASC";

    private final static String QL_IS_PARTY_UNASSIGNED = "SELECT p FROM Party p " +
            "WHERE p.nodeName = :nodeName " +
            "AND p.activeState = 1 " +
            "AND p.webManaged = 1 " +
            "AND p.id NOT IN " +
            "(SELECT ur.party.id FROM UserRole ur WHERE ur.party.id IS NOT NULL AND ur.activeState = 1) ";

    @Override
    public Party getWebManagedPartyByNodeName(String partyNodeName) {

        TypedQuery<Party> dataQuery = entityManager.createNamedQuery("findWebManagedPartyByNodeName", Party.class);
        dataQuery.setParameter("nodeName", partyNodeName);

        Party party;
        try {
            party = dataQuery.getSingleResult();
        } catch (NoResultException e) {
            party = null;
        }

        return party;
    }

    @Override
    public Party getRemotePartyByNodeNameBusId(String partyNodeName, Long busId) {
        TypedQuery<Party> dataQuery = entityManager.createNamedQuery("findRemotePartyByNodeNameBusId", Party.class);
        dataQuery.setParameter("nodeName", partyNodeName);
        dataQuery.setParameter("busId", busId);

        Party party;
        try {
            party = dataQuery.getSingleResult();
        } catch (NoResultException e) {
            party = null;
        }

        return party;
    }

    @Override
    public List<String> getAvailablePartiesPerBusiness(String businessName) {

        TypedQuery<String> dataQuery = entityManager.createQuery(QL_FIND_AVAILABLE_PARTIES_FOR_APPLICATION, String.class);
        dataQuery.setParameter("businessName", businessName);

        return dataQuery.getResultList();
    }

    @Override
    public List<Party> getPartiesOfBusiness(Business business) {

        TypedQuery<Party> dataQuery = entityManager.createNamedQuery("findAvailablePartiesForBusiness", Party.class);
        dataQuery.setParameter("business", business);

        return dataQuery.getResultList();
    }

    @Override
    public boolean isPartyAlreadyAssigned(String nodeName) {

        Query dataQuery = entityManager.createQuery(QL_IS_PARTY_UNASSIGNED);
        dataQuery.setParameter("nodeName", nodeName);

        try {
            dataQuery.getSingleResult();
        } catch (NoResultException e1) {
            return true;
        }

        return false;
    }

    @Override
    public List<Party> getAllParties() {

        TypedQuery<Party> dataQuery = entityManager.createNamedQuery("findAllParties", Party.class);

        return dataQuery.getResultList();
    }

    @Override
    public List<Party> getRemoteParties(Party party) {
        TypedQuery<Party> dataQuery = entityManager.createNamedQuery("findRemoteParties", Party.class);
        dataQuery.setParameter("party", party);

        return dataQuery.getResultList();
    }

}
