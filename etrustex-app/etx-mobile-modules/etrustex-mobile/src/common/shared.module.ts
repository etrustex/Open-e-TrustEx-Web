import {NgModule} from "@angular/core";
import { CommonModule }  from '@angular/common';
import {MessageList} from "../message-list/message-list";
import {MessageCard} from "../message-list/message-card/message-card";
import {IonicModule} from "ionic-angular";
import {TranslateModule} from "@ngx-translate/core";
import {InfoView} from "../info/info-view";
import {MessageDetails} from "../message-details/message-details/message-details";
import {AttachmentList} from "../message-details/attachment-list/attachment-list";
import {Focusable} from "../search/focusable";
//import {MomentModule} from "angular2-moment";

/**
 * Declares the components used in multiple lazy pages (using @IonicPage).
 * To be included in the module of each IonicPage.
 */
@NgModule({
  imports: [
    CommonModule,
    IonicModule,
    TranslateModule.forChild(),
    //MomentModule
  ],
  declarations: [
    Focusable,
    MessageList,
    MessageCard,
    MessageDetails,
    AttachmentList,
    InfoView
  ],
  exports: [
    MessageList,
    MessageCard,
    MessageDetails,
    AttachmentList,
    InfoView,
    TranslateModule
  ]
})
export class SharedModule {}
