package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService extends IGeneralService<ProductSF> {
    List<ProductSF> findByCategory(String category);

    ProductSF findBySerialNumber(String serialNumber);

    Page<ProductSF> findProductWithPagination(String name, int offset, int pageSize);

    Page<ProductSF> findOrderByPriceASC(String category, int offset, int pageSize);

    Page<ProductSF> findOrderByPriceDESC(String category, int offset, int pageSize);

    Page<ProductSF> findCategoryAndSizeOrderByPriceAsc(String category, String size, int offset, int pageSize);

    Page<ProductSF> findCategoryByName(String category, String name, int offset, int pageSize);

    Page<ProductSF> findCategoryAndSizeOrderByPriceDesc(String category, String size, int offset, int pageSize);

    ProductSF findProductByNameAndManufacturerAndColorAndSize(String name, String manufacturer, String color, String size);

    List<ProductSF> findProductByNameAndManufacturer(String name, String manufacturer);
}
