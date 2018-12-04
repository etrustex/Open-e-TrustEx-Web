package eu.europa.ec.etrustex.webaccess.business.queue.status;

import java.io.Serializable;

public class SendStatusQueueMessage implements Serializable {
    private final Long messageId;

    public SendStatusQueueMessage(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "SendStatusQueueMessage{" +
                "messageId=" + messageId +
                '}';
    }
}
