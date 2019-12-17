package eu.europa.ec.etrustex.webaccess.business.queue.retrievepayload;

import java.io.Serializable;

public class RetrievePayloadQueueMessage implements Serializable {
    private final String loggedInUser;
    private final Long messageId;

    public RetrievePayloadQueueMessage(String loggedInUser, Long messageId) {
        this.loggedInUser = loggedInUser;
        this.messageId = messageId;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public Long getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "RetrievePayloadQueueMessage{" +
                "loggedInUser='" + loggedInUser + '\'' +
                ", messageId=" + messageId +
                '}';
    }
}
