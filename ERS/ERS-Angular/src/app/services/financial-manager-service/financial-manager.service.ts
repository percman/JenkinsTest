import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { FinancialManager } from '../../../shared/financial-manager';

@Injectable()
export class FinancialManagerService {

  public currentFinMan: BehaviorSubject<FinancialManager> = new BehaviorSubject<FinancialManager>(null);

  constructor() { }

}
