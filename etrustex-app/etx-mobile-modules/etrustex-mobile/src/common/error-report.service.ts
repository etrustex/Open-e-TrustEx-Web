import {Injectable} from "@angular/core";
import {EtrustExHttp} from "./etrustex-http";
import {Configuration} from "../env/configuration";

@Injectable()
export class ErrorReportService {

  private readonly DETAILS: string = "User:%s1|appVersion:%s2|error:%s3";

  constructor(private http: EtrustExHttp, private config: Configuration) {
  }


  /**
   * Send the report of an error back to the server.
   * @param {string} userId currently logged user
   * @param {string} location where error happened (which view or page, ...)
   * @param {string} details detailed information about an error
   */
  public reportError(userId: string, location: string, details: string) {
    let appVersion = this.config.version;
    let url: string = this.config.clientErrorUrl;
    this.http.post(url, this.buildRequestBody(userId, appVersion, location, details));
      //.map(res => res.json);
  }

  private buildRequestBody(userId: string, version: string, location: string, details: string) {
    let body = {
      location: location,
      details: this.DETAILS.replace("%s1", userId).replace("%s2", version).replace("%s3", details)
    };
    return body;
  }

}
