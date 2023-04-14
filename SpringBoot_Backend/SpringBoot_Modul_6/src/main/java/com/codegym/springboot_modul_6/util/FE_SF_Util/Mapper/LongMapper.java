package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.*;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.*;
import com.codegym.springboot_modul_6.model.FE_SF_Model.model.CartDetailModel;
import com.codegym.springboot_modul_6.model.FE_SF_Model.model.CartModel;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductDetailSFRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LongMapper {

    @Autowired
    private IProductDetailSFRepository iProductDetailSFRepository;

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
        ProductSFDetailDto productSFDetailDto = new ProductSFDetailDto();
        BeanUtils.copyProperties(productSFDetails.get(0), productSFDetailDto);
        productSFDetailDtos.add(productSFDetailDto);
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
            BeanUtils.copyProperties(cartDetailDtos.get(i), cartDetailDto);
            cartDetailSFS.add(cartDetailDto);
        }
        return cartDetailSFS;
    }

    public Optional<CartModel> cartModel (CartSF cartSF){
        Optional<CartModel> cartModelTemp = Optional.of(new CartModel());
        cartModelTemp.orElseThrow().setAccountName(cartSF.getAccountName());
        cartModelTemp.orElseThrow().setTotalPrice(cartSF.getTotalPrice());
        cartModelTemp.orElseThrow().setCartDetailModelList(cartDetailModelList(cartSF.getCartDetailSFS()));
        return cartModelTemp;
    }

    private List<CartDetailModel> cartDetailModelList(List<CartDetailSF> cartDetailSFS){
        List<CartDetailModel> temp = new ArrayList<>();
        for (CartDetailSF c: cartDetailSFS
             ) {
            CartDetailModel cartDetailModel = new CartDetailModel();
            BeanUtils.copyProperties(c, cartDetailModel);
            cartDetailModel.setSize_color_img_quantity(getJSONSQLString(c.getSerialNumber()));
            temp.add(cartDetailModel);
        }
        return temp;
    }

    private String getJSONSQLString (String serialNumber){
        ProductSFDetail productSFDetail = iProductDetailSFRepository.getProductSFDetail(serialNumber).orElseThrow();
        return productSFDetail.getSize_color_img_quantity();
    }
}
