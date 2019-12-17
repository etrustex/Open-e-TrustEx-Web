import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {SentCounters} from "./sent-counters";
import {EtrustExHttp} from "../common/etrustex-http";
import {Configuration} from "../env/configuration";


/**
 * Menages call to retrieve sent folder counters (all and read).
 */
@Injectable()
export class SentCountersService {

  constructor(private http:EtrustExHttp, private config:Configuration) {
  }

  public getCounters(partyId: string): Observable<SentCounters> {
    console.debug("[SentCountersService.getCounters] for party", partyId); //DEBUG
    //let url: string = this.config.sentCountersUrl.replace("{partyId}", partyId);
    let url: string = this.config.sentCountersUrl.replace("{partyId}", partyId);
    return this.http.get(url)
      .map(res => res.json())
      ;
  }

}
