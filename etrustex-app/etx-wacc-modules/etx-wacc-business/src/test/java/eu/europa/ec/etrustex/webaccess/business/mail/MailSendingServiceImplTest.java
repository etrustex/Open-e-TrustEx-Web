package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailSessionAdapter;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class MailSendingServiceImplTest extends AbstractTest {

    @InjectMocks
    private MailSendingServiceImpl mailSendingService = new MailSendingServiceImpl();

    @Mock
    private MailSessionAdapter mailSessionAdapter;

    @Test
    public void testSend() throws EtxException, MessagingException {
        Mail mail = new Mail();
        mail.setContent("the message");
        mail.setMailAddress("test.this@mail.com");
        mail.setSubject("Subject");

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSessionAdapter.createMessage()).thenReturn(mimeMessage);

        Transport transport = mock(Transport.class);
        when(mailSessionAdapter.createTransport()).thenReturn(transport);

        Address[] addresses = InternetAddress.parse(mail.getMailAddress() != null ? mail.getMailAddress().trim() : null, false);
        when(mimeMessage.getAllRecipients()).thenReturn(addresses);

        mailSendingService.send(mail);

        verify(mimeMessage).setSubject(mail.getSubject(), "UTF-8");
        verify(mimeMessage).setSentDate(mail.getSentDate());
        verify(mimeMessage).setRecipients(Message.RecipientType.TO, addresses);

        verify(mimeMessage).setContent(mail.getContent(), "text/html; charset=UTF-8");
        verify(mimeMessage).setFrom(InternetAddress.parse("eTrustEx-noreply@ec.europa.eu")[0]);
        verify(mimeMessage, times(2)).getAllRecipients();
        verifyNoMoreInteractions(mimeMessage);

        verify(transport).connect();
        verify(transport).sendMessage(argThat(is(sameInstance(mimeMessage))), argThat(is(sameInstance(addresses))));
        verify(transport).close();
        verifyNoMoreInteractions(transport);

        verify(mailSessionAdapter).createMessage();
        verify(mailSessionAdapter).createTransport();
        verifyNoMoreInteractions(mailSessionAdapter);
    }

    @Test
    public void test_send_shouldAddEnvironmentPrefixToSubject_when_environmentIsProvided() throws EtxException, MessagingException {
        String environment = "ENV-1";
        Mail mail = new Mail();
        mail.setContent("the message");
        mail.setMailAddress("test.this@mail.com");
        mail.setSubject("Subject");

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSessionAdapter.createMessage()).thenReturn(mimeMessage);

        Transport transport = mock(Transport.class);
        when(mailSessionAdapter.createTransport()).thenReturn(transport);

        Address[] addresses = InternetAddress.parse(mail.getMailAddress() != null ? mail.getMailAddress().trim() : null, false);
        when(mimeMessage.getAllRecipients()).thenReturn(addresses);

        mailSendingService.emailSubjectPrefix = environment;

        mailSendingService.send(mail);

        verify(mimeMessage).setSubject(environment + " - " + mail.getSubject(), "UTF-8");
        verify(mimeMessage).setSentDate(mail.getSentDate());
        verify(mimeMessage).setRecipients(Message.RecipientType.TO, addresses);

        verify(mimeMessage).setContent(mail.getContent(), "text/html; charset=UTF-8");
        verify(mimeMessage).setFrom(InternetAddress.parse("eTrustEx-noreply@ec.europa.eu")[0]);
        verify(mimeMessage, times(2)).getAllRecipients();
        verifyNoMoreInteractions(mimeMessage);

        verify(transport).connect();
        verify(transport).sendMessage(argThat(is(sameInstance(mimeMessage))), argThat(is(sameInstance(addresses))));
        verify(transport).close();
        verifyNoMoreInteractions(transport);

        verify(mailSessionAdapter).createMessage();
        verify(mailSessionAdapter).createTransport();
        verifyNoMoreInteractions(mailSessionAdapter);
    }
}
