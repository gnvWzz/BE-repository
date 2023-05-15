package com.codegym.springboot_modul_6.service.fe_bo_service.impl;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Price;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceDto;
import com.codegym.springboot_modul_6.repository.fe_bo_repository.PriceRepository;
import com.codegym.springboot_modul_6.service.fe_bo_service.PriceService;
import com.codegym.springboot_modul_6.service.fe_bo_service.ProductDetailService;
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

// Price is an object, not a list
    @Override
    @Transactional
    public Boolean updatePriceList(String serialNumber,List<PriceDto> priceDto) {
        ProductSFDetail productDetail = productDetailService.findBySerialNumber(serialNumber).orElse(null);
        if(productDetail != null) {
            ProductSF product = productDetail.getProductSF();
            Long productId = product.getId();
            priceRepository.deletePriceListByProductId(productId);
            List<Price> prices = new ArrayList<>();
            for (PriceDto ele : priceDto) {
                Price price = new Price();
                price.setProductSF(product);
                price.setPriceId(ele.getPriceId());
                price.setFromQuantity(ele.getFromQuantity());
                price.setToQuantity(ele.getToQuantity());
                price.setPrice(ele.getPrice());

                prices.add(price);
            }
            Iterable<Price> newList = prices;

            priceRepository.saveAll(newList);
            return true;
        }
        return false;
    }
}
