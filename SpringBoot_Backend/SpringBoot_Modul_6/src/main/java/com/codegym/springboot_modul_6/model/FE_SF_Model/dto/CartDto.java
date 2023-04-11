package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private int quantity;
    private double totalPrice;

    private List<CartDetailDto> cartDetailDtos;

    private String accountName;

}
