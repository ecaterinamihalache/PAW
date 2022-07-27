import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Account } from '../models/Account';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private adminAccount = "http://localhost:8082/uac/accommodations/accounts";
  private startRepartition="http://localhost:8081/student-management-service/accommodations/preferences/start-repartition";

  constructor(private httpClient: HttpClient) { }

  public getAdminById(id: number) {
    return this.httpClient.get<Account>(`${this.adminAccount}/${id}`)
      .pipe(catchError(this.handleError));
  }

  public createAccount(account: Account): Observable<Account> {
    return this.httpClient.post<Account>(`${this.adminAccount}`, account);
  }

  public startRepartitionProcess() {
    return this.httpClient.post(this.startRepartition,{ });
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