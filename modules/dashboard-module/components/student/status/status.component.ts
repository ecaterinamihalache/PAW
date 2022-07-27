import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { RegistrationStatus } from '../../../models/RegistrationStatus';
import { RepartitionResult } from '../../../models/RepartitionResult';
import { Student } from '../../../models/Student';
import { StudentService } from '../../../services/StudentService';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  public preferencesForm = new FormGroup({
    colleaguesNames: new FormControl(''),
    dormitory: new FormControl('')
  });

  public repartiton: RepartitionResult;
  public status: RegistrationStatus;
  public student: Student;
  public message: boolean = false;
  public message2: boolean = false;

  constructor(private studentManager: StudentService) {

    this.studentManager.getRegistrationStatus().subscribe(
      (response) => {
        console.log(response);
        this.status = response;
        if (this.status.registrationStatus == true) {
          this.message = true;
        }
        else {
          this.studentManager.getRepartitionResult(Number(localStorage.getItem('accountId'))).subscribe(
            (response) => {
              this.repartiton = response;
              console.log(response);
              
              let roommates: String = ""
              this.repartiton.roommates.forEach( (roommate) => {
                roommates += roommate.lastname + " " + roommate.firstname + ", "
              });
              roommates = roommates.substr(0, roommates.length-2)
              this.preferencesForm.controls['colleaguesNames'].setValue(roommates)

              this.preferencesForm.controls['dormitory'].setValue(this.repartiton.accommodation.name + ":" + this.repartiton.room.room);
                
            },
            (error)=>{
              this.message2=true;
            }
            )
        }
      }
    )
  }
  ngOnInit(): void {
  }

}
