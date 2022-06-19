package com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dealership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dealership_id")
    private Long id;
    private String name;
    private String image;
    private String address;
    private String openHours;
    @OneToMany(mappedBy="dealership", fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();
    private String contact;

    public void addCar(Car car) {
        if (cars.isEmpty()) {
            cars = new ArrayList<>();
        }
        cars.add(car);
    }

}
