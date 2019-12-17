package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.test.support.AbstractControllerTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AbstractMessageDetailsControllerTest extends AbstractControllerTest {

    private static final WebAction ACTION = WebAction.MESSAGE_VIEW_RECEIVED;

    @InjectMocks
    private AbstractMessageDetailsController controller = new AbstractMessageDetailsController() {
        @Override
        protected WebAction getAction() {
            return ACTION;
        }
    };

    @Mock
    protected SecurityChecker securityChecker;

    @Mock
    protected MailboxManager mailboxManager;

    @Mock
    protected UserSessionContext userSessionContext;


    @Test
    public void test_buildQueryParams_should_createMessageQueryParams() throws Exception {
        Map<String, String[]> paramMap = new HashMap<>();
        Long messageId = 308945L;
        paramMap.put("mid", new String[]{messageId.toString()});
        PageState pageState = new PageState();
        pageState.populate(paramMap);

        Party party = new Party();

        // DO THE ACTUAL CALL
        QueryParams queryParams = controller.buildQueryParams(pageState, party, 10);

        assertThat(queryParams, is(instanceOf(MessageQueryParams.class)));
        assertThat(((MessageQueryParams)queryParams).getMessageId(), is(equalTo(messageId)));
        assertThat(queryParams.getParty(), is(equalTo(party)));
    }

    @Test
    public void test_isAuthorized_should_returnFalse_when_messageIsNull() throws Exception {
        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(null);

        assertThat(result, is(false));
    }

    @Test
    public void test_isAuthorized_should_returnFalse_when_messageLocalPartyIsNotAccessible() throws Exception {
        Message message = new Message();
        Party party = new Party();
        message.setLocalParty(party);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(false);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(false));
    }

    @Test
    public void test_isAuthorized_should_returnTrue_when_messageLocalPartyIsAccessible() throws Exception {
        Message message = new Message();
        Party party = new Party();
        message.setLocalParty(party);
        message.setActiveState(true);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean result = controller.isAuthorized(message);

        assertThat(result, is(true));
    }

    @Test
    public void test_getCurrentParty_should_returnNull_when_notAuthorized() throws Exception {
        long messageId = 3L;
        Message message = new Message();
        controller.pageState = new PageState();
        controller.pageState.setMessageId(messageId);

        when(mailboxManager.getMessageByMessageId(messageId)).thenReturn(message);

        AbstractMessageDetailsController spy = spy(controller);
        doReturn(false).when(spy).isAuthorized(message);

        // DO THE ACTUAL CALL
        Party result = spy.getCurrentParty();

        assertThat(result, is(nullValue()));

    }

    @Test
    public void test_getCurrentParty_should_returnParty_when_isAuthorized() throws Exception {
        long messageId = 3L;
        Message message = new Message();
        Party party = new Party();
        message.setLocalParty(party);
        controller.pageState = new PageState();
        controller.pageState.setMessageId(messageId);

        when(mailboxManager.getMessageByMessageId(messageId)).thenReturn(message);

        AbstractMessageDetailsController spy = spy(controller);
        doReturn(true).when(spy).isAuthorized(message);

        // DO THE ACTUAL CALL
        Party result = spy.getCurrentParty();

        assertThat(result, is(equalTo(party)));
    }

    @Test
    public void test_isAuthorized_should_returnFalse_when_isNotActive() throws Exception {
        long messageId = 3L;
        Message message = new Message();
        Party party = new Party();
        message.setLocalParty(party);
        message.setActiveState(false);
        controller.pageState = new PageState();
        controller.pageState.setMessageId(messageId);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean authorized = controller.isAuthorized(message);

        assertThat(authorized, is(false));
    }

    @Test
    public void test_isAuthorized_should_returnTrue_when_isActive() throws Exception {
        long messageId = 3L;
        Message message = new Message();
        Party party = new Party();
        message.setLocalParty(party);
        message.setActiveState(true);
        controller.pageState = new PageState();
        controller.pageState.setMessageId(messageId);

        when(securityChecker.canAccessMessagesOfParty(party)).thenReturn(true);

        // DO THE ACTUAL CALL
        boolean authorized = controller.isAuthorized(message);

        assertThat(authorized, is(true));
    }
}
