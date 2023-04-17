package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

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
public class PriceListDto implements Serializable {

    private Long priceId;
    private Long fromQuantity;

    private Long toQuantity;

    private Double price;
}
