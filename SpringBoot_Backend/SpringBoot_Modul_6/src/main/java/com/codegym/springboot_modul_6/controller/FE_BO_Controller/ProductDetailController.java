package com.codegym.springboot_modul_6.controller.FE_BO_Controller;

import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductDetailDto;
import com.codegym.springboot_modul_6.model.FE_BO_Model.dto.response.ResponseProductGeneralDto;
import com.codegym.springboot_modul_6.service.FE_BO_Service.ProductDetailService;
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
    public ResponseEntity<?> getProductDetailInfoBySerialNumber(@PathVariable String serialNumber){
        ResponseProductDetailDto responseProductDetailDto = productDetailService.findProductDetailInfoBySerialNumber(serialNumber);
        return new ResponseEntity<>(responseProductDetailDto, HttpStatus.OK);
    }
    @GetMapping("/general/{serialNumber}")
    public ResponseEntity<?> getProductGeneralInfoBySerialNumber(@PathVariable String serialNumber){
        ResponseProductGeneralDto responseProductGeneralDto = productDetailService.findProductGeneralInfoBySerialNumber(serialNumber);
        return new ResponseEntity<>(responseProductGeneralDto, HttpStatus.OK);
    }

    @PostMapping("/remove/{serialNumber}")
    public ResponseEntity<?> removeProductDetailBySerialNumber(@PathVariable String serialNumber){
        productDetailService.removeBySerialNumber(serialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
