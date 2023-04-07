package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSFDetail;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductRepositorySF;
//import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepositorySF productRepositorySF;

//    @Autowired
//    private ThirdService thirdService;

    public static Map<String, ArrayList<String>> cache = new HashMap<>();

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

    @Override
    public List<ProductSF> findAll(String category) {
        List<ProductSF> productSFS = productRepositorySF.findAllProduct(category);
        return productSFS;
    }

    @Override
    public Page<ProductSF> getAllByCategory(String category, String sortPrice, String sortName, int offset, int pageSize) {
        String nameTemp = "";
        if(sortName != null){
            nameTemp = "name";
        }
        String action =  (nameTemp + sortPrice).toLowerCase();
        String temp = Arrays.toString(action.split("null"));
        switch (temp){
            case "[asc]" :{
                Page<ProductSF> productSFS = productRepositorySF.getAllProductPriceAsc(category, PageRequest.of(offset, pageSize));
                return productSFS;
            }
            case "[desc]" : {
                Page<ProductSF> productSFS = productRepositorySF.getAllProductPriceDesc(category, PageRequest.of(offset, pageSize));
                return productSFS;
            }
            case "[name]" : {
                Page<ProductSF> productSFS = productRepositorySF.getAllProductByName(category, sortName, PageRequest.of(offset, pageSize));
                return productSFS;
            }
            default:{
                Page<ProductSF> productSFS = productRepositorySF.getAllProductByCategory(category, PageRequest.of(offset, pageSize));
                return productSFS;
            }
        }
    }

    @Override
    public Page<ProductSF> findAllPaging(int offset, int pageSize){
        return productRepositorySF.getAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public String test(){
        String  object = (productRepositorySF.findAll().get(0).getProductSFDetail().get(0).getSize_color_img_quantity()).replace("/", "");
        return object;
    }

    @Override
    public List<ProductSF> productSFS() {
        List<ProductSF> productSFS = productRepositorySF.findAll();
        return productSFS;
    }
}
