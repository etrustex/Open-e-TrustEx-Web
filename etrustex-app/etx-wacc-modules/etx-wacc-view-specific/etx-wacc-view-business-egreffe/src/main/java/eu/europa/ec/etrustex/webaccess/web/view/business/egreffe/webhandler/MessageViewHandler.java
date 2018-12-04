package eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.webhandler;

import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.CommonMessageViewHandler;
import eu.europa.ec.etrustex.webaccess.web.view.business.egreffe.EGREFFEUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageViewHandler extends CommonMessageViewHandler {

    private final Logger logger = Logger.getLogger(MessageViewHandler.class);

    @Autowired
    private EGREFFEUtils eGreffeUtils;

    @Override
    protected Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception {
        Map<String, Object> model = new HashMap<>();

        User user = userSessionContext.getUser();
        Party party = queryParams.getParty();
        Long messageId = queryParams.getMessageId();

        Message message = addMessageToModel(model, messageId, user);
        List<Attachment> attachments = addAttachmentsToModel(model, messageId, user);

        webHandlerHelper.addJsonParameters(model, message.getRemoteParty().getNodeName(), attachments, party, null, message.getSignatures());

        String payloadFileContent = webHandlerHelper.fetchPayloadIfReady(model, messageId);

        if (payloadFileContent != null) {
            try {
                model.put("eGreffeMetadata", eGreffeUtils.getListOfWorks(payloadFileContent, messageId));
            } catch (Exception e) {
                logger.error("The following error occurred while traversing the payload for the message with id: " + messageId, e);
                model.put("errorOccurred", "message.metadataFailure");
            }
        }

        model.put("hasMessageRemoteIca", icaHelper.hasIca(message) ? 1 : 0);

        return model;
    }

}
