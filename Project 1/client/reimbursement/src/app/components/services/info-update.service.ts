import {HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Employee} from '../../shared/Employee';
import {SimpleEmployee} from '../../shared/SimpleEmployee';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable()
export class UpdateService {

  constructor(private http: HttpClient) {

  }

  update(ep: Employee): Observable<boolean> {
    console.log('boop');
    return this.http.post<boolean>('http://localhost:8080/update-info.do',
      ep, httpOptions);
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err.message);
    return throwError(err.message);
  }
}
