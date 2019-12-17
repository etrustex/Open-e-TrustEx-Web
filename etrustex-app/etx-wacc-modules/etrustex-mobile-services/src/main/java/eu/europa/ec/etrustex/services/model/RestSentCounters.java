package eu.europa.ec.etrustex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RestSentCounters {

    @JsonProperty("all")
    private String all = null;

    @JsonProperty("delivered")
    private String delivered = null;

    @JsonProperty("failed")
    private String failed = null;

    @JsonProperty("none")
    private String none = null;

    @JsonProperty("read")
    private String read = null;

    public RestSentCounters() {
    }

    public RestSentCounters all(String all) {
        this.all = all;
        return this;
    }


    public String getAll() {
        return this.all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public RestSentCounters read(String read) {
        this.read = read;
        return this;
    }

    public String getRead() {
        return this.read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public RestSentCounters failed(String failed) {
        this.failed = failed;
        return this;
    }

    public String getFailed() {
        return this.failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public RestSentCounters delivered(String delivered) {
        this.delivered = delivered;
        return this;
    }

    public String getDelivered() {
        return this.delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public RestSentCounters none(String none) {
        this.none = none;
        return this;
    }

    public String getNone() {
        return this.none;
    }

    public void setNone(String none) {
        this.none = none;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            RestSentCounters sentCounters = (RestSentCounters) o;
            return Objects.equals(this.all, sentCounters.all) && Objects.equals(this.read, sentCounters.read) && Objects.equals(this.failed, sentCounters.failed) && Objects.equals(this.delivered, sentCounters.delivered) && Objects.equals(this.none, sentCounters.none);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.all, this.read, this.failed, this.delivered, this.none});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestSentCounters {\n");
        sb.append("    all: ").append(this.toIndentedString(this.all)).append("\n");
        sb.append("    read: ").append(this.toIndentedString(this.read)).append("\n");
        sb.append("    failed: ").append(this.toIndentedString(this.failed)).append("\n");
        sb.append("    delivered: ").append(this.toIndentedString(this.delivered)).append("\n");
        sb.append("    none: ").append(this.toIndentedString(this.none)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}

