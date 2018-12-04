package eu.europa.ec.etrustex.webaccess.web.view.business.generic.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Message;
import org.junit.Test;

public class GenericOutboxHandlerTest extends AbstractTest{

    private GenericOutboxHandler handler = new GenericOutboxHandler();

    @Test
    public void test_getMessageState_should_returnSentMessageState() throws Exception {
        // DO THE ACTUAL CALL
        assertThat(handler.getMessageState(), is(equalTo(Message.MessageState.SENT)));
    }
}
