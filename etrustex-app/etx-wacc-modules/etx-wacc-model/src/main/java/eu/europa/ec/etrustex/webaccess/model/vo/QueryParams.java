package eu.europa.ec.etrustex.webaccess.model.vo;

import eu.europa.ec.etrustex.webaccess.model.Party;

public abstract class QueryParams {
    private Party party;

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
