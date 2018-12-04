package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.webhandler;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.vo.DossierResult;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.AbstractMessageListHandler;

import java.util.HashMap;
import java.util.Map;


public class InboxHandler extends AbstractMessageListHandler {
    @Override
    protected Message.MessageState getMessageState() {
        return Message.MessageState.INCOMING;
    }

    @Override
    public Map<String, Object> buildBusinessModel(MessageListQueryParams queryParams) {

        Map<String, Object> model = new HashMap<>();
        User user = userSessionContext.getUser();
        Party party = queryParams.getParty();

        DossierResult result = mailboxManager.getDossiers(queryParams, user, Message.MessageState.INCOMING, party);
        long resultCount = result.getTotalResultSize();

        webHandlerHelper.handlePageNavigation(model, queryParams.getPage(), resultCount, queryParams.getOffset());

        model.put(MESSAGE_LIST_ATTR, result.getDossierMap());
        model.put("showExpiredIcon", true);
        model.put("hasLinkedIcas", hasLinkedIcas(party) ? 1 : 0);

        return model;
    }
}
