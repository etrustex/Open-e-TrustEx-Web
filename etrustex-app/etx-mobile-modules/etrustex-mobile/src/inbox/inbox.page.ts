import {Component} from '@angular/core';
import {IonicPage} from 'ionic-angular';
import { Events, NavController, LoadingController } from 'ionic-angular';
import { TranslateService } from "@ngx-translate/core";
import { MessagesService } from '../mailbox/messages.service';
import { Message } from "../message-list/message";
import { Observable } from "rxjs/Rx";
import {Party} from "../header/party/party-list";
import {InboxMessageStatus} from "./inbox-message-status";
import {FolderTypes} from "../mailbox/folder-types";
import {SentMessageStatus} from "../sent/sent-message-status";
import {ApplicationPlatformService} from "../app/platform.service";
import {UserService} from "../user/user.service";
import {PartyService} from "../header/party/party.service";
import {FolderCountersService} from "../mailbox/folder-counters.service";
import {MailboxAdapter} from "../mailbox/mailbox.adapter";
import {OrderBy} from "../mailbox/orderby";
import {AlertUtils} from "../common/alert-utils";
import {Subscription} from "rxjs/Subscription";
import {Loader} from "../common/loader";
import {CountersStore} from "../mailbox/providers/counters-store";

/**
 * Main page representing inbox header with user menu, application menus (folder selection, search, ...) and messages list.
 */
@IonicPage({
  segment: 'inbox'
})
@Component({
  selector: 'page-inbox',
  templateUrl: 'inbox.page.html'
})
export class InboxPage {

  // list of messages
  messagesList: Array<Message>;
  // alert or error message
  infoMessage: string;
  //label that is displayed in order filter
  orderingLabel: string = "TIME_RECEIVED";

  // loading popup view
  private loader: Loader;

  private mailbox: MailboxAdapter;

  private messagesUpdatesSubscription: Subscription;


  constructor(public events: Events,
              public navCtrl: NavController,
              private platformService: ApplicationPlatformService,
              private userService: UserService,
              private partyService: PartyService,
              private folderCountersService: FolderCountersService,
              private messagesService: MessagesService,
              loadingCtrl: LoadingController,
              public translateService: TranslateService,
              public countersStore: CountersStore,
              private alerts: AlertUtils) {
    this.mailbox = new MailboxAdapter();
    // initialize message list
    this.messagesList=[];
    this.loader = new Loader(loadingCtrl);
    this.subscribeToMessagesUpdates();
  }

  ionViewDidLoad() {
    this.initData();
  }

  ionViewWillEnter() {
    // subscribe to internal component passing events
    this.subscribeToEvents(this.events);
  }

  ionViewWillLeave() {
    // unsubscribe from registered events
    this.unsubscribeFromEvents(this.events);
  }

  ionViewWillUnload() {
    if (this.messagesUpdatesSubscription) this.messagesUpdatesSubscription.unsubscribe();
  }

  get tabletMode() : boolean {
    return this.platformService.isTabletMode();
  }

  /**
   * Subscribes to all application events
   * @param events - events handler
   */
  private subscribeToEvents(events: Events) {
    console.group("[subscribeToEvents]");
    // listening to event 'event.search.selected'
    this.events.subscribe('event.search.selected', () => {
      this.navCtrl.push('SearchPage', {
        party: this.mailbox.party.id,
        mailbox: this.mailbox.folder,
        filter: this.mailbox.getFilterName()
      });
    });

    // listening to event 'event.folder.selected'
    events.subscribe('event.folder.selected', (folder) => {
      console.debug('Now switching to folder', folder); //DEBUG
      if (folder && folder != this.mailbox.folder) {
        // do switch to list display mode
        this.doSwitchFolder(folder);
        //this.content.resize();
      }
    });

    // listening to event 'event.party.selected'
    events.subscribe('event.party.selected', (party) => {
      console.debug('Now showing messages from', party); //DEBUG
      this.doSwitchParty(party);
    });

    // listening to event 'event.menu.logout'
    events.subscribe('event.menu.logout', ()=> {
      this.logout();
    });
    console.groupEnd();
  }

  private unsubscribeFromEvents(events: Events) {
    console.group("[unsubscribeFromEvents]");
    // unsubscribe from registered events
    if (events.unsubscribe('event.search.selected')) {
      console.debug("Successfully unsubscribe from 'event.search.selected'."); //DEBUG
    }
    if (events.unsubscribe('event.folder.selected')) {
      console.debug("Successfully unsubscribe from 'event.folder.selected'."); //DEBUG
    }
    if (events.unsubscribe('event.party.selected')) {
      console.debug("Successfully unsubscribe from 'event.party.selected'."); //DEBUG
    }
    if (events.unsubscribe('event.menu.logout')) {
      console.debug("Successfully unsubscribe from 'event.menu.logout'."); //DEBUG
    }
    console.groupEnd();
  }

