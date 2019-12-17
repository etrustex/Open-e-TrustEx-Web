import {Attachments} from "../message-details/attachment-list/attachments";

/**
 * Message class holds data for one message received from a service. It will be rendered either in list view or details view.
 */
export class Message {
  id: string;
  sender: string; // party sending this message
  recipient: string; // party receiving this message
  receiptDate: Date;
  sentDate: Date;
  statusReceiptDate: Date;
  subject: string;
  content: string;
  attachmentCount: string;
  totalAttachmentSize: string;
  contentEncrypted: boolean; // if attachments are encrypted
  signed: boolean;
  expirationWarning: string;
  expired: boolean; // only if attachment expired
  status: string; // based on mailbox (inbox: unread/read, ...)
  read: boolean;

  attachments: Attachments;

}
