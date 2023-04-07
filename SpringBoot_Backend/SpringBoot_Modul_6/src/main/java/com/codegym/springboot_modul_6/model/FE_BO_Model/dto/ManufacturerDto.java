package com.codegym.springboot_modul_6.model.FE_BO_Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//bỏ qua các thuộc tính null cua Vòng lặp vô hạn trong JSON khi một đối tượng chứa 1 tham chiếu đệ quy
public class ManufacturerDto {
    private Long id;
    private String businessCode;
    private String name;
    private String mobile;
    private String landline;
    private String email;
    private String address;
    private String field;
    private Date signup;
    private String website;
    private String icon;
    private String status;
    private List<RequestManufacturerDetailDto> requestManufacturerDetailDtos;
}
