package eu.europa.ec.etrustex.webaccess.business.notification;

import eu.europa.ec.etrustex.webaccess.business.api.*;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class NotifyServiceImpl implements NotifyService {
    private static final Logger logger = Logger.getLogger(NotifyServiceImpl.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private PartyManager partyManager;

    @Autowired
    @Qualifier("messageReceivedMailHandler")
    private MailHandlerService messageReceivedHandler;

    @Autowired
    @Qualifier("warningMailHandler")
    private MailHandlerService messageWarningHandler;

    @Autowired
    @Qualifier("statusReceivedMailHandler")
    private MailHandlerService statusReceivedMailHandler;

    @Autowired
    private MailTriggerService mailTriggerService;

    protected void notifyParty(Message message, Notification.NotificationType notificationType) throws JMSException {
        Party party = message.getLocalParty();
        if (partyManager.isNotificationAllowed(party, notificationType)) {
            prepareAndSendPartyMail(message, notificationType, party);
        }
    }

    protected void notifyUsers(Message message, Notification.NotificationType notificationType) throws JMSException {
        Set<BusinessUserConfig> businessUserConfigs = userManager.getBusinessUserConfigs(message.getLocalParty(),
                Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        for (BusinessUserConfig businessUserConfig : businessUserConfigs) {
            if (userManager.isNotificationAllowed(businessUserConfig, notificationType)) {
                prepareAndSendUserMail(message, notificationType, businessUserConfig);
            }
        }
    }

    protected void notifyParty(MessageStatus messageStatus) throws JMSException {
        Party party = messageStatus.getMessage().getLocalParty();
        if (partyManager.isNotificationAllowed(party, Notification.NotificationType.STATUS_RECEIVED_EMAIL)) {
            prepareAndSendPartyMail(messageStatus, party);
        }
    }

    protected void notifyUsers(MessageStatus messageStatus) throws JMSException {
        Set<BusinessUserConfig> businessUserConfigs = userManager.getBusinessUserConfigs(messageStatus.getMessage().getLocalParty(),
                Privilege.Type.CAN_ACCESS_OWN_PARTY_MESSAGES);
        for (BusinessUserConfig businessUserConfig : businessUserConfigs) {
            if (userManager.isNotificationAllowed(businessUserConfig, Notification.NotificationType.STATUS_RECEIVED_EMAIL)) {
                prepareAndSendUserMail(messageStatus, businessUserConfig);
            }
        }
    }

    private void prepareAndSendPartyMail(Message message, Notification.NotificationType notificationType, Party party) {
        logger.info("Prepare mail:  notificationType [" + notificationType + "], partyId [" + party.getId() + "], " +
                "messageId [" + message.getId() + "], recipientEmailAddress [" + party.getEmail() + "]");
        MailHandlerService mailHandlerService = notificationType == Notification.NotificationType.MESSAGE_RECEIVED_EMAIL ? messageReceivedHandler : messageWarningHandler;

        List<Mail> mails = mailHandlerService.prepareMessageMailForParty(message.getId(), party.getId(), notificationType);
        for (Mail mail : mails) {
            mailTriggerService.triggerEmailSending(mail);
            logger.debug("mail sending triggered with notification id: [" + mail.getNotificationId() + ']');
        }
    }

    private void prepareAndSendUserMail(Message message, Notification.NotificationType notificationType, BusinessUserConfig businessUserConfig) {
        logger.info("Prepare mail: notificationType [" + notificationType + "],  businessUserConfigId [" + businessUserConfig.getId() + "], " +
                "messageId [" + message.getId() + "], recipientEmailAddress [" + businessUserConfig.getEmail() + "]");
        MailHandlerService mailHandlerService = notificationType == Notification.NotificationType.MESSAGE_RECEIVED_EMAIL ? messageReceivedHandler : messageWarningHandler;
        Mail mail = mailHandlerService.prepareMessageMailForUser(message.getId(), businessUserConfig.getId(), notificationType);
        mailTriggerService.triggerEmailSending(mail);
        logger.debug("mail sending triggered with notification id: [" + mail.getNotificationId() + ']');
    }

    private void prepareAndSendPartyMail(MessageStatus messageStatus, Party party) {
        Message message = messageStatus.getMessage();
        logger.info("Prepare mail:  notificationType [" + Notification.NotificationType.STATUS_RECEIVED_EMAIL + "], partyId [" + party.getId() + "], " +
                "messageId [" + message.getId() + "], recipientEmailAddress [" + party.getEmail() + "]");
        Mail mail = statusReceivedMailHandler.prepareStatusMailForParty(messageStatus.getId(), party.getId());
        mailTriggerService.triggerEmailSending(mail);
        logger.debug("mail sending triggered with notification id: [" + mail.getNotificationId() + ']');
    }

    private void prepareAndSendUserMail(MessageStatus messageStatus, BusinessUserConfig businessUserConfig) {
        Message message = messageStatus.getMessage();
        logger.info("Prepare mail: notificationType [" + Notification.NotificationType.STATUS_RECEIVED_EMAIL + "],  businessUserConfigId [" + businessUserConfig.getId() + "], " +
                "messageId [" + message.getId() + "], recipientEmailAddress [" + businessUserConfig.getEmail() + "]");
        Mail mail = statusReceivedMailHandler.prepareStatusMailForUser(messageStatus.getId(), businessUserConfig.getId());
        mailTriggerService.triggerEmailSending(mail);
        logger.debug("mail sending triggered with notification id: [" + mail.getNotificationId() + ']');
    }

    public void notify(Message message, Notification.NotificationType notificationType) {
        logger.info("Check if we need to notify user or party for message: [" + message.getId() + "] and notification type: [" + notificationType + "]");
        try {
            notifyParty(message, notificationType);
            notifyUsers(message, notificationType);
        } catch (JMSException e) {
            throw new EtxException("error sending message/warning notification email", e);
        }
    }

    public void notify(MessageStatus messageStatus) {
        Message message = messageStatus.getMessage();
        // only statuses for a message (and not for a previous status) have related a message
        if (message != null && message.getActiveState()) {
            logger.info("Check if we need to notify user or party for messageStatus: [" + messageStatus.getId() + "]");
            try {
                notifyParty(messageStatus);
                notifyUsers(messageStatus);
            } catch (JMSException e) {
                throw new EtxException("error sending status notification email", e);
            }
        }
    }
}
