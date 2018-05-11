import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-databinding',
  templateUrl: './databinding.component.html',
  styleUrls: ['./databinding.component.css']
})
export class DatabindingComponent implements OnInit {

  imageWidth: number = 250;

  interpolatedString: string = "Hey, there! I'm defined in the DataBindingComponent.";
  interpolatedNumber: number = 42;
  interpolatedBoolean: boolean = false;

  btnClass: string = 'btn btn-outline-primary';
  btnText: string = "Submit!";
  btnClassDisabled: string = "btn btn-outline-danger disabled";
  disabledText: string = "Disable :(";
  btnClassEnabled: string = "btn btn-outline-success";
  enabledText: string = "Enabled: :)";
  showButtonText: string = "Show the picture!";
  isDisplayed: boolean = false;

  listItemText: string = "";

  username: string = "";
  password: string = "";
  email: string = "";
  name: string = "";

  constructor() { }

  ngOnInit() {
  }

  showPicture(): void{
    this.isDisplayed = !this.isDisplayed;
  }

  showText(): void{
    this.listItemText = "Event Binding Rocks!!!";
  }

  hideText(): void{
    this.listItemText = "";
  }

}
