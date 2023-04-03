package com.codegym.springboot_modul_6.Model.FE_BO_Model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
public class ProductDtoBO {
    private Long id;
    private String name;
    private String category;
    private String serialNumber;
    private String size;
    private String color;
    private Double price;
    private String briefDescription;
    private String fullDescription;
    private Double weight;
    private String material;
    private Long quantity;
    private String icon;
    private String status;
    //test
}
