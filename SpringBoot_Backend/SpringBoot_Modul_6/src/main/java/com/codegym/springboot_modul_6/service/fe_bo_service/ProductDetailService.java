package com.codegym.springboot_modul_6.service.fe_bo_service;

import com.codegym.springboot_modul_6.model.fe_bo_model.dto.request.RequestProductDetailInfoDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductDetailDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductInfoDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductGeneralDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSFDetail;

import java.util.Optional;

public interface ProductDetailService {
    ResponseProductInfoDto findProductInfoBySerialNumber(String serialNumber);
    ResponseProductGeneralDto findProductGeneralInfoBySerialNumber(String serialNumber);

    ResponseProductDetailDto findProductDetailInfoBySerialNumber(String serialNumber);
    Optional<ProductSFDetail> findBySerialNumber(String serialNumber);

    Boolean updateProductDetailInfo(RequestProductDetailInfoDto requestProductDetailInfoDto);
    Boolean removeBySerialNumber(String serialNumber);
}