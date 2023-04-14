package com.codegym.springboot_modul_6.service.FE_SF_Service.impl;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ProductDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private IProductDetailSFRepository productDetailRepository;
    @Override
    public ProductSFDetailDto findBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber);
        ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
        BeanUtils.copyProperties(productSFDetail, productSFDetailDto);
        return productSFDetailDto;
    }

    @Override
    @Transactional
    public void removeBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber);
        if(productSFDetail != null){
            productDetailRepository.removeBySerialNumber(serialNumber);
        }
    }
}
