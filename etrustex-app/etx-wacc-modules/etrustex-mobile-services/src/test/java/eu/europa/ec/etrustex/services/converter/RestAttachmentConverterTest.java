package eu.europa.ec.etrustex.services.converter;

import eu.europa.ec.etrustex.services.model.RestAttachment;
import eu.europa.ec.etrustex.services.model.RestAttachments;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RestAttachmentConverterTest extends AbstractTest {

    @Test
    public void test_convertToRestAttachments_null_Attachments_should_return_empty_list() {
        //ACTUAL CALL
        RestAttachments result = RestAttachmentConverter.getInstance().convertToRestAttachments(null);

        assertThat(result.getAttachmentList().size(), is(0));
    }

    @Test
    public void test_convertToRestAttachments_empty_Attachments_should_return_empty_list() {
        List<Attachment> attachments = new ArrayList<>();

        //ACTUAL CALL
        RestAttachments result = RestAttachmentConverter.getInstance().convertToRestAttachments(attachments);

        assertThat(result.getAttachmentList().size(), is(0));
    }

    @Test
    public void test_convertToRestAttachments_should_return_RestAttachments() {
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment1 = new Attachment();
        attachment1.setId(1L);
        attachments.add(attachment1);
        Attachment attachment2 = new Attachment();
        attachment2.setId(2L);
        attachments.add(attachment2);

        RestAttachments restAttachments = new RestAttachments();
        RestAttachment restAttachment1 = new RestAttachment();
        restAttachment1.setId(String.valueOf(attachment1.getId()));
        restAttachments.addAttachmentListItem(restAttachment1);
        RestAttachment restAttachment2 = new RestAttachment();
        restAttachment2.setId(String.valueOf((attachment2.getId())));
        restAttachments.addAttachmentListItem(restAttachment2);

        //ACTUAL CALL
        RestAttachments result = RestAttachmentConverter.getInstance().convertToRestAttachments(attachments);

        assertThat(result.getAttachmentList().size(), is(2));
        assertThat(result.getAttachmentList().get(0), is(restAttachment1));
        assertThat(result.getAttachmentList().get(1), is(restAttachment2));
    }

}
