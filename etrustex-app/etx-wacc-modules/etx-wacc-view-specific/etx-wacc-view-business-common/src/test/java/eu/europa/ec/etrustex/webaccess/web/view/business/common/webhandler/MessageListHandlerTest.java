package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListEntry;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.ResultDataList;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Map;

import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.MESSAGE_LIST_ATTR;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageListHandlerTest extends AbstractTest {

    private final Message.MessageState MESSAGE_STATE = Message.MessageState.INCOMING;
    private final long RESULT_CNT = 123;
    private final int PAGE = 4;

    @InjectMocks
    private AbstractMessageListHandler handler = new AbstractMessageListHandler() {

        @Override
        protected Message.MessageState getMessageState() {
            return MESSAGE_STATE;
        }
    };

    @Mock
    protected UserSessionContext userSessionContext;

    @Mock
    private MailboxManager mailboxManager;

    @Mock
    private WebHandlerHelper webHandlerHelper;

    @Test
    public void test_buildMeta_should_addMandatoryObjectsToModel() throws Exception {
        MessageListQueryParams queryParams = new MessageListQueryParams();
        queryParams.setPage(PAGE);

        User user = new User();
        when(userSessionContext.getUser()).thenReturn(user);

        ArrayList<MessageListEntry> messages = new ArrayList<>();
        ResultDataList<MessageListEntry> result = new ResultDataList<>(messages, RESULT_CNT);

        when(mailboxManager.getMessages(queryParams, user, MESSAGE_STATE, null)).thenReturn(result);

        // DO THE ACTUAL CALL
        Map<String, Object> model = handler.buildBusinessModel(queryParams);

        assertThat((ArrayList<MessageListEntry>) model.get(MESSAGE_LIST_ATTR), is(sameInstance(messages)));
        assertThat((Long) model.get("resultCount"), is(RESULT_CNT));

        verify(webHandlerHelper).handlePageNavigation(model, PAGE, RESULT_CNT, 10);
    }

    @Test(expected = IllegalStateException.class)
    public void test_buildBusinessModel_should_throwIllegalStateException_when_queryParamTypeIncorrect() throws Exception {
        // DO THE ACTUAL CALL
        handler.buildBusinessModel(new MessageQueryParams());
    }
}
