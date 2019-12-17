import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Rx";
import {PartyList} from "./party-list";
import {EtrustExHttp} from "../../common/etrustex-http";
import {Configuration} from "../../env/configuration";

@Injectable()
export class PartyService {

  private partyList: PartyList = null;


  constructor(private http:EtrustExHttp, private config:Configuration) {
  }


  public getPartyList(): Observable<PartyList> {
    console.log("[PartyService.getPartyList] getting party list for user");

    if (this.partyList) {
      return Observable.of(this.partyList);
    }

    return this.http.get(this.config.partiesUrl)
      .map(res => res.json())
      .map(parties => {
        this.partyList = new PartyList();
        parties.partyList.forEach(party => {
          this.partyList.add(party);
        });
        return this.partyList;
      });
  }

}
