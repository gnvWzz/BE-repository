package com.codegym.springboot_modul_6.Model.FE_BO_Model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
public class ManufacturerDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String field;
    private Date signup;
    private String website;
}
