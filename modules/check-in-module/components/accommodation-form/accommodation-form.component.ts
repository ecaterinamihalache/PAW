import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { PublicApiService } from '../../services/PublicApiService';
import { Country } from '../../models/Country';
import { County } from '../../models/County';
import { City } from '../../models/City';
import { Student } from 'src/app/modules/dashboard-module/models/Student';
import { StudentService } from 'src/app/modules/dashboard-module/services/StudentService';
import { Account } from 'src/app/modules/dashboard-module/models/Account';

@Component({
  selector: 'app-accommodation-form',
  templateUrl: './accommodation-form.component.html',
  styleUrls: ['./accommodation-form.component.css']
})
export class AccommodationFormComponent implements OnInit {

  public accommodationForm;

  public countries: Country[]
  public counties: County[]
  public cities: City[]

  contactForm!: FormGroup;

  account: Account = new Account();
  student: Student = new Student();

  constructor(private fb: FormBuilder, private apiRequestManager: PublicApiService, private studentService: StudentService) {
    this.accommodationForm = new FormGroup({
      nume: new FormControl(''),
      prenume: new FormControl(''),
      nr_telefon: new FormControl(''),
      email: new FormControl(''),
      parola: new FormControl(''),

      universitate: new FormControl(''),
      facultate: new FormControl(''),
      specializare: new FormControl(''),
      an: new FormControl(''),

      tara: new FormControl(''),
      judet: new FormControl(''),
      oras: new FormControl(''),
      strada: new FormControl(''),
      cod_postal: new FormControl('')
    });
  }

  // La init se face cerere pentru tari
  ngOnInit() {
    this.apiRequestManager.getCountries().subscribe(
      (response) => {
        this.countries = response
      }
    )
  }

  // La selectarea unei tari se intra in aceasta functie care face cerere pentru judetele tarii alese
  onCountryChange(event: any): void {
    this.apiRequestManager.getCountryCounties(event.value).subscribe(
      (response) => {
        this.counties = response
      },
      // Daca nu exista tara, atunci se pune lista de judete pe []
      (error) => {
        this.counties = []
      }
    )
  }

  // La selectarea unui judet se intra in aceasta functie care face cerere pentru orasele judetului ales
  onCountyChange(event: any): void {
    this.apiRequestManager.getCountyCities(this.accommodationForm.get('tara')?.value, event.value).subscribe(
      (response) => {
        this.cities = response
      },
      // Daca nu exista judetul, atunci se pune lista de orase pe []
      (error) => {
        this.cities = []
      }
    )
  }

  onSubmit(): void {
    this.account.email = this.accommodationForm.value.email;
    this.account.password = this.accommodationForm.value.parola;
    this.account.isActive = true;
    this.account.role = "USER";
    this.student.lastname = this.accommodationForm.value.nume;
    this.student.firstname = this.accommodationForm.value.prenume;
    this.student.phone_number = this.accommodationForm.value.nr_telefon;
    this.student.year_grade = 9.8;
    this.student.university = this.accommodationForm.value.universitate;
    this.student.faculty = this.accommodationForm.value.facultate;
    this.student.specialization = this.accommodationForm.value.specializare;

    this.student.city = this.accommodationForm.value.oras;
    this.student.county = this.accommodationForm.value.judet;
    this.student.country = this.accommodationForm.value.tara;
    this.student.street_name = this.accommodationForm.value.strada;
    this.student.postal_code = this.accommodationForm.value.cod_postal;

    this.save();
  }

  save() {
    this.studentService.createAccount(this.account).subscribe
      (account => {
        this.student.id = account.id
        this.studentService.addStudent(this.student, account.id).subscribe(
          data => {
            console.log(data);
          }
        )
        this.studentService.addStudentDetails(this.student, account.id).subscribe(
          detailsData => {
            console.log(detailsData);
          }
        )
        this.studentService.addStudentAddresses(this.student, account.id).subscribe(
          addressesData => {
            console.log(addressesData);
          }
        )
        console.log(this.account);
      });
  }
}
