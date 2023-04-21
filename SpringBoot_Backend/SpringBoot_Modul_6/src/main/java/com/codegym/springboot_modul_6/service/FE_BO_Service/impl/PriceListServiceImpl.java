package com.codegym.springboot_modul_6.service.FE_BO_Service.impl;

import com.codegym.springboot_modul_6.repository.FE_BO_Repository.PriceListRepository;
import com.codegym.springboot_modul_6.service.FE_BO_Service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceListServiceImpl implements PriceListService {
    @Autowired
    private PriceListRepository priceListRepository;
    @Override
    public Double findStandardPriceByProductId(Long productId) {
        Double standardPrice = priceListRepository.findStandardPrice(productId);
        return standardPrice;
    }
}
