package com.codegym.springboot_modul_6.service.thirdpartyservice;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Categories;
import com.codegym.springboot_modul_6.repository.FE_SF_Repository.ICategoriesRepository;
import com.codegym.springboot_modul_6.service.FE_SF_Service.CategoriesService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryCache {

    public final String CATEGORY = "CATEGORY";

    public static ICategoryService iCategoryService = CategoriesService.getCategoriesService();

    private static final CategoryCache CATEGORY_CACHE = new CategoryCache();

    public static CategoryCache getCategoryCache(){
        return CATEGORY_CACHE;
    }


//    Hoi anh Chinh lai
//    static {
//        iCategoryService.findAll();
//    }

    private Map<String , ArrayList<Categories >> cacheCategories = new HashMap<>();

    public Map<String , ArrayList<Categories >> getCacheCategories(){
        return cacheCategories;
    }

    public CategoryCache() {
    }

    public CategoryCache(Map<String, ArrayList<Categories>> cacheCategories) {
        this.cacheCategories = cacheCategories;
    }


    public  void addCategories(ArrayList<Categories > catelogs){
        cacheCategories.put(CATEGORY, catelogs);
    }


    public void removeCategory(String name){
        cacheCategories.remove(name);
    }
}
