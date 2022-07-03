import { Component, OnInit } from '@angular/core';
import {DealershipService} from "../_services/dealership.service";
import {DealershipToReceive} from "../model/dealership";

@Component({
  selector: 'app-home',
  templateUrl: './dealershippanel.component.html',
  styleUrls: ['./dealershippanel.component.css']
})
export class DealershippanelComponent implements OnInit {
  dealership: any;

  constructor(private dealershipService: DealershipService) {
      this.dealership = new DealershipToReceive(0, '', '', '', '', '');
  }

  ngOnInit(): void {

  }

  addDealership() {
    this.dealershipService.addDealership(this.dealership).subscribe(value => value);
  }
}
