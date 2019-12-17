/**
 *
 */
package eu.europa.ec.etrustex.webaccess.business.api;

import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;

/**
 * @author apladap
 */
public interface MailSendingService {

    boolean send(Mail mail) throws EtxException;

}
