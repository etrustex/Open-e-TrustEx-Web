import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {FolderCounters} from "./folder-counters";
import {InboxCountersService} from "../inbox/inbox-counters.service";
import {SentCountersService} from "../sent/sent-counters.service";

/**
 * Manages global counters (inbox and sent).
 */
@Injectable()
export class FolderCountersService {

  constructor(private inboxCountersService: InboxCountersService,
              private sentCountersService: SentCountersService) {

  }

  public getCounters(partyId : string): Observable<FolderCounters> {
    //let counters = this.constructFakeData();
    console.debug("[FolderCountersService.getCounters] ...", partyId);
    return Observable.forkJoin(
      this.inboxCountersService.getCounters(partyId),
      this.sentCountersService.getCounters(partyId)
    ).map(dataArray =>  new FolderCounters(dataArray[0], dataArray[1]));
  }
}
