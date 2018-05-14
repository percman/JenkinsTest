import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {MyRoutes} from './shared/app.routes';
import {Globals} from './shared/Globals';
import { EmployeeSplashComponent } from './components/employee-splash/employee-splash.component';
import { CreateReimbursementComponent } from './components/create-reimbursement/create-reimbursement.component';
import { UserInfoComponent } from './components/user-info/user-info.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EmployeeSplashComponent,
    CreateReimbursementComponent,
    UserInfoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(MyRoutes)
  ],
  providers: [Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
