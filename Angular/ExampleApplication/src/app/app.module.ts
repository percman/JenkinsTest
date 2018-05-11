import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { MyRoutes } from '../shared/app-routes';

import { AppComponent } from './app.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { DataBindingComponent } from './components/data-binding/data-binding.component';
import { ExampleService } from './services/example-service/example.service';
import { HttpExampleComponent } from './components/http-example/http-example.component';



@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    DataBindingComponent,
    HttpExampleComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(MyRoutes), 
    FormsModule,
    HttpClientModule
  ],
  providers: [ExampleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
