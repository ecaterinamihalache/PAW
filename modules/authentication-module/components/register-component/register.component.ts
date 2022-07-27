import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from 'src/app/modules/dashboard-module/models/Account';
import { AdminService } from 'src/app/modules/dashboard-module/services/AdminService';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent {

  public registerForm;
  account: Account = new Account();
  formIsValid: boolean | undefined = false;

  constructor(private adminService: AdminService, private readonly router: Router) {
    this.registerForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      phone: new FormControl('', Validators.required),
      password1: new FormControl('', Validators.required),
      password2: new FormControl('', Validators.required)
    });
  }

  onSubmit(): void {
    this.account.email = this.registerForm.value.email;
    this.account.password = this.registerForm.value.password1;
    this.account.role = "ADMIN";
    this.account.isActive = true;

    this.updateFormValidity();

    if (this.formIsValid) {
      this.router.navigateByUrl('/authentication/login');
    } else {
      return;
    }

    this.save();
  }

  save() {
    this.adminService.createAccount(this.account).subscribe(
      (account) => {
        console.log(account);
      }
    )
  }

  private updateFormValidity(): void {
    this.formIsValid = this.registerForm.get('firstName')?.valid &&
    this.registerForm.get('lastName')?.valid &&
    this.registerForm.get('email')?.valid &&
    this.registerForm.get('phone')?.valid;

    if ((this.registerForm.get('password1')?.value !== this.registerForm.get('password2')?.value) || (!this.registerForm.get('password1')?.value || !this.registerForm.get('password2'))) {
      this.registerForm?.get('password1')?.setErrors({ 'invalid': true });
      this.registerForm?.get('password2')?.setErrors({ 'invalid': true });
      this.formIsValid = false;
    } else {
      this.registerForm?.get('password1')?.setErrors({ 'invalid': null });
      this.registerForm?.get('password2')?.setErrors({ 'invalid': null });
      this.formIsValid = this.formIsValid && true;
    }
  }
}
