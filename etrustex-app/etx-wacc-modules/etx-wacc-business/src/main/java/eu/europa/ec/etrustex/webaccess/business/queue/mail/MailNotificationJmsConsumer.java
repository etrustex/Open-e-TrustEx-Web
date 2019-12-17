package eu.europa.ec.etrustex.webaccess.business.queue.mail;

import eu.europa.ec.etrustex.webaccess.business.api.MailSendingService;
import eu.europa.ec.etrustex.webaccess.model.Notification;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.persistence.NotificationDAO;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.Date;

public class MailNotificationJmsConsumer implements SessionAwareMessageListener<ObjectMessage> {

    Logger logger = Logger.getLogger(MailNotificationJmsConsumer.class);

    @Autowired
    private MailSendingService mailSendingService;

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public void onMessage(ObjectMessage message, Session session) throws JMSException {
        Mail mailNotification = null;
        try {
            if (message.getObject() instanceof Mail) {
                mailNotification = (Mail) message.getObject();
                logger.debug("The onMessage() is called and received the following message:  " + mailNotification);
            }
        } catch (JMSException e) {
            logger.error("Exception while reading the object from Queue!", e);
            session.rollback();
            throw e;
        }

        if (mailNotification != null) {
            try {
                // Give to the MailService ONLY the MessageId, the recipient and Subject in order to send an email notification
                mailSendingService.send(mailNotification);

                updateSendNotification(mailNotification);
                logger.debug("Email notification send to recipient with notificationId: " + mailNotification.getNotificationId());
            } catch (EtxException e) {
                logger.error("Attempt to send mail for notificationId: " + mailNotification.getNotificationId() + " failed!", e);
                session.rollback();
                throw new JMSException(e.getMessage());
            }
        }
    }

    private void updateSendNotification(Mail mailNotification) {
        Notification notification = notificationDAO.findById(mailNotification.getNotificationId());
        if (notification != null) {
            notification.setNotificationState(Notification.NotificationState.SENT);
            notification.setUpdatedOn(new Date());
            notificationDAO.update(notification);
        } else {
            logger.warn("null notification not expected here");
        }
    }

}