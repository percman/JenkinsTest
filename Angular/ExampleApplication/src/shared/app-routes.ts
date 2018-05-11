import { Routes } from '@angular/router';
import { WelcomeComponent } from '../app/components/welcome/welcome.component';
import { DataBindingComponent } from '../app/components/data-binding/data-binding.component';


export const MyRoutes: Routes = [
    { path: '', component: WelcomeComponent },
    { path: 'data-binding', component: DataBindingComponent} 
]