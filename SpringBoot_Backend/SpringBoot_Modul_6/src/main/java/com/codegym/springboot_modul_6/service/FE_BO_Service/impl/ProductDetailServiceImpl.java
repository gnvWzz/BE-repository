package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductDetailDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductGeneralDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.PriceListRepository;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.ProductDetailService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private IProductDetailSFRepository productDetailRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PriceListServiceImpl priceListService;
    @Override
    public ResponseProductDetailDto findProductDetailInfoBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber);
        ResponseProductDetailDto responseProductDetailDto = new ResponseProductDetailDto();
        BeanUtils.copyProperties(productSFDetail, responseProductDetailDto);

        Long productId = productSFDetail.getProductSF().getId();
        ProductSF productSF = productService.findById(productId).orElse(null);
        if(productSF != null) {
            String productName = productSF.getName();
            responseProductDetailDto.setProductName(productName);
            String category = productSF.getCategory();
            responseProductDetailDto.setCategory(category);
            String manufacturer = productSF.getManufacturer();
            responseProductDetailDto.setManufacturer(manufacturer);
            Double standardPrice = priceListService.findStandardPriceByProductId(productId);
            responseProductDetailDto.setStandardPrice(standardPrice);

            return responseProductDetailDto;
        }
        return null;
    }

    @Override
    public ResponseProductGeneralDto findProductGeneralInfoBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber);
        ResponseProductGeneralDto responseProductGeneralDto = new ResponseProductGeneralDto();
        Long productId = productSFDetail.getProductSF().getId();
        ProductSF productSF = productService.findById(productId).orElse(null);
        if(productSF != null) {
            BeanUtils.copyProperties(productSF, responseProductGeneralDto);
            return responseProductGeneralDto;
        }
        return null;
    }

    @Override
    public void removeBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber);
        if(productSFDetail != null){
            productDetailRepository.removeBySerialNumber(serialNumber);
        }
    }
}
