package com.codegym.springboot_modul_6.model.FE_SF_Model.dto;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSFDto implements Serializable {

    private String name;
    private String category;
    private String status;
    private String manufacturer;
    private String accountUsername;
    private List<ProductSFDetailDto> productSFDetailDtos;
    private List<PriceListDto> priceListDtos;
    private String storeName;
    private String storeImage;
}
