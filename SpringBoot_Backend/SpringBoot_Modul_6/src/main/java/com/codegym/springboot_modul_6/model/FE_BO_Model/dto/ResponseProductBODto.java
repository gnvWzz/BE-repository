package com.codegym.springboot_modul_6.model.FE_BO_Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//bỏ qua các thuộc tính null cua Vòng lặp vô hạn trong JSON khi một đối tượng chứa 1 tham chiếu đệ quy

public class ResponseProductBODto {
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
        private String icon;
        private String status;
        private List<ResponseManufacturerDetailDto> responseManufacturerDetailDtos;

}
