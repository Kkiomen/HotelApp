package com.github.lukassakwa.dealershipuserservice.resources.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarToReceive {
    CarDtoSmall carDtoSmall;
    String dealership;
}
