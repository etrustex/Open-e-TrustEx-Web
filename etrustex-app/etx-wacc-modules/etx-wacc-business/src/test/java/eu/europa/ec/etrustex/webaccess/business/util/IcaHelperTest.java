package eu.europa.ec.etrustex.webaccess.business.util;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class IcaHelperTest extends AbstractTest {

    @Mock
    private IcaManager icaManager;

    private Party recipient;

    @Override
    protected void onSetUp() {
        recipient = new Party();
        recipient.setId(1L);
        recipient.setDisplayName("foo");
        recipient.setNodeName("foo");
    }

    @InjectMocks
    IcaHelper icaHelper;

    @Test
    public void test_hasIca_SENT_Message() {
        Date createdOn = new Date();
        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("remoteParty1");

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("mySubject");
        message.setMsgState(Message.MessageState.SENT);
        message.setRemoteParty(remoteParty1);
        message.setLocalParty(recipient);
        List<Attachment> attachmentsList = new ArrayList<>();

        // DO THE ACTUAL CALL
        Boolean hasIca = icaHelper.hasIca(message);

        assertThat(hasIca, is(true));

    }

    @Test
    public void test_hasIca_INCOMING_Message() {
        Date createdOn = new Date();
        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("remoteParty1");

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("mySubject");
        message.setMsgState(Message.MessageState.INCOMING);
        message.setRemoteParty(remoteParty1);
        message.setLocalParty(recipient);
        List<Attachment> attachmentsList = new ArrayList<>();

        when(icaManager.icaExists(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName())).thenReturn(Boolean.TRUE);
        // DO THE ACTUAL CALL
        Boolean hasIca = icaHelper.hasIca(message);

        assertThat(hasIca, is(true));

    }

    @Test
    public void test_has_no_Ica_INCOMING_Message() {
        Date createdOn = new Date();
        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("remoteParty1");

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("mySubject");
        message.setMsgState(Message.MessageState.INCOMING);
        message.setRemoteParty(remoteParty1);
        message.setLocalParty(recipient);
        List<Attachment> attachmentsList = new ArrayList<>();

        when(icaManager.icaExists(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName())).thenReturn(Boolean.FALSE);
        // DO THE ACTUAL CALL
        Boolean hasIca = icaHelper.hasIca(message);

        assertThat(hasIca, is(false));

    }

    @Test
    public void test_hasIca_DRAFT_Message() {
        Date createdOn = new Date();
        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("remoteParty1");

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("mySubject");
        message.setMsgState(Message.MessageState.DRAFT);
        message.setRemoteParty(remoteParty1);
        message.setLocalParty(recipient);
        List<Attachment> attachmentsList = new ArrayList<>();

        when(icaManager.icaExists(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName())).thenReturn(Boolean.TRUE);
        // DO THE ACTUAL CALL
        Boolean hasIca = icaHelper.hasIca(message);

        assertThat(hasIca, is(true));

    }

    @Test
    public void test_has_no_Ica_DRAFT_Message() {
        Date createdOn = new Date();
        Party remoteParty1 = new Party();
        remoteParty1.setNodeName("remoteParty1");

        Message message = new Message();
        message.setCreatedOn(createdOn);
        message.setId(45L);
        message.setSubject("mySubject");
        message.setMsgState(Message.MessageState.DRAFT);
        message.setRemoteParty(remoteParty1);
        message.setLocalParty(recipient);
        List<Attachment> attachmentsList = new ArrayList<>();

        when(icaManager.icaExists(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName())).thenReturn(Boolean.FALSE);
        // DO THE ACTUAL CALL
        Boolean hasIca = icaHelper.hasIca(message);

        assertThat(hasIca, is(false));

    }


}