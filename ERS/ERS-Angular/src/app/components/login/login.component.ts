import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { LoginService } from '../../services/login-service/login.service';
import { Employee } from '../../../shared/employee';
import { FinancialManager } from '../../../shared/financial-manager';
import { RouterLink, NavigationStart, Router } from '@angular/router';
import { IsLoggedInService } from '../../services/is-logged-in-service/is-logged-in.service';
import { NgOnChangesFeature } from '@angular/core/src/render3';

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

  isLoggedIn: boolean;

  constructor(
    private loginService: LoginService,
    private isLoggedInService: IsLoggedInService,
    private router: Router
  ) {
    this.isLoggedInService.isLoggedIn.subscribe(
      value => { this.isLoggedIn = value; }
    );
  }

  employee: Employee;
  financialManager: FinancialManager;

  ngOnInit() {
  }

  login() {
    this.loginService.checkFinMan(this.username)
      .subscribe(
        FinMan => this.isFinMan = FinMan,
        err => this.errorMessage = err
      );
    if (this.isFinMan) {
      this.loginService.loginFM(this.username, this.password, this.isFinMan)
        .subscribe(
          validEmployee => this.employee = validEmployee,
          err => this.errorMessage = err
        );
        this.isLoggedInService.isLoggedIn.next(true);
        this.router.navigate(['/app/home-page']);
    } else {
      this.loginService.loginE(this.username, this.password, this.isFinMan)
        .subscribe(
          validFinMan => this.financialManager,
          err => this.errorMessage = err
        );
        this.isLoggedInService.isLoggedIn.next(true);
        this.router.navigate(['/app/home-page']);

    }

 



  }



}
