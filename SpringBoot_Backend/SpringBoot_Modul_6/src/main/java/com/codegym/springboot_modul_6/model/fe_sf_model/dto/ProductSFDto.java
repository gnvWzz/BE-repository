package com.codegym.springboot_modul_6.model.fe_sf_model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSFDto implements Serializable {
    private String name;
    private String category;
    private String status;
    private String manufacturer;
    private String accountUsername;
    private List<ProductSFDetailDto> productSFDetailDtos;
    private List<PriceDto> priceDtos;
    private String storeName;
    private String storeImage;
    private Integer totalSales;
}
