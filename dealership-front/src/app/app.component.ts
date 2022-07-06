import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showUserBoard = false;
  showCarBoard = false;
  username?: string;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showUserBoard =
        this.roles.includes('ROLE_USER') ||
        this.roles.includes('ROLE_ADMIN') ||
        this.roles.includes('ROLE_SUPER_ADMIN') ||
        this.roles.includes('ROLE_MODERATOR');
      this.showCarBoard =
        this.roles.includes('ROLE_SUPER_ADMIN') ||
        this.roles.includes('ROLE_ADMIN');

      this.username = user.sub;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
