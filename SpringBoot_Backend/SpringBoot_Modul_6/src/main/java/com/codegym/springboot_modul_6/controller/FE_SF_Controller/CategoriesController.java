package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Categories;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.CategoriesDto;
import com.codegym.springboot_modul_6.service.FE_SF_Service.CategoriesService;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ICategoryService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.CategoryCache;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.LongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriesController {


    @Autowired
    private LongMapper longMapper;

    private CategoryCache categoryCache = CategoryCache.getCategoryCache();

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private ThirdService thirdService;


    @GetMapping(value = "/find-all")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(longMapper.mapperCategories(categoryCache.getCacheCategories().get(categoryCache.CATEGORY)), HttpStatus.OK);
    }
}