import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { MyRoutes } from '../shared/app-routes';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LoginService } from './services/login-service/login.service';
import { HomePageComponent } from './components/home-page/home-page.component';
import { IsLoggedInService } from './services/is-logged-in-service/is-logged-in.service';
import { FinancialManagerService } from './services/financial-manager-service/financial-manager.service';
import { ReimbursementService } from './services/reimbursement-service/reimbursement.service';
import { EmployeeService } from './services/employee-service/employee.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(MyRoutes), 
    FormsModule,
    HttpClientModule
  ],
  providers: [
    LoginService,
    IsLoggedInService,
    EmployeeService,
    FinancialManagerService,
    ReimbursementService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
