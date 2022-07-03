package com.github.lukassakwa.dealershipuserservice.mappers;

import com.github.lukassakwa.dealershipuserservice.cardealership.car.domain.Car;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDto;
import com.github.lukassakwa.dealershipuserservice.resources.dto.CarDtoSmall;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toDto(Car car);

    @Mapping(source = "dealership", target = "dealershipDto")
    CarDto toFullDto(Car car);

    Car toEntity(CarDto carDto);

    Car toEntity(CarDtoSmall carDto);

    @Mapping(source = "dealershipDto", target = "dealership")
    Car toFullEntity(CarDto carDto);

}
