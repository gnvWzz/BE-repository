package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class OrderDto {

    private String firstName;

    private String lastName;

    private String city;

    @JsonProperty(value = "orderDetails")
    private List<OrderDetailDto> orderDetailDtoList;

    @JsonProperty(value = "street")
    private String street;


    private String district;

    private String phone;

    private String email;

    private double totalPrice;
}
