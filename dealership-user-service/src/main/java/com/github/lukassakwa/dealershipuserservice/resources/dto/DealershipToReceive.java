package com.github.lukassakwa.dealershipuserservice.resources.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DealershipToReceive {
    public Integer id;
    public String name;
    public String image;
    public String address;
    public String openHours;
    public String contact;
}
