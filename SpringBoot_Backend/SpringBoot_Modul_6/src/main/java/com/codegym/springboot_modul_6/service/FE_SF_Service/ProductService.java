package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductRepositorySF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

//    @Override
//    public List<ProductSF> findByCategory(String category){
//        return iProductRepositorySF.findProducts(category);
//    }
//
//    public void cache(String category){
////        List<ProductSF> list = findByCategory(category);
//        List<String> demo = new ArrayList<>();
//        demo.add("Hello");
//        demo.add("Baga");
//        demo.add("Sena");
//        cache.put("products", new ArrayList<>(demo));
//    }
//
//    @Override
//    public ProductSF findBySerialNumber(String serialNumber) {
//        ProductSF productSF = iProductRepositorySF.findBySerialNumber(serialNumber);
//        return productSF;
//    }
//
//    @Override
//    public Page<ProductSF> findProductWithPagination(String name, int offset, int pageSize){
//        Page<ProductSF> productSFS = iProductRepositorySF.findAllByCategory(name,PageRequest.of(offset, pageSize));
//        return productSFS;
//    }
//
//
//    @Override
//    public Page<ProductSF> findOrderByPriceASC(String category, int offset, int pageSize){
//        Page<ProductSF> productSFS = iProductRepositorySF.findAllByPriceOrderByPriceASC(category ,PageRequest.of(offset, pageSize));
//        return productSFS;
//    }
//
//    @Override
//    public Page<ProductSF> findOrderByPriceDESC(String category, int offset, int pageSize){
//        Page<ProductSF> productSFS = iProductRepositorySF.findAllByCategoryOrderByPriceDesc(category ,PageRequest.of(offset, pageSize));
//        return productSFS;
//    }
//
//    @Override
//    public Page<ProductSF> findCategoryAndSizeOrderByPriceAsc(String category, String size, int offset, int pageSize){
//        Page<ProductSF> productSFS = iProductRepositorySF.findAllByCategoryAndSizeOrderByPriceAsc(category, size, PageRequest.of(offset, pageSize));
//        return productSFS;
//    }
//
//    @Override
//    public Page<ProductSF> findCategoryByName(String category, String name, int offset, int pageSize){
//        Page<ProductSF> productSFS = iProductRepositorySF.findByNameProduct(category, name, PageRequest.of(offset, pageSize));
//        return productSFS;
//    }
//
//
//    @Override
//    public Page<ProductSF> findCategoryAndSizeOrderByPriceDesc(String category, String size, int offset, int pageSize){
//        Page<ProductSF> productSFS = iProductRepositorySF.findAllByCategoryAndSizeOrderByPriceDesc(category, size, PageRequest.of(offset, pageSize));
//        return productSFS;
//    }
//
//    @Override
//    public ProductSF findProductByNameAndManufacturerAndColorAndSize(String name, String manufacturer, String color, String size) {
//        ProductSF productSF = iProductRepositorySF.findByNameAndManufacturerAndColorAndSize(name, manufacturer, color, size);
//        return productSF;
//    }
//
//    @Override
//    public List<ProductSF> findProductByNameAndManufacturer(String name, String manufacturer) {
//        List<ProductSF> productSFS = iProductRepositorySF.findByNameAnAndManufacturer(name, manufacturer);
//        return productSFS;
//    }

    @Override
    public List<ProductSF> findAll(String category){
        List<ProductSF> productSFS = iProductRepositorySF.findAllProduct(category);
        return productSFS;
    }

    @Override
    public Page<ProductSF> getAll(String category, int offset, int pageSize){
        Page<ProductSF> productSFS = iProductRepositorySF.getAllProductByCategory(category, PageRequest.of(offset, pageSize));
        return productSFS;
    }
}
