package eu.europa.ec.etrustex.webaccess.web.view.business.generic.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Message;
import org.junit.Test;

public class GenericInboxHandlerTest extends AbstractTest {
    private GenericInboxHandler handler = new GenericInboxHandler();

    @Test
    public void test_getMessageState_should_returnIncommingMessageState() throws Exception {
        // DO THE ACTUAL CALL
        assertThat(handler.getMessageState(), is(equalTo(Message.MessageState.INCOMING)));
    }
}
