package eu.europa.ec.digit.etrustex.mobile.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import eu.europa.ec.digit.etrustex.mobile.model.Attachment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * Attachments
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-04T11:03:17.949+02:00")

public class Attachments   {
  @JsonProperty("attachmentList")
  private List<Attachment> attachmentList = new ArrayList<Attachment>();

  public Attachments attachmentList(List<Attachment> attachmentList) {
    this.attachmentList = attachmentList;
    return this;
  }

  public Attachments addAttachmentListItem(Attachment attachmentListItem) {
    this.attachmentList.add(attachmentListItem);
    return this;
  }

   /**
   * Get attachmentList
   * @return attachmentList
  **/
  @ApiModelProperty(value = "")
  public List<Attachment> getAttachmentList() {
    return attachmentList;
  }

  public void setAttachmentList(List<Attachment> attachmentList) {
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
    Attachments attachments = (Attachments) o;
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

