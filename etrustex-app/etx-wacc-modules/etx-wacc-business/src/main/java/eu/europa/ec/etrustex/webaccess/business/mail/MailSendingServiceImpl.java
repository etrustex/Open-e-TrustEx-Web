package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.webaccess.business.api.MailSendingService;
import eu.europa.ec.etrustex.webaccess.business.api.MailSessionAdapter;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Default implementation of the MailSendingService.
 *
 * @author apladap
 */
@Service
public class MailSendingServiceImpl implements MailSendingService {

    final private static Logger logger = Logger.getLogger(MailSendingService.class);

    @Autowired
    private MailSessionAdapter mailSessionAdapter;

    @Value("${etx.web.email.subject.prefix}")
    protected String emailSubjectPrefix;

    public boolean send(Mail mail) throws EtxException {
        logger.info("Sending mail...");
        logger.info(" (*) recipient: " + mail.getMailAddress());

        try {
            MimeMessage message = mailSessionAdapter.createMessage();
            String subject = StringUtils.isEmpty(emailSubjectPrefix) ? mail.getSubject() : emailSubjectPrefix + " - " + mail.getSubject();
            message.setSubject(subject, "UTF-8");
            message.setSentDate(mail.getSentDate());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getMailAddress() != null ? mail.getMailAddress().trim() : null, false));

            message.setContent(mail.getContent(), "text/html; charset=UTF-8");
            message.setFrom(InternetAddress.parse("eTrustEx-noreply@ec.europa.eu")[0]);

            Transport transport = null;
            try {
                transport = mailSessionAdapter.createTransport();
                transport.connect();
                Address[] addr = message.getAllRecipients();
                logger.info("The email address used is {" + addr[0].toString().trim() + "}");
                transport.sendMessage(message, message.getAllRecipients());
            } finally {
                if (transport != null) {
                    transport.close();
                }
            }
        } catch (Exception e) {
            throw new EtxException(e.getMessage(), e);
        }

        return true;
    }
}
