package eu.europa.ec.etrustex.webaccess.web.view.business.edma.webhandler;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.Attachment;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
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

        return model;
    }

    @Override
    protected List<Attachment> getAttachments(User user, Long messageId) {
        return mailboxManager.getBinaryAttachments(messageId, user);
    }
}
