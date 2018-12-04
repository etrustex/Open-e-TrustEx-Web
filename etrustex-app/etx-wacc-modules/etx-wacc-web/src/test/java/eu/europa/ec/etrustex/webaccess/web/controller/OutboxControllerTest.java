package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;


public class OutboxControllerTest extends AbstractControllerTest {
    @Test
    public void test_getAction_should_returnOutboxAction() throws Exception {
        OutboxController controller = new OutboxController();

        // DO THE ACTUAL CALL
        assertThat(controller.getAction(), is(equalTo(WebAction.OUTBOX)));
    }

    @Test
    public void test_getMessageViewAction_should_messageViewSentAction() throws Exception {
        OutboxController controller = new OutboxController();

        // DO THE ACTUAL CALL
        assertThat(controller.getMessageViewAction(), is(equalTo(WebAction.MESSAGE_VIEW_SENT)));
    }
}
