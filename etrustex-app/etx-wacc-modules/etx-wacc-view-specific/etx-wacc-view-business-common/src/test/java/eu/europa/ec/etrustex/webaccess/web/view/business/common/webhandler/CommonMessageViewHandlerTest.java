package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.business.util.IcaHelper;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.RetentionMetadata;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.MESSAGE_ATTR;
import static org.mockito.Mockito.*;


public class CommonMessageViewHandlerTest extends AbstractTest {

    @Mock
    protected UserSessionContext userSessionContext;
    @Mock
    protected MailboxManager mailboxManager;
    @Mock
    protected WebHandlerHelper webHandlerHelper;
    @InjectMocks
    private CommonMessageViewHandler handler = new CommonMessageViewHandler();
    @Mock
    private ConfigurationService configurationService;
    @Mock
    private IcaManager icaManager;
    @Mock
    private IcaHelper icaHelper;

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel() throws Exception {
        Long messageId = 5L;
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setParty(party);
        queryParams.setMessageId(messageId);

        User user = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(user);

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.DRAFT);

        when(mailboxManager.getMessageDetails(queryParams.getMessageId(), user)).thenReturn(new Message());

        Configuration configuration = new Configuration();
        when(configurationService.getConfiguration()).thenReturn(configuration);

        CommonMessageViewHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        ArrayList<Attachment> attachments = new ArrayList<>();
        doReturn(attachments).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel_sentMessage() throws Exception {
        Long messageId = 5L;
        Party party = new Party();
        party.setNodeName("party");
        Party remoteParty = new Party();
        remoteParty.setNodeName("remoteParty");

        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setParty(party);
        queryParams.setMessageId(messageId);

        User user = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(user);

        Message message = new Message();
        message.setLocalParty(party);
        message.setRemoteParty(remoteParty);
        message.setMsgState(Message.MessageState.SENT);

        when(mailboxManager.getMessageDetails(queryParams.getMessageId(), user)).thenReturn(new Message());

        Configuration configuration = new Configuration();
        when(configurationService.getConfiguration()).thenReturn(configuration);

        CommonMessageViewHandler spy = spy(handler);
        doReturn(message).when(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        ArrayList<Attachment> attachments = new ArrayList<>();
        doReturn(attachments).when(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        when(icaHelper.hasIca(message)).thenReturn(Boolean.TRUE);

        // DO THE ACTUAL CALL
        Map<String, Object> result = spy.buildBusinessModel(queryParams);

        verify(spy).addMessageToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));
        verify(spy).addAttachmentsToModel(argThat(any(Map.class)), argThat(is(messageId)), argThat(is(user)));

        assertThat((int) result.get("hasMessageRemoteIca"), is(1));
        verify(icaHelper).hasIca(message);
        verifyNoMoreInteractions(icaManager);
    }

    @Test
    public void test_addMessageToModel_should_addMessageAndSigningObjectsToModel_when_messageContainsSigningValuesAndRetentionInvalid() throws Exception {
        Long messageId = 5L;
        HashMap<String, Object> model = new HashMap<>();

        Party party = new Party();
        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setParty(party);
        queryParams.setMessageId(messageId);

        User user = mock(User.class);

        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(0);
        configuration.setRetentionPolicyWarningBeforeDays(2);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        Date createdOn = new Date();

        RetentionMetadata retentionMetadata = new RetentionMetadata();
        retentionMetadata.setRetentionExpired(false);
        retentionMetadata.setRetentionWarning(false);
        retentionMetadata.setExpiredOn(null);

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setSignatures(Arrays.asList(new MessageSignature()));

        when(mailboxManager.getMessageDetailsAndFetchSignatureEagerly(messageId, user)).thenReturn(message);

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(configurationService.computeRetentionMetadata(configuration, createdOn)).thenReturn(retentionMetadata);

        // DO THE ACTUAL CALL
        handler.addMessageToModel(model, messageId, user);

        Message actual = (Message) model.get(MESSAGE_ATTR);
        RetentionMetadata actualRetentionMetadata = (RetentionMetadata) model.get("retentionMetadata");
        assertThat(configurationService.isRetentionPolicyValid(configuration), is(false));
        assertThat(actual, is(sameInstance(message)));
        assertThat(model.get("encodedMessageSignatureData"), is(nullValue()));
        assertThat(model.size(), is(equalTo(2)));
        assertThat(actualRetentionMetadata.getRetentionExpired(), is(false));
        assertThat(actualRetentionMetadata.getRetentionWarning(), is(false));
        assertThat(actualRetentionMetadata.getExpiredOn(), is(nullValue()));
    }

