package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO;
import eu.europa.ec.etrustex.webaccess.persistence.PartyIcaDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PartyIcaDAOImpl extends AbstractDAOImpl<PartyIca, Long> implements PartyIcaDAO {

    @Override
    public List<PartyIca> getIcasByParty(Party party) {
        TypedQuery<PartyIca> dataQuery = entityManager.createNamedQuery("findICAsByParty", PartyIca.class);
        dataQuery.setParameter("party", party);

        return dataQuery.getResultList();
    }

    @Override
    public PartyIca getIcaBySenderReceiver(String sender, String receiver) {
        TypedQuery<PartyIca> dataQuery = entityManager.createNamedQuery("findICAsBySenderReceiver", PartyIca.class);
        dataQuery.setParameter("sender", sender);
        dataQuery.setParameter("receiver", receiver);

        PartyIca ica;

        try {
            ica = dataQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return ica;
    }

    @Override
    public PartyIca getActiveIcaBySenderReceiver(String sender, String receiver) {
        TypedQuery<PartyIca> dataQuery = entityManager.createNamedQuery("findActiveICAsBySenderReceiver", PartyIca.class);
        dataQuery.setParameter("sender", sender);
        dataQuery.setParameter("receiver", receiver);

        List<PartyIca> icas = dataQuery.getResultList();

        return !icas.isEmpty() ? icas.get(0) : null;
    }

    @Override
    public List<PartyIca> getIcasByActiveParties() {
        TypedQuery<PartyIca> dataQuery = entityManager.createNamedQuery("findICAsByActiveParties", PartyIca.class);

        return dataQuery.getResultList();
    }

    @Override
    public List<PartyIcaVO> getAllIcasSenderReceiver() {
        TypedQuery<PartyIcaVO> dataQuery = entityManager.createNamedQuery("findAllICAsSenderReceiver", PartyIcaVO.class);

        return dataQuery.getResultList();
    }

    @Override
    public boolean icaExists(String sender, String receiver) {
        TypedQuery<Long> dataQuery = entityManager.createNamedQuery("countActiveICAsBySenderReceiver", Long.class);
        dataQuery.setParameter("sender", sender);
        dataQuery.setParameter("receiver", receiver);

        return dataQuery.getSingleResult() > 0;
    }
}
