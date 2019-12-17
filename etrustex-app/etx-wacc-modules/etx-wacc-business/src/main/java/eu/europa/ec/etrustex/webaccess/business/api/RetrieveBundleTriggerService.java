package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;

public interface RetrieveBundleTriggerService {

    void triggerRetrieveMessage(String localPartyName, String remotePartyName, String messageUuid, MessageType messageType) throws EtxException;
}
