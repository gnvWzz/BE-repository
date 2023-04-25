package com.codegym.springboot_modul_6.util;

import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceListDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.PriceList;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSFDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public List<PriceListDto> mapperPriceList(List<PriceList> priceLists){
        List<PriceListDto> priceListDtos = new ArrayList<>();
        for (PriceList p: priceLists
        ) {
            PriceListDto priceListDto = new PriceListDto();
            BeanUtils.copyProperties(p, priceListDto);
            priceListDtos.add(priceListDto);
        }
        return priceListDtos;
    }

    public List<ProductSFDto> mapperProductSFDto(List<ProductSF> productSFS){
        List<ProductSFDto> productSFDtos = new ArrayList<>();
        for (ProductSF p: productSFS
        ) {
            ProductSFDto productSFDto = new ProductSFDto();
            BeanUtils.copyProperties(p, productSFDto);
            productSFDto.setProductSFDetailDtos(mapperProductDetailDto(p.getProductSFDetail()));
            productSFDto.setPriceListDtos(mapperPriceList(p.getPrices()));
            productSFDtos.add(productSFDto);
        }
        return productSFDtos;
    }

    private List<ProductSFDetailDto> mapperProductDetailDto(List<ProductSFDetail> productSFDetails){
        List<ProductSFDetailDto> productSFDetailDtos = new ArrayList<>();
        ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
        BeanUtils.copyProperties(productSFDetails.get(0), productSFDetailDto);
        productSFDetailDtos.add(productSFDetailDto);
        return productSFDetailDtos;
    }
}
