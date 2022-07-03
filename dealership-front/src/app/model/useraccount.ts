import {Userlogin} from "./userlogin";
import {RoleDto} from "./role";

export class Useraccount {
  public username: string;
  public email: string;
  public phone: string;
  public position: string;

  constructor(username: string, password: string, email: string, phone: string, position: string) {
    this.username = username;
    this.email = email;
    this.phone = phone;
    this.position = position;
  }
}

export class FullUser extends Useraccount {
  public password: string;
  public role: RoleDto[];

  constructor(username: string, password: string, email: string, phone: string, position: string, role: RoleDto[]) {
    super(username, password, email, phone, position);
    this.password = password;
    this.role = role;
  }
}
