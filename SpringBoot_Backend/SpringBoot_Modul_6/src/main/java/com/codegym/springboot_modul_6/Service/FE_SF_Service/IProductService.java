package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.ProductSF;

import java.util.List;

public interface IProductService extends IGeneralService<ProductSF> {
    List<ProductSF> findByCategory(String category);
}
