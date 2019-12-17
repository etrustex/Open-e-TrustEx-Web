package eu.europa.ec.etrustex.webaccess.business.queue.message;

import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.webservice.message.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class RetrieveMessageJmsConsumer implements SessionAwareMessageListener<ObjectMessage> {

    private static final Logger logger = Logger.getLogger(RetrieveMessageJmsConsumer.class);

    @Autowired
    private PartyManager partyManager;

    @Autowired
    private MessageService messageService;

    @Override
    public void onMessage(ObjectMessage jmsMessage, Session session) throws JMSException {

        if (jmsMessage.getObject() instanceof RetrieveMessageQueueMessage) {

            RetrieveMessageQueueMessage queueMessage = (RetrieveMessageQueueMessage) jmsMessage.getObject();

            logger.debug("The onMessage() is called and received the following message uuid:  " + queueMessage.getMessageUuid() +
                    " and messageType: " + queueMessage.getMessageType().getType());

            Party localParty = partyManager.getWebManagedPartyByNodeName(queueMessage.getLocalPartyName());

            messageService.retrieveMessage(localParty.getNodeUserName(), localParty.getNodeUserPass(), queueMessage.getMessageUuid(),
                    queueMessage.getMessageType().getType(), queueMessage.getRemotePartyName(), queueMessage.getLocalPartyName(),
                    queueMessage.getLocalPartyName());
        }
    }
}
