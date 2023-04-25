package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceListDto;

import java.util.List;

public interface PriceService {
    Double findStandardPriceByProductId(Long productId);
    Boolean updatePriceList(String serialNumber, List<PriceListDto> priceListDto);
}
