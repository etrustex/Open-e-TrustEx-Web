import {Component} from '@angular/core';
import {Events} from 'ionic-angular';
import {TranslateService} from "@ngx-translate/core";
import {FolderCounters} from "../../mailbox/folder-counters";
import {FolderTypes} from "../../mailbox/folder-types";
import {CountersStore} from "../../mailbox/providers/counters-store";


@Component({
  selector: 'folders-menu-tablet',
  templateUrl: 'folders-menu-tablet.html'
})

export class FoldersMenuTablet {

  private folderCounters: FolderCounters;

  constructor(public events: Events,
              public translateService: TranslateService,
              public countersStore: CountersStore) {

    countersStore.subscribeToFoldersCounters().subscribe((counters:FolderCounters) => {
      this.folderCounters = counters;
      }
    );
  }

  public get inboxCounter() {
    return this.folderCounters ? this.folderCounters.inboxAll: "-"
  }

  public get sentCounter() {
    return this.folderCounters ? this.folderCounters.sentAll: "-"
  }

  /**
   * Activated whenever user selects one of a folders and its message list to be displayed on the main page.
   */
  public selectInboxFolder() {
    this.events.publish('event.folder.selected', FolderTypes.INBOX);
  }

  public selectSentFolder() {
    this.events.publish('event.folder.selected', FolderTypes.SENT);
  }


}
