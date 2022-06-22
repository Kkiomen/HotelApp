package com.github.lukassakwa.dealershipuserservice.resources.account;

import com.github.lukassakwa.dealershipuserservice.account.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@Builder
public class AccountDto {
    private Long id;
    private String username;
    private String email;
    private Integer phone;
    private String position;
    private List<String> role;
}
