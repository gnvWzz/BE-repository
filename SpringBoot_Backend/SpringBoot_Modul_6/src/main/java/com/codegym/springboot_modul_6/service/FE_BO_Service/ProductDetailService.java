package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductDetailDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductGeneralDto;

public interface ProductDetailService {
    ResponseProductDetailDto findProductDetailInfoBySerialNumber(String serialNumber);
    ResponseProductGeneralDto findProductGeneralInfoBySerialNumber(String serialNumber);
    void removeBySerialNumber(String serialNumber);
}
