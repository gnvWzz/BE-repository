package com.codegym.springboot_modul_6.model.fe_sf_model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {

    private Long quantity;

    private String name;

    private double price;

    private String serialNumber;

    private String size_color_img_quantity;
}
