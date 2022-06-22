export class Userlogin {
  public username: string;
  public password: string;
  public email: string;
  public phone: number;
  public position: string;


  constructor(username: string, password: string, email: string, phone: number, position: string) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phone = phone;
    this.position = position;
  }
}
