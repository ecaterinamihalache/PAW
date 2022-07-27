import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPanelComponent } from './components/admin/admin-panel/admin-panel.component';
import { StudentPanelComponent } from './components/student/student-panel/student-panel.component';
import { StudentsComponent } from './components/admin/students/students.component';
import { PreferencesComponent } from './components/student/preferences/preferences.component';
import { ChooseDormitoryComponent } from './components/student/choose-dormitory/choose-dormitory.component';
import { StatusComponent } from './components/student/status/status.component';
import { PaymentComponent } from './components/student/payment/payment.component';
import { StudentInformationComponent } from './components/student/student-information/student-information.component';
import { AuthGuard } from 'src/app/auth-guards/AuthGuard';
import { AdminGuard } from 'src/app/auth-guards/AdminGuard';
import { StudentGuard } from 'src/app/auth-guards/StudentGuard';
import { HomePageComponent } from './components/admin/home-page/home-page.component';
import { StartRepartitionComponent } from './components/admin/start-repartition/start-repartition.component';

const routes: Routes = [
  {
    path: 'admin-panel', component: AdminPanelComponent,
    children: [
      { path: 'students', component: StudentsComponent },
      { path: 'home', component: HomePageComponent },
      { path: 'repartition', component: StartRepartitionComponent }
    ],
    canActivate: [ AuthGuard, AdminGuard ]
  },
  {
    path: 'student-panel', component: StudentPanelComponent,
    children: [
      { path: 'choose-dormitory', component: ChooseDormitoryComponent },
      { path: 'preferences', component: PreferencesComponent },
      { path: 'status', component: StatusComponent },
      { path: 'payment', component: PaymentComponent },
      { path: 'information', component: StudentInformationComponent }
    ],
    canActivate: [ AuthGuard, StudentGuard ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardModuleRoutingModule { }