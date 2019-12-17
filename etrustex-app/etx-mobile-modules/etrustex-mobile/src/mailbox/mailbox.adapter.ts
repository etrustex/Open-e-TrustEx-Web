import {FolderTypes} from "./folder-types";
import {Party, PartyList} from "../header/party/party-list";
import {InboxCounters} from "../inbox/inbox-counters";
import {SentCounters} from "../sent/sent-counters";
import {FolderCounters} from "./folder-counters";
import {OrderBy} from "./orderby";
import {InboxMessageStatus} from "../inbox/inbox-message-status";
import {SentMessageStatus} from "../sent/sent-message-status";

export class MailboxAdapter {

  private _folder: string = FolderTypes.INBOX;
  private _party: Party;
  private _partyList: PartyList;
  // name of the selected filter (inbox or sent)
  private _filter: number;
  private _order: number;

  private _inboxCounters: InboxCounters = null;
  private _sentCounters: SentCounters = null;
  private _counters: FolderCounters = null;

  public reset() {
    this.counters = null;
    this.inboxCounters = null;
    this.sentCounters = null;
    this.filter = null;
    this.order = null;
  }

  set folder(value: string) {
    this._folder = value;
  }

  set party(value: Party) {
    this._party = value;
  }

  set partyList(value: PartyList) {
    this._partyList = value;
  }

  get folder(): string {
    return this._folder;
  }

  get party(): Party {
    return this._party;
  }

  get partyList(): PartyList {
    return this._partyList;
  }

  get filter(): number {
    return this._filter;
  }

  set filter(value: number) {
    this._filter = value;
  }

  get order(): number {
    return this._order;
  }

  set order(value: number) {
    this._order = value;
  }

  get inboxCounters(): InboxCounters {
    return this._inboxCounters;
  }

  set inboxCounters(value: InboxCounters) {
    this._inboxCounters = value;
  }

  get sentCounters(): SentCounters {
    return this._sentCounters;
  }

  set sentCounters(value: SentCounters) {
    this._sentCounters = value;
  }

  get counters(): FolderCounters {
    return this._counters;
  }

  set counters(value: FolderCounters) {
    this._counters = value;
  }

  public setCounters(folderCounters: FolderCounters) {
    this.counters = folderCounters;
    this.inboxCounters = folderCounters.inboxCounters;
    this.sentCounters = folderCounters.sentCounters;
  }

  public switchFolder(folder: string) {
    this.folder = folder;
    this.filter = folder == FolderTypes.INBOX ? InboxMessageStatus.FILTER_ALL : SentMessageStatus.FILTER_ALL;
    this.order = OrderBy.NONE;
  }

  public switchParty(party: Party) {
    this.party = party;
    this.folder = FolderTypes.INBOX;
    this.filter = InboxMessageStatus.FILTER_ALL;
    this.order = OrderBy.NONE;
  }

  public getFilterName(): string {
    if (this.folder == FolderTypes.INBOX) {
      return InboxMessageStatus[this.filter];
    } else {
      return SentMessageStatus[this.filter];
    }
  }

  public getOrderBy(): string {
    return OrderBy[this.order];
  }

}
