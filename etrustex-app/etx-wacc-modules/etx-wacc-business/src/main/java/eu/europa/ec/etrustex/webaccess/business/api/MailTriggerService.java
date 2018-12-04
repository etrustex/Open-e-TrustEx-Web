package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;

public interface MailTriggerService {
    void triggerEmailSending(Mail mailNotification) throws EtxException;
}
