package com.codegym.springboot_modul_6.service.fe_bo_service;

import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceDto;

import java.util.List;

public interface PriceService {
    Double findStandardPriceByProductId(Long productId);
    Boolean updatePriceList(String serialNumber, List<PriceDto> priceDto);
}
