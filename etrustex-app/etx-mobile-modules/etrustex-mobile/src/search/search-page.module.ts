import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import {SharedModule} from "../common/shared.module";
import {SearchPage} from "./search-page";

@NgModule({
  declarations: [
    SearchPage
  ],
  imports: [
    SharedModule,
    IonicPageModule.forChild(SearchPage)
  ],
  entryComponents: [
  ]
})
export class SearchPageModule {}
