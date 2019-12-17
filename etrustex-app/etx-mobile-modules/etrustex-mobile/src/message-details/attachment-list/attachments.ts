import {Attachment} from "./attachment";

export class Attachments {

  private _attachmentList: Array<Attachment>;

  get attachmentList(): Array<Attachment> {
    return this._attachmentList;
  }

  set attachmentList(value: Array<Attachment>) {
    this._attachmentList = value;
  }

  public sortByName(ascending: boolean) {
    this.attachmentList = this.attachmentList.sort((a, b: Attachment): number => {
      if(a.name < b.name) return ascending?-1:1;
      if(a.name > b.name) return ascending?1:-1;
      return 0;
    });
  }

  public sortByType(ascending: boolean) {
    this.attachmentList = this.attachmentList.sort((a, b: Attachment): number => {
      if(a.mimeType < b.mimeType) return ascending?-1:1;
      if(a.mimeType > b.mimeType) return ascending?1:-1;
      return 0;
    });
  }

}
