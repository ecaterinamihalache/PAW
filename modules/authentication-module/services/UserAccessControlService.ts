import { HttpClient, HttpHeaders } from "@angular/common/http";
import { BoundText } from "@angular/compiler/src/render3/r3_ast";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { Country } from "../../check-in-module/models/Country";
import { Token } from "../models/Token";
import { TokenAndClaims } from "../models/TokenAndClaims";
import { UserCredentials } from "../models/UserCredentials";

@Injectable({
    providedIn: 'root',
  })
  // Clasa pentru a face cereri http la endpoint-urile api-ului user access control
  export class UserAccessControlService {
  
    // Url-urile la care se fac cererile
    private authenticationUrl = "http://localhost:8082/uac/authenticate"
    private authorizationUrl = "http://localhost:8082/uac/validate-token"
    
    // Antetul pentru cererile post
    private httpOptions = {
      headers: new HttpHeaders({ "Content-Type": "application/json" })
    };

    constructor(private httpClient: HttpClient) {  }

    public authenticateUser(userCredentials: UserCredentials): Observable<TokenAndClaims> {
        return this.httpClient.post<TokenAndClaims>(this.authenticationUrl, userCredentials, this.httpOptions);            
    }

    public authorizeUser(token: Token): Observable<any> {
      return this.httpClient.post<any>(this.authorizationUrl, token, this.httpOptions);            
    }

}