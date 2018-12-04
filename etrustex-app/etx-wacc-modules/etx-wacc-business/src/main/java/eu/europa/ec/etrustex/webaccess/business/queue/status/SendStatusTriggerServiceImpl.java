package eu.europa.ec.etrustex.webaccess.business.queue.status;

import eu.europa.ec.etrustex.webaccess.business.api.SendStatusTriggerService;
import eu.europa.ec.etrustex.webaccess.business.queue.AbstractTriggerService;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
public class SendStatusTriggerServiceImpl extends AbstractTriggerService<SendStatusQueueMessage> implements SendStatusTriggerService {
    Logger logger = Logger.getLogger(SendStatusTriggerServiceImpl.class);

    @Autowired
    @Qualifier(value = "etxWebSendStatusJMSQueue")
    private Queue queue;

    @Override
    public void triggerSendStatus(Long messageStatusId) throws EtxException {
        SendStatusQueueMessage queueMessage = new SendStatusQueueMessage(messageStatusId);
        trigger(queueMessage);
        logger.debug("SendStatus triggered for: " + queueMessage);
    }

    @Override
    protected Queue getQueue() {
        return queue;
    }
}
