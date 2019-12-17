package eu.europa.ec.etrustex.webaccess.business.queue.error;

import eu.europa.ec.etrustex.webaccess.business.queue.retrievepayload.RetrievePayloadQueueMessage;
import eu.europa.ec.etrustex.webaccess.business.queue.status.SendStatusHandler;
import eu.europa.ec.etrustex.webaccess.business.queue.status.SendStatusQueueMessage;
import eu.europa.ec.etrustex.webaccess.model.Notification;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.persistence.NotificationDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.Date;

public class ErrorJmsConsumer implements SessionAwareMessageListener<ObjectMessage> {

    private final Logger logger = Logger.getLogger(ErrorJmsConsumer.class);

    @Autowired
    private NotificationDAO notificationDAO;

    @Autowired
    private SendStatusHandler sendStatusHandler;

    @Override
    public void onMessage(ObjectMessage objectMessage, Session session) throws JMSException {

        StringBuilder errorHandlingMessage = new StringBuilder();
        errorHandlingMessage.append("Message timestamp: ").append(new Date(objectMessage.getJMSTimestamp()));

        try {
            Object object = objectMessage.getObject();
            errorHandlingMessage.append("; JMSMessage object type:").append(object.getClass().getName());

            if (object instanceof RetrievePayloadQueueMessage) {
                errorHandlingMessage.append("; Retrieve payload failed for messageId: ").append(((RetrievePayloadQueueMessage) object).getMessageId());
            } else if (object instanceof Mail) {
                errorHandlingMessage.append("; Sending Email failed for notificationId: ").append(((Mail) object).getNotificationId());
                updateFailedNotification((Mail) object);
            } else if (object instanceof Long) {
                errorHandlingMessage.append("; Receiving of Bundle failed for messageId: ").append(object);
            } else if (object instanceof SendStatusQueueMessage) {
                errorHandlingMessage.append("; Sending SendStatusQueueMessage failed for messageId: ").append(((SendStatusQueueMessage) object).getMessageId());
                sendStatusHandler.handleError(((SendStatusQueueMessage) object).getMessageId());
            } else {
                errorHandlingMessage.append("; Unexpected JMS Object type! Please report this to the dev team!");
            }
        } catch (Exception e) {
            errorHandlingMessage.append("; Exception while handling the JMSMessage: ");
            errorHandlingMessage.append("; message: ").append(e.getMessage());
            if (e.getCause() != null) {
                errorHandlingMessage.append("; exception cause: ").append(e.getCause().getMessage());
            }
        }

        logger.warn("Handling error message for JMS Message: " + errorHandlingMessage.toString());
    }

    private void updateFailedNotification(Mail mailNotification) {
        Notification notification = notificationDAO.findById(mailNotification.getNotificationId());
        if (notification != null) {
            notification.setNotificationState(Notification.NotificationState.FAILED);
            notification.setUpdatedOn(new Date());
            notificationDAO.update(notification);
        } else {
            logger.warn("null notification not expected here");
        }
    }
}
