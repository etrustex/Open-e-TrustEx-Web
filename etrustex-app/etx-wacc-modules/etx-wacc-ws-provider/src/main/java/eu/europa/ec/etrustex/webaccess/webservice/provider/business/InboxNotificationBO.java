package eu.europa.ec.etrustex.webaccess.webservice.provider.business;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;

public interface InboxNotificationBO {

    Message handleMessage(String loggedInUser, Message message, String localPartyName, String remotePartyName) throws EtxException;

}