  private subscribeToMessagesUpdates() {
    this.messagesUpdatesSubscription = this.messagesService.messagesUpdates().subscribe((messages) => {
      let newValue: number = parseInt(this.mailbox.inboxCounters.read) + 1;
      this.mailbox.inboxCounters.read = String(newValue);
      this.messagesList = messages;
    });
  }

  private logout() {
    this.userService.logoutUser().subscribe(
      value => {
        console.debug("user successfully logged out!"); //DEBUG
        // clear all cached application data
        this.mailbox.reset();
        this.messagesList=[];
      },
      error => {
        //this.alerts.showToastAlert(this.translateService.instant("LOGOUT_ERROR"), "alert-toast-error");
        this.alerts.handleHttpError(error, this.translateService.instant("LOGOUT_ERROR"));
      }
    );
  }

  private initData() {
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show().then(() => {
      // retrieve the party lookup to be shown in a header - party menu
      this.userService.getUser()
        .flatMap(() => this.partyService.getPartyList())
        .finally(() => this.loader.destroy())
        .subscribe(
        value => {
          this.mailbox.partyList = value;
          if (this.mailbox.partyList) {
            this.mailbox.party = this.mailbox.partyList.getFirstParty();
            // retrieve global counters
            this.folderCountersService.getCounters(this.mailbox.party.id).subscribe(value => {
              if (this.tabletMode) {
                this.countersStore.setCounters(value);
              }
              this.mailbox.setCounters(value);
            });
            // retrieve folder counters and initial list of messages from webservice - for an inbox
            this.loadNewerMessages()
              .subscribe(()=> {
                // subscribe to all updates on messages that originate from the messages service
                if (this.messagesList.length == 0) {
                  this.showInfo('PARTY_FILTER_ERROR');
                }
              })
          }
        }, error => {
          this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        }
      );
    });
  }

  public fetchNewerMessages(event) {
    Observable.forkJoin(
      this.folderCountersService.getCounters(this.mailbox.party.id),
      this.loadNewerMessages()
    ).finally(()=> event.complete())
      .subscribe(dataArray => {
        let [folderCounters, flag] = dataArray;
        this.mailbox.setCounters(folderCounters);
        if (this.messagesList.length == 0) {
          this.showInfo('PARTY_FILTER_ERROR');
        }
      });
  }

  public fetchOlderMessages(event) {
    Observable.forkJoin(
      this.folderCountersService.getCounters(this.mailbox.party.id),
      this.loadOlderMessages()
    ).finally(()=> event.complete())
      .subscribe(dataArray => {
        let [folderCounters, flag] = dataArray;
        this.mailbox.setCounters(folderCounters);
        console.debug("[fetchOlderMessages] hasMoreOldMessages=", this.messagesService.hasMoreMessages()); //DEBUG
        if (this.messagesList.length == 0) {
          this.showInfo('PARTY_FILTER_ERROR');
        }
      });
  }

  public get hasMoreMessages() : boolean {
    return this.messagesService.hasMoreMessages();
  }

  public get isInboxView(): boolean {
    return this.mailbox.folder == FolderTypes.INBOX;
  }

  private startNewTimeline() {
    this.messagesService.resetTimeline();
    this.messagesList=[];
  }

  private loadOlderMessages(filterBy?: string, sortBy?: string): Observable<any> {
    return this.messagesService.getMoreMessages(this.mailbox.party.id, this.mailbox.folder, filterBy, sortBy)
      .map(value => {
        //append messages to the existing list
        this.messagesList = value;
        console.debug("[Inbox.loadOlderMessages()] new messages retrieved. list size = ", this.messagesList.length); //DEBUG
        console.debug("calculating flag for returned list: size = " + value.length);
        return (value && value.length > 0);
      })
      .catch(error => {
        this.messagesList = [];
        this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        return Observable.throw(error);
      });
  }

  /**
   * Asks for messages from the messaging service which returns list of new messages that will be appended at the top of the displayed list.
   */
  private loadNewerMessages(filterBy?: string, sortBy?: string): Observable<any> {
    return this.messagesService.getRecentMessages(this.mailbox.party.id, this.mailbox.folder, filterBy, sortBy)
      .map(value => {
          this.messagesList = value;
          if (value.length > 0) {
            this.infoMessage = null;
          }
          console.debug("[Inbox.loadNewerMessages()] new messages retrieved."); //DEBUG
          return true;
        })
      .catch(error => {
        this.messagesList = [];
        this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        return Observable.throw(error);
      });
  }

