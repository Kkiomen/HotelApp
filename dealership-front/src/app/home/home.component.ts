import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import {DealershipService} from "../_services/dealership.service";
import {Dealership} from "../model/dealership";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: any;

  constructor(private dealershipService: DealershipService) { }

  ngOnInit(): void {
    this.dealershipService.getDealership().subscribe(
      data => {
        console.log(data);
        this.content = <Dealership[]>JSON.parse(data);
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

}
