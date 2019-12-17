import {Component, Input} from '@angular/core';


@Component({
  selector: 'info-view',
  templateUrl: 'info-view.html'
})

/**
 * This view displays any type of information (warning, error, ...).
 * It replaces content and consists from a message and an icon (currently a warning icon).
 */
export class InfoView {

  @Input()
  private message: string;

  /**
   * Returns formatted message or an empty one if
   * value is undefined.
   * @returns {string}
   */
  public getMessage() : string {
    if (this.message) {
      return this.message;
    } else {
      return "";
    }
  }

  get hasMessage() : boolean {
    return !!this.message;
  }

}
