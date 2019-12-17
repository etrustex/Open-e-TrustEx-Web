/**
 * This class holds the current messages counters for both inbox and sent folder.
 */
import {InboxCounters} from "../inbox/inbox-counters";
import {SentCounters} from "../sent/sent-counters";

export class FolderCounters {
  static readonly EMPTY_COUNTER_VALUE: string = "-";

  constructor(public inboxCounters:InboxCounters, public sentCounters:SentCounters) {
  }

  get inboxAll(): string {
    if (this.inboxCounters) {
      return this.inboxCounters.all;
    }
    return FolderCounters.EMPTY_COUNTER_VALUE;
  }

  get sentAll(): string {
    if (this.sentCounters) {
      return this.sentCounters.all;
    }
    return FolderCounters.EMPTY_COUNTER_VALUE;
  }

  public clear() {
    this.inboxCounters = null;
    this.sentCounters = null;
  }

}
