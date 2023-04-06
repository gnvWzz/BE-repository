package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductRepositorySF;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepositorySF productRepositorySF;

    @Autowired
    private ThirdService thirdService;

    public static Map<String , ArrayList<String>> cache = new HashMap<>();

    @Override
    public Iterable<ProductSF> findAll() {
        return productRepositorySF.findAll();
    }

    @Override
    public Optional<ProductSF> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(ProductSF productSF) {

    }

    @Override
    public void remove(Long id) {

    }
}
