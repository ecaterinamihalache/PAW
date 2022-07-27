import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, pipe } from 'rxjs';
import { map, mapTo } from 'rxjs/operators';
import { Country } from '../models/Country';
import { City } from '../models/City';
import { County } from '../models/County';

@Injectable({
  providedIn: 'root',
})
// Clasa pentru a face cereri http la endpoint-urile api-ului public
export class PublicApiService {

  // Url-urile la care se face cererile
  private getCountriesUrl = "https://countriesnow.space/api/v0.1/countries/info?returns=name"
  private getCountiesUrl = "https://countriesnow.space/api/v0.1/countries/states"
  private getCitiesUrl = "https://countriesnow.space/api/v0.1/countries/state/cities"
  
  // Antetul pentru cererile post
  private httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" })
  };

  // Constructor ce are injectat o dependinta a clasei HttpClient
  // HttpClient este folosit pentru a face cererile
  constructor(private httpClient: HttpClient) {  }

  // Cerere get pentru a prelua tarile
  public getCountries(): Observable<Country[]> {
    return this.httpClient.get<any>(this.getCountriesUrl)
    // In acest pipe se proceseaza json-ul primit astfel: se intra in campul data din json unde se afla o lista de tari,
    // iar aceasta lista de tari, se converteste la o lista de obiecte Country 
    .pipe(map((response: any) => response.data.map( (item: any) => new Country(item.name) ) ) );            
  }

  // Cerere post pentru a prelua judetele
  public getCountryCounties(countryName : string): Observable<County[]> {
    return this.httpClient.post<any>(this.getCountiesUrl, { "country": countryName }, this.httpOptions)
    .pipe(map((response: any) => response.data.states.map( (item: any) => new County(item.name) ) ) );
  }

  // Cerere post pentru a prelua orasele
  public getCountyCities(countryName: string, countyName: string): Observable<City[]> {
    return this.httpClient.post<any>(this.getCitiesUrl, { "country": countryName, "state": countyName }, this.httpOptions)
    .pipe(map((response: any) => response.data.map( (item: string) => new City(item) ) ) );
  }
}