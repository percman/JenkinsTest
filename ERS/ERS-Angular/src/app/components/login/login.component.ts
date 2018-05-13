import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login-service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private errorMessage: string;
  private isFinMan: boolean = false;
  username: string = "";
  password: string = "";
  isLoggedIn: boolean = false;


  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

  login() {
    this.loginService.checkFinMan(this.username)
      .subscribe(
        FinMan => this.isFinMan = FinMan,
        err => this.errorMessage = err
      );
    if (this.isFinMan) {
      this.loginService.loginFM(this.username, this.password);
      this.isLoggedIn = true;
    } else {
      this.loginService.loginE(this.username, this.password);
      this.isLoggedIn = true;
    }
  }



}
