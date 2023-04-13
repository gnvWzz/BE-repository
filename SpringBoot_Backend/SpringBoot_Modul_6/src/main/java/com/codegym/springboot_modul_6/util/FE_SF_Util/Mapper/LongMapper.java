package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.*;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LongMapper {

    public List<CategoriesDto> mapperCategories(List<Categories> categories) {
        List<CategoriesDto> list = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            CategoriesDto categoriesDto = new CategoriesDto();
            BeanUtils.copyProperties(categories.get(i), categoriesDto);
            list.add(categoriesDto);
        }
        return list;
    }

    public List<ProductSFDto> mapperProductSFDto(List<ProductSF> productSFS){
        List<ProductSFDto> productSFDtos = new ArrayList<>();
        for (ProductSF p: productSFS
             ) {
            ProductSFDto productSFDto = new ProductSFDto();
            BeanUtils.copyProperties(p, productSFDto);
            productSFDto.setProductSFDetailDtos(mapperProductDetailDto(p.getProductSFDetail()));
            productSFDtos.add(productSFDto);
        }
        return productSFDtos;
    }

    private List<ProductSFDetailDto> mapperProductDetailDto(List<ProductSFDetail> productSFDetails){
        List<ProductSFDetailDto> productSFDetailDtos = new ArrayList<>();
        for (ProductSFDetail p: productSFDetails
             ) {
            ProductSFDetailDto productSFDetail = new ProductSFDetailDto();
            BeanUtils.copyProperties(p, productSFDetail);
            productSFDetailDtos.add(productSFDetail);
        }
        return productSFDetailDtos;
    }

    public CartSF mapperCart(CartDto cartDto){
        CartSF cartSF = new CartSF();
        BeanUtils.copyProperties(cartDto, cartSF);
        cartSF.setCartDetailSFS(cartDetailSFS(cartDto.getCartDetailDtos()));
        return cartSF;
    }

    private List<CartDetailSF> cartDetailSFS(List<CartDetailDto> cartDetailDtos){
        List<CartDetailSF> cartDetailSFS = new ArrayList<>();
        for (int i = 0; i < cartDetailDtos.size(); i++){
            CartDetailSF cartDetailDto = new CartDetailSF();
            BeanUtils.copyProperties(cartDetailDtos.get(0), cartDetailDto);
            cartDetailSFS.add(cartDetailDto);
        }
        return cartDetailSFS;
    }
}
