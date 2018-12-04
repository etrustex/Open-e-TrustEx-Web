package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.Notification;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;

import java.util.List;

public interface MailHandlerService {

    Mail prepareMessageMailForUser(Long messageId, Long bucId, Notification.NotificationType notificationType);

    List<Mail> prepareMessageMailForParty(Long messageId, Long partyId, Notification.NotificationType notificationType);

    Mail prepareStatusMailForUser(Long messageStatusId, Long bucId);

    Mail prepareStatusMailForParty(Long messageStatusId, Long partyId);

}
