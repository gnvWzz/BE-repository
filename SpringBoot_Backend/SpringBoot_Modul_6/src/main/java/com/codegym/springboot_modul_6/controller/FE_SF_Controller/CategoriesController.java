package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.Categories;
import com.codegym.springboot_modul_6.service.FE_SF_Service.CategoriesService;
import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriesController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping(value = "/find-all")
    public ResponseEntity<?> getAllCategories(){
        List<Categories> categoriesList = (List<Categories>) categoriesService.findAll();
        return new ResponseEntity<>(categoriesList, HttpStatus.OK);
    }

}