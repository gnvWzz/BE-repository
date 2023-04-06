package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import org.springframework.data.domain.Page;
import java.util.List;

public interface IProductService extends IGeneralService<ProductSF> {

    List<ProductSF> findAll(String category);

    Page<ProductSF> getAll(String category,String sortPrice, int offset, int pageSize);

}
