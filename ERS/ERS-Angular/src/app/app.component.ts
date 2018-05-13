import { Component, ViewChild } from '@angular/core';
import { LoginComponent } from '../app/components/login/login.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
//   template: `
//   Message: {{isLoggedIn}}
//   <app-login></app-login>
// `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // @ViewChild(LoginComponent) login;
  
  // constructor() { }

  // isLoggedIn: boolean = false;

  // ngAfterViewInit(){
  //   this.isLoggedIn = this.login.isLoggedIn;
  // }


}
