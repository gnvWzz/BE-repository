package com.codegym.springboot_modul_6.model.FE_BO_Model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ManufacturerDto {
    private Long id;
    private String name;
    private String mobile;
    private String landline;
    private String email;
    private String address;
    private String field;
    private Date signup;
    private String website;
    private String icon;

}
