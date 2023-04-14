package com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProductSFDto {
    private String name;
    private String category;
    private String packageId;
    private String status;
    private List<ProductSFDetail> productSFDetail;
}
