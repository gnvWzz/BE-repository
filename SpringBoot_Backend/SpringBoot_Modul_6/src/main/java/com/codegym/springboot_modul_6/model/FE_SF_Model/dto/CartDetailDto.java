package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {

    private Long quantity;

    private String name;

    private double price;

    private String serialNumber;

    private String accountName;

    @JsonProperty(value = "subTotal")
    private String subTotal;

}
