package com.codegym.springboot_modul_6.controller.FE_SF_Controller;

import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ProductService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/product")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ThirdService thirdService;

    @GetMapping(value = "/{category}")
    public ResponseEntity<?> getAllByCategory(@PathVariable(value = "category") String category,
                                              @RequestParam(value = "offset") int offset,
                                              @RequestParam(required = false, value = "sort_price") String sortByPrice) {
        Page<ProductSF> productSFS = productService.getAll(category, sortByPrice, offset, 16);
        return new ResponseEntity<>(thirdService.pageProductSFDto(productSFS), HttpStatus.OK);
    }



}
