package com.codegym.springboot_modul_6.service.FE_SF_Service;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.IProductRepositorySF;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepositorySF productRepositorySF;

    @Autowired
    private ThirdService thirdService;

    public static Map<String, ArrayList<String>> cache = new HashMap<>();

    @Override
    public Iterable<ProductSF> findAll() {
        return productRepositorySF.findAll();
    }

    @Override
    public Optional<ProductSF> findById(Long id) {
        return productRepositorySF.findById(id);
    }

    @Override
    public void save(ProductSF productSF) {
        productRepositorySF.save(productSF);
    }

    @Override
    public boolean remove(Long id) {
        if (id != null) {
            ProductSF productSF = productRepositorySF.findById(id).orElse(null);
            if (productSF != null) {
                if (productSF.getStatus().equals("true")) {
                    productSF.setStatus("false");
                    productRepositorySF.save(productSF);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public List<ProductSF> findAll(String category) {
        return null;
    }

    @Override
    public Page<ProductSF> getAllByCategory(String category, String sort, int offset, int pageSize) {
        String action = sort;
        if (action == null){
            action =  "null";
        }
        switch (action) {
            case "asc": {
                Page<ProductSF> page = productRepositorySF.getAllProductPriceAsc(category, PageRequest.of(offset, pageSize));
                return page;
            }
            case "desc": {
                Page<ProductSF> page = productRepositorySF.getAllProductPriceDesc(category, PageRequest.of(offset, pageSize));
                return page;
            }
            case "null": {
                Page<ProductSF> page = productRepositorySF.getAllProductByCategory(category, PageRequest.of(offset, pageSize));
                return page;
            }
            default: {
//                Page<ProductSF> page = productRepositorySF.getAllProductByName(category, sort, PageRequest.of(offset, pageSize));
                return null;
            }
        }
    }

    @Override
    public Page<ProductSF> getMaxMinPriceProduct(Double min, Double max, String category, int offset, int pageSize){
        Page<ProductSF> productSFS = productRepositorySF.findProductsByMinPriceToMaxPrice(min, max, category,PageRequest.of(offset, pageSize));
        return productSFS;
    }

    @Override
    public Page<ProductSF> findAllPaging(int offset, int pageSize) {
        return productRepositorySF.getAll(PageRequest.of(offset, pageSize));
    }




    @Override
    public List<ProductSF> productSFS() {
        List<ProductSF> productSFS = productRepositorySF.findAll();
        return productSFS;
    }

    @Override
    public ProductSFDto getProductSFDto(String packageId) {
        return thirdService.getProductSFDto(packageId);
    }

    @Override
    public ProductSFDetailDto getProductSFDetailDtoByColorAndSize(String color, String size, String name) throws ParseException {
        return thirdService.getProductSFDetailDtoByColorAndSize(color, size, name);
    }


    @Override
    public Page<ProductSF> getByName(String category, String name, int offset, int pageSize) {
        Page<ProductSF> productNameSFPage = productRepositorySF.getAllProductByName(category, name, PageRequest.of(offset, pageSize));
        return productNameSFPage;
    }

    @Override
    public ProductSF mapProductSF(ProductSFDto productSFDto) {
        return thirdService.mapProductSF(productSFDto);
    }

    @Override
    public Page<ProductSF> productService_getRandomProduct(int offset, int pageSize){
        return productRepositorySF.productRepository_getRanDomProduct(PageRequest.of(offset, pageSize));
    }
}
