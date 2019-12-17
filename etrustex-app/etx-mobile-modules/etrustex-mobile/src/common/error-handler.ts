import {IonicErrorHandler, ViewController} from "ionic-angular";
import {ErrorReportService} from "./error-report.service";
import {UserService} from "../user/user.service";

export class ApplicationErrorHandler extends IonicErrorHandler {
  constructor(private errorReportService: ErrorReportService,
              private userService: UserService,
              private viewCtrl: ViewController) {
    super();
  }

  /**
   * @internal
   */
  handleError(err: any): void {
    console.error(err); // Keep default behaviour of logging message to the console
    if (err.message && err.name /*&& err.stack*/) {
      try {
        console.debug("this.userService = " + this.userService);
        console.debug("this.userService.getCurrentUserId = " + this.userService.getCurrentUserId()); //DEBUG
        this.errorReportService.reportError(this.userService.getCurrentUserId(), this.viewCtrl.name, this.formatErrorMessage(err.message));
      } catch (newError) {
        console.warn("[ApplicationErrorHandler] -- Could not send error");
        console.warn(newError);
      }
    } else {
      console.debug("[ApplicationErrorHandler] -- Cannot handle this error:");
      console.debug(err);
    }
  }

  private formatErrorMessage(errorMessage: string): string {
    var message = errorMessage.replace(/\\s\\s+/gm, ' ');
    if (message.length > 100) {
      message = message.substr(0, 97) + '...';
    }
    return message;
  }

}
