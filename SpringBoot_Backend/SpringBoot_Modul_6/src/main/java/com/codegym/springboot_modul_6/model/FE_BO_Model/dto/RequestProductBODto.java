package com.codegym.springboot_modul_6.model.FE_BO_Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestProductBODto {
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

    private Long manufacturerId;
}
