import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule} from "@angular/forms";

import { DashboardModuleRoutingModule } from './dashboard-module-routing.module';
import { AdminPanelComponent } from './components/admin/admin-panel/admin-panel.component';
import { StudentPanelComponent } from './components/student/student-panel/student-panel.component';
import { StudentsComponent } from './components/admin/students/students.component';
import { PreferencesComponent } from './components/student/preferences/preferences.component';
import { ChooseDormitoryComponent } from './components/student/choose-dormitory/choose-dormitory.component';
import { StatusComponent } from './components/student/status/status.component';
import { PaymentComponent } from './components/student/payment/payment.component';
import { HttpClientModule } from '@angular/common/http';
import { StudentService } from './services/StudentService';
import { AdminService } from './services/AdminService';
import { StudentInformationComponent } from './components/student/student-information/student-information.component';
import { AuthGuard } from 'src/app/auth-guards/AuthGuard';
import { AdminGuard } from 'src/app/auth-guards/AdminGuard';
import { StudentGuard } from 'src/app/auth-guards/StudentGuard';
import { HomePageComponent } from './components/admin/home-page/home-page.component';
import { StartRepartitionComponent } from './components/admin/start-repartition/start-repartition.component';

@NgModule({
  declarations: [
    AdminPanelComponent,
    StudentPanelComponent,
    StudentsComponent,
    PreferencesComponent,
    ChooseDormitoryComponent,
    StatusComponent,
    PaymentComponent,
    StudentInformationComponent,
    HomePageComponent,
    StartRepartitionComponent
  ],
  imports: [
    CommonModule,
    DashboardModuleRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers:[ StudentService, AdminService, AuthGuard, AdminGuard, StudentGuard ]
})
export class DashboardModuleModule { }