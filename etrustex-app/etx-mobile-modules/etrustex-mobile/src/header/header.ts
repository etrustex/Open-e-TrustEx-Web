import { Component, Input } from '@angular/core';
import {Party, PartyList} from "./party/party-list";
import {FolderCounters} from "../mailbox/folder-counters";
import {ApplicationPlatformService} from "../app/platform.service";


@Component({
  selector: 'header',
  templateUrl: 'header.html'
})
export class Header {

  @Input()
  folderCounters: FolderCounters;

  @Input()
  partyList: PartyList;

  @Input()
  selectedParty: Party;

  constructor(private platformService: ApplicationPlatformService) {

  }

  get tabletMode(): boolean {
    return this.platformService.isTabletMode();
  }
}
