import { Component } from '@angular/core';
import {Configuration} from "../../env/configuration";

@Component({
  selector: 'navbar-title',
  templateUrl: 'title.html'
})

/**
 * Shows the top navbar with the logo, title and a drawer menu icon. This component does not handle any actions.
 */
export class Title {
  ctx : string = "/e-trustex"
  constructor(configuration : Configuration) {
    this.ctx = configuration.ctx;
  }

}
