package eu.europa.ec.etrustex.webaccess.business.api;

import javax.mail.NoSuchProviderException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;


public interface MailSessionAdapter {

	MimeMessage createMessage();

	Transport createTransport() throws NoSuchProviderException;

}