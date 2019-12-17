package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messageEdit.do")
public class MessageEditController extends AbstractMessageDetailsController {

    @Autowired
    private PartyPropertyEditor partyPropertyEditor;

    @Override
    protected WebAction getAction() {
        return WebAction.MESSAGE_EDIT;
    }

    protected boolean isAuthorized(Message message) {
        return super.isAuthorized(message)
                && securityChecker.canSend(message.getLocalParty())
                && (message.getMsgState().equals(Message.MessageState.DRAFT) || message.getMsgState().equals(Message.MessageState.IN_PREPARATION));
    }

    @InitBinder
    public void initBinderAll(WebDataBinder binder) {
        binder.registerCustomEditor(Party.class, partyPropertyEditor);
    }
}