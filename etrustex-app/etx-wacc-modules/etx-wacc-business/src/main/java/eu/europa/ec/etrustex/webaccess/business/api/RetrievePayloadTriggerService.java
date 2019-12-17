package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.utils.EtxException;

public interface RetrievePayloadTriggerService {
    void triggerRetrievePayload(String loggedInUser, Long messageId) throws EtxException;
}
