package com.codegym.springboot_modul_6.model.FE_SF_Model.model;


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

    private String account_name;

    private double total_price_cart;

    private List<CartDetailModel> cartDetailModelList;

}
