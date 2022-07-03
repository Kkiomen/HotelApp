package com.github.lukassakwa.dealershipuserservice.account.service;

import com.github.lukassakwa.dealershipuserservice.account.domain.Account;
import com.github.lukassakwa.dealershipuserservice.account.domain.Role;
import com.github.lukassakwa.dealershipuserservice.account.repo.AccountRepository;
import com.github.lukassakwa.dealershipuserservice.account.repo.RoleRepository;
import com.github.lukassakwa.dealershipuserservice.exceptions.UserExistException;
import com.github.lukassakwa.dealershipuserservice.mappers.AccountMapper;
import com.github.lukassakwa.dealershipuserservice.resources.account.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements IAccountService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final AccountMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

    public boolean accountExist(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            return false;
        }
        return true;
    }

    public Account addNewAccount(Account account) throws UserExistException {
        if(accountExist(account.getUsername())) {
            throw new UserExistException("User exist");
        }
        String password = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(password);
        Collection<Role> roles = account.getRole();
        account.setRole(Collections.emptyList());
        Account savedAccount = saveAccount(account);
        if(!roles.isEmpty()) {
            roles.forEach(role -> addRoleToUser(savedAccount.getUsername(), role.getName()));
        } else {
            addRoleToUser(savedAccount.getUsername(), "ROLE_USER");
        }
        return savedAccount;
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account registerAccount(Account account) throws Exception  {
        if(accountExist(account.getUsername())) {
            throw new UserExistException("User exist");
        }
        String password = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(password);
        Account savedAccount = saveAccount(account);
        addRoleToUser(savedAccount.getUsername(), "ROLE_USER");
        return savedAccount;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<AccountDto> getAccounts() {
        return accountRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void addRoleToUser(String username, String name) {
        Account account = accountRepository.findByUsername(username);
        Role role = roleRepository.findByName(name);
        account.addRole(role);
    }

    @Override
    public void removeUserRole(String username, String name) {
        Account account = accountRepository.findByUsername(username);
        Role role = roleRepository.findByName(name);
        account.getRole().remove(role);
    }

    @Override
    public Account getUser(String username) {
        return accountRepository.findByUsername(username);
    }

    public void deleteUser(String username) {
        Account account = accountRepository.findByUsername(username);
        accountRepository.delete(account);
    }
}
