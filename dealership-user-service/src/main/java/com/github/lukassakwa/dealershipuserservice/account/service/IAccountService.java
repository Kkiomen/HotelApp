package com.github.lukassakwa.dealershipuserservice.account.service;

import com.github.lukassakwa.dealershipuserservice.account.domain.Account;
import com.github.lukassakwa.dealershipuserservice.account.domain.Role;
import com.github.lukassakwa.dealershipuserservice.resources.account.AccountDto;

import java.util.List;

public interface IAccountService {
    Account saveAccount(Account account);
    Role saveRole(Role role);
    List<AccountDto> getAccounts();
    void addRoleToUser(String username, String name);
    Account getUser(String username);
    void removeUserRole(String username, String name);
}
