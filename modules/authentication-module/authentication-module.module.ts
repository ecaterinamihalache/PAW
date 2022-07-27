import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule} from "@angular/forms";
import { AuthenticationModuleRoutingModule } from './authentication-module-routing.module';
import { RegisterComponent } from './components/register-component/register.component';
import { LoginComponent } from './components/login-component/login.component';
import { UserAccessControlService } from './services/UserAccessControlService';
import { HttpClient } from '@angular/common/http';
import { AuthGuard } from 'src/app/auth-guards/AuthGuard';


@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    AuthenticationModuleRoutingModule,
    ReactiveFormsModule
  ],
  providers: [ AuthGuard ]
})
export class AuthenticationModuleModule { }
