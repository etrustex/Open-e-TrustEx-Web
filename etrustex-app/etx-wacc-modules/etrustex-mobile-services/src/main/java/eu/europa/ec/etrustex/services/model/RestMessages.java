package eu.europa.ec.etrustex.services.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RestMessages
 */


public class RestMessages {
    @JsonProperty("messageList")
    private List<RestMessage> messageList = new ArrayList<RestMessage>();

    @JsonProperty("messageCount")
    private String messageCount = null;

    @JsonProperty("hasMoreMessages")
    private Boolean hasMoreMessages = null;

    public RestMessages messageList(List<RestMessage> messageList) {
        this.messageList = messageList;
        return this;
    }

    public RestMessages addMessageListItem(RestMessage messageListItem) {
        this.messageList.add(messageListItem);
        return this;
    }

    /**
     * Get messageList
     *
     * @return messageList
     **/
    public List<RestMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<RestMessage> messageList) {
        this.messageList = messageList;
    }

    public RestMessages messageCount(String messageCount) {
        this.messageCount = messageCount;
        return this;
    }

    /**
     * Get messageCount
     *
     * @return messageCount
     **/
    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public RestMessages hasMoreMessages(Boolean hasMoreMessages) {
        this.hasMoreMessages = hasMoreMessages;
        return this;
    }

    /**
     * Get hasMoreMessages
     *
     * @return hasMoreMessages
     **/
    public Boolean getHasMoreMessages() {
        return hasMoreMessages;
    }

    public void setHasMoreMessages(Boolean hasMoreMessages) {
        this.hasMoreMessages = hasMoreMessages;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestMessages messages = (RestMessages) o;
        return Objects.equals(this.messageList, messages.messageList) &&
                Objects.equals(this.messageCount, messages.messageCount) &&
                Objects.equals(this.hasMoreMessages, messages.hasMoreMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageList, messageCount, hasMoreMessages);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Messages {\n");

        sb.append("    messageList: ").append(toIndentedString(messageList)).append("\n");
        sb.append("    messageCount: ").append(toIndentedString(messageCount)).append("\n");
        sb.append("    hasMoreMessages: ").append(toIndentedString(hasMoreMessages)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

