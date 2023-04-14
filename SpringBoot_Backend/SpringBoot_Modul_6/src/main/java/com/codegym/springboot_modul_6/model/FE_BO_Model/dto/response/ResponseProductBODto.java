package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseProductBODto {
        private String name;
        private String category;

        private String packageId;
        private String status;
        private String manufacturer;

        private List<ResponseProductBODto> responseProductBODtos;

}
