package com.codegym.springboot_modul_6.model.fe_sf_model.dto;

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

    private String street;

    private String district;

    private String phone;

    private String email;

    private double totalPrice;

    private String dateOrder;
}
