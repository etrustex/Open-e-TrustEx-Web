package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListEntry;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.ResultDataList;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractMessageListHandler implements WebHandler {

    @Autowired
    protected UserSessionContext userSessionContext;

    @Autowired
    protected MailboxManager mailboxManager;

    @Autowired
    protected WebHandlerHelper webHandlerHelper;

    @Override
    public Map<String, Object> buildBusinessModel(QueryParams queryParams) {
        if (queryParams instanceof MessageListQueryParams) {
            return buildBusinessModel((MessageListQueryParams) queryParams);
        } else {
            throw new IllegalStateException("Unexpected type of QueryParams: " + queryParams);
        }
    }

    protected Map<String, Object> buildBusinessModel(MessageListQueryParams queryParams) {

        Map<String, Object> model = new HashMap<>();
        User user = userSessionContext.getUser();
        Party party = queryParams.getParty();

        ResultDataList<MessageListEntry> result = mailboxManager.getMessages(queryParams, user, getMessageState(), party);
        long resultCount = result.getTotalRowCount();

        List<MessageListEntry> messages = result.getData();

        model.put(MESSAGE_LIST_ATTR, messages);
        model.put("resultCount", resultCount);
        model.put("showExpiredIcon", false);
        model.put("hasLinkedIcas", hasLinkedIcas(party) ? 1 : 0);

        webHandlerHelper.handlePageNavigation(model, queryParams.getPage(), resultCount, queryParams.getOffset());

        return model;
    }

    protected abstract Message.MessageState getMessageState();

    protected boolean hasLinkedIcas(Party party) {
        return webHandlerHelper.hasLinkedIcas(party);
    }
}
