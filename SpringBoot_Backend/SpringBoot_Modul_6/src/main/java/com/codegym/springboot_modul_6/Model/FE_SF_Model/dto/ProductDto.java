package com.codegym.springboot_modul_6.Model.FE_SF_Model.dto;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto implements Serializable {

    private String name;

    private String category;

    private String serial_number;

    private List<ImageDTO> list;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSerialNumber() {
        return serial_number;
    }

    public ProductDto() {
    }

    public ProductDto(String name, String category, String serial_number) {
        this.name = name;
        this.category = category;
        this.serial_number = serial_number;
    }
}
