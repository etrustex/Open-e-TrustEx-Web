/**
 *
 */
package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.AttachmentType;
import eu.europa.ec.etrustex.webaccess.model.Message.MessageState;
import junit.framework.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.List;

import static eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author apladap
 */
public class AttachmentDAOTest extends DAOTest {

    private AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();

    private Calendar currentDate = Calendar.getInstance();
    private Calendar issueDate = Calendar.getInstance();


    @Override
    protected void onSetUp(EntityManager entityManager) {

        attachmentDAO.entityManager = entityManager;

        currentDate.set(2012, 9, 10); //10-SEP-2012
        issueDate.set(2012, 7, 10); //10-JUL-2012

        insertUser(1L, "user1", "user1", currentDate.getTime(), 1L, true);

        insertParty(3L, "party@party3.eu", "Party3", "ref3", null, currentDate.getTime(), 1L, true, true);
        insertParty(4L, "party@party4.eu", "Party4", "ref4", null, currentDate.getTime(), 1L, true, true);

        insertParty(10L, "remoteParty@party.eu", "partySender", "partySender", null, currentDate.getTime(), 1L, true, true);

        insertUser(101L, "user101", "user101", currentDate.getTime(), 1L, true);
        insertUser(102L, "user102", "user102", currentDate.getTime(), 1L, true);

        insertMessage(50L, "testContent", issueDate.getTime(), "reference1", "subject1", 4L, 10L, MessageState.INCOMING.name(), "bundleReferenceID1", currentDate.getTime(), 1L, true);

        insertAttachment(150L, null, "SHA-512", "application/pdf", "filename1", 15054642L, "BINARY", "reference1", 50L);
        insertAttachment(151L, null, "SHA-512", "application/pdf", "filename2", 43543642L, "BINARY", "reference2", 50L);
        insertAttachment(152L, null, "SHA-512", "application/pdf", "filename3", 15245323L, "BINARY", "reference3", 50L);
        insertAttachment(153L, null, "SHA-512", "application/pdf", "filename4", 68653262L, "BINARY", "reference4", 50L);
        insertAttachment(154L, null, "SHA-512", "application/pdf", "filename5", 68524252L, "BINARY", "reference5", 50L);
        insertAttachment(155L, null, "SHA-512", "application/xml", "filename6", 24964632L, "METADATA", "reference6", 50L);
    }

    @Test
    public void testGetReferenceIdListByMessageId() {
        List<Attachment> attachmentsList = attachmentDAO.getAttachmentsListByMessageId(50L);

        Assert.assertEquals(attachmentsList.size(), 6);
        Assert.assertEquals(attachmentsList.get(0).getWrapperId(), "reference1");
        Assert.assertEquals(attachmentsList.get(1).getWrapperId(), "reference2");
        Assert.assertEquals(attachmentsList.get(2).getWrapperId(), "reference3");
        Assert.assertEquals(attachmentsList.get(3).getWrapperId(), "reference4");
        Assert.assertEquals(attachmentsList.get(4).getWrapperId(), "reference5");
        Assert.assertEquals(attachmentsList.get(5).getWrapperId(), "reference6");
    }

    @Test
    public void test_countAttachmentsByMessageAndType_should_returnNoBinaryAtt_when_messageWithoutAtt() {
        long messageId = 51L;
        insertMessage(messageId, "testContent", issueDate.getTime(), "reference1", "subject1", 4L, 10L, MessageState.INCOMING.name(), "bundleReferenceID1", currentDate.getTime(), 1L, true);

        // ACTUAL CALL
        long total = attachmentDAO.countAttachmentsByMessageAndType(messageId, AttachmentType.BINARY);

        assertThat(total, is(0L));
    }

    @Test
    public void test_countAttachmentsByMessageAndType_should_returnNoMetadataAtt_when_messageWithoutAtt() {
        long messageId = 51L;
        insertMessage(messageId, "testContent", issueDate.getTime(), "reference1", "subject1", 4L, 10L, MessageState.INCOMING.name(), "bundleReferenceID1", currentDate.getTime(), 1L, true);

        // ACTUAL CALL
        long total = attachmentDAO.countAttachmentsByMessageAndType(messageId, AttachmentType.METADATA);

        assertThat(total, is(0L));
    }

    @Test
    public void test_countAttachmentsByMessageAndType_should_returnBinaryAtt() {
        long messageId = 50L;

        // ACTUAL CALL
        long total = attachmentDAO.countAttachmentsByMessageAndType(messageId, AttachmentType.BINARY);

        assertThat(total, is(5L));
    }

    @Test
    public void test_countAttachmentsByMessageAndType_should_returnMetadataAtt() {
        long messageId = 50L;

        // ACTUAL CALL
        long total = attachmentDAO.countAttachmentsByMessageAndType(messageId, AttachmentType.METADATA);

        assertThat(total, is(1L));
    }
}
