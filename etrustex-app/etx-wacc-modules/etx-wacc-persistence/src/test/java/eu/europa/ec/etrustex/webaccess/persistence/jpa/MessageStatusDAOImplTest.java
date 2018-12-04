package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;

import static eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class MessageStatusDAOImplTest extends DAOTest {

    private MessageStatusDAOImpl messageStatusDAO;
    private MessageDAOImpl messageDAO;
    private UserDAOImpl userDAO;
    private Calendar currentDate = Calendar.getInstance();

    @Override
    protected void onSetUp(EntityManager entityManager) {
        messageStatusDAO = new MessageStatusDAOImpl();
        messageStatusDAO.entityManager = entityManager;

        messageDAO = new MessageDAOImpl();
        messageDAO.entityManager = entityManager;

        userDAO = new UserDAOImpl();
        userDAO.entityManager = entityManager;
    }

    @Test
    public void testFindByStatusUuid_should_returnMessageStatus_when_messageStatusExists() throws Exception {
        insertUser(1010L, "user101", "user101", new Date(), 1010L, true);
        User user = userDAO.getUser("user101");

        Party receiver = new Party();
        receiver.setId(1L);
        receiver.setEmail("party@party1.eu");
        receiver.setDisplayName("Party1");
        receiver.setNodeName("ref1");
        insertParty(receiver.getId(), receiver.getEmail(), receiver.getDisplayName(), receiver.getNodeName(), null, new Date(), 1010L, true, true);

        Party remoteParty = new Party();
        remoteParty.setId(2L);
        remoteParty.setEmail("remoteParty@remoteParty.eu");
        remoteParty.setDisplayName("Remote party");
        remoteParty.setNodeName("rem");
        insertParty(remoteParty.getId(), remoteParty.getEmail(), remoteParty.getDisplayName(), remoteParty.getNodeName(), null, currentDate.getTime(), 1010L, true, true);

        PartyIca partyIca = new PartyIca();
        partyIca.setId(1L);
        partyIca.setParty(receiver);
        partyIca.setRemoteParty(remoteParty);
        insertPartyIca(partyIca.getId(), receiver.getId(), remoteParty.getId(), 1010L, currentDate.getTime(), currentDate.getTime(), currentDate.getTime(), 1010L, true);

        Message message = generateMessage(receiver, remoteParty, Message.MessageState.INCOMING);
        message.setLocalParty(receiver);
        message.setCreatedBy(user);
        messageDAO.save(message);

        String statusUuid = "uuid_local_test_AT_4317";
        insertMessageStatus(122L, message.getId(), statusUuid, MessageStatus.State.INCOMING, MessageStatus.Status.READ, "READ", "ETX-ADAPTER-SYS-USER", new Date(), null, null);
        MessageStatus messageStatus = messageStatusDAO.findByStatusUuid(statusUuid);
        Assert.assertNotNull(messageStatus);
        Assert.assertEquals(messageStatus.getStatusUuid(), statusUuid);
    }

    @Test
    public void testFindByStatusUuid_should_throwException_when_noMessageStatus() throws Exception {
        String statusUuid = "uuid_local_test_AT_4317";
        MessageStatus messageStatus = messageStatusDAO.findByStatusUuid(statusUuid);

        assertThat(messageStatus, is(nullValue()));
    }

    @Test
    public void testSaveOrUpdateMessageStatus_should_save_when_noId() throws Exception {


        MessageStatus messageStatus = new MessageStatus();

        MessageStatusDAOImpl messageStatusDAOSpy = Mockito.spy(messageStatusDAO);

        messageStatusDAOSpy.saveOrUpdateMessageStatus(messageStatus);

        verify(messageStatusDAOSpy).saveOrUpdateMessageStatus(argThat(sameInstance(messageStatus)));
        verify(messageStatusDAOSpy).save(argThat(sameInstance(messageStatus)));
        verifyNoMoreInteractions(messageStatusDAOSpy);
    }

    @Test
    public void testSaveOrUpdateMessageStatus_should_update_when_idIsSet() throws Exception {


        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setId(132L);

        MessageStatusDAOImpl messageStatusDAOSpy = Mockito.spy(messageStatusDAO);

        messageStatusDAOSpy.saveOrUpdateMessageStatus(messageStatus);

        verify(messageStatusDAOSpy).saveOrUpdateMessageStatus(argThat(sameInstance(messageStatus)));
        verify(messageStatusDAOSpy).update(argThat(sameInstance(messageStatus)));
        verifyNoMoreInteractions(messageStatusDAOSpy);
    }
}