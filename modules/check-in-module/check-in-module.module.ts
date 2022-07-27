import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule} from "@angular/forms";
import { CheckInModuleRoutingModule } from './check-in-module-routing.module';
import { AccommodationFormComponent } from './components/accommodation-form/accommodation-form.component';
import { HttpClientModule } from '@angular/common/http';
import { PublicApiService } from './services/PublicApiService';
import { StudentService } from '../dashboard-module/services/StudentService';

@NgModule({
  declarations: [
    AccommodationFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CheckInModuleRoutingModule,
    HttpClientModule
  ],
  exports: [
  ],
  providers: [ PublicApiService, StudentService ]
})
export class CheckInModuleModule { }
