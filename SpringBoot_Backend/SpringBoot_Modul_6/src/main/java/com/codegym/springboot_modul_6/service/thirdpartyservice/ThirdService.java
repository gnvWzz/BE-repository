package com.codegym.springboot_modul_6.service.thirdpartyservice;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IProductService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.LongMapper;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThirdService {

    @Autowired
    private IProductService productService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private LongMapper mapper;

    public Page<ProductSFDto> pageProductSFDto(Page<ProductSF> pageEntity){
        List<ProductSFDto> productSFList = mapper.mapperProductSFDto(pageEntity.getContent());
        Page<ProductSFDto> page = new PageImpl<ProductSFDto>(productSFList, pageEntity.getPageable(), pageEntity.getTotalElements());
        return page;
    }

//    public Page<ProductDto> getProducts(String asc, String desc, String sort_size, String category, int offset) {
//        String sort_temp = "";
//        if (sort_size != null) {
//            sort_temp = "sort_size";
//        }
//
//        String action = asc + desc + sort_temp;
//        String temp = Arrays.toString(action.split("null"));
//        switch (temp) {
//            case "[asc]": {
//                Page<ProductSF> productSFS = productService.findOrderByPriceASC(category, offset, 16);
//                Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
//                return productDtos;
//            }
//            case "[desc]": {
//                Page<ProductSF> productSFS = productService.findOrderByPriceDESC(category, offset, 16);
//                Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
//                return productDtos;
//            }
//            case "[asc, sort_size]": {
//                Page<ProductSF> productSFS = productService.findCategoryAndSizeOrderByPriceAsc(category, sort_size, offset, 16);
//                Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
//                return productDtos;
//            }
//            case "[, descsort_size]": {
//                Page<ProductSF> productSFS = productService.findCategoryAndSizeOrderByPriceDesc(category, sort_size, offset, 16);
//                Page<ProductDto> productDtos = requestMapper.productDtoPage(productSFS);
//                return productDtos;
//            }
//            default: {
//                Page<ProductSF> productSFS = productService.findProductWithPagination(category, offset, 16);
//                Page<ProductDto> productDto = requestMapper.productDtoPage(productSFS);
//                return productDto;
//            }
//        }
//    }
}
