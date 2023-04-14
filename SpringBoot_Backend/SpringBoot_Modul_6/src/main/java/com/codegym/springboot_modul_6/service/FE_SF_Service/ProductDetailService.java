package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;

public interface ProductDetailService {
    ProductSFDetailDto findBySerialNumber(String serialNumber);
    void removeBySerialNumber(String serialNumber);
}
