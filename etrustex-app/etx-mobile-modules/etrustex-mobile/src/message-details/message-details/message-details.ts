import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {Message} from "../../message-list/message";
import {Configuration} from "../../env/configuration";
import {TranslateService} from "@ngx-translate/core";


@Component({
  selector: 'message-details',
  templateUrl: 'message-details.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})

/**
 * View component displaying the message details. None of the content fields are truncated.
 * @Input: Message
 */
export class MessageDetails {

  ctx : string =  "/e-trustex";

  @Input()
  message: Message;

  @Input()
  isInbox: boolean;

  constructor(configuration : Configuration,
              public translateService: TranslateService) {
    this.ctx = configuration.ctx;
  }

  public isSigned(): boolean {
    return (this.message && this.message.signed);
  }

  public isEncrypted(): boolean {
    return (this.message && this.message.contentEncrypted);
  }

  public isExpired(): boolean {
    return (this.isInbox && this.message && this.message.expired);
  }

  public hasWarning(): boolean {
    return (this.isInbox && this.message && !this.message.expired && (this.message.expirationWarning  && this.message.expirationWarning.length > 0));
  }

  public hasStatus(): boolean {
    return (!this.isInbox && this.message && (this.message.status && this.message.status.length > 0))
  }

  public get status(): string {
    //console.debug("status() expired:" + this.message.expired + ", warning: " + this.message.expirationWarning); //DEBUG
    if (this.message) {
      if (this.isInbox) {
        // displayed options/statuses inside mailbox folder
        if (this.message.expired) {
          //return this.translateService.instant("ATTACHMENT_EXPIRED");
          return this.message.expirationWarning;
        } else if (this.message.expirationWarning && this.message.expirationWarning.length > 0) {
          return this.message.expirationWarning;
        }
      } else {
        // displayed options/statuses inside sent folder
        if (this.message.status && this.message.status.length > 0) {
          return this.message.status;
        }
      }
    } else {
      return "";
    }
  }

  public get warning(): string {
    if (this.message) {
      return this.message.expirationWarning;
    } else {
      return "";
    }
  }

  public getAddresseeLabel(): string {
    return this.translateService.instant(this.isInbox?"FROM":"TO");
  }

}
