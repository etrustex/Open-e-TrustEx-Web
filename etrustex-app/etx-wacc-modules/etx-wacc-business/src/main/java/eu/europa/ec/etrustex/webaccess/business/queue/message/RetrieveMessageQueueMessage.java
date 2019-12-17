package eu.europa.ec.etrustex.webaccess.business.queue.message;

import eu.europa.ec.etrustex.webaccess.business.message.MessageType;

import java.io.Serializable;

public class RetrieveMessageQueueMessage implements Serializable {
    private final String localPartyName;
    private final String remotePartyName;
    private final String messageUuid;
    private final MessageType messageType;

    public RetrieveMessageQueueMessage(String localPartyName, String remotePartyName, String messageUuid, MessageType messageType) {
        this.localPartyName = localPartyName;
        this.remotePartyName = remotePartyName;
        this.messageUuid = messageUuid;
        this.messageType = messageType;
    }

    public String getLocalPartyName() {
        return localPartyName;
    }

    public String getRemotePartyName() {
        return remotePartyName;
    }

    public String getMessageUuid() {
        return messageUuid;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public String toString() {
        return "RetrieveMessageQueueMessage{" +
                "localPartyName='" + localPartyName + '\'' +
                ", remotePartyName='" + remotePartyName + '\'' +
                ", messageUuid='" + messageUuid + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
