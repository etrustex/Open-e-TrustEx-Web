package eu.europa.ec.etrustex.services.business;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;

import java.util.List;

/**
 * Service layer for icas.
 */

public interface IcaService {

    /**
     * Gets ICAS associated to one party.
     *
     * @param party Party.
     * @return Associated ICAs.
     */

    List<PartyIca> getIcasByParty(Party party);

}
