package eu.europa.ec.etrustex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Entity RestClientError expected by the client.
 */

public class RestClientError {

    @JsonProperty("location")
    private String location = null;

    @JsonProperty("details")
    private String details = null;

    public RestClientError location(String location) {
        this.location = location;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RestClientError details(String details) {
        this.details = details;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestClientError clientError = (RestClientError) o;
        return Objects.equals(this.location, clientError.location) &&
                Objects.equals(this.details, clientError.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, details);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClientError {\n");

        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    details: ").append(toIndentedString(details)).append("\n");
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

