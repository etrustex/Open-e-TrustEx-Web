package eu.europa.ec.etrustex.webaccess.business.queue.retrievepayload;

import eu.europa.ec.etrustex.webaccess.business.api.RetrievePayloadTriggerService;
import eu.europa.ec.etrustex.webaccess.business.queue.AbstractTriggerService;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
public class RetrievePayloadTriggerServiceImpl extends AbstractTriggerService<RetrievePayloadQueueMessage> implements RetrievePayloadTriggerService {
    Logger logger = Logger.getLogger(RetrievePayloadTriggerServiceImpl.class);

    @Autowired
    @Qualifier(value = "etxWebRetrieveMetadataJMSQueue")
    private Queue queue;

    @Override
    public void triggerRetrievePayload(String loggedInUser, Long messageId) throws EtxException {
        RetrievePayloadQueueMessage queueMessage = new RetrievePayloadQueueMessage(loggedInUser, messageId);
        trigger(queueMessage);
        logger.debug("RetrievePayload triggered for: " + queueMessage);
    }


    @Override
    protected Queue getQueue() {
        return queue;
    }
}
