package com.github.lukassakwa.dealershipuserservice.account.service;

import com.github.lukassakwa.dealershipuserservice.account.domain.Account;
import com.github.lukassakwa.dealershipuserservice.account.domain.Role;

import java.util.List;

public interface IAccountService {
    Account saveAccount(Account account);
    Role saveRole(Role role);
    List<Account> getAccounts();
    void addRoleToUser(String username, String name);
    Account getUser(String username);
}
