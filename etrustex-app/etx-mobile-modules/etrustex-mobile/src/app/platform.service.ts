import {Injectable} from '@angular/core';
import { Platform } from 'ionic-angular';


/**
 * ApplicationPlatform exposes services for different platform specific modes.
 *  * Tablet/phone mode
 *  * Portrait/landscape orientation
 */
@Injectable()
export class ApplicationPlatformService {

  private isTablet: boolean = false;

  constructor(public platform: Platform) {
  }

  init() {
    this.isTablet = (this.platform.is('tablet') /*|| this.platform.is('ipad')*/ || this.platform.is('core')) /*&& this.platform.isLandscape()*/;
    if (!this.isTablet) {
      // optional screen orientation lock in portrait mode for mobile phones
      // https://ionicframework.com/docs/native/screen-orientation/
    }
  }

  /**
   * Checks whether this application is run on a tablet/desktop or on the mobile phone.
   * @returns {boolean} true if run on tablet
   */
  public isTabletMode(): boolean {
    return this.isTablet;
  }

  /**
   * Checks whether this application is displayed in portrait or landscape orientation.
   * @returns {boolean} true if it is portrait orientation
   */
  public isPortraitMode(): boolean {
    return this.platform.isPortrait();
  }

}
