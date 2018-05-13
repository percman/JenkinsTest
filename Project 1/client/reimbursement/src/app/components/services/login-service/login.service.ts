import { Component, OnInit } from '@angular/core';
import {HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Employee} from '../../../shared/Employee';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {

  }
  /** POST: add a new hero to the database */

  login (ep: Employee): Observable<Employee> {
    console.log('boop');
    return this.http.post<Employee>('http://localhost:8080/login.do',
      ep, httpOptions).pipe(catchError(err => this.handleError(err)));
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err.message);
    return throwError(err.message);
  }


}
