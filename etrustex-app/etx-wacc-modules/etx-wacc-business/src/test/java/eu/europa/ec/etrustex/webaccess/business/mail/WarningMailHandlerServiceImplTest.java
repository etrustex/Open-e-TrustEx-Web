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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class WarningMailHandlerServiceImplTest extends AbstractTest {

    @InjectMocks
    private WarningMailHandlerServiceImpl warningMailHandlerService = new WarningMailHandlerServiceImpl();

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
    public void test_build_content() throws Exception {
        String subjectText = "My subject";
        String dummyText = "New message available in e-TrustEx";
        String recipientEmailAddress = "";
        Long messageId = 1L;
        String userName = "username1";
        String customViewName = "business-name";
        String templateFile = "templates/user-warning-email.vm";
        String referenceId = "myref";

        Message message = new Message();
        message.setId(messageId);
        message.setCreatedOn(new Date());
        message.setReferenceId(referenceId);

        Party party = new Party();
        Business business = new Business();
        business.setBusinessConfigs(new ArrayList<BusinessConfig>());

        BusinessConfig businessConfig = new BusinessConfig();
        businessConfig.setBusinessConfigProperty(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
        businessConfig.setPropertyValue(customViewName);

        business.getBusinessConfigs().add(businessConfig);

        party.setBusiness(business);
        message.setLocalParty(party);

        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWarningBeforeDays(6);
        configuration.setRetentionPolicyWeeks(5);
        configuration.setApplicationUrl("/e-trustex/");

        when(messageDAO.findById(anyLong())).thenReturn(message);
        when(configurationService.getConfiguration()).thenReturn(configuration);

        Map<String, Object> model = new HashMap<>();
        model.put("messageTitle", message.getSubject());
        model.put("payloadDossierTitle", null);
        when(velocityService.applyTemplate("templates/warning-subject.vm", model)).thenReturn(subjectText);

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(message.getCreatedOn());
        expiryDate.add(Calendar.DATE, configuration.getRetentionPolicyWeeks() * 7);

        Map<String, Object> contentModel = new HashMap<>();
        contentModel.put("userName", userName);
        contentModel.put("receiverName", null);
        contentModel.put("retentionPolicyWarningDays", configuration.getRetentionPolicyWarningBeforeDays());
        contentModel.put("expirationDate", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(expiryDate.getTime()));
        contentModel.put("messageTitle", null);
        contentModel.put("messageURL", configuration.getApplicationUrl() + "messageViewReceived.do" + "?mid=" + messageId);
        when(velocityService.applyTemplate(templateFile, contentModel)).thenReturn(dummyText);

        Long bucId = 1L;
        BusinessUserConfig businessUserConfig = new BusinessUserConfig();
        businessUserConfig.setId(bucId);
        businessUserConfig.setId(bucId);
        businessUserConfig.setEmail(recipientEmailAddress);
        businessUserConfig.setName(userName);
        businessUserConfig.setBusiness(business);

        when(businessUserConfigDAO.findById(bucId)).thenReturn(businessUserConfig);

        final Long notifId = 1L;

        WarningMailHandlerServiceImpl spy = spy(warningMailHandlerService);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Notification notification = (Notification) invocationOnMock.getArguments()[0];
                notification.setId(notifId);
                return null;
            }
        }).when(notificationDAO).save(argThat((any(Notification.class))));

        // DO THE ACTUAL CALL
        Mail mail = spy.prepareMessageMailForUser(messageId, bucId, Notification.NotificationType.WARNING_EMAIL);

        assertThat(mail, notNullValue());
        assertThat(mail.getSubject(), is(subjectText));
        assertThat(mail.getContent(), is(dummyText));

        assertThat(mail.getMailAddress(), is(recipientEmailAddress));
        assertThat(mail.getNotificationId(), is(notNullValue()));

        verify(configurationService, times(2)).getConfiguration();
        verifyNoMoreInteractions(configurationService);

        verify(velocityService).applyTemplate("templates/warning-subject.vm", model);
        verify(velocityService).applyTemplate(templateFile, contentModel);
        verifyNoMoreInteractions(velocityService);

        verify(messageDAO).findById(messageId);
        verifyNoMoreInteractions(messageDAO);
    }
}