package eu.europa.ec.etrustex.webaccess.business.queue.retrievepayload;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class RetrievePayloadJmsConsumer implements SessionAwareMessageListener<ObjectMessage> {
    Logger logger = Logger.getLogger(RetrievePayloadJmsConsumer.class);

    @Autowired
    private AttachmentHandler attachmentHandler;

    @Override
    public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {

        RetrievePayloadQueueMessage queueMessage = null;
        try {
            if (objectMessage.getObject() instanceof RetrievePayloadQueueMessage) {
                queueMessage = (RetrievePayloadQueueMessage) objectMessage.getObject();
                logger.debug("The onMessage() is called and received the following message:  " + queueMessage);
            }
        } catch (JMSException e) {
            logger.error("Exception while reading the object from Queue!", e);
            session.rollback();
            throw e;
        }

        if (queueMessage != null) {
            attachmentHandler.retrievePayloadForMessage(queueMessage.getLoggedInUser(), queueMessage.getMessageId());
        }
    }
}
