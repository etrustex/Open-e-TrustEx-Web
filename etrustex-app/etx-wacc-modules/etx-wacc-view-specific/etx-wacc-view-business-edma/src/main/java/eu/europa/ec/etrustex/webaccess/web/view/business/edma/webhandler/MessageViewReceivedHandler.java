package eu.europa.ec.etrustex.webaccess.web.view.business.edma.webhandler;

import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.CommonMessageViewHandler;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EDMAUtils;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageViewReceivedHandler extends CommonMessageViewHandler {

    private final Logger logger = Logger.getLogger(MessageViewReceivedHandler.class);

    @Autowired
    private EDMAUtils edmaUtils;


    @Override
    protected Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception {
        Map<String, Object> model = new HashMap<>();

        User user = userSessionContext.getUser();
        Party party = queryParams.getParty();
        Long messageId = queryParams.getMessageId();

        Message message = addMessageToModel(model, messageId, user);
        List<Attachment> attachments = addAttachmentsToModel(model, messageId, user);

        String payloadXml = webHandlerHelper.fetchPayloadIfReady(model, messageId);

        EdmaMessage edmaMessage = new EdmaMessage();
        if (payloadXml != null) {
            try {
                edmaMessage = edmaUtils.convertXMLToEdma(payloadXml);
            } catch (Exception e) {
                logger.error("The following error occurred while traversing the payload for the message with id: " + messageId, e);
                model.put("errorOccurred", "message.metadataFailure");
            }
        }
        model.put(EDMA_MESSAGE_ATTR, edmaMessage);

        webHandlerHelper.addJsonParameters(model, message.getRemoteParty().getNodeName(), attachments,
                party, edmaMessage.getAttachmentMetadataList(), message.getSignatures());

        model.put("hasMessageRemoteIca", icaHelper.hasIca(message) ? 1 : 0);

        return model;
    }

    //todo get rid of getBinaryAttachments. inline method
    @Override
    protected List<Attachment> getAttachments(User user, Long messageId) {
        return mailboxManager.getBinaryAttachments(messageId, user);
    }
}
