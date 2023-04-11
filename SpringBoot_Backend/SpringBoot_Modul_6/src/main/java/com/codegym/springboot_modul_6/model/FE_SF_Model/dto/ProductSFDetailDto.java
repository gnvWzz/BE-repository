package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductSFDetailDto implements Serializable {

    private Double price;

    private String serialNumber;

    private String briefDescription;

    private String fullDescription;

    private Double weight;

    private String material;

    private String cpu;

    private String gpu;

    private String ram;

    private String storageDrive;

    private String display;

    private String size_color_img_quantity;
}
