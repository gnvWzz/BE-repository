package com.codegym.springboot_modul_6.model.fe_sf_model.entity;

import com.codegym.springboot_modul_6.model.fe_bo_model.entity.Store;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String status;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
    private String phone;

    private String city;
    private String district;
    private String street;


    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Store store;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<AccountRoles> rolesList;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)

    private List<OrderSF> orderSFList;

    public Account() {
    }

    public Account(Long id, String username, String email, String password, String status, String firstName, String lastName, String phone, String city, String district, String street, Store store, List<AccountRoles> rolesList, List<OrderSF> orderSFList) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.street = street;
        this.store = store;
        this.rolesList = rolesList;
        this.orderSFList = orderSFList;
    }

    public List<OrderSF> getOrderSFList() {
        return orderSFList;
    }

    public void setOrderSFList(List<OrderSF> orderSFList) {
        this.orderSFList = orderSFList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
