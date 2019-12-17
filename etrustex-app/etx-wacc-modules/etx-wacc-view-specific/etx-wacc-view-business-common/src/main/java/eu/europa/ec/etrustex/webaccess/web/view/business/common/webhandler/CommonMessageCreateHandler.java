package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.business.api.PartyManager;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.PartyIca;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.NodeIcaDetails;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonMessageCreateHandler extends AbstractMessageHandler {

    @Autowired
    protected MailboxManager mailboxManager;

    @Autowired
    protected PartyManager partyManager;

    @Override
    protected Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception {
        Map<String, Object> model = new HashMap<>();
        Party party = queryParams.getParty();

        Message message = new Message();
        message.setContent("");
        message.setMsgState(Message.MessageState.DRAFT);
        message.setLocalParty(party);
        model.put(MESSAGE_ATTR, message);

        List<Party> remoteParties = partyManager.getRemoteParties(party);
        model.put(REMOTE_PARTIES_ATTR, remoteParties);
        model.put("hasLinkedIcas", hasLinkedIcas(party) ? 1 : 0);

        if (remoteParties.size() == 1) {
            String remotePartyNodeName = remoteParties.get(0).getNodeName();
            PartyIca partyIca = icaManager.getActiveIcaBySenderReceiver(message.getLocalParty().getNodeName(), remotePartyNodeName);
            NodeIcaDetails nodeIcaDetails = icaDetailsService.getNodeIcaDetailsBySenderReceiver(partyIca, false);
            model.put(MESSAGE_ICA, adaptMessageIcaDetails(nodeIcaDetails));
        }

        return model;
    }
}
