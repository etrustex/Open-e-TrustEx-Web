import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {AlertController, ToastController} from "ionic-angular";
import {TranslateService} from "@ngx-translate/core";

/**
 * @version: 1.0 (08/09/2017)
 *
 * Base methods for displaying messages inside a toast or a popup dialog.
 *
 */
@Injectable()
export class AlertUtils {

    constructor(private translateService: TranslateService,
                private alertCtrl: AlertController,
                private toastCtrl: ToastController) {
    }

    public handleHttpError(error: any, message?: string) : Observable<any> {
      if (!message) message = error.message;
      console.debug("handleGetPostsError", message);
      this.showToastAlert(message, 'alert-toast-error');
      return Observable.throw(error);
    }

    public showToastAlert(message: string, cssClass: string) {
        let toast = this.toastCtrl.create({
            message: message,
            duration: 6000,
            showCloseButton: true,
            position: 'bottom',
            cssClass: cssClass
        });
        toast.present();
    }

    public showAlert(message: string) {
        let alert = this.alertCtrl.create({
            message: message,
            buttons: [
                {
                    text: this.translateService.instant('GLOBAL_OK'),
                    role: 'cancel',
                    handler: () => {
                    }
                }
            ]
        });
        alert.present();
    }

}
