import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CarsService} from "../_services/cars.service";
import {Car} from "../model/car";

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.scss']
})
export class CarComponent implements OnInit {

  id: string | null = '';
  dealershipCars: Car[] = [];

  constructor(
    private activatedroute: ActivatedRoute,
    private carsService: CarsService) { }

  ngOnInit(): void {
    this.id = this.activatedroute.snapshot.paramMap.get("id");
    this.carsService.getDealershipCars(Number(this.id)).subscribe(data => {
      this.dealershipCars = <Car[]>JSON.parse(data);
    })
  }

}
