package com.github.lukassakwa.dealershipuserservice.resources.car;

import com.github.lukassakwa.dealershipuserservice.cardealership.CarFacade;
import com.github.lukassakwa.dealershipuserservice.cardealership.car.service.CarService;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarRestController {

    private final CarFacade carFacade;

    @GetMapping(path = "/api/cars")
    public ResponseEntity<List<CarDto>> getDealershipCars() {
        return ResponseEntity.ok().body(carFacade.getAll());
    }

}
