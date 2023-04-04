package com.codegym.springboot_modul_6.service.FE_SF_Service;

<<<<<<< HEAD:SpringBoot_Backend/SpringBoot_Modul_6/src/main/java/com/codegym/springboot_modul_6/service/FE_SF_Service/IProductService.java
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
=======
import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.ProductSF;
import org.springframework.data.domain.Page;
>>>>>>> 815825339867bd4ca60d12e7bb6781ce959ee491:SpringBoot_Backend/SpringBoot_Modul_6/src/main/java/com/codegym/springboot_modul_6/Service/FE_SF_Service/IProductService.java

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
}
