import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {User} from "../model/user";

const AUTH_API = 'http://localhost:8080';

const headers = new HttpHeaders()
    .set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(user: User): Observable<any> {
    const body = JSON.stringify(user);
    return this.http.post(AUTH_API + '/login', body , {'headers': headers});
  }

  register(user: User): Observable<any> {
    const body = JSON.stringify(user);
    return this.http.post(AUTH_API + '/register', body, {'headers': headers});
  }

}
