package eu.europa.ec.digit.etrustex.mobile.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import eu.europa.ec.digit.etrustex.mobile.model.Party;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * Parties
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-04T11:03:17.949+02:00")

public class Parties   {
  @JsonProperty("partyList")
  private List<Party> partyList = new ArrayList<Party>();

  public Parties partyList(List<Party> partyList) {
    this.partyList = partyList;
    return this;
  }

  public Parties addPartyListItem(Party partyListItem) {
    this.partyList.add(partyListItem);
    return this;
  }

   /**
   * Get partyList
   * @return partyList
  **/
  @ApiModelProperty(value = "")
  public List<Party> getPartyList() {
    return partyList;
  }

  public void setPartyList(List<Party> partyList) {
    this.partyList = partyList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Parties parties = (Parties) o;
    return Objects.equals(this.partyList, parties.partyList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Parties {\n");
    
    sb.append("    partyList: ").append(toIndentedString(partyList)).append("\n");
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

