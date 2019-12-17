package eu.europa.ec.etrustex.webaccess.business.queue.mail;

import eu.europa.ec.etrustex.webaccess.business.api.MailTriggerService;
import eu.europa.ec.etrustex.webaccess.business.queue.AbstractTriggerService;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

/**
 * @author: micleva
 * @date: 1/10/13 2:51 PM
 * @project: ETX
 */
@Service
class MailTriggerServiceImpl extends AbstractTriggerService<Mail> implements MailTriggerService {

    @Autowired
    @Qualifier(value = "etxWebMailQueue")
    private Queue queue;

    Logger logger = Logger.getLogger(MailTriggerServiceImpl.class);

    public void triggerEmailSending(Mail mailNotification) throws EtxException {
        logger.info("Procedure on feeding the queue just started");
        trigger(mailNotification);
        logger.debug("Email notification triggered for notificationId: " + mailNotification.getNotificationId());
    }

    @Override
    protected Queue getQueue() {
        return queue;
    }
}
