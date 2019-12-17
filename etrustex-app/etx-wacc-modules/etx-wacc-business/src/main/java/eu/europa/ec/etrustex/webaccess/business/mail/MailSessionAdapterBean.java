package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.webaccess.business.api.MailSessionAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;


@Component
public class MailSessionAdapterBean implements MailSessionAdapter {


    @Resource(mappedName = "mail/EtxWebSession")
    private Session mailSession;

    @Override
    public MimeMessage createMessage() {
        return new MimeMessage(mailSession);
    }

    @Override
    public Transport createTransport() throws NoSuchProviderException {
        return mailSession.getTransport("smtp");
    }

}
