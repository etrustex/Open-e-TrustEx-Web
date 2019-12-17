package eu.europa.ec.etrustex.services.converter;

import eu.europa.ec.etrustex.services.business.config.Configuration;
import eu.europa.ec.etrustex.services.model.RestMessage;
import eu.europa.ec.etrustex.services.model.RestMessages;
import eu.europa.ec.etrustex.services.utils.ResourceBundleUtils;
import eu.europa.ec.etrustex.webaccess.model.*;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Rest Message converter.
 */

public class RestMessageConverter {

    private static final RestMessageConverter INSTANCE = new RestMessageConverter();
    private static final SimpleDateFormat simpleDateFormatterJs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final static SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    /**
     * Gets singleton instance.
     *
     * @return Singleton instance.
     */

    public static RestMessageConverter getInstance() {
        return INSTANCE;
    }

    private RestMessageConverter() {
    }

    /**
     * Converts list of Messages entities to a REST Messages entity.
     *
     * @param messages List of Message entities.
     * @param party    Party
     * @param configuration Configuration
     * @param isIncoming  true = incoming   false = sent
     * @return REST Messages entity.
     */

    public RestMessages convertToRestMessages(List<Message> messages, Party party, Configuration configuration, boolean isIncoming) {
        RestMessages restMessages = new RestMessages();

        int restMessageCounter = 0;

        if (messages != null) {

            int retentionPolicyWeeks = configuration.getRetentionPolicyWeeks();

            for (Message message : messages) {
                RestMessage restMessage = new RestMessage();
                restMessage.setId(message.getId() != null ? String.valueOf(message.getId()) : null);
                restMessage.setSender(isIncoming ? message.getRemoteParty().getNodeName() : message.getLocalParty().getNodeName());
                restMessage.setRecipient(isIncoming ? message.getLocalParty().getNodeName() : message.getRemoteParty().getNodeName());
                restMessage.setReceiptDate(simpleDateFormatterJs.format(message.getCreatedOn()));
                restMessage.setSubject(message.getSubject() != null ? message.getSubject() : "");
                restMessage.setContent(message.getContent() != null ? message.getContent() : "");
                restMessage.setAttachmentCount((message.getAttachments() != null) ? String.valueOf(message.getAttachments().size()) : "0");

                Long totAttachementSize = 0L;
                for (Attachment attachment : message.getAttachments()) {
                    totAttachementSize = totAttachementSize + attachment.getFileSize();
                }
                restMessage.setTotalAttachmentSize(String.valueOf(totAttachementSize));

                Calendar expiryDate = Calendar.getInstance();
                expiryDate.setTime(message.getCreatedOn());
                expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

                restMessage.setStatus((message.getLastStatus() != null) ? ((message.getLastStatus().getMstStatus()) != null ? message.getLastStatus().getMstStatus().toString() : "") : "");

                Calendar currentDate = Calendar.getInstance();

                boolean expiredMessage = currentDate.compareTo(expiryDate) > 0;
                restMessage.setExpired(String.valueOf(expiredMessage));

                restMessage.setExpirationWarning(expiredMessage ? ResourceBundleUtils.message("label.retention.expired") : ResourceBundleUtils.message("label.retention.expires.on").replace("{0}", String.valueOf(simpleDateFormatter.format(expiryDate.getTime()))));

                List<PartyIca> partyIcas = party.getPartyICAs();
                boolean encryptionRequired = false;
                for (PartyIca partyIca : partyIcas) {
                    if (partyIca.getRemoteParty().getNodeName().equals(message.getRemoteParty().getNodeName())) {
                        encryptionRequired = ConfidentialityCode.forCode(partyIca.getConfidentialityCode()).isEncryptionRequired();
                        break;
                    }
                }

                restMessage.setSigned(CollectionUtils.isEmpty(message.getSignatures()) ? "false" : "true");

                restMessage.setSentDate(message.getSentOn() != null ? simpleDateFormatterJs.format(message.getSentOn()) : "");
                restMessage.setStatusReceiptDate((message.getLastStatus() != null) ? (message.getLastStatus().getCreatedOn() != null ? simpleDateFormatterJs.format(message.getLastStatus().getCreatedOn()) : "") : "");
                restMessage.setContentEncrypted(String.valueOf(encryptionRequired));
                restMessages.addMessageListItem(restMessage);
                restMessageCounter++;

            }
        }

        restMessages.setMessageCount(String.valueOf(restMessageCounter));
        restMessages.setHasMoreMessages((restMessageCounter > 0) ? true : false);

        return restMessages;
    }

}
