import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {InboxCounters} from "./inbox-counters";
import {EtrustExHttp} from "../common/etrustex-http";
import {Configuration} from "../env/configuration";


/**
 * Manages call to retrieve inbox counters (all and read).
 */
@Injectable()
export class InboxCountersService {

  constructor(private http:EtrustExHttp, private config:Configuration) {
  }

  public getCounters(partyId: string): Observable<InboxCounters> {
    console.debug("[InboxCountersService.getCounters] for party", partyId); //DEBUG
    let url: string = this.config.inboxCountersUrl.replace("{partyId}", partyId);
    return this.http.get(url)
      .map(res => res.json());
  }

}
