package com.codegym.springboot_modul_6.model.fe_sf_model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto implements Serializable {

    private String username;
    private String email;
    private String password;
    private String status;

    private String phone;

    private String firstName;

    private String lastName;

    private String city;
    private  String district;
    private String street;

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


    public AccountDto(String username, String email, String password, String status, String phone, String firstName, String lastName, String city, String district, String street) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.district = district;
        this.street = street;
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

    public AccountDto() {
    }
}
