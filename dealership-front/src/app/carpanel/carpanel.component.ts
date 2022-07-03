import { Component, OnInit } from '@angular/core';
import {DealershipService} from "../_services/dealership.service";
import {CarDtoSmall, Dealership} from "../model/dealership";
import {CarsService} from "../_services/cars.service";
import {Car, CarToReceive, DealershipSmall} from "../model/car";

@Component({
  selector: 'app-home',
  templateUrl: './carpanel.component.html',
  styleUrls: ['./carpanel.component.css']
})
export class CarpanelComponent implements OnInit {
  car: CarToReceive = new CarToReceive(new CarDtoSmall(0, '', '', '', '', 0,
    0, '', 0, '', '', 0,
    0, '', '', 0), '');

  constructor(
    private carsService: CarsService
  ) {
  }

  ngOnInit(): void {

  }

  addCar() {
    this.carsService.addCar(this.car).subscribe(value => value);
  }
}
