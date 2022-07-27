import { Component } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { Router } from "@angular/router";
import { TokenAndClaims } from "../../models/TokenAndClaims";
import { UserCredentials } from "../../models/UserCredentials";
import { UserAccessControlService } from "../../services/UserAccessControlService";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm = new FormGroup({
    userEmail: new FormControl(''),
    userPassword: new FormControl('')
  });

  constructor(private authenticationService: UserAccessControlService, private router: Router) {
  }

  onSubmit(): void {

    var userCredentials = new UserCredentials(this.loginForm.get('userEmail')?.value, this.loginForm.get('userPassword')?.value)
    this.authenticationService.authenticateUser(userCredentials).subscribe(
      (tokenAndClaims: TokenAndClaims) => { 
        localStorage.setItem('token', tokenAndClaims.token)
        localStorage.setItem('accountId', tokenAndClaims.accountId.toString())
        localStorage.setItem('role', tokenAndClaims.role)
        
        if(tokenAndClaims.role == 'USER')
          this.router.navigate(['/dashboard/student-panel/information'])
        else
          this.router.navigate(['/dashboard/admin-panel/home'])
      },
      (error) => { alert("Invalid credentials!") }
    )

  }
}

