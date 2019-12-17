import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PopoverController } from 'ionic-angular';
import { TranslateService } from "@ngx-translate/core";
import {InboxCounters} from "../inbox-counters";
import {InboxMessageStatus} from "../inbox-message-status";
import {FilterDialog} from "../filter-dialog/filter-dialog";
import {Configuration} from "../../env/configuration";


@Component({
  selector: 'inbox-counters-menu',
  templateUrl: 'inbox-counters-menu.html'
})

/**
 * Displays selected filter option alongside with the messages count and handles filter options for an inbox.
 */
export class InboxCountersMenu {

  ctx: string = "/e-trustex";

  @Input()
  counters: InboxCounters = null;

  @Output()
  onFilterChanged = new EventEmitter<InboxMessageStatus>();

  private selectedFilterOption: string;

  constructor(private popoverCtrl: PopoverController,
              public translateService: TranslateService, configuration : Configuration) {
    this.ctx = configuration.ctx;
    this.selectedFilterOption = InboxMessageStatus[InboxMessageStatus.FILTER_ALL];
  }

  public getNameForSelectedOption(): string {
    return this.selectedFilterOption;
  }

  public getCounterForSelectedOption(): string  {
    if (this.selectedFilterOption == InboxMessageStatus[InboxMessageStatus.FILTER_ALL]) {
      return this.getCounterAll();
    } else {
      return this.getCounterUnread();
    }
  }

  public getCounterAll(): string {
    return this.counters!=null ? this.counters.all : "-";
  }

  public getCounterUnread(): string {
    let read: number = parseInt(this.counters.read);
    let all: number = parseInt(this.counters.all);
    return this.counters!=null ? String(all-read) : "-";
  }

  public openFilterDialog(ev) {
    let popover = this.popoverCtrl.create(FilterDialog, {
      // pass data params - create value from translated option name and a counter: "option (num)"
      filterChoices:
        [ this.createOptionValue(InboxMessageStatus[InboxMessageStatus.FILTER_ALL], this.getCounterAll()),
          this.createOptionValue(InboxMessageStatus[InboxMessageStatus.FILTER_UNREAD], this.getCounterUnread()) ]
    });
    popover.present({
      ev: ev
    });

    popover.onDidDismiss((selectedIndex) => {
      if (selectedIndex != null) {
        // change displayed data
        this.selectedFilterOption = selectedIndex == 0 ? InboxMessageStatus[InboxMessageStatus.FILTER_ALL] : InboxMessageStatus[InboxMessageStatus.FILTER_UNREAD];
        // notify inbox about the change
        this.onFilterChanged.emit(selectedIndex);
        //this.onFilterChanged.emit(selectedId == 0 ? InboxMessageStatus.FILTER_ALL : InboxMessageStatus.FILTER_UNREAD);
      }
    })
  }

  private createOptionValue(option: string, counter: string): string {
    return this.translateService.instant(option)+ " (" + counter + ")";
  }

}
