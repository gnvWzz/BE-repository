package com.codegym.springboot_modul_6.controller.FE_BO_Controller;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseStoreDto;
import com.codegym.springboot_modul_6.model.FE_SF_Model.dto.ProductSFDetailDto;
import com.codegym.springboot_modul_6.service.FE_SF_Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/productdetail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/{serialNumber}")
    public ResponseEntity<?> getProductDetailBySerialNumber(@PathVariable String serialNumber){
        ProductSFDetailDto productDetailDto = productDetailService.findBySerialNumber(serialNumber);
        return new ResponseEntity<>(productDetailDto, HttpStatus.OK);
    }

    @PostMapping("/remove/{serialNumber}")
    public ResponseEntity<?> removeProductDetailBySerialNumber(@PathVariable String serialNumber){
        productDetailService.removeBySerialNumber(serialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
