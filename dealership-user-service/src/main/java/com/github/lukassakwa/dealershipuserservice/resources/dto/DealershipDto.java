package com.github.lukassakwa.dealershipuserservice.resources.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DealershipDto {
    private Long id;
    private String name;
    private String image;
    private String address;
    private String openHours;
    private String contact;
    private List<CarDtoSmall> cars;
}
