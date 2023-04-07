package com.codegym.springboot_modul_6.model.FE_BO_Model.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseManufacturerDetailDto {
    private Long id;
    private Long manufacturerId;
    private String manufacturerName;
    private Long productBOId;
}
