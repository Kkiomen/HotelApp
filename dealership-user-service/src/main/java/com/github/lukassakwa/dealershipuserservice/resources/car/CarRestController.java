package com.github.lukassakwa.dealershipuserservice.resources.car;

import com.github.lukassakwa.dealershipuserservice.cardealership.CarFacade;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDto;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarToReceive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarRestController {

    private final CarFacade carFacade;

    @GetMapping(path = "/api/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok().body(carFacade.getAll());
    }

    @GetMapping(path = "/api/cars/{dealershipId}")
    public ResponseEntity<List<CarDto>> getDealershipCars(@PathVariable Long dealershipId) {
        return ResponseEntity.ok().body(carFacade.getAllByDealership(dealershipId));
    }

    @PostMapping("/api/car/save")
    public ResponseEntity<CarDto> saveCar(@RequestBody CarToReceive carToReceive) {
        return ResponseEntity.ok().body(carFacade.saveCarWithDealership(carToReceive.getCarDtoSmall(), carToReceive.getDealership()));
    }
}
