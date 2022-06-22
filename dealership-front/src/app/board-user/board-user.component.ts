import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import {Useraccount} from "../model/useraccount";
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  content: Useraccount[] = [];
  isLoggedIn: boolean = false;
  private roles: string[] = [];

  constructor(
    private userService: UserService,
    private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
    }
    this.userService.getUserBoard().subscribe(
      data => {
        this.content = <Useraccount[]>JSON.parse(data);
        console.log(this.content);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

  isAdmin() : boolean {
    return this.roles.includes('ROLE_ADMIN') ||
        this.roles.includes('ROLE_SUPER_ADMIN');
  }

  isMod() : boolean {
    return  this.roles.includes('ROLE_SUPER_ADMIN') ||
      this.roles.includes('ROLE_ADMIN') ||
      this.roles.includes('ROLE_MODERATOR');
  }
}