    @Test
    public void test_addMessageToModel_should_addMessageAndSigningObjectsToModel_when_messageContainsSigningValues() throws Exception {
        Long messageId = 5L;
        HashMap<String, Object> model = new HashMap<>();

        Party party = new Party();
        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setParty(party);
        queryParams.setMessageId(messageId);

        User user = mock(User.class);

        Configuration configuration = new Configuration();
        configuration.setRetentionPolicyWeeks(6);
        configuration.setRetentionPolicyWarningBeforeDays(3);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        Date createdOn = new Date();
        int retentionPolicyWeeks = configuration.getRetentionPolicyWeeks();
        int retentionPolicyWarningBeforeDays = configuration.getRetentionPolicyWarningBeforeDays();

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(createdOn);
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        Calendar warningLimitDate = Calendar.getInstance();
        warningLimitDate.setTime(expiryDate.getTime());
        warningLimitDate.add(Calendar.DATE, -(retentionPolicyWarningBeforeDays));

        RetentionMetadata retentionMetadata = new RetentionMetadata();
        retentionMetadata.setRetentionExpired(now.compareTo(expiryDate) > 0);
        retentionMetadata.setRetentionWarning(!retentionMetadata.getRetentionExpired() && now.compareTo(warningLimitDate) > 0);
        retentionMetadata.setExpiredOn(expiryDate);

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setSignatures(Arrays.asList(new MessageSignature()));
        when(mailboxManager.getMessageDetailsAndFetchSignatureEagerly(messageId, user)).thenReturn(message);

        when(configurationService.getConfiguration()).thenReturn(configuration);
        when(configurationService.computeRetentionMetadata(configuration, createdOn)).thenReturn(retentionMetadata);

        // DO THE ACTUAL CALL
        handler.addMessageToModel(model, messageId, user);

        Message actual = (Message) model.get(MESSAGE_ATTR);
        RetentionMetadata actualRetentionMetadata = (RetentionMetadata) model.get("retentionMetadata");
        assertThat(actual, is(sameInstance(message)));
        assertThat(model.get("encodedMessageSignatureData"), is(nullValue()));
        assertThat(model.size(), is(equalTo(2)));
        assertThat(actualRetentionMetadata.getRetentionExpired(), is(false));
        assertThat(actualRetentionMetadata.getRetentionWarning(), is(false));
        assertThat(actualRetentionMetadata.getExpiredOn(), is(expiryDate));
    }

    @Test
    public void test_addAttachmentsToModel_should_addAttachmentObjectsToModel() throws Exception {
        Long messageId = 5L;
        HashMap<String, Object> model = new HashMap<>();

        User user = mock(User.class);

        List<Attachment> attachments = new ArrayList<>();
        attachments.add(new Attachment());
        attachments.add(new Attachment());

        CommonMessageViewHandler spy = spy(handler);
        doReturn(attachments).when(spy).getAttachments(user, messageId);

        // DO THE ACTUAL CALL
        spy.addAttachmentsToModel(model, messageId, user);

        assertThat((List<Attachment>) model.get("attachments"), is(sameInstance(attachments)));
        assertThat((Integer) model.get("attachmentCount"), is(equalTo(2)));
        assertThat(model.size(), is(equalTo(2)));
    }
}
