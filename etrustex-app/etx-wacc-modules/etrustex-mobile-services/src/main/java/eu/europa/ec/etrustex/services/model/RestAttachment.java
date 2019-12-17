package eu.europa.ec.etrustex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Entity RestAttachment expected by the client.
 */

public class RestAttachment {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("messageId")
    private String messageId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("mimeType")
    private String mimeType = null;

    public RestAttachment id(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RestAttachment messageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public RestAttachment name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestAttachment mimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestAttachment attachment = (RestAttachment) o;
        return Objects.equals(this.id, attachment.id) &&
                Objects.equals(this.messageId, attachment.messageId) &&
                Objects.equals(this.name, attachment.name) &&
                Objects.equals(this.mimeType, attachment.mimeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageId, name, mimeType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Attachment {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
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
