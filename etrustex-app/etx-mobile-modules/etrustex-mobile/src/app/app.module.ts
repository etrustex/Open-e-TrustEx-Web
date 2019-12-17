import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import {IonicApp, IonicModule, ViewController} from 'ionic-angular';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HttpModule, Http } from '@angular/http';

import { MyApp } from './app.component';
import { DrawerMenu } from "../header/drawer-menu/drawer-menu";
import {Configuration} from "../env/configuration";
import {EtrustExHttp} from "../common/etrustex-http";
import {FilterDialog} from "../inbox/filter-dialog/filter-dialog";
import {PartySelectionDialog} from "../header/party/party-menu";
import {ErrorReportService} from "../common/error-report.service";
import {ApplicationErrorHandler} from "../common/error-handler";
import {UserService} from "../user/user.service";
import {FoldersMenuTablet} from "../header/folders-tablet/folders-menu-tablet";


/*
 * Translation initialization:
 * https://ionicframework.com/docs/developer-resources/ng2-translate/
 *
 */
export function createTranslateLoader(http: Http) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

export function provideErrorHandler(errorReportService: ErrorReportService, userService: UserService, viewController: ViewController) {
  return new ApplicationErrorHandler(errorReportService, userService, viewController);
}

@NgModule({
  declarations: [
    MyApp,
    DrawerMenu,
    FoldersMenuTablet,

    FilterDialog,
    PartySelectionDialog
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [Http]
      }
    })
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,

    // dialogs used by several pages
    FilterDialog,
    PartySelectionDialog
  ],
  providers: [
    ErrorReportService,
    //{provide: ErrorHandler, useClass: IonicErrorHandler},
    {provide: ErrorHandler, useFactory: provideErrorHandler, deps: [ErrorReportService]},
    Configuration,
    EtrustExHttp
  ]
})


export class AppModule {

    constructor() {
    }

}

