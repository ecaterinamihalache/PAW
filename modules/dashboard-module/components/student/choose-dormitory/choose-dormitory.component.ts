import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { StudentService } from '../../../services/StudentService';

@Component({
  selector: 'app-choose-dormitory',
  templateUrl: './choose-dormitory.component.html',
  styleUrls: ['./choose-dormitory.component.css']
})
export class ChooseDormitoryComponent implements OnInit {

  public preferencesForm;
  
  constructor(private studentService: StudentService) {
    this.preferencesForm = new FormGroup({
      firstColleague: new FormControl(''),
      secondColleague: new FormControl(''),
      thirdColleague: new FormControl(''),
      firstDormitory: new FormControl(''),
      secondDormitory: new FormControl(''),
      thirdDormitory: new FormControl(''),
      firstRoom: new FormControl(''),
      secondRoom: new FormControl(''),
      thirdRoom: new FormControl('')
    });
   }
   
  ngOnInit(): void {
  }

  onSubmit():void{
  }
}
