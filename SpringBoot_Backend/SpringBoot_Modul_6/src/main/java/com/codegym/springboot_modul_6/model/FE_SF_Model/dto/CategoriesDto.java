package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;


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
