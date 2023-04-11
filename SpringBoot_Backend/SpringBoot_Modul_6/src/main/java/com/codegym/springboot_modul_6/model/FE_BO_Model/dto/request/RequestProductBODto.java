package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestProductBODto {
    private Long id;
    private String name;
    private String category;
    private String serialNumber;
    private String size;
    private String color;
    private Double price;
    private String briefDescription;
    private String fullDescription;
    private Double weight;
    private String material;
    private Long quantity;
//    private String image;
//    private String status;

    private Long manufacturerId;
}
