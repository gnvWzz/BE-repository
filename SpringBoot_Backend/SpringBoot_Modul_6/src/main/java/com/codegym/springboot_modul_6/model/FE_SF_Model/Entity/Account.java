package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String status;

    @OneToMany(mappedBy = "account",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private List<AccountRoles> rolesList;

    public Account() {
    }

    public Account(Long id, String username, String email, String password, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AccountRoles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<AccountRoles> rolesList) {
        this.rolesList = rolesList;
    }
}
