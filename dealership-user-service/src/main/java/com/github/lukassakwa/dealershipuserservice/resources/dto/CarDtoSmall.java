package com.github.lukassakwa.dealershipuserservice.resources.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CarDtoSmall {
    private Long id;
    private String brand;
    private String model;
    private String category;
    private Date productionYear;
    private Integer displacement;
    private Long mileage;
    private String fuel;
    private Integer power;
    private String carGearbox;
    private String carBodyType;
    private Integer doorsNumber;
    private Integer seatsNumber;
    private String color;
    private String VIN;
    private Long price;
}
