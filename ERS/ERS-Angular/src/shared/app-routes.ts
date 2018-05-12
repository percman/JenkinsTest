import { Routes } from '@angular/router';

import { LoginComponent } from '../app/components/login/login.component';

export const MyRoutes: Routes = [
    { path: '', component: LoginComponent },
    { path: '**', redirectTo: '', pathMatch: "full" }
]