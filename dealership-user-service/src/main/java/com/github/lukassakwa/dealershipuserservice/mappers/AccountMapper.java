package com.github.lukassakwa.dealershipuserservice.mappers;

import com.github.lukassakwa.dealershipuserservice.account.domain.Account;
import com.github.lukassakwa.dealershipuserservice.account.domain.Role;
import com.github.lukassakwa.dealershipuserservice.resources.account.AccountDto;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntity(AccountDto accountDto);

    AccountDto toDto(Account account);

    Collection<Role> map(List<String> value);

    List<String> map(Collection<Role> value);

    Role map(String role);

    String map(Role role);

}
