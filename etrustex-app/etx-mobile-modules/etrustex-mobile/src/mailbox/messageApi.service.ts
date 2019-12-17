import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Message} from "../message-list/message";
import {Configuration} from "../env/configuration";
import {EtrustExHttp} from "../common/etrustex-http";
import {Attachments} from "../message-details/attachment-list/attachments";
import {Messages} from "./messages";

/**
 * Message API implements all calls connecting to the web service.
 * It is used by MessagesService and SearchService on request.
 */
@Injectable()
export class MessageAPI {

  constructor(private http: EtrustExHttp, private config: Configuration) {
  }


  public getInboxMessages(partyId: string, skip: string, size: string, searchQuery?: string, filterBy?: string, sortBy?: string): Observable<Messages> {
    let skipParam = skip ? skip : 1;
    let sizeParam = size ? size : 10;
    let url: string = this.config.partyInboxUrl.replace("{partyId}", partyId) + "?startfrom=" + skipParam + "&resultsize=" + sizeParam;
    if (searchQuery) url += "&subject=" + searchQuery;
    if (filterBy) url += "&status=" + this.convertFilterByParam(filterBy);
    if (sortBy) url += "&sortorder=" + sortBy.toLowerCase();
    console.debug("[MessageAPI.getInboxMessages] created url " + url); //DEBUG
    return this.http.get(url)
      .map(response => response.json())
      .map(value => {
        let inbox: Messages = new Messages();
        console.debug("[MessageAPI.getInboxMessages] ", value.messageCount);
        inbox.messageCount = value.messageCount;
        inbox.hasMoreMessages = value.hasMoreMessages;
        value.messageList.forEach(e => {inbox.addMessage(this.mapMessage(e))});
        console.debug("[MessageAPI.getInboxMessages] success ... msgCount = %s, more messages = %s", inbox.messageCount, inbox.hasMoreMessages); //DEBUG
        return inbox;
      });
  }

  public getSentMessages(partyId: string, skip: string, size: string, searchQuery?: string, filterBy?: string, sortBy?: string): Observable<Messages> {
    let skipParam = skip ? skip : 0;
    let sizeParam = size ? size : 10;
    let url: string = this.config.partySentUrl.replace("{partyId}", partyId) + "?startfrom=" + skipParam + "&resultsize=" + sizeParam;
    if (searchQuery) url += "&subject=" + searchQuery;
    if (filterBy) url += "&status=" + this.convertFilterByParam(filterBy);
    if (sortBy) url += "&sortorder=" + sortBy.toLowerCase();
    console.debug("[MessageAPI.getSentMessages] created url " + url); //DEBUG
    return this.http.get(url)
      .map(response => response.json())
      .map(value => {
        let sent: Messages = new Messages();
        sent.messageCount = value.messageCount;
        sent.hasMoreMessages = value.hasMoreMessages;
        value.messageList.forEach(e => {sent.addMessage(this.mapMessage(e))});
        console.debug("[MessageAPI.getInboxMessages] success ... msgCount = %s", sent.messageCount); //DEBUG
        return sent;
      });
  }

  /**
   * Returns valid request param converted from the filter values.
   * @param {string} val filter value passed by ui
   * @returns {string} request uri parameter
   */
  private convertFilterByParam(val: string): string {
    switch(val) {
      case "FILTER_ALL" : return "";
      case "FILTER_UNREAD" : return "unread";
      // sent folder
      case "FILTER_READ" : return "read";
      case "FILTER_DELIVERED" : return "delivered";
      case "FILTER_FAILED" : return "failed";
      case "FILTER_NONE" : return "none";
      default: return "";
    }
  }

  public markMessageRead(messageId: string): Observable<boolean> {
    let url: string = this.config.messageReadUrl.replace("{messageId}", messageId);
    return this.http.post(url, "").map(response => {
      return true;
    });
  }


  //date format: "02-06-2017 11:43"

  private mapMessage(value): Message {
    let message: Message = new Message();
    message.id = value.id;
    message.sender = value.sender;
    message.recipient = value.recipient;

    message.receiptDate = value.receiptDate ? new Date(value.receiptDate) : null;
    message.sentDate = value.sentDate ? new Date(value.sentDate) : null;
    message.subject = value.subject;
    message.content = value.content;
    message.attachmentCount = value.attachmentCount;
    message.totalAttachmentSize = value.totalAttachmentSize;
    message.contentEncrypted = JSON.parse(value.contentEncrypted);
    message.signed = JSON.parse(value.signed);
    //message.expirationDate = new Date(value.expirationDate);
    message.status = value.status;
    message.statusReceiptDate = value.statusReceiptDate ? new Date(value.statusReceiptDate) : null;
    message.read = (value.status == 'READ');
    message.expired = JSON.parse(value.expired);
    message.expirationWarning = value.expirationWarning;
    message.attachments = new Attachments();
    /*console.debug("--- message[%s].read ", message.id, message.read);
    console.debug("--- message[%s].expired ", message.id, message.expired);
    console.debug("--- message[%s].expired date ", message.id, message.expirationDate);*/
    /*console.debug("--- message[%s].receiptDate %s from %s", message.id, message.receiptDate, value.receiptDate); //DEBUG
    console.debug("--- message[%s].sentDate  %s from %s", message.id, message.sentDate, value.sentDate); //DEBUG
    console.debug("--- message[%s].statusReceiptDate %s from %s ", message.id, message.statusReceiptDate, value.statusReceiptDate); //DEBUG*/
    return message;
  }

}
