import {Loading, LoadingController} from "ionic-angular";

/**
 * @version: 1.0 (08/09/2017)
 *
 * Base methods for displaying loading spinner with custom message and look.
 *
 */
export class Loader {

  private loader: Loading;

  constructor(private loadingCtrl: LoadingController) {}

  public create(loadingMessage: string): Loader {
    this.loader = this.loadingCtrl.create({
      spinner: 'circles',
      content: loadingMessage
    });
    return this;
  }

  public show(): Promise<any> {
    return this.loader.present();
  }

  public destroy() {
    if (this.loader) {
      this.loader.dismiss().then(() => {this.loader=null;} )
      .catch(() => {this.loader=null;} );
    }
  }

}
