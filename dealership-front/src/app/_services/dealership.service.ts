import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const API_URL = 'http://localhost:8080/api';

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

}
