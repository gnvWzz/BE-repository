package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseManufacturerDto {
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
    private String status;
    private String image;
    private List<ResponseManufacturerProductBODto> responseManufacturerProductBODtos;
}
