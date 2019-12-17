package eu.europa.ec.etrustex.webaccess.web.view.business.edma.webhandler;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.CommonMessageViewHandler;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EDMAUtils;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageViewSentHandler extends CommonMessageViewHandler {

    private final Logger logger = Logger.getLogger(MessageViewSentHandler.class);

    @Autowired
    private EDMAUtils edmaUtils;

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    private AttachmentHandler attachmentHandler;


    @Override
    protected Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception {
        Map<String, Object> model = new HashMap<>();

        User user = userSessionContext.getUser();

        Long messageId = queryParams.getMessageId();

        Message message = addMessageToModel(model, messageId, user);

        List<Attachment> binaryAttachments = mailboxManager.getBinaryAttachments(messageId, user);
        Metadata metadata = attachmentHandler.getMetadata(message.getId());

        EdmaMessage edmaMessage = edmaUtils.fetchEdmaMessage(message, binaryAttachments, metadata);
        model.put(EDMA_MESSAGE_ATTR, edmaMessage);

        model.put("attachmentMetadataList", edmaMessage.getAttachmentMetadataList());

        try {
            Party party = queryParams.getParty();
            model.put("hasMessageRemoteIca", icaHelper.hasIca(message) ? 1 : 0);
            webHandlerHelper.addJsonParameters(model, message.getRemoteParty().getNodeName(), binaryAttachments, party, null, message.getSignatures());

            NodeIcaDetails nodeIcaDetails = icaDetailsService.getIcaDetails(party, message.getRemoteParty().getNodeName());
            model.put("fromSentIsEncryptionRequired", nodeIcaDetails.getConfidentialityCode().isEncryptionRequired());

        } catch (Exception e) {
            logger.error("Cannot handle additional info needed for downloading from sender!");
            logger.error(e.toString());
        }

        return model;
    }

    @Override
    protected List<Attachment> getAttachments(User user, Long messageId) {
        return mailboxManager.getBinaryAttachments(messageId, user);
    }
}
