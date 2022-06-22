package com.github.lukassakwa.dealershipuserservice.account.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private Integer phone;
    private String position;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> role = new ArrayList<>();

    public Account(String username, String password, Collection<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(String username, String password, String email, Integer phone, String position, Collection<Role> role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.role = role;
    }

    public void addRole(Role role) {
        if(this.role.isEmpty()) {
            this.role = new ArrayList<>();
        }
        this.role.add(role);
    }
}
