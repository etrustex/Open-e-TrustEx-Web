package eu.europa.ec.digit.etrustex.mobile.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * SentCounters
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-04T11:03:17.949+02:00")

public class SentCounters   {
  @JsonProperty("all")
  private String all = null;

  @JsonProperty("read")
  private String read = null;

  @JsonProperty("failed")
  private String failed = null;

  @JsonProperty("delivered")
  private String delivered = null;

  @JsonProperty("none")
  private String none = null;

  public SentCounters all(String all) {
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

  public SentCounters read(String read) {
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

  public SentCounters failed(String failed) {
    this.failed = failed;
    return this;
  }

   /**
   * Get failed
   * @return failed
  **/
  @ApiModelProperty(value = "")
  public String getFailed() {
    return failed;
  }

  public void setFailed(String failed) {
    this.failed = failed;
  }

  public SentCounters delivered(String delivered) {
    this.delivered = delivered;
    return this;
  }

   /**
   * Get delivered
   * @return delivered
  **/
  @ApiModelProperty(value = "")
  public String getDelivered() {
    return delivered;
  }

  public void setDelivered(String delivered) {
    this.delivered = delivered;
  }

  public SentCounters none(String none) {
    this.none = none;
    return this;
  }

   /**
   * Get none
   * @return none
  **/
  @ApiModelProperty(value = "")
  public String getNone() {
    return none;
  }

  public void setNone(String none) {
    this.none = none;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SentCounters sentCounters = (SentCounters) o;
    return Objects.equals(this.all, sentCounters.all) &&
        Objects.equals(this.read, sentCounters.read) &&
        Objects.equals(this.failed, sentCounters.failed) &&
        Objects.equals(this.delivered, sentCounters.delivered) &&
        Objects.equals(this.none, sentCounters.none);
  }

  @Override
  public int hashCode() {
    return Objects.hash(all, read, failed, delivered, none);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SentCounters {\n");
    
    sb.append("    all: ").append(toIndentedString(all)).append("\n");
    sb.append("    read: ").append(toIndentedString(read)).append("\n");
    sb.append("    failed: ").append(toIndentedString(failed)).append("\n");
    sb.append("    delivered: ").append(toIndentedString(delivered)).append("\n");
    sb.append("    none: ").append(toIndentedString(none)).append("\n");
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

