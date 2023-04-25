package com.codegym.springboot_modul_6.service.thirdpartyservice;

import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Categories;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Promos;
import com.codegym.springboot_modul_6.model.fe_sf_model.entity.Province;
import com.codegym.springboot_modul_6.repository.fe_sf_repository.IPromoRepository;
import com.codegym.springboot_modul_6.service.fe_sf_service.CategoryService;
import com.codegym.springboot_modul_6.service.fe_sf_service.impl.ProvinceServiceIplm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryCache {

    public static final String CATEGORY = "CATEGORY";

    public static final String PROVINCE = "PROVINCE";

    public static final String PROMOS = "PROMOS";

    @Autowired
    private static final Map<String, List<?>> cacheCategories = new HashMap<>();

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProvinceServiceIplm provinceServiceIplm;

    @Autowired
    private IPromoRepository iPromoRepository;


    private static final CategoryCache CATEGORY_CACHE = new CategoryCache();

    public static CategoryCache getCategoryCache() {
        return CATEGORY_CACHE;
    }

    @PostConstruct
    public void init() {
        List<Promos> promos = iPromoRepository.findAll();
        List<Categories> categories = (List<Categories>) categoryService.findAll();
        List<Province> provinces = (List<Province>) provinceServiceIplm.findAll();
        cacheCategories.put(CATEGORY, categories);
        cacheCategories.put(PROVINCE, provinces);
        cacheCategories.put(PROMOS, promos);
    }


    public Map<String, List<?>> getCacheCategories() {
        return cacheCategories;
    }

    public CategoryCache() {
    }

}
