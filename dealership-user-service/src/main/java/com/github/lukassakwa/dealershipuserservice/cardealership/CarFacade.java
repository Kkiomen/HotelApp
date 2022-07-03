package com.github.lukassakwa.dealershipuserservice.cardealership;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import com.github.lukassakwa.dealershipuserservice.cardealership.car.service.CarService;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.service.DealershipService;
import com.github.lukassakwa.dealershipuserservice.mappers.CarMapper;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDto;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDtoSmall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarFacade {
    private final CarService carService;
    private final DealershipService dealershipService;
    private final CarMapper carMapper;

    public List<CarDto> getAll() {
        return carService.getAllCars();
    }

    public List<CarDto> getAllByDealership(Long dealershipId) {
        return carService.findAllCarsByDealership(dealershipId);
    }

    public Car addDealership(Long carId, Long dealershipId) {
        Car car = carService.findById(carId).orElseThrow(IllegalStateException::new);
        Dealership dealership = dealershipService.findById(dealershipId);

        car.setDealership(dealership);
        return carService.saveCar(car);
    }

    public Car save(Car car) {
        return carService.saveCar(car);
    }

    public CarDto saveCarWithDealership(CarDtoSmall carDto, String dealershipName) {
        Car savedCar = save(carMapper.toEntity(carDto));
        Optional<Dealership> dealership = dealershipService.findByName(dealershipName);
        if(!dealership.isPresent()) {
            throw new IllegalStateException();
        }
        addDealership(savedCar.getId(), dealership.get().getId());
        return carMapper.toDto(savedCar);
    }
}
