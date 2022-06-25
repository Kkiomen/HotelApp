package com.github.lukassakwa.dealershipuserservice.cardealership;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import com.github.lukassakwa.dealershipuserservice.cardealership.car.service.CarService;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.service.DealershipService;
import com.github.lukassakwa.dealershipuserservice.resources.dto.DealershipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealershipFacade {

    private final CarService carService;
    private final DealershipService dealershipService;

    public List<DealershipDto> getAll() {
        return dealershipService.getAllDealerships();
    }

    public Dealership addCar(Long dealershipId, Long carId) {
        Dealership dealership = dealershipService.findById(dealershipId);
        Car car = carService.findById(carId);

        dealership.addCar(car);
        return dealershipService.save(dealership);
    }

    public Dealership save(Dealership dealership) {
        return dealershipService.save(dealership);
    }
}
