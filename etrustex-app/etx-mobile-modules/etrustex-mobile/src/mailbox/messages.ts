import {Message} from "../message-list/message";

export class Messages {
  private _messages: Array<Message>;
  private _messageCount: string;
  private _hasMoreMessages: boolean;

  constructor () {
    this.messages = [];
  }

  get messages(): Array<Message> {
    return this._messages;
  }

  set messages(value: Array<Message>) {
    this._messages = value;
  }

  get messageCount(): string {
    return this._messageCount;
  }

  set messageCount(value: string) {
    this._messageCount = value;
  }

  get hasMoreMessages(): boolean {
    return this._hasMoreMessages;
  }

  set hasMoreMessages(value: boolean) {
    this._hasMoreMessages = value;
  }

  /**
   * Validates and adds a message to the list. The message is added only
   * if it passes the validation.
   * @param {Message} message incoming message from the request
   */
  public addMessage(message: Message) {
    if (this.validateMessage(message)) {
      this.messages.push(message);
    } else {
      // print out an error and a message with failed validation
      console.error("Message is empty or has invalid attributes:", message);
    }
  }

  /**
   * Validates the given message if it conforms to predefined rules.
   * Currently this method validates if passing message is not null and
   * if has main attributes: id, sender and receiver.
   * @param {Message} message message to be validates
   * @returns {boolean} true if message passes the validation, false otherwise.
   */
  private validateMessage(message: Message): boolean {
    if (message) {
      // check for existence of attributes
      if (!message.id || !message.sender || !message.recipient) {
        return false;
      } else {
        return true;
      }
    }
    return false;
  }
}
