import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { AuthenticationModuleModule } from "../../authentication-module.module";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AuthenticationModuleModule,
    ReactiveFormsModule
  ]
})
export class LoginModule{
  
}
