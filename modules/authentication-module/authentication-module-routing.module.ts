import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent} from './components/register-component/register.component';
import { LoginComponent } from './components/login-component/login.component';
import { RedirectGuard } from 'src/app/auth-guards/RedirectGuard';
import { AuthGuard } from 'src/app/auth-guards/AuthGuard';

const routes: Routes =
[
  { path: 'register', component: RegisterComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent, pathMatch: 'full', canActivate: [ AuthGuard ] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthenticationModuleRoutingModule { }
