import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Employee } from '../../../shared/employee';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { FinancialManager } from '../../../shared/financial-manager';


@Injectable()
export class LoginService {

  readonly urlL: string = "http://localhost:8080/EmployeeReimbursementSystem/login";
  readonly urlFM: string = "http://localhost:8080/EmployeeReimbursementSystem/isFinMan";


  constructor(private http: HttpClient) { }

  checkFinMan(username: string): Observable<boolean> {
    return this.http.post(
      this.urlFM,
      JSON.stringify([username]))
      .catch(err => this.handleError(err));
  }

  loginE(username: string, password: string, isFinMan: boolean): Observable<Employee> {

    return this.http.post<Employee>(
      this.urlL, 
      {username: username, 
        password:password,
        isFinMan: isFinMan
      })
      .catch(err => this.handleError(err));
  }

  loginFM(username: string, password: string, isFinMan: boolean): Observable<FinancialManager> {
    return this.http.post<FinancialManager>(
      this.urlL,
      JSON.stringify([username, password, isFinMan]))
      .catch(err => this.handleError(err));
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
  }

}
