import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { SplashPage } from './splash';
import {SharedModule} from "../common/shared.module";

@NgModule({
  declarations: [
    SplashPage,
  ],
  imports: [
    SharedModule,
    IonicPageModule.forChild(SplashPage),
  ],
  exports: [
    SplashPage
  ]
})
export class SplashPageModule {}
