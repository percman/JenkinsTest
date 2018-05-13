import { Component, ViewChild } from '@angular/core';
import { LoginComponent } from '../app/components/login/login.component';
import { IsLoggedInService } from './services/is-logged-in-service/is-logged-in.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  isLoggedIn: boolean;
  constructor(private isLoggedInService: IsLoggedInService){
    this.isLoggedInService.isLoggedIn.subscribe(
      value => {this.isLoggedIn = value;}
    );
  }

}
