package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.RetentionMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonMessageViewHandler extends AbstractMessageHandler {

    @Autowired
    protected MailboxManager mailboxManager;

    @Autowired
    private ConfigurationService configurationService;

    @Override
    protected Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception {
        Map<String, Object> model = new HashMap<>();

        User user = userSessionContext.getUser();
        Party party = queryParams.getParty();
        Long messageId = queryParams.getMessageId();

        Message message = addMessageToModel(model, messageId, user);
        List<Attachment> attachments = addAttachmentsToModel(model, messageId, user);

        webHandlerHelper.addJsonParameters(model, message.getRemoteParty().getNodeName(), attachments, party, null, message.getSignatures());

        Configuration configuration = configurationService.getConfiguration();
        int retentionPolicyWeeks = configuration.getRetentionPolicyWeeks();
        model.put("retentionPolicyWeeks", retentionPolicyWeeks);

        model.put("hasMessageRemoteIca", icaHelper.hasIca(message) ? 1 : 0);

        return model;
    }

    public List<Attachment> addAttachmentsToModel(Map<String, Object> model, Long messageId, User user) {
        List<Attachment> attachments = getAttachments(user, messageId);
        model.put("attachments", attachments);
        model.put("attachmentCount", attachments != null ? attachments.size() : 0);
        return attachments;
    }

    //todo[VM]: drop this method completely and always fetch the full list of attachments. Then, at specific places (egreffe/edma.. applet, message list) ignore the attachment with type payload
    protected List<Attachment> getAttachments(User user, Long messageId) {
        return mailboxManager.getAttachments(messageId, user);
    }

    public Message addMessageToModel(Map<String, Object> model, Long messageId, User user) throws IOException {
        Message message = mailboxManager.getMessageDetailsAndFetchSignatureEagerly(messageId, user);
        if (message == null) {
            throw new IllegalStateException("Couldn't find a message in the DB for the given id: " + messageId);
        }
        model.put(MESSAGE_ATTR, message);

        if (message.getMsgState() == Message.MessageState.SENT) {
            MessageStatus messageStatus = message.getLastStatus();
            if (messageStatus != null && messageStatus.getMstState() == MessageStatus.State.INCOMING) {
                MessageListQueryParams.MessageStatus status = MessageListQueryParams.MessageStatus.parse(messageStatus.getMstStatus());
                model.put("statusCode", status != null ? status.getMsgCode() : null);
                model.put("statusDate", messageStatus.getCreatedOn());
            }
        }

        Configuration configuration = configurationService.getConfiguration();

        Date createdOn = message.getCreatedOn();

        RetentionMetadata retentionMetadata = configurationService.computeRetentionMetadata(configuration, createdOn);

        model.put("retentionMetadata", retentionMetadata);

        return message;
    }
}
