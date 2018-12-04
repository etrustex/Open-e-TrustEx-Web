package eu.europa.ec.etrustex.webaccess.web.controller;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.web.view.business.api.WebAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/messageViewReceivedWS.do")
public class MessageViewReceivedControllerWS extends AbstractMessageDetailsController {
    @Override
    protected WebAction getAction() {
        return WebAction.MESSAGE_VIEW_RECEIVED_WS;
    }

    @Override
    protected boolean isAuthorized(Message message) {
        return super.isAuthorized(message)
                && message.getMsgState().equals(Message.MessageState.INCOMING);
    }
}
