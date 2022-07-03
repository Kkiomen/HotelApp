import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {CarToReceive} from "../model/car";

const headers = new HttpHeaders()
  .set('Content-Type', 'application/json');

const API_URL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class CarsService {
  constructor(private http: HttpClient) { }

  getCars(): Observable<any> {
    return this.http.get(API_URL + '/cars', { responseType: 'text' });
  }

  getDealershipCars(dealershipId: number): Observable<any> {
    return this.http.get(API_URL + '/cars/' + dealershipId, { responseType: 'text' });
  }

  addCar(car: CarToReceive): Observable<any> {
    const body = JSON.stringify(car);
    return this.http.post(API_URL + "/car/save", body, {headers: headers})
  }
}
