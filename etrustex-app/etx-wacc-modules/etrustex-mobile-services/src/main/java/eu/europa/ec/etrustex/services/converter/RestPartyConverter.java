package eu.europa.ec.etrustex.services.converter;

import eu.europa.ec.etrustex.services.model.RestParties;
import eu.europa.ec.etrustex.services.model.RestParty;
import eu.europa.ec.etrustex.webaccess.model.Party;

import java.util.List;

/**
 * Rest party converter.
 */

public class RestPartyConverter {

    private static final RestPartyConverter INSTANCE = new RestPartyConverter();

    /**
     * Gets singleton instance.
     *
     * @return Singleton instance.
     */

    public static RestPartyConverter getInstance() {
        return INSTANCE;
    }

    private RestPartyConverter() {
    }

    /**
     * Converts a list of Party entities to a REST Parties entity.
     *
     * @param parties List of Party entities.
     * @return REST Parties entity.
     */

    public RestParties convertToRestParties(List<Party> parties) {
        RestParties restParties = new RestParties();

        if (parties != null) {
            for (Party party : parties) {
                RestParty restParty = new RestParty();
                restParty.setId(party.getId() != null ? String.valueOf(party.getId()) : null);
                restParty.setName(party.getDisplayName());

                restParties.addPartyListItem(restParty);
            }
        }

        return restParties;
    }

}
