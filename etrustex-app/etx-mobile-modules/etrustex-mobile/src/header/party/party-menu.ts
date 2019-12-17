import {Component, Input} from '@angular/core';
import { ViewController, PopoverController, NavParams, Events } from 'ionic-angular';
import {Party, PartyList} from "./party-list";
import {Configuration} from "../../env/configuration";


@Component({
  template: `
   <!-- <ion-list class="popover-page">-->
   <ion-list style="width:100%; border-radius: 0;">
      <ion-item *ngFor="let e of listData;"
                tappable (click)="selectParty(e.id)"                
                style="font-size: 1em;">
        <ion-label>{{e.name}}</ion-label>
      </ion-item>      
    </ion-list>
  `
})
export class PartySelectionDialog {
  private listData: PartyList;

  constructor(private viewCtrl: ViewController, private navParams: NavParams) {
  }


  ngOnInit() {
    if (this.navParams.data) {
      this.listData = this.navParams.data.listData;
    }
  }

  public selectParty(id: string) : void {
    // close the dialog and return the selected id
    this.viewCtrl.dismiss(id);
  }
}


//----------------------------------------------

/**
 * Selector component with current selected value displayed.
 * It handles click on component after the popup with selection
 * list is opened.
 */
@Component({
  selector: 'party-menu',
  templateUrl: 'party-menu.html'
})

export class PartyMenu {

  ctx : string = "/e-trustex";

  @Input()
  private parties: PartyList;

  @Input()
  private selectedParty: Party;

  private groupShown: boolean = false;
  private arrowIcon: string = "arrow-down";


  constructor(private popoverCtrl: PopoverController, public events: Events, configuration : Configuration) {
    this.ctx = configuration.ctx;
  }

  public toggleGroup() : void {
      this.groupShown = !this.groupShown;
      if (this.isGroupShown()) {
        this.setIcon("arrow-up");
      } else {
        this.setIcon("arrow-down");
      }
  };

  public isGroupShown() : boolean {
    return this.groupShown;
  };

  public setIcon(icon: string) : void {
    this.arrowIcon = icon;
  }

  public openPartyDialog(ev) {
    this.setIcon("arrow-up");
    console.debug("[openPartyDialog] parties: " + this.parties.size()); //DEBUG
    let a = this.parties.all();
    let popover = this.popoverCtrl.create(PartySelectionDialog, {
      // pass data params
      listData: a
    }, {
      cssClass: "party-popover"
    });

    popover.onDidDismiss((selectedId) => {
      console.debug("returned (selected) id = " + selectedId); //DEBUG
      this.setIcon("arrow-down");
      if (selectedId != null) {
        this.selectedParty = this.parties.getParty(selectedId);
        // notify parent which party was selected
        this.events.publish('event.party.selected', this.parties.getParty(selectedId));
      }
    });

    popover.present({
      ev: ev
    });

  }
}
