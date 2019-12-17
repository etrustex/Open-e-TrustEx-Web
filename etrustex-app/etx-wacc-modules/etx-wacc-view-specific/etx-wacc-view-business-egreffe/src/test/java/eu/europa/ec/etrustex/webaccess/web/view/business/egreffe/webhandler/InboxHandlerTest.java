package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.WebHandlerHelper;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.DossierResult;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListEntry;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler.MESSAGE_LIST_ATTR;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class InboxHandlerTest extends AbstractTest {

    @InjectMocks
    private InboxHandler handler = new InboxHandler();

    @Mock
    protected MailboxManager mailboxManager;

    @Mock
    protected UserSessionContext userSessionContext;

    @Mock
    private WebHandlerHelper webHandlerHelper;

    @Test
    public void test_buildBusinessModel_should_addMandatoryObjectsToModel() throws Exception {

        MessageListQueryParams queryParams = new MessageListQueryParams();
        HashMap<String, List<MessageListEntry>> dossierMap = new HashMap<>();

        User mockUser = mock(User.class);
        when(userSessionContext.getUser()).thenReturn(mockUser);

        DossierResult mockResult = mock(DossierResult.class);
        when(mockResult.getDossierMap()).thenReturn(dossierMap);

        when(mailboxManager.getDossiers(queryParams, mockUser, Message.MessageState.INCOMING, null)).thenReturn(mockResult);

        // DO THE ACTUAL CALL
        Map<String, Object> result = handler.buildBusinessModel(queryParams);

        assertThat((HashMap<String, List<MessageListEntry>>) result.get(MESSAGE_LIST_ATTR), is(sameInstance(dossierMap)));
        verify(webHandlerHelper).handlePageNavigation(argThat(any(Map.class)), anyInt(), anyLong(), anyInt());
    }
}
