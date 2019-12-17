import { Component } from '@angular/core';
import {IonicPage} from 'ionic-angular';
import {TranslateService} from "@ngx-translate/core";

/**
 * Generated class for the SplashPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@IonicPage({
  segment: 'splash'
})
@Component({
  selector: 'page-splash',
  templateUrl: 'splash.html',
})
export class SplashPage {

  constructor(public translateService: TranslateService) {
  }

  ionViewDidLoad() {
  }

}
