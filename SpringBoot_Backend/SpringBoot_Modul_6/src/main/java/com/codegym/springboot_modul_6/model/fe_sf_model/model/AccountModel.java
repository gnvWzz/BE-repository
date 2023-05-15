package com.codegym.springboot_modul_6.model.fe_sf_model.model;

public class AccountModel {
    private String token;
    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountModel() {
    }

    public AccountModel(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
