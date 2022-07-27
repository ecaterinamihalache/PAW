import { Student } from '../models/Student';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Account } from '../models/Account';
import { Preferences } from '../models/Preferences';
import { RepartitionResult } from '../models/RepartitionResult';
import { RegistrationStatus } from '../models/RegistrationStatus';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private getStudentsDetalis = "http://localhost:8081/student-management-service/accommodations/students";
  private getStudentsInfo = "http://localhost:8081/student-management-service/accommodations/students-details";
  private getStudentsAdresses = "http://localhost:8081/student-management-service/accommodations/students-addresses";
  private studentAccount = "http://localhost:8082/uac/accommodations/accounts";
  private displayPreferences = "http://localhost:8081/student-management-service/accommodations/students";
  private repartitionStatus="http://localhost:8081/student-management-service/accommodations/preferences/registration-status";


  constructor(private httpClient: HttpClient) { }

  public getAllStudents() {
    return this.httpClient.get<Student[]>(`${this.getStudentsDetalis}/accommodated`)
      .pipe(catchError(this.handleError));
  }

  public getRepartitionResult(id:number) {
    return this.httpClient.get<RepartitionResult>(`${this.getStudentsDetalis}/${id}/roommates`)
      .pipe(catchError(this.handleError));
  }

  public getRegistrationStatus() {
    return this.httpClient.get<RegistrationStatus>(`${this.repartitionStatus}`)
      .pipe(catchError(this.handleError));
  }

  public getAllStudentsDetails() {
    return this.httpClient.get<Student[]>(this.getStudentsInfo)
      .pipe(catchError(this.handleError));
  }

  public getAllStudentsAddresses() {
    return this.httpClient.get<Student[]>(this.getStudentsAdresses)
      .pipe(catchError(this.handleError));
  }

  public getStudentById(id: number) {
    return this.httpClient.get<Student>(`${this.getStudentsDetalis}/${id}`)
      .pipe(catchError(this.handleError));
  }

  public getStudentAccountById(id: number) {
    return this.httpClient.get<Account>(`${this.studentAccount}/${id}`)
      .pipe(catchError(this.handleError));
  }

  public deleteStudentById(id: number): Observable<{}> {
    return this.httpClient.delete(`${this.getStudentsDetalis}/${id}`)
      .pipe(catchError(this.handleError));
  }

  public createAccount(account: Account): Observable<Student> {
    return this.httpClient.post<Student>(`${this.studentAccount}`, account);
  }

  public addStudent(student: Student, id: number): Observable<Student> {
    return this.httpClient.post<Student>(`${this.getStudentsDetalis}/${id}`, student);
  }

  public addStudentDetails(student: Student, id: number): Observable<Student> {
    return this.httpClient.post<Student>(`${this.getStudentsDetalis}/${id}/students-details`, student);
  }

  public addStudentAddresses(student: Student, id: number): Observable<Student> {
    return this.httpClient.post<Student>(`${this.getStudentsDetalis}/${id}/addresses`, student);
  }
  // public getStudentPreferences(id:number):Observable<String> {
  //   return this.httpClient.get(`${this.displayPreferences}/${id}/preferences`, {responseType: 'text'})
  // }

  public getStudentPreferences(id:number):Observable<Preferences> {
    return this.httpClient.get<Preferences>(`${this.displayPreferences}/${id}/preferences`);
  }

   
  private handleError(httpError: HttpErrorResponse) {
    if (httpError.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', httpError.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${httpError.status}, ` +
        `body was: ${httpError.error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError('Something bad happened; please try again later.');
  }
}