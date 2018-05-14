import {HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Employee} from '../../shared/Employee';
import {SimpleEmployee} from '../../shared/SimpleEmployee';
import {MyReimbursement} from '../../shared/MyReimbursement';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable()
export class GetMyReimbursementsService {

  constructor(private http: HttpClient) {

  }

  getMyReimbursements(ep: SimpleEmployee): Observable<MyReimbursement[]> {
    console.log('boop');
    return this.http.post<MyReimbursement[]>('http://localhost:8080/get-my-reimbursements.do',
      ep, httpOptions);
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err.message);
    return throwError(err.message);
  }
}
