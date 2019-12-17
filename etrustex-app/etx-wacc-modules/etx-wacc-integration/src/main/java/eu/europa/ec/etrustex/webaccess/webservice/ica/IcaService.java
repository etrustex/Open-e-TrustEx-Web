package eu.europa.ec.etrustex.webaccess.webservice.ica;

import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;

import java.util.List;

public interface IcaService {

    int MAX_CONCURRENT_TRANSMISSIONS = 3;

    List<NodeIcaDetails> retrieveICADetails(String nodeUserName, String nodePassword, String loggedInUserParty, String senderParty, String receiverParty);

    public void fillIcaWrapperPool();

}
