package com.codegym.springboot_modul_6.service.thirdpartyservice;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Categories;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryCache {

    public static final String CATEGORY = "CATEGORY";

    @Autowired
    public ICategoryService iCategoryService;

    private static final Map<String , List<Categories >> cacheCategories = new HashMap<>();

    private static final CategoryCache CATEGORY_CACHE = new CategoryCache();

    public static CategoryCache getCategoryCache(){
        return CATEGORY_CACHE;
    }

    @PostConstruct
    public void init(){
        List<Categories> categories =  (ArrayList<Categories>) iCategoryService.findAll();
        cacheCategories.put(CATEGORY, categories);
    }



    public Map<String , List<Categories >> getCacheCategories(){
        return cacheCategories;
    }

    public CategoryCache() {
    }


    public  void addCategories(ArrayList<Categories > catelogs){
        cacheCategories.put(CATEGORY, catelogs);
    }


    public void removeCategory(String name){
        cacheCategories.remove(name);
    }
}
