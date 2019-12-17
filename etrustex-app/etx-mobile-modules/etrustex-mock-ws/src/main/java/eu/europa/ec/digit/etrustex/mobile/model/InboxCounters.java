package eu.europa.ec.digit.etrustex.mobile.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * InboxCounters
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-04T11:03:17.949+02:00")

public class InboxCounters   {
  @JsonProperty("all")
  private String all = null;

  @JsonProperty("read")
  private String read = null;

  public InboxCounters all(String all) {
    this.all = all;
    return this;
  }

   /**
   * Get all
   * @return all
  **/
  @ApiModelProperty(value = "")
  public String getAll() {
    return all;
  }

  public void setAll(String all) {
    this.all = all;
  }

  public InboxCounters read(String read) {
    this.read = read;
    return this;
  }

   /**
   * Get read
   * @return read
  **/
  @ApiModelProperty(value = "")
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
    InboxCounters inboxCounters = (InboxCounters) o;
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

