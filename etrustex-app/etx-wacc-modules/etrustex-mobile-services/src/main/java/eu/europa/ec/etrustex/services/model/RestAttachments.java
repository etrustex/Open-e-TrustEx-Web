package eu.europa.ec.etrustex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity RestAttachments expected by the client.
 */

public class RestAttachments {

    @JsonProperty("attachmentList")
    private List<RestAttachment> attachmentList = new ArrayList<RestAttachment>();

    public RestAttachments attachmentList(List<RestAttachment> attachmentList) {
        this.attachmentList = attachmentList;
        return this;
    }

    public RestAttachments addAttachmentListItem(RestAttachment attachmentListItem) {
        this.attachmentList.add(attachmentListItem);
        return this;
    }

    public List<RestAttachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<RestAttachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestAttachments attachments = (RestAttachments) o;
        return Objects.equals(this.attachmentList, attachments.attachmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachmentList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Attachments {\n");

        sb.append("    attachmentList: ").append(toIndentedString(attachmentList)).append("\n");
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
