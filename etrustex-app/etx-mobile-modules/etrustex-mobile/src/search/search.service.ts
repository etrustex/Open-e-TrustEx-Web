import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Message} from "../message-list/message";
import {MessageAPI} from "../mailbox/messageApi.service";
import {Messages} from "../mailbox/messages";
import {Timeline} from "../mailbox/timeline";
import {Subject} from "rxjs/Subject";


/**
 * SearchService retrieves messages based on the search query. It uses the same API calls as the MessagesService.
 */
@Injectable()
export class SearchService {

  /**
   * Size of the batch (number of the messages to be retrieved in each call.
   * @type {string}
   */
  private pageSize: string;
  /**
   * Cache of retrieved messages ordered from oldest (index 0) to the newest (top index).
   */
  private timeline: Timeline;

  private subjectMessagesUpdates: Subject<Array<Message>>;


  constructor(private messagesApi : MessageAPI) {
    this.timeline = new Timeline(); // initialize or read from cache
    this.pageSize = "10";
    this.subjectMessagesUpdates = new Subject();
  }

  /**
   * Adds subscription to any changes on the messages. In case of changes the list of messages with all the updates is returned.
   * @returns {Observable<Array<Message>>}
   */
  public messagesUpdates(): Observable<Array<Message>> {
    console.debug("[service.subscribeToMessagesUpdates]"); //DEBUG
    return this.subjectMessagesUpdates.asObservable();
  }


  public resetTimeline() {
    this.timeline = new Timeline();
  }

  public resetSearch() {
    this.timeline.clear();
  }

  public hasMoreMessages() : boolean {
    return this.timeline.hasMoreMessages;
  }

  /**
   * Finds a message by it's id.
   * @param {string} messageId
   * @returns {Observable<Message>} message or null if no message is found
   */
  public getMessage(messageId: string): Observable<Message> {
    return Observable.of(this.timeline.findMessage(messageId)).timeout(500);
  }

  public getMessages(): Array<Message> {
    return this.timeline.getAll();
  }

  public getInboxMessages(partyId: string, searchQuery: string, filter: string): Observable<Message[]> {
    console.debug("[SearchService.getInboxMessages] ... partyId = %s, query = %s, filter = %s, from = %s, maxId = %s", partyId, searchQuery, filter, this.timeline.maxId); //DEBUG
    return this.messagesApi.getInboxMessages(partyId, this.timeline.maxId, this.pageSize, searchQuery, filter).map(value => {
      return this.updateTimeline(value);
    });
  }

  public getSentMessages(partyId: string, searchQuery: string, filter: string): Observable<Message[]> {
    console.debug("[SearchService.getSentMessages] ... partyId = %s, query = %s, filter = %s, from = %s, maxId = %s", partyId, searchQuery, filter, this.timeline.maxId); //DEBUG
    return this.messagesApi.getSentMessages(partyId, this.timeline.maxId, this.pageSize, searchQuery, filter).map(value => {
      return this.updateTimeline(value);
    });
  }

  private updateTimeline(value: Messages): Array<Message> {
    if (this.timeline.size() == 0) {
      this.timeline.addTo(value.messages);
    } else {
      this.timeline.addToEnd(value.messages);
    }
    this.timeline.hasMoreMessages = value.hasMoreMessages;
    return this.timeline.getAll();
  }

  public getNextMessage(currentMessageId: string) : Observable<Message> {
    let currentIndex = this.timeline.findMessageIndex(currentMessageId);
    if (currentIndex + 1 == this.timeline.size()) {
      return Observable.of(this.timeline.get(currentIndex)).timeout(500);
    } else {
      return Observable.of(this.timeline.get(currentIndex+1)).timeout(500);
    }
  }

  public getPrevMessage(currentMessageId: string) : Observable<Message> {
    let currentIndex = this.timeline.findMessageIndex(currentMessageId);
    if (currentIndex == 0) {
      return Observable.of(this.timeline.get(currentIndex)).timeout(500);
    } else {
      return Observable.of(this.timeline.get(currentIndex+1)).timeout(500);
    }
  }

  /**
   * Marks opened message with a read flag. The change of the read status is reported to the server and selected message is updated locally.
   * @param {string} messageId - id of a displayed message
   * @returns {Observable<boolean>} true if message was marked ok
   */
  public markMessageRead(messageId: string): Observable<boolean> {
    return this.messagesApi.markMessageRead(messageId).map(() => {
      if (this.timeline.markMessageRead(messageId)) {
        this.subjectMessagesUpdates.next(this.timeline.messages);
        return true;
      } else {
        return false;
      }
    });
  }

}
