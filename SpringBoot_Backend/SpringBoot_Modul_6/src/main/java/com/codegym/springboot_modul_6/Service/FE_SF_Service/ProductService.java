package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Image;
import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.Repository.FE_SF_Repository.IProductRepositorySF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepositorySF iProductRepositorySF;

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
    public List<ProductSF> findByCategory(String category){
        return iProductRepositorySF.findCategory(category);
    }

    @Override
    public ProductSF findBySerialNumber(String serialNumber) {
        ProductSF productSF = iProductRepositorySF.findBySerialNumber(serialNumber);
//        List<Image> imageList = productSF.getImageList();
//        System.out.println(imageList);
        return productSF;
    }
}
