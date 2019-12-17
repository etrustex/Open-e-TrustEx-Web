package eu.europa.ec.etrustex.webaccess.business.facade;

import eu.europa.ec.etrustex.webaccess.business.api.AttachmentHandler;
import eu.europa.ec.etrustex.webaccess.business.api.MailboxManager;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Metadata;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.User;
import eu.europa.ec.etrustex.webaccess.model.vo.SignatureVO;
import eu.europa.ec.etrustex.webaccess.security.MessageSignatureUtil;
import eu.europa.ec.etrustex.webaccess.utils.EtxEntityException;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import eu.europa.ec.etrustex.webaccess.webservice.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BusinessFacadeImpl implements BusinessFacade {

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    private AttachmentHandler attachmentHandler;

    @Autowired
    private DocumentService documentService;


    @Override
    public Message saveOrUpdateMessage(Message message, List<Long> uploadedFiles, Long idSelectedPayloadFile, String payloadXML, SignatureVO signature, User currentUser) throws EtxException {
        if (signature != null) {
            MessageSignatureUtil.populateMessageSignature(message, signature.getSignature(), signature.getSignedData(), signature.getSignatureId());
        }

        Message originalMessage = message.getId() != null ? mailboxManager.getMessageByMessageId(message.getId()) : null;
        Message savedMessage = mailboxManager.saveOrUpdateMessage(message, uploadedFiles, idSelectedPayloadFile, currentUser);
        saveMetadata(savedMessage, payloadXML);

        if (savedMessage.getMsgState() != Message.MessageState.DRAFT) {
            try {
                sendMessageBundle(savedMessage, signature);
            } catch (Exception e) {
                //Reverts state for draft messages
                if (originalMessage != null && originalMessage.getMsgState().equals(Message.MessageState.DRAFT)) {
                    savedMessage.setMsgState(Message.MessageState.DRAFT);
                    savedMessage = mailboxManager.saveOrUpdateMessage(savedMessage, uploadedFiles, idSelectedPayloadFile, currentUser);
                }

                throw new EtxEntityException(SENDING_BUNDLE_ERROR, savedMessage);
            }
            savedMessage.setMsgState(Message.MessageState.SENT);
            savedMessage = mailboxManager.saveOrUpdateMessage(savedMessage, uploadedFiles, idSelectedPayloadFile, currentUser);
        }

        return savedMessage;
    }

    private void sendMessageBundle(Message message, SignatureVO signature) throws Exception {
        final Party localParty = message.getLocalParty();
        documentService.sendMessageBundle(localParty.getNodeUserName(), localParty.getNodeUserPass(), localParty.getNodeName(),
                message.getRemoteParty().getNodeName(), message.getSubject(), message.getContent(), message.getAttachments(), signature, message.getBundleId());

    }

    private void saveMetadata(Message savedMessage, String payloadXML) {
        if (payloadXML != null) {
            Metadata.MetadataState metadataState;
            switch (savedMessage.getMsgState()) {
                case SENT:
                case IN_PREPARATION:
                    metadataState = Metadata.MetadataState.UPLOADED;
                    break;
                case DRAFT:
                    metadataState = Metadata.MetadataState.CREATED;
                    break;
                default:
                    throw new IllegalStateException("Unexpected message state: " + savedMessage.getMsgState());
            }
            attachmentHandler.savePayloadMetadata(payloadXML, savedMessage.getId(), metadataState);
        }
    }

    @Override
    public void disableMessage(Message message) throws EtxException {
        mailboxManager.disableMessage(message);
    }
}
