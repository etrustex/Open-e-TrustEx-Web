import {Injectable} from "@angular/core";
import {EtrustExHttp} from "../../common/etrustex-http";
import {Configuration} from "../../env/configuration";
import {Observable} from "rxjs/Observable";
import {Attachments} from "./attachments";

@Injectable()
export class AttachmentsService {

  constructor(private http:EtrustExHttp, private config:Configuration) {
  }


  public getAttachments(messageId: string): Observable<Attachments> {
    console.debug("[AttachmentsService.getAttachments] for message", messageId); //DEBUG
    let url: string = this.config.messageAttachmentsUrl.replace("{messageId}", messageId);
    return this.http.get(url)
      .map(res => res.json())
      .map(atts => {
        let attachments = new Attachments();
        attachments.attachmentList = atts.attachmentList;
        return attachments;
      });
  }

}
