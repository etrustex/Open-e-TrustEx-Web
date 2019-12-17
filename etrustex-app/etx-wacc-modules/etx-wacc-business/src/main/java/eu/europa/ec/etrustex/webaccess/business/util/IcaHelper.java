package eu.europa.ec.etrustex.webaccess.business.util;

import eu.europa.ec.etrustex.webaccess.business.api.IcaManager;
import eu.europa.ec.etrustex.webaccess.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IcaHelper {

    @Autowired
    private IcaManager icaManager;

    /**
     * This method return false for draft and incoming messages when there are no remote ica
     *
     * @param message containing the messageState, the localpary.nodeName and remoteParty.NodeName
     * @return boolean true when msgstate SENT or when msgstate DRAFT or INCOMING and remote ica exist
     *                 false when there are no remote ica for msgstate DRAFT or INCOMING
     */
    public boolean hasIca(Message message) {
        boolean hasIca = true;

        if (message.getMsgState() == Message.MessageState.DRAFT || message.getMsgState() == Message.MessageState.INCOMING) {
            hasIca = icaManager.icaExists(message.getLocalParty().getNodeName(), message.getRemoteParty().getNodeName());
        }

        return hasIca;
    }
}
