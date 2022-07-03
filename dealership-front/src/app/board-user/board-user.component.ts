import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import {Useraccount} from "../model/useraccount";
import {TokenStorageService} from "../_services/token-storage.service";
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  content: Useraccount[] = [];
  isLoggedIn: boolean = false;
  private roles: string[] = [];
  closeResult: string = "";

  constructor(
    private userService: UserService,
    private tokenStorageService: TokenStorageService,
    private modalService: NgbModal) { }

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

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
}
