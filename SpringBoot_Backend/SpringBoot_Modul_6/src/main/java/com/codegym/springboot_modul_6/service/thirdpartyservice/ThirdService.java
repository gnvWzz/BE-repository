package com.codegym.springboot_modul_6.service.thirdpartyservice;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Image;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ImageDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IImageRepositorySF;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductDetailSFRepository;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductRepositorySF;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThirdService {

    @Autowired
    private IProductRepositorySF productRepositorySF;

    @Autowired
    private IProductDetailSFRepository productDetailSFRepository;

    @Autowired
    private IImageRepositorySF imageRepositorySF;

    public ProductSFDto getProductSFDto(Long id) {
        ProductSF productSF = productRepositorySF.findById(id).get();

        List<ProductSFDetail> productSFDetails = productSF.getProductDetail();

        List<Long> ids = productSFDetails.stream().map(ProductSFDetail::getId).collect(Collectors.toList());

        List<ProductSFDetailDto> productSFDetailDtos = new ArrayList<>();

        List<Image> imageList = imageRepositorySF.findByProductDetailIds(ids);

        for (Long productDetailsSFId: ids) {
            ProductSFDetail productSFDetail = productDetailSFRepository.findById(productDetailsSFId).get();
            List<ImageDto> imageDtoList = new ArrayList<>();
            for (Image i: imageList) {
                ImageDto imageDto = new ImageDto();
                BeanUtils.copyProperties(i, imageDto);
                imageDtoList.add(imageDto);
            }
            ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
            BeanUtils.copyProperties(productSFDetail, productSFDetailDto);
            productSFDetailDto.setImageDtoList(imageDtoList);
            productSFDetailDtos.add(productSFDetailDto);
        }

        ProductSFDto productSFDto = new ProductSFDto();
        BeanUtils.copyProperties(productSF, productSFDto);
        productSFDto.setProductSFDetailDtos(productSFDetailDtos);
        return productSFDto;
    }
}
