import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {DealershipToReceive} from "../model/dealership";

const API_URL = 'http://localhost:8080/api';

const headers = new HttpHeaders()
  .set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class DealershipService {
  constructor(private http: HttpClient) { }

  getCars(): Observable<any> {
    return this.http.get(API_URL + '/cars', { responseType: 'text' });
  }

  getDealership(): Observable<any> {
    return this.http.get(API_URL + '/dealership', { responseType: 'text' });
  }

  addDealership(dealership: DealershipToReceive): Observable<any> {
    const body = JSON.stringify(dealership);
    return this.http.post(API_URL + '/dealership/save', body, {headers: headers});
  }

}
