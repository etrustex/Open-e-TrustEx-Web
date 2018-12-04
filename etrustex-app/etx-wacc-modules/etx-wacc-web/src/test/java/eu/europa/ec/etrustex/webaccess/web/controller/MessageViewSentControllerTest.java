package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class MessageViewSentControllerTest extends AbstractControllerTest {

    @InjectMocks
    private MessageViewSentController controller;

    @Mock
    private SecurityChecker securityChecker;

    @Test
    public void test_getAction_should_returnMessageViewSentAction() throws Exception {
        // DO THE ACTUAL CALL
        assertThat(controller.getAction(), is(equalTo(WebAction.MESSAGE_VIEW_SENT)));
    }

    @Test
    public void test_isAuthorized_should_returnTrue_when_correctState() throws Exception {
        Message message = new Message();
        Party party = new Party();
        message.setLocalParty(party);
        message.setActiveState(true);
        message.setMsgState(Message.MessageState.SENT);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(true));

        verify(securityChecker).canAccessMessagesOfParty(party);
        verifyNoMoreInteractions(securityChecker);
    }

    @Test
    public void test_isAuthorized_should_returnFalse_when_incorrectState() throws Exception {
        Message message = new Message();
        Party party = new Party();
        message.setLocalParty(party);
        message.setActiveState(true);
        message.setMsgState(Message.MessageState.INCOMING);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(false));

        verify(securityChecker).canAccessMessagesOfParty(party);
        verifyNoMoreInteractions(securityChecker);
    }
}
