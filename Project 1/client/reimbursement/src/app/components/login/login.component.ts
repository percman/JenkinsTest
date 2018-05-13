import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Employee} from '../../shared/Employee';
import {HttpHeaders} from '@angular/common/http';
import {LoginService} from '../services/login-service/login.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [LoginService],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  indicator = '';
  constructor(private loginService: LoginService) { }

  onSubmit(): void {
    console.log('beep');
    this.loginService.login({'username': this.username, 'password': this.password})
      .subscribe(success => this.indicator = 'IT WORKED',
                      err => this.indicator = err);
  }
  ngOnInit() {
  }

}
