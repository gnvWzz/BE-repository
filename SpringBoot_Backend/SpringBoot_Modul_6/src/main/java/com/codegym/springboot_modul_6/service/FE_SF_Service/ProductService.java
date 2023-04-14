//package com.codegym.springboot_modul_6.service.FE_SF_Service;
//
//import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
//import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductRepositorySF;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class ProductService implements IProductService {
//
//    @Autowired
//    private IProductRepositorySF productRepositorySF;
//
////    @Autowired
////    private ThirdService thirdService;
//
//    public static Map<String, ArrayList<String>> cache = new HashMap<>();
//
//    @Override
//    public Iterable<ProductSF> findAll() {
//        return productRepositorySF.findAll();
//    }
//
//    @Override
//    public Optional<ProductSF> findById(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public void save(ProductSF productSF) {
//
//    }
//
//    @Override
//    public void remove(Long id) {
//
//    }
//
//    @Override
//    public List<ProductSF> findAll(String category) {
//        return null;
//    }
//
//    @Override
//    public Page<ProductSF> getAllByCategory(String category, String sort, int offset, int pageSize) {
//        String action = sort;
//        if (action == null){
//            action =  "null";
//        }
//        switch (action) {
//            case "asc": {
//                Page<ProductSF> page = productRepositorySF.getAllProductPriceAsc(category, PageRequest.of(offset, pageSize));
//                return page;
//            }
//            case "desc": {
//                Page<ProductSF> page = productRepositorySF.getAllProductPriceDesc(category, PageRequest.of(offset, pageSize));
//                return page;
//            }
//            case "null": {
//                Page<ProductSF> page = productRepositorySF.getAllProductByCategory(category, PageRequest.of(offset, pageSize));
//                return page;
//            }
//            default: {
//                Page<ProductSF> page = productRepositorySF.getAllProductByName(category, sort, PageRequest.of(offset, pageSize));
//                return page;
//            }
//        }
//
//    }
//
//    @Override
//    public Page<ProductSF> findAllPaging(int offset, int pageSize) {
//        return productRepositorySF.getAll(PageRequest.of(offset, pageSize));
//    }
//
//
//    @Override
//    public List<ProductSF> productSFS() {
//        List<ProductSF> productSFS = productRepositorySF.findAll();
//        return productSFS;
//    }
//}
