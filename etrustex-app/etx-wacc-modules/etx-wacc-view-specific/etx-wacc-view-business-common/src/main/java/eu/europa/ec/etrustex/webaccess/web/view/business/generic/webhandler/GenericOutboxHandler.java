package eu.europa.ec.etrustex.webaccess.web.view.business.generic.webhandler;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.AbstractMessageListHandler;


public class GenericOutboxHandler extends AbstractMessageListHandler {

    @Override
    protected Message.MessageState getMessageState() {
        return Message.MessageState.SENT;
    }
}
