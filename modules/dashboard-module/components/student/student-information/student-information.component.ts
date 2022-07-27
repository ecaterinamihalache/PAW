import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Account } from '../../../models/Account';
import { Student } from '../../../models/Student';
import { StudentService } from '../../../services/StudentService';

@Component({
  selector: 'app-student-information',
  templateUrl: './student-information.component.html',
  styleUrls: ['./student-information.component.css']
})
export class StudentInformationComponent{

  public student: Student;
  public account: Account;

  public showPassword: boolean;
  
  infoForm = new FormGroup({
    userLastName: new FormControl(),
    userFirstName: new FormControl(),
    userPhone: new FormControl(),
    userEmail:new FormControl(),
    userPassword:new FormControl()
  });
  constructor(private studentManager: StudentService) {

    this.studentManager.getStudentById(Number(localStorage.getItem('accountId'))).subscribe(
      (response) => {
        this.student = response
        this.infoForm.controls['userLastName'].setValue(this.student.lastname);
        this.infoForm.controls['userFirstName'].setValue(this.student.firstname);
        this.infoForm.controls['userPhone'].setValue(this.student.phone_number);
        this.studentManager.getStudentAccountById(Number(localStorage.getItem('accountId'))).subscribe(
          (accountResponse) => {
            this.account = accountResponse;
            this.infoForm.controls['userEmail'].setValue(this.account.email);
            this.infoForm.controls['userPassword'].setValue(this.account.password);
          })
      })
  }
}
