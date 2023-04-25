package com.codegym.springboot_modul_6.service.fe_sf_service;

import com.codegym.springboot_modul_6.model.fe_bo_model.dto.request.RequestProductGeneralInfoDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.ProductSF;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.fe_sf_model.dto.ProductSFDto;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ProductService extends GeneralService<ProductSF> {

    List<ProductSF> findAll(String category);

    Page<ProductSF> getAllByCategory(String category, String sort, int offset, int pageSize);

    Page<ProductSF> getMaxMinPriceProduct(Double min, Double max, String category,int offset, int pageSize);

    Page<ProductSF> findAllPaging(int offset, int pageSize);


    List<ProductSF> productSFS();

//    ProductSFDto getProductSFDto(String packageId);
//
//
//    ProductSFDetailDto getProductSFDetailDtoByColorAndSize(String color, String size, String packageId) throws ParseException;
//

    ProductSFDto getProductSFDto(String name);

    ProductSFDetailDto getProductSFDetailDtoByColorAndSize(String color, String size, String name) throws ParseException, ParseException;

    Page<ProductSF> getByName(String category, String name, int offset, int i);

    ProductSF mapProductSF(ProductSFDto productSFDto);

    Page<ProductSF> productService_getRandomProduct(int offset, int pageSize);

    Boolean updateProductGeneralInfo(RequestProductGeneralInfoDto requestProductGeneralInfoDto);

    Page<ProductSF> getProductOfStore(int offset, int pageSize, String productName);

    ProductSFDetailDto getProductSFDetailDtoByColor(String color, String name) throws ParseException, ParseException;
}
