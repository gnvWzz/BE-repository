package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestProductDetailInfoDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.request.RequestProductGeneralInfoDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import java.util.List;

public interface IProductService extends IGeneralService<ProductSF> {

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

}
