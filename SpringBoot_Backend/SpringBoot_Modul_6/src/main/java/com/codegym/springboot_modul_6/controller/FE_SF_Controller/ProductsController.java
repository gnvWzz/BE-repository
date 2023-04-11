package com.codegym.springboot_modul_6.controller.FE_SF_Controller;


import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.service.FE_SF_Service.IProductService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.ThirdService;
import com.codegym.springboot_modul_6.service.thirdpartyservice.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping("/api/product")
public class ProductsController {

    @Autowired
    private IProductService productService;

    public UserOnlineService userOnlineService = UserOnlineService.getUserOnlineService();

    @Autowired
    private ThirdService thirdService;

    @GetMapping(value = "/{category}")
    public ResponseEntity<?> getAll(@PathVariable(value = "category") String category,
                                    @RequestParam(required = true, value = "offset") int offset,
                                    @RequestParam(required = false, value = "sort") String sort) {
        Page<ProductSF> temp = productService.getAllByCategory(category, sort, offset, 16);
        return new ResponseEntity<>(thirdService.productSFDtoPage(temp), HttpStatus.OK);
    }
}

