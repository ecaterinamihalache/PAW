import { Component, OnInit } from '@angular/core';
import { Student } from '../../../models/Student';
import { StudentService } from '../../../services/StudentService';
import { Router } from "@angular/router";

@Component({
  selector: 'app-second-panel',
  templateUrl: './student-panel.component.html',
  styleUrls: ['./student-panel.component.css']
})
export class StudentPanelComponent implements OnInit {

  public student: Student;

  constructor(private studentManager: StudentService, private router: Router) {
    this.student = new Student()
  }

  ngOnInit(): void {
    this.studentManager.getStudentById(Number(localStorage.getItem('accountId'))).subscribe(
      (response) => {
        this.student = response;
       }
    )
  }

  public onLogout():void{
    localStorage.clear();
    this.router.navigate(['/']);
  }

}
