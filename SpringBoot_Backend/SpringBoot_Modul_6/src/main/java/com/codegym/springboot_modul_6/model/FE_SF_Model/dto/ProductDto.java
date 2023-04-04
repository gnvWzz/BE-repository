package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private String name;

    private String category;

    private String serial_number;

    private List<String> list;

    private Double price;

    private String brief_description;

    private String full_description;

    private String manufacturer;

    private Double weight;

    private String material;

    private String colors;

    private String size;

    private String cpu;

    private String gpu;

    private String ram;

    private String storageDrive;

    private String display;
}
