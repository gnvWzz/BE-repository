package com.codegym.springboot_modul_6.Model.FE_SF_Model.dto;



public class LoginDto {

    private String username;
    private String email;
    private String password;
    private String status;

    public LoginDto(String username, String email, String password, String status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public LoginDto() {
    }
}
