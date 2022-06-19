package com.github.lukassakwa.dealershipuserservice.account.repo;

import com.github.lukassakwa.dealershipuserservice.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
