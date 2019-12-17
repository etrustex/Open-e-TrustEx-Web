package eu.europa.ec.digit.etrustex.mobile.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import eu.europa.ec.digit.etrustex.mobile.model.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * Messages
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-04T11:03:17.949+02:00")

public class Messages   {
  @JsonProperty("messageList")
  private List<Message> messageList = new ArrayList<Message>();

  @JsonProperty("messageCount")
  private String messageCount = null;

  @JsonProperty("hasMoreMessages")
  private Boolean hasMoreMessages = null;

  public Messages messageList(List<Message> messageList) {
    this.messageList = messageList;
    return this;
  }

  public Messages addMessageListItem(Message messageListItem) {
    this.messageList.add(messageListItem);
    return this;
  }

   /**
   * Get messageList
   * @return messageList
  **/
  @ApiModelProperty(value = "")
  public List<Message> getMessageList() {
    return messageList;
  }

  public void setMessageList(List<Message> messageList) {
    this.messageList = messageList;
  }

  public Messages messageCount(String messageCount) {
    this.messageCount = messageCount;
    return this;
  }

   /**
   * Get messageCount
   * @return messageCount
  **/
  @ApiModelProperty(value = "")
  public String getMessageCount() {
    return messageCount;
  }

  public void setMessageCount(String messageCount) {
    this.messageCount = messageCount;
  }

  public Messages hasMoreMessages(Boolean hasMoreMessages) {
    this.hasMoreMessages = hasMoreMessages;
    return this;
  }

   /**
   * Get hasMoreMessages
   * @return hasMoreMessages
  **/
  @ApiModelProperty(value = "")
  public Boolean getHasMoreMessages() {
    return hasMoreMessages;
  }

  public void setHasMoreMessages(Boolean hasMoreMessages) {
    this.hasMoreMessages = hasMoreMessages;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Messages messages = (Messages) o;
    return Objects.equals(this.messageList, messages.messageList) &&
        Objects.equals(this.messageCount, messages.messageCount) &&
        Objects.equals(this.hasMoreMessages, messages.hasMoreMessages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageList, messageCount, hasMoreMessages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Messages {\n");
    
    sb.append("    messageList: ").append(toIndentedString(messageList)).append("\n");
    sb.append("    messageCount: ").append(toIndentedString(messageCount)).append("\n");
    sb.append("    hasMoreMessages: ").append(toIndentedString(hasMoreMessages)).append("\n");
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

