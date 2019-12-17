package eu.europa.ec.etrustex.webaccess.persistence;

import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.vo.PartyIcaVO;

import java.util.List;

public interface PartyIcaDAO extends AbstractDAO<PartyIca, Long> {

    /**
     * Gets ICAS associated to one party.
     *
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
     * Gets ICAs with local and remote parties active.
     * @return All ICAs.
     */

    List<PartyIca> getIcasByActiveParties();

    /**
     * Gets sender and receiver of all ICAs.
     * @return Sender and receiver of ICAs.
     */

    List<PartyIcaVO> getAllIcasSenderReceiver();

    /**
     * Counts the number of existing ICAs (0 or 1) by sender and receiver.
     *
     * @param sender   Sender.
     * @param receiver Receiver.
     * @return True if ICA exists, false otherwise.
     */

    boolean icaExists(String sender, String receiver);

}
