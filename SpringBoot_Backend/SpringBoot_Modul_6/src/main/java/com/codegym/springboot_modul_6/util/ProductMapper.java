package com.codegym.springboot_modul_6.util;

import com.codegym.springboot_modul_6.model.fe_sf_model.dto.PriceDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Price;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.SizeColorImgQuantity;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IProductRepositorySF;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    @Autowired
    private IProductRepositorySF productRepositorySF;

    public ProductSFDetailDto getProductSFDetailDtoByColor(String color, String name) {
        ProductSF productSF = productRepositorySF.findProductSFByName(name).orElse(null);
        ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
        if(productSF != null) {
            List<ProductSFDetail> productSFDetailList = productSF.getProductSFDetail();
            for (ProductSFDetail productSFDetail : productSFDetailList) {
                try {
                    JSONParser parser = new JSONParser();
                    JSONObject sizeColorImgQuantity = (JSONObject) parser.parse(productSFDetail.getSize_color_img_quantity());
                    Gson gson = new GsonBuilder().create();
                    SizeColorImgQuantity sizeColorImgQuantity1 = gson.fromJson(sizeColorImgQuantity.toString(), SizeColorImgQuantity.class);
                    if (sizeColorImgQuantity1.getColor().equals(color)) {
                        BeanUtils.copyProperties(productSFDetail, productSFDetailDto);
                        return productSFDetailDto;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public ProductSFDetailDto getProductSFDetailDtoByColorAndSize(String color, String size, String name) {
        ProductSF productSF = productRepositorySF.findProductSFByName(name).orElse(null);
        ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
        if(productSF != null) {
            List<ProductSFDetail> productSFDetailList = productSF.getProductSFDetail();
            for (ProductSFDetail productSFDetail : productSFDetailList) {
                try {
                    JSONParser parser = new JSONParser();
                    JSONObject sizeColorImgQuantity = (JSONObject) parser.parse(productSFDetail.getSize_color_img_quantity());
                    Gson gson = new GsonBuilder().create();
                    SizeColorImgQuantity sizeColorImgQuantity1 = gson.fromJson(sizeColorImgQuantity.toString(), SizeColorImgQuantity.class);
                    if (sizeColorImgQuantity1.getColor().equals(color) && sizeColorImgQuantity1.getSize().equals(size)) {
                        BeanUtils.copyProperties(productSFDetail, productSFDetailDto);
                        return productSFDetailDto;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public ProductSFDto getProductSFDto(String name) {
        try {
            ProductSF productSF = productRepositorySF.findProductSFByName(name).orElse(null);
            List<ProductSFDetail> productSFDetailList = productSF.getProductSFDetail();
            List<Price> priceLists = productSF.getPrices();
            List<ProductSFDetailDto> productSFDetailDtoList = new ArrayList<>();
            for (ProductSFDetail p : productSFDetailList) {
                ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
                BeanUtils.copyProperties(p, productSFDetailDto);
                productSFDetailDtoList.add(productSFDetailDto);
            }
            List<PriceDto> priceListDtos = new ArrayList<>();
            for (Price priceList : priceLists) {
                PriceDto priceListDto = new PriceDto();
                BeanUtils.copyProperties(priceList, priceListDto);
                priceListDtos.add(priceListDto);
            }
            ProductSFDto productSFDto = new ProductSFDto();
            BeanUtils.copyProperties(productSF, productSFDto);
            productSFDto.setProductSFDetailDtos(productSFDetailDtoList);
            productSFDto.setPriceDtos(priceListDtos);
            productSFDto.setStoreName(productSF.getStore().getName());
            productSFDto.setStoreImage(productSF.getStore().getImage());
            return productSFDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
