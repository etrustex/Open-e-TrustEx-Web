package eu.europa.ec.etrustex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Entity InboxCounters expected by the client.
 */

public class RestInboxCounters {
    @JsonProperty("all")
    private String all = null;

    @JsonProperty("read")
    private String read = null;

    public RestInboxCounters all(String all) {
        this.all = all;
        return this;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public RestInboxCounters read(String read) {
        this.read = read;
        return this;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestInboxCounters inboxCounters = (RestInboxCounters) o;
        return Objects.equals(this.all, inboxCounters.all) &&
                Objects.equals(this.read, inboxCounters.read);
    }

    @Override
    public int hashCode() {
        return Objects.hash(all, read);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class InboxCounters {\n");

        sb.append("    all: ").append(toIndentedString(all)).append("\n");
        sb.append("    read: ").append(toIndentedString(read)).append("\n");
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