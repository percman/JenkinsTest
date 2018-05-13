import { Routes } from '@angular/router';
import {LoginComponent} from '../components/login/login.component';
import {EmployeeSplashComponent} from '../components/employee-splash/employee-splash.component';


export const MyRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'employee-splash', component: EmployeeSplashComponent},
  { path: '**', redirectTo: '', pathMatch: 'full'}
];
