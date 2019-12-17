import { Component, ViewChild } from '@angular/core';
import {NavController, NavParams, ViewController, LoadingController, IonicPage} from 'ionic-angular';
import { Observable } from "rxjs/Rx";
import { TranslateService } from "@ngx-translate/core";
import {Message} from "../message-list/message";
import {SearchService} from "./search.service";
import {ApplicationPlatformService} from "../app/platform.service";
import {FolderTypes} from "../mailbox/folder-types";
import {AlertUtils} from "../common/alert-utils";
import {Subscription} from "rxjs/Subscription";
import {Loader} from "../common/loader";

@IonicPage({
  segment: 'searchPage'
})
@Component({
  selector: 'search-page',
  templateUrl: 'search-page.html'
})
/**
 * SearchPage controls the search input field and shows the search result list.
 */
export class SearchPage {

  // input parameter - selected party from the inbox/sent screen
  private selectedParty: string;
  // input parameter - selected mailbox (inbox/sent)
  protected selectedMailbox: string;
  // input parameter - filter used on folder
  protected selectedFilter: string;
  // list only meant to be displayed (to trigger the data refresh on a page)
  private messagesList: Array<Message>;

  private searchQuery: string;

  // loading popup view
  private loader: Loader;
  // alert or error message
  private infoMessage: string;

  private tabletMode: boolean;

  private messagesUpdatesSubscription: Subscription;

  @ViewChild('searchField')
  searchField: any;

  constructor(public navCtrl: NavController,
              public navParams: NavParams,
              public viewCtrl: ViewController,
              public loadingCtrl: LoadingController,
              public platformService: ApplicationPlatformService,
              public translateService: TranslateService,
              public searchService: SearchService,
              private alerts: AlertUtils) {
    this.tabletMode = platformService.isTabletMode();
    this.selectedParty = navParams.get('party');
    this.selectedMailbox = navParams.get('mailbox');
    this.selectedFilter = navParams.get('filter');
    this.resetSearch();
    this.subscribeToMessagesUpdates();
    this.loader = new Loader(loadingCtrl);
  }

  ionViewDidLoad() {
    let title = this.selectedMailbox==FolderTypes.INBOX?"RETURN_TO_INBOX":"RETURN_TO_SENT";
    this.viewCtrl.setBackButtonText(this.translateService.instant(title));

    setTimeout(() => {
      //Keyboard.show() // for android
      this.searchField.setFocus();
    },1500); //a least 150ms.
  }

  ionViewWillUnload() {
    if (this.messagesUpdatesSubscription) this.messagesUpdatesSubscription.unsubscribe();
  }

  private subscribeToMessagesUpdates() {
    this.messagesUpdatesSubscription = this.searchService.messagesUpdates().subscribe((messages) => {
      this.messagesList = messages;
    });
  }

  private resetSearch() {
    // prepare for a new search
    this.searchQuery = null;
    this.searchService.resetSearch();
  }

  public isInbox(): boolean {
    return this.selectedMailbox==FolderTypes.INBOX;
  }

  public get hasMoreMessages() : boolean {
    return this.searchService.hasMoreMessages();
  }

  private getSearchMessages() {
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show().then(() => {
      this.loadMessages(this.searchQuery)
        .finally(() => this.loader.destroy())
        .subscribe(()=> {
          //console.debug("[getSearchMessages] hasMoreMessages=", this.searchService.hasMoreMessages()); //DEBUG
          if (this.messagesList.length == 0) {
            // no results found
            this.showInfo('SEARCH_ERROR');
          }
        }, () => {
          this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        })
    });
  }

  private showInfo(messageCode: string) {
    this.infoMessage = this.translateService.instant(messageCode);
  }

  /**
   * This method retrieves first page of the messages in the result list which is based on the search query.
   * @returns {Observable<any>}
   */
  private loadMessages(query: string): Observable<any> {
    if (this.isInbox()) {
      return this.searchService.getInboxMessages(this.selectedParty, query, this.selectedFilter)
        .map(value => {
          this.messagesList = value;
          return true;
        }).catch(error => {
          console.error("SearchService -> cannot retrieve messages! " + error); //ERROR INFO
          this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
          return Observable.throw(error);
        });
    } else {
    return this.searchService.getSentMessages(this.selectedParty, query, this.selectedFilter)
      .map(value => {
        this.messagesList = value;
        return true;
      }).catch(error => {
        console.error("SearchService -> cannot retrieve messages! " + error); //ERROR INFO
        this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        return Observable.throw(error);
      });
    }
  }

  /*
  LISTENERS FOR CHILD COMPONENTS' EVENTS
   */

  public getNextPage(event) {
    this.loadMessages(this.searchQuery)
      .finally(() => event.complete())
      .subscribe(()=> {})
  }

  //when the onInput was called the last time
  private lastInputTs: number = 0;

  public onInput(event) {
    //console.debug("[Search.onInput] ", event.target.value); //DEBUG
    let value = event.target.value;
    if (value != undefined) {
      let now:number = Date.now();
      if (this.lastInputTs == 0 || now - this.lastInputTs > 6000) {
        // user put some value there and it did not press the clear button
        value = value.trim();
        if (value.length > 3 && value != this.searchQuery) {
          this.messagesList = [];
          this.searchQuery = value;
          this.getSearchMessages();
          //this.resetSearch();
          this.lastInputTs = now;
        }
      }
    }
  }

  public onCancel(event) {
    console.debug("[Search.onCancel] ", event); //DEBUG
  }

  /**
   * Called whenever message is selected for details to be displayed on separate page.
   * @param {number} messageId
   */
  public onMessageSelected(messageId: number) {
    console.debug("[Search.onMessageSelected] selected id =", messageId); //DEBUG
    this.navCtrl.push('MessageDetailsPage', {
      messageId: messageId,
      mailbox: this.selectedMailbox,
      serviceName: "SearchService"
    });
  }

}
