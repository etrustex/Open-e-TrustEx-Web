package eu.europa.ec.etrustex.services.business.impl;

import eu.europa.ec.etrustex.services.business.IcaService;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.persistence.PartyIcaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer implementation for partyIcas.
 */

@Service
@Transactional
public class IcaServiceImpl implements IcaService {

    @Autowired
    private PartyIcaDAO partyIcaDAO;

    @Override
    public List<PartyIca> getIcasByParty(Party party) {
        return partyIcaDAO.getIcasByParty(party);
    }

}
