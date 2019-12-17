import {Observable} from "rxjs/Observable";
import {Request, RequestOptionsArgs, Response, Headers, Http, RequestOptions} from "@angular/http";
import {Injectable} from "@angular/core";
import {Configuration} from "../env/configuration";
import 'rxjs/add/observable/throw';
import {EtrustExError} from "./etrustex-error";
import stringify from "fast-safe-stringify";

/**
 * Allows for explicit authenticated HTTP requests.
 */
@Injectable()
export class EtrustExHttp {
    constructor(private http: Http, private configuration : Configuration) {
    }

    private handleResponse(res: Response) : Response | Observable<any> {
      console.debug("[handleResponse] status=" + res.status + ", message=" + res.statusText); //DEBUG
        if (res.status <= 0) {
          // no network
          throw new EtrustExError(String(res.status), "No network");
        }
        if (res.status == 500) {
          let err = res.json();
          throw new EtrustExError(err.type, err.message);
        }
        if (res.status == 401 || res.status == 403) {
          throw new EtrustExError(String(res.status), "Cannot access service: " + res.statusText);
        }
        if (res.status >= 201) {
          throw new EtrustExError(String(res.status), res.statusText);
        }
        return res;
    }

  private handleError(error: Response) : Observable<any> {
    console.warn("HTTP error response: " + stringify(error));
    console.debug("[handleResponse] status=" + error.status + ", message=" + error.statusText); //DEBUG
    let message = "";

    switch (error.status) {
      case -1:
      case 0:
        message = "No network";
        break;
      case 304:
        // No operation: not modified!
        break;
      case 400:
      case 401:
      case 403:
        message = error.json().message;
        //this.logout();
        break;
      default:
        message = "An error occurred with the server";
        break;
    }
    return this.throwErrorForObservable(error.status + "", message);
  }

  private throwErrorForObservable(status: string, message: string): Observable<any> {
    return Observable.throw(new EtrustExError(status, message));
  }

    request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.request(url, this.includeCustomHeaders(options));
    }

    get(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.get(url, this.includeCustomHeaders(options))
          .map(this.handleResponse)
          .catch(this.handleError);
    }

    post(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.post(url, body, this.includeCustomHeaders(options))
          .map(this.handleResponse)
          .catch(this.handleError);
    }

    /*put(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.put(url, body, this.includeCustomHeaders(options)).map(this.handleResponse);
    }

    delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.delete(url, this.includeCustomHeaders(options)).map(this.handleResponse);
    }

    patch(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.patch(url, body, this.includeCustomHeaders(options)).map(this.handleResponse);
    }

    head(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.head(url, options);
    }

    options(url: string, options?: RequestOptionsArgs): Observable<Response> {
        return this.http.options(url, this.includeCustomHeaders(options));
    }*/

    private includeCustomHeaders(options?: RequestOptionsArgs): RequestOptionsArgs {
        let customHeaders = this.getCustomHeaders();
        console.log("Using custom headers " + stringify(customHeaders));
        if (options && options != null) {
            if (options.headers) {
                customHeaders.forEach((values: string[], name: string) => {
                    options.headers.set(name, values);
                });
            } else {
                options.headers = customHeaders;
            }
            return options;
        } else {
            options = new RequestOptions();
            options.headers = customHeaders;
            return options;

            /*return {
                headers: customHeaders,
            };*/
        }
    }

    private getCustomHeaders(): Headers {
        let customHeaders = new Headers();
        //customHeaders.append('Access-Control-Allow-Origin', '*');

        if (this.configuration.useFakeIdentity) {
          // # separates ecas ID from full name
          //customHeaders.append('Authorization', 'EtrustExFakeIdentity jdoeid#John DOE');
          customHeaders.append('Authorization', 'EtrustExFakeIdentity arkoosk#');
        }

        return customHeaders;
    }
}
