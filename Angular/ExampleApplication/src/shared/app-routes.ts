import { Routes } from '@angular/router';
import { WelcomeComponent } from '../app/components/welcome/welcome.component';
import { DataBindingComponent } from '../app/components/data-binding/data-binding.component';
import { HttpExampleComponent } from '../app/components/http-example/http-example.component';


export const MyRoutes: Routes = [
    { path: '', component: WelcomeComponent },
    { path: 'http-example', component: HttpExampleComponent },
    { path: 'data-binding', component: DataBindingComponent },
    { path: '**', redirectTo: '', pathMatch: "full" }
]