  private doSwitchParty(party: Party) {
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show();

    this.mailbox.switchParty(party);
    this.orderingLabel = "TIME_RECEIVED";

    // get new inbox counters and new messages for the selected party
    this.startNewTimeline();

    Observable.forkJoin(
      this.folderCountersService.getCounters(party.id),
      this.loadNewerMessages()
    ).finally(()=> this.loader.destroy())
     .subscribe(dataArray => {
       let [folderCounters, newerMessages] = dataArray;
       this.mailbox.setCounters(folderCounters);
         if (this.messagesList.length == 0) {
           this.showInfo('PARTY_FILTER_ERROR');
         }
      });
  }


  private doSwitchFolder(folder: string) {
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show();

    this.mailbox.switchFolder(folder);
    this.startNewTimeline();

    Observable.forkJoin(
      this.folderCountersService.getCounters(this.mailbox.party.id),
      this.loadNewerMessages())
      .finally(()=> this.loader.destroy())
      .subscribe(dataArray => {
        let [folderCounters, newerMessages] = dataArray;
        this.mailbox.setCounters(folderCounters);
        if (this.mailbox.folder == FolderTypes.INBOX) {
          // do change to INBOX
          this.orderingLabel = "TIME_RECEIVED";
        } else {
          // do change to SENT
          this.orderingLabel = "TIME_SENT";
        }
      });
  }


  private showInfo(messageCode: string) {
    this.infoMessage = this.translateService.instant(messageCode);
  }

  /*
  LISTENERS FOR CHILD COMPONENTS' EVENTS
   */

  /**
   * Called whenever user selects message sorting
   * @param {boolean} ascending
   */
  public onOrderChanged(ascending: boolean) {
    //console.debug("[Inbox.onOrderChange] ascending=", ascending); //DEBUG
    this.mailbox.order = ascending ? OrderBy.ASC : OrderBy.DESC;
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show();

    this.startNewTimeline();
    Observable.forkJoin(
      this.folderCountersService.getCounters(this.mailbox.party.id),
      this.loadNewerMessages(this.mailbox.getFilterName(), this.mailbox.getOrderBy())
    ).finally(()=> this.loader.destroy())
      .subscribe(dataArray => {
        let [folderCounters, newerMessages] = dataArray;
        this.mailbox.setCounters(folderCounters);
        if (this.messagesList.length == 0) {
          this.showInfo('PARTY_FILTER_ERROR');
        }
      });
  }

  /**
   * Called whenever user selects message status (read, unread, ...) filtering.
   * @param {InboxMessageStatus} status
   */
  public onInboxFilterChanged(status: InboxMessageStatus) {
    //console.debug("[Inbox.onInboxFilterChanged] filter by status=", InboxMessageStatus[status]); //DEBUG
    this.mailbox.filter = status;
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show();

    this.startNewTimeline();
    Observable.forkJoin(
      this.folderCountersService.getCounters(this.mailbox.party.id),
      this.loadNewerMessages(this.mailbox.getFilterName(), null)
    ).finally(()=> this.loader.destroy())
      .subscribe(dataArray => {
        let [folderCounters, newerMessages] = dataArray;
        this.mailbox.setCounters(folderCounters);
        if (this.messagesList.length == 0) {
          this.showInfo('PARTY_FILTER_ERROR');
        }
      });
  }

  /**
   * Called whenever user selects message status (read, unread, ...) filtering.
   * @param {SentMessageStatus} status
   */
  public onSentFilterChanged(status: SentMessageStatus) {
    //console.debug("[Inbox.onSentFilterChanged] filter by status=", SentMessageStatus[status]); //DEBUG
    this.mailbox.filter = status;
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show();

    this.startNewTimeline();
    Observable.forkJoin(
      this.folderCountersService.getCounters(this.mailbox.party.id),
      this.loadNewerMessages(this.mailbox.getFilterName(), null)
    ).finally(()=> this.loader.destroy())
      .subscribe(dataArray => {
        let [folderCounters, newerMessages] = dataArray;
        this.mailbox.setCounters(folderCounters);
        if (this.messagesList.length == 0) {
          this.showInfo('PARTY_FILTER_ERROR');
        }
      });
  }

  /**
   * Called whenever message is selected for details to be displayed on separate page.
   * @param {number} messageId
   */
  public onMessageSelected(messageId: string) {
    console.debug("[Inbox.onMessageSelected] selected id=", messageId); //DEBUG
    this.navCtrl.push('MessageDetailsPage', {
      messageId: messageId,
      mailbox: this.mailbox.folder,
      serviceName: "MessagesService"
    });
  }

}
