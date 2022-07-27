import { Component } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { RegistrationStatus } from '../../../models/RegistrationStatus';
import { RepartitionResult } from '../../../models/RepartitionResult';
import { StudentService } from '../../../services/StudentService';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent {

  public message: boolean = false;
  public message2: boolean = false;
  public repartiton: RepartitionResult;
  public status:RegistrationStatus;

  informationForm = new FormGroup({
    userDormitory: new FormControl(),
    userRoom: new FormControl()
  });

  constructor(private studentManager: StudentService) {
    this.studentManager.getRegistrationStatus().subscribe(
      (response)=>{
        console.log(response);
        this.status=response;
        if(this.status.registrationStatus==true){
          this.message2=true;
        }
        else{

    this.studentManager.getRepartitionResult(Number(localStorage.getItem('accountId'))).subscribe(
      (response) => {
        this.repartiton = response;
        this.informationForm.controls['userRoom'].setValue(this.repartiton.room.room);
        this.informationForm.controls['userDormitory'].setValue(this.repartiton.accommodation.name);
      })
    }
  }
)
}

  ngOnInit(): void {
  }

  public showMessage() {
    this.message = true;
  }
}
