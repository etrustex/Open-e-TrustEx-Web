import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {FolderCounters} from "../folder-counters";
import {Subject} from "rxjs/Subject";


@Injectable()
export class CountersStore {

  private folderCounters = new Subject<FolderCounters>();

  public setCounters(counters: FolderCounters) {
    this.folderCounters.next(counters);
  }

  public subscribeToFoldersCounters(): Observable<FolderCounters> {
    return this.folderCounters.asObservable();
  }

}
