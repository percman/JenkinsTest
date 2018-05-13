import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
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
    return this.http.get(this.urlFM)
      .catch(err => this.handleError(err));
  }

  loginE(username: string, password: string): Observable<Employee> {
    return this.http.get<Employee>(this.urlL)
      .catch(err => this.handleError(err));
  }

  loginFM(username: string, password: string): Observable<FinancialManager> {
    return this.http.get<FinancialManager>(this.urlL)
      .catch(err => this.handleError(err));
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err.message);
    return Observable.throw(err.message);
  }

}
