package com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper;


import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Categories;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Image;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Account;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.CategoriesDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ImageDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.AccountDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;



import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestMapper {
    @Autowired
    private ModelMapper mapper;

    public Account toAccount(AccountDto accountDto){
        return mapper.map(accountDto, Account.class);
    }

//    Long them categoriesdto
//    public List<CategoriesDto> categoriesDtos(List<Categories> list){
//        return list.stream().map(categories -> mapper.map(categories, CategoriesDto.class)).collect(Collectors.toList());
//    }
//
//    public List<ProductSFDto> productDtos(List<ProductSF> list){
//        return list.stream()
//                .map(product -> mapper.map(product, ProductSFDto.class))
//                .collect(Collectors.toList());
//    }
//
//    public List<ImageDto> imageDTOList(List<Image> list) {
//        return list.stream()
//                .map(image -> mapper.map(image, ImageDto.class))
//                .collect(Collectors.toList());
//    }
//
//    public ProductSFDto productDto(ProductSF productSF) {
//        return mapper.map(productSF, ProductSFDto.class);
//    }
//
//    public AccountDto toAccountDto ( Account account){
//        return mapper.map(account, AccountDto.class);
//    }
//
//    public Page<ProductSFDto> productDtoPage(Page<ProductSF> productSFS){
//        Page<ProductSFDto> productDtos = mapper.map(productSFS, new TypeToken<Page<ProductSFDto>>() {}.getType());
//        return productDtos;
//    }
}
