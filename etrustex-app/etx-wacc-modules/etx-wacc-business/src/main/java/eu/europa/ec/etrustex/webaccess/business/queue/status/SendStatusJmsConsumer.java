package eu.europa.ec.etrustex.webaccess.business.queue.status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class SendStatusJmsConsumer implements SessionAwareMessageListener<ObjectMessage> {
    private static Logger logger = Logger.getLogger(SendStatusJmsConsumer.class);

    @Autowired
    private SendStatusHandler sendStatusHandler;

    @Override
    public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {
        SendStatusQueueMessage queueMessage = null;
        try {
            if (objectMessage.getObject() instanceof SendStatusQueueMessage) {
                queueMessage = (SendStatusQueueMessage) objectMessage.getObject();
                logger.debug("The onMessage() is called and received the following message:  " + queueMessage);
            }
        } catch (JMSException e) {
            logger.error("Exception while reading the object from Queue!", e);
            session.rollback();
            throw e;
        }

        if (queueMessage != null) {
            try {
                sendStatusHandler.handleSendStatus(queueMessage.getMessageId());
            } catch (Exception e) {
                logger.error("Error occurred handling message status id: " + queueMessage.getMessageId(), e);
                throw new RuntimeException(e);
            }
        }
    }
}