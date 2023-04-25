package com.codegym.springboot_modul_6.model.fe_sf_model.dto;

public class CategoriesDto {

    private String name;

    public CategoriesDto(String name) {
        this.name = name;
    }

    public CategoriesDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
