package com.github.lukassakwa.dealershipuserservice.cardealership.dealership.service;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.repo.DealershipRepository;
import com.github.lukassakwa.dealershipuserservice.mappers.CarMapper;
import com.github.lukassakwa.dealershipuserservice.mappers.DealershipMapper;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDto;
import com.github.lukassakwa.dealershipuserservice.resources.dto.DealershipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealershipService {

    private final DealershipRepository repository;
    private final DealershipMapper mapper;
    private final CarMapper carMapper;

    public List<CarDto> getAllCars() {
        return repository.getAllCars().stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DealershipDto> getAllDealerships() {
        return repository.getAllDealerships().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Dealership save(Dealership dealership) {
        return repository.save(dealership);
    }

    public Dealership findById(Long dealershipId) {
        return repository.findById(dealershipId)
                .orElseThrow(() -> { throw new IllegalStateException("Dealership does not exitst"); } );
    }
}
