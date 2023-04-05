package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Image;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
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

    private List<ImageDto> mapperImageDtoList(List<ProductSF> productSFS){
        List<ImageDto> imageDtos = new ArrayList<>();
        int i = 0;
        for (ProductSF p: productSFS
        ) {
            try {
                ImageDto imageDto = new ImageDto();
                BeanUtils.copyProperties(p.getProductDetail().get(0).getImageList().get(0), imageDto);
                imageDtos.add(imageDto);
                i++;
            }catch (Exception e){
                throw e;
            }

        }
        return imageDtos;
    }

    private List<ProductSFDetailDto> mapperProductSFDetailDto(List<ProductSF> productSFS){
        List<ProductSFDetailDto> productSFDetailDtos = new ArrayList<>();
        for(ProductSF p : productSFS){
            try {
                int i = 0;
                ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
                BeanUtils.copyProperties(p.getProductDetail().get(i), productSFDetailDto);
                i++;
                productSFDetailDtos.add(productSFDetailDto);
            }catch (Exception e){
                throw e;
            }

        }
        return productSFDetailDtos;
    }

    public List<ProductSFDto> mapperProductSFDto(List<ProductSF> productSFS){
        List<ProductSFDto> productSFDtos = new ArrayList<>();
        List<ImageDto> imageDtos = mapperImageDtoList(productSFS);
        List<ProductSFDetailDto> productSFDetailDtos = mapperProductSFDetailDto(productSFS);
        int i = 0;
        for (ProductSF p : productSFS
        ) {
            try {
                ProductSFDto productSFDto = new ProductSFDto();
                BeanUtils.copyProperties(p, productSFDto);
                productSFDto.setProductDetail(Collections.singletonList(productSFDetailDtos.get(i)));
                productSFDto.getProductDetail().get(0).setImageList(Collections.singletonList(imageDtos.get(i)));
                productSFDtos.add(productSFDto);
                i++;
            } catch (Exception e) {
                throw e;
            }
        }
        return productSFDtos;
    }



}
