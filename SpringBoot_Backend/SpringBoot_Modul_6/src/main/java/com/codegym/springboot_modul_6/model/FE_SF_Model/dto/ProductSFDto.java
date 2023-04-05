package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

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

    private String serial_number;

    private List<ProductSFDetailDto> productSFDetailDtos;
}
