package com.codegym.springboot_modul_6.Model.FE_SF_Model.dto;

public class ProductDto {

    private String name;

    private String category;

    private String serialNumber;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public ProductDto() {
    }

    public ProductDto(String name, String category, String serialNumber) {
        this.name = name;
        this.category = category;
        this.serialNumber = serialNumber;
    }
}
