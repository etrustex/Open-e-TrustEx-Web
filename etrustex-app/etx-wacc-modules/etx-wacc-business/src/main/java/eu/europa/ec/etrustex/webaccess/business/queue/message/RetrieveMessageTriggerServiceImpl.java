package eu.europa.ec.etrustex.webaccess.business.queue.message;

import eu.europa.ec.etrustex.webaccess.business.api.RetrieveBundleTriggerService;
import eu.europa.ec.etrustex.webaccess.business.message.MessageType;
import eu.europa.ec.etrustex.webaccess.business.queue.AbstractTriggerService;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
public class RetrieveMessageTriggerServiceImpl extends AbstractTriggerService<RetrieveMessageQueueMessage> implements RetrieveBundleTriggerService {

    private static final Logger logger = Logger.getLogger(RetrieveMessageTriggerServiceImpl.class);

    @Autowired
    @Qualifier(value = "etxWebRetrieveBundleJMSQueue")
    private Queue queue;

    @Override
    public void triggerRetrieveMessage(String localPartyName, String remotePartyName, String messageUuid, MessageType messageType) throws EtxException {
        logger.info("Node message sending to queue just started");
        RetrieveMessageQueueMessage queueMessage = new RetrieveMessageQueueMessage(localPartyName, remotePartyName, messageUuid, messageType);
        trigger(queueMessage);
        logger.info("Node message sending to queue completed");
    }

    @Override
    protected Queue getQueue() {
        return queue;
    }
}
