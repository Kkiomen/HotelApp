export class CarDtoSmall {
  public id: number;
  public brand: string;
  public model: string;
  public category: string;
  public image: string;
  public productionYear: string;
  public displacement: number;
  public mileage: number;
  public fuel: string;
  public power: number;
  public carGearbox: string;
  public carBodyType: string;
  public doorsNumber: number;
  public seatsNumber: number;
  public color: string;
  public VIN: string;
  public price: number;


  constructor(id: number, brand: string, model: string, category: string, image: string, productionYear: string, displacement: number, mileage: number, fuel: string, power: number, carGearbox: string, carBodyType: string, doorsNumber: number, seatsNumber: number, color: string, VIN: string, price: number) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.category = category;
    this.image = image;
    this.productionYear = productionYear;
    this.displacement = displacement;
    this.mileage = mileage;
    this.fuel = fuel;
    this.power = power;
    this.carGearbox = carGearbox;
    this.carBodyType = carBodyType;
    this.doorsNumber = doorsNumber;
    this.seatsNumber = seatsNumber;
    this.color = color;
    this.VIN = VIN;
    this.price = price;
  }
}

export class Dealership {
  public id: number;
  public name: string;
  public image: string;
  public address: string;
  public openHours: string;
  public contact: string;
  public cars: CarDtoSmall[];


  constructor(id: number, name: string, image: string, address: string, openHours: string, contact: string, cars: CarDtoSmall[]) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.address = address;
    this.openHours = openHours;
    this.contact = contact;
    this.cars = cars;
  }
}

export class DealershipToReceive {
  public id: number;
  public name: string;
  public image: string;
  public address: string;
  public openHours: string;
  public contact: string;


  constructor(id: number, name: string, image: string, address: string, openHours: string, contact: string) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.address = address;
    this.openHours = openHours;
    this.contact = contact;
  }
}
