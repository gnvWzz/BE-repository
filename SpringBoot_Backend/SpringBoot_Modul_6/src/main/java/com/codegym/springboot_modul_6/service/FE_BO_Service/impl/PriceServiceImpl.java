package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.PriceList;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceListDto;
import com.codegym.springboot_modul_6.repository.fe_bo_repository.PriceRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.PriceService;
import com.codegym.springboot_modul_6.service.FE_BO_Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            }
            Iterable<PriceList> newList = priceLists;
            priceRepository.saveAll(newList);
            return true;
        }
        return false;
    }
}
