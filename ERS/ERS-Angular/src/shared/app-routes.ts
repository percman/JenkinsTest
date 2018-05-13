import { Routes } from '@angular/router';

import { LoginComponent } from '../app/components/login/login.component';
import { HomePageComponent } from '../app/components/home-page/home-page.component';

export const MyRoutes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home-page', component: HomePageComponent},
    { path: '**', redirectTo: '', pathMatch: "full" }
]