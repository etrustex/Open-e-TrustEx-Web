import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import {TranslateModule} from "@ngx-translate/core";
import {MessageDetailsPage} from "./message-details-page";
import {SharedModule} from "../common/shared.module";


@NgModule({
  declarations: [
    MessageDetailsPage
  ],
  imports: [
    IonicPageModule.forChild(MessageDetailsPage),
    TranslateModule.forChild(),
    SharedModule
  ],
  entryComponents: [
  ]
})
export class MessageDetailsPageModule {}
