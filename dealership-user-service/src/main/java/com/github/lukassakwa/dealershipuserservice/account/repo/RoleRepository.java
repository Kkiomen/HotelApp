package com.github.lukassakwa.dealershipuserservice.account.repo;

import com.github.lukassakwa.dealershipuserservice.account.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
