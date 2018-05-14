import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {catchError, mapTo} from 'rxjs/operators';
import {Employee} from '../../shared/Employee';
import {HttpHeaders} from '@angular/common/http';
import {LoginService} from '../services/login.service';
import {Globals} from '../../shared/Globals';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [LoginService],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  indicator = '';
  username = '';
  password = '';

  constructor(private loginService: LoginService, public globals: Globals, private router: Router) { }

  onSubmit(): void {
    console.log('beep');
    this.loginService.login({'username': this.username, 'password': this.password})
      .subscribe(success => this.populate(success),
                      err => this.indicator = err['error']);
  }

  private populate(empl: Employee) {
    this.indicator = JSON.stringify(empl);
    this.globals.address = empl['address'];
    this.globals.eid = empl['eid'];
    this.globals.firstName = empl['firstName'];
    this.globals.lastName = empl['lastName'];
    this.globals.isManager = empl['manager'];
    this.globals.ready = true;
    this.indicator = JSON.stringify(this.globals.isManager);
    if (!this.globals.isManager) {
      this.router.navigate(['/employee-splash']);
    }
  }
  ngOnInit() {
  }

}
