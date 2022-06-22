package com.github.lukassakwa.dealershipuserservice.resources.account;

import com.github.lukassakwa.dealershipuserservice.account.domain.Account;
import com.github.lukassakwa.dealershipuserservice.account.domain.Role;
import com.github.lukassakwa.dealershipuserservice.account.service.AccountService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(path = "/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(accountService.registerAccount(account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }

    @GetMapping(path = "/api/users")
    public ResponseEntity<List<AccountDto>> getUsers() {
        return ResponseEntity.ok().body(accountService.getAccounts());
    }

    @PostMapping("/api/user/save")
    public ResponseEntity<Account> saveUser(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.saveAccount(account));
    }

    @PostMapping("/api/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(accountService.saveRole(role));
    }

    @PostMapping("/api/addToUser")
    public ResponseEntity<Void> addRoleToUser(@RequestBody FormDto formDto) {
        accountService.addRoleToUser(formDto.getUsername(), formDto.getRole());
        return ResponseEntity.ok().build();
    }


}
