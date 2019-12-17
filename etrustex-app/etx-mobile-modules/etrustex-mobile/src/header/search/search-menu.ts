import {Component} from '@angular/core';
import {Events} from 'ionic-angular';

@Component({
  selector: 'search-menu',
  templateUrl: 'search-menu.html'
})

export class SearchMenu {

  public constructor(public events: Events) {}

  /**
   * Emits event whenever user press the search button which opens or closes the search bar beneath.
   */
  public onSearchSelected() {
    // change the icon colour based on the searchbar state
    //this.view.nativeElement.setAttribute("style", "\"color:#004494;fill:#004494\"");

    //console.debug("[onSearchPress] status", this.searchbarActive); //DEBUG
    this.events.publish('event.search.selected');
  }

}
