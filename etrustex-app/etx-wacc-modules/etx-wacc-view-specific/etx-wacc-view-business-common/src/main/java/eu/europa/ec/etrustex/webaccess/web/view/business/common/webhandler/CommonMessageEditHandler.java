package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.business.config.Configuration;
import eu.europa.ec.etrustex.webaccess.business.config.ConfigurationService;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.credentials.UserSessionContext;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.model.vo.RetentionMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonMessageEditHandler extends AbstractMessageHandler {

    @Autowired
    protected UserSessionContext userSessionContext;

    @Autowired
    protected MailboxManager mailboxManager;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    protected PartyManager partyManager;

    @Override
    protected Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception {
        Map<String, Object> model = new HashMap<>();
        User user = userSessionContext.getUser();

        Long messageId = queryParams.getMessageId();
        Message message = mailboxManager.getMessageDetailsAndFetchSignatureEagerly(messageId, user);
        if (message == null) {
            throw new IllegalStateException("Couldn't find a message in the DB for the given id: " + queryParams.getMessageId());
        }

        List<Attachment> attachments = mailboxManager.getAttachments(messageId, user);
        message.setAttachments(attachments);
        model.put(ATTACHMENTS_LIST, attachments);
        model.put(MESSAGE_ATTR, message);

        Party party = queryParams.getParty();
        List<Party> remoteParties = partyManager.getRemoteParties(party);
        model.put(REMOTE_PARTIES_ATTR, remoteParties);

        PartyIca partyIca = icaManager.getActiveIcaBySenderReceiver(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName());
        NodeIcaDetails nodeIcaDetails = icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false);
        model.put(MESSAGE_ICA, adaptMessageIcaDetails(nodeIcaDetails));

        if (Message.MessageState.DRAFT.equals(message.getMsgState())) {
            computeRetentionMetadata(model, message);
        }

        model.put("hasMessageRemoteIca", icaHelper.hasIca(message) ? 1 : 0);
        webHandlerHelper.addJsonParameters(model, message.getRemoteParty().getNodeName(), attachments, party, null, message.getSignatures());
        return model;
    }

    protected void computeRetentionMetadata(Map<String, Object> model, Message message) {
        Configuration configuration = configurationService.getConfiguration();
        Date createdOn = message.getCreatedOn();
        boolean isRetentionPolicyValid = configurationService.isRetentionPolicyValid(configuration);
        RetentionMetadata retentionMetadata;
        if (isRetentionPolicyValid) {
            retentionMetadata = configurationService.computeRetentionMetadata(configuration, createdOn);
        } else {
            retentionMetadata = RetentionMetadata.INVALID_RETENTION_POLICY_SETTINGS_INSTANCE;
        }
        model.put(RETENTION_METADATA_ATTR, retentionMetadata);
    }
}
