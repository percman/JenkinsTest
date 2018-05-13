import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Employee } from '../../../shared/employee';

@Injectable()
export class EmployeeService {

  public currentEmployee: BehaviorSubject<Employee> = new BehaviorSubject<Employee>({
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
