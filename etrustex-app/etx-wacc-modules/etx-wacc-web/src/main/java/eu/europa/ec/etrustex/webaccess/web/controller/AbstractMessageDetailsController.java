package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;
import eu.europa.ec.etrustex.webaccess.web.helper.SecurityChecker;
import eu.europa.ec.etrustex.webaccess.web.model.PageState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public abstract class AbstractMessageDetailsController extends AbstractController {

    @Autowired
    protected SecurityChecker securityChecker;

    @Override
    protected QueryParams buildQueryParams(PageState pageState, Party party, int messageListPageSize) {
        MessageQueryParams queryParams = new MessageQueryParams();
        queryParams.setParty(party);
        queryParams.setMessageId(Long.valueOf(pageState.getMessageId()));
        return queryParams;
    }

    @Override
    protected Party getCurrentParty() {
        Long messageId = Long.valueOf(pageState.getMessageId());
        Message message = mailboxManager.getMessageByMessageId(messageId);

        if (isAuthorized(message)) {
            return message.getLocalParty();
        }else{
            return null;
        }
    }

    protected boolean isAuthorized(Message message) {
        return message != null
            && message.getActiveState()
            && securityChecker.canAccessMessagesOfParty(message.getLocalParty());
    }
}
