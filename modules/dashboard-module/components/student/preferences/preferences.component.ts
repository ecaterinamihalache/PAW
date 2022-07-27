import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { StudentService } from 'src/app/modules/dashboard-module/services/StudentService';
import { Preferences } from '../../../models/Preferences';

@Component({
  selector: 'app-preferences',
  templateUrl: './preferences.component.html',
  styleUrls: ['./preferences.component.css']
})
export class PreferencesComponent  {

  public preferences:Preferences;
  public preferencesForm;

  constructor(private studentService:StudentService) {
    this.preferencesForm = new FormGroup({
      colleaguesNames: new FormControl(''),
      dormitory: new FormControl('')
    });

    this.studentService.getStudentPreferences(Number(localStorage.getItem('accountId'))).subscribe(
      (response)=>{
        this.preferences=response;
        this.preferencesForm.controls['colleaguesNames'].setValue(this.preferences.roommates);
        this.preferencesForm.controls['dormitory'].setValue(this.preferences.rooms);
      }
    )
   }
}
