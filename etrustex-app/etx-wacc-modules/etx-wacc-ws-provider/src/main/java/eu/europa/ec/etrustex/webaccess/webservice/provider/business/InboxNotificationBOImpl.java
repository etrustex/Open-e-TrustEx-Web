package eu.europa.ec.etrustex.webaccess.webservice.provider.business;

import eu.europa.ec.etrustex.webaccess.business.api.*;
import eu.europa.ec.etrustex.webaccess.model.BusinessConfigProperty;
import eu.europa.ec.etrustex.webaccess.model.Message;
import eu.europa.ec.etrustex.webaccess.model.Notification;
import eu.europa.ec.etrustex.webaccess.model.Party;
import eu.europa.ec.etrustex.webaccess.model.vo.BusinessCustomViewName;
import eu.europa.ec.etrustex.webaccess.utils.EtxException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InboxNotificationBOImpl implements InboxNotificationBO {

    Logger logger = Logger.getLogger(InboxNotificationBOImpl.class);

    @Autowired
    private MailboxManager mailboxManager;

    @Autowired
    private PartyManager partyManager;

    @Autowired
    private RetrievePayloadTriggerService retrievePayloadTriggerService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private IcaManager icaManager;

    @Override
    public Message handleMessage(String loggedInUser, Message message, String localPartyName, String remotePartyName) throws EtxException {

        Message savedMessage = null;
        Party localParty = partyManager.getWebManagedPartyByNodeName(localPartyName);

        if (localParty != null) {
            Party remoteParty = partyManager.getRemotePartyByNodeNameBusId(remotePartyName, localParty.getBusiness().getId());

            //Checks if the remote party exists in the context of the business of the local party
            if (remoteParty == null) {
                remoteParty = new Party();
                remoteParty.setDisplayName(remotePartyName);
                remoteParty.setNodeName(remotePartyName);
                remoteParty.setWebManaged(Boolean.FALSE);
                remoteParty.setBusiness(localParty.getBusiness());
                partyManager.saveOrUpdate(remoteParty);

                logger.warn("Created new remote party for incoming message with bundleID: " + message.getBundleId() + ". Node name: " + remoteParty.getNodeName() + ", Business id: " + remoteParty.getBusiness().getId());
            }

            String customViewName = localParty.getBusiness().getBusinessConfigValues().get(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME);
            BusinessCustomViewName businessCustomViewName = BusinessCustomViewName.forCustomViewName(customViewName);

            message.setMsgState(Message.MessageState.INCOMING);
            message.setLocalParty(localParty);
            message.setRemoteParty(remoteParty);

            Long localPartyId = message.getLocalParty().getId();
            logger.debug("handleMessage is called for message with bundleID: " + message.getBundleId() +
                    "; remotePartyName: " + remotePartyName +
                    "; localPartyId: " + localPartyId);

            boolean messageAlreadyExists = false;
            if (StringUtils.isNotBlank(message.getBundleId())) {
                messageAlreadyExists = mailboxManager.searchForDuplicateMessageBundle(message.getBundleId(), localPartyId, remotePartyName);
            }

            if (!messageAlreadyExists) {
                savedMessage = mailboxManager.saveOrUpdateMessage(message, null);
                if (savedMessage.getId() != null) {
                    logger.debug("message with Id:  " + savedMessage.getId() + " was persisted");
                    if (!businessCustomViewName.name().equals(BusinessCustomViewName.GENERIC.toString())) {
                        retrievePayloadTriggerService.triggerRetrievePayload(loggedInUser, savedMessage.getId());
                    }
                    notifyService.notify(savedMessage, Notification.NotificationType.MESSAGE_RECEIVED_EMAIL);
                }
            } else {
                logger.warn("Message already exists!" +
                        message.getBundleId() +
                        "; remotePartyName = " + remotePartyName +
                        "; localPartyId = " + localPartyId);
            }
        } else {
            logger.warn("Local party '" + localPartyName + "' not found for message with bundleID: " + message.getBundleId());
        }

        return savedMessage;
    }
}
