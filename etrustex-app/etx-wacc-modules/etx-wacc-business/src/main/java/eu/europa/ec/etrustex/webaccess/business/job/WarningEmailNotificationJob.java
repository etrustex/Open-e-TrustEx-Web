package eu.europa.ec.etrustex.webaccess.business.job;

import eu.europa.ec.etrustex.webaccess.business.api.EtxJob;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.NotifyService;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Notification;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WarningEmailNotificationJob implements EtxJob {

    private static final Logger logger = Logger.getLogger(WarningEmailNotificationJob.class);

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    private NotifyService notifyService;

    @Override
    public void execute() {
        logger.info("### WarningEmailNotificationJob start ###");

        long startTime = System.currentTimeMillis();

        List<Message> messages = mailboxManager.getWarningMessages();
        if (messages != null && messages.size() > 0) {
            logger.info("number of warning messages to be processed: [" + messages.size() + "]");
            for (Message message : messages) {
                try {
                    notifyService.notify(message, Notification.NotificationType.WARNING_EMAIL);
                } catch (Exception e) {
                    logger.error("error sending warning notification for message: " + message.getId(), e);
                }
            }
        } else {
            logger.info("no warning messages to be processed by job");
        }

        logger.info("### WarningEmailNotificationJob finished after " + (double) (System.currentTimeMillis() - startTime) / 1000 + " sec ###");
    }
}
