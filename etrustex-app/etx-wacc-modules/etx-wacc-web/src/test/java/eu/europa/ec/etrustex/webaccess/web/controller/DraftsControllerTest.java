package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;

public class DraftsControllerTest  extends AbstractControllerTest {
    @Test
    public void test_getAction_should_returnDraftsAction() throws Exception {
        DraftsController controller = new DraftsController();

        // DO THE ACTUAL CALL
        assertThat(controller.getAction(), is(equalTo(WebAction.DRAFTS)));
    }

    /*@Test
    public void test_getMessageViewAction_should_messageEditAction() throws Exception {
        DraftsController controller = new DraftsController();

        // DO THE ACTUAL CALL
        assertThat(controller.getMessageViewAction(), is(equalTo(WebAction.MESSAGE_EDIT)));
    }*/
}
