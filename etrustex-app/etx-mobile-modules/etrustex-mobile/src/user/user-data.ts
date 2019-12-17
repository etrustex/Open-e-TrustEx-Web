/**
 * UserData holds the information about the currently logged
 * user. Data is displayed in a popup opened after avatar icon
 * is clicked.
 */
export class UserData {
  fullName: string;
  userId: string;
  private logFlag: boolean = false;

  constructor(fullName:string, userId:string) {
    this.fullName = fullName;
    this.userId = userId;
    this.logFlag = true;
  }

  public setLogged(flag: boolean) {
    this.logFlag = flag;
  }

  public isLogged(): boolean {
    return this.logFlag;
  }

}
