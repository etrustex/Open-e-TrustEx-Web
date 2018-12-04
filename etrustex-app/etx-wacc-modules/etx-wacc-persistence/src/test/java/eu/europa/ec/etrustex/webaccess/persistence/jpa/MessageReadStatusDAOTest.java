package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.User;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Calendar;

public class MessageReadStatusDAOTest extends DAOTest {

    private MessageReadStatusDAOImpl messageReadStatusDAO;
    private MessageDAOImpl messageDAO;
    private UserDAOImpl userDAO;
    private Calendar currentDate = Calendar.getInstance();

    @Override
    protected void onSetUp(EntityManager entityManager) {
        currentDate.set(2012, 9, 10);

        messageReadStatusDAO = new MessageReadStatusDAOImpl();
        messageReadStatusDAO.entityManager = entityManager;

        messageDAO = new MessageDAOImpl();
        messageDAO.entityManager = entityManager;

        userDAO = new UserDAOImpl();
        userDAO.entityManager = entityManager;
    }

    @Test
    public void testMarkMessageReadByUser() {

        insertUser(1010L, "user101", "user101", currentDate.getTime(), 1010L, true);
        User user = userDAO.getUser("user101");

        Party receiver = new Party();
        receiver.setId(1L);
        receiver.setEmail("party@party1.eu");
        receiver.setDisplayName("Party1");
        receiver.setNodeName("ref1");
        insertParty(receiver.getId(), receiver.getEmail(), receiver.getDisplayName(), receiver.getNodeName(), null, currentDate.getTime(), 1010L, true, true);

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

        message = messageDAO.findByReferenceId(message.getReferenceId());
        messageReadStatusDAO.markMessageReadByUser(message.getId(), 1010L);
    }
}
