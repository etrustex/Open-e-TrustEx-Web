import {Message} from "../message-list/message";

export class Timeline {
  /**
   * Id of the top last message (most recent one) that was being already processed.
   * When pull to refresh is executed then all new messages come above this id and it is updated to the last id.
   * @type {string}
   * @info <i>since id is currently not used because messages are retrieved by a sequential order.<i>
   */
  public sinceId: string;
  /**
   * Id of the last message that was received after paginated calls. At the end of the retrieval this value will be the id of the oldest message.
   * @type {string}
   */
  public maxId: string;
  /**
   * Cache of retrieved messages ordered from oldest (index 0) to the newest (top index).
   */
  public messages: Array<Message>;

  public hasMoreMessages: boolean;


  constructor() {
    this.clear();
  }

  public clear() {
    this.messages = []; // initialize or read from cache
    this.sinceId = "1";
    this.maxId = "1";
    this.hasMoreMessages = true;
  }

  public size(): number {
    return this.messages.length;
  }

  public isEmpty(): boolean {
    return this.messages.length == 0;
  }

  public get(index: number): Message {
    let message = this.messages[index];
    return message? message : null;
  }

  public getAll(): Array<Message> {
    return this.messages;
  }

  /**
   * Adds a new message to the end of the messages.
   * @param {Message} message
   */
  public addMessage(message: Message) {
    if (message) {
      this.messages.push(message);
    }
  }

  /**
   * Add new messages to an empty messages. MaxId and SinceId are adjusted accordingly.
   * @param {Array<Message>} messages
   */
  public addTo(messages: Array<Message>) {
    if (messages) {
      this.messages = messages;
      // init boundaries
      if (this.messages.length > 0) {
        /*this.sinceId = this.messages[0].id;
        this.maxId = this.messages[this.messages.length-1].id;*/
        this.sinceId = String(1);
        this.maxId = String(this.messages.length + 1);
      } else {
        // case when messages is initially empty and we're adding empty list to it
        this.sinceId = "1";
        this.maxId = "1";
      }
    }
  }

  /**
   * Appends new messages to the end of a messages. Max index is updated to the oldest message.
   * @param {Array<Message>} messages
   */
  public addToEnd(messages: Array<Message>) {
    if (messages && messages.length > 0) {
      //append messages to the existing list
      this.messages = this.messages.concat(messages);
      //maxId has a new value - id of now the oldest message
      //this.maxId = this.messages[this.messages.length-1].id;
      this.maxId = String(this.messages.length + 1);
    }
  }

  /**
   * Appends new messages to the start of a messages as recent messages. Since index is updated to the newest message.
   * @param {Array<Message>} messages
   */
  public addToStart(messages: Array<Message>) {
    if (messages) {
      this.messages = messages.concat(this.messages);
      //the most recent message id will become new sinceId
      //this.sinceId = this.messages[0].id;
      this.sinceId = String(1);
      //this.maxId = this.messages[this.messages.length-1].id;
    }
  }

  public getTimelineSlice(fromIndex: number, toIndex: number): Array<Message> {
    return this.messages.slice(fromIndex, toIndex);
  }

  /**
   * Searches for the message in the list of existing messages.
   * @param {string} messageId
   * @returns {Message} message or null if message was not found
   */
  public findMessage(messageId: string): Message {
    console.debug("[Timeline.findMessage] messageId=%s in a list of %d items", messageId, this.messages.length); //DEBUG
    let result: Message = this.messages.find(e => e.id == messageId);
    console.debug("[Timeline.findMessage] message found: ", result); //DEBUG
    return result == undefined?null:result;
  }

  /**
   * Finds the element with the given messageId and returns its position.
   * @param {string} messageId
   * @returns {number} message index or -1 if message was not found
   */
  public findMessageIndex(messageId: string): number {
    console.debug("[Timeline.findMessageIndex] messageId", messageId); //DEBUG
    for (let i=0; i<this.messages.length; i++) {
      if (this.messages[i].id == messageId) return i;
    }
    // message was not found, returning out of bounds index - caller
    // should check for a returned value and do a appropriate call.
    return -1;
  }

  public markMessageRead(messageId: string): boolean {
    let index = this.findMessageIndex(messageId);
    if (index > -1) {
      let newMessage = new Message();
      Object.assign(newMessage, this.messages[index]);
      newMessage.read = true;

      let oldMessages = this.messages;
      this.messages = [...oldMessages.slice(0, index), newMessage, ...oldMessages.slice(index + 1)];

      return true;
    }
    return false;
  }
}
