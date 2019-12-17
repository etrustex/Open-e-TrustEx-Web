package eu.europa.ec.etrustex.services.business;

import eu.europa.ec.etrustex.webaccess.model.Party;

import java.util.List;

/**
 * Service layer for parties.
 */

public interface PartyService {

    List<Party> getPartiesByUser(String userName);

    List<Party> getAllParties();

    Party getPartyById(Long partyId);

}
