import {Injectable} from '@angular/core';
import {Message} from "../message-list/message";
import {Observable} from 'rxjs/Observable';
import {MessageAPI} from "./messageApi.service";
import {FolderTypes} from "./folder-types";
import {Timeline} from "./timeline";
import {Subject} from "rxjs/Subject";
import {Messages} from "./messages";


/**
 * Acts as a hub and handles all the messages and attachments for different parts of application (messages list, message detail, sorting, ...).
 */
@Injectable()
export class MessagesService {

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


  constructor(private messagesApi: MessageAPI) {
    this.timeline = new Timeline(); // initialize or read from cache
    this.pageSize = "10";
    this.subjectMessagesUpdates = new Subject();
  }

  public resetTimeline() {
    this.timeline = new Timeline();
  }

  /**
   * Adds subscription to any changes on the messages. In case of changes the list of messages with all the updates is returned.
   * @returns {Observable<Array<Message>>}
   */
  public messagesUpdates(): Observable<Array<Message>> {
    console.debug("[service.subscribeToMessagesUpdates]"); //DEBUG
    return this.subjectMessagesUpdates.asObservable();
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

  public hasMoreMessages() : boolean {
    return this.timeline.hasMoreMessages;
  }

  /**
   * Retrieves all new messages created after the previous top one message (identified with since id).
   * Messages are added at the head of a timeline so they can be displayed first.
   * @returns {Observable<Message[]>}
   */
  public getRecentMessages(partyId: string, folderId: string, filterBy: string, sortBy: string): Observable<Message[]> {
    console.debug("[MessageService.getRecentMessages] start ... "); //DEBUG
    // we need to retrieve possible new messages from server since we don't have them in cache
    return this.loadMessages(partyId, folderId, "1", this.pageSize, filterBy, sortBy)
      .map(value => {
        return this.updateTimeline(value.messages);
      }).catch(
        error => {
          console.error("[MessageService.getRecentMessages] error: " + error); //DEBUG
          return Observable.throw(error);
        }
      );
  }

  private updateTimeline(value: Array<Message>): Array<Message> {
    if (this.timeline.size() == 0) {
      this.timeline.addTo(value);
    }
    else {
      this.timeline.addToStart(value);
    }
    return this.timeline.getAll();
  }

  /**
   * Retrieve next page of the old messages (backwards in messages), starting with the latest maxId value.
   * Messages
   * @param {string} partyId - selected party
   * @param {string} folderId - selected folder
   * @param {string} filterBy - filter value
   * @param {string} sortBy - sort order
   * @returns {Observable<Message[]>} - list of new messages starting from the displayedMaxId+1
   */
  public getMoreMessages(partyId: string, folderId: string, filterBy: string, sortBy: string): Observable<Message[]> {
    console.debug("[MessageService.getMoreMessages] start ... maxId = ", this.timeline.maxId); //DEBUG
    // load more messages from server and add incoming list to the cached list
    return this.loadMessages(partyId, folderId, this.timeline.maxId, this.pageSize, filterBy, sortBy)
      .map(value => {
        this.timeline.hasMoreMessages = value.hasMoreMessages;
        // add retrieved messages to the start of the list (oldest one)
        this.timeline.addToEnd(value.messages);
        //nex page of messages was successfully loaded and new list to be displayed will be created
        return this.timeline.getAll();
      })
      .catch(error => {
        console.error("[MessagesService.getMoreMessages] error: " + error); //DEBUG
        return Observable.throw(error);
      });
  }

  public getNextMessage(currentMessageId: string): Observable<Message> {
    let currentIndex = this.timeline.findMessageIndex(currentMessageId);
    if (currentIndex + 1 == this.timeline.size()) {
      // we don't have next message available, so load the next page //TODO!!!
      return Observable.of(this.timeline.get(currentIndex)).timeout(500);
    } else {
      return Observable.of(this.timeline.get(currentIndex + 1)).timeout(500);
    }
  }

  public getPrevMessage(currentMessageId: string): Observable<Message> {
    let currentIndex = this.timeline.findMessageIndex(currentMessageId);
    if (currentIndex == 0) {
      // we are at the first message. check if we can load newer ones ... //TODO!!!
      return Observable.of(this.timeline.get(currentIndex)).timeout(500);
    } else {
      return Observable.of(this.timeline.get(currentIndex - 1)).timeout(500);
    }
  }

  /**
   * Retrieves certain number of new messages from the server. Retrieved messages are part of paginated result and are appended at the end of the messages.
   * @param {string} partyId - selected party
   * @param {string} folderId - selected folder
   * @param {string} from - start messageId
   * @param {string} pageSize  - number of messages to be retrieved
   * @param {string} filterBy  - filter value (ALL, READ, SENT, ...). It's based on the values for inbox or sent box.
   * @param {string} sortBy - sort order
   * @returns {Observable<Message[]>} list of retrieved messages
   */
  private loadMessages(partyId: string, folderId: string, from: string, pageSize: string, filterBy: string, sortBy: string): Observable<Messages> {
    console.debug("[MessageService.loadMessages] start ... ", from, pageSize, sortBy); //DEBUG
    if (!sortBy) sortBy="desc";
    if (folderId == FolderTypes.INBOX) {
      return this.messagesApi.getInboxMessages(partyId, from, pageSize, null, filterBy, sortBy)
        .map(value => {
          console.debug("[MessageService.loadMessages] fetched messages for inbox ... ", value.messageCount, value.hasMoreMessages); //DEBUG
          return value;
        });
    } else {
      return this.messagesApi.getSentMessages(partyId, from, pageSize, null, filterBy, sortBy)
        .map(value => {
          console.debug("[MessageService.loadMessages] fetched messages for sent ... ", value.messageCount, value.hasMoreMessages); //DEBUG
          return value;
        });
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
