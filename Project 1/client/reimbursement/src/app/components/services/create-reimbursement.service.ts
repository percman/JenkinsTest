import {HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Employee} from '../../shared/Employee';
import {SimpleEmployee} from '../../shared/SimpleEmployee';
import {ReimbursementForm} from '../../shared/ReimbursementForm';
import {FileUpload} from '../../shared/FileUpload';



const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable()
export class CreateReimbursementService {

  constructor(private http: HttpClient) {

  }

  createReimbursement(ep: ReimbursementForm): Observable<boolean> {
    console.log('boop');
    return this.http.post<boolean>('http://localhost:8080/create-reimbursement.do',
      ep, httpOptions);
  }

  uploadFile(fileToUpload: any) {
    return this.http
      .post('http://localhost:8080/upload-file-test.do', {msg: fileToUpload});
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err.message);
    return throwError(err.message);
  }
}
