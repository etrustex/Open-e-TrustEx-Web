package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.utils.EtxException;

public interface SendStatusTriggerService {
    void triggerSendStatus(Long messageStatusId) throws EtxException;
}
