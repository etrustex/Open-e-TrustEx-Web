import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PopoverController } from 'ionic-angular';
import { TranslateService } from "@ngx-translate/core";
import {FilterDialog} from "../../inbox/filter-dialog/filter-dialog";
import {SentMessageStatus} from "../sent-message-status";
import {SentCounters} from "../sent-counters";
import {Configuration} from "../../env/configuration";


@Component({
  selector: 'sent-counters-menu',
  templateUrl: 'sent-counters-menu.html'
})

/**
 * Displays selected filter option alongside with the messages count and handles filter options for an inbox.
 */
export class SentCountersMenu {

  ctx : string = "/e-trustex";

  @Input()
  counters: SentCounters = null;

  @Output()
  onFilterChanged = new EventEmitter<SentMessageStatus>();

  private selectedFilterOption: string;

  constructor(private popoverCtrl: PopoverController,
              public translateService: TranslateService,
              configuration : Configuration) {
    this.ctx = configuration.ctx;
    this.selectedFilterOption = SentMessageStatus[SentMessageStatus.FILTER_ALL];
  }

  public getNameForSelectedOption(): string {
    return this.selectedFilterOption;
  }

  public getCounterForSelectedOption(): string  {
    switch (this.selectedFilterOption) {
      case SentMessageStatus[SentMessageStatus.FILTER_ALL]: return this.getCounterAll();
      case SentMessageStatus[SentMessageStatus.FILTER_READ]: return this.getCounterRead();
      case SentMessageStatus[SentMessageStatus.FILTER_DELIVERED]: return this.getCounterDelivered();
      case SentMessageStatus[SentMessageStatus.FILTER_FAILED]: return this.getCounterFailed();
      case SentMessageStatus[SentMessageStatus.FILTER_NONE]: return this.getCounterNone();
    }
  }

  public getCounterAll(): string {
    return this.counters!=null ? this.counters.all : "-";
  }

  public getCounterRead(): string {
    return this.counters!=null ? this.counters.read : "-";
  }

  public getCounterDelivered(): string {
    return this.counters!=null ? this.counters.delivered : "-";
  }

  public getCounterFailed(): string {
    return this.counters!=null ? this.counters.failed : "-";
  }

  public getCounterNone(): string {
    return this.counters!=null ? this.counters.none : "-";
  }

  public openFilterDialog(ev) {
    let popover = this.popoverCtrl.create(FilterDialog, {
      // pass data params - create value from translated option name and a counter: "option (num)"
      filterChoices:
        [ this.createOptionValue(SentMessageStatus[SentMessageStatus.FILTER_ALL], this.getCounterAll()),
          this.createOptionValue(SentMessageStatus[SentMessageStatus.FILTER_READ], this.getCounterRead()),
          this.createOptionValue(SentMessageStatus[SentMessageStatus.FILTER_DELIVERED], this.getCounterDelivered()),
          this.createOptionValue(SentMessageStatus[SentMessageStatus.FILTER_FAILED], this.getCounterFailed()),
          this.createOptionValue(SentMessageStatus[SentMessageStatus.FILTER_NONE], this.getCounterNone())]
    });
    popover.present({
      ev: ev
    });

    popover.onDidDismiss((selectedIndex) => {
      // change displayed data
      if (selectedIndex != null) {
        this.selectedFilterOption = SentMessageStatus[selectedIndex];
      }
      // notify inbox about the change (even if null - pressed outside)
      this.onFilterChanged.emit(selectedIndex);
    })
  }

  private createOptionValue(option: string, counter: string): string {
    return this.translateService.instant(option)+ " (" + counter + ")";
  }

}
