import {Component, Input} from '@angular/core';
import { Events, PopoverController } from 'ionic-angular';
import { TranslateService } from "@ngx-translate/core";
import {FilterDialog} from "../../inbox/filter-dialog/filter-dialog";
import {FolderCounters} from "../../mailbox/folder-counters";
import {FolderTypes} from "../../mailbox/folder-types";


@Component({
  selector: 'folders-menu',
  templateUrl: 'folders-menu.html'
})

export class FoldersMenu {

  @Input()
  private folderCounters: FolderCounters;

  private selectedFolder: string = FolderTypes.INBOX;

  private iconName = "mail";

  constructor(public events: Events,
              private popoverCtrl: PopoverController,
              public translateService: TranslateService) {
  }

  public getCurrentCounter(): string {
    if (!this.folderCounters) {
      return "-";
    } else {
      return this.selectedFolder == FolderTypes.INBOX ? this.folderCounters.inboxAll: this.folderCounters.sentAll;
    }
  }

  /**
   * Activated whenever user selects one of a folders and its message list to be displayed on the main page.
   */
  public openFilterDialog(ev) {
    let popover = this.popoverCtrl.create(FilterDialog, {
      // pass data params
      filterChoices: [
        this.createOptionValue(FolderTypes.INBOX, this.folderCounters.inboxAll),
        this.createOptionValue(FolderTypes.SENT, this.folderCounters.sentAll)]
    });
    popover.present({
      ev: ev
    });

    popover.onDidDismiss((selectedIndex) => {
      if (selectedIndex != null) {
        this.selectedFolder = (selectedIndex == 0 ? FolderTypes.INBOX : FolderTypes.SENT);
        this.iconName = (this.selectedFolder == FolderTypes.INBOX ? "mail" : "send");
        this.events.publish('event.folder.selected', this.selectedFolder);
      }
    })
  }

  private createOptionValue(option: string, counter: string): string {
    return this.translateService.instant(option)+ " (" + counter + ")";
  }

}
