package eu.europa.ec.etrustex.webaccess.business.queue.status;

public interface SendStatusHandler {

    void handleSendStatus(Long messageStatusId) throws Exception;

    void handleError(Long messageStatusId);
}
