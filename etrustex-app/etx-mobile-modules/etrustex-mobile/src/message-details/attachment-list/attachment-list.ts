import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Attachments} from "./attachments";
import {Attachment} from "./attachment";


@Component({
  selector: 'attachment-list',
  templateUrl: 'attachment-list.html'
})


/**
 * Component displaying the list of attachments in a table view
 * that can by sorted by name (asc or desc).
 */
export class AttachmentList {

  @Input()
  attachments: Attachments;

  @Output()
  onShowAttachments = new EventEmitter<boolean>();

  private attachmentsVisible: boolean = false;

  private sortByNameAscending: boolean = false;
  private sortByTypeAscending: boolean = false;

  public getAttachments(): Array<Attachment> {
    if (this.attachments) {
      return this.attachments.attachmentList;
    } else {
      return [];
    }
  }

  public sortByName(): void {
    this.sortByNameAscending = !this.sortByNameAscending;
    console.log("sortByName triggered!", this.sortByNameAscending?"ASC":"DESC"); //DEBUG
    this.attachments.sortByName(this.sortByNameAscending);
  }

  public sortByType(): void {
    this.sortByTypeAscending = !this.sortByTypeAscending;
    console.log("sortByType triggered!", this.sortByTypeAscending?"ASC":"DESC"); //DEBUG
    this.attachments.sortByType(this.sortByTypeAscending);
  }

  /**
   * Shows or hides the table with attachments. This
   * method is called when button is pressed in 'List of files'.
   */
  public changeListView(): void {
    this.attachmentsVisible = !this.attachmentsVisible;
    // notify parent to load attachments if files have been opened
    if (this.attachmentsVisible && !this.attachments.attachmentList) {
      this.onShowAttachments.emit();
    }
  }

  public closeListView(): void {
    this.attachmentsVisible = false;
  }

  public getAttachmentType(att: Attachment): string {
    if (att) {
      return this.getMimeType(att);
    }
    return "-";
  }

  private getMimeType(att: Attachment): string {
    if (att.mimeType) {
      let val = att.mimeType.toUpperCase();
      switch (val) {
        case "APPLICATION/PDF": return "PDF";
        case "APPLICATION/XLS": return "XLS";
        case "APPLICATION/DOC": return "DOC";
        case "APPLICATION/XML": return "XML";
        default: return val.substring(val.indexOf("/")+1);
      }
    }
  }

}
