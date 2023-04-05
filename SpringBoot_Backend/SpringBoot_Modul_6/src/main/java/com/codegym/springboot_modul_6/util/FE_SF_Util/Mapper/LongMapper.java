package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Image;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ImageDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LongMapper {

    private List<ImageDto> mapperImageDtoList(ProductSF productSFS){
        List<ImageDto> imageDtos = new ArrayList<>();
        for(int i = 0; i < productSFS.getProductDetail().get(0).getImageList().size(); i++){
            ImageDto url = new ImageDto();
            BeanUtils.copyProperties(productSFS.getProductDetail().get(0).getImageList().get(i), url);
            imageDtos.add(url);
        }
        return imageDtos;
    }

    private List<ImageDto> mapperImageDto(ProductSFDetail productSFDetail){
        List<ImageDto> imageDtoss = new ArrayList<>();
        for(int i = 0; i < productSFDetail.getImageList().size(); i++){
            ImageDto url = new ImageDto();
            BeanUtils.copyProperties(productSFDetail.getImageList().get(i), url);
            imageDtoss.add(url);
        }
        return imageDtoss;
    }

    private List<ProductSFDetailDto> mapperProductSFDetailDto(ProductSF productSFS){
        List<ProductSFDetailDto> productSFDetailDtos = new ArrayList<>();
        for(int i = 0; i < productSFS.getProductDetail().size(); i++){
            ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
            BeanUtils.copyProperties(productSFS.getProductDetail().get(i), productSFDetailDto);
            productSFDetailDto.setImageList(mapperImageDto(productSFS.getProductDetail().get(i)));
            productSFDetailDtos.add(productSFDetailDto);
        }
        return productSFDetailDtos;
    }

    public List<ProductSFDto> mapperProductSFDto(List<ProductSF> productSFS){
        List<ProductSFDto> productSFDtos = new ArrayList<>();
        int i = 0;
        for (ProductSF p : productSFS
        ) {
            try {
                ProductSFDto productSFDto = new ProductSFDto();
                BeanUtils.copyProperties(p, productSFDto);
                productSFDto.setProductDetail(mapperProductSFDetailDto(p));
                productSFDtos.add(productSFDto);
                i++;
            } catch (Exception e) {
                throw e;
            }
        }
        return productSFDtos;
    }



}
