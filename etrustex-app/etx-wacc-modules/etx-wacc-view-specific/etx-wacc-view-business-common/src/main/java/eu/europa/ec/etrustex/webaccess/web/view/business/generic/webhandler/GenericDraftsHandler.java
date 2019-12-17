package eu.europa.ec.etrustex.webaccess.web.view.business.generic.webhandler;

import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.vo.QueryParams;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.AbstractMessageListHandler;

import java.util.Map;


public class GenericDraftsHandler extends AbstractMessageListHandler {

    @Override
    protected Message.MessageState getMessageState() {
        return Message.MessageState.DRAFT;
    }

    @Override
    public Map<String, Object> buildBusinessModel(QueryParams queryParams) {

        Map<String, Object> resultModel = super.buildBusinessModel(queryParams);

        resultModel.put("showExpiredIcon", true);

        return resultModel;
    }
}
