import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {Message} from "../message";
import {ApplicationPlatformService} from "../../app/platform.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'message-card',
  templateUrl: 'message-card.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
/**
 * Card view for displaying one card with a message.
 */
export class MessageCard {

  @Input()
  message: Message;

  @Input()
  isInbox: boolean;

  constructor(private platformService: ApplicationPlatformService,
              public translateService: TranslateService) {
  }

  public get tabletMode() {
    return this.platformService.isTabletMode();
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

  /**
   * Returns text that will be displayed inside the badge. Based
   * on flags type of badge can be alert (expired = true) or warning
   * (expired = false, expirationWarning = ...). This behaviour is
   * intended for an inbox screen. In sent screen status will return
   * the text for actual status of the message (READ, UNREAD, DELIVERED ...).
   * @returns {string} status message displayed on the badge.
   */
  public get status() : string {
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

}
