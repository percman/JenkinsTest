import {HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Employee} from '../../shared/Employee';
import {SimpleEmployee} from '../../shared/SimpleEmployee';
import {MyReimbursement} from '../../shared/MyReimbursement';
import {RStatusSetter} from '../../shared/RStatusSetter';
import {BigReimbursement} from '../../shared/BigReimbursement';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable()
export class AdminTableService {

  constructor(private http: HttpClient) {

  }

  getAllReimbursements(ep: SimpleEmployee): Observable<BigReimbursement[]> {
    console.log('boop');
    return this.http.post<BigReimbursement[]>('http://localhost:8080/get-all-pending.do',
      ep, httpOptions);
  }

  setRStatus(rs: RStatusSetter): Observable<boolean> {
    console.log('boop');
    return this.http.post<boolean>('http://localhost:8080/get-my-reimbursements.do',
      rs, httpOptions);
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err.message);
    return throwError(err.message);
  }
}
