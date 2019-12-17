import { Component, Input } from '@angular/core';
import { Content, Events } from 'ionic-angular';
import {UserData} from "../../user/user-data";
import {Configuration} from "../../env/configuration";


@Component({
  selector: 'drawer-menu',
  templateUrl: 'drawer-menu.html'
})

/**
 * Side menu presents all the application options and main user information.
 * It dispatched selected option via event publishing.
 */
export class DrawerMenu {

  @Input()
  user: UserData;

  @Input()
  content: Content;

  version: string;

  constructor (public events: Events, configuration: Configuration) {
    this.version = configuration.version;
  }


  public logout() {
    this.user = null;
    // notify any component listening to this event that user is not valid anymore
    this.events.publish('event.menu.logout');
  }

  public isLogged(): boolean {
    return (this.user != null);
  }
}
