import { Component, OnInit, Input } from '@angular/core';
import { LoginService } from '../../services/login-service/login.service';
import { Employee } from '../../../shared/employee';
import { FinancialManager } from '../../../shared/financial-manager';
import { RouterLink, NavigationStart, Router } from '@angular/router';
import { IsLoggedInService } from '../../services/is-logged-in-service/is-logged-in.service';

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
  
  @Input() isLoggedIn: boolean;


  constructor(
    private loginService: LoginService, 
    private isLoggedInService: IsLoggedInService,
    private router: Router
  ) { }

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
    } else {
      this.loginService.loginE(this.username, this.password, this.isFinMan)
      .subscribe(
        validFinMan => this.financialManager,
        err => this.errorMessage = err
      );
    }



  }



}
