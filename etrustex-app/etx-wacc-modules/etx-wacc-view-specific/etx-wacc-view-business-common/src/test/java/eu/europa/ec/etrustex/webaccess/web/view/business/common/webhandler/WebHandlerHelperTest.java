package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class WebHandlerHelperTest extends AbstractTest {

    private final int PAGE_SIZE = 10;
    private final int RESULT_CNT = 123;
    private final int PAGE = 4;

    @InjectMocks
    WebHandlerHelper helper;

    @Mock
    private AttachmentHandler attachmentHandler;

    @Test
    public void test_handlePageNavigation_should_addNavigationPropertiesToModel() throws Exception {
        Map<String, Object> model = new HashMap<>();

        // DO THE ACTUAL CALL
        helper.handlePageNavigation(model, PAGE, RESULT_CNT, PAGE_SIZE);

        assertThat((Integer) model.get("firstPage"), is(1));
        assertThat((Integer) model.get("prevPage"), is(3));
        assertThat((Integer) model.get("lastPage"), is(13));
        assertThat((Integer) model.get("nextPage"), is(5));
        assertThat((Integer) model.get("currentPage"), is(4));
    }


    @Test
    public void test_fetchPayloadIfReady_should_addMessageToModel_when_payloadIsNotReady() throws Exception {
        HashMap<String, Object> model = new HashMap<>();
        Long messageId = 123L;
        Metadata metadata = new Metadata();

        when(attachmentHandler.getMetadata(messageId)).thenReturn(null);

        // DO THE ACTUAL CALL
        String payload = helper.fetchPayloadIfReady(model, messageId);

        assertThat(payload, is(nullValue()));
        assertThat((String) model.get("notReady"), is(equalTo("message.metadataNotReady")));
    }

    @Test
    public void test_fetchPayloadIfReady_should_addMessageToModel_when_metadataIsInFailedState() throws Exception {
        HashMap<String, Object> model = new HashMap<>();
        Long messageId = 123L;
        Metadata metadata = new Metadata();
        metadata.setMetadataState(Metadata.MetadataState.FAILED);

        when(attachmentHandler.getMetadata(messageId)).thenReturn(metadata);

        // DO THE ACTUAL CALL
        String payload = helper.fetchPayloadIfReady(model, messageId);

        assertThat(payload, is(nullValue()));
        assertThat((String) model.get("errorOccurred"), is(equalTo("message.metadataFailure")));
    }

    @Test
    public void test_fetchPayloadIfReady_should_returnPayload_when_metadataIsReady() throws Exception {
        HashMap<String, Object> model = new HashMap<>();
        Long messageId = 123L;
        Metadata metadata = new Metadata();
        metadata.setMetadataState(Metadata.MetadataState.DOWNLOADED);
        String content = "content";
        metadata.setContent(content);

        when(attachmentHandler.getMetadata(messageId)).thenReturn(metadata);

        // DO THE ACTUAL CALL
        String payload = helper.fetchPayloadIfReady(model, messageId);

        assertThat(payload, is(equalTo(content)));
        assertThat(model.get("errorOccurred"), is(nullValue()));
        assertThat(model.get("notReady"), is(nullValue()));
    }
}
