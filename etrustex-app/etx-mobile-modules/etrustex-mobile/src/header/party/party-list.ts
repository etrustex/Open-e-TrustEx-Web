/**
 * List of all available parties for the current user.
 */
export class PartyList {

  private list: Array<Party> = [];

  public add(val: Party) {
    this.list.push(val);
  }

  public all(): Array<Party> {
    return this.list;
  }

  public size(): number {
    return this.list.length;
  }

  public getParty(partyId: string): Party {
    for (let i=0; i<this.list.length; i++) {
      if (this.list[i].id === partyId) {
        return this.list[i];
      }
    }
    return null;
  }

  public getFirstParty(): Party {
    if (this.size() > 0) {
      return this.list[0];
    } else {
      return null;
    }
  }

}


export class Party {
  id: string;
  name: string;
}
