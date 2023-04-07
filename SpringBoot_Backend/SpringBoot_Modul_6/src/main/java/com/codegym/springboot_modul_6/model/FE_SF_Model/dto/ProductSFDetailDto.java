package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductSFDetailDto implements Serializable {

    private String size;

    private Long price;

    private String serialNumber;

    private String briefDescription;

    private String fullDescription;

    private Double weight;

    private String material;

    private String color;

    private String cpu;

    private String gpu;

    private String storageDrive;

    private String display;

    private Long quantity;

    private String manufacturer;

    private List<ImageDto> imageList;
}
