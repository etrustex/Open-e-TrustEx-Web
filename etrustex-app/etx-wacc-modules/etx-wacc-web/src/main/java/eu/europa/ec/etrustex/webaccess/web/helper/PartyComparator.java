package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.model.Party;

import java.util.Comparator;

public class PartyComparator implements Comparator<Party> {
    @Override
    public int compare(Party party1, Party party2) {
        return party1.getDisplayName().compareTo(party2.getDisplayName());
    }
}
