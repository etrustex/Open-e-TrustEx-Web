package eu.europa.ec.etrustex.webaccess.business.mail;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.VelocityService;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.MessageStatus;
import eu.europa.ec.etrustex.webaccess.model.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class StatusMailHandlerServiceImplTest extends AbstractTest {

    @Mock
    private VelocityService velocityService;

    @Mock
    private ConfigurationService configurationService;

    @InjectMocks
    private StatusMailHandlerServiceImpl statusMailHandlerService = new StatusMailHandlerServiceImpl();

    @Test
    public void test_buildSubject() throws Exception {
        String subjectText = "My subject";
        Long messageId = 2L;
        String referenceId = "myref";
        String userName = "username1";
        String receiverName = "party1";
        String senderName = "party2";
        String applicationUrl = "appUrl/";
        String messageUrl = applicationUrl + "messageViewSent.do?mid=" + messageId;
        String messageCreatedBy = "user1";
        long numFiles = 2;

        MessageStatus.Status mstStatus = MessageStatus.Status.AVAILABLE;
        String mstStatusCode = "abc";

        User user = new User();
        user.setName(messageCreatedBy);

        Message message = new Message();
        message.setId(messageId);
        message.setReferenceId(referenceId);
        message.setSubject(subjectText);
        message.setCreatedBy(user);
        message.setSentOn(new Date());

        Long messageStatusId = 3L;
        Date createdOn = new Date();
        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setId(messageStatusId);
        messageStatus.setMessage(message);
        messageStatus.setMstStatus(mstStatus);
        messageStatus.setMstStatusCode(mstStatusCode);
        messageStatus.setCreatedOn(createdOn);

        String sdate = AbstractMailHandlerService.dateFormat.format(messageStatus.getCreatedOn());
        String stime = AbstractMailHandlerService.timeFormat.format(messageStatus.getCreatedOn());

        String sentOnDate = AbstractMailHandlerService.dateFormat.format(message.getSentOn());
        String sentOnTime = AbstractMailHandlerService.timeFormat.format(message.getSentOn());

        Map<String, Object> model = new HashMap<>();
        model.put("messageDossierId", referenceId);
        model.put("messageURL", messageUrl);
        model.put("userName", userName);
        model.put("receiverName", receiverName);
        model.put("senderName", senderName);
        model.put("messageTitle", subjectText);
        model.put("messageMstStatus", mstStatus.name());
        model.put("messageMstStatusCode", mstStatusCode);
        model.put("createdByUser", message.getCreatedBy().getName());
        model.put("statusDate", sdate);
        model.put("statusTime", stime);
        model.put("numFiles", numFiles);
        model.put("sentOnDate", sentOnDate);
        model.put("sentOnTime", sentOnTime);
        when(velocityService.applyTemplate("templates/subject", model)).thenReturn(subjectText);

        Configuration configuration = new Configuration();
        configuration.setApplicationUrl(applicationUrl);
        when(configurationService.getConfiguration()).thenReturn(configuration);

        MailContentData mailContentData = new MailContentData();
        mailContentData.setTemplateFile("templates/subject");
        mailContentData.setMessageReferenceId(referenceId);
        mailContentData.setMessageId(messageId);
        mailContentData.setUserName(userName);
        mailContentData.setReceiverName(receiverName);
        mailContentData.setSenderName(senderName);
        mailContentData.setMessageTitle(subjectText);
        mailContentData.setStatus(messageStatus.getMstStatus().name());
        mailContentData.setStatusCode(messageStatus.getMstStatusCode());
        mailContentData.setCreatedByUser(messageCreatedBy);
        mailContentData.setStatusDate(sdate);
        mailContentData.setStatusTime(stime);
        mailContentData.setNumFiles(numFiles);
        mailContentData.setMessageSentOnDate(sentOnDate);
        mailContentData.setMessageSentOnTime(sentOnTime);

        StatusMailHandlerServiceImpl spy = spy(statusMailHandlerService);

        // DO THE ACTUAL CALL
        spy.buildContent(mailContentData);

        verify(spy).buildContent(mailContentData);
        verify(spy).buildMessageUrl(messageId);
        verifyNoMoreInteractions(spy);

        verify(velocityService).applyTemplate("templates/subject", model);
        verifyNoMoreInteractions(velocityService);
    }
}