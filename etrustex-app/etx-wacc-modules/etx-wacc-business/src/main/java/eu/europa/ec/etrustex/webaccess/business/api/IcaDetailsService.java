package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaKey;

import java.util.Map;

public interface IcaDetailsService {

    /**
     * Get a cached node ICA detail.
     *
     * @return Cached node ICA detail.
     */
    public NodeIcaDetails getIcaDetails(Party webParty, String icaParty);

    /**
     * Reload in memory the ICA Details for a specific sender and receiver.
     *
     * @param webParty       Node sender party name.
     * @param icaParty     Node receiver party name.
     * @return Reloaded node ICA detail.
     */

    public NodeIcaDetails loadIcaDetails(String webParty, String icaParty);

    /**
     * Get cached ICA details.
     *
     * @return Cached map.
     */
    public Map<NodeIcaKey, NodeIcaDetails> getIcasByActivePartiesDetails();

    /**
     * Reload in memory ICA details for all parties.
     *
     * @return Newly ICA details.
     */
    public Map<NodeIcaKey, NodeIcaDetails> loadAllIcaDetails();

    /**
     * Get the node ica details by sender and receiver.
     *
     * @param ica                 Ica.
     * @param extendedCertificate True if extended certificate requested, false otherwise.
     * @return Node ica details.
     */

    public NodeIcaDetails getNodeIcaDetailsBySenderReceiver(PartyIca ica, boolean extendedCertificate);
}
