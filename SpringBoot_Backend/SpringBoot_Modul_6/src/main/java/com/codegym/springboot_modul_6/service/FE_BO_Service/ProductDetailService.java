package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestProductDetailInfoDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductDetailDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductInfoDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductGeneralDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;

import java.util.Optional;

public interface ProductDetailService {
    ResponseProductInfoDto findProductInfoBySerialNumber(String serialNumber);
    ResponseProductGeneralDto findProductGeneralInfoBySerialNumber(String serialNumber);

    ResponseProductDetailDto findProductDetailInfoBySerialNumber(String serialNumber);
    Optional<ProductSFDetail> findBySerialNumber(String serialNumber);

    Boolean updateProductDetailInfo(RequestProductDetailInfoDto requestProductDetailInfoDto);
    Boolean removeBySerialNumber(String serialNumber);
}