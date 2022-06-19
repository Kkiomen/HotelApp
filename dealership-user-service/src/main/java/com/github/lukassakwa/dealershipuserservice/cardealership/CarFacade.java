package com.github.lukassakwa.dealershipuserservice.cardealership;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import com.github.lukassakwa.dealershipuserservice.cardealership.car.service.CarService;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.service.DealershipService;
import com.github.lukassakwa.dealershipuserservice.mappers.CarMapper;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarFacade {
    private final CarService carService;
    private final DealershipService dealershipService;

    public List<CarDto> getAll() {
        return carService.getAllCars();
    }

    public Car addDealership(Long carId, Long dealershipId) {
        Car car = carService.findById(carId);
        Dealership dealership = dealershipService.findById(dealershipId);

        car.setDealership(dealership);
        return carService.saveCar(car);
    }

    public Car save(Car car) {
        return carService.saveCar(car);
    }
}
