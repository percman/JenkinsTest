import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { LoginService } from '../../services/login-service/login.service';
import { Employee } from '../../../shared/employee';
import { FinancialManager } from '../../../shared/financial-manager';
import { RouterLink, NavigationStart, Router } from '@angular/router';
import { IsLoggedInService } from '../../services/is-logged-in-service/is-logged-in.service';
import { NgOnChangesFeature } from '@angular/core/src/render3';
import { EmployeeService } from '../../services/employee-service/employee.service';
import { FinancialManagerService } from '../../services/financial-manager-service/financial-manager.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private errorMessage: string;
  private isFinMan: boolean;
  username: string = "";
  password: string = "";

  isLoggedIn: boolean = false;
  currentEmployee: Employee;
  currentFinMan: FinancialManager;


  constructor(
    private loginService: LoginService,
    private isLoggedInService: IsLoggedInService,
    private employeeService: EmployeeService,
    private financialManagerService: FinancialManagerService,
    private router: Router
  ) {
    this.isLoggedInService.isLoggedIn.subscribe(
      isLoggedIn => {
        this.isLoggedIn = isLoggedIn
      });
    this.employeeService.currentEmployee.subscribe(
      currentEmployee => {
        this.currentEmployee = currentEmployee
      });
    this.financialManagerService.currentFinMan.subscribe(
      currentFinMan => {
        this.currentFinMan = currentFinMan
      });
  }

  financialManager: FinancialManager;

  ngOnInit() {
  }

  login() {
    console.log(this.isFinMan);
    this.loginService.checkFinMan(this.username)
      .subscribe(
        FinMan => this.isFinMan = FinMan,
        err => this.errorMessage = err
      );
      console.log(this.isFinMan.valueOf);
    if (this.isFinMan) {
      this.loginService.loginFM(this.username, this.password)
        .subscribe(
          validFinMan => this.financialManager,
          err => this.errorMessage = err
        );
        console.log(JSON.stringify(this.financialManagerService.currentFinMan))
      if (this.currentFinMan.id != 0) {
        this.isLoggedInService.isLoggedIn.next(true);
        this.router.navigate(['../home-page'])
      }
    } else {
      this.loginService.loginE(this.username, this.password)
        .subscribe(
          validEmployee => this.employeeService.currentEmployee.next(validEmployee),
          err => this.errorMessage = err
        );
        console.log(this.employeeService.currentEmployee);
      if (this.currentEmployee.id != 0) {
        this.isLoggedInService.isLoggedIn.next(true);
        this.router.navigate(['app/home-page'])
      }
    }





  }


}
