package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.util.FE_SF_Util.Mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/categories")
public class CategoriesController {

    @Autowired
    private RequestMapper requestMapper;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
