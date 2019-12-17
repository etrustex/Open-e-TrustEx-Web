import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {UserData} from "./user-data";
import {Configuration} from "../env/configuration";
import {EtrustExHttp} from "../common/etrustex-http";


/**
 * Exposes all API calls regarding the user:
 * - retrieving user data.
 */
@Injectable()
export class UserService {

  private currentUser: UserData = null;

  public constructor(public http: EtrustExHttp, private config : Configuration) {
  }

  /**
   * Returns the id of the current user (if one exists).
   * @returns {String} id of the logged user or null
   */
  public getCurrentUserId(): string {
    if (this.currentUser) {
      return this.currentUser.userId;
    } else {
      return null;
    }
  }

  /**
   * Obtains the user information (user name, id, ...) from a remote service.
   * @returns {Observable<UserData>}
   */
  public getUser(): Observable<UserData> {
    if (this.currentUser != null) {
      return Observable.of(this.currentUser);
    } else {
      // return new Observable(observer => {
      //   setTimeout(() => {
      //     // OA. currently we are returning the fake user. otherwise we'll return the one obtained from service.
      //     this.currentUser = new UserData("John Doe", "extjdoe");
      //     observer.next(this.currentUser);
      //   }, 1000);
      // });
      return this.http.get(this.config.usersUrl)
          .map(res => res.json())
          .map(user => {
            this.currentUser = new UserData(user.fullName, user.userId);
            return this.currentUser;
          });
    }
  }


  public removeUser() {
    this.currentUser = null;
  }

  public isLogged(): boolean {
    return this.currentUser != null;
  }

  /**
   * Logout user from this application by signing it off from ecas after logout api call.
   * @returns {Observable<boolean>} true if logout succeeded
   */
  public logoutUser(): Observable<boolean> {
    let url = this.config.ecasLogoutUrl;
    //TODO wrap this inside the api call for a logout!
    return this.http.post(url, "").map(
      value => {
        // we logged out from ecas
        this.removeUser();
        return true;
      });
  }
}
