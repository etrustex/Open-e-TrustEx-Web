import {Component, ViewChild} from '@angular/core';
import {IonicPage, LoadingController, NavParams, ViewController} from 'ionic-angular';
import {Message} from "../message-list/message";
import {AttachmentList} from "./attachment-list/attachment-list";
import {Attachments} from "./attachment-list/attachments";
import {TranslateService} from "@ngx-translate/core";
import {AttachmentsService} from "./attachment-list/attachments.service";
import {MessagesService} from "../mailbox/messages.service";
import {AlertUtils} from "../common/alert-utils";
import {SearchService} from "../search/search.service";
import {FolderTypes} from "../mailbox/folder-types";
import {Loader} from "../common/loader";


@IonicPage({
  segment: 'message-details/:messageId'
})
@Component({
  selector: 'message-details-page',
  templateUrl: 'message-details-page.html'
})
/**
 * Base component for displaying message. Message content and attachments are displayed in full.
 * Controls for navigating between messages (previous, next) are added on top of a view. This page
 * is extended by InboxMessageDetailsPage and SentMessageDetailsPage as default views for each mailbox.
 */
export class MessageDetailsPage {
  // input parameter - message to be displayed
  protected messageId: string;
  // input parameter - selected folder
  protected selectedMailbox: string;
  // input parameter - which service is used
  protected service: any;
  protected title: string;
  protected message: Message;
  // loading popup view
  protected loader: Loader;

  @ViewChild(AttachmentList)
  protected view: AttachmentList;

  constructor(public navParams: NavParams,
              private viewCtrl: ViewController,
              public loadingCtrl: LoadingController,
              public messagesService: MessagesService,
              public searchService: SearchService,
              public translateService: TranslateService,
              private attachmentsService: AttachmentsService,
              private alerts: AlertUtils) {

    this.messageId = navParams.get('messageId');
    this.selectedMailbox = navParams.get('mailbox');
    this.title = this.selectedMailbox==FolderTypes.INBOX?"RETURN_TO_INBOX":"RETURN_TO_SENT";
    let serviceName = navParams.get('serviceName');
    if (serviceName && serviceName == "SearchService") {
      this.service = searchService;
      this.title = "RETURN_TO_SEARCH"; // update title for a search window
    } else {
      this.service = messagesService;
    }
    this.loader = new Loader(loadingCtrl);
  }

  ionViewDidLoad() {
    this.init();
  }

  public init() {
    this.viewCtrl.setBackButtonText(this.translateService.instant(this.title));

    this.loader.create(this.translateService.instant("LOADER_WAITING")).show().then(() => {
      this.service.getMessage(this.messageId)
        .finally(this.loader.destroy())
        .subscribe(_message => {
          //console.debug("[MessageDetailsPage] found message details with id ", value.id); //DEBUG
          this.message = _message;
          // also the message is marked read after retrieving messages if message is unread and selected folder is inbox
          if (this.isInbox() && !this.message.read) {
            this.service.markMessageRead(this.messageId).subscribe((data) => {});
          }
        }).error(() => {
          this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        })
    });
  }


  public onShowAttachments() {
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show().then(() => {
      this.attachmentsService.getAttachments(this.messageId)
        .finally(() => this.loader.destroy())
        .subscribe(_attachments => {
          this.message.attachments = _attachments;
        }, error => {
          this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        });
    });
  }

  public isInbox(): boolean {
    return this.selectedMailbox == FolderTypes.INBOX;
  }

  public getMessage(): Message {
    if (this.message) {
      return this.message;
    } else {
      return new Message();
    }
  }

  public getAttachments(): Attachments {
    if (this.message && this.message.attachments) {
      return this.message.attachments;
    } else {
      let atts = new Attachments();
      atts.attachmentList = [];
      return atts;
    }
  }

  public doShowPrevious(): void {
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show().then(() => {
      this.service.getPrevMessage(this.message.id)
        .finally(this.loader.destroy())
        .subscribe(value => {
          console.debug("[SentMessageDetailsPage.previous] found prev message details with id ", value.id); //DEBUG
          if (value != null) {
            this.message = value;
            this.view.closeListView();
          }
        }).error(() => {
          this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        });
    });
  }

  public doShowNext(): void {
    this.loader.create(this.translateService.instant("LOADER_WAITING")).show().then(() => {
      this.service.getNextMessage(this.message.id)
        .finally(this.loader.destroy())
        .subscribe(value => {
          console.debug("[SentMessageDetailsPage.next] found next message details with id ", value.id); //DEBUG
          if (value != null) {
            this.message = value;
            this.view.closeListView();
          }
        }).error(() => {
          this.alerts.showToastAlert(this.translateService.instant("SERVER_ACCESS_ERROR"), "alert-toast-error");
        });
    });
  }
}
