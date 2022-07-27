import { Component, OnInit } from '@angular/core';
import { Student } from '../../../models/Student';
import { StudentService } from '../../../services/StudentService';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  public students: Student[];
  public studentInfo: Student[];
  public studentAddresses: Student[];

  errorMsg = null;

  constructor(private studentManager: StudentService) { }

  ngOnInit() {
    this.studentManager.getAllStudents().subscribe(
      (response) => {
        this.students = response
      })

    this.studentManager.getAllStudentsDetails().subscribe(
      (response) => {
        this.studentInfo = response
      })

    this.studentManager.getAllStudentsAddresses().subscribe(
      (response) => {
        this.studentAddresses = response
      })
  }

  onCheckOut(id: number) {
    this.studentManager.deleteStudentById(id)
      .subscribe(responseData => {
        this.studentManager.getAllStudents().subscribe(
          data => {
            this.students = data
          })
      }, error => {
        this.errorMsg = error
      });
  }
}