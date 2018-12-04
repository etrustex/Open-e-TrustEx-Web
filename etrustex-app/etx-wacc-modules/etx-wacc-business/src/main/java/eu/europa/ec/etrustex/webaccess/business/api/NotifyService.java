package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import eu.europa.ec.etrustex.webaccess.model.Notification;

public interface NotifyService {

    /**
     * Send notification for a message of specified type.
     */
    void notify(Message message, Notification.NotificationType notificationType);

    /**
     * Send notification for a message status.
     */
    void notify(MessageStatus messageStatus);
}
