import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from "./auth.service";
import {TokenStorageService} from "./token-storage.service";

const API_URL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + '/users', { responseType: 'text' });
  }

}
