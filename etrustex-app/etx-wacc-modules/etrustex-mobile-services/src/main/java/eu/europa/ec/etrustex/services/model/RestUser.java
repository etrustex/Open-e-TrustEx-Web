package eu.europa.ec.etrustex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RestUser {

    @JsonProperty("userId")
    private String userId = "";

    @JsonProperty("fullName")
    private String fullName = "";

    public RestUser userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Get userId
     *
     * @return userId
     **/
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RestUser fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Get fullName
     *
     * @return fullName
     **/
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestUser user = (RestUser) o;
        return Objects.equals(this.userId, user.userId) &&
                Objects.equals(this.fullName, user.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, fullName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

