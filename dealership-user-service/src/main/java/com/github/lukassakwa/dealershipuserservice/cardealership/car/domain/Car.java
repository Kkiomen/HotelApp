package com.github.lukassakwa.dealershipuserservice.cardealership.car.domain;

import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private Long id;
    private String brand;
    private String model;
    private String image;
    @Enumerated(EnumType.STRING)
    private CarCategory category;
    private Date productionYear;
    private Integer displacement;
    private Long mileage;
    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    private Integer power;
    @Enumerated(EnumType.STRING)
    private CarGearbox carGearbox;
    //Do zmiany
    @Enumerated(EnumType.STRING)
    private CarBodyType carBodyType;
    private Integer doorsNumber;
    private Integer seatsNumber;
    private String color;
    private String VIN;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "dealership_id")
    private Dealership dealership;
}
