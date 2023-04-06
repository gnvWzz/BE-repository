package com.codegym.springboot_modul_6.model.FE_SF_Model.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "roles")
    private List<AccountRoles> accountRoles;

    public Roles(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Roles() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountRoles> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<AccountRoles> accountRoles) {
        this.accountRoles = accountRoles;
    }
}
