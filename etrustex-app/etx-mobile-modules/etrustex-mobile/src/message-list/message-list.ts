import {Component, Input, Output, EventEmitter, ChangeDetectionStrategy} from '@angular/core';
import {Message} from "./message";


@Component({
  selector: 'message-list',
  templateUrl: 'message-list.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})

/**
 * Displays and handle the list of messages - compact view.
 */
export class MessageList {

  @Input()
  messages: Array<Message>;

  @Input()
  isInbox: boolean;

  @Output()
  onMessageSelected: EventEmitter<string> = new EventEmitter<string>();

  constructor() {
  }

  /**
   * Shows the message details in a new view.
   */
  public onShowMessage(id: string): void {
    this.onMessageSelected.emit(id)
  }

  /**
   * Remove all existing elements from the list.
   */
  public resetList(): void {
    this.messages.length = 0;
  }

  trackById(index, message) {
    return message.id;
  }

}
