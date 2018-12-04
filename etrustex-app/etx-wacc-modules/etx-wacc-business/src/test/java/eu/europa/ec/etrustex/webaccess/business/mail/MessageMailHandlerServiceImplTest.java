package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.VelocityService;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.Mail;
import eu.europa.ec.etrustex.webaccess.persistence.BusinessUserConfigDAO;
import eu.europa.ec.etrustex.webaccess.persistence.MessageDAO;
import eu.europa.ec.etrustex.webaccess.persistence.NotificationDAO;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * @author apladap
 */
public class MessageMailHandlerServiceImplTest extends AbstractTest {

    @InjectMocks
    private MessageMailHandlerServiceImpl mailHandlerServiceBean = new MessageMailHandlerServiceImpl();

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private VelocityService velocityService;

    @Mock
    private MessageDAO messageDAO;

    @Mock
    private BusinessUserConfigDAO businessUserConfigDAO;

    @Mock
    private NotificationDAO notificationDAO;

    @Test
    public void test_PrepareUserMail_should_returnUserMail_fromTemplate() throws Exception {
        String subjectText = "My subject";
        String dummyText = "New message available in e-TrustEx";
        String recipientEmailAddress = "recipientEmailAddress";
        String userName = "username1";
        Long messageId = 2L;
        String referenceId = "myref";

        Configuration configuration = new Configuration();
        configuration.setApplicationUrl("/application's url/");
        when(configurationService.getConfiguration()).thenReturn(configuration);

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);

        Party party = new Party();
        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue("business-name");

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        when(messageDAO.findById(messageId)).thenReturn(message);

        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", null);
        when(velocityService.applyTemplate("templates/message-received-subject.vm", model)).thenReturn(subjectText);

        Map<String, Object> contentModel = new HashMap<>();
        contentModel.put("messageDossierId", referenceId);
        contentModel.put("messageURL", "/application's url/messageViewReceivedWS.do?mid=2");
        contentModel.put("userName", userName);
        contentModel.put("receiverName", null);
        contentModel.put("messageTitle", null);
        when(velocityService.applyTemplate("templates/user-message-received-email.vm", contentModel)).thenReturn(dummyText);

        Long bucId = 1L;
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setId(bucId);
        businessUserConfig.setEmail(recipientEmailAddress);
        businessUserConfig.setName(userName);
        businessUserConfig.setBusiness(business);

        when(businessUserConfigDAO.findById(bucId)).thenReturn(businessUserConfig);

        final Long notifId = 1L;
        Notification notification = new Notification();
        notification.setId(notifId);

        MessageMailHandlerServiceImpl spy = spy(mailHandlerServiceBean);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(notifId);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        // DO THE ACTUAL CALL
        Mail mail = spy.prepareMessageMailForUser(messageId, bucId, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);

        assertThat(mail, notNullValue());

        assertThat(mail.getSubject(), is(subjectText));
        assertThat(mail.getContent(), is(dummyText));
        assertThat(mail.getSentDate(), notNullValue());

        assertThat(mail.getMailAddress(), is(recipientEmailAddress));
        assertThat(mail.getNotificationId(), is(notifId));

        verify(configurationService).getConfiguration();
        verifyNoMoreInteractions(configurationService);

        verify(velocityService).applyTemplate("templates/message-received-subject.vm", model);
        verify(velocityService).applyTemplate("templates/user-message-received-email.vm", contentModel);
        verifyNoMoreInteractions(velocityService);

        verify(messageDAO).findById(messageId);
        verifyNoMoreInteractions(messageDAO);
    }
}
