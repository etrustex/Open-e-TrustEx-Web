package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO;
import eu.europa.ec.etrustex.webaccess.persistence.PartyIcaDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IcaManagerImpl implements IcaManager {

    @Autowired
    private PartyIcaDAO partyIcaDAO;

    private static final Logger logger = Logger.getLogger(IcaManagerImpl.class);

    @Override
    public PartyIca findById(Long id) {
        return partyIcaDAO.findById(id);
    }

    @Override
    public List<PartyIca> getIcasByParty(Party party) {
        return partyIcaDAO.getIcasByParty(party);
    }

    @Override
    public PartyIca getIcaBySenderReceiver(String sender, String receiver) {
        return partyIcaDAO.getIcaBySenderReceiver(sender, receiver);
    }

    @Override
    public PartyIca getActiveIcaBySenderReceiver(String sender, String receiver) {
        return partyIcaDAO.getActiveIcaBySenderReceiver(sender, receiver);
    }

    @Override
    public List<PartyIca> getIcasByActiveParties() {
        return partyIcaDAO.getIcasByActiveParties();
    }

    @Override
    public List<PartyIcaVO> getAllIcasSenderReceiver() {
        return partyIcaDAO.getAllIcasSenderReceiver();
    }

    @Override
    @Transactional(readOnly = false)
    public PartyIca saveOrUpdate(PartyIca partyIca) {
        if (partyIca.getId() != null) {
            partyIcaDAO.update(partyIca);
        } else {
            partyIcaDAO.save(partyIca);
        }
        logger.info("Save ICA [" + partyIca.toString() + "] isNew: " + partyIca.getId() == null);
        return partyIca;
    }

    @Override
    public boolean icaExists(String sender, String receiver) {
        return partyIcaDAO.icaExists(sender, receiver);
    }
}
