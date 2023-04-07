package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseManufacturerProductBODto {
    private Long id;
    private Long manufacturerId;
    private String manufacturerName;
    private Long productBOId;
    private String productBOName;
}
