package com.codegym.springboot_modul_6.model.fe_sf_model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDto implements Serializable {

    private Long priceId;
    private Long fromQuantity;

    private Long toQuantity;

    private Double price;
}
