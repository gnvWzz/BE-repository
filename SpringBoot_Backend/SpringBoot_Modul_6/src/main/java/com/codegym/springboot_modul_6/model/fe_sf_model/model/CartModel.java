package com.codegym.springboot_modul_6.model.fe_sf_model.model;


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
public class CartModel {

    private String accountName;

    private double totalPrice;

    private List<CartDetailModel> cartDetailModelList;

}
