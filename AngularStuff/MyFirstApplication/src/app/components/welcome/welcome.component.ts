import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html'//,
//  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  imageWidth: number = 500;
  messageFromInsideWelcomeComponent: string = "Hello, World!";

  constructor() { }

  ngOnInit() {
  }

}
