import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { FinancialManager } from '../../../shared/financial-manager';

@Injectable()
export class FinancialManagerService {

  public currentFinMan: BehaviorSubject<FinancialManager> = new BehaviorSubject<FinancialManager>({
    fmid: 0,
    id: 0,
    username: '',
    password: '',
    isFinancialManager: false,
    firstname: '',
    middleInitial: '',
    lastname: '',
    phone: 0,
    email: ''
  });

  constructor() { }

}
