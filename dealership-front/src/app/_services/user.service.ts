import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from "./auth.service";
import {TokenStorageService} from "./token-storage.service";
import {FullUser} from "../model/useraccount";
import {RoleDto} from "../model/role";
import {UserRoleDto} from "../model/roleUserDto";

const API_URL = 'http://localhost:8080/api';

const headers = new HttpHeaders()
  .set('Content-Type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + '/users', { responseType: 'text' });
  }

  deleteUser(username: string): Observable<any> {
    return this.http.delete(API_URL + '/user/delete', { body: username });
  }

  addUser(user: FullUser): Observable<any> {
    const body = JSON.stringify(user);
    return this.http.post(API_URL + '/user/add', body, {headers: headers});
  }

  addRoleUser(role: UserRoleDto) {
    const body = JSON.stringify(role);
    this.http.post(API_URL + '/addToUser', body, {headers: headers})
      .subscribe(value => value);
  }

}
