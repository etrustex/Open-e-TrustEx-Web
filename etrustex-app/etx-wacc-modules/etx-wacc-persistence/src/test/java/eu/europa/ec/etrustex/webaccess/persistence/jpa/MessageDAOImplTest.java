package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.Message.MessageState;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class MessageDAOImplTest extends DAOTest {

    private MessageDAOImpl messageDAO;
    private MessageStatusDAOImpl messageStatusDAO;
    private UserDAOImpl userDAO;
    private MessageReadStatusDAOImpl messageReadStatusDAO;

    private User defaultUser;

    @Override
    protected void onSetUp(EntityManager entityManager) {

        messageDAO = new MessageDAOImpl();
        messageDAO.entityManager = entityManager;

        messageReadStatusDAO = new MessageReadStatusDAOImpl();
        messageReadStatusDAO.entityManager = entityManager;

        messageStatusDAO = new MessageStatusDAOImpl();
        messageStatusDAO.entityManager = entityManager;

        userDAO = new UserDAOImpl();
        userDAO.entityManager = entityManager;

        Calendar currentDate = Calendar.getInstance();
        //currentDate.set(2012, 9, 10);

        defaultUser = new User();
        defaultUser.setId(1L);
        defaultUser.setLogin("user1");
        defaultUser.setCreatedOn(currentDate.getTime());
        defaultUser.setCreatedBy(defaultUser);
        defaultUser.setActiveState(true);

        insertUser(defaultUser.getId(), defaultUser.getLogin(), defaultUser.getName(), defaultUser.getCreatedOn(), defaultUser.getCreatedBy().getId(), defaultUser.getActiveState());

        insertParty(1L, "party@party1.eu", "Party1", "ref1", null, currentDate.getTime(), 1L, true, true);
        insertParty(2L, "party@party2.eu", "Party2", "ref2", null, currentDate.getTime(), 1L, true, true);
        insertParty(3L, "party@party3.eu", "Party3", "ref3", null, currentDate.getTime(), 1L, true, true);

        insertParty(10L, "remoteParty@party.eu", "partySender", "partySender", null, currentDate.getTime(), 1L, true, true);

        insertLanguage(1L, "en", "english");
        insertLanguage(2L, "ro", "romanian");
        insertLanguage(3L, "el", "greek");
        insertLanguage(4L, "pl", "polish");
    }

    @Test
    public void testNoMessages() {

        List<Message> msgs = messageDAO.getMessages(null, MessageState.INCOMING, null, null, true, 1, 10);
        Assert.assertNotNull("There's no message in database", msgs);
        Assert.assertTrue("There's no message in database", msgs == null
                || msgs.size() == 0);
    }

    @Test
    public void testAddMessage() {

        Collection<Message> msgs = new ArrayList<>();

        msgs.add(createMessageGraph(1L, 10L, 1L, MessageState.INCOMING));
        msgs.add(createMessageGraph(2L, 10L, 1L, MessageState.SENT));
        msgs.add(createMessageGraph(1L, 10L, 1L, MessageState.INCOMING));
        msgs.add(createMessageGraph(2L, 10L, 1L, MessageState.SENT));
        msgs.add(createMessageGraph(3L, 10L, 1L, MessageState.DRAFT));
        msgs.add(createMessageGraph(3L, 10L, 1L, MessageState.DRAFT));

        for (Message message : msgs) {
            messageDAO.save(message);
        }
    }

    @Test
    public void testQueryAll() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();
        Message msg1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        msg1.setLocalParty(localParty);
        msg1.setActiveState(true);
        msgs.add(msg1);

        Message msg2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        msg2.setLocalParty(localParty);
        msg2.setActiveState(true);
        msgs.add(msg2);

        Message msg3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        msg3.setLocalParty(localParty);
        msg3.setActiveState(false);
        msgs.add(msg3);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        List<Message> resultDataList = messageDAO.getMessages(null, MessageState.INCOMING, localParty, null, true, 1, 10);
        Assert.assertNotNull("There's no message in database", resultDataList);
        Assert.assertTrue("There's no message in database",
                resultDataList.size() == 2);

        Assert.assertEquals(msg1.getId(), resultDataList.get(0).getId());
        Assert.assertEquals(msg2.getId(), resultDataList.get(1).getId());
    }

    @Test
    public void testQueryUnread() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();
        Message msg1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        msg1.setLocalParty(localParty);
        msg1.setActiveState(true);
        msgs.add(msg1);

        Message msg2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        msg2.setLocalParty(localParty);
        msg2.setActiveState(true);
        msgs.add(msg2);

        Message msg3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        msg3.setLocalParty(localParty);
        msg3.setActiveState(false);
        msgs.add(msg3);

        Message msgRead = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        msgRead.setLocalParty(localParty);
        msgs.add(msgRead);

        User user = new User();
        user.setId(101L);
        insertUser(101L, "user101", "user101", Calendar.getInstance().getTime(), 1L, true);


        for (Message message : msgs) {
            messageDAO.save(message);
        }

        messageReadStatusDAO.markMessageReadByUser(msgRead.getId(), 101L);

        List<Message> resultDataList = messageDAO.getUnreadMessages(user, null, MessageState.INCOMING, localParty, null, true, 1, 10);
        Assert.assertNotNull("There's no message in database", resultDataList);
        Assert.assertTrue("There's no message in database",
                resultDataList.size() == 2); //the read one should not be returned

        Assert.assertEquals(resultDataList.get(0).getId(), msg1.getId());
        Assert.assertEquals(resultDataList.get(1).getId(), msg2.getId());
    }


    @Test
    public void queryNoneStatuses() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();
        Message msg1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg1.setLocalParty(localParty);
        msg1.setActiveState(true);
        msgs.add(msg1);

        Message msg2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg2.setLocalParty(localParty);
        msg2.setActiveState(true);
        msgs.add(msg2);

        MessageStatus ms = new MessageStatus();
        ms.setMstStatus(MessageStatus.Status.READ);
        Message msg3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg3.setLocalParty(localParty);
        msg3.setActiveState(true);
        ms.setMessage(msg3);
        messageStatusDAO.save(ms);
        msg3.setLastStatus(ms);

        msgs.add(msg3);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        List<Message> resultDataList = messageDAO.getMessages(null, MessageState.SENT, null, localParty, null, true, 1, 10);
        Assert.assertNotNull("There's no message in database", resultDataList);
        Assert.assertTrue("There's no message in database",
                resultDataList.size() == 2);

        Assert.assertEquals(msg1.getId(), resultDataList.get(0).getId());
        Assert.assertEquals(msg2.getId(), resultDataList.get(1).getId());
    }

    @Test
    public void queryReadStatuses() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();
        Message msg1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg1.setLocalParty(localParty);
        msg1.setActiveState(true);
        msgs.add(msg1);

        Message msg2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg2.setLocalParty(localParty);
        msg2.setActiveState(true);
        msgs.add(msg2);

        MessageStatus ms = new MessageStatus();
        ms.setMstStatus(MessageStatus.Status.READ);
        Message msg3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg3.setLocalParty(localParty);
        msg3.setActiveState(true);
        ms.setMessage(msg3);
        messageStatusDAO.save(ms);
        msg3.setLastStatus(ms);

        msgs.add(msg3);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        List<Message> resultDataList = messageDAO.getMessages(null, MessageState.SENT, MessageStatus.Status.READ, localParty, null, true, 1, 10);
        Assert.assertNotNull("There's no message in database", resultDataList);
        Assert.assertTrue("There's no message in database",
                resultDataList.size() == 1);

        Assert.assertEquals(msg3.getId(), resultDataList.get(0).getId());
    }

    @Test
    public void queryDeliveredStatuses() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();
        Message msg1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg1.setLocalParty(localParty);
        msg1.setActiveState(true);
        msgs.add(msg1);

        Message msg2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg2.setLocalParty(localParty);
        msg2.setActiveState(true);
        msgs.add(msg2);

        MessageStatus ms = new MessageStatus();
        ms.setMstStatus(MessageStatus.Status.AVAILABLE);
        Message msg3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg3.setLocalParty(localParty);
        msg3.setActiveState(true);
        ms.setMessage(msg3);
        messageStatusDAO.save(ms);
        msg3.setLastStatus(ms);

        msgs.add(msg3);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        List<Message> resultDataList = messageDAO.getMessages(null, MessageState.SENT, MessageStatus.Status.AVAILABLE, localParty, null, true, 1, 10);
        Assert.assertNotNull("There's no message in database", resultDataList);
        Assert.assertTrue("There's no message in database",
                resultDataList.size() == 1);

        Assert.assertEquals(msg3.getId(), resultDataList.get(0).getId());
    }

    @Test
    public void queryFailedStatuses() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();
        Message msg1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg1.setLocalParty(localParty);
        msg1.setActiveState(true);
        msgs.add(msg1);

        Message msg2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg2.setLocalParty(localParty);
        msg2.setActiveState(true);
        msgs.add(msg2);

        MessageStatus ms = new MessageStatus();
        ms.setMstStatus(MessageStatus.Status.FAILED);
        Message msg3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.SENT);
        msg3.setLocalParty(localParty);
        msg3.setActiveState(true);
        ms.setMessage(msg3);
        messageStatusDAO.save(ms);
        msg3.setLastStatus(ms);

        msgs.add(msg3);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        List<Message> resultDataList = messageDAO.getMessages(null, MessageState.SENT, MessageStatus.Status.FAILED, localParty, null, true, 1, 10);
        Assert.assertNotNull("There's no message in database", resultDataList);
        Assert.assertTrue("There's no message in database",
                resultDataList.size() == 1);

        Assert.assertEquals(msg3.getId(), resultDataList.get(0).getId());
    }

    @Test
    public void testFilterBySubject() throws ParseException {
        Party localParty = getTestRecipient();
        String subject = "sub";

        Message message = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message.setLocalParty(localParty);
        message.setSubject(subject);
        message.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2001-12-03"));
        message.setActiveState(true);

        Message message1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1.setLocalParty(localParty);
        message1.setSubject(subject);
        message1.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2001-12-03"));
        message1.setActiveState(false);

        messageDAO.save(message);

        List<Message> messageList = messageDAO.getMessages(subject, MessageState.INCOMING, localParty, null, true, 1, 10);
        Assert.assertNotNull(messageList.get(0));
        Assert.assertEquals(message.getSubject(), messageList.get(0).getSubject());
    }

    @Test
    public void testFilterBySubjectAndUnread() throws ParseException {
        Party localParty = getTestRecipient();

        Message message = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message.setLocalParty(localParty);
        message.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2001-12-03"));
        message.setActiveState(true);

        Message message1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1.setLocalParty(localParty);
        message1.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2001-12-03"));
        message1.setActiveState(false);

        messageDAO.save(message);
        messageDAO.save(message1);

        List<Message> s = messageDAO.getUnreadMessages(defaultUser, null, MessageState.INCOMING, localParty, null, true, 1, 10);
        Assert.assertNotNull(s.get(0));
        Assert.assertEquals(message.getSubject(), s.get(0).getSubject());
    }

    @Test
    public void testFilterById2DiffUsers() throws ParseException {
        Party localParty = getTestRecipient();

        // 2 users for 1 folder
        // 1 user has read the message
        // test if the other user can fetch the message
        Message message = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message.setLocalParty(localParty);
        message.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2001-12-03"));
        message.setActiveState(true);

        Message message1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1.setLocalParty(localParty);
        message1.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2001-12-03"));
        message1.setActiveState(false);

        messageDAO.save(message);
        messageDAO.save(message1);

        insertUser(50L, "me", "name", Calendar.getInstance().getTime(), 1L, true);
        insertUser(45L, "meOther", "nameOther", Calendar.getInstance().getTime(), 1L, true);

        messageReadStatusDAO.markMessageReadByUser(message.getId(), 50L);

        List<Message> s = messageDAO.getMessages(null, MessageState.INCOMING, localParty, null, true, 1, 10);
        Assert.assertNotNull(s.get(0));
        Assert.assertEquals(message.getId(), s.get(0).getId());
    }


    @Test
    public void testGetMessagesByDossier() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();

        Message message1Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier1.setLocalParty(localParty);
        message1Dossier1.setReferenceId("dossier1");
        message1Dossier1.setActiveState(true);
        msgs.add(message1Dossier1);

        Message message2Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier1.setLocalParty(localParty);
        message2Dossier1.setReferenceId("dossier1");
        message2Dossier1.setReferenceId(message1Dossier1.getReferenceId());
        message2Dossier1.setActiveState(true);
        msgs.add(message2Dossier1);


        Message message3Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message3Dossier1.setLocalParty(localParty);
        message3Dossier1.setReferenceId("dossier1");
        message3Dossier1.setReferenceId(message1Dossier1.getReferenceId());
        message3Dossier1.setActiveState(true);
        msgs.add(message3Dossier1);

        Message message1Dossier2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier2.setLocalParty(localParty);
        message1Dossier2.setReferenceId("dossier2");
        message1Dossier2.setActiveState(true);
        msgs.add(message1Dossier2);

        Message message2Dossier2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier2.setLocalParty(localParty);
        message2Dossier2.setReferenceId("dossier2");
        message2Dossier2.setReferenceId(message1Dossier2.getReferenceId());
        message2Dossier2.setActiveState(true);
        msgs.add(message2Dossier2);

        Message message1Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier3.setLocalParty(localParty);
        message1Dossier3.setReferenceId("dossier3");
        message1Dossier3.setActiveState(true);
        msgs.add(message1Dossier3);

        Message message2Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier3.setLocalParty(localParty);
        message2Dossier3.setReferenceId("dossier3");
        message2Dossier3.setReferenceId(message1Dossier3.getReferenceId());
        message2Dossier3.setActiveState(true);
        msgs.add(message2Dossier3);

        Message message3Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message3Dossier3.setLocalParty(localParty);
        message3Dossier3.setReferenceId("dossier3");
        message3Dossier3.setReferenceId(message1Dossier3.getReferenceId());
        message3Dossier3.setActiveState(false);
        msgs.add(message3Dossier3);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        List<Message> s = messageDAO.getUnreadMessagesByDossier(defaultUser, null, MessageState.INCOMING, localParty, null, true, 1, 3);

        Assert.assertEquals(s.size(), 7); // active messages only
        Assert.assertEquals(s.get(0).getReferenceId(), message1Dossier1.getReferenceId());
        Assert.assertEquals(s.get(1).getReferenceId(), message2Dossier1.getReferenceId());
        Assert.assertEquals(s.get(2).getReferenceId(), message3Dossier1.getReferenceId());
        Assert.assertEquals(s.get(3).getReferenceId(), message1Dossier2.getReferenceId());
        Assert.assertEquals(s.get(4).getReferenceId(), message2Dossier2.getReferenceId());
        Assert.assertEquals(s.get(5).getReferenceId(), message1Dossier3.getReferenceId());
        Assert.assertEquals(s.get(6).getReferenceId(), message2Dossier3.getReferenceId());

        Assert.assertEquals(s.get(0).getReferenceId(), "dossier1");
        Assert.assertEquals(s.get(3).getReferenceId(), "dossier2");
        Assert.assertEquals(s.get(5).getReferenceId(), "dossier3");
    }

    @Test
    public void test_countMessagesByDossier() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();

        Message message1Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier1.setLocalParty(localParty);
        message1Dossier1.setReferenceId("dossier1");
        message1Dossier1.setActiveState(true);
        msgs.add(message1Dossier1);

        Message message2Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier1.setLocalParty(localParty);
        message2Dossier1.setReferenceId("dossier1");
        message2Dossier1.setReferenceId(message1Dossier1.getReferenceId());
        message2Dossier1.setActiveState(true);
        msgs.add(message2Dossier1);


        Message message3Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message3Dossier1.setLocalParty(localParty);
        message3Dossier1.setReferenceId("dossier1");
        message3Dossier1.setReferenceId(message1Dossier1.getReferenceId());
        message3Dossier1.setActiveState(true);
        msgs.add(message3Dossier1);

        Message message1Dossier2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier2.setLocalParty(localParty);
        message1Dossier2.setReferenceId("dossier2");
        message1Dossier2.setActiveState(true);
        msgs.add(message1Dossier2);

        Message message2Dossier2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier2.setLocalParty(localParty);
        message2Dossier2.setReferenceId("dossier2");
        message2Dossier2.setReferenceId(message1Dossier2.getReferenceId());
        message2Dossier2.setActiveState(true);
        msgs.add(message2Dossier2);

        Message message1Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier3.setLocalParty(localParty);
        message1Dossier3.setReferenceId("dossier3");
        message1Dossier3.setActiveState(true);
        msgs.add(message1Dossier3);

        Message message2Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier3.setLocalParty(localParty);
        message2Dossier3.setReferenceId("dossier3");
        message2Dossier3.setReferenceId(message1Dossier3.getReferenceId());
        message2Dossier3.setActiveState(true);
        msgs.add(message2Dossier3);

        Message message1Dossier4 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier4.setLocalParty(localParty);
        message1Dossier4.setReferenceId("dossier4");
        message1Dossier4.setActiveState(false);
        msgs.add(message1Dossier4);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        long result = messageDAO.countUnreadMessagesByDossier(defaultUser, null, MessageState.INCOMING, localParty);

        Assert.assertEquals(result, 3); //is counting the active dossiers
    }

    @Test
    public void test_countMessagesByDossier_with_refid_null() {

        Party localParty = getTestRecipient();
        String remotePartyName = "partySender";

        Collection<Message> msgs = new ArrayList<>();

        Message message1Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier1.setLocalParty(localParty);
        message1Dossier1.setActiveState(true);
        message1Dossier1.setReferenceId(null);
        msgs.add(message1Dossier1);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        long result = messageDAO.countUnreadMessagesByDossier(defaultUser, null, MessageState.INCOMING, localParty);

        Assert.assertEquals(result, 1); //is counting the active dossiers
    }

    @Test
    public void testGetUnreadMessagesByDossier() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();

        Message message1Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier1.setLocalParty(localParty);
        message1Dossier1.setReferenceId("dossier1");
        message1Dossier1.setActiveState(true);
        msgs.add(message1Dossier1);

        Message message2Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier1.setLocalParty(localParty);
        message2Dossier1.setReferenceId("dossier1");
        message2Dossier1.setReferenceId(message1Dossier1.getReferenceId());
        message2Dossier1.setActiveState(true);
        msgs.add(message2Dossier1);


        Message message3Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message3Dossier1.setLocalParty(localParty);
        message3Dossier1.setReferenceId("dossier1");
        message3Dossier1.setReferenceId(message1Dossier1.getReferenceId());
        message3Dossier1.setActiveState(true);
        msgs.add(message3Dossier1);

        Message message1Dossier2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier2.setLocalParty(localParty);
        message1Dossier2.setReferenceId("dossier2");
        message1Dossier2.setActiveState(true);
        msgs.add(message1Dossier2);

        Message message2Dossier2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier2.setLocalParty(localParty);
        message2Dossier2.setReferenceId("dossier2");
        message2Dossier2.setReferenceId(message1Dossier2.getReferenceId());
        message2Dossier2.setActiveState(true);
        msgs.add(message2Dossier2);

        Message message1Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier3.setLocalParty(localParty);
        message1Dossier3.setReferenceId("dossier3");
        message1Dossier3.setActiveState(true);
        msgs.add(message1Dossier3);

        Message message2Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier3.setLocalParty(localParty);
        message2Dossier3.setReferenceId("dossier3");
        message2Dossier3.setReferenceId(message1Dossier3.getReferenceId());
        message2Dossier3.setActiveState(true);
        msgs.add(message2Dossier3);

        Message message3Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message3Dossier3.setLocalParty(localParty);
        message3Dossier3.setReferenceId("dossier3");
        message3Dossier3.setReferenceId(message1Dossier3.getReferenceId());
        message3Dossier3.setActiveState(false);
        msgs.add(message3Dossier3);

        User user = new User();
        user.setId(101L);
        insertUser(101L, "user101", "user101", Calendar.getInstance().getTime(), 1L, true);


        for (Message message : msgs) {
            messageDAO.save(message);
        }

        messageReadStatusDAO.markMessageReadByUser(message1Dossier1.getId(), 101L);
        messageReadStatusDAO.markMessageReadByUser(message2Dossier1.getId(), 101L);
        messageReadStatusDAO.markMessageReadByUser(message2Dossier3.getId(), 101L);
        messageReadStatusDAO.markMessageReadByUser(message3Dossier3.getId(), 101L);

        List<Message> s = messageDAO.getUnreadMessagesByDossier(user, null, MessageState.INCOMING, localParty, null, true, 1, 10);

        Assert.assertEquals(s.size(), 4); //is counting the number of active unread messages
        Assert.assertEquals(s.get(0).getReferenceId(), message3Dossier1.getReferenceId());
        Assert.assertEquals(s.get(1).getReferenceId(), message1Dossier2.getReferenceId());
        Assert.assertEquals(s.get(2).getReferenceId(), message2Dossier2.getReferenceId());
        Assert.assertEquals(s.get(3).getReferenceId(), message1Dossier3.getReferenceId());

        Assert.assertEquals(s.get(0).getReferenceId(), "dossier1");
        Assert.assertEquals(s.get(1).getReferenceId(), "dossier2");
        Assert.assertEquals(s.get(2).getReferenceId(), "dossier2");
        Assert.assertEquals(s.get(3).getReferenceId(), "dossier3");
    }

    @Test
    public void test_countUnreadMessagesByDossier() {
        Party localParty = getTestRecipient();

        Collection<Message> msgs = new ArrayList<>();

        Message message1Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier1.setLocalParty(localParty);
        message1Dossier1.setReferenceId("dossier1");
        message1Dossier1.setActiveState(true);
        msgs.add(message1Dossier1);

        Message message2Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier1.setLocalParty(localParty);
        message2Dossier1.setReferenceId("dossier1");
        message2Dossier1.setReferenceId(message1Dossier1.getReferenceId());
        message2Dossier1.setActiveState(true);
        msgs.add(message2Dossier1);


        Message message3Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message3Dossier1.setLocalParty(localParty);
        message3Dossier1.setReferenceId("dossier1");
        message3Dossier1.setReferenceId(message1Dossier1.getReferenceId());
        message3Dossier1.setActiveState(true);
        msgs.add(message3Dossier1);

        Message message1Dossier2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier2.setLocalParty(localParty);
        message1Dossier2.setReferenceId("dossier2");
        message1Dossier2.setActiveState(true);
        msgs.add(message1Dossier2);

        Message message2Dossier2 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier2.setLocalParty(localParty);
        message2Dossier2.setReferenceId("dossier2");
        message2Dossier2.setReferenceId(message1Dossier2.getReferenceId());
        message2Dossier2.setActiveState(true);
        msgs.add(message2Dossier2);

        Message message1Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier3.setLocalParty(localParty);
        message1Dossier3.setReferenceId("dossier3");
        message1Dossier3.setActiveState(true);
        msgs.add(message1Dossier3);

        Message message2Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message2Dossier3.setLocalParty(localParty);
        message2Dossier3.setReferenceId("dossier3");
        message2Dossier3.setReferenceId(message1Dossier3.getReferenceId());
        message2Dossier3.setActiveState(true);
        msgs.add(message2Dossier3);

        Message message3Dossier3 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message3Dossier3.setLocalParty(localParty);
        message3Dossier3.setReferenceId("dossier3");
        message3Dossier3.setReferenceId(message1Dossier3.getReferenceId());
        message3Dossier3.setActiveState(false);
        msgs.add(message3Dossier3);

        User user = new User();
        user.setId(101L);
        insertUser(101L, "user101", "user101", Calendar.getInstance().getTime(), 1L, true);


        for (Message message : msgs) {
            messageDAO.save(message);
        }

        messageReadStatusDAO.markMessageReadByUser(message1Dossier1.getId(), 101L);
        messageReadStatusDAO.markMessageReadByUser(message2Dossier1.getId(), 101L);
        messageReadStatusDAO.markMessageReadByUser(message2Dossier3.getId(), 101L);
        messageReadStatusDAO.markMessageReadByUser(message3Dossier3.getId(), 101L);

        long result = messageDAO.countUnreadMessagesByDossier(user, null, MessageState.INCOMING, localParty);

        Assert.assertEquals(result, 3); //is counting the active dossiers
    }

    @Test
    public void test_countUnreadMessagesByDossier_with_refid_null() {
        Party localParty = getTestRecipient();
        String remotePartyName = "partySender";

        Collection<Message> msgs = new ArrayList<>();

        Message message1Dossier1 = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message1Dossier1.setLocalParty(localParty);
        message1Dossier1.setReferenceId(null);
        message1Dossier1.setActiveState(true);
        msgs.add(message1Dossier1);

        User user = new User();
        user.setId(101L);
        insertUser(101L, "user101", "user101", Calendar.getInstance().getTime(), 1L, true);

        for (Message message : msgs) {
            messageDAO.save(message);
        }

        long result = messageDAO.countUnreadMessagesByDossier(user, null, MessageState.INCOMING, localParty);

        Assert.assertEquals(result, 1); //is counting the active dossiers
    }

    @Test
    public void testGetMessagesByDossierNoResults() {

        List<Message> s = messageDAO.getMessagesByDossier(null, MessageState.INCOMING, null, null, true, 1, 2);

        Assert.assertEquals(s.size(), 0);
    }

    @Test
    public void test_countMessagesByDossierNoResults() {

        long result = messageDAO.countMessagesByDossier(null, MessageState.INCOMING, null);

        Assert.assertEquals(result, 0L);
    }

    @Test
    public void test_getSubMessagesWithBinaryAttachments_shouldReturnSubList_when_filterEnabled() {
        Party localParty = getTestRecipient();

        Date currentDate = new Date();

        List<Message> messages = new ArrayList<>();
        Message msg1 = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg1.setId(50L);
        msg1.setLocalParty(localParty);
        insertMessage(msg1.getId(), "testContent", currentDate, "reference1", "subject1", localParty.getId(), 10L, MessageState.INCOMING.name(), "bundleReferenceID1", currentDate, null, true);
        messages.add(msg1);


        Message msg2 = createMessageGraph(51L, 10L, 1L, MessageState.INCOMING);
        msg2.setId(51L);
        msg2.setLocalParty(localParty);
        insertMessage(msg2.getId(), "testContent", currentDate, "reference1", "subject1", localParty.getId(), 10L, MessageState.INCOMING.name(), "bundleReferenceID1", currentDate, null, true);
        messages.add(msg2);

        Message msgRead = createMessageGraph(1L, 10L, 1L, MessageState.INCOMING);
        msgRead.setId(52L);
        msg2.setLocalParty(localParty);
        insertMessage(msgRead.getId(), "testContent", currentDate, "reference1", "subject1", localParty.getId(), 10L, MessageState.DRAFT.name(), "bundleReferenceID1", currentDate, 1L, true);
        messages.add(msgRead);

        User user = new User();
        user.setId(101L);
        insertUser(101L, "user101", "user101", currentDate, 1L, true);

        insertAttachment(150L, null, "SHA-512", "application/pdf", "filename1", 15054642L, "BINARY", "reference1", msg2.getId());

        //DO THE ACTUAL CALL
        List<Long> resultDataList = messageDAO.getMessageIdsWithBinaryAttachments(messages);

        Assert.assertNotNull("There's no message in database", resultDataList);
        Assert.assertTrue("There's no message in database", resultDataList.size() == 1);

        Assert.assertEquals(resultDataList.get(0), msg2.getId());
    }

    @Test
    public void test_getSubMessagesReadByUser_shouldReturnSubList_when_filterEnabled() {
        Party localParty = getTestRecipient();

        Date currentDate = new Date();

        List<Message> messages = new ArrayList<>();
        Message msg1 = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg1.setId(50L);
        msg1.setLocalParty(localParty);
        insertMessage(msg1.getId(), "testContent", currentDate, "reference1", "subject1", localParty.getId(), 10L, MessageState.INCOMING.name(), "bundleReferenceID1", currentDate, null, true);
        messages.add(msg1);


        Message msg2 = createMessageGraph(51L, 10L, 1L, MessageState.INCOMING);
        msg2.setId(51L);
        msg2.setLocalParty(localParty);
        insertMessage(msg2.getId(), "testContent", currentDate, "reference1", "subject1", localParty.getId(), 10L, MessageState.INCOMING.name(), "bundleReferenceID1", currentDate, null, true);
        messages.add(msg2);

        Message msgRead = createMessageGraph(1L, 10L, 1L, MessageState.INCOMING);
        msgRead.setId(52L);
        msg2.setLocalParty(localParty);
        insertMessage(msgRead.getId(), "testContent", currentDate, "reference1", "subject1", localParty.getId(), 10L, MessageState.DRAFT.name(), "bundleReferenceID1", currentDate, 1L, true);
        messages.add(msgRead);

        User user = new User();
        user.setId(101L);
        insertUser(101L, "user101", "user101", currentDate, 1L, true);

        insertAttachment((long) 150, null, "SHA-512", "application/pdf", "filename1", 15054642L, "BINARY", "reference1", msg2.getId());

        messageReadStatusDAO.markMessageReadByUser(msgRead.getId(), 101L);

        //DO THE ACTUAL CALL
        List<Long> resultDataList = messageDAO.getSubMessagesReadByUser(messages, user.getId());

        Assert.assertNotNull("There's no message in database", resultDataList);
        Assert.assertTrue("There's no message in database", resultDataList.size() == 1);

        Assert.assertEquals(resultDataList.get(0), msgRead.getId());
    }

    @Test
    public void testFindByMessageBundleId() {
        Date currentDate = new Date();

        Party localParty = getTestRecipient();
        String remotePartyName = "partySender";

        Message msg1 = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg1.setId(50L);
        msg1.setLocalParty(localParty);
        String bundleId = "bundleReferenceID1";
        insertMessage(msg1.getId(), "testContent", currentDate, "reference1", "subject1", localParty.getId(), 10L, MessageState.INCOMING.name(), bundleId, currentDate, null, true);

        Message msg2 = createMessageGraph(51L, 10L, 1L, MessageState.INCOMING);
        msg2.setId(51L);
        msg2.setLocalParty(localParty);
        insertMessage(msg2.getId(), "testContent", currentDate, "reference2", "subject2", localParty.getId(), 10L, MessageState.INCOMING.name(), bundleId, currentDate, null, false);

        try {
            messageDAO.findByMessageBundleId(bundleId, localParty.getId(), remotePartyName);
            fail("NonUniqueResultException expected");
        } catch (NonUniqueResultException ex) {
        }
    }

    @Test
    public void testFindAndUpdate_should_updateMsgBundleId_after_msgWasSaved() {
        Party localParty = getTestRecipient();
        Long msgId = 50L;
        String bundleId = String.valueOf(new Random().nextLong());

        Date currentDate = new Date();

        Message msg = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg.setId(msgId);
        msg.setLocalParty(localParty);
        insertMessage(msg.getId(), "testContent", currentDate, "reference1", "subject1", localParty.getId(), 10L, MessageState.INCOMING.name(), null, currentDate, null, true);

        Message savedMsg = messageDAO.findById(msgId);
        assertThat(savedMsg.getId(), is(msgId));
        assertThat(savedMsg.getBundleId(), is(nullValue()));

        savedMsg.setBundleId(bundleId);
        Message updatedMsg = messageDAO.update(savedMsg);

        assertThat(updatedMsg.getId(), is(msgId));
        assertThat(updatedMsg.getBundleId(), is(bundleId));
    }

    private Party getTestRecipient() {
        Party recipient = new Party();
        recipient.setId(2L);
        recipient.setDisplayName("Party2");
        recipient.setNodeName("ref2");

        return recipient;
    }

    @Test
    public void test_getWarningMessages_should_returnNoMessage_when_warningNotifDisabled() {

        Calendar now = Calendar.getInstance();

        insertUser(1010L, "user101", "user101", now.getTime(), 1010L, true);

        Long busId = 101L;
        Long busConfigId = 1L;
        insertBusiness(busId, "EDMA", now.getTime(), 1010L, true);
        insertBusinessConfig(busConfigId, busId, BusinessConfigProperty.BUS_WARN_EMAIL_NOTIF_ENABLED.getCode(), "false");

        Party receiver = new Party();
        receiver.setId(101L);
        receiver.setEmail("party@party2.eu");
        receiver.setDisplayName("Party2");
        receiver.setNodeName("ref2");
        insertParty(receiver.getId(), receiver.getEmail(), receiver.getDisplayName(), receiver.getNodeName(), 101L, now.getTime(), 1010L, true, true);

        Message msg1 = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg1.setId(50L);
        msg1.setLocalParty(receiver);
        String bundleId = "bundleReferenceID1";
        insertMessage(msg1.getId(), "testContent", now.getTime(), "reference1", "subject1", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, true);

        Message msg2 = createMessageGraph(51L, 10L, 1L, MessageState.INCOMING);
        msg2.setId(51L);
        msg2.setLocalParty(receiver);
        insertMessage(msg2.getId(), "testContent2", now.getTime(), "reference2", "subject2", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, false);

        now.add(Calendar.DAY_OF_YEAR, -1);
        Date startDate = now.getTime();

        now.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = now.getTime();

        List<Message> resultDataList = messageDAO.getWarningMessages(startDate, endDate);

        Assert.assertNotNull(resultDataList);
        Assert.assertEquals(0, resultDataList.size());
    }

    @Test
    public void test_getWarningMessages_shouldReturnMessage_when_warningNotifEnabled() {
        Calendar now = Calendar.getInstance();

        insertUser(1010L, "user101", "user101", now.getTime(), 1010L, true);

        String code = BusinessConfigProperty.BUS_WARN_EMAIL_NOTIF_ENABLED.getCode();
        Long busId = 101L;
        Long busConfigId = 1L;
        insertBusiness(busId, "EDMA", now.getTime(), 1010L, true);
        insertBusinessConfig(busConfigId, busId, code, "true");

        Party receiver = new Party();
        receiver.setId(101L);
        receiver.setEmail("party@party2.eu");
        receiver.setDisplayName("Party2");
        receiver.setNodeName("ref2");
        insertParty(receiver.getId(), receiver.getEmail(), receiver.getDisplayName(), receiver.getNodeName(), 101L, now.getTime(), 1010L, true, true);

        Message msg1 = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg1.setId(50L);
        msg1.setLocalParty(receiver);
        String bundleId = "bundleReferenceID1";
        insertMessage(msg1.getId(), "testContent", now.getTime(), "reference1", "subject1", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, true);

        Message msg2 = createMessageGraph(51L, 10L, 1L, MessageState.INCOMING);
        msg2.setId(51L);
        msg2.setLocalParty(receiver);
        insertMessage(msg2.getId(), "testContent2", now.getTime(), "reference2", "subject2", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, false);

        now.add(Calendar.DAY_OF_YEAR, -1);
        Date startDate = now.getTime();

        now.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = now.getTime();

        List<Message> resultDataList = messageDAO.getWarningMessages(startDate, endDate);

        Assert.assertNotNull(resultDataList);
        Assert.assertEquals(1, resultDataList.size());
        Assert.assertEquals(msg1.getId(), resultDataList.get(0).getId());
    }

    @Test
    public void test_getWarningMessages_shouldReturnNoMessage_when_warningNotifEnabledAndNotified() {
        Calendar now = Calendar.getInstance();

        insertUser(1010L, "user101", "user101", now.getTime(), 1010L, true);

        Long busId = 101L;
        Long busConfigId = 1L;
        insertBusiness(busId, "EDMA", now.getTime(), 1010L, true);
        insertBusinessConfig(busConfigId, busId, BusinessConfigProperty.BUS_WARN_EMAIL_NOTIF_ENABLED.getCode(), "true");

        Party receiver = new Party();
        receiver.setId(101L);
        receiver.setEmail("party@party2.eu");
        receiver.setDisplayName("Party2");
        receiver.setNodeName("ref2");
        insertParty(receiver.getId(), receiver.getEmail(), receiver.getDisplayName(), receiver.getNodeName(), 101L, now.getTime(), 1010L, true, true);

        Message msg1 = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg1.setId(50L);
        msg1.setLocalParty(receiver);
        String bundleId = "bundleReferenceID1";
        insertMessage(msg1.getId(), "testContent", now.getTime(), "reference1", "subject1", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, true);

        Message msg2 = createMessageGraph(51L, 10L, 1L, MessageState.INCOMING);
        msg2.setId(51L);
        msg2.setLocalParty(receiver);
        insertMessage(msg2.getId(), "testContent2", now.getTime(), "reference2", "subject2", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, false);

        insertNotification(1L, msg1.getId(), null, receiver.getId(), receiver.getEmail(), Notification.NotificationType.WARNING_EMAIL, Notification.NotificationState.CREATED, new Date());

        now.add(Calendar.DAY_OF_YEAR, -1);
        Date startDate = now.getTime();

        now.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = now.getTime();

        List<Message> resultDataList = messageDAO.getWarningMessages(startDate, endDate);

        Assert.assertNotNull(resultDataList);
        Assert.assertEquals(0, resultDataList.size());
    }

    @Test
    public void test_getWarningMessages_shouldReturnMessage_when_warningNotifEnabledAndOtherNotifications() {
        Calendar now = Calendar.getInstance();

        insertUser(1010L, "user101", "user101", now.getTime(), 1010L, true);

        Long busId = 101L;
        Long busConfigId = 1L;
        insertBusiness(busId, "EDMA", now.getTime(), 1010L, true);
        insertBusinessConfig(busConfigId, busId, BusinessConfigProperty.BUS_WARN_EMAIL_NOTIF_ENABLED.getCode(), "true");

        Party receiver = new Party();
        receiver.setId(101L);
        receiver.setEmail("party@party2.eu");
        receiver.setDisplayName("Party2");
        receiver.setNodeName("ref2");
        insertParty(receiver.getId(), receiver.getEmail(), receiver.getDisplayName(), receiver.getNodeName(), 101L, now.getTime(), 1010L, true, true);

        Message msg1 = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg1.setId(50L);
        msg1.setLocalParty(receiver);
        String bundleId = "bundleReferenceID1";
        insertMessage(msg1.getId(), "testContent", now.getTime(), "reference1", "subject1", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, true);

        Message msg2 = createMessageGraph(51L, 10L, 1L, MessageState.INCOMING);
        msg2.setId(51L);
        msg2.setLocalParty(receiver);
        insertMessage(msg2.getId(), "testContent2", now.getTime(), "reference2", "subject2", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, false);

        insertNotification(1L, msg1.getId(), null, receiver.getId(), receiver.getEmail(), Notification.NotificationType.MESSAGE_RECEIVED_EMAIL, Notification.NotificationState.CREATED, new Date());

        now.add(Calendar.DAY_OF_YEAR, -1);
        Date startDate = now.getTime();

        now.add(Calendar.DAY_OF_YEAR, 2);
        Date endDate = now.getTime();

        List<Message> resultDataList = messageDAO.getWarningMessages(startDate, endDate);

        Assert.assertNotNull(resultDataList);
        Assert.assertEquals(1, resultDataList.size());
    }

    @Test
    public void test_getWarningMessages_shouldReturnNoMessage_when_messageNotExpired() {
        Calendar now = Calendar.getInstance();

        insertUser(1010L, "user101", "user101", now.getTime(), 1010L, true);

        Long busId = 101L;
        Long busConfigId = 1L;
        insertBusiness(busId, "EDMA", now.getTime(), 1010L, true);
        insertBusinessConfig(busConfigId, busId, BusinessConfigProperty.BUS_WARN_EMAIL_NOTIF_ENABLED.getCode(), "true");

        Party receiver = new Party();
        receiver.setId(101L);
        receiver.setEmail("party@party2.eu");
        receiver.setDisplayName("Party2");
        receiver.setNodeName("ref2");
        insertParty(receiver.getId(), receiver.getEmail(), receiver.getDisplayName(), receiver.getNodeName(), 101L, now.getTime(), 1010L, true, true);

        Message msg1 = createMessageGraph(50L, 10L, 1L, MessageState.INCOMING);
        msg1.setId(50L);
        msg1.setLocalParty(receiver);
        String bundleId = "bundleReferenceID1";
        insertMessage(msg1.getId(), "testContent", now.getTime(), "reference1", "subject1", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, true);

        Message msg2 = createMessageGraph(51L, 10L, 1L, MessageState.INCOMING);
        msg2.setId(51L);
        msg2.setLocalParty(receiver);
        insertMessage(msg2.getId(), "testContent2", now.getTime(), "reference2", "subject2", receiver.getId(), 10L, MessageState.INCOMING.name(), bundleId, now.getTime(), null, false);

        Date startDate = now.getTime();

        now.add(Calendar.DAY_OF_YEAR, 1);
        Date endDate = now.getTime();

        List<Message> resultDataList = messageDAO.getWarningMessages(startDate, endDate);

        Assert.assertNotNull(resultDataList);
        Assert.assertEquals(resultDataList.size(), 0);
    }

    @Test
    public void test_saveSearchableContent() {
        // given
        Message message = createMessageGraph(1L, 10L, 1L, MessageState.INCOMING);
        message.setSubject("DPD/123-22(2014)");

        // when
        messageDAO.save(message);

        // then
        Message dbMessage = messageDAO.findById(message.getId());

        assertThat(dbMessage.getSearchableContent(), is("dpd123222014"));
    }

    @Test
    public void test_updateSearchableContent() {
        // given
        Message message = createMessageGraph(1L, 10L, 1L, MessageState.INCOMING);
        message.setSubject("DPD/123-22(2014)");
        messageDAO.save(message);

        Message dbMessage = messageDAO.findById(message.getId());
        dbMessage.setSubject(message.getSubject() + ".DG.B/2");

        // when
        messageDAO.update(dbMessage);

        // then
        dbMessage = messageDAO.findById(message.getId());

        assertThat(dbMessage.getSearchableContent(), is("dpd123222014dgb2"));
    }

    @Test
    public void test_findBySearchableContent() {
        // given
        Party localParty = getTestRecipient();

        Message message = createMessageGraph(localParty.getId(), 10L, 1L, MessageState.INCOMING);
        message.setLocalParty(localParty);
        message.setActiveState(true);
        message.setSubject("DPD/123-22(2014)");

        messageDAO.save(message);

        // when
        List<Message> messages = messageDAO.getMessages("22(2014)", MessageState.INCOMING, localParty, null, true, 1, 2);

        // then
        assertThat(messages, hasSize(1));
        assertThat(messages.iterator().next().getSearchableContent(), is("dpd123222014"));
    }
}
