package eu.europa.ec.etrustex.webaccess.business.queue;

import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.io.Serializable;

@Service
public abstract class AbstractTriggerService<T extends Serializable> {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void trigger(T queueMessage) throws EtxException {
        jmsTemplate.convertAndSend(getQueue(), queueMessage);
    }

    abstract protected Queue getQueue();
}
