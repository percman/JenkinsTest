import { Routes } from '@angular/router';
import {LoginComponent} from '../components/login/login.component';
import {EmployeeSplashComponent} from '../components/employee-splash/employee-splash.component';
import {CreateReimbursementComponent} from '../components/create-reimbursement/create-reimbursement.component';
import {UserInfoComponent} from '../components/user-info/user-info.component';
import {ViewEmployeesComponent} from '../components/view-employees/view-employees.component';


export const MyRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'employee-splash', component: EmployeeSplashComponent},
  { path: 'create-reimbursement', component: CreateReimbursementComponent},
  { path: 'update-info', component: UserInfoComponent},
  { path: 'view-employees', component: ViewEmployeesComponent},
  { path: '**', redirectTo: '', pathMatch: 'full'}
];
