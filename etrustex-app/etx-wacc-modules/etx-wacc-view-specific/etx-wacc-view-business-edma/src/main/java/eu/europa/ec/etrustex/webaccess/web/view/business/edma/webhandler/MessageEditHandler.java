package eu.europa.ec.etrustex.webaccess.web.view.business.edma.webhandler;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.*;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler.CommonMessageEditHandler;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EDMAUtils;
import eu.europa.ec.etrustex.webaccess.web.view.business.edma.EdmaMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageEditHandler extends CommonMessageEditHandler {

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    private AttachmentHandler attachmentHandler;

    @Autowired
    private EDMAUtils edmaUtils;

    @Override
    protected Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception {
        Map<String, Object> model = new HashMap<>();
        User user = userSessionContext.getUser();

        Message message = mailboxManager.getMessageDetailsAndFetchSignatureEagerly(queryParams.getMessageId(), user);
        List<Attachment> binaryAttachments = mailboxManager.getBinaryAttachments(queryParams.getMessageId(), user);
        Metadata metadata = attachmentHandler.getMetadata(message.getId());
        model.put(ATTACHMENTS_LIST, binaryAttachments);
        //todo split to common and business specific parts
        Long messageId = queryParams.getMessageId();
        EdmaMessage edmaMessage = edmaUtils.fetchEdmaMessage(message, binaryAttachments, metadata);
        List<Attachment> attachments = mailboxManager.getAttachments(messageId, user);
        model.put(EDMA_MESSAGE_ATTR, edmaMessage);
        model.put(MESSAGE_ATTR, message);

        Party party = queryParams.getParty();
        List<Party> remoteParties = partyManager.getRemoteParties(party);
        model.put(REMOTE_PARTIES_ATTR, remoteParties);

        PartyIca partyIca = icaManager.getActiveIcaBySenderReceiver(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName());
        NodeIcaDetails nodeIcaDetails = icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false);
        model.put(MESSAGE_ICA, adaptMessageIcaDetails(nodeIcaDetails));

        computeRetentionMetadata(model, message);

        model.put("hasMessageRemoteIca", icaHelper.hasIca(message) ? 1 : 0);
        webHandlerHelper.addJsonParameters(model, message.getRemoteParty().getNodeName(), attachments,
                party, edmaMessage.getAttachmentMetadataList(), message.getSignatures());
        return model;
    }


}
