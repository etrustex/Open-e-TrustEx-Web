import { Component, EventEmitter, Input, Output } from '@angular/core';
import {Configuration} from "../../env/configuration";

@Component({
  selector: 'inbox-ordering',
  templateUrl: 'inbox-ordering.html'
})

/**
 * Handles message order selector. Selector has two states: ascending (true) and descending (false) with initial value is set to false.
 */
export class InboxOrdering {

  ctx:string = "/e-trustex";

  @Input()
  label: string; // label displaying the order type (e.g. time sent, time received, ...).

  @Output()
  onOrderChanged = new EventEmitter<boolean>();

  private ascending: boolean = false;

  constructor(configuration : Configuration) {
    this.ctx = configuration.ctx;
  }

  public doOrderChange() {
    this.ascending = !this.ascending;
    this.onOrderChanged.emit(this.ascending);
  }

}
