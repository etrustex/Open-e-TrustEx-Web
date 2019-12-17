package eu.europa.ec.etrustex.webaccess.web.view.business.generic.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Message;
import org.junit.Test;

public class GenericDraftsHandlerTest extends AbstractTest {
    private GenericDraftsHandler handler = new GenericDraftsHandler();

    @Test
    public void test_getMessageState_should_returnDraftMessageState() throws Exception {
        // DO THE ACTUAL CALL
        assertThat(handler.getMessageState(), is(equalTo(Message.MessageState.DRAFT)));
    }
}
