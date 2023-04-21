package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProductDetailDto {

    private String serialNumber;

    private String briefDescription;

    private String fullDescription;

    private Double weight;

    private String material;

    private String cpu;

    private String gpu;

    private String ram;

    private String storageDrive;

    private String display;
//    private String status;
    private String  size_color_img_quantity;


    private String productName;
    private String category;
    private String manufacturer;
    private Double standardPrice;
}
