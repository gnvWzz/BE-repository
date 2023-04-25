package com.codegym.springboot_modul_6.model.fe_sf_model.model;

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
public class CartDetailModel {

    private Long quantity;

    private String name;

    private Double price;

    private String serialNumber;

    private String size_color_img_quantity;

    private Double subTotal;

}
