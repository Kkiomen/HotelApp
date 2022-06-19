package com.github.lukassakwa.dealershipuserservice.mappers;

import com.github.lukassakwa.dealershipuserservice.cardealership.dealership.domain.Dealership;
import com.github.lukassakwa.dealershipuserservice.resources.dto.DealershipDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealershipMapper {

    DealershipDto toDto(Dealership dealership);

    Dealership toEntity(DealershipDto dealershipDto);

}
