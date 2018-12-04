package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.Business;
import eu.europa.ec.etrustex.webaccess.model.Party;

import java.util.List;

/**
 * DAO for parties and associated classes.
 * 
 * @author apladap
 * 
 */
public interface PartyDAO extends AbstractDAO<Party, Long>{

	/**
     * Search for particular web managed party.
     *
     * @param partyNodeName - requested party node name
     */
    Party getWebManagedPartyByNodeName(String partyNodeName);

    /**
     * Search for particular remote party.
     *
     * @param partyNodeName - requested party node name
     * @param busId    - requested business id
     */
    Party getRemotePartyByNodeNameBusId(String partyNodeName, Long busId);

    /**
	 * Search for available parties/application
	 *
	 * @param businessName - The application name for which the available parties are returned
	 */
	List<String> getAvailablePartiesPerBusiness(String businessName);

	/**
	 * Checks if the supplied party is already assigned to a user
	 * 
	 * @param referenceId - The reference id of the party we need to check if it's available
	 */
	boolean isPartyAlreadyAssigned(String referenceId);

    List<Party> getPartiesOfBusiness(Business business);

    List<Party> getAllParties();

    List<Party> getRemoteParties(Party party);

}