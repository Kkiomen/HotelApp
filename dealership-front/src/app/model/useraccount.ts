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
