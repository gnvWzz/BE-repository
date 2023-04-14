package com.codegym.springboot_modul_6.controller.FE_SF_Controller;


import com.codegym.springboot_modul_6.model.FE_SF_Model.Entity.ProductSF;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDto;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/product")
public class ProductsController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ThirdService thirdService;

    @GetMapping(value = "/{category}")
    public ResponseEntity<?> getAllByCategory(@PathVariable(value = "category") String category,
                                    @RequestParam(required = true, value = "offset") int offset,
                                    @RequestParam(required = false, value = "sort") String sort) {
        Page<ProductSF> temp = productService.getAllByCategory(category, sort, offset, 16);
        return new ResponseEntity<>(thirdService.productSFDtoPage(temp), HttpStatus.OK);
    }

    @GetMapping(value = "/get_home")
    public ResponseEntity<?> getAll(@RequestParam(required = true, value = "offset") int offset){
        Page<ProductSF> temp = productService.findAllPaging(offset, 16);
        return new ResponseEntity<>(thirdService.productSFDtoPage(temp), HttpStatus.OK);
    }

    @GetMapping("/package-id-product/{package-id}")
    public ResponseEntity<?> getProductByProductId(@PathVariable("package-id")String packageId) {
        ProductSFDto productSFDto = productService.getProductSFDto(packageId);
        return new ResponseEntity<>(productSFDto, HttpStatus.OK);
    }

    @GetMapping("/find-product-detail-by-color-and-size/{color}/{size}/{package_id}")
    public ResponseEntity<?> getProductDetailByColorAndSize(@PathVariable("color") String color,
                                                            @PathVariable("size") String size,
                                                            @PathVariable("package_id") String packageId) {
        ProductSFDetailDto productSFDetailDto = productService.getProductSFDetailDtoByColorAndSize(color, size, packageId);
        return new ResponseEntity<>(productSFDetailDto, HttpStatus.OK);
    }
}
