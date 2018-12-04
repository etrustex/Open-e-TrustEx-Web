package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * @author: micleva
 * @date: 10/2/13 10:22 AM
 * @project: ETX
 */
@Component
public class PartyPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private PartyManager partyManager;

    @Override
    public String getAsText() {
        Party party = (Party) getValue();
        return party.getNodeName();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Party party = partyManager.getWebManagedPartyByNodeName(text);
        setValue(party);
    }
}
