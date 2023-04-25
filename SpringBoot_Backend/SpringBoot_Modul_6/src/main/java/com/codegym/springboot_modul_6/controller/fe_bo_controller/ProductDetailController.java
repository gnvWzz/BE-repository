package com.codegym.springboot_modul_6.controller.fe_bo_controller;

import com.codegym.springboot_modul_6.model.fe_bo_model.dto.request.RequestProductDetailInfoDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductDetailDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductInfoDto;
import com.codegym.springboot_modul_6.model.fe_bo_model.dto.response.ResponseProductGeneralDto;
import com.codegym.springboot_modul_6.service.fe_bo_service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping("/api/productdetail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/{serialNumber}")
    public ResponseEntity<?> getProductInfoBySerialNumber(@PathVariable String serialNumber){
        ResponseProductInfoDto responseProductInfoDto = productDetailService.findProductInfoBySerialNumber(serialNumber);
        if(responseProductInfoDto != null) {
            return new ResponseEntity<>(responseProductInfoDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/general/{serialNumber}")
    public ResponseEntity<?> getProductGeneralInfoBySerialNumber(@PathVariable String serialNumber){
        ResponseProductGeneralDto responseProductGeneralDto = productDetailService.findProductGeneralInfoBySerialNumber(serialNumber);
        if(responseProductGeneralDto != null) {
            return new ResponseEntity<>(responseProductGeneralDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/detail/{serialNumber}")
    public ResponseEntity<?> getProductDetailInfoBySerialNumber(@PathVariable String serialNumber){
        ResponseProductDetailDto responseProductDetailDto = productDetailService.findProductDetailInfoBySerialNumber(serialNumber);
        if(responseProductDetailDto != null) {
            return new ResponseEntity<>(responseProductDetailDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/remove/{serialNumber}")
    public ResponseEntity<?> removeProductDetailBySerialNumber(@PathVariable String serialNumber){
        Boolean isSuccess = productDetailService.removeBySerialNumber(serialNumber);
        if(isSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProductDetailInfo(@RequestBody RequestProductDetailInfoDto requestProductDetailInfoDto) {
        Boolean isSuccess = productDetailService.updateProductDetailInfo(requestProductDetailInfoDto);
        if(isSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
