package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.facade.BusinessFacade;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.utils.RedirectViewWithLogging;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class MessageDeleteControllerTest extends AbstractControllerTest{

    @InjectMocks
    private MessageDeleteController controller;

    @Mock
    private MailboxManager mailboxManager;

    @Mock
    protected SecurityChecker securityChecker;;

    @Mock
    private BusinessFacade businessFacade;

    @Mock
    private PageState pageState;

    @Mock
    protected UserSessionContext userSessionContext;

    @Test
    public void test_isAuthorized_should_returnFalse_when_messageIsNull() throws Exception {
        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(null);

        assertThat(result, is(false));
    }

    @Test
    public void test_isAuthorized_should_returnFalse_when_messageLocalPartyIsNotAccessible() throws Exception {
        Message message = new Message();
        message.setMsgState(Message.MessageState.DRAFT);
        Party party = new Party();
        message.setLocalParty(party);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(false));
    }

    @Test
    public void test_isAuthorized_should_returnFalse_when_messageIsNotDRAFT() throws Exception {
        long messageId = 5L;
        Party party = new Party();
        ArrayList<Party> parties = new ArrayList<>();
        parties.add(party);
        Message message = new Message();
        message.setId(messageId);
        message.setLocalParty(party);
        message.setMsgState(Message.MessageState.SENT);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);
        when(mailboxManager.getMessageByMessageId(messageId)).thenReturn(message);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(false));
    }

    @Test
    public void test_isAuthorized_should_returnTrue_when_messageLocalPartyIsAccessible() throws Exception {
        Message message = new Message();
        message.setMsgState(Message.MessageState.DRAFT);
        Party party = new Party();
        message.setLocalParty(party);
        message.setActiveState(true);
        ArrayList<Party> parties = new ArrayList<>();
        parties.add(party);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(true));
    }

    @Test
    public void test_deleteDraftMessage_should_403_when_notAuthorized() throws Exception {
        Long messageId = 53L;
        Message message = new Message();
        message.setId(messageId);

        when(mailboxManager.getMessageByMessageId(messageId)).thenReturn(message);

        MessageDeleteController spy = spy(controller);
        doReturn(false).when(spy).isAuthorized(message);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        User loggedInUser = mock(User.class);

        when(mockRequest.getRequestURI()).thenReturn("/e-trustex/inbox.do");
        when(mockRequest.getQueryString()).thenReturn("test=10");
        when(userSessionContext.getUser()).thenReturn(loggedInUser);
        when(loggedInUser.getLogin()).thenReturn("testUsr");

        // DO THE ACTUAL CALL
        ModelAndView result = spy.deleteDraftMessage(messageId, mockRequest);

        assertThat(((RedirectViewWithLogging) result.getView()).getUrl(), is(equalTo("error/error403.do")));
    }

    @Test
    public void test_deleteDraftMessage_should_disableMessage_when_authorized() throws Exception {
        Long messageId = 53L;
        Long partyId = 33L;
        Message message = new Message();
        message.setId(messageId);
        Party party = new Party();
        party.setId(partyId);
        message.setLocalParty(party);

        controller.pageState = new PageState();

        when(mailboxManager.getMessageByMessageId(messageId)).thenReturn(message);

        MessageDeleteController spy = spy(controller);
        doReturn(true).when(spy).isAuthorized(message);

        // DO THE ACTUAL CALL
        ModelAndView result = spy.deleteDraftMessage(messageId, request);

        assertThat((String)result.getModelMap().get("pid"), is(equalTo(partyId.toString())));
        assertThat(((RedirectView) result.getView()).getUrl(), is(equalTo(WebAction.DRAFTS.getDo())));

        verify(businessFacade).disableMessage(message);

    }
}
