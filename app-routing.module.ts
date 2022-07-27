import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth-guards/AuthGuard';
import { RedirectGuard } from './auth-guards/RedirectGuard';
import { LoginComponent } from './modules/authentication-module/components/login-component/login.component';
import { DashboardModuleModule } from './modules/dashboard-module/dashboard-module.module';
import { UnauthorizedAccessComponent } from './unauthorized-access/unauthorized-access.component';

const routes: Routes =
[
  {
    path: '', redirectTo: 'authentication/login', pathMatch: 'full'
  },
  {
    path: 'authentication',
    loadChildren: () => import('./modules/authentication-module/authentication-module.module').then(mod => mod.AuthenticationModuleModule)
  },
  {
    path: 'dashboard',
    loadChildren: () => import('./modules/dashboard-module/dashboard-module.module').then(mod => mod.DashboardModuleModule)
  },
  {
    path: 'accommodation-form',
    loadChildren: () => import('./modules/check-in-module/check-in-module.module').then(mod => mod.CheckInModuleModule)
  },
  { 
    path: 'unauthorized-access', component: UnauthorizedAccessComponent, pathMatch: 'full'
  },
  { 
    path: '**', redirectTo: '', canActivate: [ RedirectGuard ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
