package eu.europa.ec.etrustex.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity RestParties expected by the client.
 */

public class RestParties {

    @JsonProperty("partyList")
    private List<RestParty> partyList = new ArrayList<RestParty>();

    public RestParties partyList(List<RestParty> partyList) {
        this.partyList = partyList;
        return this;
    }

    public RestParties addPartyListItem(RestParty partyListItem) {
        this.partyList.add(partyListItem);
        return this;
    }

    public List<RestParty> getPartyList() {
        return partyList;
    }

    public void setPartyList(List<RestParty> partyList) {
        this.partyList = partyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestParties restParties = (RestParties) o;

        return getPartyList() != null ? getPartyList().equals(restParties.getPartyList()) : restParties.getPartyList() == null;
    }

    @Override
    public int hashCode() {
        return getPartyList() != null ? getPartyList().hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestParties {\n");

        sb.append("    partyList: ").append(toIndentedString(partyList)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
