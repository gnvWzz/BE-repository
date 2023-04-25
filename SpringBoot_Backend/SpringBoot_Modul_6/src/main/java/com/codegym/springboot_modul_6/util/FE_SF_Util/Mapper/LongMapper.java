package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.*;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.*;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.CartDetailModel;
import com.codegym.springboot_modul_6.model.fe_sf_model.model.CartModel;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IProductDetailSFRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            productSFDto.setPriceListDtos(mapperPriceList(p.getPrices()));
            productSFDtos.add(productSFDto);
        }
        return productSFDtos;
    }

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
        cartModelTemp.orElseThrow().setCartDetailModelList(cartDetailModelList(cartSF.getCartDetailSFS()));
        cartModelTemp.orElseThrow().setTotalPrice(getTotalMoney(cartModelTemp.get().getCartDetailModelList()));
        return cartModelTemp;
    }

    private Double getTotalMoney (List<CartDetailModel> cartDetailModelList){
        double money = 0.0;
        for (CartDetailModel c: cartDetailModelList
             ) {
            money += c.getSubTotal();
        }
        return money;
    }

    private List<CartDetailModel> cartDetailModelList(List<CartDetailSF> cartDetailSFS){
        List<CartDetailModel> temp = new ArrayList<>();
        int cartIsNotDeleted = 0;
        for (CartDetailSF c: cartDetailSFS
             ) {
            if (Objects.equals(c.getIsDeleted(), "false")){
                CartDetailModel cartDetailModel = new CartDetailModel();
                BeanUtils.copyProperties(c, cartDetailModel);
                cartDetailModel.setSize_color_img_quantity(getJSONSQLString(c.getSerialNumber()));
                temp.add(cartDetailModel);
            }
        }
        return temp;
    }

    private String getJSONSQLString (String serialNumber){
        ProductSFDetail productSFDetail = iProductDetailSFRepository.getProductSFDetail(serialNumber).orElseThrow();
        return productSFDetail.getSize_color_img_quantity();
    }
}
