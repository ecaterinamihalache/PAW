import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccommodationFormComponent } from './components/accommodation-form/accommodation-form.component';

const routes: Routes = [
  { 
    path: '', 
    component: AccommodationFormComponent, 
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CheckInModuleRoutingModule { }
