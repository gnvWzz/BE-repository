package com.codegym.springboot_modul_6.service.FE_SF_Service.impl;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.PriceListRepository;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ProductDetailService;
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
    private PriceListRepository priceListRepository;
    @Override
    @Transactional
    public ProductSFDetailDto findBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber);
        ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
        BeanUtils.copyProperties(productSFDetail, productSFDetailDto);

        Long productId = productSFDetail.getProductSF().getId();
        Optional<ProductSF> productSFOptional = productService.findById(productId);
        String productName = productSFOptional.get().getName();
        productSFDetailDto.setProductName(productName);
        String category = productSFOptional.get().getCategory();
        productSFDetailDto.setCategory(category);
        Double standardPrice = priceListRepository.findStandardPrice(productId);
        productSFDetailDto.setStandardPrice(standardPrice);

        return productSFDetailDto;
    }

    @Override
    public void removeBySerialNumber(String serialNumber) {
        ProductSFDetail productSFDetail = productDetailRepository.findBySerialNumber(serialNumber);
        if(productSFDetail != null){
            productDetailRepository.removeBySerialNumber(serialNumber);
        }
    }
}
