import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';

import {MessagesService} from "../mailbox/messages.service";
import {TranslateService} from "@ngx-translate/core";
import {MessageAPI} from "../mailbox/messageApi.service";
import {UserData} from "../user/user-data";
import {UserService} from "../user/user.service";
import {PartyService} from "../header/party/party.service";
import {InboxCountersService} from "../inbox/inbox-counters.service";
import {SearchService} from "../search/search.service";
import {SentCountersService} from "../sent/sent-counters.service";
import {ApplicationPlatformService} from "./platform.service";
import {FolderCountersService} from "../mailbox/folder-counters.service";
import {AttachmentsService} from "../message-details/attachment-list/attachments.service";
import {AlertUtils} from "../common/alert-utils";
import {ErrorReportService} from "../common/error-report.service";
import stringify from "fast-safe-stringify";
import {CountersStore} from "../mailbox/providers/counters-store";
import {Configuration} from "../env/configuration";


@Component({
  templateUrl: 'app.html',
  providers: [
    MessagesService,
    SearchService,
    MessageAPI,
    UserService,
    PartyService,
    FolderCountersService,
    InboxCountersService,
    SentCountersService,
    AttachmentsService,
    ApplicationPlatformService,
    ErrorReportService,
    CountersStore,
    AlertUtils]
})
export class MyApp {
  rootPage:any = 'SplashPage';

  // information about currently logged user
  private currentUser: UserData = null;


  constructor(platform: Platform,
              private platformService: ApplicationPlatformService,
              private translateService: TranslateService,
              userService: UserService,
              config : Configuration,
              private alerts: AlertUtils) {
    platform.ready().then(() => {
      platformService.init();

      console.debug("isTablet? %s ... portrait? %s", platformService.isTabletMode(), platformService.isPortraitMode()); //DEBUG
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.

      // this language will be used as a fallback when a translation isn't found in the current language
      translateService.setDefaultLang('en');
      translateService.use('en');

      console.info("Using API base url: " + config.apiBaseUrl);

      userService.getUser().subscribe
        (value => {
          console.debug("found user " + value.fullName); //DEBUG
          this.currentUser = value;
          // we have a user and now show the page
          this.rootPage = 'InboxPage';
        }, error => {
          console.error("UserService -> unknown user or user not found! " + stringify(error));
          this.alerts.handleHttpError(error, this.translateService.instant("SERVER_ACCESS_ERROR"));
        });
    });
  }

  /**
   * Returns link to the currently logged user.
   * User is retrieved from a user service at init time.
   * @returns {UserData}
   */
  get user(): UserData {
    return this.currentUser;
  }

  public get tabletMode() {
    return this.platformService.isTabletMode();
  }

}

