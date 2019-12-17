import { Component } from '@angular/core';
import { ViewController, NavParams, } from 'ionic-angular';


@Component({
  template: `   
   <ion-list style="width:100%; border-radius: 0;">
      <ion-item *ngFor="let e of filterChoices; let i=index"
                tappable (click)="doSelectFilter(i)"                
                style="font-size: 1.15em;">        
        <ion-label>{{ e }}</ion-label>
      </ion-item>      
    </ion-list>
  `
})

/**
 * FilterDialog component displays popup with the given choices (as a string array). Values in an array must be already translated.
 * It returns index inside the list or null if user presses outside the component.
 */
export class FilterDialog {
  private filterChoices: string[];

  constructor(private viewCtrl: ViewController, private navParams: NavParams) {
  }


  ngOnInit() {
    if (this.navParams.data) {
      this.filterChoices = this.navParams.data.filterChoices;
    }
  }

  public doSelectFilter(choiceIndex: string) : void {
    // close the dialog and return the selected index in the list of choices
    this.viewCtrl.dismiss(choiceIndex);
  }

}
