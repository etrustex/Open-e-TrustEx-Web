package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO;

import java.util.List;

public interface IcaManager {

    /**
     * Finds ICA by id.
     *
     * @param id Id to be found.
     * @return ICA found.
     */

    PartyIca findById(Long id);

    /**
     * Gets ICAS associated to one party.
     * @param party Party.
     * @return Associated ICAs.
     */

    List<PartyIca> getIcasByParty(Party party);

    /**
     * Gets ICA (both activated and deactivated) by sender and receiver.
     * @param sender Sender.
     * @param receiver Receiver.
     * @return ICA found.
     */

    PartyIca getIcaBySenderReceiver(String sender, String receiver);

    /**
     * Gets active ICA by sender and receiver.
     * @param sender Sender.
     * @param receiver Receiver.
     * @return ICA found.
     */

    PartyIca getActiveIcaBySenderReceiver(String sender, String receiver);

    /**
     * Gets all ICAs with local and remote parties active.
     * @return All ICAs.
     */

    List<PartyIca> getIcasByActiveParties();

    /**
     * Gets sender and receiver of all ICAs.
     * @return Sender and receiver of ICAs.
     */

    List<PartyIcaVO> getAllIcasSenderReceiver();

    /**
     * Saves or updated ICA.
     * @param partyIca ICA to be saved/updated.
     * @return ICA saved/updated.
     */

    PartyIca saveOrUpdate(PartyIca partyIca);

    /**
     * Counts the number of existing ICAs (0 or 1) by sender and receiver.
     *
     * @param sender   Sender.
     * @param receiver Receiver.
     * @return True if ICA exists, false otherwise.
     */

    boolean icaExists(String sender, String receiver);

}
