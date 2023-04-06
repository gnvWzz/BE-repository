package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductRepositorySF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepositorySF iProductRepositorySF;

    public static Map<String , ArrayList<String>> cache = new HashMap<>();

    @Override
    public Iterable<ProductSF> findAll() {
        return iProductRepositorySF.findAll();
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


    @Override
    public List<ProductSF> findAll(String category){
        List<ProductSF> productSFS = iProductRepositorySF.findAllProduct(category);
        return productSFS;
    }

    @Override
    public Page<ProductSF> getAll(String category, String sortPrice , int offset, int pageSize){
        String action = (category + sortPrice).toLowerCase();
        String temp = Arrays.toString(action.split("null"));
        System.out.println(temp);
        Page<ProductSF> productSFS = iProductRepositorySF.getAllProductByCategory(category, PageRequest.of(offset, pageSize));
        return productSFS;
    }
}
