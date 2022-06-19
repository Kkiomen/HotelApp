package com.github.lukassakwa.dealershipuserservice.account.service;

import com.github.lukassakwa.dealershipuserservice.account.domain.Account;
import com.github.lukassakwa.dealershipuserservice.account.domain.Role;
import com.github.lukassakwa.dealershipuserservice.account.repo.AccountRepository;
import com.github.lukassakwa.dealershipuserservice.account.repo.RoleRepository;
import com.github.lukassakwa.dealershipuserservice.exceptions.UserExistException;
import lombok.RequiredArgsConstructor;
import net.jcip.annotations.Immutable;
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

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements IAccountService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
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

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account registerAccount(Account account) throws Exception  {
        //if(accountExist(account.getUsername())) {
        //    throw new UserExistException("User exist");
        //}
        //account.addRole(new Role("ROLE_USER"));
        String password = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(password);
        return saveAccount(account);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void addRoleToUser(String username, String name) {
        Account account = accountRepository.findByUsername(username);
        Role role = roleRepository.findByName(name);
        account.getRole().add(role);
    }

    @Override
    public Account getUser(String username) {
        return accountRepository.findByUsername(username);
    }
}
