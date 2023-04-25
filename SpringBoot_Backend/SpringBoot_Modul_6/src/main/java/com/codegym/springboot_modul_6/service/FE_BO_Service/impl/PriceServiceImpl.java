package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.PriceList;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.PriceListDto;
import com.codegym.springboot_modul_6.repository.FE_BO_Repository.PriceRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.PriceService;
import com.codegym.springboot_modul_6.service.FE_BO_Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private ProductDetailService productDetailService;
    @Override
    public Double findStandardPriceByProductId(Long productId) {
        Double standardPrice = priceRepository.findStandardPrice(productId);
        return standardPrice;
    }

// PriceList is an object, not a list
    @Override
    @Transactional
    public Boolean updatePriceList(String serialNumber,List<PriceListDto> priceListDto) {
        ProductSFDetail productDetail = productDetailService.findBySerialNumber(serialNumber).orElse(null);
        if(productDetail != null) {
            ProductSF product = productDetail.getProductSF();
            Long productId = product.getId();
            priceRepository.deletePriceListByProductId(productId);
            List<PriceList> priceLists = new ArrayList<>();
            for (PriceListDto ele : priceListDto) {
                PriceList priceObj = new PriceList();
                priceObj.setProductSF(product);

                priceObj.setPriceId(ele.getPriceId());
                priceObj.setFromQuantity(ele.getFromQuantity());
                priceObj.setToQuantity(ele.getToQuantity());
                priceObj.setPrice(ele.getPrice());

                priceLists.add(priceObj);
//                priceRepository.save(priceObj);
            }
            Iterable<PriceList> newList = priceLists;
            priceRepository.saveAll(newList);
            return true;
        }
        return false;
    }
}
