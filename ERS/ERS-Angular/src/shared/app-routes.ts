import { Routes } from '@angular/router';

export const MyRoutes: Routes = [
    { path:'**', redirectTo: '', pathMatch: "full" }
]