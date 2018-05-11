import { Routes } from '@angular/router';
import { WelcomeComponent } from '../app/components/welcome/welcome.component';
import { DatabindingComponent } from '../app/components/databinding/databinding.component';

export const MyRoutes: Routes = [
    { path: '' , component: WelcomeComponent},
    { path: 'data-binding', component: DatabindingComponent}
]