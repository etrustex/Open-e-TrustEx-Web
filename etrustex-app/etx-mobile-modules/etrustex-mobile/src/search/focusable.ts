import {Directive, Renderer, ElementRef} from '@angular/core';


@Directive({
  selector : 'focusable'//'ion-searchbar'
})
export class Focusable {
  constructor(public renderer: Renderer, public elementRef: ElementRef) {}

  focus() {
    console.debug(this.elementRef.nativeElement);
    this.renderer.invokeElementMethod(
      this.elementRef.nativeElement, 'focus', []);
  }
/*
  ngOnInit() {
    //search bar is wrapped with a div so we get the child input
    const searchInput = this.elementRef.nativeElement.querySelector('input');
    setTimeout(() => {
      //delay required or ionic styling gets finicky
      this.renderer.invokeElementMethod(searchInput, 'focus', []);
    }, 0);
  }
  */

}
