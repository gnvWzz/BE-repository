package com.codegym.springboot_modul_6.service.FE_BO_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.PriceListDto;

import java.util.List;

public interface PriceService {
    Double findStandardPriceByProductId(Long productId);
    Boolean updatePriceList(String serialNumber, List<PriceListDto> priceListDto);
}
