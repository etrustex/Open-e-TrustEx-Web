import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import {InboxPage} from "./inbox.page";
import {Header} from "../header/header";
import {InboxCountersMenu} from "./inbox-counters-menu/inbox-counters-menu";
import {SentCountersMenu} from "../sent/sent-counters-menu/sent-counters-menu";
import {InboxOrdering} from "./inbox-ordering/inbox-ordering";
import {FoldersMenu} from "../header/folders/folders-menu";
import {SearchMenu} from "../header/search/search-menu";
import {PartyMenu} from "../header/party/party-menu";
import {Title} from "../header/title/title";
import {SharedModule} from "../common/shared.module";

@NgModule({
  declarations: [
    InboxPage,

    Header,
    Title,
    FoldersMenu,
    SearchMenu,

    PartyMenu,

    InboxOrdering,
    InboxCountersMenu,
    SentCountersMenu
  ],
  imports: [
    SharedModule,
    IonicPageModule.forChild(InboxPage)
  ]
})
export class InboxPageModule {}
